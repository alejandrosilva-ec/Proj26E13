package sistemaOcorrencias;

/**
 * Subclasse de Utilizador.
 * Usamos herança para poder distinguir os perfis no Main através de Polimorfismo (instanceof).
 */
public class Administrador extends Utilizador {
    
    // Chama o construtor da superclasse
    public Administrador(String username, String password) {
        super(username, password);
    }
}