
package sistemaOcorrencias;

import java.util.ArrayList;

/**
 * Gere a autenticação e armazena as contas do sistema.
 */
public class Login {

    private ArrayList<Utilizador> utilizadores;
    
    public Login() {
        utilizadores = new ArrayList<>();
        
        // Dados pré-carregados para demonstração aos professores
        // Com polimorfismo a lista guarda tanto Utilizadores normais como Administradores
        utilizadores.add(new Utilizador("54354@utilizador.upt", "123"));
        utilizadores.add(new Utilizador("76543@utilizador.upt", "123"));
        utilizadores.add(new Administrador("flcarvalho@administrador.upt", "admin123"));
        utilizadores.add(new Administrador("amsilva@administrador.upt", "admin123"));
    }
    
    // Regista apenas utilizadores comuns
    public void registrarConta(String user, String pass) {
        utilizadores.add(new Utilizador(user, pass));
    }
    
    // Verifica credenciais e devolve o objeto correto (Utilizador ou Administrador)
    public Utilizador fazerLogin(String user, String pass) {
        for(Utilizador i : utilizadores) {
            if(i.getUsername().equals(user) && i.getPassword().equals(pass)) {
                return i;
            }
        }
        return null; 
        // Retorna null se não encontrar
    }
}