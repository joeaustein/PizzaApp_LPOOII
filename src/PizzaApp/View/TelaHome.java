/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PizzaApp.View;

/**
 *
 * @author joeau
 */
public class TelaHome extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public TelaHome() {
        initComponents();
    }
    
    // ------Navegabilidade:------- //
    private void abrirTela_CadastrarCliente(java.awt.event.ActionEvent evt) {
        TelaCadastrarCliente cadastroCliente = new TelaCadastrarCliente(this);
        cadastroCliente.setVisible(true);
        this.setVisible(false);
    }
    // ------------------------------- //
    private void abrirTela_RealizarPedido(java.awt.event.ActionEvent evt) {
        TelaRealizarPedido realizarPedido = new TelaRealizarPedido(this);
        realizarPedido.setVisible(true);
        this.setVisible(false);
    }
    // ------------------------------- //
    private void abrirTela_AtualizarValores(java.awt.event.ActionEvent evt) {
        TelaAtualizarValores atualizarValores = new TelaAtualizarValores(this);
        atualizarValores.setVisible(true);
        this.setVisible(false);
    }
    // ------------------------------- //
    private void abrirTela_CadastrarSabor(java.awt.event.ActionEvent evt) {
        TelaCadastrarSabor cadastrarSabor = new TelaCadastrarSabor(this);
        cadastrarSabor.setVisible(true);
        this.setVisible(false);
    }
    // ------------------------------- //
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_ImagemBG = new javax.swing.JLabel();
        jMenuBar1_Home = new javax.swing.JMenuBar();
        jMenu_Home_Programa = new javax.swing.JMenu();
        jMenuItem_Sair = new javax.swing.JMenuItem();
        jMenu_Home_Cadastro = new javax.swing.JMenu();
        jMenuItem_CadastrarCliente = new javax.swing.JMenuItem();
        jMenuItem_CadastrarSabor = new javax.swing.JMenuItem();
        jMenuItem_AtualizarValores = new javax.swing.JMenuItem();
        jMenu_Home_Pedido = new javax.swing.JMenu();
        jMenuItem_RealizarPedido = new javax.swing.JMenuItem();
        jMenuItem_VisualizarPedido = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        setBackground(new java.awt.Color(255, 49, 49));
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jLabel_ImagemBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/BGLogo.png"))); // NOI18N
        jLabel_ImagemBG.setMaximumSize(new java.awt.Dimension(800, 600));
        jLabel_ImagemBG.setMinimumSize(new java.awt.Dimension(800, 600));
        jLabel_ImagemBG.setPreferredSize(new java.awt.Dimension(800, 600));

        jMenuBar1_Home.setBorder(null);
        jMenuBar1_Home.setForeground(new java.awt.Color(255, 189, 89));
        jMenuBar1_Home.setToolTipText("");
        jMenuBar1_Home.setName(""); // NOI18N

        jMenu_Home_Programa.setForeground(new java.awt.Color(22, 23, 23));
        jMenu_Home_Programa.setText("Programa");
        jMenu_Home_Programa.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N

        jMenuItem_Sair.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jMenuItem_Sair.setForeground(new java.awt.Color(22, 23, 23));
        jMenuItem_Sair.setText("Sair");
        jMenuItem_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SairActionPerformed(evt);
            }
        });
        jMenu_Home_Programa.add(jMenuItem_Sair);

        jMenuBar1_Home.add(jMenu_Home_Programa);

        jMenu_Home_Cadastro.setForeground(new java.awt.Color(22, 23, 23));
        jMenu_Home_Cadastro.setText("Cadastro");
        jMenu_Home_Cadastro.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N

        jMenuItem_CadastrarCliente.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jMenuItem_CadastrarCliente.setForeground(new java.awt.Color(22, 23, 23));
        jMenuItem_CadastrarCliente.setText("Cadastrar Cliente");
        jMenuItem_CadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_CadastrarClienteActionPerformed(evt);
            }
        });
        jMenu_Home_Cadastro.add(jMenuItem_CadastrarCliente);

        jMenuItem_CadastrarSabor.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jMenuItem_CadastrarSabor.setForeground(new java.awt.Color(22, 23, 23));
        jMenuItem_CadastrarSabor.setText("Cadastrar Sabor");
        jMenuItem_CadastrarSabor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_CadastrarSaborActionPerformed(evt);
            }
        });
        jMenu_Home_Cadastro.add(jMenuItem_CadastrarSabor);

        jMenuItem_AtualizarValores.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jMenuItem_AtualizarValores.setForeground(new java.awt.Color(22, 23, 23));
        jMenuItem_AtualizarValores.setText("Atualizar Valores");
        jMenuItem_AtualizarValores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_AtualizarValoresActionPerformed(evt);
            }
        });
        jMenu_Home_Cadastro.add(jMenuItem_AtualizarValores);

        jMenuBar1_Home.add(jMenu_Home_Cadastro);

        jMenu_Home_Pedido.setForeground(new java.awt.Color(22, 23, 23));
        jMenu_Home_Pedido.setText("Pedido");
        jMenu_Home_Pedido.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N

        jMenuItem_RealizarPedido.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jMenuItem_RealizarPedido.setForeground(new java.awt.Color(22, 23, 23));
        jMenuItem_RealizarPedido.setText("Realizar Pedido");
        jMenuItem_RealizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_RealizarPedidoActionPerformed(evt);
            }
        });
        jMenu_Home_Pedido.add(jMenuItem_RealizarPedido);

        jMenuItem_VisualizarPedido.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jMenuItem_VisualizarPedido.setForeground(new java.awt.Color(22, 23, 23));
        jMenuItem_VisualizarPedido.setText("Visualizar Pedido");
        jMenu_Home_Pedido.add(jMenuItem_VisualizarPedido);

        jMenuBar1_Home.add(jMenu_Home_Pedido);

        setJMenuBar(jMenuBar1_Home);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_ImagemBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_ImagemBG, javax.swing.GroupLayout.PREFERRED_SIZE, 567, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_CadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_CadastrarClienteActionPerformed
        abrirTela_CadastrarCliente(evt);
    }//GEN-LAST:event_jMenuItem_CadastrarClienteActionPerformed

    private void jMenuItem_RealizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_RealizarPedidoActionPerformed
         abrirTela_RealizarPedido(evt);
    }//GEN-LAST:event_jMenuItem_RealizarPedidoActionPerformed

    private void jMenuItem_AtualizarValoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_AtualizarValoresActionPerformed
        abrirTela_AtualizarValores(evt);
    }//GEN-LAST:event_jMenuItem_AtualizarValoresActionPerformed

    private void jMenuItem_CadastrarSaborActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_CadastrarSaborActionPerformed
        abrirTela_CadastrarSabor(evt);
    }//GEN-LAST:event_jMenuItem_CadastrarSaborActionPerformed

    private void jMenuItem_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SairActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenuItem_SairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_ImagemBG;
    private javax.swing.JMenuBar jMenuBar1_Home;
    private javax.swing.JMenuItem jMenuItem_AtualizarValores;
    private javax.swing.JMenuItem jMenuItem_CadastrarCliente;
    private javax.swing.JMenuItem jMenuItem_CadastrarSabor;
    private javax.swing.JMenuItem jMenuItem_RealizarPedido;
    private javax.swing.JMenuItem jMenuItem_Sair;
    private javax.swing.JMenuItem jMenuItem_VisualizarPedido;
    private javax.swing.JMenu jMenu_Home_Cadastro;
    private javax.swing.JMenu jMenu_Home_Pedido;
    private javax.swing.JMenu jMenu_Home_Programa;
    // End of variables declaration//GEN-END:variables
}
