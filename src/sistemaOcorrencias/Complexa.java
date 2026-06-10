package sistemaOcorrencias;

/**
 * Subclasse que herda de Ocorrencia para tratar problemas deprioridade Alta 
 */
public class Complexa extends Ocorrencia {
	
	private String link;
	private int tamanho;
	
	// Construtor da subclasse
	public Complexa(String codigo, String titulo, String descricao, String prioridade, String estado,
			String localizacao, String departamento, int prazo, String link, int tamanho) {
		
		super(codigo, titulo, descricao, prioridade, estado, localizacao, departamento, prazo);
		this.link = link;
		this.tamanho = tamanho;
	}
	
	public String getLink() { return link; }
	public void setLink(String link) { this.link = link; }
	public int getTamanho() { return tamanho; }
	public void setTamanho(int tamanho) { this.tamanho = tamanho; }
	
	// Método de impressão com polimorfismo
	@Override
	public void printOcorrencia() {
		super.printOcorrencia();
		System.out.print("Link: " + link + "\nTamanho: " + tamanho + "MB\n");
	}
}