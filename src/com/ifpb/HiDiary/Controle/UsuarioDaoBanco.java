
package com.ifpb.HiDiary.Controle;

import com.ifpb.HiDiary.Banco.ConFactory;
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

public class UsuarioDaoBanco implements UsuarioDao{
    public UsuarioDaoBanco(){
        
    }
    @Override
    public Usuario read(String email) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ?");
        stmt.setString(1, email);
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            Usuario usuario = new Usuario();
            
            usuario.setEmail(rs.getString("email"));
            Date dataDate = rs.getDate("nascimento");
            LocalDate nascimento = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dataDate));
            usuario.setNascimento(nascimento);
            usuario.setSexo(rs.getString("sexo"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
            con.close();
            return usuario;
        }else{
            con.close();
            return null;
        }
    }

    @Override
    public List<Usuario> list() throws SQLException, ClassNotFoundException, IOException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario");
        
        ResultSet rs = stmt.executeQuery();
        List<Usuario> usuarios = new ArrayList<>();
        while(rs.next()){
            Usuario usuario = new Usuario();
            usuario.setEmail(rs.getString("email"));
            Date dataDate = rs.getDate("nascimento");
            LocalDate nascimento = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dataDate));
            usuario.setNascimento(nascimento);
            usuario.setSexo(rs.getString("sexo"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
            usuarios.add(usuario);
        }
        con.close();
        return usuarios;
    }

    @Override
    public boolean create(Usuario usuario) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO usuario (nome, nascimento, sexo, email, senha)"+
                                                      "VALUES (?,?,?,?,?)");
        stmt.setString(1, usuario.getNome());
        stmt.setDate(2, java.sql.Date.valueOf(usuario.getNascimento()));
        stmt.setString(3, usuario.getSexo());
        
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getSenha());
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

    @Override
    public boolean update(Usuario usuario) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE usuario SET(nome, nascimento, sexo, email, senha)"+
                                                      "= (?,?,?,?,?) WHERE email = ?");
        
        stmt.setString(1, usuario.getNome());
        stmt.setDate(2, java.sql.Date.valueOf(usuario.getNascimento()));
        stmt.setString(3, usuario.getSexo());
        
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getSenha());
        boolean retorno = stmt.executeUpdate()>0;
        con.close();
        return retorno;
    }

}
