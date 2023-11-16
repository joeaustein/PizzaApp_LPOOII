/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PizzaApp.View;

import PizzaApp.Controller.PedidoControl;
import PizzaApp.Model.Pedido;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joeau
 */
public class TelaAcompanharPedido extends javax.swing.JFrame {
 // --------------------------------------------------------Atributos:---------------------------------------------------------- //
    // Variáveis globais:
    private TelaHome home;
    private int nrPedido;
    private int status;
    private PedidoControl pedidoControl;
    // -------------------------------------------------------Contrutores:-------------------------------------------------------- //
    public TelaAcompanharPedido() {
        initComponents();
    }
    // ------------------------------------------------------------- //
    // Contrutor Modificado:
    public TelaAcompanharPedido(TelaHome home) {
        // Armazenamos a tela anterior para a navegabilidade (opção "voltar"):
        this.home = home;
        this.pedidoControl = new PedidoControl();
        // Inicialização:
        initComponents();
        inicializacaoPersonalizada();
    }
    // --------------------------------------------------------Métodos:---------------------------------------------------------- //
    // Método de Inicialização Personalizada:
    public void inicializacaoPersonalizada() {
        // (A ideia é colocar aqui as configurações adicionais de inicialização)

        // Ordenação da tabela por coluna:
        jTable_Pedidos.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int colunaClicada = jTable_Pedidos.columnAtPoint(e.getPoint());
                ordenarPorColuna(jTable_Pedidos, colunaClicada);
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
        carregaTabela();
        resetSelecao();
    }
    // Reset de seleção na tabela:
    public void resetSelecao() {
        desabilitaBotao_AtStatus();
        jTable_Pedidos.clearSelection();
        nrPedido = 0;
        status = 0;
        jLabel_NrPedidoResp.setText("");
        atualizarSprite();
    }
    // ------------------------------------------------------------- //   
    // Atualizar Sprite:
    public void atualizarSprite() {
        switch (status) {
            case 1:
                jLabel_SpriteEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/PedidoAberto.png"))); 
                break;
            case 2:
                jLabel_SpriteEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/PedidoACaminho.png")));       
                break;
            case 3:
                jLabel_SpriteEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/PedidoEntregue.png")));
                break;  
            default:
                jLabel_SpriteEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/PedidoNaoSelecionado.png")));
        }
    }
    // ------------------------------------------------------------- //     
    // Metodos dos botões:
    // Botão Atualizar Status:
    public void desabilitaBotao_AtStatus() {
        jButton_AtStatus.setForeground(new Color (0x65686b));
        jButton_AtStatus.setEnabled(false);
    }
    public void habilitaBotao_AtStatus() {
        jButton_AtStatus.setForeground(new Color (0x161717));
        jButton_AtStatus.setEnabled(true);
    }
    // ------------------------------------------------------------- //
    // Métodos tabela:
    // Carregamento:
    public void carregaTabela() {
        // Carregamento dados tabela:
        DefaultTableModel tableModel = (DefaultTableModel) jTable_Pedidos.getModel();
        // Limpa tabela antes de carregar, para não duplicar:
        tableModel.setRowCount(0);
        // Popula tableModel para jTable_Pedidos:
        ArrayList<Pedido> pedidos = pedidoControl.listarPedidos();
        // Tratamento em caso de null:
        if(pedidos != null) {
            pedidos.forEach((Pedido pedido) -> {
                tableModel.addRow(new Object[] {pedido.getNrPedido(),
                                                                           pedido.getCliente().getNome(),
                                                                           pedido.getValorTotal(),
                                                                           pedido.getStatus()});
            });
        } else {
            apresentarMsg("Impossivel carregar tabela de pedidos!");
        }
        jTable_Pedidos.setModel(tableModel);
        
        // Isso aqui vai fazer com que a celula da tabela nao seja editavel diretamente:
        DefaultCellEditor cellEditor = new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(java.util.EventObject e) {
                return false; // Impede a edição de todas as células
            }
        };
        jTable_Pedidos.setDefaultEditor(Object.class, cellEditor);
        
        // Carregamento inicial ordena pelo primeiro campo:
        ordenarPorColuna(jTable_Pedidos, 3);
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_AcompanharPedidoBG = new javax.swing.JPanel();
        jLabel_IconDir = new javax.swing.JLabel();
        jLabel_Title = new javax.swing.JLabel();
        jLabel_IconEsq2 = new javax.swing.JLabel();
        jLabel_SpriteEntrega = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Pedidos = new javax.swing.JTable();
        jButton_Voltar = new javax.swing.JButton();
        jButton_AtStatus = new javax.swing.JButton();
        jLabel_NrPedido = new javax.swing.JLabel();
        jLabel_NrPedidoResp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acompanhar Pedido");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel_AcompanharPedidoBG.setBackground(new java.awt.Color(255, 49, 49));
        jPanel_AcompanharPedidoBG.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel_AcompanharPedidoBG.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel_AcompanharPedidoBG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_AcompanharPedidoBGMouseClicked(evt);
            }
        });

        jLabel_IconDir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoDIR.png"))); // NOI18N

        jLabel_Title.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_Title.setText("Acompanhar Pedido");

        jLabel_IconEsq2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/MiniLogoESQ.png"))); // NOI18N

        jLabel_SpriteEntrega.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel_SpriteEntrega.setForeground(new java.awt.Color(255, 189, 89));
        jLabel_SpriteEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/PedidoNaoSelecionado.png"))); // NOI18N
        jLabel_SpriteEntrega.setText("                                    ");
        jLabel_SpriteEntrega.setMaximumSize(new java.awt.Dimension(446, 118));
        jLabel_SpriteEntrega.setMinimumSize(new java.awt.Dimension(446, 118));
        jLabel_SpriteEntrega.setPreferredSize(new java.awt.Dimension(446, 118));
        jLabel_SpriteEntrega.setRequestFocusEnabled(false);

        jTable_Pedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nr Pedido", "Cliente", "Valor", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_Pedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_PedidosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Pedidos);

        jButton_Voltar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_Voltar.setText("Voltar");
        jButton_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VoltarActionPerformed(evt);
            }
        });

        jButton_AtStatus.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton_AtStatus.setText("Atualizar Status");
        jButton_AtStatus.setPreferredSize(new java.awt.Dimension(180, 35));
        jButton_AtStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AtStatusActionPerformed(evt);
            }
        });

        jLabel_NrPedido.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel_NrPedido.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_NrPedido.setText("Pedido:");

        jLabel_NrPedidoResp.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel_NrPedidoResp.setForeground(new java.awt.Color(22, 23, 23));
        jLabel_NrPedidoResp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_NrPedidoResp.setText("   ");

        javax.swing.GroupLayout jPanel_AcompanharPedidoBGLayout = new javax.swing.GroupLayout(jPanel_AcompanharPedidoBG);
        jPanel_AcompanharPedidoBG.setLayout(jPanel_AcompanharPedidoBGLayout);
        jPanel_AcompanharPedidoBGLayout.setHorizontalGroup(
            jPanel_AcompanharPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AcompanharPedidoBGLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(jPanel_AcompanharPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel_AcompanharPedidoBGLayout.createSequentialGroup()
                        .addComponent(jLabel_NrPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_NrPedidoResp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_AtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jButton_Voltar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_AcompanharPedidoBGLayout.createSequentialGroup()
                        .addComponent(jLabel_IconEsq2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_Title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_IconDir)))
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_AcompanharPedidoBGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_SpriteEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
        );
        jPanel_AcompanharPedidoBGLayout.setVerticalGroup(
            jPanel_AcompanharPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AcompanharPedidoBGLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel_AcompanharPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_IconEsq2)
                    .addComponent(jLabel_IconDir)
                    .addComponent(jLabel_Title))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_AcompanharPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_Voltar)
                    .addComponent(jButton_AtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_AcompanharPedidoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_NrPedido)
                        .addComponent(jLabel_NrPedidoResp)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_SpriteEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_AcompanharPedidoBG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_AcompanharPedidoBG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AtStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AtStatusActionPerformed
        // Chama a função:
        // (Obs: passa status após realizar incremento)
        pedidoControl.atualizarStatus(nrPedido, ++status); 
        // Pós operação:
        carregaTabela();
        atualizarSprite();
        if(status > 2) {desabilitaBotao_AtStatus();}
    }//GEN-LAST:event_jButton_AtStatusActionPerformed

    private void jButton_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VoltarActionPerformed
        this.home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_VoltarActionPerformed

    private void jTable_PedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_PedidosMouseClicked
        // Recupera dados selecionados na tabela:
        if(evt.getClickCount() == 1) {
            nrPedido = (int) jTable_Pedidos.getModel().getValueAt(jTable_Pedidos.getSelectedRow(),0);
            status = (int) jTable_Pedidos.getModel().getValueAt(jTable_Pedidos.getSelectedRow(),3);
            // Função para atualizar imagem de entrega:
            atualizarSprite();
            // Não permitimos atulizar status de pedidos finalizados;
            if(status < 3) {
                habilitaBotao_AtStatus();
            } else {
                desabilitaBotao_AtStatus();
            }
            jLabel_NrPedidoResp.setText(String.valueOf(nrPedido));
        }   
    }//GEN-LAST:event_jTable_PedidosMouseClicked

    private void jPanel_AcompanharPedidoBGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_AcompanharPedidoBGMouseClicked
        // Quando clica fora da tabela, deve sair do modo seleção para edição/exclusão:
        resetSelecao();
    }//GEN-LAST:event_jPanel_AcompanharPedidoBGMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AtStatus;
    private javax.swing.JButton jButton_Voltar;
    private javax.swing.JLabel jLabel_IconDir;
    private javax.swing.JLabel jLabel_IconEsq2;
    private javax.swing.JLabel jLabel_NrPedido;
    private javax.swing.JLabel jLabel_NrPedidoResp;
    private javax.swing.JLabel jLabel_SpriteEntrega;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JPanel jPanel_AcompanharPedidoBG;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Pedidos;
    // End of variables declaration//GEN-END:variables
}
