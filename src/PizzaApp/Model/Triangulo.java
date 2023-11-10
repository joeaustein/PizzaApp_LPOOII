/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Model;

/**
 *
 * @author joeau
 */
public class Triangulo extends Forma{
    // ------------------Métodos:------------------ //
    // Contructor 01:
    public Triangulo(){
        super();    // Cria Obj Pai
        System.out.println("Obj Triangulo Criado! C01");
    }
    // Contructor 02:
    public Triangulo(double area, double dimensao){
        super(area, dimensao);    // Cria Obj Pai
        System.out.println("Obj Triangulo Criado! C02");
    }
    // ---------------------------------------------- //
    // Implementações metodos abstrados (polimorfismo) :
    @Override
    public void calcularArea(){
        setArea((getDimensao() * getDimensao() * Math.sqrt(3)) / 4);
    };
    @Override
    public void calcularDimensao(){
        setDimensao( Math.sqrt((4 * getArea()) / Math.sqrt(3)) );
    };
    // ---------------------------------------------- //
}