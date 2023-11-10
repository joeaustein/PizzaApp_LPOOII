/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.DAO;

import PizzaApp.Model.Cliente;
import PizzaApp.Model.Sabor;
import PizzaApp.Model.TipoSabor;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joeau
 */
public class TipoSaborDAO {
    // ------------------Atributos:------------------ //
    // Sql's:
    private final String sqlTipoPorID = 
        "select * from tiposabor    " +
        "where idtipo = ?               "; 
    
    private final String sqlAtualizarValor =
        "update tiposabor   " +
        "set valorcm2 = ?     " +
        "where idtipo = ?     ";
    
    private final String sqlListar = 
        "select * from tiposabor   " +
        "where idtipo <> 0          "; 
    
    // ------------------Métodos:------------------ //
    public TipoSabor montarTipoPorID(int idTipo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        TipoSabor tipoSabor = null;       
        String nome;     
        double valor;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlTipoPorID);
            pstmt.setInt(1, idTipo);
            // ResultSet:
            ResultSet rs = pstmt.executeQuery();
            // Recuperação dos dados:
            if(rs != null && rs.next()){
                nome = rs.getString("nomecategoria");       
                valor = rs.getDouble("valorcm2");
                // Cria Obj:
                tipoSabor = new TipoSabor(idTipo, nome, valor);                
            }            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao buscar tipo sabor: " + e);
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
        return tipoSabor;   
    }
    // ---------------------------------------------- //
    public void atualizarValor(int idTipo, double valorCm2) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlAtualizarValor);
            // Prepara o SQL:
            pstmt.setDouble(1, valorCm2);
            pstmt.setInt(2, idTipo);
            // Executa:
            pstmt.execute();            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao atualizar valor: " + e);
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
    public ArrayList<TipoSabor> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        ArrayList<TipoSabor> tipos = null;
        int idTipo;
        String nome;
        double valorCm2;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlListar);
            // ResultSet:
            ResultSet rs = pstmt.executeQuery(sqlListar);
            // Recuperação dos dados:
            if(rs != null){
                tipos = new ArrayList<TipoSabor>();
                while(rs.next()) {
                    idTipo = rs.getInt("idtipo");
                    nome = rs.getString("nomecategoria");          
                    valorCm2 = rs.getDouble("valorcm2");
                    // Cria Obj:
                    TipoSabor tipo = new TipoSabor(idTipo, nome, valorCm2);    
                    // Adiciona na lista:
                    tipos.add(tipo);
                }  
            }            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao listar categorias: " + e);
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
        return tipos;
    }
    // ---------------------------------------------- //
}
