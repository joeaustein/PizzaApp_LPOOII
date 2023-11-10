/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Model;

import PizzaApp.DAO.PizzaDAO;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Locale;

/**
 *
 * @author joeau
 */
public class Pizza {
    // ------------------Atributos:------------------ //
    private int idItem;
    private int nrPedido;
    private Forma forma;
    private Sabor sabor1;
    private Sabor sabor2;
    private double area;
    private double valor;
    
    // ------------------Métodos:------------------ //
    // Contructor 01 (para novo item, com inserção na base):
    public Pizza(int nrPedido, Forma forma, Sabor sabor01, Sabor sabor02) throws SQLException {
        // Preenche atributos:
        this.nrPedido = nrPedido;
        this.forma = forma;
        this.setArea(forma.getArea());
        this.sabor1 = sabor01;
        this.sabor2 = sabor02;
        calcularValor();
        try {
            // Insercao na base retorna o id:
            this.idItem = inserirPizza(this);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        System.out.println("Obj Pizza criado! C01");       
    }
    // Contructor 02 (para itens recuperados da base, precisara montar alguns objetos para associar):
    public Pizza(int idItem, int nrPedido, String forma, double area, int sabor01, int sabor02) throws SQLException {
        this.idItem = idItem;
        this.nrPedido = nrPedido;
        // como recuperamos a area, passamos ela e calculamos a dimensão a partir disso:
        this.forma = montaForma(forma, area, 0) ;  
        this.setArea(area);
        try {
            this.sabor1 = new Sabor().recuperarSabor(sabor01);
            this.sabor2 = new Sabor().recuperarSabor(sabor02);
            // Preenche valor:
            calcularValor();
        } catch (SQLException e) {
            throw new SQLException(e);
        } 
        System.out.println("Obj Pizza criado! C02");   
    } 
    // Contructor opcional:
    public Pizza(){}
    // ---------------------------------------------- //
    // Get's & Set's:
    public int getIdItem(){
        return idItem;
    }
    public int getNrPedido(){
        return nrPedido;
    }
    public void setNrPedido(int nrPedido){
        this.nrPedido = nrPedido;
    }
    public Forma getForma(){
        return forma;
    }
    public void setForma(Forma forma){
        this.forma = forma;
    }
    public Sabor getSabor1(){
        return sabor1;
    }
    public void setSabor1(Sabor sabor){
        this.sabor1 = sabor;
    }
    public Sabor getSabor2(){
        return sabor2;
    }
    public void setSabor2(Sabor sabor){
        this.sabor2 = sabor;
    }
    public double getValor(){
        return valor;
    }
    public void setValor(double valor){
        String valorFormatado = String.format(Locale.US,"%.2f",valor);         
        this.valor = Double.parseDouble(valorFormatado);
    }
    public double getArea(){
        return area;
    }
    public void setArea(double area){
        String valorFormatado = String.format(Locale.US,"%.2f",area);         
        this.area = Double.parseDouble(valorFormatado);
    }
    // ---------------------------------------------- //
    // Demais métodos:
    // Calcula valor de acordo com a area e o tipo dos sabores da pizza:
    public void calcularValor(){
        System.out.println("Obj Pizza - Iniciando calcularValor..."); 
        double valorcm2 = 0;
        // Essa função vai calcular o valor considerando a area e o tipo de cada sabor:
        if(sabor1 != null) {
            if(sabor1.getIdSabor() != 0) {
                valorcm2 = sabor1.getTipoSabor().getValorCm2();
            }
        }
        if(sabor2 != null) {
            if(sabor2.getIdSabor() != 0) {
                if(valorcm2 > 0) {
                    // Se entrar aqui, é porque vieram os dois sabores!
                    // Vamos calcular a media:
                    valorcm2 = (valorcm2 + sabor2.getTipoSabor().getValorCm2()) / 2;
                } else {
                    // Se apenas o sabor2 tiver sido preenchido:
                    valorcm2 = sabor2.getTipoSabor().getValorCm2();
                }
            }
        }
        this.setValor(forma.getArea() * valorcm2); 
    }
    // Monta a forma a partir do preenchido pelo cliente ou recuperado na base:
    public Forma montaForma(String forma, double area, double dimensao) {
        System.out.println("Obj Pizza - Iniciando montaForma..."); 
        Forma objForma = null;
        // Monta a forma de acordo com o tipo:
        if(forma.equals("CIRCULO")) {
                objForma  = new Circulo(area, dimensao);
        } else if(forma.equals("QUADRADO")) {
                objForma = new Quadrado(area, dimensao);
        } else if(forma.equals("TRIANGULO")) {
                objForma = new Triangulo(area, dimensao);
        }  
        return objForma;
    }
    // Inserir (retorna o ID):
    public int inserirPizza(Pizza pizza) throws SQLException {
        System.out.println("Obj Pizza - Iniciando inserirPizza..."); 
        int idGerado = 0;
        try {
            idGerado = new PizzaDAO().inserir(pizza);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return idGerado;
    }
    // Listar itens pedido:
    public ArrayList<Pizza> listarItensPorPedido(int nrPedido) throws SQLException {
        System.out.println("Obj Pizza - Iniciando listarItensPorPedido..."); 
        ArrayList<Pizza> itens = null;
        try {
            itens = new PizzaDAO().listarItensPedido(nrPedido);
        } catch (SQLException e) {
            throw new SQLException(e);
        }    
        return itens;
    }
    // Limpar itens pedido:
    public void limparItensPorPedido(int nrPedido) throws SQLException {
        System.out.println("Obj Pizza - Iniciando limparItensPorPedido..."); 
        try {
            new PizzaDAO().limparItensPedido(nrPedido);
        } catch (SQLException e) {
            throw new SQLException(e);
        }    
    }
    // Excluir item pedido:
    public void excluirItemPedido(int nrPedido, int idItem) throws SQLException {
        System.out.println("Obj Pizza - Iniciando excluirItemPedido..."); 
        try {
            new PizzaDAO().excluirItemPedido(nrPedido, idItem);
        } catch (SQLException e) {
            throw new SQLException(e);
        }    
    }
    // Adicionar item ao pedido:
    public void adicionarItemPedido(int nrPedido, String forma, Sabor sabor1, Sabor sabor2, double area, double dimensao) throws SQLException {
        System.out.println("Obj Pizza - Iniciando adicionarItemPedido..."); 
        Forma objForma = null;
        // Monta forma:
        objForma = montaForma(forma, area, dimensao);
        try {
            new Pizza(nrPedido, objForma, sabor1, sabor2);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // ---------------------------------------------- // 
}
