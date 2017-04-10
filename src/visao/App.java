
package visao;

import controle.UsuarioList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import modelo.Agenda;
import modelo.Compromisso;
import modelo.Usuario;

public class App {
    
    public static void main(String[] args){
        UsuarioList cad = new UsuarioList();
        cad.create(new Usuario("Lyndemberg",LocalDate.of(1996, 05, 30),"Masculino", "lyndembergbatista@outlook.com","123"));
        cad.read("lyndembergbatista@outlook.com").criarAgenda(new Agenda("Pessoal"));
        cad.read("lyndembergbatista@outlook.com").criarAgenda(new Agenda("Faculdade"));
        cad.read("lyndembergbatista@outlook.com").criarAgenda(new Agenda("Trabalho"));
        cad.read("lyndembergbatista@outlook.com").mostrarAgenda("Pessoal").addCompromisso(new Compromisso(LocalDate.of(2017, 04, 11),
                                                                                    LocalTime.of(10, 30), "assistir","casa"));
        cad.read("lyndembergbatista@outlook.com").mostrarAgenda("Faculdade").addCompromisso(new Compromisso(LocalDate.of(2017, 04, 12),
                                                                                    LocalTime.of(10, 30), "projeto","faculdade"));
        cad.read("lyndembergbatista@outlook.com").mostrarAgenda("Pessoal").addCompromisso(new Compromisso(LocalDate.of(2017, 04, 13),
                                                                                    LocalTime.of(10, 30), "comer","casa"));
        cad.read("lyndembergbatista@outlook.com").mostrarAgenda("Trabalho").addCompromisso(new Compromisso(LocalDate.of(2017, 04, 14),
                                                                                    LocalTime.of(10, 30), "aulaInternet","InfoExatas"));
        cad.read("lyndembergbatista@outlook.com").mostrarAgenda("Trabalho").addCompromisso(new Compromisso(LocalDate.of(2017, 04, 15),
                                                                                    LocalTime.of(10, 30), "aulaInterativo","InfoExatas"));
        cad.read("lyndembergbatista@outlook.com").mostrarAgenda("Faculdade").addCompromisso(new Compromisso(LocalDate.of(2017, 04, 16),
                                                                                    LocalTime.of(10, 30), "projetoFábio2","IF"));
        System.out.println(cad.read("lyndembergbatista@outlook.com").compromissosIntervalo(LocalDate.of(2017, 04, 10),LocalDate.of(2017, 04, 17)));
    }
}
