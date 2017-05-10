
package com.ifpb.HiDiary.Controle;

import Excecoes.AgendasVaziasException;
import Excecoes.CompromissosException;
import com.ifpb.HiDiary.Modelo.Compromisso;
import com.ifpb.HiDiary.Modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CompromissoDaoBinario implements CompromissoDao {
    private File arquivo;
    
    public CompromissoDaoBinario(){
        arquivo = new File("compromissos.bin");
        
        if(!arquivo.exists()){
            try{
                arquivo.createNewFile();
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Falha na conexão com o arquivo","Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
    
    @Override
    public boolean create(Compromisso novo) throws ClassNotFoundException, IOException {
        List<Compromisso> compromissos = list();
        
        if(!compromissos.isEmpty()){
            for(int i=0; i<compromissos.size(); i++){
                if(compromissos.get(i).getEmailUsuario().equals(novo.getEmailUsuario())
                        && compromissos.get(i).getData().equals(novo.getData())
                        && compromissos.get(i).getHora().equals(novo.getHora())){
                    return false;
                }
            }
            
       
        }
        compromissos.add(novo);
        atualizarArquivo(compromissos);
        return true;
    }

    @Override
    public Compromisso read(String emailUsuario, String agenda, LocalDate data, LocalTime hora) throws ClassNotFoundException, IOException {
        List<Compromisso> compromissos = list();
        
        for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getEmailUsuario().equals(emailUsuario) && 
                    compromissos.get(i).getNomeAgenda().equals(agenda) &&
                        compromissos.get(i).getData().equals(data) &&
                            compromissos.get(i).getHora().equals(hora)){
               return compromissos.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Compromisso> list(String emailUsuario) throws  ClassNotFoundException, IOException{
        List<Compromisso> compromissos = list();
        List<Compromisso> compromissosUsuario = new ArrayList<>();
        if(compromissos.size()>0){
            for(int i=0; i<compromissos.size();i++){
                if(compromissos.get(i).getEmailUsuario().equals(emailUsuario)){
                    compromissosUsuario.add(compromissos.get(i));
                }
            }  
        }
        if(!compromissosUsuario.isEmpty()){
            return compromissosUsuario;
        }else{
            return new ArrayList<Compromisso>();
        }
       
        
    }


    @Override
    public List<Compromisso> list() throws ClassNotFoundException, IOException {
        List<Compromisso> compromissos = null;
        
        if(arquivo.length()>0){
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(arquivo));
            return (List<Compromisso>) input.readObject();
        }else{
            return new ArrayList<Compromisso>();
        }
    }

    @Override
    public boolean delete(Compromisso comp) throws ClassNotFoundException, IOException {
        List<Compromisso> compromissos = list();
        if(!compromissos.isEmpty()){
            for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).equals(comp)){
                compromissos.remove(compromissos.get(i));
                atualizarArquivo(compromissos);
                return true;
            }
        }
        }
        return false;
    }

    @Override
    public boolean update(Compromisso antigo, Compromisso atual) throws ClassNotFoundException, IOException {
        List<Compromisso> compromissos = list();
        if(!compromissos.isEmpty()){
            for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).equals(antigo)){
                compromissos.set(i, atual);
                atualizarArquivo(compromissos);
                return true;
            }
        }
        }
        return false;
    }

    @Override
    public List<Compromisso> compromissos30dias(String emailUsuario) throws ClassNotFoundException, IOException, CompromissosException{
        List<Compromisso> compUsuario = list(emailUsuario);
        List<Compromisso> comp30dias = new ArrayList<>();
        if(!compUsuario.isEmpty()){
            for(int i=0; i<compUsuario.size(); i++){
            if(compUsuario.get(i).getData().compareTo(LocalDate.now())>=0
                    && compUsuario.get(i).getData().compareTo(LocalDate.now().plusDays(30))<=0){
                comp30dias.add(compUsuario.get(i));
            }
            }   
        }
        if(!comp30dias.isEmpty()){
            return comp30dias;
        }else{
            throw new CompromissosException("Sem compromissos para os próximos 30 dias");
        }
        
        
    }

    @Override
    public List<Compromisso> compromissos30dias(String emailUsuario, String nomeAgenda) throws  ClassNotFoundException, IOException, CompromissosException{
        List<Compromisso> compAgenda = compAgenda(emailUsuario, nomeAgenda);
        List<Compromisso> compAgenda30dias = new ArrayList<>();
       if(!compAgenda.isEmpty()){
            for(int i=0; i<compAgenda.size(); i++){
            if(compAgenda.get(i).getData().compareTo(LocalDate.now())>=0
                    && compAgenda.get(i).getData().compareTo(LocalDate.now().plusDays(30))<=0){
                compAgenda30dias.add(compAgenda.get(i));
            }
        }
       }
       if(!compAgenda30dias.isEmpty()){
           return compAgenda30dias;
       }else{
           throw new CompromissosException("Essa agenda não tem compromissos para os próximos 30 dias");
       }
       
    }
    

    @Override
    public boolean deletaCompAgenda(String emailUsuario, String nomeAgenda) throws ClassNotFoundException, IOException {
        List<Compromisso> compromissos = list();
        if(!compromissos.isEmpty()){
            for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getEmailUsuario().equals(emailUsuario) && compromissos.get(i).getNomeAgenda().equals(nomeAgenda)){
                compromissos.remove(compromissos.get(i));
                atualizarArquivo(compromissos);
                return true;
            }
            }
        }
        return false;
    }

    @Override
    public List<Compromisso> compromissosIntervalo(String emailUsuario, LocalDate inicio, LocalDate fim) throws ClassNotFoundException, IOException, CompromissosException{
        if(inicio.isAfter(fim)) throw new DateTimeException("Intervalo inválido. A data de fim é menor que de início");
        List<Compromisso> compromissosUsuario = list(emailUsuario);
        List<Compromisso> compromissosIntervalo = new ArrayList<>();
        if(!compromissosUsuario.isEmpty()){
            for(int i=0; i<compromissosUsuario.size(); i++){
                if(compromissosUsuario.get(i).getData().compareTo(inicio)>=0
                            && compromissosUsuario.get(i).getData().compareTo(fim)<=0){
                        compromissosIntervalo.add(compromissosUsuario.get(i));
                }
            }
        }
        if(!compromissosIntervalo.isEmpty()){
            return compromissosIntervalo;
        }else{
            throw new CompromissosException("Você não tem compromissos nesse intervalo");
        }
    }

    @Override
    public List<Compromisso> compAgenda(String emailUsuario, String nomeAgenda) throws ClassNotFoundException, IOException{
        List<Compromisso> compromissosUsuario = list(emailUsuario);
        List<Compromisso> compAgenda = new ArrayList<>();
        if(!compromissosUsuario.isEmpty()){
            for(int i=0; i<compromissosUsuario.size(); i++){
            if(compromissosUsuario.get(i).getNomeAgenda().equals(nomeAgenda)){
                compAgenda.add(compromissosUsuario.get(i));
            }
            }
        }
        if(!compAgenda.isEmpty()){
            return compAgenda;
        }else{
            return new ArrayList<Compromisso>();
        }
    }
    
    private void atualizarArquivo(List<Compromisso> compromissos) throws FileNotFoundException, IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(arquivo));
        output.writeObject(compromissos);
        output.close();
    }

    @Override
    public boolean updateAgendaComp(String emailUsuario, String nomeAntigo, String nomeAtual) throws ClassNotFoundException, IOException {
        List<Compromisso> compromissos = list();
        if(!compromissos.isEmpty()){
            for(int i=0; i<compromissos.size(); i++){
                if(compromissos.get(i).getEmailUsuario().equals(emailUsuario) && 
                        compromissos.get(i).getNomeAgenda().equals(nomeAntigo)){
                            compromissos.get(i).setNomeAgenda(nomeAtual);
                    atualizarArquivo(compromissos);
                    return true;
                }
            }
        }
        return false;
    }
    
    
}
