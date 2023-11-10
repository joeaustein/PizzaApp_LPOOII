/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package PizzaApp.View;

import PizzaApp.Controller.ClienteControl;
import PizzaApp.Model.Cliente;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joeau
 */
public class TelaCadastrarCliente extends javax.swing.JFrame {
    // --------------------------------------------------------Atributos:---------------------------------------------------------- //
    // Variáveis globais:
    private String nome;
    private String sobreNome;
    private String telefone;
    private String oldTel;
    private boolean editMode;
    private TelaHome home;
    // -------------------------------------------------------Contrutores:-------------------------------------------------------- //
    // Contrutor Default:
    public TelaCadastrarCliente() {
        initComponents();
    }
    // ------------------------------------------------------------- //
    // Contrutor Modificado:
    public TelaCadastrarCliente(TelaHome home) {
        // Armazenamos a tela anterior para a navegabilidade (opção "voltar"):
        this.home = home;
        this.nome = "";
        this.sobreNome = "";
        this.telefone = "";
        this.oldTel = "";
        this.editMode = false;
        // Inicialização:
        initComponents();
        inicializacaoPersonalizada();
    }
    // -----------------------------------------------------Classes Internas:------------------------------------------------------ //
    // Classe aninhada para implementar DocumentListener, alterando alguns recursos:
    // Campos de texto do cadastro:
    private class TextFieldsDLCadastro implements DocumentListener {
        // Valida preenchimento dos campos para habilitar/desabilitar botões:
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
        // Função para ativar/desativar botoes conforme o preenchimento dos campos:
        private void updateButtonState() {
            if ( (!jTextField_Nome.getText().isEmpty() && !jTextField_Nome.getText().equals("(Apenas letras)"))
                && (!jTextField_Sobrenome.getText().isEmpty() && !jTextField_Sobrenome.getText().equals("(Apenas letras)"))
                && (!jTextField_Telefone.getText().isEmpty() && !jTextField_Telefone.getText().equals("(Apenas numeros)")) ){
                if(editMode){
                    desabilitaBotao_Cadastrar();
                    habilitaBotao_Atualizar();
                } else {
                    desabilitaBotao_Atualizar();
                    habilitaBotao_Cadastrar();
                }
            } else {
                desabilitaBotao_Cadastrar();
                desabilitaBotao_Atualizar();
            }
        }
    }
    // ------------------------------------------------------------- //
    // Classe aninhada para implementar DocumentListener, alterando alguns recursos:
    // Campos de texto do filtro:
    private class TextFieldsDLFiltro implements DocumentListener {
        // Valida preenchimento dos campos para habilitar/desabilitar botões e limpar texto de instrução:
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
            // Botao "Filtrar" é desabilitado quando nenhum dos campos nao estiver preenchido:
            if ((jTextField_FiltroNome.getText().equals("Nome") || jTextField_FiltroNome.getText().equals("")) 
                && (jTextField_FiltroSobrenome.getText().equals("Sobrenome") || jTextField_FiltroSobrenome.getText().equals("")) 
                && (jTextField_FiltroTelefone.getText().equals("Telefone") || jTextField_FiltroTelefone.getText().equals(""))) 
            {
                desabilitaBotao_Filtrar();
            } else {
                habilitaBotao_Filtrar();
            }           
        }
    }
    // --------------------------------------------------------Métodos:---------------------------------------------------------- //
    // Método de Inicialização Personalizada:
    public void inicializacaoPersonalizada() {
        // (A ideia é colocar aqui as configurações adicionais de inicialização)
      
        // Listeners: 
        // Adiciona FocusListener modificado para cada campo de texto
        // para exibir/esconder as mensagens instrutivas:        
        adicionaFLCampoMsg(jTextField_FiltroNome, "Nome");
        adicionaFLCampoMsg(jTextField_FiltroSobrenome, "Sobrenome");
        adicionaFLCampoMsg(jTextField_FiltroTelefone, "Telefone");
        adicionaFLCampoMsg(jTextField_Nome, "(Apenas letras)");
        adicionaFLCampoMsg(jTextField_Sobrenome, "(Apenas letras)");
        adicionaFLCampoMsg(jTextField_Telefone, "(Apenas numeros)");
        // Validação de preenchimento de campos para ativar/desativar botões:
        // Campos do filtro:
        jTextField_FiltroNome.getDocument().addDocumentListener(new TextFieldsDLFiltro());
        jTextField_FiltroSobrenome.getDocument().addDocumentListener(new TextFieldsDLFiltro());
        jTextField_FiltroTelefone.getDocument().addDocumentListener(new TextFieldsDLFiltro());
        // Campos do cadastro:
        jTextField_Nome.getDocument().addDocumentListener(new TextFieldsDLCadastro());
        jTextField_Sobrenome.getDocument().addDocumentListener(new TextFieldsDLCadastro());
        jTextField_Telefone.getDocument().addDocumentListener(new TextFieldsDLCadastro());
        // Ordenação da tabela por coluna:
        jTable_Clientes.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int colunaClicada = jTable_Clientes.columnAtPoint(e.getPoint());
                ordenarPorColuna(jTable_Clientes, colunaClicada);
            }
        });
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
        limparCampos();
        desabilitaBotao_Cadastrar();
        desabilitaBotao_Atualizar();
        desabilitaBotao_Editar();
        desabilitaBotao_Excluir();
        desabilitaBotao_Filtrar();
        carregaTabela();
    }
    // ------------------------------------------------------------- //
    // Métodos dos campos de texto: 
    // Limpeza dos campos:
    public void limparCampos() {
        // Campos cadastro:
        jTextField_Nome.setText("");
        jTextField_Sobrenome.setText("");
        jTextField_Telefone.setText("");
        
        // Campos filtro: (reset de msg e cor):
        jTextField_FiltroNome.setForeground(new Color(0x65686b));
        jTextField_FiltroSobrenome.setForeground(new Color(0x65686b));
        jTextField_FiltroTelefone.setForeground(new Color(0x65686b));    
        jTextField_FiltroNome.setText("Nome");
        jTextField_FiltroSobrenome.setText("Sobrenome");
        jTextField_FiltroTelefone.setText("Telefone");
        jTextField_Nome.setForeground(new Color(0x65686b));
        jTextField_Sobrenome.setForeground(new Color(0x65686b));
        jTextField_Telefone.setForeground(new Color(0x65686b));    
        jTextField_Nome.setText("(Apenas letras)");
        jTextField_Sobrenome.setText("(Apenas letras)");
        jTextField_Telefone.setText("(Apenas numeros)");
        
        editMode = false;
        jTable_Clientes.clearSelection();
    }
    // ------------------------------------------------------------- //
    // Preenche os campos com os valores dos atributos:
    public void preencherCampos() {
        jTextField_Nome.setForeground(new Color(0x161717));
        jTextField_Sobrenome.setForeground(new Color(0x161717));
        jTextField_Telefone.setForeground(new Color(0x161717));  
        jTextField_Nome.setText(nome);
        jTextField_Sobrenome.setText(sobreNome);
        jTextField_Telefone.setText(telefone);
    }
    // ------------------------------------------------------------- //
    // Essa função irá adicionar um FocusListener num campo de texto para exibir/esconder a msg instrutiva:
    public void adicionaFLCampoMsg(JTextField textField, String msg) {
        // Iniciando com a mensagem instrutiva em cinza:
        textField.setForeground(new Color(0x65686b));
        textField.setText(msg);

        // Adicionando FocusListener: 
        textField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(msg)) {
                    textField.setText("");
                    textField.setForeground(new Color(0x161717));
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(msg);
                    textField.setForeground(new Color(0x65686b));
                }
            }     
        });
    }
    // ------------------------------------------------------------- //    
    // Metodos dos botões:
    // Botão Cadastrar:
    public void desabilitaBotao_Cadastrar() {
        jButton_Cadastrar.setForeground(new Color (0x65686b));
        jButton_Cadastrar.setEnabled(false);
    }
    public void habilitaBotao_Cadastrar() {
        jButton_Cadastrar.setForeground(new Color (0x161717));
        jButton_Cadastrar.setEnabled(true);
    }
    // ------------------------------------------------------------- //
    // Botão Atualizar:
    public void desabilitaBotao_Atualizar() {
        jButton_Atualizar.setForeground(new Color (0x65686b));
        jButton_Atualizar.setEnabled(false);
    }
    public void habilitaBotao_Atualizar() {
        jButton_Atualizar.setForeground(new Color (0x161717));
        jButton_Atualizar.setEnabled(true);
    }
    // ------------------------------------------------------------- //
    // Botão Limpar:
    public void desabilitaBotao_Limpar() {
        jButton_Limpar.setForeground(new Color (0x65686b));
        jButton_Limpar.setEnabled(false);
    }
    public void habilitaBotao_Limpar() {
        jButton_Limpar.setForeground(new Color (0x161717));
        jButton_Limpar.setEnabled(true);
    }
    // ------------------------------------------------------------- //
    // Botão Editar:
    public void desabilitaBotao_Editar() {
        jButton_Editar.setForeground(new Color (0x65686b));
        jButton_Editar.setEnabled(false);
    }
    public void habilitaBotao_Editar() {
        jButton_Editar.setForeground(new Color (0x161717));
        jButton_Editar.setEnabled(true);
    }
    // ------------------------------------------------------------- //
    // Botão Excluir:
    public void desabilitaBotao_Excluir() {
        jButton_Excluir.setForeground(new Color (0x65686b));
        jButton_Excluir.setEnabled(false);
    }
    public void habilitaBotao_Excluir() {
        jButton_Excluir.setForeground(new Color (0x161717));
        jButton_Excluir.setEnabled(true);
    }
    // ------------------------------------------------------------- //
    // Botão Filtrar:
    public void desabilitaBotao_Filtrar() {
        jButton_Filtrar.setForeground(new Color (0x65686b));
        jButton_Filtrar.setEnabled(false);
    }
    public void habilitaBotao_Filtrar() {
        jButton_Filtrar.setForeground(new Color (0x161717));
        jButton_Filtrar.setEnabled(true);
    }
    // ------------------------------------------------------------- //    
    // Métodos tabela:
    // Carregamento:
    public void carregaTabela() {
        // Carregamento dados tabela:
        DefaultTableModel tableModel = (DefaultTableModel) jTable_Clientes.getModel();
        ClienteControl clienteControl = new ClienteControl();
        // Limpa tabela antes de carregar, para não duplicar:
        tableModel.setRowCount(0);
        // Popula tableModel para jTable_Clientes:
        ArrayList<Cliente> clientes = clienteControl.listarClientes();
        // Tratamento em caso de null:
        if(clientes != null) {
            clientes.forEach((Cliente cliente) -> {
                tableModel.addRow(new Object[] {cliente.getNome(),
                                                                           cliente.getSobreNome(),
                                                                           cliente.getTelefone()});
            });
        } else {
            apresentarMsg("Impossivel carregar tabela de clientes!");
        }
        jTable_Clientes.setModel(tableModel);
        
        // Isso aqui vai fazer com que a celula da tabela nao seja editavel diretamente:
        DefaultCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(java.util.EventObject e) {
                return false; // Impede a edição de todas as células
            }
        };
        jTable_Clientes.setDefaultEditor(Object.class, cellEditor);
        
        // Carregamento inicial ordena pelo primeiro campo:
        ordenarPorColuna(jTable_Clientes, 0);
    }
    // ------------------------------------------------------------- //
    // Carregamento com filtros:
    public void carregaTabelaComFiltro(String nome, String sobrenome, String telefone) {
        // Carregamento dados tabela:
        DefaultTableModel tableModel = (DefaultTableModel) jTable_Clientes.getModel();
        ClienteControl clienteControl = new ClienteControl();
        // Limpa tabela antes de carregar, para não duplicar:
        tableModel.setRowCount(0);
        // Popula tableModel para jTable_Clientes:
        ArrayList<Cliente> clientes = clienteControl.listarClientesComFiltro(nome, sobrenome, telefone);
        // Tratamento em caso de null:
        if(clientes != null) {
            clientes.forEach((Cliente cliente) -> {
                tableModel.addRow(new Object[] {cliente.getNome(),
                                                                           cliente.getSobreNome(),
                                                                           cliente.getTelefone()});
            });
        } else {
            apresentarMsg("Impossivel carregar tabela de clientes!");
        }
        jTable_Clientes.setModel(tableModel);
        // Carregamento inicial ordena pelo primeiro campo:
        ordenarPorColuna(jTable_Clientes, 0);
    }
    // ------------------------------------------------------------- //
    // Ordenação:
    private static void ordenarPorColuna(JTable table, int coluna) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Vector<Vector> data = model.getDataVector();

        data.sort(new Comparator<Vector>() {
            @Override
            public int compare(Vector o1, Vector o2) {
                
                Object valor1 = o1.get(coluna);
                Object valor2 = o2.get(coluna);

                if (valor1 instanceof Comparable && valor2 instanceof Comparable) {
                    return ((Comparable) valor1).compareTo((Comparable) valor2);
                } else {
                    return valor1.toString().compareTo(valor2.toString());
                }
            }
        });
        model.fireTableDataChanged();
    }
    // ----------------------------------------------------------------------------------------------------------------------------- //
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel_CadastroClienteBG = new javax.swing.JPanel();
        jLabel_Title = new javax.swing.JLabel();
        jLabel_Sobrenome = new javax.swing.JLabel();
        jLabel_Nome1 = new javax.swing.JLabel();
        jLabel_Sobrenome1 = new javax.swing.JLabel();
        jTextField_Nome = new javax.swing.JTextField();
        jTextField_Sobrenome = new javax.swing.JTextField();
        jTextField_Telefone = new javax.swing.JTextField();
        jButton_Limpar = new javax.swing.JButton();
        jButton_Cadastrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Clientes = new javax.swing.JTable();
        jLabel_IconDir = new javax.swing.JLabel();
        jLabel_IconEsq2 = new javax.swing.JLabel();
        jLabel_Cadastrados = new javax.swing.JLabel();
        jButton_Editar = new javax.swing.JButton();
        jButton_Excluir = new javax.swing.JButton();
        jButton_Voltar = new javax.swing.JButton();
        jButton_Atualizar = new javax.swing.JButton();
        jButton_Filtrar = new javax.swing.JButton();
        jTextField_FiltroSobrenome = new javax.swing.JTextField();
        jTextField_FiltroNome = new javax.swing.JTextField();
        jTextField_FiltroTelefone = new javax.swing.JTextField();

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
        setTitle("Cadastrar Cliente");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel_CadastroClienteBG.setBackground(new java.awt.Color(255, 49, 49));
        jPanel_CadastroClienteBG.setForeground(new java.awt.Color(255, 49, 49));
        jPanel_CadastroClienteBG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_CadastroClienteBGMouseClicked(evt);
            }
        });

        jLabel_Title.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Title.setText("Cadastro Cliente");

        jLabel_Sobrenome.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Sobrenome.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Sobrenome.setText("Sobrenome:");

        jLabel_Nome1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Nome1.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Nome1.setText("Nome:");

        jLabel_Sobrenome1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Sobrenome1.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Sobrenome1.setText("Telefone:");

        jTextField_Nome.setForeground(new java.awt.Color(255, 189, 89));

        jTextField_Sobrenome.setForeground(new java.awt.Color(255, 189, 89));

        jTextField_Telefone.setForeground(new java.awt.Color(255, 189, 89));

        jButton_Limpar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Limpar.setText("Limpar");
        jButton_Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LimparActionPerformed(evt);
            }
        });

        jButton_Cadastrar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Cadastrar.setText("Cadastrar!");
        jButton_Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CadastrarActionPerformed(evt);
            }
        });

        jTable_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Sobrenome", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Clientes);

        jLabel_IconDir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoDIR.png"))); // NOI18N

        jLabel_IconEsq2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoESQ.png"))); // NOI18N

        jLabel_Cadastrados.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Cadastrados.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Cadastrados.setText("Clientes Cadastrados:");

        jButton_Editar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Editar.setText("Editar");
        jButton_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditarActionPerformed(evt);
            }
        });

        jButton_Excluir.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Excluir.setText("Excluir");
        jButton_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExcluirActionPerformed(evt);
            }
        });

        jButton_Voltar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Voltar.setText("Voltar");
        jButton_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VoltarActionPerformed(evt);
            }
        });

        jButton_Atualizar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Atualizar.setText("Atualizar");
        jButton_Atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AtualizarActionPerformed(evt);
            }
        });

        jButton_Filtrar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Filtrar.setText("Filtrar");
        jButton_Filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FiltrarActionPerformed(evt);
            }
        });

        jTextField_FiltroSobrenome.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTextField_FiltroSobrenome.setForeground(new java.awt.Color(101, 104, 107));
        jTextField_FiltroSobrenome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_FiltroSobrenomeActionPerformed(evt);
            }
        });

        jTextField_FiltroNome.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTextField_FiltroNome.setForeground(new java.awt.Color(101, 104, 107));
        jTextField_FiltroNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_FiltroNomeActionPerformed(evt);
            }
        });

        jTextField_FiltroTelefone.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTextField_FiltroTelefone.setForeground(new java.awt.Color(101, 104, 107));
        jTextField_FiltroTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_FiltroTelefoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_CadastroClienteBGLayout = new javax.swing.GroupLayout(jPanel_CadastroClienteBG);
        jPanel_CadastroClienteBG.setLayout(jPanel_CadastroClienteBGLayout);
        jPanel_CadastroClienteBGLayout.setHorizontalGroup(
            jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CadastroClienteBGLayout.createSequentialGroup()
                .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_CadastroClienteBGLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel_CadastroClienteBGLayout.createSequentialGroup()
                                    .addComponent(jButton_Cadastrar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton_Atualizar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton_Limpar))
                                .addGroup(jPanel_CadastroClienteBGLayout.createSequentialGroup()
                                    .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel_Sobrenome)
                                        .addComponent(jLabel_Nome1)
                                        .addComponent(jLabel_Sobrenome1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_Sobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_Telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel_CadastroClienteBGLayout.createSequentialGroup()
                                .addComponent(jTextField_FiltroNome, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_FiltroSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_FiltroTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel_Cadastrados)
                            .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_CadastroClienteBGLayout.createSequentialGroup()
                                    .addComponent(jButton_Editar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton_Excluir)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_Voltar))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_CadastroClienteBGLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel_IconEsq2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_Title)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_IconDir)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel_CadastroClienteBGLayout.setVerticalGroup(
            jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CadastroClienteBGLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel_IconEsq2)
                        .addComponent(jLabel_Title, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel_IconDir))
                .addGap(18, 18, 18)
                .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Nome1)
                    .addComponent(jTextField_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Sobrenome)
                    .addComponent(jTextField_Sobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Sobrenome1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Limpar)
                    .addComponent(jButton_Cadastrar)
                    .addComponent(jButton_Atualizar))
                .addGap(8, 8, 8)
                .addComponent(jLabel_Cadastrados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_FiltroNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_FiltroSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_FiltroTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Filtrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CadastroClienteBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Editar)
                    .addComponent(jButton_Excluir)
                    .addComponent(jButton_Voltar))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_CadastroClienteBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_CadastroClienteBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CadastrarActionPerformed
        // Recupera dados dos campos e salva nas variáveis:
        nome = jTextField_Nome.getText();
        sobreNome = jTextField_Sobrenome.getText();
        telefone = jTextField_Telefone.getText();
        
        // Instancia a controladora e chama função de cadastro:
        ClienteControl clienteControl = new ClienteControl();
        clienteControl.cadastrarCliente(nome, sobreNome, telefone);
        // Pós operação:
        resetTela();
    }//GEN-LAST:event_jButton_CadastrarActionPerformed

    private void jButton_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VoltarActionPerformed
        this.home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_VoltarActionPerformed

    private void jButton_LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LimparActionPerformed
        resetTela();
    }//GEN-LAST:event_jButton_LimparActionPerformed

    private void jTable_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ClientesMouseClicked
        // Recupera dados selecionados na tabela:
        if(evt.getClickCount() == 1) {
            nome = (String) jTable_Clientes.getModel().getValueAt(jTable_Clientes.getSelectedRow(),0);
            sobreNome = (String) jTable_Clientes.getModel().getValueAt(jTable_Clientes.getSelectedRow(),1);
            telefone = (String) jTable_Clientes.getModel().getValueAt(jTable_Clientes.getSelectedRow(),2);
            // armazena telefone antigo para caso de alterações:
            oldTel = telefone; 
            habilitaBotao_Editar();
            habilitaBotao_Excluir();
        }   
    }//GEN-LAST:event_jTable_ClientesMouseClicked

    private void jButton_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AtualizarActionPerformed
        // Recupera dados dos campos e salva nas variáveis:
        nome = jTextField_Nome.getText();
        sobreNome = jTextField_Sobrenome.getText();
        telefone = jTextField_Telefone.getText();
        
        // Instancia a controladora e chama função de cadastro:
        ClienteControl clienteControl = new ClienteControl();
        clienteControl.atualizarCliente(nome, sobreNome, telefone, oldTel);
        // Pós operação:
        resetTela();
    }//GEN-LAST:event_jButton_AtualizarActionPerformed

    private void jButton_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditarActionPerformed
        // Entra no modo Edição:
        editMode = true;
        preencherCampos();
    }//GEN-LAST:event_jButton_EditarActionPerformed

    private void jPanel_CadastroClienteBGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_CadastroClienteBGMouseClicked
        // Quando clica fora da tabela, deve sair do modo seleção para edição/exclusão:
        jTable_Clientes.clearSelection();
        desabilitaBotao_Editar();
        desabilitaBotao_Excluir();
    }//GEN-LAST:event_jPanel_CadastroClienteBGMouseClicked

    private void jButton_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExcluirActionPerformed
        // Instancia a controladora e chama função de cadastro:
        ClienteControl clienteControl = new ClienteControl();
        clienteControl.excluirCliente(nome, sobreNome, telefone); 
        // Pós operação:
        resetTela();
    }//GEN-LAST:event_jButton_ExcluirActionPerformed

    private void jButton_FiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FiltrarActionPerformed
        // Carrega a tabela realizando uma busca com os filtros aplicados:
        carregaTabelaComFiltro(jTextField_FiltroNome.getText(), jTextField_FiltroSobrenome.getText(), jTextField_FiltroTelefone.getText());
    }//GEN-LAST:event_jButton_FiltrarActionPerformed

    private void jTextField_FiltroSobrenomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_FiltroSobrenomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_FiltroSobrenomeActionPerformed

    private void jTextField_FiltroNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_FiltroNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_FiltroNomeActionPerformed

    private void jTextField_FiltroTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_FiltroTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_FiltroTelefoneActionPerformed
// ----------------------------------------------------------------------------------------------------------------------------- //

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Atualizar;
    private javax.swing.JButton jButton_Cadastrar;
    private javax.swing.JButton jButton_Editar;
    private javax.swing.JButton jButton_Excluir;
    private javax.swing.JButton jButton_Filtrar;
    private javax.swing.JButton jButton_Limpar;
    private javax.swing.JButton jButton_Voltar;
    private javax.swing.JLabel jLabel_Cadastrados;
    private javax.swing.JLabel jLabel_IconDir;
    private javax.swing.JLabel jLabel_IconEsq2;
    private javax.swing.JLabel jLabel_Nome1;
    private javax.swing.JLabel jLabel_Sobrenome;
    private javax.swing.JLabel jLabel_Sobrenome1;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JPanel jPanel_CadastroClienteBG;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_Clientes;
    private javax.swing.JTextField jTextField_FiltroNome;
    private javax.swing.JTextField jTextField_FiltroSobrenome;
    private javax.swing.JTextField jTextField_FiltroTelefone;
    private javax.swing.JTextField jTextField_Nome;
    private javax.swing.JTextField jTextField_Sobrenome;
    private javax.swing.JTextField jTextField_Telefone;
    // End of variables declaration//GEN-END:variables

}
