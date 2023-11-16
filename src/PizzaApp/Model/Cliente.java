/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Model;

import PizzaApp.DAO.ClienteDAO;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author joeau
 */
public class Cliente {
    // ------------------Atributos:------------------ //
    private String nome;
    private String sobreNome;
    private String telefone;
    private ClienteDAO clienteDAO = new ClienteDAO();
    // ------------------Métodos:------------------ //
    // Contructor 01:
    public Cliente(String nome, String sobreNome, String telefone){
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefone = telefone;  
        System.out.println("Obj Cliente criado! C01");   
    }
    // Contructor rápido:
    public Cliente(){}
    // ---------------------------------------------- //
    // Get's & Set's:
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getSobreNome(){
        return sobreNome;
    }
    public void setSobreNome(String sobreNome){
        this.sobreNome = sobreNome;
    }
    public String getTelefone(){
        return telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }    
    // ---------------------------------------------- //
    // Demais métodos:
    // Cadastrar:
    public void cadastrarCliente(Cliente cliente) throws SQLException {
        System.out.println("Obj Cliente - Iniciando cadastrarCliente..."); 
        try {
            clienteDAO.inserir(cliente);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Atualizar:
    public void atualizarCliente(Cliente cliente, String oldTel) throws SQLException {
        System.out.println("Obj Cliente - Iniciando atualizarCliente..."); 
        try {
            clienteDAO.atualizar(cliente, oldTel);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Excluir:
    public void excluirCliente(Cliente cliente) throws SQLException {
        System.out.println("Obj Cliente - Iniciando excluirCliente..."); 
        try {
            // Primeiro limpa os pedidos do cliente:
            new Pedido().limparPedidosCliente(cliente);
            clienteDAO.excluir(cliente);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Listar:
    public ArrayList<Cliente> listarClientes() throws SQLException {
        System.out.println("Obj Cliente - Iniciando listarClientes..."); 
        ArrayList<Cliente> clientes = null;
        try {
            clientes = clienteDAO.listar();
        } catch (SQLException e) {
            throw new SQLException(e);
        }  
        return clientes;
    }
    // Listar (com filtro):
    public ArrayList<Cliente> listarClientesComFiltro(String nome, String sobrenome, String telefone) throws SQLException {
        System.out.println("Obj Cliente - Iniciando listarClientesComFiltro..."); 
        ArrayList<Cliente> clientes = null;
        try {
            clientes = clienteDAO.listarComFiltro(nome, sobrenome, telefone);    
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return clientes;
    }
    // Monta cliente recuperado da base:
    public Cliente montarClientePorTel(String telefone) throws SQLException {
        System.out.println("Obj Cliente - Iniciando montarClientePorTel..."); 
        Cliente cliente = null;
        try {
            cliente = clienteDAO.montarClientePorTel(telefone);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return cliente;
    }
    // ---------------------------------------------- //
}
