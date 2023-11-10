/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Controller;

import PizzaApp.Model.Sabor;
import PizzaApp.View.TelaCadastrarSabor;
import PizzaApp.View.TelaRealizarPedido;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author joeau
 */
public class SaborControl {
    // ----------------------Métodos----------------------  //
    public ArrayList<Sabor> listarSabores() {
        ArrayList<Sabor> sabores = null;
        try {
            sabores = new Sabor().listarSabores();
        } catch (SQLException e){
            String msgErro = e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaRealizarPedido().apresentarMsg(msgErro);
        }
        return sabores;
    } 
    // ------------------------------------------------------ //
    // Cadastrar sabor:
    public void cadastrarSabor(int idTipo, String nomeSabor) {
        // Regex para validação de nome:
        // (Aceita letras maiúsculas e minúsculas, acentuadas e espaços em branco)
        String regexNomes = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$"; 
        // Faz a validação de entrada de dados:
        if  ( (idTipo > 0 && idTipo < 4) && nomeSabor.matches(regexNomes) ) {
            // Armazenamos tudo em letras maiúsculas:
            nomeSabor = nomeSabor.toUpperCase();
            try {
                new Sabor().cadastrarSabor(idTipo, nomeSabor);
                new TelaCadastrarSabor().apresentarMsg("Sabor cadastrado com sucesso!");
            } catch (SQLException e) {
                String msgErro = "Erro ao tentar cadastrar sabor! Msg: " + e.getMessage();
                System.out.println(msgErro);
                // Função para apresentar erro na View:
                new TelaCadastrarSabor().apresentarMsg(msgErro);
            }         
        } else {
            new TelaCadastrarSabor().apresentarMsg("Dados preenchidos incorretamente!");
        }   
    }
    // ------------------------------------------------------ //
    // Excluir sabor:
    public void excluirSabor(int idSabor) { 
        try {
            new Sabor().excluirSabor(idSabor);
            new TelaCadastrarSabor().apresentarMsg("Sabor excluido com sucesso!");
        } catch (SQLException e) {
            String msgErro = "Erro ao tentar excluir sabor! Msg: " + e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaCadastrarSabor().apresentarMsg(msgErro);
        }    
    }
    // ------------------------------------------------------ //
}
