package sistemaOcorrencias;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void interfaceSistema() {
		
		System.out.println("======================================");
		System.out.println("         SISTEMA DE OCORRENCIAS       ");
		System.out.println("======================================");
		
	}
	
	static Scanner scanner = new Scanner(System.in);
	static Gestor gestor = new Gestor();
	static GestorLocais gestorLocais = new GestorLocais();
	static ArrayList<Utilizador> utilizadores = new ArrayList<>();
	
	
	public static void menuInicial(Gestor ocorrencias) {
		
		int opcao;
		
		do {
			System.out.println("\n ---Menu---");
			System.out.println("1 - Utilizador");
			System.out.println("2 - Administrador");
			System.out.println("3 - Registrar Conta");
			System.out.println("0 - Sair");
			
			System.out.print("Opção: ");

			opcao=scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
			
				case 1:
					
					System.out.println("Username:");
					String user = scanner.nextLine();
					
					System.out.println("Password:");
					String pass = scanner.nextLine();
					
					Utilizador encontrado = null;
					
					for(Utilizador u : utilizadores) {
						if(u.getUsername().equals(user) && u.getPassword().equals(pass)){
							encontrado = u;
							break;
						}
					}
					
					if(encontrado != null) {
						System.out.println("Login efetuado com sucesso!");
						menuUtilizador(ocorrencias);
					}else{
						System.out.println("Credenciais inválidas.");
					}
					
					break;

			case 2:
				
				System.out.println("Username:");
				String adminUser = scanner.nextLine();
				
				System.out.println("Password:");
				String adminPass = scanner.nextLine();
				
				Utilizador admin = null;
				
				for(Utilizador u : utilizadores){
					if(u.getUsername().equals(adminUser) && u.getPassword().equals(adminPass)) {
						admin = u;
						break;
					}
				}
				
				if(admin != null) {
					System.out.println("Login de administrador efetuado com sucesso!");
					menuAdministrador(ocorrencias);
				}else {
					System.out.println("Credenciais inválidas");
				}
				
				break;
				
			case 3:
				
				System.out.println("Novo username:");
				String novoUser = scanner.nextLine();
				
				System.out.println("Nova password:");
				String novaPass = scanner.nextLine();
				
				utilizadores.add(new Utilizador(novoUser,novaPass));
				
				System.out.println("Conta criada com sucesso!");
				
				break;
	
			case 0:
				System.out.println("Programa terminado.");
				break;
				
			default:
				
					System.out.println("opção inválida.");
			
			}
		}while(opcao!=0);
	}
	
	public static void menuUtilizador(Gestor ocorrencias) {
		
		int opcao;
		
		do {
			
			System.out.println("---MENU UTILIZADOR---");
			System.out.println("1 - Registrar Ocorrência");
			System.out.println("2 - Procurar ocorrência");
			System.out.println("0 - Logout");
			
			System.out.println("Opção:");
			
			opcao=scanner.nextInt();
			scanner.nextInt();
			
			switch(opcao) {
			
			case 1:
				
				System.out.println("Escolha o local da sua ocorrência:");
				
				Local localizacao = gestorLocais.addLocalizacao(scanner);
			
				if(localizacao == null)
					continue;
			
				String localString = localizacao.toString();
				
				System.out.println("Titulo:");
				String titulo = scanner.nextLine();
				
				System.out.println("Descrição:");
				String descricao = scanner.nextLine();
				
				System.out.print("Prioridade(Alta ou Baixa):");
				String prioridade = scanner.nextLine();
				
				System.out.println("Departamento:");
				
				System.out.println("Departamento:");
                System.out.println("1 - Departamento de TI");
                System.out.println("2 - Secretaria");
                System.out.println("3 - Equipa de limpeza");
                System.out.println("4 - Segurança");
                
                int opDep = scanner.nextInt();
                scanner.nextLine();
                
                String departamento = "";
                
                switch(opDep) {
                
                case 1:
                	departamento = "Departamento de TI";
                	break;
                	
                case 2:
                	departamento = "Secretaria";
                	break;
                	
                case 3:
                	departamento = "Equipa de limpeza";
                	break;
                	
                case 4:
                	departamento = "Segurança";
                	
                default:
                	System.out.println("Opção inválida.");
                	continue;
                }
                
				break;
				
			case 2:
				
				System.out.println("Código a procurar:");
				
				String cod = scanner.nextLine();
				
				boolean encontrado = false;
				
				for(Ocorrencia o : ocorrencias.getOcorrencias()) {
					if(o.getCodigo().equalsIgnoreCase(cod)) {
						o.printOcorrencia();
						encontrado = true;
						break;
					}
				}
				
				if(!encontrado) {
					System.out.println("A ocorrência com o código" + cod + "não existe.");
				}
				
				break;
				
			case 0:
				System.out.println("Logout efetuado.");
				break;
				
			default:
				System.out.println("Opção inválida.");
				
			}
		}while(opcao != 0);
	}
	
	public static void menuAdministrador(Gestor ocorrencias) {
		
		int opcao;
		
		do {
			
			System.out.println("---MENU ADMINISTRADOR---");
	        System.out.println("1 - Procurar ocorrência");
	        System.out.println("2 - Listar pendentes");
	        System.out.println("3 - Filtrar por estado");
	        System.out.println("0 - Logout");
			
			System.out.print("Opção:");
			
			opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch(opcao) {
			
			case 1:
				
				System.out.println("Código a procurar:");
				
				String cod = scanner.nextLine();
				
				boolean encontrado = false;
				
				for(Ocorrencia o : ocorrencias.getOcorrencias()) {
					if(o.getCodigo().equalsIgnoreCase(cod)) {
						o.printOcorrencia();
						encontrado = true;
						break;
					}
				}
				
				if(!encontrado) {
					System.out.println("A ocorrência com o código" + cod + "não existe.");
				}
				
				break;
			
			case 2:
				
				ocorrencias.listarPendentes();
				
			case 3:
				
				System.out.println("1 - Abertas");
				System.out.println("2 - Em atraso");
				System.out.print("3 - Todas");
				
				int filtro = scanner.nextInt();
				scanner.nextLine();
				
				switch(filtro) {
				
				case 1:
					
					ocorrencias.imprimirPorFiltro("Aberta");
					break;
					
				case 2:
					
					ocorrencias.imprimirPorFiltro("Em atraso");
					break;
				
				case 3:
					
					ocorrencias.imprimirPorFiltro("Todas");
					break;
					
				default:
					
					System.out.println("Opção inválida.");
				
			}
			
			break;
			
			case 0:
				
				System.out.println("Logout efetuado.");
				break;
				
			default:
				
				System.out.println("Opção inválida.");
			
			}
		}while(opcao != 0);
	}
	
		public static void main(String [] args) {
			
			Gestor ocorrencias = new Gestor();
			interfaceSistema();
			menuInicial(ocorrencias);
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