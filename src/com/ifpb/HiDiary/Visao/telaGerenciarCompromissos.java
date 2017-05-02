
package com.ifpb.HiDiary.Visao;

import com.ifpb.HiDiary.Modelo.Agenda;
import com.ifpb.HiDiary.Modelo.Compromisso;
import com.ifpb.HiDiary.Modelo.Usuario;
import static com.ifpb.HiDiary.Visao.PaginaInicial.jTable30days;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class telaGerenciarCompromissos extends javax.swing.JFrame {
    private static List<Compromisso> listaIntervalo;
    public telaGerenciarCompromissos() {
        initComponents();
        inicializaJTable();
    }

    public static void inicializaJTable(){
                Usuario u = PaginaInicial.usuarioLogado;
                try{
                buttonEditar.setVisible(false);
                buttonExcluir.setVisible(false);
                Date inicioDate = dataInicio.getDate();
                LocalDate inicio = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(inicioDate));
                Date fimDate = dataFim.getDate();
                LocalDate fim = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(fimDate));
                listaIntervalo = u.compromissosIntervalo(inicio, fim);
                if(listaIntervalo==null){
                    buttonEditar.setVisible(false);
                    buttonExcluir.setVisible(false);
                    labelErro.setText("Sem compromissos");
                    String[] titulos = {"Data","Hora","Descrição","Local"};
                    String[][] matriz = new String[0][4];
                    DefaultTableModel modelo = new DefaultTableModel(matriz, titulos);
                    jTableCompromissos.setModel(modelo);   
                }else{
                    labelErro.setText(null);
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
                    buttonEditar.setVisible(true);
                    buttonExcluir.setVisible(true);
                }     
                    
                }catch(NullPointerException ex){
                    labelErro.setText("Insira um intervalo");
                    String[] titulos = {"Data","Hora","Descrição","Local"};
                    String[][] matriz = new String[0][4];
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
        labelErro = new javax.swing.JLabel();
        buttonAtualizar = new javax.swing.JButton();
        buttonEditar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableCompromissos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Hora", "Descrição", "Local"
            }
        ));
        jScrollPane1.setViewportView(jTableCompromissos);

        labelErro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelErro.setForeground(new java.awt.Color(255, 0, 0));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAtualizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelErro, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dataFim, dataInicio});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAtualizar))
                .addGap(16, 16, 16)
                .addComponent(labelErro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(buttonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dataFim, dataInicio});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAtualizarActionPerformed
        inicializaJTable();
    }//GEN-LAST:event_buttonAtualizarActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
     
//        Usuario user = PaginaInicial.usuarioLogado;
//        Date inicioDate = dataInicio.getDate();
//        LocalDate inicio = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(inicioDate));
//        Date fimDate = dataFim.getDate();
//        LocalDate fim = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(fimDate));
//        List<Compromisso> lista = user.compromissosIntervalo(inicio, fim);
        if(jTableCompromissos.getSelectedRow()!=-1){
            telaEditaCompromisso tela = new telaEditaCompromisso(listaIntervalo.get(jTableCompromissos.getSelectedRow()));
            tela.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"Nenhum compromisso selecionado");
        }
        
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        if(jTableCompromissos.getSelectedRow()!=-1){
            int janela = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Remover compromisso",JOptionPane.YES_NO_OPTION);    
                if(janela==JOptionPane.OK_OPTION){
                    List<Agenda> agendas = PaginaInicial.usuarioLogado.getAgendas();
                    for(int i=0; i<agendas.size(); i++){
                        if(PaginaInicial.usuarioLogado.getAgendas().get(i).removerCompromisso(listaIntervalo.get(jTableCompromissos.getSelectedRow()))){
                           JOptionPane.showMessageDialog(null, "Removido com sucesso");
                           telaGerenciarCompromissos.inicializaJTable();
                           PaginaInicial.inicializarTabela();
                        }
                    }
                }
        }else{
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
