package sistemaOcorrencias;

import java.util.ArrayList;

/**
 * Gere a lista de ocorrências e a lógica de criação baseada na prioridade
 */
public class Gestor {

	private ArrayList<Ocorrencia> ocorrencias;
	private int contadorCodigo;
	
	public Gestor() {
		ocorrencias = new ArrayList<Ocorrencia>();
		contadorCodigo = 1;
	}

	public ArrayList<Ocorrencia> getOcorrencias() { return ocorrencias; }
	
	public Ocorrencia registarOcorrencia(String titulo, String descricao, String prioridade,
			String localizacao, String departamento, String link, int tamanho) {
 
		if (titulo == null || titulo.trim().isEmpty()) {
			System.out.println("[ERRO] O título não pode estar vazio.");
			return null;
		}
		if (descricao == null || descricao.trim().isEmpty()) {
			System.out.println("[ERRO] A descrição não pode estar vazia.");
			return null;
		}
		if (localizacao == null || localizacao.trim().isEmpty()) {
			System.out.println("[ERRO] A localização não pode estar vazia.");
			return null;
		}
		if (departamento == null || departamento.trim().isEmpty()) {
			System.out.println("[ERRO] O departamento não pode estar vazio.");
			return null;
		}
		
		String prioridadeDefinida = definirPrioridade(prioridade);
		if (prioridadeDefinida == null) {
			System.out.println("[ERRO] Prioridade definida é inválida: " + prioridade + ". Use Alta ou Baixa.");
			return null;
		}
		
		if (prioridadeDefinida.equals("Alta")) {
			if (link == null || link.trim().isEmpty()) {
				System.out.println("[ERRO] Ocorrências de prioridade Alta requerem um link de anexo.");
				return null;
			}
			if (tamanho <= 0) {
				System.out.println("[ERRO] Ocorrências de prioridade Alta requerem um tamanho de ficheiro válido.");
				return null;
			}
			return newComplexa(titulo, descricao, prioridadeDefinida, localizacao, departamento, link, tamanho);
		}
 
		return newOcorrencia(titulo, descricao, prioridadeDefinida, localizacao, departamento);
	}
		
	public Ocorrencia newOcorrencia(String titulo, String descricao, String prioridade,
			String localizacao, String departamento) {
 
		String codigo = gerarCodigo();
		int prazo     = definirPrazoPrio(prioridade);
 
		Ocorrencia oc = new Ocorrencia(codigo, titulo.trim(), descricao.trim(), prioridade,
				"Aberta", localizacao.trim(), departamento.trim(), prazo);
 
		ocorrencias.add(oc);
		contadorCodigo++;
 
		System.out.println("[OK] Ocorrência registada! Código: " + codigo + " | Tipo: Normal | Prazo: " + prazo + " dia(s).");
		return oc;
	}
 
	public Complexa newComplexa(String titulo, String descricao, String prioridade,
			String localizacao, String departamento, String link, int tamanho) {
 
		String codigo = gerarCodigo();
		int prazo     = definirPrazoPrio(prioridade);
 
		Complexa oc = new Complexa(codigo, titulo.trim(), descricao.trim(), prioridade,
				"Aberta", localizacao.trim(), departamento.trim(), prazo, link.trim(), tamanho);
 
		ocorrencias.add(oc);
		contadorCodigo++;
 
		System.out.println("[OK] Ocorrência registada! Código: " + codigo + " | Tipo: Complexa | Prazo: " + prazo + " dia(s).");
		return oc;
	}
 
	private String gerarCodigo() {
		return String.format("OC-%03d", contadorCodigo);
	}

	private String definirPrioridade(String prioridade) {
		if (prioridade == null) return null;
		switch (prioridade.trim().toLowerCase()) {
			case "alta":  return "Alta";
			case "baixa": return "Baixa";
			default:      return null;
		}
	}
 
	private int definirPrazoPrio(String prioridade) {
		switch (prioridade) {
			case "Alta": return 3;
			default:     return 5; 
		}
	}
	
	public Ocorrencia procurarOcorrencia(String codigo) {
		for(Ocorrencia i : ocorrencias) {
			if(i.getCodigo().equals(codigo)) {
				return i;
			}
		}
		return null;
	}
	
	public void visualizarOcorrencias(String estado) {
		for(Ocorrencia o : ocorrencias) {
			if(o.getEstado().equalsIgnoreCase(estado)) {
				o.printOcorrencia(); 
			}
		}
	}
	
	public void listarPendentes() {
		java.time.LocalDate hoje = java.time.LocalDate.now();
		for (Ocorrencia o : ocorrencias) {
			if (o.getEstado().equalsIgnoreCase("Aberta") && o.getDataLimite().isBefore(hoje)) {
				o.setEstado("Em atraso");
				System.out.println("[AVISO] Ocorrência " + o.getCodigo() + " passou para \"Em atraso\".");
			}
		}
		
		java.util.List<Ocorrencia> emAtraso = new java.util.ArrayList<>();
		java.util.List<Ocorrencia> abertas = new java.util.ArrayList<>();
		
		for (Ocorrencia o : ocorrencias) {
			if (o.getEstado().equalsIgnoreCase("Em atraso")) {
				emAtraso.add(o);
			} else if (o.getEstado().equalsIgnoreCase("Aberta")) {
				abertas.add(o);
			}
		}

		if (emAtraso.isEmpty() && abertas.isEmpty()) {
			System.out.println("\n[INFO] Não existem ocorrências pendentes. Tudo resolvido!");
			return;
		}
		
		System.out.println("\n==========================================");
		System.out.println("       OCORRÊNCIAS PENDENTES              ");
		System.out.println("==========================================");
		
		if (!emAtraso.isEmpty()) {
			System.out.println("\n⚠  EM ATRASO (" + emAtraso.size() + ")");
			System.out.println("------------------------------------------");
			for (Ocorrencia o : emAtraso) {
					o.printOcorrencia();
			System.out.println("------------------------------------------");
			}
		}

		if (!abertas.isEmpty()) {
			System.out.println("\n✔  ABERTAS (" + abertas.size() + ")");
			System.out.println("------------------------------------------");
			for (Ocorrencia o : abertas) {
				o.printOcorrencia();
			System.out.println("------------------------------------------");
			}
		}
		
		System.out.println("\nTotal pendentes: " + (emAtraso.size() + abertas.size()));
		System.out.println("==========================================\n");
	}
	
	public void imprimirPorFiltro(String filtro) {
	    java.time.LocalDate hoje = java.time.LocalDate.now();
	    for (Ocorrencia o : ocorrencias) {
	        if (o.getEstado().equalsIgnoreCase("Aberta") && o.getDataLimite().isBefore(hoje)) {
	            o.setEstado("Em atraso");
	        }
	    }

	    System.out.println("\n--- Filtrando por: " + filtro + " ---");
	    boolean encontrou = false;

	    for (Ocorrencia o : ocorrencias) {
	        if (filtro.equalsIgnoreCase("Todas") || o.getEstado().equalsIgnoreCase(filtro)) {
	            o.printOcorrencia();
	            System.out.println("--------------------------");
	            encontrou = true;
	        }
	    }

	    if (!encontrou) {
	        System.out.println("[INFO] Nenhuma ocorrência encontrada com o filtro: " + filtro);
	    }
	}
}