/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.DAO;

import PizzaApp.Model.Circulo;
import PizzaApp.Model.Pizza;
import PizzaApp.Model.Quadrado;
import PizzaApp.Model.Triangulo;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author joeau
 */
public class PizzaDAO {
    // ------------------Atributos:------------------ //
    // Sql's:
    private final String sqlInserir = 
        "insert into pizza     " +
        " (nrpedido, idsabor01, idsabor02, area, valor, forma) " +          
        "values (?, ?, ?, ?, ?, ?)";
    
    private final String sqlListarItensPedido =
        "select * from pizza    " +
        "where nrpedido = ?  ";    
    
    private final String sqlLimparItensPedido =
        "delete from pizza     " +
        "where nrpedido = ? ";
    
    private final String sqlExcluirItemPedido =
        "delete from pizza         " +
        "where nrpedido = ?     " +
        "and iditem = ?             ";
    
    // ------------------Métodos:------------------ //
    public int inserir(Pizza pizza) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int idItem = 0;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlInserir, Statement.RETURN_GENERATED_KEYS);
            // Prepara o SQL:
            pstmt.setInt(1, pizza.getNrPedido());
            if(pizza.getSabor1() != null) {
                pstmt.setInt(2, pizza.getSabor1().getIdSabor());
            } else {
                pstmt.setInt(2, 0);
            }
            if(pizza.getSabor2() != null) {
                pstmt.setInt(3, pizza.getSabor2().getIdSabor());
            } else {
                pstmt.setInt(3, 0);
            }
            pstmt.setDouble(4, pizza.getArea());
            pstmt.setDouble(5, pizza.getValor());
            // Insere forma de acordo com o tipo do obj forma:
            if(pizza.getForma() instanceof Circulo) {
                pstmt.setString(6, "CIRCULO");
            } else if(pizza.getForma() instanceof Quadrado) {
                pstmt.setString(6, "QUADRADO");
            } else if(pizza.getForma() instanceof Triangulo) {
                pstmt.setString(6, "TRIANGULO");
            } 
            // Executa:
            int rows = pstmt.executeUpdate(); 
            // Recupera id gerado e instancia pedido:
            ResultSet gk = pstmt.getGeneratedKeys();
            if (gk.next()) {
                idItem = gk.getInt(1);
            }      
        } catch (SQLException e) {
            throw new SQLException ("Erro ao inserir pizza: " + e);
        } finally {
            // Finaliza statement e conexão:
            try {
                if(pstmt != null) {pstmt.close();}
            } catch (SQLException e) {
                throw new SQLException ("Erro ao fechar statement: " + e);
            }
            try {
                if(conn != null) {conn.close();}
            } catch (SQLException e) {
                throw new SQLException ("Erro ao fechar conexao: " + e);
            }
        }
        return idItem;
    }    
    // ---------------------------------------------- //
    public ArrayList<Pizza> listarItensPedido(int nrPedido) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        ArrayList<Pizza> itens = null;
        int idItem;
        String forma;
        double area;
        int sabor01;
        int sabor02;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlListarItensPedido);
            pstmt.setInt(1, nrPedido);
            // ResultSet:
            ResultSet rs = pstmt.executeQuery();
            // Recuperação dos dados:
            if(rs != null){
                itens = new ArrayList<Pizza>();
                while(rs.next()) {
                    idItem = rs.getInt("iditem");
                    forma = rs.getString("forma");
                    area = rs.getDouble("area");
                    sabor01 = rs.getInt("idsabor01");
                    sabor02 = rs.getInt("idsabor02");
                    Pizza item = null;
                    try {
                        // Cria Obj:
                        item = new Pizza(idItem, nrPedido, forma, area, sabor01,sabor02);   
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(e.getMessage());
                    } catch (SQLException e) {
                        throw new SQLException(e);
                    } 
                    // Adiciona na lista:
                    itens.add(item);
                }  
            }            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao listar itens: " + e);
        } finally {
            // Finaliza statement e conexão:
            try {
                if(pstmt != null) {pstmt.close();}
            } catch (SQLException e) {
                throw new SQLException ("Erro ao fechar statement: " + e);
            }
            try {
                if(conn != null) {conn.close();}
            } catch (SQLException e) {
                throw new SQLException ("Erro ao fechar conexao: " + e);
            }
        } 
        return itens;
    }
    // ---------------------------------------------- //
    public void limparItensPedido(int nrPedido) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlLimparItensPedido);
            // Prepara o SQL:
            pstmt.setInt(1, nrPedido);
            // Executa:
            pstmt.execute();            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao limpar itens pedido: " + e);
        } finally {
            // Finaliza statement e conexão:
            try {
                if(pstmt != null) {pstmt.close();}
            } catch (SQLException e) {
                throw new SQLException ("Erro ao fechar statement: " + e);
            }
            try {
                if(conn != null) {conn.close();}
            } catch (SQLException e) {
                throw new SQLException ("Erro ao fechar conexao: " + e);
            }
        }     
    }
    // ---------------------------------------------- //
    public void excluirItemPedido(int nrPedido, int idItem) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlExcluirItemPedido);
            // Prepara o SQL:
            pstmt.setInt(1, nrPedido);
            pstmt.setInt(2, idItem);
            // Executa:
            pstmt.execute();            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao excluir item pedido: " + e);
        } finally {
            // Finaliza statement e conexão:
            try {
                if(pstmt != null) {pstmt.close();}
            } catch (SQLException e) {
                throw new SQLException ("Erro ao fechar statement: " + e);
            }
            try {
                if(conn != null) {conn.close();}
            } catch (SQLException e) {
                throw new SQLException ("Erro ao fechar conexao: " + e);
            }
        }     
    }
    // ---------------------------------------------- // 
}
