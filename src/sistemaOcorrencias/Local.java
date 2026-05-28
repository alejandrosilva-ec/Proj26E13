/**
 * This class contains the object that allow the user to select the location of the 
 * problem
 */

package sistemaOcorrencias;

public class Local {

    private String tipo;
    private String numeroSala;
    
    public Local(String tipo, String numeroSala) {
        
        this.tipo=tipo;
        this.numeroSala=numeroSala;        
        
    }
    
    public String toString() {
        
        if(tipo.equalsIgnoreCase("Sala")) {
            
            return "Tipo:" + tipo + ", Número da sala:" + numeroSala;
            
        }
        
        return "Tipo:" + tipo;
    }
}