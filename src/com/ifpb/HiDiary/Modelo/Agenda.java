
package com.ifpb.HiDiary.Modelo;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

    /**
     * Essa classe representa cada Agenda, tendo seu nome e sua lista de compromissos. Nela é possível
     * fazer todo o CRUD de compromissos usando lista
     * @author Lyndemberg
     * @version 1.0
     */
public class Agenda{
    private String nome;
    private List<Compromisso> compromissos;

    /**
    * Construtor da classe Agenda
    * @param nome representa o nome da agenda a ser construída
    * @author Lyndemberg
    * @version 1.0
    */
    public Agenda(String nome){
        this.nome=nome;
        compromissos = new ArrayList<>();
    }
    
    /**
    * Método para buscar o nome da Agenda
    * @return Retorna a String contendo o nome da Agenda
    * @author Lyndemberg
    * @version 1.0
    */
    public String getNome() {
        return nome;
    }
    
    /**
    * Método para modificar o nome da Agenda
    * @param nome representa o novo nome a ser aplicado na Agenda
    * @author Lyndemberg
    * @version 1.0
    */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
    * Método para imprimir uma Agenda contendo seus atributos
    * @return Retorna a String com os atributos da Agenda
    * @author Lyndemberg
    * @version 1.0
    */
    @Override
    public String toString() {
        return "Agenda{" + "nome=" + nome + ", compromissos=" + compromissos + '}';
    }
    
    /**
    * Método para adicionar um novo Compromisso a lista de compromissos da Agenda
    * @param c representa o novo compromisso a ser adicionado na lista
    * @return Retorna True se conseguiu adicionar o compromisso. Retorna False se não conseguiu.
    * @author Lyndemberg
    * @version 1.0
    */
    public boolean addCompromisso(Compromisso c) throws DateTimeException{

        for(int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(c.getData()) && compromissos.get(i).getHora().equals(c.getHora())){
                throw new DateTimeException("Você já tem um compromisso nesse dia e hora");
            }
        }
        return compromissos.add(c);
    }
    
    /**
    * Método para pegar todos os compromissos da Agenda
    * @return Retorna uma lista de compromissos
    * @author Lyndemberg
    * @version 1.0
    */
    public List<Compromisso> getCompromissos() {
        return compromissos;
    }
    
    /**
    * Método para atualizar um compromisso da Agenda
    * @param c representa o compromisso a ser atualizado. Se a Data e a Hora desse compromisso for igual a algum 
    * compromissso da lista ocorre a atualização
    * @return Retorna True se conseguiu atualizar. Retorna False se não conseguiu
    * @author Lyndemberg
    * @version 1.0
    */
    public boolean atualizarCompromisso(Compromisso c){
        for (int i=0; i<compromissos.size(); i++){
            if(compromissos.get(i).getData().equals(c.getData())  &&   compromissos.get(i).getHora().equals(c.getHora())){
                compromissos.set(i, c);
                return true;
            }
        }
        return false;
    }
    
    
    /**
    * Método para remover um compromisso da Agenda
    * @param c representa o compromisso a ser removido.
    * @return Retorna True se conseguiu remover. Retorna False se não conseguiu
    * @author Lyndemberg
    * @version 1.0
    */
    public boolean removerCompromisso(Compromisso c){
        return compromissos.remove(c);
    }

   
}
