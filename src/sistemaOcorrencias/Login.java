package sistemaOcorrencias;

import java.util.ArrayList;

public class Login {

    private ArrayList<Utilizador> utilizadores;
    
    public Login() {
        
        utilizadores = new ArrayList<>();
        
    }
    
    public void registrarConta(String user,String pass) {
        
        utilizadores.add(new Utilizador(user,pass));
        
    }
    
    public Utilizador login(String user,String pass) {
        
        for(Utilizador i : utilizadores) {
            if(i.getUsername().equals(user) && i.getPassword().equals(pass)) {
                return i;
            }
        }
        return null;
    }
}