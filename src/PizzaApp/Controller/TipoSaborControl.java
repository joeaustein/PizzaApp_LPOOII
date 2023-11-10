/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Controller;

import PizzaApp.Model.TipoSabor;
import PizzaApp.View.TelaAtualizarValores;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joeau
 */
public class TipoSaborControl {
    // ----------------------Métodos----------------------  //
    // Atualizar cliente:
    public void atualizarValor(int idTipo, String valorCm2Str) {
        // Regex para validação de nome e sobrenome:
        // Regex para validação do valor:
        String regex = "^[0-9.]+$";
        double valorCm2 = 0;
        // Faz a validação de entrada de dados:
        if  (valorCm2Str.matches(regex) && idTipo < 4 && idTipo > 0) {
            // conversão:
            if(valorCm2Str != null) {valorCm2 = Double.parseDouble(valorCm2Str);}  
            // Cria Obj e chama função:   
            try {
                new TipoSabor().atualizarValor(idTipo, valorCm2);
                new TelaAtualizarValores().apresentarMsg("Valor atualizado com sucesso!");
            } catch (SQLException e) {
                String msgErro = "Erro ao tentar atualizar valor! Msg: " + e.getMessage();
                System.out.println(msgErro);
                // Função para apresentar erro na View:
                new TelaAtualizarValores().apresentarMsg(msgErro);
            } 
        } else {
            new TelaAtualizarValores().apresentarMsg("Dados preenchidos incorretamente!");
        }
    }
    // ------------------------------------------------------ //
    public ArrayList<TipoSabor> listarTipos() {
        ArrayList<TipoSabor> tipos = null;
        try {
            tipos = new TipoSabor().listarTipos();
        } catch (SQLException e){
            String msgErro = e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaAtualizarValores().apresentarMsg(msgErro);
        }
        return tipos;
    } 
    // ------------------------------------------------------ //
}
