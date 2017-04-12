
package modelo;


import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

public class Agenda{
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
    
    @Override
    public String toString() {
        return "Agenda{" + "nome=" + nome + ", compromissos=" + compromissos + '}';
    }
    

    public boolean addCompromisso(Compromisso c) throws DateTimeException{
        
        
        
        for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(c.getData()) && compromissos.get(i).getHora().equals(c.getHora())){
                System.out.println("Você já tem um compromisso nesse dia e hora");
                return false;
            }
        }
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

   
}
