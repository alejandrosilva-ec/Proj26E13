package sistemaOcorrencias;

import java.time.LocalDate;

/**
 * Superclasse que contém as informações gerais de uma ocorrência.
 */
public class Ocorrencia {

	private String codigo;	
	private String titulo;
	private String descricao;
	private String prioridade;
	private String estado;
	private String localizacao;
	private String departamento;
	private LocalDate dataCriacao;
	private LocalDate dataLimite;
	
	// Construtor
	public Ocorrencia(String codigo, String titulo, String descricao, String prioridade, String estado,
			String localizacao, String departamento, int prazo) {
		
		this.codigo = codigo;
		this.titulo = titulo;
		this.descricao = descricao;
		this.prioridade = prioridade;
		this.estado = estado;
		this.localizacao = localizacao;
		this.departamento = departamento;
		dataCriacao = LocalDate.now();
		dataLimite = dataCriacao.plusDays(prazo);
	}
	
	public String getCodigo() { 
		return codigo; }
	
	public void setCodigo(String codigo) { 
		this.codigo = codigo; }
	
	public String getTitulo() { 
		return titulo; }
	
	public void setTitulo(String titulo) { 
		this.titulo = titulo; }
	
	public String getDescricao() { 
		return descricao; }
	
	public void setDescricao(String descricao) { 
		this.descricao = descricao; }
	
	public String getPrioridade() { 
		return prioridade; }
	
	public void setPrioridade(String prioridade) { 
		this.prioridade = prioridade; }
	
	public String getEstado() { 
		return estado; }
	
	public void setEstado(String estado) { 
		this.estado = estado; }
	
	public String getDepartamento() { 
		return departamento; }
	
	public void setDepartamento(String departamento) { 
		this.departamento = departamento; }
	
	public String getLocalizacao() { 
		return localizacao; }
	
	public LocalDate getDataCriacao() { 
		return dataCriacao; }
	
	public LocalDate getDataLimite() { 
		return dataLimite; }
	
	
	public void printOcorrencia() {
		System.out.println("\nCodigo: " + codigo + "\nTitulo: " + titulo + "\nDescrição: " + descricao 
				+ "\nDepartamento: " + departamento + "\nEstado: " + estado 
				+ "\nLocalização: " + localizacao + "\nPrioridade: " + prioridade + "\nPrazo: " + dataLimite);
	}
}