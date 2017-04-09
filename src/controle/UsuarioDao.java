
package controle;

import modelo.Usuario;

public interface UsuarioDao {
    
    public boolean create(Usuario u);
    public Usuario read(String email);
    public boolean update(Usuario u);
    public boolean remove(Usuario u);
    
}
