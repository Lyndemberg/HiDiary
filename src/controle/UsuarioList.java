
package controle;

import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class UsuarioList{
    private List<Usuario> usuarios;
    
    public UsuarioList(){
        usuarios = new ArrayList<>();
    }
    
    
    public boolean create(Usuario u) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(u.getEmail())){
                return false;
            }
        }
        return usuarios.add(u);
    }

    
    public Usuario read(String email) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(email)) return usuarios.get(i);
        }
        return null;
    }

    
    public boolean update(Usuario u) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(u.getEmail())){
                usuarios.set(i, u);
                return true;
            }
        }
        return false;
    }

    
    public boolean remove(Usuario u) {
        return usuarios.remove(u);
    }
    
}
