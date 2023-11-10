/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.DAO;

import PizzaApp.Model.Cliente;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author joeau
 */
public class ClienteDAO {
    // ------------------Atributos:------------------ //
    // Sql's:
    private final String sqlInserir = 
        "insert into cliente    " +
        "values (?, ?, ?)          ";
    
    private final String sqlListar = 
        "select * from cliente  "; 
        
    private final String sqlAtualizar = 
        "update cliente                                                 " + 
        "set nome = ?, sobrenome = ?, telefone = ?  " + 
        "where telefone = ?                                         ";    
    
    private final String sqlExcluir =
        "delete from cliente    " +
        "where telefone = ?    ";    
    
    private final String sqlBuscarPorTelefone =
        "select * from cliente   " +
        "where telefone = ?    ";
    
    // ------------------Métodos:------------------ //
    public void inserir(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlInserir);
            // Prepara o SQL:
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getSobreNome());
            pstmt.setString(3, cliente.getTelefone());
            // Executa:
            pstmt.execute();            
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir cliente: " + e);
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
    public ArrayList<Cliente> listar() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        ArrayList<Cliente> clientes = null;
        String nome;
        String sobreNome;
        String telefone;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlListar);
            // ResultSet:
            ResultSet rs = pstmt.executeQuery(sqlListar);
            // Recuperação dos dados:
            if(rs != null){
                clientes = new ArrayList<Cliente>();
                while(rs.next()) {
                    nome = rs.getString("nome");
                    sobreNome = rs.getString("sobrenome");
                    telefone = rs.getString("telefone");          
                    // Cria Obj:
                    Cliente cliente = new Cliente(nome, sobreNome, telefone);         
                    // Adiciona na lista:
                    clientes.add(cliente);
                }  
            }            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao listar clientes: " + e);
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
        return clientes;
    }
    // ---------------------------------------------- //
    public ArrayList<Cliente> listarComFiltro(String nome, String sobreNome, String telefone) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        ArrayList<Cliente> clientes = null;
        String nomeSalvo;
        String sobrenomeSalvo;
        String telefoneSalvo;
        boolean filtraNome = false;
        boolean filtraSobrenome = false;
        boolean filtraTelefone = false;
        
        // Vamos montar aqui a Query pois essa depende dos parametros:
        String sqlListarComFiltro = "select * from cliente where ";
        // Veio nome?
        if(!nome.equals("Nome") && !nome.equals("")) {
            // Acrescentamos trecho de busca por nome:
            sqlListarComFiltro = sqlListarComFiltro + 
                    "nome like ? ";
            filtraNome = true;
        }
        // Veio sobrenome?
        if(!sobreNome.equals("Sobrenome") && !sobreNome.equals("")) {
            // Se ja estiver filtrando por nome, adicionamos um "and":
            if(filtraNome) {
                sqlListarComFiltro = sqlListarComFiltro + "and ";
            }
            // Acrescentamos trecho de busca por sobrenome:
            sqlListarComFiltro = sqlListarComFiltro + 
                    "sobrenome like ? ";
            filtraSobrenome = true;
        }
        // Veio Telefone?
        if(!telefone.equals("Telefone") && !telefone.equals("")) {
            // Se ja estiver filtrando por nome ou sobrenome, adicionamos um "and":
            if(filtraNome || filtraSobrenome) {
                sqlListarComFiltro = sqlListarComFiltro + "and ";
            }
            // Acrescentamos trecho de busca por telefone:
            sqlListarComFiltro = sqlListarComFiltro + 
                    "telefone like ?";
            filtraTelefone = true;
        }
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlListarComFiltro);
            // Prepara o SQL:        
            if(filtraNome){
                pstmt.setString(1, "%" + nome + "%");
            }
            if(filtraSobrenome){
                if(filtraNome){
                    pstmt.setString(2, "%" + sobreNome + "%");
                } else {
                    pstmt.setString(1, "%" + sobreNome + "%");
                }
            }
            if(filtraTelefone){
                if(filtraNome && filtraSobrenome){
                    pstmt.setString(3, "%" + telefone + "%");
                } else if(filtraNome || filtraSobrenome){
                    pstmt.setString(2, "%" + telefone + "%");
                } else {
                    pstmt.setString(1, "%" + telefone + "%");
                }
            }
            // ResultSet:
            ResultSet rs = pstmt.executeQuery();
            // Recuperação dos dados:
            if(rs != null){
                clientes = new ArrayList<Cliente>();
                while(rs.next()) {
                    nomeSalvo = rs.getString("nome");
                    sobrenomeSalvo = rs.getString("sobrenome");
                    telefoneSalvo = rs.getString("telefone");          
                    // Cria Obj:
                    Cliente cliente = new Cliente(nomeSalvo, sobrenomeSalvo, telefoneSalvo);         
                    // Adiciona na lista:
                    clientes.add(cliente);
                }  
            }            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao listar clientes por filtro: " + e);
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
        return clientes;
    }
    // ---------------------------------------------- //
    public void atualizar(Cliente cliente, String oldTel) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlAtualizar);
            // Prepara o SQL:
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getSobreNome());
            pstmt.setString(3, cliente.getTelefone());
            pstmt.setString(4, oldTel);
            // Executa:
            pstmt.execute();            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao atualizar cliente: " + e);
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
    public void excluir(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlExcluir);
            // Prepara o SQL:
            pstmt.setString(1, cliente.getTelefone());
            // Executa:
            pstmt.execute();            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao excluir cliente: " + e);
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
    public Cliente montarClientePorTel(String telCliente) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        Cliente cliente = null;
        String nome;
        String sobreNome;
        String telefone;
        
        try {
            // Configura conexão:
            conn = new ConnectionFactory().getConnection();
            // Recebe um PreparedStatement:
            pstmt = conn.prepareStatement(sqlBuscarPorTelefone);
            pstmt.setString(1, telCliente);
            // ResultSet:
            ResultSet rs = pstmt.executeQuery();
            // Recuperação dos dados:
            if(rs != null && rs.next()){
                nome = rs.getString("nome");
                sobreNome = rs.getString("sobrenome");
                telefone = rs.getString("telefone");          
                // Cria Obj:
                cliente = new Cliente(nome, sobreNome, telefone);         
            }            
        } catch (SQLException e) {
            throw new SQLException ("Erro ao buscar cliente: " + e);
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
        return cliente;
    }
    // ---------------------------------------------- //
}
