
package com.ifpb.HiDiary.Controle;
import com.ifpb.HiDiary.Banco.ConFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Excecoes.EmailException;
import Excecoes.SenhaException;
import com.ifpb.HiDiary.Modelo.Agenda;
import com.ifpb.HiDiary.Modelo.Compromisso;
import com.ifpb.HiDiary.Modelo.Usuario;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDaoBanco implements UsuarioDao{
    
    public UsuarioDaoBanco(){
        
    }
    
    @Override
    public Usuario read(String email) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ?");
        stmt.setString(1, email);
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            Usuario usuario = new Usuario();
            try {
                usuario.setEmail(rs.getString("email"));
            } catch (org.apache.commons.mail.EmailException ex) {
                
            }
            String nascString = rs.getString("nascimento");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate nascimento = LocalDate.parse(nascString, dtf);
            usuario.setNascimento(nascimento);
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setSexo(rs.getString("sexo"));
            
            PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM compromisso WHERE emailUsuario = ?");
            stmt.setString(1, email);
            ResultSet rs2 = stmt.executeQuery();
            if(rs2.next()){
                while(rs2.next()){
                    Date dataDate = rs2.getDate(3);
                    LocalDate data = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dataDate));
                    Date hora = rs2.getTime(4);
                    LocalTime h = LocalTime.parse(new SimpleDateFormat("HH:mm").format(hora));
                    String descricao = rs2.getString("descricao");
                    String local = rs2.getString("local");
                    Compromisso novo = new Compromisso(data, h, descricao, local);
                    if(usuario.buscarAgenda(rs2.getString("nomeAgenda"))==null){
                        usuario.criarAgenda(new Agenda(rs2.getString("nomeAgenda")));
                        usuario.buscarAgenda(rs2.getString("nomeAgenda")).addCompromisso(novo);
                    }else{
                        usuario.buscarAgenda(rs2.getString("nomeAgenda")).addCompromisso(novo);
                    }
                }
            }

            con.close();
            return usuario;
        }else{
            con.close();
            return null;
        }
            
    }
    
    @Override
    public List<Usuario> list() throws SQLException, ClassNotFoundException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario");
        
        ResultSet rs = stmt.executeQuery();
        List<Usuario> usuarios = new ArrayList<>();
        if(rs.next()){
            while(rs.next()){
                usuarios.add(read(rs.getString("email")));
            }
            con.close();
            return usuarios;
        }else{
            con.close();
            return null;
        }
 
    }
    
    @Override
    public boolean create(Usuario u) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO usuario (nome, nascimento, sexo, email, senha)"+
                                                      "VALUES (?,?,?,?,?)");
        stmt.setString(1,u.getEmail());
        Date date = java.sql.Date.valueOf(u.getNascimento());
        stmt.setDate(2, (java.sql.Date) date);
        
        stmt.setString(3, u.getSexo());
        stmt.setString(4, u.getEmail());
        stmt.setString(5, u.getSenha());
       
        boolean retorno = stmt.executeUpdate()>0;
        con.close();
        return retorno;
    }
    
    @Override
    public boolean delete(String email) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM usuario WHERE email = ?");
        stmt.setString(1, email);
        
        boolean retorno =  stmt.executeUpdate()>0;
        con.close();
        return retorno;
        
    }
    
    //IDEIA DE FAZER A LÓGICA DE ARQUIVO, MANDAR TODO O USUÁRIO COM SUAS AGENDAS E COMPROMISSOS
    @Override
    public boolean update(Usuario u) throws ClassNotFoundException, SQLException{
        if(delete(u.getEmail()) && create(u)){
            
            int cont=0;
            int cont2=0;
            for(int i=0; i<u.getAgendas().size(); i++){
                for(int k=0; k<u.getAgendas().get(i).getCompromissos().size(); k++){
                    cont++;
                    if(setNovoCompromisso(u.getEmail(),u.getAgendas().get(i).getNome(),u.getAgendas().
                            get(i).getCompromissos().get(k))){
                        cont2++;
                    }

                }  
            }
            if(cont==cont2) return true;
        }

       return false;
    }
    
    
    private boolean setNovoCompromisso(String email, String agenda, Compromisso c) throws SQLException, ClassNotFoundException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE compromisso SET(emailUsuario, nomeAgenda, data, hora, descricao,local)"+
                                                      "= (?,?,?,?,?,?) WHERE email = ?");

        stmt.setString(1, email);
        con.close();
        boolean retorno = stmt.executeUpdate()>0;
        return retorno;
    }
   
}
