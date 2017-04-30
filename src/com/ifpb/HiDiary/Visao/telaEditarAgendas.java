/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.HiDiary.Visao;

import com.ifpb.HiDiary.Modelo.Agenda;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lyndemberg
 */
public class telaEditarAgendas extends javax.swing.JFrame {

    /**
     * Creates new form telaEditarAgendas
     */
    public telaEditarAgendas() {
        
        initComponents();
        inicializaListaAgendas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonCriarAgenda = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListaAgendas = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        buttonEditarAgenda = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonCriarAgenda.setText("Criar Agenda");
        buttonCriarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCriarAgendaActionPerformed(evt);
            }
        });

        jListaAgendas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jScrollPane1.setViewportView(jListaAgendas);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("AGENDAS");

        buttonEditarAgenda.setText("Editar");
        buttonEditarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarAgendaActionPerformed(evt);
            }
        });

        buttonExcluir.setText("Excluir");
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonCriarAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonEditarAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonExcluir)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonCriarAgenda, buttonEditarAgenda, buttonExcluir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonCriarAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonEditarAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonExcluir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonCriarAgenda, buttonEditarAgenda, buttonExcluir});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCriarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCriarAgendaActionPerformed
        telaCriarAgenda criarAgenda = new telaCriarAgenda();
        criarAgenda.setVisible(true);
    }//GEN-LAST:event_buttonCriarAgendaActionPerformed

    private void buttonEditarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarAgendaActionPerformed
        telaAgenda editar = new telaAgenda(PaginaInicial.usuarioLogado.buscarAgenda(jListaAgendas.getSelectedValue()));
        editar.setVisible(true);
    }//GEN-LAST:event_buttonEditarAgendaActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        if(jListaAgendas.getSelectedValue() != null){
            int opcao = JOptionPane.showConfirmDialog(null, "Confirmar?");
            if(opcao==0){
                if(PaginaInicial.usuarioLogado.removerAgenda(jListaAgendas.getSelectedValue())){
                    inicializaListaAgendas();
                    PaginaInicial.cbAgenda30days.removeAllItems();
                    PaginaInicial.inicializarComboBox();
                    JOptionPane.showMessageDialog(null, "Agenda removida");
                }
            }
        }
            
            
        
    }//GEN-LAST:event_buttonExcluirActionPerformed

    public static void inicializaListaAgendas(){
        jListaAgendas.removeAll();
        String[] vetor = new String[PaginaInicial.usuarioLogado.getNomesAgendas().size()];
        for(int i=0; i<vetor.length; i++){
            vetor[i]=PaginaInicial.usuarioLogado.getNomesAgendas().get(i);
        }
        
        jListaAgendas.setListData(vetor);
    }
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
            java.util.logging.Logger.getLogger(telaEditarAgendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaEditarAgendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaEditarAgendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaEditarAgendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaEditarAgendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCriarAgenda;
    private javax.swing.JButton buttonEditarAgenda;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JLabel jLabel1;
    private static javax.swing.JList<String> jListaAgendas;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
