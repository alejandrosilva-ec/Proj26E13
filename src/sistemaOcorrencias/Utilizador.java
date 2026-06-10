package sistemaOcorrencias;

/**
 * Classe base que representa um utilizador do sistema.
 * Aplica o princípio de encapsulamento com atributos privados.
 */
public class Utilizador {

    private String username;
    private String password;
    
    // Construtor para inicializar as credenciais
    public Utilizador(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
}