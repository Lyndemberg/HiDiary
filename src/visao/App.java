
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
        Scanner scanner = new Scanner(System.in);
        int op;
        do{
            System.out.println("-----MENU-----");
            System.out.println("1- LOGIN");
            System.out.println("2- CADASTRAR NOVO USUÁRIO");
            System.out.println("0- SAIR DA APLICAÇÃO");
            op = scanner.nextInt();
            switch(op){
                case 1: 
                    System.out.println("Email: ");
                    String email = scanner.next();
                    System.out.println("Senha: ");
                    String senha = scanner.next();
                    if(cad.read(email).autentica(email, senha)){
                        int homeOp;   
                        do{
                            System.out.println("------PÁGINA INICIAL------");
                            System.out.println("1- Novo compromisso");
                            System.out.println("2- Gerenciar agendas");
                            System.out.println("3- Gerenciar compromissos");
                            System.out.println("0- SAIR DA CONTA");
                            homeOp = scanner.nextInt();
                            if(homeOp == 1){
                                System.out.println("Data:");
                                String dataString = scanner.next();
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate data = LocalDate.parse(dataString, dtf);
                                System.out.println("Hora:");
                                String horaString = scanner.next();
                                DateTimeFormatter dtfHora = DateTimeFormatter.ISO_TIME;
                                LocalTime hora = LocalTime.parse(horaString, dtfHora);
                                System.out.println("Descrição:");
                                String descricao = scanner.next();
                                System.out.println("Local:");
                                String local = scanner.next();
                                System.out.println(cad.read(email).getNomesAgendas());
                                System.out.println("Agenda:");
                                String agenda = scanner.next();
                                if(cad.read(email).mostrarAgenda(agenda).addCompromisso(new Compromisso(data,hora,descricao,local))){
                                    System.out.println("Compromisso cadastrado com sucesso!");
                                }else System.out.println("Erro ao cadastrar compromisso!");
                            }
                            if(homeOp == 2){
                                System.out.println("Agendas disponiveis:");
                                System.out.println(cad.read(email).getNomesAgendas());
                                System.out.println("1- Criar nova agenda");
                                System.out.println("2- Alterar agenda");
                                System.out.println("3- Excluir agenda");
                                int opGerenciar = scanner.nextInt();
                                if(opGerenciar == 1){
                                    System.out.println("Digite o nome da nova agenda:");
                                    String nomeAgenda = scanner.next();
                                    if(cad.read(email).criarAgenda(new Agenda(nomeAgenda))) 
                                        System.out.println("Agenda criada com sucesso!");
                                                else System.out.println("Erro ao criar a agenda!");  
                                }
                                if(opGerenciar == 2){
                                    System.out.println("Qual deseja alterar?");
                                    System.out.println(cad.read(email).getNomesAgendas());
                                    String alterarAgenda = scanner.next();
                                    System.out.println(cad.read(email).mostrarAgenda(alterarAgenda));
                                    System.out.println("1- Alterar nome da agenda");
                                    System.out.println("2- Alterar dados de um compromisso");
                                    int opAlterarAgenda = scanner.nextInt(); 
                                    if(opAlterarAgenda == 1){
                                        System.out.println("Digite o novo nome: ");
                                        String novoNome = scanner.next();
                                        cad.read(email).mostrarAgenda(alterarAgenda).setNome(novoNome);
                                        System.out.println("Nome da agenda atualizado!");
                                        
                                    }
                                    if(opAlterarAgenda == 2){
                                        System.out.println("Compromissos dessa agenda:");
                                        System.out.println(cad.read(email).mostrarAgenda(alterarAgenda).getCompromissos());
                                        System.out.println("NOVOS DADOS");

                                        System.out.println("Digite a data do compromisso:");
                                        String dataString = scanner.next();
                                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                        LocalDate data = LocalDate.parse(dataString, dtf);
                                        System.out.println("Digite a hora do compromisso:");
                                        String horaString = scanner.next();
                                        DateTimeFormatter dtfHora = DateTimeFormatter.ISO_TIME;
                                        LocalTime hora = LocalTime.parse(horaString, dtfHora);
                                        System.out.println("Nova descrição:");
                                        String descricao = scanner.next();
                                        System.out.println("Novo local:");
                                        String local = scanner.next();
                      
                                        if(cad.read(email).mostrarAgenda(alterarAgenda).atualizarCompromisso(new Compromisso(data,hora,descricao,local))){
                                            System.out.println("Compromisso atualizado com sucesso!");
                                        }else System.out.println("Erro ao atualizar compromisso!");

                                    }
                                    if(opAlterarAgenda == 3){
                                        System.out.println(cad.read(email).getNomesAgendas());
                                        System.out.println("Qual deseja excluir? ");
                                        String agendaExcluir = scanner.next();
                                        if(cad.read(email).removerAgenda(agendaExcluir)){
                                            System.out.println("Agenda removida com sucesso!");
                                        }else{
                                            System.out.println("Erro ao remover a agenda!");   
                                        }
                                    }
                                }

                            }
                            if(homeOp == 3){
                                System.out.println("TODOS OS COMPROMISSOS");
                                System.out.println(cad.read(email).getAgendas());
                                System.out.println("1- Excluir compromisso");
                                System.out.println("2- Atualizar compromisso");
                                int opCompromissos = scanner.nextInt();
                                if(opCompromissos == 1){
                                    System.out.println("Qual a data do compromisso:");
                                    String dataString = scanner.next();
                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    LocalDate data = LocalDate.parse(dataString, dtf);
                                    System.out.println("Qual a hora do compromisso:");
                                    String horaString = scanner.next();
                                    DateTimeFormatter dtfHora = DateTimeFormatter.ISO_TIME;
                                    LocalTime hora = LocalTime.parse(horaString, dtfHora);
                                    System.out.println("Descrição:");
                                    String descricao = scanner.next();
                                    System.out.println("Local:");
                                    String local = scanner.next();
                                    System.out.println("Qual a agenda:");
                                    String agenda = scanner.next();
                                    if(cad.read(email).mostrarAgenda(agenda).removerCompromisso(new Compromisso(data,hora,descricao,local))){
                                        System.out.println("Compromisso removido com sucesso!");
                                    }else{
                                        System.out.println("Erro ao remover esse compromisso");
                                    }
                                }
                            }
                        }while(homeOp != 0); 

            }
                    case 2:
                        System.out.println("Nome: ");
                        String nome = scanner.next();
                        System.out.println("Nascimento: ");
                        String dataString = scanner.next();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate nascimento = LocalDate.parse(dataString, dtf);
                        System.out.println("Sexo: ");
                        String sexo = scanner.next();
                        System.out.println("Email: ");
                        String emailNovo = scanner.next();
                        System.out.println("Senha: ");
                        String senhaNovo = scanner.next();
                        if(cad.create(new Usuario(nome, nascimento, sexo, emailNovo, senhaNovo))){
                            System.out.println("Usuário cadastrado com sucesso!");
                        }else{
                            System.out.println("Erro ao cadastrar usuário");
                        }
                    break;
    }
            
  }while(op != 0);
        
    
}
}
