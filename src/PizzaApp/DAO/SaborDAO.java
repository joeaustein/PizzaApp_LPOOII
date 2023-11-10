/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.DAO;

import PizzaApp.Model.Sabor;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joeau
 */
public class SaborDAO {
    // ------------------Atributos:------------------ //
    // Sql's:
    private final String sqlListar = 
        "select * from sabor   " +
        "where idsabor <> 0 "; 
    
    private final String sqlRecuperaSabor =
        "select * from sabor     " +
        "where idsabor = ?      ";    
    
    private final String sqlCadastrar = 
        "insert into sabor              " +
        "    (idtipo, nomesabor)    " +    
        "values (?, ?)                      ";
    
    // ------------------Métodos:------------------ //
    public ArrayList<Sabor> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        ArrayList<Sabor> sabores = null;
        int id;
        int tipo;
        String nome;     
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlListar);
            // ResultSet:
            ResultSet rs = pstmt.executeQuery(sqlListar);
            // Recuperação dos dados:
            if(rs != null){
                sabores = new ArrayList<Sabor>();
                while(rs.next()) {
                    id = rs.getInt("idsabor");
                    tipo = rs.getInt("idtipo");
                    nome = rs.getString("nomesabor");          
                    // Cria Obj:
                    Sabor sabor = new Sabor(id, tipo, nome);    
                    // Adiciona na lista:
                    sabores.add(sabor);
                }  
            }            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao listar sabores: " + e);
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
        return sabores;
    }
    // ---------------------------------------------- //
    public Sabor montarSaborPorID(int idSabor) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
     
        Sabor sabor = null;
        int tipoSabor;
        String nome;

        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlRecuperaSabor);
            pstmt.setInt(1, idSabor);
            // ResultSet:
            ResultSet rs = pstmt.executeQuery();
            // Recuperação dos dados:
            if(rs != null && rs.next()){
                tipoSabor = rs.getInt("idtipo");
                nome = rs.getString("nomesabor");
                // Cria Obj:
                sabor = new Sabor(idSabor, tipoSabor, nome) ;      
            }            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao buscar sabor: " + e);
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
        return sabor;
    }
    // ---------------------------------------------- //
        public void cadastrarSabor(int idTipo, String nomeSabor) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlCadastrar);
            // Prepara o SQL:
            pstmt.setInt(1, idTipo);
            pstmt.setString(2, nomeSabor);
            // Executa:
            pstmt.execute();            
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar sabor: " + e);
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
