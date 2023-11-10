/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Model;

/**
 *
 * @author joeau
 */
public class Quadrado extends Forma{
    // ------------------Métodos:------------------ //
    // Contructor 01:
    public Quadrado(){
        super();    // Cria Obj Pai
        System.out.println("Obj Quadrado Criado! C01");
    }
    // Contructor 02:
    public Quadrado(double area, double dimensao){
        super(area, dimensao);    // Cria Obj Pai
        System.out.println("Obj Quadrado Criado! C02");
    }
    // ---------------------------------------------- //
    // Implementações metodos abstrados (polimorfismo) :
    @Override
    public void calcularArea(){
        setArea(getDimensao() * getDimensao());
    };
    @Override
    public void calcularDimensao(){
        setDimensao(Math.sqrt(getArea()));
    };
    // ---------------------------------------------- //
}
