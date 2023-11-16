/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Model;

import PizzaApp.DAO.SaborDAO;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author joeau
 */
public class Sabor {
    // ------------------Atributos:------------------ //
    private int idSabor;
    private TipoSabor tipoSabor;
    private String nomeSabor; 
    private SaborDAO saborDAO = new SaborDAO();
    // ------------------Métodos:------------------ //
    // Contructor 01 (utilizado na recuperação também):
    public Sabor(int id, int tipoSabor, String nome) throws SQLException {
        this.idSabor = id;
        this.nomeSabor = nome;
        try {
            this.tipoSabor = new TipoSabor().montarTipo(tipoSabor);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        System.out.println("Obj Sabor criado! C01");    
    }
    // Contructor opcional:
    public Sabor(){}
    // ---------------------------------------------- //
    // Get's & Set's:
    public int getIdSabor(){
        return idSabor;
    }
    public String getNomeSabor(){
        return nomeSabor;
    }
    public void setNomeSabor(String nome){
        this.nomeSabor = nome;
    }
    public TipoSabor getTipoSabor(){
        return tipoSabor;
    }
    public void setTipoSabor(TipoSabor tipo){
        this.tipoSabor = tipo;
    }
    // ---------------------------------------------- //
    // Demais métodos:
    // Listar:
    public ArrayList<Sabor> listarSabores() throws SQLException {
        System.out.println("Obj Sabor - Iniciando listarSabores..."); 
        ArrayList<Sabor> sabores = null;
        try {
            sabores = saborDAO.listar();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return sabores;
    }
    // Monta sabor recuperado da base: 
    public Sabor recuperarSabor(int idSabor) throws SQLException {
        System.out.println("Obj Sabor - Iniciando recuperarSabor..."); 
        Sabor sabor = null;
        try {
            sabor = saborDAO.montarSaborPorID(idSabor);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return sabor;
    }
    // Cadastrar:
    public void cadastrarSabor(int idTipo, String nomeSabor) throws SQLException {
        System.out.println("Obj Sabor - Iniciando cadastrarSabor..."); 
        try {
            saborDAO.cadastrarSabor(idTipo, nomeSabor);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Excluir:
    public void excluirSabor(int idSabor) throws SQLException {
        System.out.println("Obj Sabor - Iniciando excluirSabor..."); 
        try {
            saborDAO.excluir(idSabor);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // ---------------------------------------------- //
}
