
package com.ifpb.HiDiary.Controle;

import com.ifpb.HiDiary.Modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDaoBinario implements UsuarioDao{
    private File arquivo;
    
    public UsuarioDaoBinario(){
        arquivo = new File("usuarios.bin");
        
        if(!arquivo.exists()){
            try{
                arquivo.createNewFile();
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Falha na conexão com o arquivo","Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
    
    @Override
    public Usuario read(String email) throws ClassNotFoundException, IOException {
        List<Usuario> usuarios = list();
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(email)){
                return usuarios.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Usuario> list() throws ClassNotFoundException, IOException {
        List<Usuario> usuario = null;
        
        if(arquivo.length()>0){
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(arquivo));
            return (List<Usuario>) input.readObject();
        }else{
            return new ArrayList<Usuario>();
        }
    }

    @Override
    public boolean create(Usuario usuario) throws ClassNotFoundException, IOException {
        List<Usuario> usuarios = list();
        
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(usuario.getEmail())){
                return false;
            }
        }
        usuarios.add(usuario);
        atualizarArquivo(usuarios);
        return true;
    }

    @Override
    public boolean delete(String email) throws ClassNotFoundException, IOException {
        List<Usuario> usuarios = list();
        
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(email)){
                usuarios.remove(usuarios.get(i));
                atualizarArquivo(usuarios);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Usuario usuario) throws ClassNotFoundException, IOException {
        List<Usuario> usuarios = list();
        
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(usuario.getEmail())){
                usuarios.set(i, usuario);
                atualizarArquivo(usuarios);
                return true;
            }
        }
        return false;
    }
    
     private void atualizarArquivo(List<Usuario> usuarios) throws FileNotFoundException, IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(arquivo));
        output.writeObject(usuarios);
        output.close();
    }
}
