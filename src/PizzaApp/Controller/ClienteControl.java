/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Controller;

import PizzaApp.Model.Cliente;
import PizzaApp.View.TelaCadastrarCliente;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author joeau
 */

public class ClienteControl {
    // ----------------------Métodos----------------------  //
    // Cadastrar cliente:
    public void cadastrarCliente(String nome, String sobreNome, String telefone) {
        // Regex para validação de nome e sobrenome:
        // (Aceita letras maiúsculas e minúsculas, acentuadas e espaços em branco)
        String regexNomes = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$"; 
        // Regex para validação de telefone:
        String regexTelefone = "^[0-9]+$";
        // Faz a validação de entrada de dados:
        if  ( (nome != null && nome.matches(regexNomes) && !nome.trim().isEmpty()) 
            && (sobreNome != null && sobreNome.matches(regexNomes) && !sobreNome.trim().isEmpty()) 
            && (telefone != null && (telefone.length() == 10 || telefone.length() == 11) && telefone.matches(regexTelefone)) ) {
            // Armazenamos tudo em letras maiúsculas:
            nome = nome.toUpperCase();
            sobreNome = sobreNome.toUpperCase();
            telefone = telefone.toUpperCase();
            // Cria Obj e chama função:        
            Cliente cliente = new Cliente(nome, sobreNome, telefone);
            try {
                cliente.cadastrarCliente(cliente);
                new TelaCadastrarCliente().apresentarMsg("Cliente cadastrado com sucesso!");
            } catch (SQLException e) {
                String msgErro = "Erro ao tentar cadastrar cliente! Msg: " + e.getMessage();
                System.out.println(msgErro);
                // Função para apresentar erro na View:
                new TelaCadastrarCliente().apresentarMsg(msgErro);
            }         
        } else {
            new TelaCadastrarCliente().apresentarMsg("Dados preenchidos incorretamente!");
        }   
    }
    // ------------------------------------------------------ //
    // Atualizar cliente:
    public void atualizarCliente(String nome, String sobreNome, String telefone, String oldTel) {
        // Regex para validação de nome e sobrenome:
        // (Aceita letras maiúsculas e minúsculas, acentuadas e espaços em branco)
        String regexNomes = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$"; 
        // Regex para validação de telefone:
        String regexTelefone = "^[0-9]+$";
        // Faz a validação de entrada de dados:
        if  ( (nome != null && nome.matches(regexNomes) && !nome.trim().isEmpty()) 
            && (sobreNome != null && sobreNome.matches(regexNomes) && !sobreNome.trim().isEmpty()) 
            && (telefone != null && (telefone.length() == 10 || telefone.length() == 11) && telefone.matches(regexTelefone)) ) {
            // Armazenamos tudo em letras maiúsculas:
            nome = nome.toUpperCase();
            sobreNome = sobreNome.toUpperCase();
            telefone = telefone.toUpperCase();
            // Cria Obj e chama função:   
            Cliente cliente = new Cliente(nome, sobreNome, telefone);
            try {
                cliente.atualizarCliente(cliente, oldTel);
                new TelaCadastrarCliente().apresentarMsg("Cliente atualizado com sucesso!");
            } catch (SQLException e) {
                String msgErro = "Erro ao tentar atualizar cliente! Msg: " + e.getMessage();
                System.out.println(msgErro);
                // Função para apresentar erro na View:
                new TelaCadastrarCliente().apresentarMsg(msgErro);
            } 
        } else {
            new TelaCadastrarCliente().apresentarMsg("Dados preenchidos incorretamente!");
        }
    }
    // ------------------------------------------------------ //
    // Excluir cliente:
    public void excluirCliente(String nome, String sobreNome, String telefone) { 
        Cliente cliente = new Cliente(nome, sobreNome, telefone);
        try {
            cliente.excluirCliente(cliente);
            new TelaCadastrarCliente().apresentarMsg("Cliente excluido com sucesso!");
        } catch (SQLException e) {
            String msgErro = "Erro ao tentar excluir cliente! Msg: " + e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaCadastrarCliente().apresentarMsg(msgErro);
        }    
    }
    // ------------------------------------------------------ //
    // Listar clientes:
    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = null;
        try {
            clientes = new Cliente().listarClientes();
        } catch (SQLException e) {
                String msgErro = e.getMessage();
                System.out.println(msgErro);
                // Função para apresentar erro na View:
                new TelaCadastrarCliente().apresentarMsg(msgErro);
        }
        return clientes;
    }
    // ------------------------------------------------------ //
    // Listar clientes com filtro:
    public ArrayList<Cliente> listarClientesComFiltro(String nome, String sobrenome, String telefone) {
        ArrayList<Cliente> clientes = null;
        try {
            clientes = new Cliente().listarClientesComFiltro(nome, sobrenome, telefone);
        } catch (SQLException e) {
            String msgErro = e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaCadastrarCliente().apresentarMsg(msgErro);
        }
        return clientes;
    }
    // ------------------------------------------------------ //
}
