/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PizzaApp.View;

import PizzaApp.Controller.TipoSaborControl;
import PizzaApp.Model.TipoSabor;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author joeau
 */
public class TelaAtualizarValores extends javax.swing.JFrame {
    // --------------------------------------------------------Atributos:---------------------------------------------------------- //
    // Variáveis globais:
    private TelaHome home;
    // -------------------------------------------------------Contrutores:-------------------------------------------------------- //
    public TelaAtualizarValores() {
        initComponents();
    }
    // ------------------------------------------------------------- //
    // Contrutor Modificado:
    public TelaAtualizarValores(TelaHome home) {
        // Armazenamos a tela anterior para a navegabilidade (opção "voltar"):
        this.home = home;
        // Inicialização:
        initComponents();
        inicializacaoPersonalizada();
    }
    // -----------------------------------------------------Classes Internas:------------------------------------------------------ //
    // Classe aninhada para implementar DocumentListener, alterando alguns recursos:
    // Campos de texto para reajuste de valores:
    private class TextFieldsDLReajustes implements DocumentListener {
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
            if(!jTextField_ValorSimples.getText().isEmpty() || !jTextField_ValorEspecial.getText().isEmpty() || !jTextField_ValorPremium.getText().isEmpty()) {
                // Habilita/desabilita campos:
                if(!jTextField_ValorSimples.getText().isEmpty() && jTextField_ValorEspecial.getText().isEmpty() && jTextField_ValorPremium.getText().isEmpty()) {
                    jTextField_ValorSimples.setEnabled(true);
                    jTextField_ValorEspecial.setEnabled(false);
                    jTextField_ValorPremium.setEnabled(false);
                } else if (jTextField_ValorSimples.getText().isEmpty() && !jTextField_ValorEspecial.getText().isEmpty() && jTextField_ValorPremium.getText().isEmpty()) {
                    jTextField_ValorSimples.setEnabled(false);
                    jTextField_ValorEspecial.setEnabled(true);
                    jTextField_ValorPremium.setEnabled(false);
                } else if (jTextField_ValorSimples.getText().isEmpty() && jTextField_ValorEspecial.getText().isEmpty() && !jTextField_ValorPremium.getText().isEmpty()) {
                    jTextField_ValorSimples.setEnabled(false);
                    jTextField_ValorEspecial.setEnabled(false);
                    jTextField_ValorPremium.setEnabled(true);
                }            
                habilitaBotao_Atualizar();
            } else {
                jTextField_ValorSimples.setEnabled(true);
                jTextField_ValorEspecial.setEnabled(true);
                jTextField_ValorPremium.setEnabled(true);
                desabilitaBotao_Atualizar();
            }
        }
    }
    // --------------------------------------------------------Métodos:---------------------------------------------------------- //
    // Método de Inicialização Personalizada:
    public void inicializacaoPersonalizada() {
        // (A ideia é colocar aqui as configurações adicionais de inicialização)
        
        // Campos do Reajuste:
        jTextField_ValorSimples.getDocument().addDocumentListener(new TextFieldsDLReajustes());
        jTextField_ValorEspecial.getDocument().addDocumentListener(new TextFieldsDLReajustes());
        jTextField_ValorPremium.getDocument().addDocumentListener(new TextFieldsDLReajustes());
        
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
        desabilitaBotao_Atualizar();
        carregarValores();
    }
    // ------------------------------------------------------------- //
    // Limpeza dos campos:
    public void limparCampos() {
        // Campos cadastro:
        jTextField_ValorSimples.setText("");
        jTextField_ValorEspecial.setText("");
        jTextField_ValorPremium.setText("");
    }
    // ------------------------------------------------------------- //
    // Carregamento de valores:
    public void carregarValores(){
        ArrayList<TipoSabor> tipos = new TipoSaborControl().listarTipos();
        
        jLabel_ValorSimples.setText(Double.toString(tipos.get(0).getValorCm2()));
        jLabel_ValorEspecial.setText(Double.toString(tipos.get(1).getValorCm2()));
        jLabel_ValorPremium.setText(Double.toString(tipos.get(2).getValorCm2()));
    }
    // ------------------------------------------------------------- //
    // Metodos dos botões:
    // Botão Cadastrar:
    public void desabilitaBotao_Atualizar() {
        jButton_Atualizar.setForeground(new Color (0x65686b));
        jButton_Atualizar.setEnabled(false);
    }
    public void habilitaBotao_Atualizar() {
        jButton_Atualizar.setForeground(new Color (0x161717));
        jButton_Atualizar.setEnabled(true);
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
        jButton_Voltar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel_Tipo = new javax.swing.JLabel();
        jLabel_Valor = new javax.swing.JLabel();
        jLabel_Simples = new javax.swing.JLabel();
        jLabel_Especial = new javax.swing.JLabel();
        jLabel_Premium = new javax.swing.JLabel();
        jLabel_ValorSimples = new javax.swing.JLabel();
        jLabel_ValorEspecial = new javax.swing.JLabel();
        jLabel_ValorPremium = new javax.swing.JLabel();
        jTextField_ValorSimples = new javax.swing.JTextField();
        jTextField_ValorEspecial = new javax.swing.JTextField();
        jTextField_ValorPremium = new javax.swing.JTextField();
        jLabel_Reajuste = new javax.swing.JLabel();
        jButton_Atualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Atualizar Valores");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 49, 49));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));

        jLabel_IconEsq2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoESQ.png"))); // NOI18N

        jLabel_Title.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Title.setText("Atualizar Valores");

        jLabel_IconDir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoDIR.png"))); // NOI18N

        jButton_Voltar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Voltar.setText("Voltar");
        jButton_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VoltarActionPerformed(evt);
            }
        });

        jLabel_Tipo.setFont(new java.awt.Font("Segoe UI Black", 0, 21)); // NOI18N
        jLabel_Tipo.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Tipo.setText("Tipo");

        jLabel_Valor.setFont(new java.awt.Font("Segoe UI Black", 0, 21)); // NOI18N
        jLabel_Valor.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Valor.setText("Valor Atual");

        jLabel_Simples.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Simples.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Simples.setText("Simples");

        jLabel_Especial.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Especial.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Especial.setText("Especial");

        jLabel_Premium.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_Premium.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Premium.setText("Premium");

        jLabel_ValorSimples.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_ValorSimples.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_ValorSimples.setText("        ");

        jLabel_ValorEspecial.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_ValorEspecial.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_ValorEspecial.setText("        ");

        jLabel_ValorPremium.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel_ValorPremium.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_ValorPremium.setText("        ");

        jTextField_ValorSimples.setForeground(new java.awt.Color(22, 23, 23));
        jTextField_ValorSimples.setMaximumSize(new java.awt.Dimension(64, 22));

        jTextField_ValorEspecial.setForeground(new java.awt.Color(22, 23, 23));
        jTextField_ValorEspecial.setMaximumSize(new java.awt.Dimension(64, 22));

        jTextField_ValorPremium.setForeground(new java.awt.Color(22, 23, 23));
        jTextField_ValorPremium.setMaximumSize(new java.awt.Dimension(64, 22));

        jLabel_Reajuste.setFont(new java.awt.Font("Segoe UI Black", 0, 21)); // NOI18N
        jLabel_Reajuste.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_Reajuste.setText("Reajuste");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Tipo)
                    .addComponent(jLabel_Simples)
                    .addComponent(jLabel_Especial)
                    .addComponent(jLabel_Premium))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Valor)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_ValorEspecial)
                            .addComponent(jLabel_ValorSimples)
                            .addComponent(jLabel_ValorPremium))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel_Reajuste)
                        .addGap(9, 9, 9))
                    .addComponent(jTextField_ValorEspecial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_ValorSimples, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_ValorPremium, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Tipo)
                    .addComponent(jLabel_Valor)
                    .addComponent(jLabel_Reajuste))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Simples)
                    .addComponent(jLabel_ValorSimples)
                    .addComponent(jTextField_ValorSimples, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Especial)
                    .addComponent(jLabel_ValorEspecial)
                    .addComponent(jTextField_ValorEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Premium)
                    .addComponent(jLabel_ValorPremium)
                    .addComponent(jTextField_ValorPremium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jButton_Atualizar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Atualizar.setText("Atualizar");
        jButton_Atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_IconEsq2)
                .addGap(18, 18, 18)
                .addComponent(jLabel_Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_IconDir)
                .addGap(171, 171, 171))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_Atualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Voltar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_IconEsq2)
                    .addComponent(jLabel_IconDir)
                    .addComponent(jLabel_Title, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(65, 65, 65)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Voltar)
                    .addComponent(jButton_Atualizar))
                .addContainerGap(213, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VoltarActionPerformed
        this.home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_VoltarActionPerformed

    private void jButton_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AtualizarActionPerformed
        int idTipo = 0;
        String valor = "";
        if(!jTextField_ValorSimples.getText().isEmpty()){
            idTipo = 1;
            valor = jTextField_ValorSimples.getText();
        } else if(!jTextField_ValorEspecial.getText().isEmpty()){
            idTipo = 2;
            valor = jTextField_ValorEspecial.getText();
        } else if(!jTextField_ValorPremium.getText().isEmpty()){
            idTipo = 3;
            valor = jTextField_ValorPremium.getText();
        } 
        // Chama função:
        new TipoSaborControl().atualizarValor(idTipo, valor);
        // Pós operação:
        resetTela();
    }//GEN-LAST:event_jButton_AtualizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Atualizar;
    private javax.swing.JButton jButton_Voltar;
    private javax.swing.JLabel jLabel_Especial;
    private javax.swing.JLabel jLabel_IconDir;
    private javax.swing.JLabel jLabel_IconEsq2;
    private javax.swing.JLabel jLabel_Premium;
    private javax.swing.JLabel jLabel_Reajuste;
    private javax.swing.JLabel jLabel_Simples;
    private javax.swing.JLabel jLabel_Tipo;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JLabel jLabel_Valor;
    private javax.swing.JLabel jLabel_ValorEspecial;
    private javax.swing.JLabel jLabel_ValorPremium;
    private javax.swing.JLabel jLabel_ValorSimples;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField_ValorEspecial;
    private javax.swing.JTextField jTextField_ValorPremium;
    private javax.swing.JTextField jTextField_ValorSimples;
    // End of variables declaration//GEN-END:variables
}
