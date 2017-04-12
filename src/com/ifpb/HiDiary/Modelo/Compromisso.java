
package com.ifpb.HiDiary.Modelo;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

    /**
     * Essa classe representa cada Compromisso
     * @author Lyndemberg
     * @version 1.0
     */
public class Compromisso{
    private LocalDate data;
    private LocalTime hora;
    private String descricao;
    private String local;
    
    /**
    * Construtor da classe Compromisso
    * @param data representa a data do compromisso
    * @param hora representa a hora do compromisso
    * @param descricao representa a descrição do compromisso
    * @param local representa o lugar onde o compromisso será realizado
    * @throws DateTimeException Caso a data do compromisso informada seja inválida ou seja antes da data atual
    * @author Lyndemberg
    * @version 1.0
    */
    
    public Compromisso(LocalDate data, LocalTime hora, String descricao, String local) throws DateTimeException{
        if(data.isBefore(LocalDate.now())) throw new DateTimeException("Data anterior a hoje");  
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.local = local;
        
        
    }
    
    /**
    * Método para buscar a data de um Compromisso
    * @return Retorna a data do compromisso
    * @author Lyndemberg
    * @version 1.0
    */
    public LocalDate getData() {
        return data;
    }

    /**
    * Método para modificar a data do Compromisso
    * @param data representa a nova data a ser aplicada ao compromisso
    * @throws DateTimeException Caso a data informada seja anterior que a data atual
    * @author Lyndemberg
    * @version 1.0
    */
    public void setData(LocalDate data) throws DateTimeException {
        if(data.isBefore(LocalDate.now())) throw new DateTimeException("Data anterior a hoje");
        this.data = data;
    }

    /**
    * Método para buscar a hora do Compromisso
    * @return Retorna a hora do compromisso
    * @author Lyndemberg
    * @version 1.0
    */
    public LocalTime getHora() {
        return hora;
    }
    
    /**
    * Método para modificar a hora do Compromisso
    * @param hora representa a nova hora a ser aplicada ao Compromisso
    * @author Lyndemberg
    * @version 1.0
    */
    public void setHora(LocalTime hora){
        this.hora = hora;
    }
    
    /**
    * Método para buscar a descrição do Compromisso
    * @return Retorna a String da descrição do Compromisso
    * @author Lyndemberg
    * @version 1.0
    */
    public String getDescricao() {
        return descricao;
    }

    /**
    * Método para modificar a descrição do Compromisso
    * @param descricao representa a nova descrição a ser aplicada ao Compromisso
    * @author Lyndemberg
    * @version 1.0
    */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    /**
    * Método para buscar o local do Compromisso
    * @return Retorna a String do local do Compromisso
    * @author Lyndemberg
    * @version 1.0
    */
    public String getLocal() {
        return local;
    }

    /**
    * Método para modificar o local do Compromisso
    * @param local representa o novo local a ser aplicada ao Compromisso
    * @author Lyndemberg
    * @version 1.0
    */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
    * Método imprimir o Compromisso com seus atributos
    * @return Retorna a String com as informações do Compromisso
    * @author Lyndemberg
    * @version 1.0
    */
    @Override
    public String toString() {
        return "Compromisso{" + "data=" + data + ", hora=" + hora + ", descricao=" + descricao + ", local=" + local + '}';
    }

    /**
    * Método para calcular o hashCode de Compromisso
    * @return Retorna o número inteiro contendo o código hash de Compromisso
    * @author Lyndemberg
    * @version 1.0
    */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.data);
        hash = 43 * hash + Objects.hashCode(this.hora);
        hash = 43 * hash + Objects.hashCode(this.descricao);
        hash = 43 * hash + Objects.hashCode(this.local);
        return hash;
    }

    /**
    * Método para comparar Compromisso com outro objeto
    * @return Retorna True se os objetos forem iguais. Retorna False se não forem
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
        final Compromisso other = (Compromisso) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        return true;
    }
    
    
    
}
