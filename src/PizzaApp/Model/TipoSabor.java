/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Model;

import PizzaApp.DAO.TipoSaborDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author joeau
 */
public class TipoSabor {
    // ------------------Atributos:------------------ //
    private int idTipo;
    private String nomeCategoria;
    private double valorCm2; 
    private TipoSaborDAO tipoSaborDAO = new TipoSaborDAO();
    // ------------------Métodos:------------------ //
    // Contructor 01 (utilizado na recuperação também):
    public TipoSabor(int idTipo, String nomeCategoria, double valorCm2){
        this.idTipo = idTipo;
        this.nomeCategoria = nomeCategoria;
        // Usamos o "set" aqui para armazenar com duas casas decimais:
        this.setValorCm2(valorCm2);
        System.out.println("Obj TipoSabor criado! C01");  
    }
    // Contructor opcional:
    public TipoSabor(){}
    // ---------------------------------------------- //
    // Get's & Set's:
    public int getIdTipo(){
        return idTipo;
    }
    public String getNomeCategoria(){
        return nomeCategoria;
    }
    public double getValorCm2(){
        return valorCm2;
    }
    public void setValorCm2(double valor){
        String valorFormatado = String.format(Locale.US,"%.2f",valor);        
        this.valorCm2 = Double.parseDouble(valorFormatado);
    }
    // ---------------------------------------------- //
    // Demais métodos:
    // Monta tipoSabor recuperado da base: 
    public TipoSabor montarTipo(int idTipo) throws SQLException {
        System.out.println("Obj TipoSabor - Iniciando montaTipo..."); 
        TipoSabor tipo = null;
        try {
            tipo = tipoSaborDAO.montarTipoPorID(idTipo);  
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return tipo;
    }
    // Atualizar valor:
    public void atualizarValor(int idTipo, double valorCm2) throws SQLException {
        System.out.println("Obj TipoSabor - Iniciando atualizarValor..."); 
        try {
            tipoSaborDAO.atualizarValor(idTipo, valorCm2);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Listar:
    public ArrayList<TipoSabor> listarTipos() throws SQLException {
        System.out.println("Obj TipoSabor - Iniciando listarTipos..."); 
        ArrayList<TipoSabor> tipos = null;
        try {
            tipos = tipoSaborDAO.listar();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return tipos;
    }
    // ---------------------------------------------- //
}
