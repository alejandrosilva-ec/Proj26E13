package sistemaOcorrencias;

/**
 * Representa um espaço físico do sistema
 * Os atributos são imutáveis para proteger a estrutura física
 */
public class Local {

    private String bloco;
    private String piso;
    private String espaco;
    private String tipo;

    public Local(String bloco, String piso, String espaco, String tipo) {
        this.bloco  = bloco;
        this.piso   = piso;
        this.espaco = espaco;
        this.tipo   = tipo;
    }

    // Construtor de compatibilidade com código legado
    public Local(String tipo, String numeroSala) {
        this.bloco  = "Edifício Principal";
        this.piso   = "";
        this.espaco = numeroSala.isEmpty() ? tipo : "Sala " + numeroSala;
        this.tipo   = tipo;
    }

    public String getBloco()  { return bloco; }
    public String getPiso()   { return piso; }
    public String getEspaco() { return espaco; }
    public String getTipo()   { return tipo; }

    @Override
    public String toString() {
        if (espaco != null && !espaco.isEmpty()) {
            return bloco + " | " + piso + " | " + espaco + " (" + tipo + ")";
        }
        return bloco + " | " + piso + " (" + tipo + ")";
    }
}