/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PizzaApp.View;

import PizzaApp.Controller.SaborControl;
import PizzaApp.Model.Sabor;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joeau
 */
public class TelaCadastrarSabor extends javax.swing.JFrame {
    // --------------------------------------------------------Atributos:---------------------------------------------------------- //
    // Variáveis globais:
    private TelaHome home;
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
    // --------------------------------------------------------Métodos:---------------------------------------------------------- //
    // Método de Inicialização Personalizada:
    public void inicializacaoPersonalizada() {
        // (A ideia é colocar aqui as configurações adicionais de inicialização)

        // Carrega comboBox do Tipo:
        carregaComboBoxTipo();
        
        // Aqui setamos um Renderer para a ComboBox:
        jComboBox_Tipo.setRenderer(new ComboBoxTipoDLCR());
        
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
        jButton_Excluir1 = new javax.swing.JButton();
        jButton_Voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Sabor");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 49, 49));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));

        jLabel_IconEsq2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoESQ.png"))); // NOI18N

        jLabel_Title.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Title.setText("Cadastrar Sabor");

        jLabel_IconDir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoDIR.png"))); // NOI18N

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

        jButton_Excluir1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Excluir1.setText("Cadastrar!");
        jButton_Excluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Excluir1ActionPerformed(evt);
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
                        .addComponent(jButton_Excluir1)
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
                    .addComponent(jButton_Excluir1))
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
    }//GEN-LAST:event_jButton_ExcluirActionPerformed

    private void jButton_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VoltarActionPerformed
        this.home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_VoltarActionPerformed

    private void jComboBox_TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_TipoActionPerformed

    private void jButton_Excluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Excluir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Excluir1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Excluir;
    private javax.swing.JButton jButton_Excluir1;
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
