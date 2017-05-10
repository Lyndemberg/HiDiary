
package com.ifpb.HiDiary.Controle;

import Excecoes.AgendaInvalidaException;
import Excecoes.AgendasVaziasException;
import com.ifpb.HiDiary.Banco.ConFactory;
import com.ifpb.HiDiary.Modelo.Agenda;
import com.ifpb.HiDiary.Modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendaDaoBanco implements AgendaDao{
    public AgendaDaoBanco(){
        
    }
    @Override
    public boolean create(Agenda nova) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO agenda (emailUsuario, nome)"+
                                                      "VALUES (?,?)");        
        stmt.setString(1, nova.getEmailUsuario());
        stmt.setString(2, nova.getNome());
        boolean retorno = stmt.executeUpdate()>0;
        con.close();
        return retorno;
    }

    @Override
    public Agenda read(String emailUsuario, String nome) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM agenda WHERE emailUsuario=? AND nome=?");
        stmt.setString(1, emailUsuario);
        stmt.setString(2, nome);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            Agenda agenda = new Agenda(emailUsuario,nome);
            con.close();
            return agenda;
        }else{
            con.close();
            return null;
        }
    }

    @Override
    public List<Agenda> list() throws SQLException, ClassNotFoundException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM agenda");
        
        ResultSet rs = stmt.executeQuery();
        List<Agenda> agendas = new ArrayList<>();
        while(rs.next()){
            Agenda agenda = new Agenda(rs.getString("emailUsuario"),rs.getString("nome"));
            agendas.add(agenda);    
        }
        con.close();
        return agendas;
    }

    @Override
    public List<Agenda> list(String emailUsuario) throws SQLException, ClassNotFoundException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM agenda WHERE emailUsuario=?");
        stmt.setString(1, emailUsuario);
        ResultSet rs = stmt.executeQuery();
        List<Agenda> agendasUsuario = new ArrayList<>();
        while(rs.next()){
            Agenda agenda = new Agenda(rs.getString("emailUsuario"),rs.getString("nome"));
            agendasUsuario.add(agenda);
        }
        con.close();
        if(agendasUsuario.isEmpty()) throw new AgendasVaziasException("Você ainda não tem agendas");
        return agendasUsuario; 
    }

    @Override
    public List<String> listNomesAgendas(String emailUsuario) throws SQLException, ClassNotFoundException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT nome FROM agenda WHERE emailUsuario=?");
        stmt.setString(1, emailUsuario);
        ResultSet rs = stmt.executeQuery();
        List<String> nomesAgendasUsuario= new ArrayList<>();
        while(rs.next()){
            nomesAgendasUsuario.add(rs.getString("nome"));
        }
        con.close();
        if(nomesAgendasUsuario.isEmpty()) throw new AgendasVaziasException("Você ainda não tem agendas");
        return nomesAgendasUsuario;
    }

    @Override
    public boolean delete(String emailUsuario, String nome) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM agenda WHERE emailUsuario=? AND nome=?");
        stmt.setString(1, emailUsuario);
        stmt.setString(2, nome);
        boolean retorno =  stmt.executeUpdate()>0;
        con.close();
        return retorno;
    }

    @Override
    public boolean update(String emailUsuario, String nomeAntigo, String nomeAtual) throws ClassNotFoundException, SQLException{
        if(nomeAntigo.equals(nomeAtual)) throw new AgendaInvalidaException("A agenda já está com esse nome");
        if(nomeAtual==null) throw new AgendaInvalidaException("O nome da agenda não pode ser vazio");
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM agenda WHERE emailUsuario=? AND nome=?");
        stmt.setString(1, emailUsuario);
        stmt.setString(2, nomeAtual);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            con.close();
            throw new AgendaInvalidaException("Você já tem uma agenda com esse nome");
        }else{
            PreparedStatement stmt2 = con.prepareStatement("UPDATE agenda SET(nome) = (?) WHERE emailUsuario=? AND nome=?");
            stmt2.setString(1, nomeAtual);
            stmt2.setString(2, emailUsuario);
            stmt2.setString(3, nomeAntigo);
            boolean retorno = stmt2.executeUpdate()>0;
            con.close();
            return retorno;
        }
    }
    
}
