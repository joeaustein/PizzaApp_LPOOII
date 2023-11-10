/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Controller;

import PizzaApp.Model.Sabor;
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
}
