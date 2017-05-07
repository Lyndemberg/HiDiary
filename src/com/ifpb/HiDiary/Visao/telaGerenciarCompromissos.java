
package com.ifpb.HiDiary.Visao;

import Excecoes.CompromissosException;
import com.ifpb.HiDiary.Controle.CompromissoDao;
import com.ifpb.HiDiary.Controle.CompromissoDaoBinario;
import com.ifpb.HiDiary.Controle.UsuarioDao;
import com.ifpb.HiDiary.Controle.UsuarioDaoBinario;
import com.ifpb.HiDiary.Modelo.Agenda;
import com.ifpb.HiDiary.Modelo.Compromisso;
import com.ifpb.HiDiary.Modelo.Usuario;
import static com.ifpb.HiDiary.Visao.PaginaInicial.jTable30days;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class telaGerenciarCompromissos extends javax.swing.JFrame {
    private static List<Compromisso> listaIntervalo;
    private static  CompromissoDao daoComp;
    public telaGerenciarCompromissos() {
        daoComp = new CompromissoDaoBinario();
        initComponents();
        dataInicio.setDate(java.sql.Date.valueOf(LocalDate.now()));
        dataFim.setDate(java.sql.Date.valueOf(LocalDate.now()));
        inicializaJTable();
    }

    public static void inicializaJTable(){
        try{
            labelErro.setText(null);
            buttonEditar.setVisible(false);
            buttonExcluir.setVisible(false);
            Date inicioDate = dataInicio.getDate();
            LocalDate inicio = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(inicioDate));
            Date fimDate = dataFim.getDate();
            LocalDate fim = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(fimDate));
            listaIntervalo = daoComp.compromissosIntervalo(PaginaInicial.usuarioLogado.getEmail(), inicio, fim);
            
                String[] titulos = {"Data","Hora","Descrição","Local"};
                String[][] matriz = new String[listaIntervalo.size()][4];
                for(int i=0; i<listaIntervalo.size(); i++){
                    Compromisso comp = listaIntervalo.get(i);

                    DateTimeFormatter dtfData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    matriz[i][0] = dtfData.format(comp.getData());

                    DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm");
                    matriz[i][1] = dtfHora.format(comp.getHora());

                    matriz[i][2] = comp.getDescricao();
                    matriz[i][3] = comp.getLocal();  
                }
                DefaultTableModel modelo = new DefaultTableModel(matriz, titulos);
                jTableCompromissos.setModel(modelo);
                ListSelectionModel modoSelecao = jTableCompromissos.getSelectionModel();
                modoSelecao.setSelectionMode(modoSelecao.SINGLE_SELECTION);
                jTableCompromissos.setSelectionModel(modoSelecao);
                buttonEditar.setVisible(true);
                buttonExcluir.setVisible(true);
                
            
             
        }catch(ClassNotFoundException | IOException | SQLException ex ){
            JOptionPane.showMessageDialog(null, "Falha na conexão");
        }catch(NullPointerException ex){
            buttonEditar.setVisible(false);
            buttonExcluir.setVisible(false);
            labelErro.setText("Insira datas válidas no intervalo");
            String[] titulos = {"Data","Hora","Descrição","Local"};
            String[][] matriz = new String[0][4];
            DefaultTableModel modelo = new DefaultTableModel(matriz, titulos);
            jTableCompromissos.setModel(modelo);
        }catch(DateTimeException ex){
            buttonEditar.setVisible(false);
            buttonExcluir.setVisible(false);
            labelErro.setText(ex.getMessage());
        }catch(CompromissosException ex){
            buttonEditar.setVisible(false);
            buttonExcluir.setVisible(false);
            String[] titulos = {"Data","Hora","Descrição","Local"};
            String[][] matriz = new String[0][4];
            labelErro.setText(ex.getMessage());
            DefaultTableModel modelo = new DefaultTableModel(matriz, titulos);
            jTableCompromissos.setModel(modelo);
        }
        
                
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataInicio = new com.toedter.calendar.JDateChooser();
        dataFim = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCompromissos = new javax.swing.JTable();
        buttonAtualizar = new javax.swing.JButton();
        buttonEditar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        labelErro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableCompromissos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Hora", "Descrição", "Local"
            }
        ));
        jScrollPane1.setViewportView(jTableCompromissos);

        buttonAtualizar.setText("Atualizar");
        buttonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAtualizarActionPerformed(evt);
            }
        });

        buttonEditar.setText("Editar");
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarActionPerformed(evt);
            }
        });

        buttonExcluir.setText("Excluir");
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirActionPerformed(evt);
            }
        });

        labelErro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelErro.setForeground(new java.awt.Color(255, 0, 0));
        labelErro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelErro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonAtualizar)
                .addGap(39, 39, 39))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dataFim, dataInicio});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAtualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelErro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(buttonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dataFim, dataInicio});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAtualizarActionPerformed
        inicializaJTable();
    }//GEN-LAST:event_buttonAtualizarActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed

        if(jTableCompromissos.getSelectedRow()!=-1){
           
            try {
                telaEditaCompromisso tela = new telaEditaCompromisso(listaIntervalo.get(jTableCompromissos.getSelectedRow()));
                tela.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha na conexão");
            }
            
        }else{
            JOptionPane.showMessageDialog(null,"Nenhum compromisso selecionado");
        }
        
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        if(jTableCompromissos.getSelectedRow()!=-1){
            int janela = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Remover compromisso",JOptionPane.YES_NO_OPTION);    
                if(janela==JOptionPane.OK_OPTION){
                try {
                    daoComp.delete(listaIntervalo.get(jTableCompromissos.getSelectedRow()));
                    inicializaJTable();
                    PaginaInicial.inicializarTabela();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(telaGerenciarCompromissos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(telaGerenciarCompromissos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(telaGerenciarCompromissos.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }
                }
        else{
                    JOptionPane.showMessageDialog(null,"Nenhum compromisso selecionado");
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

    
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
            java.util.logging.Logger.getLogger(telaGerenciarCompromissos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaGerenciarCompromissos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaGerenciarCompromissos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaGerenciarCompromissos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaGerenciarCompromissos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAtualizar;
    private static javax.swing.JButton buttonEditar;
    private static javax.swing.JButton buttonExcluir;
    private static com.toedter.calendar.JDateChooser dataFim;
    private static com.toedter.calendar.JDateChooser dataInicio;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTableCompromissos;
    private static javax.swing.JLabel labelErro;
    // End of variables declaration//GEN-END:variables
}
