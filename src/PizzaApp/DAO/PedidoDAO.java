/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.DAO;

import PizzaApp.Model.Cliente;
import PizzaApp.Model.Pedido;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author joeau
 */
public class PedidoDAO {
    // ------------------Atributos:------------------ //
    // Sql's:
    private final String sqlGerar = 
        "insert into pedido     " +
        "   (telcliente, status)  " +    
        "values (?, 0)               ";
    
    private final String sqlExclusaoPorCliente =
        "delete from pedido     " +
        "where telcliente = ?    ";    
    
    private final String sqlPedidoPendente =
        "select * from pedido     " +
        "where telcliente = ?      " +
        "and status <> 3            ";   
    
    private final String sqlRecuperaPedido =
        "select * from pedido     " +
        "where nrpedido = ?      ";    
    
    private final String sqlAtualizarStatus =
        "update pedido         " +
        "set status = ?            " +
        "where nrpedido = ? ";
    
    private final String sqlExcluir =
        "delete from pedido    " +
        "where nrpedido = ?   ";   
    
    // ------------------Métodos:------------------ //
    public Pedido gerarPedido(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Pedido pedido = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement: (Esse ira retornar a key gerada, que sera o nrPedido)
            pstmt = conn.prepareStatement(sqlGerar, Statement.RETURN_GENERATED_KEYS);
            // Prepara o SQL:
            pstmt.setString(1, cliente.getTelefone());
            // Executa:
            int rows = pstmt.executeUpdate(); 
            // Recupera id gerado e instancia pedido:
            ResultSet gk = pstmt.getGeneratedKeys();
            if (gk.next()) {
                int nrPedido = gk.getInt(1);
                pedido = new Pedido(nrPedido, cliente);    
            }  
        } catch (SQLException e) {
            throw new SQLException ("Erro ao gerar pedido: " + e);
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
        return pedido;
    }    
    // ---------------------------------------------- //
    public void excluirPorCliente(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlExclusaoPorCliente);
            // Prepara o SQL:
            pstmt.setString(1, cliente.getTelefone());
            // Executa:
            pstmt.execute();            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao excluir pedidos: " + e);
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
    public Pedido buscarPedidoPendente(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Pedido pedido = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlPedidoPendente);
            pstmt.setString(1, cliente.getTelefone());
            // ResultSet:
            ResultSet rs = pstmt.executeQuery();
            // Recuperação dos dados:
            if(rs != null && rs.next()){
                int nrPedido = rs.getInt("nrpedido");
                double valor = rs.getDouble("valor");
                int status = rs.getInt("status");
                // Cria e seta Obj:
                pedido = new Pedido(nrPedido ,cliente);       
                pedido.setValorTotal(valor);
                pedido.setStatus(status);
            }            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao buscar pedido em aberto: " + e);
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
        return pedido;
    }
    // ---------------------------------------------- //
    public void atualizarStatus(int nrPedido, int status) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlAtualizarStatus);
            // Prepara o SQL:
            pstmt.setInt(1, status);
            pstmt.setInt(2, nrPedido);
            // Executa:
            pstmt.execute();            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao atualizar status pedido: " + e);
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
    public void excluir(int nrPedido) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlExcluir);
            // Prepara o SQL:
            pstmt.setInt(1, nrPedido);
            // Executa:
            pstmt.execute();            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao excluir pedido: " + e);
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
    public Pedido montarPedidoPorID(int nrPedido) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        Pedido pedido = null;
        int status;
        String telCliente;

        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlRecuperaPedido);
            pstmt.setInt(1, nrPedido);
            // ResultSet:
            ResultSet rs = pstmt.executeQuery();
            // Recuperação dos dados:
            if(rs != null && rs.next()){
                status = rs.getInt("status");
                telCliente = rs.getString("telcliente");
                // Cria Obj:
                pedido = new Pedido(nrPedido, telCliente, status);      
            }            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao buscar pedido: " + e);
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
        return pedido;
    }
    // ---------------------------------------------- //
}