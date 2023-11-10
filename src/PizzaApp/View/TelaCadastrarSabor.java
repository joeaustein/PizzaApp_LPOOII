/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PizzaApp.View;

import PizzaApp.Controller.SaborControl;
import PizzaApp.Model.Sabor;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
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
public class TelaCadastrarSabor extends javax.swing.JFrame {
    // --------------------------------------------------------Atributos:---------------------------------------------------------- //
    // Variáveis globais:
    private TelaHome home;
    private int idSabor;
    // -------------------------------------------------------Contrutores:-------------------------------------------------------- //
    public TelaCadastrarSabor() {
        initComponents();
    }
    // ------------------------------------------------------------- //
    // Contrutor Modificado:
    public TelaCadastrarSabor(TelaHome home) {
        // Armazenamos a tela anterior para a navegabilidade (opção "voltar"):
        this.home = home;
        // Inicialização:
        initComponents();
        inicializacaoPersonalizada();
    }
    // -----------------------------------------------------Classes Internas:------------------------------------------------------ //
    private class ComboBoxTipoDLCR extends DefaultListCellRenderer{
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            // index 0 ficara em cinza:      
            if(index == 0) {
                renderer.setForeground(new Color(0x65686b));
            }
            return this;
        }  
    }
    // ------------------------------------------------------------- //
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
            if(!jTextField_NomeSabor.getText().isEmpty() && jComboBox_Tipo.getSelectedIndex() != 0) {
                habilitaBotao_Cadastrar();
            } else {
                desabilitaBotao_Cadastrar();
            }
        }
    }
    // ------------------------------------------------------------- //
    // Habilita/desabilita botão MontarPizza conforme seleção do cliente:
    private class ComboBoxTipoAL implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(jComboBox_Tipo.getSelectedIndex() != 0 && !jTextField_NomeSabor.getText().isEmpty()) {
                habilitaBotao_Cadastrar();         
            } else {
                desabilitaBotao_Cadastrar();
            }
        } 
    }
    // --------------------------------------------------------Métodos:---------------------------------------------------------- //
    // Método de Inicialização Personalizada:
    public void inicializacaoPersonalizada() {
        // (A ideia é colocar aqui as configurações adicionais de inicialização)

        // Carrega comboBox do Tipo:
        carregaComboBoxTipo();
        // Action listener:
        jComboBox_Tipo.addActionListener(new ComboBoxTipoAL()); 
        // Aqui setamos um Renderer para a ComboBox:
        jComboBox_Tipo.setRenderer(new ComboBoxTipoDLCR());
        // Campo do nome:
        jTextField_NomeSabor.getDocument().addDocumentListener(new TextFieldsDLCadastro());
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
        carregaTabela();
        desabilitaBotao_Cadastrar();
        desabilitaBotao_Excluir();
        jTextField_NomeSabor.setText("");
        jComboBox_Tipo.setSelectedIndex(0);
        jTable_Sabores.clearSelection();
    }
    // ------------------------------------------------------------- //
    // Métodos tabela:
    // Carregamento:
    public void carregaTabela() {
        // Carregamento dados tabela:
        DefaultTableModel tableModel = (DefaultTableModel) jTable_Sabores.getModel();
        // Limpa tabela antes de carregar, para não duplicar:
        tableModel.setRowCount(0);
        // Popula tableModel para jTable_Clientes:
        ArrayList<Sabor> sabores = new SaborControl().listarSabores();
        // Tratamento em caso de null:
        if(sabores != null) {
            sabores.forEach((Sabor sabor) -> {
                tableModel.addRow(new Object[] {sabor.getIdSabor(),
                                                                           sabor.getTipoSabor().getNomeCategoria(),
                                                                           sabor.getNomeSabor()});
            });
        } else {
            apresentarMsg("Impossivel carregar tabela de sabores!");
        }
        jTable_Sabores.setModel(tableModel);
        
        // Isso aqui vai fazer com que a celula da tabela nao seja editavel diretamente:
        DefaultCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(java.util.EventObject e) {
                return false; // Impede a edição de todas as células
            }
        };
        jTable_Sabores.setDefaultEditor(Object.class, cellEditor);       
    }
    // ------------------------------------------------------------- //
    // Carregamento combo box Tipo:
    public void carregaComboBoxTipo() {   
        // Define modelo ComboBox:
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        jComboBox_Tipo.setModel(model);
        // Inicia a ComboBox com uma mensagem:
        model.addElement("Selecione o tipo");
        model.addElement("SIMPLES");        
        model.addElement("ESPECIAL");  
        model.addElement("PREMIUM");         
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
    // Botão Excluir:
    public void desabilitaBotao_Excluir() {
        jButton_Excluir.setForeground(new Color (0x65686b));
        jButton_Excluir.setEnabled(false);
    }
    public void habilitaBotao_Excluir() {
        jButton_Excluir.setForeground(new Color (0x161717));
        jButton_Excluir.setEnabled(true);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel_IconEsq2 = new javax.swing.JLabel();
        jLabel_Title = new javax.swing.JLabel();
        jLabel_IconDir = new javax.swing.JLabel();
        jPanel_Cadastro = new javax.swing.JPanel();
        jButton_Excluir = new javax.swing.JButton();
        jTextField_NomeSabor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Sabores = new javax.swing.JTable();
        jLabel_NomeSabor = new javax.swing.JLabel();
        jLabel_Tipo = new javax.swing.JLabel();
        jComboBox_Tipo = new javax.swing.JComboBox<>();
        jButton_Cadastrar = new javax.swing.JButton();
        jButton_Voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Sabor");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 49, 49));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel_IconEsq2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoESQ.png"))); // NOI18N

        jLabel_Title.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Title.setText("Cadastrar Sabor");

        jLabel_IconDir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoDIR.png"))); // NOI18N

        jPanel_Cadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_CadastroMouseClicked(evt);
            }
        });

        jButton_Excluir.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Excluir.setText("Excluir");
        jButton_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExcluirActionPerformed(evt);
            }
        });

        jTextField_NomeSabor.setForeground(new java.awt.Color(22, 23, 23));
        jTextField_NomeSabor.setMaximumSize(new java.awt.Dimension(64, 22));

        jTable_Sabores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tipo", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_Sabores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_SaboresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Sabores);

        jLabel_NomeSabor.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_NomeSabor.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_NomeSabor.setText("Nome:");

        jLabel_Tipo.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Tipo.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Tipo.setText("Tipo:");

        jComboBox_Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_Tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TipoActionPerformed(evt);
            }
        });

        jButton_Cadastrar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Cadastrar.setText("Cadastrar!");
        jButton_Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_CadastroLayout = new javax.swing.GroupLayout(jPanel_Cadastro);
        jPanel_Cadastro.setLayout(jPanel_CadastroLayout);
        jPanel_CadastroLayout.setHorizontalGroup(
            jPanel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CadastroLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                    .addGroup(jPanel_CadastroLayout.createSequentialGroup()
                        .addComponent(jLabel_NomeSabor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_NomeSabor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_Tipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_CadastroLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_Cadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Excluir)))
                .addGap(33, 33, 33))
        );
        jPanel_CadastroLayout.setVerticalGroup(
            jPanel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CadastroLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_NomeSabor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_NomeSabor)
                    .addComponent(jLabel_Tipo)
                    .addComponent(jComboBox_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_CadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Excluir)
                    .addComponent(jButton_Cadastrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jButton_Voltar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Voltar.setText("Voltar");
        jButton_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_Voltar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(182, 182, 182)
                            .addComponent(jLabel_IconEsq2)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel_Title)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel_IconDir))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(137, 137, 137)
                            .addComponent(jPanel_Cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_IconEsq2)
                    .addComponent(jLabel_IconDir)
                    .addComponent(jLabel_Title))
                .addGap(49, 49, 49)
                .addComponent(jPanel_Cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Voltar)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExcluirActionPerformed
        // Chama a função:
        new SaborControl().excluirSabor(idSabor);
        // Pós operação:
        resetTela();
    }//GEN-LAST:event_jButton_ExcluirActionPerformed

    private void jButton_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VoltarActionPerformed
        this.home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_VoltarActionPerformed

    private void jComboBox_TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_TipoActionPerformed

    private void jButton_CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CadastrarActionPerformed
        // Recupera dados dos campos e salva nas variáveis:
        String nomeSabor = jTextField_NomeSabor.getText();
        int idTipo = jComboBox_Tipo.getSelectedIndex();
        // Chama função de cadastro:
        new SaborControl().cadastrarSabor(idTipo, nomeSabor);
        // Pós operação:
        resetTela();
    }//GEN-LAST:event_jButton_CadastrarActionPerformed

    private void jTable_SaboresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_SaboresMouseClicked
        // Recupera dados selecionados na tabela:
        if(evt.getClickCount() == 1) {
            idSabor = (int) jTable_Sabores.getModel().getValueAt(jTable_Sabores.getSelectedRow(),0);
            habilitaBotao_Excluir();
        }   
    }//GEN-LAST:event_jTable_SaboresMouseClicked

    private void jPanel_CadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_CadastroMouseClicked
        // Quando clica fora da tabela, deve sair do modo seleção para edição/exclusão:
        jTable_Sabores.clearSelection();
        desabilitaBotao_Excluir();
    }//GEN-LAST:event_jPanel_CadastroMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // Quando clica fora da tabela, deve sair do modo seleção para edição/exclusão:
        jTable_Sabores.clearSelection();
        desabilitaBotao_Excluir();
    }//GEN-LAST:event_jPanel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cadastrar;
    private javax.swing.JButton jButton_Excluir;
    private javax.swing.JButton jButton_Voltar;
    private javax.swing.JComboBox<String> jComboBox_Tipo;
    private javax.swing.JLabel jLabel_IconDir;
    private javax.swing.JLabel jLabel_IconEsq2;
    private javax.swing.JLabel jLabel_NomeSabor;
    private javax.swing.JLabel jLabel_Tipo;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Cadastro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Sabores;
    private javax.swing.JTextField jTextField_NomeSabor;
    // End of variables declaration//GEN-END:variables
}
