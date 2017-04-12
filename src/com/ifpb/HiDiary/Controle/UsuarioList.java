
package com.ifpb.HiDiary.Controle;
import java.util.ArrayList;
import java.util.List;
import com.ifpb.HiDiary.Modelo.Usuario;

/**
 * Essa classe representa o DAO da classe usuário, nela é possível fazer todo o CRUD de usuários usando lista
 * @author Lyndemberg
 * @version 1.0
 */
public class UsuarioList{
    private List<Usuario> usuarios;
    /**
    * Construtor da classe UsuarioList 
    * @author Lyndemberg 
    * @version 1.0
    */
    public UsuarioList(){
        usuarios = new ArrayList<>();
    }
    
    /**
    * Método para criar um novo usuário na lista de usuários
    * @author Lyndemberg 
    * @version 1.0
    * @param u representa o usuário que foi passado para ser criado dentro da lista
    * @return retorna True se o usuário foi adicionado na lista. Retorna False se ele não foi adicionado
    */
    public boolean create(Usuario u) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(u.getEmail())){
                return false;
            }
        }
        return usuarios.add(u);
    }

    /**
    * Método para ler um usuário
    * @author Lyndemberg 
    * @version 1.0
    * @param email representa o email do usuário a ser lido
    * @return Retorna o usuário que é dono do email que foi passado. Retorna Null se não encontrar nenhum usuário com esse email
    */
    public Usuario read(String email) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(email)) return usuarios.get(i);
        }
        return null;
    }

    /**
    * Método para atualizar um determinado usuário
    * @author Lyndemberg 
    * @version 1.0
    * @param u representa o usuário que vai ser atualizado
    * @return Retorna True se encontrar um usuário que tem o mesmo email do usuário passado, dessa maneira atualizado-o. Retorna False 
    * se não encontrar nenhum usuário com o email do usuário passado.
    */
    public boolean update(Usuario u) {
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(u.getEmail())){
                usuarios.set(i, u);
                return true;
            }
        }
        return false;
    }

    /**
    * Método para remover um determinado usuário da lista
    * @author Lyndemberg 
    * @version 1.0
    * @param u representa o usuário que vai ser removido
    * @return Retorna True se remover o usuário. Retorna False se não encontrar o usuário para remover
    */
    public boolean remove(Usuario u) {
        return usuarios.remove(u);
    }
    
}
