/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.Model;

import PizzaApp.DAO.PedidoDAO;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Locale;

/**
 *
 * @author joeau
 */
public class Pedido {
     // ------------------Atributos:------------------ //
    private int nrPedido;
    private Cliente cliente;
    private double valorTotal;
    private int status;
    private ArrayList<Pizza> itens;
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private Pizza pizzaModel = new Pizza();
    private Cliente clienteModel = new Cliente();
    // ------------------Métodos:------------------ //
    // Contructor 01 (para geração de novo pedido, é chamado por PedidoDAO, que gera o ID):
    public Pedido(int nrPedido, Cliente cliente){
        this.nrPedido = nrPedido;
        this.cliente = cliente;
        this.status = 0;    // Em montagem...
        System.out.println("Obj Pedido criado! C01");   
    }
    // Contructor 02 (para montar pedidos recuperados da base):
    public Pedido(int nrPedido, String telCliente, int status) throws SQLException {
        this.nrPedido = nrPedido;
        this.status = status;
        try {
            // Buscar/montar cliente por tel:
            this.cliente = clienteModel.montarClientePorTel(telCliente);
            // Busca/montar lista de itens:
            this.itens = pizzaModel.listarItensPorPedido(nrPedido);
            // Operação para calcular valor total após termos carregado a lista:      
            calcularValorTotal();
        } catch (SQLException e) {
            throw new SQLException(e);
        }     
        System.out.println("Obj Pedido criado! C02");   
    }
    // Contructor opcional:
    public Pedido(){}
    // ---------------------------------------------- //
    // Get's & Set's:
    public int getNrPedido(){
        return nrPedido;
    }
    public void setNrPedido(int nrPedido){
        this.nrPedido = nrPedido;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public int getStatus(){
        return status;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public double getValorTotal(){
        return valorTotal;
    }
    public void setValorTotal(double valor){
        String valorFormatado = String.format(Locale.US,"%.2f",valor);         
        this.valorTotal = Double.parseDouble(valorFormatado);
    }
    public ArrayList<Pizza> getItens(){
        return itens;
    }
    public void setItens(ArrayList<Pizza> itens){
        this.itens = itens;
    }
    // ---------------------------------------------- //
    // Demais métodos:
    // Limpar pedidos cliente:
    public void limparPedidosCliente(Cliente cliente) throws SQLException {
        System.out.println("Obj Pedido - Iniciando limparPedidosCliente..."); 
        try {
            pedidoDAO.excluirPorCliente(cliente);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Listar clientes (para tela de geração de pedidos):
    public ArrayList<Cliente> listarClientes() throws SQLException {
        System.out.println("Obj Pedido - Iniciando listarClientes..."); 
        ArrayList<Cliente> clientes = null;
        try {
            clientes = clienteModel.listarClientes();    
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return clientes;
    }
    // Listar clientes com filtro (para tela de geração de pedidos):
    public ArrayList<Cliente> listarClientesComFiltro(String telefone) throws SQLException {
        System.out.println("Obj Pedido - Iniciando listarClientesComFiltro..."); 
        ArrayList<Cliente> clientes = null;
        try {
            clientes = clienteModel.listarClientesComFiltro("", "", telefone);   
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return clientes;
    }
    // Listar Sabores (para tela de geração de pedidos):
    public ArrayList<Sabor> listarSabores() throws SQLException {
        System.out.println("Obj Pedido - Iniciando listarSabores..."); 
        ArrayList<Sabor> sabores = null;
        try {
            sabores = new Sabor().listarSabores();    
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return sabores;
    }
    // Buscar pendente:
    public Pedido buscarPedidoPendente(Cliente cliente) throws SQLException {
        System.out.println("Obj Pedido - Iniciando buscarPedidoPendente..."); 
        Pedido pedidoPendente = null;
        try {
            pedidoPendente = pedidoDAO.buscarPedidoPendente(cliente);
            if (pedidoPendente != null) {
                // Função para montar lista de itens do pedido, buscando na base:     
                pedidoPendente.recuperarListaItens(); 
                // Calculamos o valor após termos a lista preenchida:  
                pedidoPendente.calcularValorTotal();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return pedidoPendente;
    }
    // Gerar novo pedido:
    public Pedido gerarNovoPedido(Cliente cliente, String forma, Sabor sabor1, Sabor sabor2, double area, double dimensao) throws SQLException {
        System.out.println("Obj Pedido - Iniciando gerarNovoPedido..."); 
        Pedido novoPedido = null;
        try {
            // Gera novo pedido (apenas com o cliente e o id recuperado pós inserção):
            novoPedido = pedidoDAO.gerarPedido(cliente);  
            // Cria objeto Pizza e insere na base:
            adicionarItemPedido(novoPedido, forma, sabor1, sabor2, area, dimensao);
            // Função para montar lista de itens do pedido, buscando na base:     
            novoPedido.recuperarListaItens(); 
            // Calculamos o valor após termos a lista preenchida:  
            novoPedido.calcularValorTotal();
        } catch (SQLException e) {
            throw new SQLException(e);
        } 
        // Retorna pedido gerado: 
        return novoPedido;
    }
    // Adicionar item ao pedido:
    public void adicionarItemPedido(Pedido pedido, String forma, Sabor sabor1, Sabor sabor2, double area, double dimensao) throws SQLException {
        System.out.println("Obj Pedido - Iniciando adicionarItemPedido..."); 
        try {
            pizzaModel.adicionarItemPedido(pedido.getNrPedido(), forma, sabor1, sabor2, area, dimensao);
            // Função para montar lista de itens do pedido, buscando na base:     
            pedido.recuperarListaItens(); 
            // Calculamos o valor após termos a lista preenchida:  
            pedido.calcularValorTotal();
        } catch (SQLException e) {
            throw new SQLException(e);
        } 
    }
    // Recupera e monta lista de itens referentes ao pedido:
    public void recuperarListaItens() throws SQLException {
        System.out.println("Obj Pedido - Iniciando recuperarListaItens..."); 
        try {
            this.itens = pizzaModel.listarItensPorPedido(nrPedido);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Calcula valor total, de acordo com a lista de itens do pedido:
    public void calcularValorTotal(){
        System.out.println("Obj Pedido - Iniciando calcularValorTotal..."); 
        double soma = 0;
        for (Pizza item : itens) {
            soma += item.getValor();
        }
        this.setValorTotal(soma);
    }
    // Atualizar status:
    public void atualizarStatus(int nrPedido, int status) throws SQLException {
        System.out.println("Obj Pedido - Iniciando atualizarStatus..."); 
        try {
            pedidoDAO.atualizarStatus(nrPedido, status);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Excluir pedido (cancelar):
    public void excluirPedido(Pedido pedido) throws SQLException {
        System.out.println("Obj Pedido - Iniciando excluirPedido..."); 
        try {
            limparItensPedido(pedido);
            pedidoDAO.excluir(pedido.nrPedido);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Limpar itens pedido:
    public void limparItensPedido(Pedido pedido) throws SQLException {
        System.out.println("Obj Pedido - Iniciando limparItensPedido..."); 
        try {
            pizzaModel.limparItensPorPedido(pedido.nrPedido); 
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Excluir item pedido:
    public void excluirItemPedido(Pedido pedido, int idItem) throws SQLException {
        System.out.println("Obj Pedido - Iniciando excluirItemPedido..."); 
        try {
            pizzaModel.excluirItemPedido(pedido.nrPedido, idItem); 
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    // Monta pedido recuperado da base:
    public Pedido recuperarPedido(int nrPedido) throws SQLException {
        System.out.println("Obj Pedido - Iniciando recuperarPedido..."); 
        Pedido pedido = null;
        try {
            pedido = pedidoDAO.montarPedidoPorID(nrPedido);   
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return pedido;
    }
    // Listar pedidos:
    public ArrayList<Pedido> listarPedidos() throws SQLException {
        System.out.println("Obj Pedido - Iniciando listarPedidos..."); 
        ArrayList<Pedido> pedidos = null;
        try {
            pedidos = pedidoDAO.listar();
        } catch (SQLException e) {
            throw new SQLException(e);
        }  
        return pedidos;
    }
    // ---------------------------------------------- //
}
