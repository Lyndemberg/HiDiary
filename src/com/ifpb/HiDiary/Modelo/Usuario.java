
package com.ifpb.HiDiary.Modelo;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

    /**
     * Essa classe representa cada Usuario
     * @author Lyndemberg
     * @version 1.0
     */
public class Usuario{
    private String nome;
    private LocalDate nascimento;
    private String sexo;
    private String email;
    private String senha;
    private List<Agenda> agendas;
    

    /**
    * Construtor da classe Usuario
    * @param nome representa o nome do Usuario
    * @param nascimento representa a data de nascimento do Usuario
    * @param sexo representa o sexo do Usuario
    * @param email representa o email do Usuario
    * @param senha representa a senha do Usuario
    * @exception Lança exceção DateTimeException informar uma data inválida para o seu nascimento
    * @author Lyndemberg
    * @version 1.0
    */
    public Usuario(String nome, LocalDate nascimento, String sexo, String email, String senha) throws DateTimeException{
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        agendas = new ArrayList<>();
    }

    /**
    * Método para buscar o nome do Usuario
    * @return Retorna a String com o nome do Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    public String getNome() {
        return nome;
    }

    /**
    * Método para modificar o nome do Usuario
    * @param nome representa o novo nome a ser aplicado no Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
    * Método para buscar a data de nascimento do Usuario
    * @return Retorna a data de nascimento do Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    public LocalDate getNascimento() {
        return nascimento;
    }

    /**
    * Método para modificar a data de nascimento do Usuario
    * @param nascimento representa a nova data de nascimento a ser aplicada no Usuario
    * @exception Lança exceção do tipo DateTimeException se informar uma data de nascimento inválida
    * @author Lyndemberg
    * @version 1.0
    */
    public void setNascimento(LocalDate nascimento) throws DateTimeException{
        this.nascimento = nascimento;
    }

    /**
    * Método para buscar o sexo do Usuario
    * @return Retorna a String do sexo do Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    public String getSexo() {
        return sexo;
    }
    /**
    * Método para modificar o sexo do Usuario
    * @param sexo representa o novo sexo a ser aplicado no Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
    * Método para buscar o email do Usuario
    * @return Retorna a String do email do Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    public String getEmail() {
        return email;
    }
    
    /**
    * Método para modificar o email do Usuario
    * @param email representa o novo email a ser aplicado no Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    public void setEmail(String email) {
        this.email = email;
    }   

    /**
    * Método para modificar a senha do Usuario
    * @param senha representa a nova senha a ser aplicada ao Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    /**
    * Método para autenticar o Usuario
    * @param email representa o email a ser verificado
    * @param senha representa a senha a ser verificada
    * @return Retorna True se a autenticação for validada. Retorna False se não for validada
    * @author Lyndemberg
    * @version 1.0
    */
    public boolean autenticar(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }

    /**
    * Método para buscar todas as agendas do Usuario
    * @return Retorna a lista de agendas do Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    public List<Agenda> getAgendas() {
        return agendas;
    } 
    
    /**
    * Método para buscar apenas os nomes de todas as agendas do Usuario
    * @return Retorna a lista de nomes das agendas do Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    public List getNomesAgendas(){
        List listaNomes = new ArrayList(    );
        for (int i=0; i<agendas.size(); i++){
            listaNomes.add(agendas.get(i).getNome());
        }
        return listaNomes;
    }
    
    /**
    * Método para criar uma nova agenda para o Usuario
    * @param a representa a agenda a ser criada
    * @return Retorna True se a criação acontecer. Retorna False se a criação não acontecer
    * @author Lyndemberg
    * @version 1.0
    */
    public boolean criarAgenda(Agenda a){
        if(buscarAgenda(a.getNome()) != null){
            System.out.println("Já existe uma agenda com esse nome!");
            return false;
        }else{
            return agendas.add(a);
        }
        
    }

    /**
    * Método para buscar uma agenda do Usuario
    * @param nome representa o nome da agenda a ser buscada
    * @return Retorna a Agenda se encontrar, caso contrário retornará Null
    * @author Lyndemberg
    * @version 1.0
    */
    public Agenda buscarAgenda(String nome) {
        for (int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(nome)) return agendas.get(i);
        }
        return null;
    }
    
    /**
    * Método para atualizar uma agenda do Usuario
    * @param a representa a agenda a ser atualizada
    * @return Retorna True se atualizar ou False se não atualizar
    * @author Lyndemberg
    * @version 1.0
    */
    public boolean atualizarAgenda(Agenda a) {
        for (int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(a.getNome())){
                agendas.set(i, a);
                return true;
            }    
        }
        return false;
    }

    /**
    * Método para remover uma agenda do Usuario
    * @param nome representa o nome da agenda a ser removida
    * @return Retorna True se remover a agenda, ou False se não remover
    * @author Lyndemberg
    * @version 1.0
    */
    public boolean removerAgenda(String nome) {
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNome().equals(nome)){
                agendas.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
    * Método para imprimir o Usuario com todos os seus atributos
    * @return Retorna a String com os atributos do Usuario
    * @author Lyndemberg
    * @version 1.0
    */
    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", nascimento=" + nascimento + ", sexo=" + sexo + ", email=" + email + ", senha=" + senha + ", agendas=" + agendas + '}';
    }

    /**
    * Método para calcular o hashCode de Usuario
    * @return Retorna um número inteiro com o código hash do Usuario
    * @author Lyndemberg
    * @version 1.0
    */
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

    /**
    * Método para comparar o Usuario com outro objeto
    * @param obj representa o objeto a ser comparado com o Usuario
    * @return Retorna True se os objetos forem iguais ou False se não forem
    * @author Lyndemberg
    * @version 1.0
    */
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
    
    /**
    * Método para listar todos os compromissos do Usuario em todas as suas agendas dentro de um intervalo de datas
    * @param inicio representa a data de inicio do intervalo a ser estabelecido
    * @param fim representa a data de fim do intervalo a ser estabelecido
    * @return Retorna a lista de compromissos que estão no intervalo ou retorna Null se não tiver compromissos nesse
    * intervalo
    * @author Lyndemberg
    * @version 1.0
    */
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
            System.out.println("Não existem compromissos nesses dias");
            return null;
        }else{
            return lista;
        }
    }
    
    
    /**
    * Método para listar todos os compromissos que o Usuario tem em todas as suas agendas no intervalo de 30 dias
    * a partir da data atual
    * @return Retorna a lista de compromissos dos próximos 30 dias ou retorna Null se não tiver compromissos para os
    * próximos 30 dias
    * @author Lyndemberg
    * @version 1.0
    */
    public List<Compromisso> compromissosTrintaDias(){
       return compromissosIntervalo(LocalDate.now(),LocalDate.now().plusDays(30));
    }

}
