
package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Compromisso {
    private LocalDate data;
    private LocalTime hora;
    private String descricao;
    private String local;

    public Compromisso(LocalDate data, LocalTime hora, String descricao, String local) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.local = local;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Compromisso{" + "data=" + data + ", hora=" + hora + ", descricao=" + descricao + ", local=" + local + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.data);
        hash = 43 * hash + Objects.hashCode(this.hora);
        hash = 43 * hash + Objects.hashCode(this.descricao);
        hash = 43 * hash + Objects.hashCode(this.local);
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
