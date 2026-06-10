

package sistemaOcorrencias;

import java.util.Scanner;

public class Main {

	public static void interfaceSistema() {
		
		System.out.println("======================================");
		System.out.println("         SISTEMA DE OCORRENCIAS       ");
		System.out.println("======================================");
		
	}
	
	static Scanner scanner = new Scanner(System.in);
	static Gestor gestor = new Gestor();
	static GestorLocais gestorLocais = new GestorLocais();
	
	public static void menu(Gestor ocorrencias) {
		
		int opcao;
		
		do {
			System.out.println("\n ---Menu---");
			System.out.println("1 - Registrar ocorrência");
			System.out.println("2 - Procurar ocorrência");
			System.out.println("3 - Listar pendentes (Abertas + Em Atraso)");
			System.out.println("4 - Filtrar / Imprimir por estado");
			System.out.println("0 - Sair");
			System.out.println("\nEscolha uma opção (Inserir só o número): ");
			
			opcao=scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
			
				case 1:
					
				System.out.println("Escolha o local da sua ocorrencia: ");

				Local localizacao = gestorLocais.addLocalizacao(scanner);

				if (localizacao == null) continue;

				String localString = localizacao.toString();
				
				
				System.out.print("Titulo: ");
				String titulo = scanner.nextLine();
				
				System.out.print("Descrição: ");
				String descricao = scanner.nextLine();
				
				System.out.print("Prioridade: ");
				String prioridade = scanner.nextLine();
				
				System.out.println("Departamento: ");
				System.out.println("1 - Departamento de TI");
				System.out.println("2 - Secretaria");
				System.out.println("3 - Equipe de limpeza");
				System.out.println("4 - Segurança");
				System.out.print("Opção:");
				int opcaoDept = scanner.nextInt();
				scanner.nextLine();
				
				
				String departamento;
				switch(opcaoDept) {
				case 1:
					departamento = "Departamento de TI";
					usePrioridade(prioridade, ocorrencias, titulo, descricao, localString, departamento);
					break;
				case 2:
					departamento = "Secretaria";
					usePrioridade(prioridade, ocorrencias, titulo, descricao, localString, departamento);
					break;
				case 3:
					departamento = "Equipe de limpeza";
					usePrioridade(prioridade, ocorrencias, titulo, descricao, localString, departamento);
					break;
				case 4:
					departamento = "Segurança";
					usePrioridade(prioridade, ocorrencias, titulo, descricao, localString, departamento);
					break;
				default:
					System.out.println("Opção inválida.");
					continue;
				}

				// Depois de registar conforme prioridade, volta ao menu
				break;

			case 2:
				
				/*System.out.print("Código a procurar:");
				String cod = scanner.nextLine();
				
				Ocorrencia encontrada = gestor.procurarOcorrencia(cod);
				
				if(encontrada != null) {
					System.out.print(encontrada);
					
				}else {
					System.out.print("Ocorrencia não encontrada.");
				}
				break;*/
				
				System.out.print("Código a procurar: ");
				String cod = scanner.nextLine();
				Boolean encontrado = false;
				
				for (Ocorrencia o : ocorrencias.getOcorrencias()) {
					if(o.getCodigo().equalsIgnoreCase(cod)) {
						o.printOcorrencia();
						encontrado = true;
						break;
					}
				}
				if(encontrado == false) {
					System.out.println("A ocorrência com o código " + cod + " não existe!");
				}
				break;

			case 3:
				// Listar pendentes (Aberta + Em Atraso)
				ocorrencias.listarPendentes();
				break;
				
			case 4:
				
			    System.out.println("Escolha o filtro:");
			    System.out.println("1 - Abertas");
			    System.out.println("2 - Em atraso");
			    System.out.println("3 - Mostrar todas");
			    int opFiltro = scanner.nextInt();
			    scanner.nextLine();

				switch(opFiltro) {
				    case 1: ocorrencias.imprimirPorFiltro("Aberta"); break;
				    case 2: ocorrencias.imprimirPorFiltro("Em atraso"); break;
				    case 3: ocorrencias.imprimirPorFiltro("Todas"); break;
				    default: System.out.println("Opção inválida.");
				}
			    break;
					
					}
			
				
				}while(opcao !=0);
			}
	
		public static void main(String [] args) {
			
			Gestor ocorrencias = new Gestor();
			interfaceSistema();
			menu(ocorrencias);
		}
		
		public static void usePrioridade(String prioridade, Gestor ocorrencias, String titulo, String descricao, String localString, String departamento) {
			if(prioridade.equalsIgnoreCase("Baixa")) {
				
			    ocorrencias.newOcorrencia(titulo,descricao,prioridade,localString,departamento);
			
			    System.out.print("Ocorrencia registrada");
			    return;
			}else if(prioridade.equalsIgnoreCase("Alta")) {
				
				System.out.print("Link: ");
				String link = scanner.nextLine();
				
				System.out.print("Tamanho: ");
				int tamanho = scanner.nextInt();
				scanner.nextLine();
				
				ocorrencias.newComplexa(titulo,descricao,prioridade,localString,departamento,link,tamanho);
				return;
			}
			
		}
	}
		
			