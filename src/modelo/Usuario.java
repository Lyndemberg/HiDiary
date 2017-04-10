
package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario{
    private String nome;
    private LocalDate nascimento;
    private String sexo;
    private String email;
    private String senha;
    private List<Agenda> agendas;

    public Usuario(String nome, LocalDate nascimento, String sexo, String email, String senha) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        agendas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }   

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Agenda> getAgendas() {
        return agendas;
    } 
    
    public List<Compromisso> compromissosIntervalo(LocalDate inicio, LocalDate fim){
        
        List lista = new ArrayList<>();
        for(int i=0; i<agendas.size(); i++){
            for(int k=0; k<agendas.get(i).getCompromissos().size(); k++){
                if((agendas.get(i).getCompromissos().get(k).getData().compareTo(inicio)==0
                        || agendas.get(i).getCompromissos().get(k).getData().compareTo(inicio)>0)
                            && (agendas.get(i).getCompromissos().get(k).getData().compareTo(fim)==0
                                || agendas.get(i).getCompromissos().get(k).getData().compareTo(fim)<0)){
                    lista.add(agendas.get(i).getCompromissos().get(k));
                }
            }
        }
        if(lista.isEmpty()){
            System.out.println("Não tem compromissos nesse intervalo");
            return null;
        }else{
            return lista;
        }
        
    }
        
    
    
    public List getNomesAgendas(){
        List listaNomes = new ArrayList();
        for (int i=0; i<agendas.size(); i++){
            listaNomes.add(agendas.get(i).getNome());
        }
        return listaNomes;
    }
    
    
    public boolean criarAgenda(Agenda a) {
        return agendas.add(a);
    }

    public Agenda mostrarAgenda(String nome) {
        for (int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(nome)) return agendas.get(i);
        }
        return null;
    }
    
    public boolean atualizarAgenda(Agenda a) {
        for (int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(a.getNome())){
                agendas.set(i, a);
                return true;
            }    
        }
        return false;
    }

    public boolean removerAgenda(String nome) {
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(nome)){
                agendas.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean autentica(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }
   
    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", nascimento=" + nascimento + ", sexo=" + sexo + ", email=" + email + ", senha=" + senha + ", agendas=" + agendas + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.nascimento);
        hash = 53 * hash + Objects.hashCode(this.sexo);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.senha);
        hash = 53 * hash + Objects.hashCode(this.agendas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.nascimento, other.nascimento)) {
            return false;
        }
        if (!Objects.equals(this.agendas, other.agendas)) {
            return false;
        }
        return true;
    }

    
    
    
    
    

}
