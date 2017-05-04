
package com.ifpb.HiDiary.Controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.ifpb.HiDiary.Modelo.Usuario;

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
    public boolean create(Usuario novo) throws IOException, ClassNotFoundException{
        List<Usuario> usuarios = list();
        
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(novo.getEmail())){
                return false;
            }
        }
        usuarios.add(novo);
        atualizarArquivo(usuarios);
        return true;
    }
    
    
    @Override
    public Usuario read(String email) throws IOException, ClassNotFoundException{
        List<Usuario> usuarios = list();
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(email)){
                return usuarios.get(i);
            }
        }
        return null;
    }
    
    @Override
    public boolean update(Usuario u) throws IOException, ClassNotFoundException{
        List<Usuario> usuarios = list();
        
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(u.getEmail())){
                usuarios.set(i, u);
                atualizarArquivo(usuarios);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean delete(String email) throws IOException, ClassNotFoundException{
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
    public List<Usuario> list() throws IOException, ClassNotFoundException{
        List<Usuario> usuario = null;
        
        if(arquivo.length()>0){
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(arquivo));
            return (List<Usuario>) input.readObject();
        }else{
            return new ArrayList<Usuario>();
        }
    }
    
    private void atualizarArquivo(List<Usuario> usuarios) throws FileNotFoundException, IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(arquivo));
        output.writeObject(usuarios);
        output.close();
    }
    
    
}
