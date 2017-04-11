
package modelo;

import controle.AgendaInvalidaException;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private String nome;
    private List<Compromisso> compromissos;

    public Agenda(String nome){
        this.nome=nome;
        compromissos = new ArrayList<>();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public boolean addCompromisso(Compromisso c){
        return compromissos.add(c);
    }
    
    public List<Compromisso> getCompromissos() {
        return compromissos;
    }
    
    public boolean atualizarCompromisso(Compromisso c){
        for (int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(c.getData())  &&   compromissos.get(i).getHora().equals(c.getHora())){
                compromissos.set(i, c);
                return true;
            }
        }
        return false;
    }
    
    public boolean removerCompromisso(Compromisso c){
        return compromissos.remove(c);
    }

    @Override
    public String toString() {
        return "Agenda{" + "nome=" + nome + ", compromissos=" + compromissos + '}';
    }
    
    
   
}
