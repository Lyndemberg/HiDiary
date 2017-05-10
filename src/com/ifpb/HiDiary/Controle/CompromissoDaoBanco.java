
package com.ifpb.HiDiary.Controle;

import Excecoes.AgendasVaziasException;
import Excecoes.CompromissosException;
import com.ifpb.HiDiary.Banco.ConFactory;
import com.ifpb.HiDiary.Modelo.Agenda;
import com.ifpb.HiDiary.Modelo.Compromisso;
import com.ifpb.HiDiary.Modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompromissoDaoBanco implements CompromissoDao{
    public CompromissoDaoBanco(){
        
    }
    @Override
    public boolean create(Compromisso novo) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO compromisso (emailUsuario, nomeAgenda, data, hora, descricao, local)"+
                                                      "VALUES (?,?,?,?,?,?)");
        stmt.setString(1, novo.getEmailUsuario());
        stmt.setString(2, novo.getNomeAgenda());
        stmt.setDate(3, java.sql.Date.valueOf(novo.getData()));
        stmt.setTime(4, java.sql.Time.valueOf(novo.getHora()));
        stmt.setString(5, novo.getDescricao());
        stmt.setString(6, novo.getLocal());
        boolean retorno = stmt.executeUpdate()>0;
        con.close();
        return retorno;
    }

    @Override
    public Compromisso read(String emailUsuario, String agenda, LocalDate data, LocalTime hora) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * compromisso WHERE emailUsuario=? AND nomeAgenda=? AND data=? AND hora=?");
        stmt.setString(1, emailUsuario);
        stmt.setString(2, agenda);
        stmt.setDate(3, java.sql.Date.valueOf(data));
        stmt.setTime(4, java.sql.Time.valueOf(hora));
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            Compromisso comp = new Compromisso();
            comp.setEmailUsuario(rs.getString("emailUsuario"));
            comp.setNomeAgenda(rs.getString("nomeAgenda"));
            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getTime("hora").toLocalTime());
            comp.setDescricao(rs.getString("descricao"));
            comp.setLocal(rs.getString("local"));
            con.close();
            return comp;
        }else{
            con.close();
            return null;
        }
    }

    @Override
    public List<Compromisso> list(String emailUsuario) throws SQLException, ClassNotFoundException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM compromisso WHERE emailUsuario=?");
        stmt.setString(1, emailUsuario);
        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compUsuario = new ArrayList<>();
        while(rs.next()){
            Compromisso comp = new Compromisso();
            comp.setEmailUsuario(rs.getString("emailUsuario"));
            comp.setNomeAgenda(rs.getString("nomeAgenda"));
            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getTime("hora").toLocalTime());
            comp.setDescricao(rs.getString("descricao"));
            comp.setLocal(rs.getString("local"));
            compUsuario.add(comp);
        }
        con.close();
        return compUsuario;
    }

    @Override
    public List<Compromisso> list() throws SQLException, ClassNotFoundException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM compromissos");
        
        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compromissos = new ArrayList<>();
        while(rs.next()){
            Compromisso comp = new Compromisso();
            comp.setEmailUsuario(rs.getString("emailUsuario"));
            comp.setNomeAgenda(rs.getString("nomeAgenda"));
            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getTime("hora").toLocalTime());
            comp.setDescricao(rs.getString("descricao"));
            comp.setLocal(rs.getString("local"));
            compromissos.add(comp);
        }
        con.close();
        return compromissos;
    }

    @Override
    public boolean delete(Compromisso comp) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM compromisso WHERE emailUsuario = ? AND data=? AND hora=?");
        stmt.setString(1, comp.getEmailUsuario());
        stmt.setDate(2, java.sql.Date.valueOf(comp.getData()));
        stmt.setTime(3, java.sql.Time.valueOf(comp.getHora()));
        boolean retorno =  stmt.executeUpdate()>0;
        con.close();
        return retorno;
    }

    @Override
    public boolean update(Compromisso antigo, Compromisso atual) throws ClassNotFoundException, SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Compromisso> compromissosIntervalo(String emailUsuario, LocalDate inicio, LocalDate fim) throws ClassNotFoundException, SQLException{
        if(inicio.isAfter(fim)) throw new DateTimeException("Intervalo inválido. A data de fim é menor que de início");
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM compromisso WHERE emailUsuario=? AND data>=? AND data<=?");
        stmt.setString(1, emailUsuario);
        stmt.setDate(2, java.sql.Date.valueOf(inicio));
        stmt.setDate(3, java.sql.Date.valueOf(fim));
        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compIntervalo = new ArrayList<>();
        while(rs.next()){
            Compromisso comp = new Compromisso();
            comp.setEmailUsuario(rs.getString("emailUsuario"));
            comp.setNomeAgenda(rs.getString("nomeAgenda"));
            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getTime("hora").toLocalTime());
            comp.setDescricao(rs.getString("descricao"));
            comp.setLocal(rs.getString("local"));
            compIntervalo.add(comp);
        }
        con.close();
        if(!compIntervalo.isEmpty()){
            return compIntervalo;
        }else{
            throw new CompromissosException("Você não tem compromissos nesse intervalo");
        }
    }

    @Override
    public List<Compromisso> compromissos30dias(String emailUsuario) throws SQLException, ClassNotFoundException{
        LocalDate inicio = LocalDate.now();
        LocalDate fim = LocalDate.now().plusDays(30);
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM compromisso WHERE emailUsuario=? AND data>=? AND data<=?");
        stmt.setString(1, emailUsuario);
        stmt.setDate(2, java.sql.Date.valueOf(inicio));
        stmt.setDate(3, java.sql.Date.valueOf(fim));
        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compUsuario = new ArrayList<>();
        while(rs.next()){
            Compromisso comp = new Compromisso();
            comp.setEmailUsuario(rs.getString("emailUsuario"));
            comp.setNomeAgenda(rs.getString("nomeAgenda"));
            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getTime("hora").toLocalTime());
            comp.setDescricao(rs.getString("descricao"));
            comp.setLocal(rs.getString("local"));
            compUsuario.add(comp);
        }
        con.close();
        if(!compUsuario.isEmpty()){
            return compUsuario;
        }else{
            throw new CompromissosException("Sem compromissos para os próximos 30 dias");
        }
    }

    @Override
    public List<Compromisso> compromissos30dias(String emailUsuario, String nomeAgenda) throws SQLException, ClassNotFoundException{
        LocalDate inicio = LocalDate.now();
        LocalDate fim = LocalDate.now().plusDays(30);
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM compromisso WHERE emailUsuario=? AND nomeAgenda=? AND data>=? AND data<=?");
        stmt.setString(1, emailUsuario);
        stmt.setString(2, nomeAgenda);
        stmt.setDate(3, java.sql.Date.valueOf(inicio));
        stmt.setDate(4, java.sql.Date.valueOf(fim));
        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compAgenda30dias = new ArrayList<>();
        while(rs.next()){
            Compromisso comp = new Compromisso();
            comp.setEmailUsuario(rs.getString("emailUsuario"));
            comp.setNomeAgenda(rs.getString("nomeAgenda"));
            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getTime("hora").toLocalTime());
            comp.setDescricao(rs.getString("descricao"));
            comp.setLocal(rs.getString("local"));
            compAgenda30dias.add(comp);
        }
        con.close();
        if(!compAgenda30dias.isEmpty()){
            return compAgenda30dias;
        }else{
            throw new CompromissosException("Essa agenda não tem compromissos para os próximos 30 dias");
        }
    }

    @Override
    public boolean deletaCompAgenda(String emailUsuario, String nomeAgenda) throws SQLException, ClassNotFoundException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM compromisso WHERE emailUsuario=? AND nomeAgenda=?");
        stmt.setString(1, emailUsuario);
        stmt.setString(2, nomeAgenda);
        boolean retorno =  stmt.executeUpdate()>0;
        con.close();
        return retorno;
    }

    @Override
    public List<Compromisso> compAgenda(String emailUsuario, String nomeAgenda) throws SQLException, ClassNotFoundException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM compromisso WHERE emailUsuario=? AND nomeAgenda=?");
        stmt.setString(1, emailUsuario);
        stmt.setString(2, nomeAgenda);
        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compUsuarioAgenda = new ArrayList<>();
        while(rs.next()){
            Compromisso comp = new Compromisso();
            comp.setEmailUsuario(rs.getString("emailUsuario"));
            comp.setNomeAgenda(rs.getString("nomeAgenda"));
            comp.setData(rs.getDate("data").toLocalDate());
            comp.setHora(rs.getTime("hora").toLocalTime());
            comp.setDescricao(rs.getString("descricao"));
            comp.setLocal(rs.getString("local"));
            compUsuarioAgenda.add(comp);
        }
        con.close();
        return compUsuarioAgenda;
    }

    @Override
    public boolean updateAgendaComp(String emailUsuario, String nomeAntigo, String nomeAtual) throws SQLException, ClassNotFoundException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE compromisso SET nomeAgenda = ? WHERE emailUsuario=? AND nomeAgenda=?");
        stmt.setString(1, nomeAtual);
        stmt.setString(2, emailUsuario);
        stmt.setString(3, nomeAntigo );
        boolean retorno = stmt.executeUpdate()>0;
        con.close();
        return retorno;
    }
    
}
