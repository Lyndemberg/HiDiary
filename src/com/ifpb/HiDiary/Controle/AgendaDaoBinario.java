
package com.ifpb.HiDiary.Controle;

import Excecoes.AgendaInvalidaException;
import Excecoes.AgendasVaziasException;
import com.ifpb.HiDiary.Modelo.Agenda;
import com.ifpb.HiDiary.Modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AgendaDaoBinario implements AgendaDao{
    private File arquivo;
    
    public AgendaDaoBinario(){
        arquivo = new File("agendas.bin");
        
        if(!arquivo.exists()){
            try{
                arquivo.createNewFile();
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Falha na conexão com o arquivo","Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    @Override
    public boolean create(Agenda nova) throws ClassNotFoundException, IOException {
        List<Agenda> agendas = list();
        
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getEmailUsuario().equals(nova.getEmailUsuario()) && agendas.get(i).getNome().equals(nova.getNome())){
                return false;
            }
        }
        agendas.add(nova);
        atualizarArquivo(agendas);
        return true;
    }

    @Override
    public Agenda read(String emailUsuario, String nome) throws ClassNotFoundException, IOException {
        List<Agenda> agendas = list();
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getEmailUsuario().equals(emailUsuario) && agendas.get(i).getNome().equals(nome)){
                return agendas.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Agenda> list(String emailUsuario) throws ClassNotFoundException, IOException {
        List<Agenda> agendas = list();
        List<Agenda> agendasUsuario = new ArrayList<>();
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getEmailUsuario().equals(emailUsuario)){
                agendasUsuario.add(agendas.get(i));
            }
        }
        if(!agendasUsuario.isEmpty()) return agendasUsuario;
        return null;
    }

    @Override
    public boolean delete(String emailUsuario, String nome) throws ClassNotFoundException, IOException {
        List<Agenda> agendas = list();
        
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getEmailUsuario().equals(emailUsuario) && agendas.get(i).getNome().equals(nome)){
                agendas.remove(agendas.get(i));
                atualizarArquivo(agendas);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(String emailUsuario, String nomeAntigo, String nomeAtual) throws ClassNotFoundException, IOException {
        List<Agenda> agendasUsuario = list(emailUsuario);
        
        for(int i=0; i<agendasUsuario.size(); i++){
            if(agendasUsuario.get(i).getNome().equals(nomeAntigo)){
                agendasUsuario.get(i).setNome(nomeAtual);
                atualizarArquivo(agendasUsuario);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Agenda> list() throws ClassNotFoundException, IOException {
        List<Agenda> agenda = new ArrayList<>();
        
        if(arquivo.length()>0){
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(arquivo));
            return (List<Agenda>) input.readObject();
        }else{
            return new ArrayList<Agenda>();
        }
    }
    
    private void atualizarArquivo(List<Agenda> agendas) throws FileNotFoundException, IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(arquivo));
        output.writeObject(agendas);
        output.close();
    }

    @Override
    public List<String> listNomesAgendas(String emailUsuario) throws SQLException, ClassNotFoundException, IOException {
        List<Agenda> agendas = list(emailUsuario);
        List<String> nomesAgendas = new ArrayList<>();
        for(int i=0; i<agendas.size(); i++){
            nomesAgendas.add(agendas.get(i).getNome());
        }
        if(!nomesAgendas.isEmpty()) return nomesAgendas;
        return null;
    }
    
}
