/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Controller;

import PizzaApp.Model.Circulo;
import PizzaApp.Model.Cliente;
import PizzaApp.Model.Forma;
import PizzaApp.Model.Pedido;
import PizzaApp.Model.Pizza;
import PizzaApp.Model.Quadrado;
import PizzaApp.Model.Sabor;
import PizzaApp.Model.Triangulo;
import PizzaApp.View.TelaCadastrarCliente;
import PizzaApp.View.TelaRealizarPedido;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author joeau
 */

public class PedidoControl {
    // ----------------------Métodos----------------------  //
    // Listar clientes para a tela de geração de pedido:  
    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = null;
        try {
            clientes = new Pedido().listarClientes();
        } catch (SQLException e) {
            String msgErro = e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaRealizarPedido().apresentarMsg(msgErro);
        }
        return clientes;
    }
    // ------------------------------------------------------ //
    // Listar clientes para a tela de geração de pedido (com filtro): 
    public ArrayList<Cliente> listarClientesComFiltro(String telefone) {
        ArrayList<Cliente> clientes = null;
        try {
            clientes = new Pedido().listarClientesComFiltro(telefone);
        } catch (SQLException e) {
            String msgErro = e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaRealizarPedido().apresentarMsg(msgErro);
        }
        return clientes;
    }
    // ------------------------------------------------------ //
    // Buscar pedido pendente para exibição:
    public Pedido buscarPedidoPendente(Cliente cliente) {
        if  (cliente != null) {
                Pedido pedidoPendente = null;
                try {
                    pedidoPendente = new Pedido().buscarPedidoPendente(cliente);
                } catch (SQLException e) {
                    String msgErro = e.getMessage();
                    System.out.println(msgErro);
                    // Função para apresentar erro na View:
                    new TelaRealizarPedido().apresentarMsg(msgErro);
                }
                return pedidoPendente;
        } else {
            return null;
        }
    }
    // ------------------------------------------------------ //
    // Listar sabores:
    public ArrayList<Sabor> listarSabores() {
        ArrayList<Sabor> sabores = null;
        try {
            sabores = new Pedido().listarSabores();
        } catch (SQLException e) {
            String msgErro = e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaRealizarPedido().apresentarMsg(msgErro);
        }
        return sabores;
    }
    // ------------------------------------------------------ //
    // Listar itens:
    public ArrayList<Pizza> listarItens(Pedido pedido) {
        ArrayList<Pizza> itens = pedido.getItens();
        return itens;
    }
    // ------------------------------------------------------ //
    // Gerar novo pedido:
    public Pedido gerarNovoPedido(Cliente cliente, String forma, Sabor sabor1, Sabor sabor2, String areaStr, String dimensaoStr) {
        // Regex para validação de area e dimensao:
        String regex = "^[0-9]+$";
        // Obj do retorno:
        Pedido pedido = null;
        double area = 0;
        double dimensao = 0;
        
        // Validações de entrada:
        if( (areaStr != null && areaStr.matches(regex)) || (dimensaoStr != null && dimensaoStr.matches(regex)) 
            && (cliente != null) && (forma != null) && (sabor1 != null || sabor2 != null) ) {   
            // converte as medidas:
            if(areaStr != null) {area = Double.parseDouble(areaStr);}
            if(dimensaoStr != null) {dimensao = Double.parseDouble(dimensaoStr);} 
            try {
                // Validações de entrada:
                validaEntradaMedidas(forma, area, dimensao);
                 // Chama função:
                pedido = new Pedido().gerarNovoPedido(cliente, forma, sabor1, sabor2, area, dimensao);
                new TelaRealizarPedido().apresentarMsg("Novo pedido gerado!");   
            } catch (IllegalArgumentException e) {
                String msgErro = "Erro ao tentar gerar novo pedido! Msg: " + e.getMessage();
                System.out.println(msgErro);
                // Função para apresentar erro na View:
                new TelaRealizarPedido().apresentarMsg(msgErro);          
            } catch (SQLException e) {
                String msgErro = "Erro ao tentar gerar novo pedido! Msg: " + e.getMessage();
                System.out.println(msgErro);
                // Função para apresentar erro na View:
                new TelaRealizarPedido().apresentarMsg(msgErro);
            } 
        } else {
            new TelaRealizarPedido().apresentarMsg("Dados preenchidos incorretamente!");
        }
        return pedido;
    }
    // ------------------------------------------------------ //
    // Adicionar item ao pedido:
    public void adicionarItemPedido(Pedido pedido, String forma, Sabor sabor1, Sabor sabor2, String areaStr, String dimensaoStr) {
        // Regex para validação de area e dimensao:
        String regex = "^[0-9]+$";
        double area = 0;
        double dimensao = 0;
        
        // Validações de entrada:
        if( (areaStr != null && areaStr.matches(regex)) || (dimensaoStr != null && dimensaoStr.matches(regex)) 
            && (pedido != null) && (forma != null) && (sabor1 != null || sabor2 != null) ) {
            // converte as medidas:
            if(areaStr != null) {area = Double.parseDouble(areaStr);}
            if(dimensaoStr != null) { dimensao = Double.parseDouble(dimensaoStr);}
            try {
                // Validações de entrada:
                validaEntradaMedidas(forma, area, dimensao);
                // Chama função:
                new Pedido().adicionarItemPedido(pedido, forma, sabor1, sabor2, area, dimensao);
                new TelaRealizarPedido().apresentarMsg("Item adicionado ao pedido!");  
            } catch (IllegalArgumentException e) {
                String msgErro = "Erro ao tentar adicionar item ao pedido! Msg: " + e.getMessage();
                System.out.println(msgErro);
                // Função para apresentar erro na View:
                new TelaRealizarPedido().apresentarMsg(msgErro); 
            } catch (SQLException e) {
                String msgErro = "Erro ao tentar adicionar item ao pedido! Msg: " + e.getMessage();
                System.out.println(msgErro);
                // Função para apresentar erro na View:
                new TelaRealizarPedido().apresentarMsg(msgErro);
            } 
        } else {
            new TelaRealizarPedido().apresentarMsg("Dados preenchidos incorretamente!");
        }
    }
    // ------------------------------------------------------ //
    // Excluir pedido (cancelar):
    public boolean excluirPedido(Pedido pedido) {
        try {
            pedido.excluirPedido(pedido);
        } catch (SQLException e) {
            String msgErro = e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaRealizarPedido().apresentarMsg(msgErro);
            return false;
        }    
        return true;    
    }
    // ------------------------------------------------------ //
    // Excluir item pedido:
    public boolean excluirItemPedido(Pedido pedido, int idItem) {
        try {
            pedido.excluirItemPedido(pedido, idItem);
        } catch (SQLException e) {
            String msgErro = e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaRealizarPedido().apresentarMsg(msgErro);
            return false;
        }    
        return true;    
    }
    // ------------------------------------------------------ //
    // Atualizar status:
    public boolean atualizarStatus(int nrPedido, int status) {
        try {
            new Pedido().atualizarStatus(nrPedido, status);
        } catch (SQLException e) {
            String msgErro = e.getMessage();
            System.out.println(msgErro);
            // Função para apresentar erro na View:
            new TelaRealizarPedido().apresentarMsg(msgErro);
            return false;
        }    
        return true;
    }
    // ------------------------------------------------------ //
    // Valida entrada das medidas:
    public void validaEntradaMedidas(String forma, double area, double dimensao) throws IllegalArgumentException {
        // Validações de entrada:
                if(area != 0 && (area < 100 || area > 1600)) {
                    throw new IllegalArgumentException("A area deve estar entre 100 e 1600 cm2");
                }   
                if(forma.equals("CIRCULO")) {
                    if(dimensao != 0 && (dimensao < 7 || dimensao > 23)) {
                        throw new IllegalArgumentException("O raio deve estar entre 7 e 23 cm");
                    }
                } else if(forma.equals("QUADRADO")) {
                    if(dimensao != 0 && (dimensao < 10 || dimensao > 40)) {
                        throw new IllegalArgumentException("O lado deve estar entre 10 e 40 cm");
                    }
                } else if(forma.equals("TRIANGULO")) {
                    if(dimensao != 0 && (dimensao < 20 || dimensao > 60)) {
                        throw new IllegalArgumentException("O lado deve estar entre 20 e 60 cm");
                    }
                } 
    }   
    // ------------------------------------------------------ //
    // Listar pedidos:
    public ArrayList<Pedido> listarPedidos() {
        ArrayList<Pedido> pedidos = null;
        try {
            pedidos = new Pedido().listarPedidos();
        } catch (SQLException e) {
                String msgErro = e.getMessage();
                System.out.println(msgErro);
                // Função para apresentar erro na View:
                new TelaCadastrarCliente().apresentarMsg(msgErro);
        }
        return pedidos;
    }
    // ------------------------------------------------------ //
}
