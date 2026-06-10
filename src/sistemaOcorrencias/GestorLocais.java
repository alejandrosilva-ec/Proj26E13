package sistemaOcorrencias;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Gere os locais disponíveis no sistema, baseados no mapeamento de espaços do ficheiro Excel.
 *
 * Estrutura de dados:
 *   Bloco → Piso → Lista de espaços (Local)
 *
 * Blocos disponíveis:
 *   1. Edifício Principal  (Pisos 1-5 + Geral)
 *   2. São Tomé            (Piso Único)
 *   3. Parque de Estacionamento 1 (Pisos 1 e 3)
 */
public class GestorLocais {

    // Mapa: Bloco → Piso → Lista de Locais
    private Map<String, Map<String, List<Local>>> mapa;

    public GestorLocais() {
        mapa = new LinkedHashMap<>();
        carregarLocais();
    }

    /**
     * Carrega todos os locais com base no mapeamento do ficheiro "Espaços.xlsx".
     */
    private void carregarLocais() {

        // ── Edifício Principal ────────────────────────────────────────────────
        String blocoP = "Edifício Principal";
        addLocal(blocoP, "Piso 1", "Sala 101", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 102", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 103", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 104", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 105", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 106", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 107", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 108", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 109", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 110", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 111", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 112", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 113", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 114", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Sala 115", "Sala de Aula");
        addLocal(blocoP, "Piso 1", "Casa de Banho 1", "Serviços");
        addLocal(blocoP, "Piso 1", "Casa de Banho 2", "Serviços");

        addLocal(blocoP, "Piso 2", "Sala 201", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 202", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 203", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 204", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 205", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 206", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 207", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 208", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 209", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 210", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 211", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 212", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 213", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 214", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 215", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 216", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Sala 217", "Sala de Aula");
        addLocal(blocoP, "Piso 2", "Casa de Banho 1", "Serviços");
        addLocal(blocoP, "Piso 2", "Casa de Banho 2", "Serviços");

        addLocal(blocoP, "Piso 3", "Sala 301", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 302", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 303", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 304", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 305", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 306", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 307", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 308", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 309", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 310", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 311", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 312", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 313", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 314", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 315", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 316", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Sala 317", "Sala de Aula");
        addLocal(blocoP, "Piso 3", "Casa de Banho 1", "Serviços");
        addLocal(blocoP, "Piso 3", "Casa de Banho 2", "Serviços");

        addLocal(blocoP, "Piso 4", "Sala 401", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 402", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 403", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 404", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 405", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 406", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 407", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 408", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 409", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 410", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 411", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 412", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 413", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 414", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 415", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 416", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 417", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 418", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 419", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Sala 422", "Sala de Aula");
        addLocal(blocoP, "Piso 4", "Casa de Banho 1", "Serviços");
        addLocal(blocoP, "Piso 4", "Casa de Banho 2", "Serviços");

        addLocal(blocoP, "Piso 5", "Sala 501", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 502", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 503", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 504", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 505", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 506", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 507", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 508", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 509", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 510", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 511", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 512", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 513", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 514", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 515", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 516", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 517", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 518", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 519", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Sala 522", "Sala de Aula");
        addLocal(blocoP, "Piso 5", "Casa de Banho 1", "Serviços");
        addLocal(blocoP, "Piso 5", "Casa de Banho 2", "Serviços");

        addLocal(blocoP, "Geral", "Cantina", "Serviços");

        // ── São Tomé 
        String blocoST = "São Tomé";
        addLocal(blocoST, "Piso Único", "Sala 754", "Sala de Aula");
        addLocal(blocoST, "Piso Único", "Sala 755", "Sala de Aula");
        addLocal(blocoST, "Piso Único", "Sala 756", "Sala de Aula");
        addLocal(blocoST, "Piso Único", "Sala 757", "Sala de Aula");

        // ── Parque de Estacionamento 1 
        String blocoParque = "Parque de Estacionamento 1";
        addLocal(blocoParque, "Piso 1", "", "Estacionamento");
        addLocal(blocoParque, "Piso 3", "", "Estacionamento");
    }

    /**
     * Adiciona um local ao mapa interno.
     */
    private void addLocal(String bloco, String piso, String espaco, String tipo) {
        mapa.computeIfAbsent(bloco, k -> new LinkedHashMap<>())
            .computeIfAbsent(piso,  k -> new ArrayList<>())
            .add(new Local(bloco, piso, espaco, tipo));
    }

    /**
     * Guia o utilizador na seleção de um local, apresentando os blocos,
     * depois os pisos e depois os espaços disponíveis de forma interativa.
     *
     * @param scanner Scanner partilhado com o Main
     * @return o Local selecionado, ou null se a opção for inválida
     */
    public Local addLocalizacao(Scanner scanner) {

        // 1. Selecionar bloco
        List<String> blocos = new ArrayList<>(mapa.keySet());
        System.out.println("\nBlocos disponíveis:");
        for (int i = 0; i < blocos.size(); i++) {
            System.out.println((i + 1) + " - " + blocos.get(i));
        }
        System.out.print("Opção: ");
        int opcaoBloco = scanner.nextInt();
        scanner.nextLine();

        if (opcaoBloco < 1 || opcaoBloco > blocos.size()) {
            System.out.println("Bloco inválido.");
            return null;
        }

        String blocoEscolhido = blocos.get(opcaoBloco - 1);
        Map<String, List<Local>> pisos = mapa.get(blocoEscolhido);

        // 2. Selecionar piso
        List<String> listaPisos = new ArrayList<>(pisos.keySet());
        System.out.println("\nPisos disponíveis em " + blocoEscolhido + ":");
        for (int i = 0; i < listaPisos.size(); i++) {
            System.out.println((i + 1) + " - " + listaPisos.get(i));
        }
        System.out.print("Opção: ");
        int opcaoPiso = scanner.nextInt();
        scanner.nextLine();

        if (opcaoPiso < 1 || opcaoPiso > listaPisos.size()) {
            System.out.println("Piso inválido.");
            return null;
        }

        String pisoEscolhido = listaPisos.get(opcaoPiso - 1);
        List<Local> espacos = pisos.get(pisoEscolhido);

        // Para Estacionamento não há seleção de espaço individual
        if (espacos.size() == 1 && espacos.get(0).getEspaco().isEmpty()) {
            System.out.println("Local selecionado: " + espacos.get(0));
            return espacos.get(0);
        }

        // 3. Selecionar espaço
        System.out.println("\nEspaços disponíveis em " + pisoEscolhido + ":");
        for (int i = 0; i < espacos.size(); i++) {
            System.out.println((i + 1) + " - " + espacos.get(i).getEspaco() + " (" + espacos.get(i).getTipo() + ")");
        }
        System.out.print("Opção: ");
        int opcaoEspaco = scanner.nextInt();
        scanner.nextLine();

        if (opcaoEspaco < 1 || opcaoEspaco > espacos.size()) {
            System.out.println("Espaço inválido.");
            return null;
        }

        Local selecionado = espacos.get(opcaoEspaco - 1);
        System.out.println("Local selecionado: " + selecionado);
        return selecionado;
    }
}