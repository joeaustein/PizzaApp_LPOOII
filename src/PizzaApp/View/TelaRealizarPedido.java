/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PizzaApp.View;

import PizzaApp.Controller.PedidoControl;
import PizzaApp.Model.Cliente;
import PizzaApp.Model.Pedido;
import PizzaApp.Model.Pizza;
import PizzaApp.Model.Sabor;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joeau
 */
public class TelaRealizarPedido extends javax.swing.JFrame {
    // --------------------------------------------------------Atributos:---------------------------------------------------------- //
    // Variáveis globais:
    private TelaHome home;
    private Cliente cliente;
    private Pedido pedidoEmAberto;
    private int idItemSelecionado;
    private Color colorDisab;
    private Color colorHabil;
    private PedidoControl pedidoControl;
    // -------------------------------------------------------Contrutores:-------------------------------------------------------- //
    // Contrutor Default:
    public TelaRealizarPedido() {
        initComponents();
    }
    // ------------------------------------------------------------- //
    // Contrutor Modificado:
    public TelaRealizarPedido(TelaHome home) {
        // Armazenamos a tela anterior para a navegabilidade (opção "voltar"):
        this.home = home;
        this.cliente = null;
        this.pedidoEmAberto = null;
        this.idItemSelecionado = 0;
        this.colorDisab = new Color(0x65686b);
        this.colorHabil = new Color(0x161717);
        this.pedidoControl = new PedidoControl();
        // Inicialização:
        initComponents();
        inicializacaoPersonalizada();
    }
    // -----------------------------------------------------Classes Internas:------------------------------------------------------ //
    // Classe aninhada para implementar DocumentListener, alterando alguns recursos:
    // Campo do filtro de telefone:
    // Valida preenchimento do campo de filtro para recarregar ComboBox conforme o parametro passado:
    private class TextFieldsDLFiltro implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateComboList();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            updateComboList();
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            updateComboList();
        }
        // Função para ativar/desativar botoes e limpar mensagens conforme o preenchimento dos campos:
        private void updateComboList() {       
            carregaComboBoxCliente(jTextField_FiltroTelefone.getText());
        }
    }
    // ------------------------------------------------------------- //
    private class TextFieldsDLAreaDimensao implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateTextField();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            updateTextField();
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            updateTextField();
        }
        // Função para ativar/desativar botoes e limpar mensagens conforme o preenchimento dos campos:
        private void updateTextField() {       
            if(!jTextField_Area.getText().equals("")){
                jTextField_Dimensao.setEnabled(false);
                jLabel_Dimensao.setForeground(colorDisab);
            } else {
                jTextField_Dimensao.setEnabled(true);
                jLabel_Dimensao.setForeground(colorHabil);
            }
            if(!jTextField_Dimensao.getText().equals("") 
                && !jTextField_Dimensao.getText().equals("Lado")
                && !jTextField_Dimensao.getText().equals("Raio")){
                jTextField_Area.setEnabled(false);
                jLabel_Area.setForeground(colorDisab);
            } else {
                jTextField_Area.setEnabled(true);
                jLabel_Area.setForeground(colorHabil);
            }
        }
    }
    // ------------------------------------------------------------- //
    private class TextFieldsDLMontarPizza implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateButtonState();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            updateButtonState();
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            updateButtonState();
        }
        // Função para ativar/desativar botoes e limpar mensagens conforme o preenchimento dos campos:
        private void updateButtonState() {       
            // Botao "Adicionar Item" dica desabilitado enquanto os campos nao forem todos preenchidos:
            if(     (jComboBox_Cliente.getSelectedIndex() != 0) &&
                    (jComboBox_Sabor1.getSelectedIndex() != 0 || jComboBox_Sabor2.getSelectedIndex() != 0) &&
                    ((!jTextField_Dimensao.getText().equals("") 
                    && (!jTextField_Dimensao.getText().equals("Lado") && !jTextField_Dimensao.getText().equals("Raio")) ) 
                    || !jTextField_Area.getText().equals("")) ) {
                habilitaBotao_AdicionarItem();
            } else {
                desabilitaBotao_AdicionarItem();
            }      
        }
    }
    // ------------------------------------------------------------- //
    // Classe aninhada para implementar ActionListener, alterando alguns recursos:
    // ComboBox clientes: 
    // Habilita/desabilita botão MontarPizza conforme seleção do cliente:
    private class ComboBoxClienteAL implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(jComboBox_Cliente.getSelectedIndex() != 0) {
                // Validamos se o cliente selecionado tem um pedido em aberto:
                cliente = (Cliente) jComboBox_Cliente.getSelectedItem();
                // Vai carregar a tela de acordo com o pedido em aberto:
                carregaTelaPedidos();             
            } else {
                desabilitaBotao_MontarPizza();
                desabilitaBotao_FinalizarPedido();
                limpaResumoPedido();
            }
        } 
    }
    // ------------------------------------------------------------- //
    private class ComboBoxMontarPizzaAL implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(     (jComboBox_Forma.getSelectedIndex() != 0) &&
                    (jComboBox_Sabor1.getSelectedIndex() != 0 || jComboBox_Sabor2.getSelectedIndex() != 0) &&
                    ((!jTextField_Dimensao.getText().equals("") 
                    && (!jTextField_Dimensao.getText().equals("Lado") && !jTextField_Dimensao.getText().equals("Raio")) ) 
                    || !jTextField_Area.getText().equals("")) ) {
                habilitaBotao_AdicionarItem();
            } else {
                desabilitaBotao_AdicionarItem();
            }
            // Altera Label dimensão conforme a forma selecionada:
            if(jComboBox_Forma.getSelectedIndex() != 0) {
                if(jComboBox_Forma.getSelectedIndex() == 1) {
                    if (jTextField_Dimensao.getText().equals("") || jTextField_Dimensao.getText().equals("Lado")) {
                        adicionaFLMsgCampo(jTextField_Dimensao, "Raio");
                    }
                } else {
                    if (jTextField_Dimensao.getText().equals("") || jTextField_Dimensao.getText().equals("Raio")) {
                        adicionaFLMsgCampo(jTextField_Dimensao, "Lado");
                    }
                }
            } else {
                adicionaFLMsgCampo(jTextField_Dimensao, "");
            }
        } 
    }
    // ------------------------------------------------------------- //
    // Classe aninhada que extende DefaultListCellRenderer, alterando alguns recursos:
    // ComboBox clientes:
    // Isso vai fazer o componente armazenar o objeto Cliente e mostrar apenas o nome:
    private class ComboBoxClienteDLCR extends DefaultListCellRenderer{
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Cliente) {              
                Cliente cliente = (Cliente) value;
                setText(cliente.getNome());        
                }
            // index 0 ficara em cinza:      
            if(index == 0) {
                renderer.setForeground(colorDisab);
            }
            return this;
        }  
    }
    // ------------------------------------------------------------- //
    private class ComboBoxSaborDLCR extends DefaultListCellRenderer{
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Sabor) {
                Sabor sabor = (Sabor) value;
                setText(sabor.getNomeSabor());
            }
            // index 0 ficara em cinza:      
            if(index == 0) {
                renderer.setForeground(colorDisab);
            }
            return this;
        }  
    }
    // ------------------------------------------------------------- //
    private class ComboBoxFormaDLCR extends DefaultListCellRenderer{
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            // index 0 ficara em cinza:      
            if(index == 0) {
                renderer.setForeground(colorDisab);
            }
            return this;
        }  
    }
    // --------------------------------------------------------Métodos:---------------------------------------------------------- //
    // Método de Inicialização Personalizada:
    public void inicializacaoPersonalizada() {
        // (A ideia é colocar aqui as configurações adicionais de inicialização):
        
        // Inicia combo box:
        carregaComboBoxCliente(""); 
        carregaComboBoxForma();
        carregaComboBoxSabor(jComboBox_Sabor1);
        carregaComboBoxSabor(jComboBox_Sabor2);
        // Listeners: 
        // Adiciona FocusListener no campo de filtro, para exibir/esconder as mensagens instrutivas:   
        adicionaFLMsgCampo(jTextField_FiltroTelefone, "Filtrar por Telefone");
        // Validação de preenchimento do campo de filtro para atualizar a combo box:
        jTextField_FiltroTelefone.getDocument().addDocumentListener(new TextFieldsDLFiltro());
        // Validações preenchimento de Area e Dimensao:
        DocumentListener dlMedidas = new TextFieldsDLAreaDimensao();
        jTextField_Area.getDocument().addDocumentListener(dlMedidas);
        jTextField_Dimensao.getDocument().addDocumentListener(dlMedidas);
        // Validação de preenchimento do campo de filtro para ativar/desativar botão Adicionar Item:
        DocumentListener dlMontarPizza = new TextFieldsDLMontarPizza();
        jTextField_Dimensao.getDocument().addDocumentListener(dlMontarPizza);
        jTextField_Area.getDocument().addDocumentListener(dlMontarPizza);
        // Esse Listener vai habilitar/desabilitar o botao Montar Pizza conforme a seleção do cliente:
        jComboBox_Cliente.addActionListener(new ComboBoxClienteAL());
        // Esse Listener vai habilitar/desabilitar o botao Adicionar Item conforme o preenchimento correto:
        ActionListener alComboBox = new ComboBoxMontarPizzaAL();
        jComboBox_Forma.addActionListener(alComboBox);
        jComboBox_Sabor1.addActionListener(alComboBox);
        jComboBox_Sabor2.addActionListener(alComboBox);
        // Aqui setamos um Renderer para que a ComboBox armazene o obj Cliente, mas apresente apenas o nome:
        DefaultListCellRenderer dlcrComboBox = new ComboBoxSaborDLCR();
        jComboBox_Sabor1.setRenderer(dlcrComboBox);
        jComboBox_Sabor2.setRenderer(dlcrComboBox);
        jComboBox_Cliente.setRenderer(new ComboBoxClienteDLCR());        
        jComboBox_Forma.setRenderer(new ComboBoxFormaDLCR());
        // Reset da tela:
        resetTela();    
    }
    // ------------------------------------------------------------- //
    // Função para trazer erros da camada Controller e aprensetar na camada View:
    public void apresentarMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
    // ------------------------------------------------------------- //
    // Reset (seta botoes e campos para o estado inicial):
    public void resetTela() {
        desabilitaBotao_MontarPizza();
        desabilitaBotao_FinalizarPedido();
        desabilitaBotao_CancelarPedido();
        desabilitaBotao_ExcluirItem();    
        desabilitaFormularioMontagem();
        desabilitaLabelsResumo();
    }
    // Carrega tela pedidos:
    public void carregaTelaPedidos() {
        pedidoEmAberto = pedidoControl.buscarPedidoPendente(cliente);
        if(pedidoEmAberto != null) {
            // Se existe pedido em aberto, ja podemos exibir o resumo:
            mostraResumoPedido(pedidoEmAberto);   
            // Pedido ja finalizado?
            if(pedidoEmAberto.getStatus() != 0) {
                desabilitaFormularioMontagem();
                desabilitaBotao_MontarPizza();
                desabilitaBotao_FinalizarPedido();
            } else {
                // Se o status ainda for 0 (em montagem), ainda pode adicionar itens no pedido:
                habilitaBotao_MontarPizza();
                // Se o status ainda for 0 e a lista não estiver vazia, habilita botão finalizar:
                if(pedidoEmAberto.getItens() != null) {
                    habilitaBotao_FinalizarPedido();
                }
            }          
        } else {
            habilitaBotao_MontarPizza();
            desabilitaLabelsResumo();
            limpaResumoPedido();        
        }
    }
    // Desativa painel de montagem de pizzas:
    public void desabilitaFormularioMontagem() {
        resetFormularioMontagem();
        desabilitaBotao_AdicionarItem();
        desabilitaLabelsMontagem();
        jComboBox_Forma.setEnabled(false);
        jComboBox_Sabor1.setEnabled(false);
        jComboBox_Sabor2.setEnabled(false);
        jTextField_Dimensao.setEnabled(false);
        jTextField_Area.setEnabled(false);
    }
    // Ativa painel de montagem de pizzas:
    public void habilitaFormularioMontagem() {
        resetFormularioMontagem();
        habilitaLabelsMontagem();
        jComboBox_Forma.setEnabled(true);
        jComboBox_Sabor1.setEnabled(true);
        jComboBox_Sabor2.setEnabled(true);
        jTextField_Dimensao.setEnabled(true);
        jTextField_Area.setEnabled(true);
    }
    // reseta painel de montagem de pizzas:
    public void resetFormularioMontagem() {
        jComboBox_Forma.setSelectedIndex(0);
        jComboBox_Sabor1.setSelectedIndex(0);
        jComboBox_Sabor2.setSelectedIndex(0);
        jTextField_Dimensao.setText("");
        jTextField_Area.setText("");
    }
    // Desabilita labels painel montagem:
    public void desabilitaLabelsMontagem() {
        jLabel_Forma.setForeground(colorDisab);
        jLabel_Sabor1.setForeground(colorDisab);
        jLabel_Sabor2.setForeground(colorDisab);
        jLabel_Dimensao.setForeground(colorDisab);
        jLabel_Area.setForeground(colorDisab);
    }
    // Habilita labels painel montagem:
    public void habilitaLabelsMontagem() {
        jLabel_Forma.setForeground(colorHabil);
        jLabel_Sabor1.setForeground(colorHabil);
        jLabel_Sabor2.setForeground(colorHabil);
        jLabel_Dimensao.setForeground(colorHabil);
        jLabel_Area.setForeground(colorHabil);
    }    
    // Desabilita labels painel resumo:
    public void desabilitaLabelsResumo() {
        jLabel_ResumoPedido.setForeground(colorDisab);
        jLabel_NrPedido.setForeground(colorDisab);
        jLabel_ClientePedido.setForeground(colorDisab);
        jLabel_ValorTotalPedido.setForeground(colorDisab);
        jLabel_StatusPedido.setForeground(colorDisab);
        jLabel_ItensPedido.setForeground(colorDisab);
    }
    // Habilita labels painel resumo:
    public void habilitaLabelsResumo() {
        jLabel_ResumoPedido.setForeground(colorHabil);
        jLabel_NrPedido.setForeground(colorHabil);
        jLabel_ClientePedido.setForeground(colorHabil);
        jLabel_ValorTotalPedido.setForeground(colorHabil);
        jLabel_StatusPedido.setForeground(colorHabil);
        jLabel_ItensPedido.setForeground(colorHabil);
    }  
    // Mostra resumo do pedido:
    public void mostraResumoPedido(Pedido pedido) {
        habilitaLabelsResumo();
        habilitaBotao_CancelarPedido();
        jLabel_NrPedidoResp.setText(Integer.toString(pedido.getNrPedido()));
        jLabel_ClientePedidoResp.setText(pedido.getCliente().getNome());
        jLabel_ValorTotalPedidoResp.setText(Double.toString(pedido.getValorTotal()));   
        switch (pedido.getStatus()) {
            case 1:
                jLabel_StatusPedidoResp.setText("ABERTO"); 
                break;
            case 2:
                jLabel_StatusPedidoResp.setText("ENVIADO"); 
                break;
            case 3:
                jLabel_StatusPedidoResp.setText("ENTREGUE"); 
                break;
        
            default:
                jLabel_StatusPedidoResp.setText("MONTAGEM");        
        }  
        carregaTabelaItens(pedido);
    }
    // Limpa resumo do pedido:
    public void limpaResumoPedido() {
        desabilitaBotao_CancelarPedido();
        jLabel_NrPedidoResp.setText("");
        jLabel_ClientePedidoResp.setText("");
        jLabel_ValorTotalPedidoResp.setText("");
        jLabel_StatusPedidoResp.setText("");
        carregaTabelaItens(null); 
    }
    // ------------------------------------------------------------- //
    public void carregaTabelaItens(Pedido pedido) {
        // Carregamento dados tabela:
        DefaultTableModel tableModel = (DefaultTableModel) jTable_Itens.getModel();
        // Limpa tabela antes de carregar, para não duplicar:
        tableModel.setRowCount(0);
        if(pedido != null) {
            // Popula tableModel para jTable_Clientes:
            ArrayList<Pizza> itens = pedidoControl.listarItens(pedido);
            // Tratamento em caso de null:            
            if(itens != null) {
                itens.forEach((Pizza pizza) -> {
                    tableModel.addRow(new Object[] {pizza.getIdItem(),
                                                                               pizza.getForma().formaToString(),
                                                                               pizza.getSabor1().getNomeSabor(),
                                                                               pizza.getSabor2().getNomeSabor(),
                                                                               pizza.getArea(),
                                                                               pizza.getForma().getDimensao(),
                                                                               pizza.getValor()});
                });
            } else {
                apresentarMsg("Impossivel carregar tabela de itens!");
            }
        } 
        jTable_Itens.setModel(tableModel);
        
        // Isso aqui vai fazer com que a celula da tabela nao seja editavel diretamente:
        DefaultCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(java.util.EventObject e) {
                return false; // Impede a edição de todas as células
            }
        };
        jTable_Itens.setDefaultEditor(Object.class, cellEditor);    
    }
    // ------------------------------------------------------------- //
    // Métodos ComboBox:
    // Carregamento combo box Cliente (pode ser com ou sem filtro):
    public void carregaComboBoxCliente(String filtro) {   
        // Define modelo ComboBox:
        DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel<>();
        jComboBox_Cliente.setModel(model);
        // Inicia a ComboBox com uma mensagem: 
        Cliente defaultMsg = new Cliente();
        defaultMsg.setNome("Selecione o Cliente");
        model.addElement(defaultMsg);
        // Tem filtro?
        if(filtro.equals("") || filtro.equals("Filtrar por Telefone")) {
            // Chama função para recuperar os clientes e salvar os nomes:
            ArrayList<Cliente> clientes = pedidoControl.listarClientes();
            // Tratamento em caso de null:
            if(clientes != null) {
                // Preenche comboBox:
                for(Cliente cliente : clientes) {
                    model.addElement(cliente);
                }
            } else {
                apresentarMsg("Impossivel carregar combo box de clientes!");
            }
        } else {
            // Chama função para recuperar os clientes (filtrando) e salvar os objetos:
            ArrayList<Cliente> clientes = pedidoControl.listarClientesComFiltro(filtro);
            // Tratamento em caso de null:
            if(clientes != null) {
                // Preenche comboBox:
                for(Cliente cliente : clientes) {
                    model.addElement(cliente);
                }
            } else {
                apresentarMsg("Impossivel carregar combo box de clientes!");
            }
        }
    }
    // ------------------------------------------------------------- //   
    // Carregamento combo box Sabor:
    public void carregaComboBoxSabor(JComboBox comboBox) {   
        // Define modelo ComboBox:
        DefaultComboBoxModel<Sabor> model = new DefaultComboBoxModel<>();
        comboBox.setModel(model);
        // Inicia a ComboBox com uma mensagem:
        Sabor dafaultMsg = new Sabor();
        dafaultMsg.setNomeSabor("Selecione o Sabor");
        model.addElement(dafaultMsg);
        // Chama função para recuperar os sabores e salvar os objetos:
        ArrayList<Sabor> sabores = pedidoControl.listarSabores();
        // Tratamento em caso de null:
        if(sabores != null) {
            // Preenche comboBox:
            for(Sabor sabor : sabores) {
                model.addElement(sabor);
            }
        } else {
            apresentarMsg("Impossivel carregar combo box de sabores!");
        }
    }
    // ------------------------------------------------------------- //   
    // Carregamento combo box Forma:
    public void carregaComboBoxForma() {   
        // Define modelo ComboBox:
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        jComboBox_Forma.setModel(model);
        // Inicia a ComboBox com uma mensagem:
        model.addElement("Selecione a forma");
        model.addElement("CIRCULO");        
        model.addElement("QUADRADO");  
        model.addElement("TRIANGULO");         
    }
    // ------------------------------------------------------------- //   
    // Essa função irá adicionar um FocusListener num campo de texto para exibir/esconder a msg instrutiva:
    public void adicionaFLMsgCampo(JTextField textField, String msg) {
        // Iniciando com a mensagem instrutiva em cinza:
        textField.setForeground(colorDisab);
        textField.setText(msg);

        // Adicionando FocusListener: 
        textField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(msg)) {
                    textField.setText("");
                    textField.setForeground(colorHabil);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(msg);
                    textField.setForeground(colorDisab);
                }
            }     
        });
    }
    // ------------------------------------------------------------- //
    // Metodos dos botões:
    // Botão Montar Pizza:
    public void desabilitaBotao_MontarPizza() {
        jButton_MontarPizza.setForeground(colorDisab);
        jButton_MontarPizza.setEnabled(false);
    }
    public void habilitaBotao_MontarPizza() {
        jButton_MontarPizza.setForeground(colorHabil);
        jButton_MontarPizza.setEnabled(true);
    }
    // Botão Adicionar Item:
    public void desabilitaBotao_AdicionarItem() {
        jButton_AdicionarItem.setForeground(colorDisab);
        jButton_AdicionarItem.setEnabled(false);
    }
    public void habilitaBotao_AdicionarItem() {
        jButton_AdicionarItem.setForeground(colorHabil);
        jButton_AdicionarItem.setEnabled(true);
    }
    // Botão Finalizar Pedido:
    public void desabilitaBotao_FinalizarPedido() {
        jButton_FinalizarPedido.setForeground(colorDisab);
        jButton_FinalizarPedido.setEnabled(false);
    }
    public void habilitaBotao_FinalizarPedido() {
        jButton_FinalizarPedido.setForeground(colorHabil);
        jButton_FinalizarPedido.setEnabled(true);
    }
    // Botão Cancelar Pedido:
    public void desabilitaBotao_CancelarPedido() {
        jButton_CancelarPedido.setForeground(colorDisab);
        jButton_CancelarPedido.setEnabled(false);
    }
    public void habilitaBotao_CancelarPedido() {
        jButton_CancelarPedido.setForeground(colorHabil);
        jButton_CancelarPedido.setEnabled(true);
    }
    // Botão Alterar Item:
    public void desabilitaBotao_ExcluirItem() {
        jButton_ExcluirItem.setForeground(colorDisab);
        jButton_ExcluirItem.setEnabled(false);
    }
    public void habilitaBotao_ExcluirItem() {
        jButton_ExcluirItem.setForeground(colorHabil);
        jButton_ExcluirItem.setEnabled(true);
    }
    // ----------------------------------------------------------------------------------------------------------------------------- //
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel_RealizarPedidoBG = new javax.swing.JPanel();
        jLabel_IconEsq2 = new javax.swing.JLabel();
        jLabel_Title = new javax.swing.JLabel();
        jLabel_IconDir = new javax.swing.JLabel();
        jLabel_Cliente = new javax.swing.JLabel();
        jComboBox_Cliente = new javax.swing.JComboBox<>();
        jLabel_Telefone = new javax.swing.JLabel();
        jTextField_FiltroTelefone = new javax.swing.JTextField();
        jPanel_Resumo = new javax.swing.JPanel();
        jLabel_ResumoPedido = new javax.swing.JLabel();
        jButton_ExcluirItem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Itens = new javax.swing.JTable();
        jLabel_ItensPedido = new javax.swing.JLabel();
        jButton_CancelarPedido = new javax.swing.JButton();
        jLabel_NrPedido = new javax.swing.JLabel();
        jLabel_ClientePedido = new javax.swing.JLabel();
        jLabel_ValorTotalPedido = new javax.swing.JLabel();
        jLabel_StatusPedido = new javax.swing.JLabel();
        jLabel_NrPedidoResp = new javax.swing.JLabel();
        jLabel_ClientePedidoResp = new javax.swing.JLabel();
        jLabel_StatusPedidoResp = new javax.swing.JLabel();
        jLabel_ValorTotalPedidoResp = new javax.swing.JLabel();
        jButton_FinalizarPedido = new javax.swing.JButton();
        jPanel_MontarPizza = new javax.swing.JPanel();
        jLabel_Forma = new javax.swing.JLabel();
        jComboBox_Forma = new javax.swing.JComboBox<>();
        jLabel_Dimensao = new javax.swing.JLabel();
        jTextField_Dimensao = new javax.swing.JTextField();
        jLabel_Area = new javax.swing.JLabel();
        jTextField_Area = new javax.swing.JTextField();
        jLabel_Sabor1 = new javax.swing.JLabel();
        jComboBox_Sabor1 = new javax.swing.JComboBox<>();
        jLabel_Sabor2 = new javax.swing.JLabel();
        jComboBox_Sabor2 = new javax.swing.JComboBox<>();
        jButton_AdicionarItem = new javax.swing.JButton();
        jButton_MontarPizza = new javax.swing.JButton();
        jButton_Voltar1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Realizar Pedido");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel_RealizarPedidoBG.setBackground(new java.awt.Color(255, 49, 49));
        jPanel_RealizarPedidoBG.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel_RealizarPedidoBG.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel_RealizarPedidoBG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_RealizarPedidoBGMouseClicked(evt);
            }
        });

        jLabel_IconEsq2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoESQ.png"))); // NOI18N

        jLabel_Title.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Title.setText("Realizar Pedido");

        jLabel_IconDir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoDIR.png"))); // NOI18N

        jLabel_Cliente.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Cliente.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Cliente.setText("Cliente:");

        jLabel_Telefone.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Telefone.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Telefone.setText("Telefone:");

        jPanel_Resumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_ResumoMouseClicked(evt);
            }
        });

        jLabel_ResumoPedido.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_ResumoPedido.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_ResumoPedido.setText("Resumo Pedido:");

        jButton_ExcluirItem.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButton_ExcluirItem.setText("Excluir Item");
        jButton_ExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExcluirItemActionPerformed(evt);
            }
        });

        jTable_Itens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Forma", "Sabor 1", "Sabor 2", "AreaCM2", "Dimensao", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_Itens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ItensMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Itens);

        jLabel_ItensPedido.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_ItensPedido.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_ItensPedido.setText("Itens:");

        jButton_CancelarPedido.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jButton_CancelarPedido.setText("Cancelar");
        jButton_CancelarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelarPedidoActionPerformed(evt);
            }
        });

        jLabel_NrPedido.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel_NrPedido.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_NrPedido.setText("Pedido:");

        jLabel_ClientePedido.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel_ClientePedido.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_ClientePedido.setText("Cliente:");

        jLabel_ValorTotalPedido.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel_ValorTotalPedido.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_ValorTotalPedido.setText("Total R$:");

        jLabel_StatusPedido.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel_StatusPedido.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_StatusPedido.setText("Status:");

        jLabel_NrPedidoResp.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel_NrPedidoResp.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_NrPedidoResp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_NrPedidoResp.setText("   ");

        jLabel_ClientePedidoResp.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel_ClientePedidoResp.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_ClientePedidoResp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_ClientePedidoResp.setText("   ");

        jLabel_StatusPedidoResp.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel_StatusPedidoResp.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_StatusPedidoResp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_StatusPedidoResp.setText("   ");

        jLabel_ValorTotalPedidoResp.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel_ValorTotalPedidoResp.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_ValorTotalPedidoResp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_ValorTotalPedidoResp.setText("   ");

        javax.swing.GroupLayout jPanel_ResumoLayout = new javax.swing.GroupLayout(jPanel_Resumo);
        jPanel_Resumo.setLayout(jPanel_ResumoLayout);
        jPanel_ResumoLayout.setHorizontalGroup(
            jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ResumoLayout.createSequentialGroup()
                .addGroup(jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ResumoLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_ResumoLayout.createSequentialGroup()
                                .addComponent(jLabel_NrPedido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_NrPedidoResp))
                            .addGroup(jPanel_ResumoLayout.createSequentialGroup()
                                .addComponent(jLabel_ValorTotalPedido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_ValorTotalPedidoResp))
                            .addGroup(jPanel_ResumoLayout.createSequentialGroup()
                                .addComponent(jLabel_ClientePedido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_ClientePedidoResp))
                            .addComponent(jLabel_ResumoPedido)
                            .addGroup(jPanel_ResumoLayout.createSequentialGroup()
                                .addComponent(jLabel_StatusPedido)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_StatusPedidoResp))))
                    .addGroup(jPanel_ResumoLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jButton_CancelarPedido)))
                .addGap(18, 18, 18)
                .addGroup(jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ResumoLayout.createSequentialGroup()
                        .addComponent(jLabel_ItensPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_ExcluirItem))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        jPanel_ResumoLayout.setVerticalGroup(
            jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ResumoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ResumoLayout.createSequentialGroup()
                        .addGroup(jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ResumoPedido)
                            .addComponent(jLabel_ItensPedido))
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ResumoLayout.createSequentialGroup()
                        .addComponent(jButton_ExcluirItem, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_ResumoLayout.createSequentialGroup()
                        .addGroup(jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_NrPedido)
                            .addComponent(jLabel_NrPedidoResp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ClientePedido)
                            .addComponent(jLabel_ClientePedidoResp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ValorTotalPedido)
                            .addComponent(jLabel_ValorTotalPedidoResp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_ResumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_StatusPedido)
                            .addComponent(jLabel_StatusPedidoResp))
                        .addGap(18, 18, 18)
                        .addComponent(jButton_CancelarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButton_FinalizarPedido.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_FinalizarPedido.setText("Finalizar Pedido!");
        jButton_FinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FinalizarPedidoActionPerformed(evt);
            }
        });

        jPanel_MontarPizza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_MontarPizzaMouseClicked(evt);
            }
        });

        jLabel_Forma.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Forma.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Forma.setText("Forma:");

        jComboBox_Forma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel_Dimensao.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Dimensao.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Dimensao.setText("Dimensão:");

        jLabel_Area.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Area.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Area.setText("Área:");

        jLabel_Sabor1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Sabor1.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Sabor1.setText("Sabor 1:");

        jComboBox_Sabor1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel_Sabor2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Sabor2.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Sabor2.setText("Sabor 2:");

        jComboBox_Sabor2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton_AdicionarItem.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_AdicionarItem.setText("Adicionar item");
        jButton_AdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AdicionarItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_MontarPizzaLayout = new javax.swing.GroupLayout(jPanel_MontarPizza);
        jPanel_MontarPizza.setLayout(jPanel_MontarPizzaLayout);
        jPanel_MontarPizzaLayout.setHorizontalGroup(
            jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MontarPizzaLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_MontarPizzaLayout.createSequentialGroup()
                        .addComponent(jLabel_Sabor2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Sabor2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_MontarPizzaLayout.createSequentialGroup()
                        .addGroup(jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_Forma)
                            .addComponent(jLabel_Sabor1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox_Forma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox_Sabor1, 0, 152, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MontarPizzaLayout.createSequentialGroup()
                            .addComponent(jLabel_Area)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_Area, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MontarPizzaLayout.createSequentialGroup()
                            .addComponent(jLabel_Dimensao)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_Dimensao, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton_AdicionarItem, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(15, 15, 15))
        );
        jPanel_MontarPizzaLayout.setVerticalGroup(
            jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MontarPizzaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Forma)
                    .addComponent(jComboBox_Forma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Dimensao)
                    .addComponent(jTextField_Dimensao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_MontarPizzaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_Sabor1)
                            .addComponent(jComboBox_Sabor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_Sabor2)
                            .addComponent(jComboBox_Sabor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_MontarPizzaLayout.createSequentialGroup()
                        .addGroup(jPanel_MontarPizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_Area)
                            .addComponent(jTextField_Area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_AdicionarItem)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButton_MontarPizza.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_MontarPizza.setText("Montar Pizza");
        jButton_MontarPizza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MontarPizzaActionPerformed(evt);
            }
        });

        jButton_Voltar1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Voltar1.setText("Voltar");
        jButton_Voltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Voltar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_RealizarPedidoBGLayout = new javax.swing.GroupLayout(jPanel_RealizarPedidoBG);
        jPanel_RealizarPedidoBG.setLayout(jPanel_RealizarPedidoBGLayout);
        jPanel_RealizarPedidoBGLayout.setHorizontalGroup(
            jPanel_RealizarPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_RealizarPedidoBGLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel_RealizarPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_RealizarPedidoBGLayout.createSequentialGroup()
                        .addGroup(jPanel_RealizarPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_FinalizarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_RealizarPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel_MontarPizza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_RealizarPedidoBGLayout.createSequentialGroup()
                                    .addComponent(jLabel_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel_Telefone)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField_FiltroTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton_MontarPizza, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Voltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_Resumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_RealizarPedidoBGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_IconEsq2)
                .addGap(18, 18, 18)
                .addComponent(jLabel_Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_IconDir)
                .addGap(185, 185, 185))
        );
        jPanel_RealizarPedidoBGLayout.setVerticalGroup(
            jPanel_RealizarPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_RealizarPedidoBGLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel_RealizarPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_RealizarPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel_IconEsq2)
                        .addComponent(jLabel_Title, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel_IconDir))
                .addGap(18, 18, 18)
                .addGroup(jPanel_RealizarPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Cliente)
                    .addComponent(jComboBox_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Telefone)
                    .addComponent(jTextField_FiltroTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_MontarPizza)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_MontarPizza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_RealizarPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_FinalizarPedido)
                    .addComponent(jButton_Voltar1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Resumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_RealizarPedidoBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_RealizarPedidoBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AdicionarItemActionPerformed
        // Iniciando variaveis:
        String forma = null;
        Sabor sabor1 = null;
        Sabor sabor2 = null;
        String area = null;
        String dimensao = null;
        cliente = (Cliente) jComboBox_Cliente.getSelectedItem();
        
        // Recupera as informações preenchidas:
        if(jComboBox_Sabor1.getSelectedIndex() != 0) {
            sabor1 = (Sabor) jComboBox_Sabor1.getSelectedItem();
        }
        if(jComboBox_Sabor2.getSelectedIndex() != 0) {
            sabor2 = (Sabor) jComboBox_Sabor2.getSelectedItem();
        }
        if(jComboBox_Forma.getSelectedIndex() != 0) {
            forma = (String) jComboBox_Forma.getSelectedItem();
        }
        // Aqui apenas um dos campos podera ser recuperado, salvamos o que tiver sido preenchido:
        if(!jTextField_Area.getText().equals("")) {
            area = jTextField_Area.getText();
        }  
        if(!jTextField_Dimensao.getText().equals("") 
            && !jTextField_Dimensao.getText().equals("Raio") 
            && !jTextField_Dimensao.getText().equals("Lado")) {
            dimensao = jTextField_Dimensao.getText();
        }
        if(pedidoEmAberto == null) {
            // Gera o novo pedido:
            pedidoEmAberto = pedidoControl.gerarNovoPedido(cliente, forma, sabor1, sabor2, area, dimensao);
        } else {
            // Adiciona item ao pedido:
            pedidoControl.adicionarItemPedido(pedidoEmAberto, forma, sabor1, sabor2, area, dimensao);
        }    

        // Mostra resumo pedido:
        carregaTelaPedidos();
        desabilitaFormularioMontagem();
    }//GEN-LAST:event_jButton_AdicionarItemActionPerformed

    private void jButton_ExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExcluirItemActionPerformed
        if(pedidoControl.excluirItemPedido(pedidoEmAberto, idItemSelecionado)) {
            apresentarMsg("Item excluido!");
            carregaTelaPedidos();
        } else {
            apresentarMsg("Erro ao excluir item!");
        }
    }//GEN-LAST:event_jButton_ExcluirItemActionPerformed

    private void jButton_FinalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FinalizarPedidoActionPerformed
        if(pedidoControl.atualizarStatus(pedidoEmAberto.getNrPedido(), 1)) {
            apresentarMsg("Pedido realizado!");
            carregaTelaPedidos();
        } else {
            apresentarMsg("Erro ao finalizar pedido!");
        }
    }//GEN-LAST:event_jButton_FinalizarPedidoActionPerformed

    private void jButton_MontarPizzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MontarPizzaActionPerformed
        habilitaFormularioMontagem();
    }//GEN-LAST:event_jButton_MontarPizzaActionPerformed

    private void jButton_CancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarPedidoActionPerformed
        if(pedidoControl.excluirPedido(pedidoEmAberto)) {
            apresentarMsg("Pedido cancelado!");
            carregaTelaPedidos();
        } else {
            apresentarMsg("Erro ao cancelar pedido!");
        }
    }//GEN-LAST:event_jButton_CancelarPedidoActionPerformed

    private void jButton_Voltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Voltar1ActionPerformed
        this.home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_Voltar1ActionPerformed

    private void jPanel_ResumoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_ResumoMouseClicked
        // Limpa seleção da tabela:
        jTable_Itens.clearSelection();
        desabilitaBotao_ExcluirItem();
    }//GEN-LAST:event_jPanel_ResumoMouseClicked

    private void jTable_ItensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ItensMouseClicked
        // Recupera dados selecionados na tabela:
        if(evt.getClickCount() == 1) {
            idItemSelecionado = (int) jTable_Itens.getModel().getValueAt(jTable_Itens.getSelectedRow(),0);
            habilitaBotao_ExcluirItem();
        }  
    }//GEN-LAST:event_jTable_ItensMouseClicked

    private void jPanel_RealizarPedidoBGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_RealizarPedidoBGMouseClicked
        // Limpa seleção da tabela:
        jTable_Itens.clearSelection();
        desabilitaBotao_ExcluirItem();
    }//GEN-LAST:event_jPanel_RealizarPedidoBGMouseClicked

    private void jPanel_MontarPizzaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_MontarPizzaMouseClicked
        // Limpa seleção da tabela:
        jTable_Itens.clearSelection();
        desabilitaBotao_ExcluirItem();
    }//GEN-LAST:event_jPanel_MontarPizzaMouseClicked
// ----------------------------------------------------------------------------------------------------------------------------- //

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AdicionarItem;
    private javax.swing.JButton jButton_CancelarPedido;
    private javax.swing.JButton jButton_ExcluirItem;
    private javax.swing.JButton jButton_FinalizarPedido;
    private javax.swing.JButton jButton_MontarPizza;
    private javax.swing.JButton jButton_Voltar1;
    private javax.swing.JComboBox<Cliente> jComboBox_Cliente;
    private javax.swing.JComboBox<String> jComboBox_Forma;
    private javax.swing.JComboBox<String> jComboBox_Sabor1;
    private javax.swing.JComboBox<String> jComboBox_Sabor2;
    private javax.swing.JLabel jLabel_Area;
    private javax.swing.JLabel jLabel_Cliente;
    private javax.swing.JLabel jLabel_ClientePedido;
    private javax.swing.JLabel jLabel_ClientePedidoResp;
    private javax.swing.JLabel jLabel_Dimensao;
    private javax.swing.JLabel jLabel_Forma;
    private javax.swing.JLabel jLabel_IconDir;
    private javax.swing.JLabel jLabel_IconEsq2;
    private javax.swing.JLabel jLabel_ItensPedido;
    private javax.swing.JLabel jLabel_NrPedido;
    private javax.swing.JLabel jLabel_NrPedidoResp;
    private javax.swing.JLabel jLabel_ResumoPedido;
    private javax.swing.JLabel jLabel_Sabor1;
    private javax.swing.JLabel jLabel_Sabor2;
    private javax.swing.JLabel jLabel_StatusPedido;
    private javax.swing.JLabel jLabel_StatusPedidoResp;
    private javax.swing.JLabel jLabel_Telefone;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JLabel jLabel_ValorTotalPedido;
    private javax.swing.JLabel jLabel_ValorTotalPedidoResp;
    private javax.swing.JPanel jPanel_MontarPizza;
    private javax.swing.JPanel jPanel_RealizarPedidoBG;
    private javax.swing.JPanel jPanel_Resumo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_Itens;
    private javax.swing.JTextField jTextField_Area;
    private javax.swing.JTextField jTextField_Dimensao;
    private javax.swing.JTextField jTextField_FiltroTelefone;
    // End of variables declaration//GEN-END:variables
}
