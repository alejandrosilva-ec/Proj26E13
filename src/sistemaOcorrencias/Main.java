/**
 * ============================================================================
 * SISTEMA DE OCORRÊNCIAS - UNIVERSIDADE PORTUCALENSE (UPT)
 * Departamento de Ciência e Tecnologia
 * ============================================================================
 *
 * Descrição do Programa:
 * Este sistema permite a gestão eficiente de ocorrências no campus da UPT.
 * Os colaboradores podem registar incidentes detalhados, selecionando a 
 * localização exata através de uma hierarquia predefinida (Bloco > Piso > Sala) 
 * e atribuindo níveis de prioridade. 
 * 
 * O sistema automatiza a definição de prazos e alerta para ocorrências 
 * "Em Atraso". 
 * 
 * Os administradores possuem ferramentas 
 * adicionais para monitorizar o estado das ocorrências, adicionar novos locais 
 * ao campus e gerar links simulados de QR Code para cada espaço físico.
 *
 * Desenvolvido pelo Grupo 13:
 * - Afonso Pinto (54290)
 * - Esteban Silva (54354)
 * - Filipe Tolentino (54660)
 * - André Monteiro (53995)
 *
 * ============================================================================
 */

package sistemaOcorrencias;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Gestor gestor = new Gestor();
    static GestorLocais gestorLocais = new GestorLocais();
    static Login sistemaLogin = new Login(); // Instanciamos o sistema de login central

    public static void interfaceSistema() {
        System.out.println("======================================");
        System.out.println("         SISTEMA DE OCORRÊNCIAS       ");
        System.out.println("      Universidade Portucalense       ");
        System.out.println("======================================");
    }

    /**
     * Menu inicial onde o utilizador escolhe a sua ação.
     * Implementa lógica para evitar crashes por letras em vez de números 
     * e um sistema de segurança com 3 tentativas + chave mestra.
     */
    public static void menuInicial(Gestor ocorrencias) {
        int opcao = -1;

        do {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1 - Iniciar Sessão");
            System.out.println("2 - Registar nova conta");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            // Medida de segurança: verifica se a entrada é um inteiro
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1:
                        processarLogin(ocorrencias);
                        break;
                    case 2:
                        System.out.print("Novo e-mail institucional: ");
                        String novoUser = scanner.nextLine();
                        System.out.print("Nova password: ");
                        String novaPass = scanner.nextLine();
                        sistemaLogin.registrarConta(novoUser, novaPass);
                        System.out.println("[OK] Conta criada com sucesso!");
                        break;
                    case 0:
                        System.out.println("\n[INFO] A encerrar o programa. Obrigado por usar o sistema de ocorrências da UPT!");
                        break;
                    default:
                        System.out.println("[ERRO] Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("[ERRO] Por favor, insira um número válido.");
                scanner.nextLine(); // Limpar lixo do buffer
            }
        } while (opcao != 0);
    }

    /**
     * NOVO: Método dedicado para gerir a lógica de 3 tentativas e chave mestra.
     */
    private static void processarLogin(Gestor ocorrencias) {
        int tentativas = 0;
        boolean logado = false;

        System.out.println("\n--- INICIAR SESSÃO ---");

        while (tentativas < 3 && !logado) {
            System.out.print("Username (e-mail): ");
            String user = scanner.nextLine();
            System.out.print("Password: ");
            String pass = scanner.nextLine();

            // Delega a verificação à classe Login
            Utilizador u = sistemaLogin.fazerLogin(user, pass);

            if (u != null) {
                logado = true;
                // Polimorfismo: verifica a que classe pertence a instância
                if (u instanceof Administrador) {
                    System.out.println("\n[OK] Bem-vindo Administrador");
                    menuAdministrador(ocorrencias);
                } else {
                    System.out.println("\n[OK] Login efetuado com sucesso!");
                    menuUtilizador(ocorrencias);
                }
            } else {
                tentativas++;
                System.out.println("[ERRO] Credenciais inválidas. Tentativa " + tentativas + "/3.");
                
                // Sistema de recuperação com a chave da universidade
                if (tentativas == 3) {
                    System.out.println("\n[AVISO] Atingiu o limite de tentativas de login.");
                    System.out.print("Introduza a chave de recuperação do suporte técnico: ");
                    String chave = scanner.nextLine();
                    
                    if (chave.equals("12345ABC")) {
                        System.out.println("[OK] Segurança validada. O sistema concedeu mais uma oportunidade.");
                        tentativas = 2; // Volta atrás no contador para permitir mais 1 tentativa
                    } else {
                        System.out.println("[ERRO] Chave incorreta. Regresso ao menu inicial.");
                    }
                }
            }
        }
    }

    /**
     * Menu para utilizadores padrão. Permite criar e procurar problemas.
     */
    public static void menuUtilizador(Gestor ocorrencias) {
        int opcao = -1;

        do {
            System.out.println("\n========== MENU UTILIZADOR ==========");
            System.out.println("1 - Registar Ocorrência");
            System.out.println("2 - Procurar ocorrência");
            System.out.println("0 - Logout");
            System.out.print("Opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- Nova Ocorrência ---");
                        Local localizacao = gestorLocais.addLocalizacao(scanner);

                        if (localizacao == null) continue; // Aborta se o local for inválido
                        String localString = localizacao.toString();

                        System.out.print("Titulo: ");
                        String titulo = scanner.nextLine();

                        System.out.print("Descrição do problema: ");
                        String descricao = scanner.nextLine();

                        System.out.print("Prioridade (Alta ou Baixa): ");
                        String prioridade = scanner.nextLine();

                        System.out.println("\nDepartamentos:");
                        System.out.println("1 - Departamento de TI");
                        System.out.println("2 - Secretaria");
                        System.out.println("3 - Equipa de limpeza");
                        System.out.println("4 - Segurança");
                        System.out.print("Opção: ");

                        int opDep = scanner.nextInt();
                        scanner.nextLine();

                        String departamento = "";
                        switch (opDep) {
                            case 1: departamento = "Departamento de TI"; break;
                            case 2: departamento = "Secretaria"; break;
                            case 3: departamento = "Equipa de limpeza"; break;
                            case 4: departamento = "Segurança"; break;
                            default:
                                System.out.println("[ERRO] Departamento inválido.");
                                continue;
                        }
                        
                        usePrioridade(prioridade, ocorrencias, titulo, descricao, localString, departamento);
                        break;

                    case 2:
                        System.out.print("\nCódigo a procurar (ex: OC-001): ");
                        String cod = scanner.nextLine();
                        Ocorrencia encontrada = ocorrencias.procurarOcorrencia(cod);
                        
                        if (encontrada != null) {
                            encontrada.printOcorrencia();
                        } else {
                            System.out.println("[INFO] A ocorrência com o código " + cod + " não existe.");
                        }
                        break;

                    case 0:
                        System.out.println("[INFO] Logout efetuado.");
                        break;

                    default:
                        System.out.println("[ERRO] Opção inválida.");
                }
            } else {
                System.out.println("[ERRO] Insira um número.");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    /**
     * Menu para gestores do sistema. Acesso a filtros e QR Codes.
     */
    public static void menuAdministrador(Gestor ocorrencias) {
        int opcao = -1;

        do {
            System.out.println("\n========= MENU ADMINISTRADOR =========");
            System.out.println("1 - Procurar ocorrência");
            System.out.println("2 - Listar pendentes");
            System.out.println("3 - Filtrar por estado");
            System.out.println("4 - Gerar Link QR de um Local");
            System.out.println("5 - Adicionar Novo Local ao Campus");
            System.out.println("0 - Logout");
            System.out.print("Opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("\nCódigo a procurar: ");
                        String cod = scanner.nextLine();
                        Ocorrencia encontrada = ocorrencias.procurarOcorrencia(cod);
                        if (encontrada != null) encontrada.printOcorrencia();
                        else System.out.println("[INFO] Ocorrência " + cod + " não encontrada.");
                        break;

                    case 2:
                        ocorrencias.listarPendentes();
                        break;

                    case 3:
                        System.out.println("\nFiltros:");
                        System.out.println("1 - Abertas");
                        System.out.println("2 - Em atraso");
                        System.out.println("3 - Todas");
                        System.out.print("Opção: ");
                        
                        int filtro = scanner.nextInt();
                        scanner.nextLine();

                        switch (filtro) {
                            case 1: ocorrencias.imprimirPorFiltro("Aberta"); break;
                            case 2: ocorrencias.imprimirPorFiltro("Em atraso"); break;
                            case 3: ocorrencias.imprimirPorFiltro("Todas"); break;
                            default: System.out.println("[ERRO] Opção inválida.");
                        }
                        break;

                    case 4:
                        System.out.println("\n--- Gerador de QR Code ---");
                        Local locQR = gestorLocais.addLocalizacao(scanner);
                        if (locQR != null) {
                            String link = gestorLocais.gerarLinkQR(locQR);
                            System.out.println("\n[QR LINK GERADO] Imprima o seguinte link para o QR Code:");
                            System.out.println("=> " + link);
                        }
                        break;

                    case 5:
                        System.out.println("\n--- Adicionar Novo Local ---");
                        System.out.print("Nome do Bloco (ex: Edifício Principal): ");
                        String bloco = scanner.nextLine();
                        System.out.print("Piso (ex: Piso 6): ");
                        String piso = scanner.nextLine();
                        System.out.print("Espaço/Sala (ex: Sala 601): ");
                        String espaco = scanner.nextLine();
                        System.out.print("Tipo (ex: Laboratório): ");
                        String tipo = scanner.nextLine();
                        
                        gestorLocais.adicionarNovoLocal(bloco, piso, espaco, tipo);
                        
                        // Criação do objeto temporário apenas para extrair o link automático
                        Local novoLoc = new Local(bloco, piso, espaco, tipo);
                        System.out.println("[OK] Novo local guardado com sucesso na memória.");
                        System.out.println("[QR LINK AUTOMÁTICO] => " + gestorLocais.gerarLinkQR(novoLoc));
                        break;

                    case 0:
                        System.out.println("[INFO] Logout de Administrador efetuado.");
                        break;

                    default:
                        System.out.println("[ERRO] Opção inválida.");
                }
            } else {
                System.out.println("[ERRO] Insira um número.");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    /**
     * Trata da instanciação correta (Normal vs Complexa) de acordo com a prioridade.
     */
    public static void usePrioridade(String prioridade, Gestor ocorrencias, String titulo, String descricao, String localString, String departamento) {
        if (prioridade.equalsIgnoreCase("Baixa")) {
            ocorrencias.newOcorrencia(titulo, descricao, prioridade, localString, departamento);
            
        } else if (prioridade.equalsIgnoreCase("Alta")) {
            System.out.print("Link da imagem/prova de dano: ");
            String link = scanner.nextLine();
            
            System.out.print("Tamanho do ficheiro (MB): ");
            // Proteção simples para o tamanho da imagem
            if (scanner.hasNextInt()) {
                int tamanho = scanner.nextInt();
                scanner.nextLine();
                ocorrencias.newComplexa(titulo, descricao, prioridade, localString, departamento, link, tamanho);
            } else {
                System.out.println("[ERRO] Tamanho inválido. A ocorrência não foi registada.");
                scanner.nextLine();
            }
        } else {
            System.out.println("[ERRO] Prioridade inválida. A ocorrência não foi registada.");
        }
    }

    public static void main(String[] args) {
        interfaceSistema();
        menuInicial(gestor);
    }
}