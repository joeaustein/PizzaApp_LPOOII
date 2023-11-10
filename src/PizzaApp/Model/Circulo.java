/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Model;

/**
 *
 * @author joeau
 */
public class Circulo extends Forma{
    // ------------------Métodos:------------------ //
    // Contructor 01:
    public Circulo(){
        super();    // Cria Obj Pai
        System.out.println("Obj Circulo Criado! C01");
    }
    // Contructor 02:
    public Circulo(double area, double dimensao){
        super(area, dimensao);    // Cria Obj Pai
        System.out.println("Obj Circulo Criado! C02");
    }
    // ---------------------------------------------- //
    // Implementações metodos abstrados (polimorfismo) :
    @Override
    public void calcularArea(){
        setArea(Math.PI * Math.pow(getDimensao(), 2));
    };
    @Override
    public void calcularDimensao(){
        setDimensao(Math.sqrt(getArea() / Math.PI));
    };
    // ---------------------------------------------- //
}
