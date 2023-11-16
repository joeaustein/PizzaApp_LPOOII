/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Model;

import java.util.Locale;

/**
 *
 * @author joeau
 */
public abstract class Forma {
    // ------------------Atributos:------------------ //
    private double dimensao;
    private double area;
    // ------------------Métodos:------------------ //
    // Contructor 01:
    protected Forma(){
        this.area = 0.0;
        this.dimensao = 0.0;
        
        System.out.println("Obj Forma Criado! C01");
    };   
    // Constructor 02:
    protected Forma(double area, double dimensao) {
        // Seta uma medida para calcular a outra:
        if(area != 0) {
            setArea(area);
            calcularDimensao();
        } else if (dimensao != 0) {
            setDimensao(dimensao);
            calcularArea();
        }
        System.out.println("Obj Forma Criado! C02");
    }
    // ---------------------------------------------- //
    // Get's & Set's:
    public double getDimensao(){
        return dimensao;
    }
    public void setDimensao(double dimensao){
        String valorFormatado = String.format(Locale.US,"%.2f",dimensao);         
        this.dimensao = Double.parseDouble(valorFormatado);
    }
    public double getArea(){
        return area;
    }
    public void setArea(double area){
        String valorFormatado = String.format(Locale.US,"%.2f",area);         
        this.area = Double.parseDouble(valorFormatado);
    }
    // ---------------------------------------------- //
    // Forma para String:
    public String formaToString() {
        String forma = "";
        if(this instanceof Circulo) {
            forma = "CIRCULO";
        } else if(this instanceof Quadrado) {
            forma = "QUADRADO";
        } else if(this instanceof Triangulo) {
            forma = "TRIANGULO";
        } 
        return forma;
    }
    // Calculos (implementação nas classes filhas):
    protected abstract void calcularArea();
    protected abstract void calcularDimensao();
    // ---------------------------------------------- //
}
