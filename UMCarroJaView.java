import java.io.Serializable;

import static java.lang.System.out;

public class UMCarroJaView implements Serializable {

    // Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE
    // Base separator
    private static final String SEPARATOR = "-";
    // Separator length
    private static final int LEN_SEP = 80;
    // Reset color
    private static final String RESET = "\033[0m";

    public UMCarroJaView() {
        // Faz nada
    }

    public static void clearScreen() {
        out.print("\033[H\033[2J");  
        out.flush();  
    }
    
    public static void menuPrincipal() {
        printHeader("UMCarroJa");
        imprimeLinha("1: Carregar ficheiro em formato binário");
        imprimeLinha("2: Guardar informação em formato binário");
        imprimeLinha("3: Carregar ficheiro de logs");
        imprimeLinha("4: Login como Proprietário");
        imprimeLinha("5: Login como Cliente");
        imprimeLinha("6: Registar como Proprietário");
        imprimeLinha("7: Registar como Cliente");
        imprimeLinha("8: Obter o top 10 clientes que mais utilizaram o sistema");
        imprimeLinha("0: Sair");
        printFooter();
    }

    public static void menuCliente() {
        printHeader("Menu Cliente");
        imprimeLinha("1: Solicitar aluguer");
        imprimeLinha("2: Verificar histórico de alugueres(entre datas)");
        imprimeLinha("3: Verificar estatísticas");
        imprimeLinha("4: Alterar password");
        imprimeLinha("5: Alterar morada");
        imprimeLinha("6: Indicar classificacao");
        imprimeLinha("7: Solicitar aluguer - ADVANCED VERSION");
        imprimeLinha("0: Logout");
        printFooter();
    }

    public static void menuProprietario() {
        printHeader("Menu Proprietário");
        imprimeLinha("1: Verificar pedidos de aluguer em espera");
        imprimeLinha("2: Sinalizar disponibilidade de veículo");
        imprimeLinha("3: Abastecer veículo");
        imprimeLinha("4: Alterar o preço/KM de veículo");
        imprimeLinha("5: Adicionar veículo");
        imprimeLinha("6: Remover veículo");
        imprimeLinha("7: Verificar histórico de alugueres(entre datas)");
        imprimeLinha("8: Verificar estatísticas");
        imprimeLinha("9: Alterar password");
        imprimeLinha("10: Alterar morada");
        imprimeLinha("11: Indicar classificacao");
        imprimeLinha("12: Indicar classificacao da viatura");
        imprimeLinha("13: Verificar historico de aluguer em veiculo(entre datas)");
        imprimeLinha("14: Verificar os meus veiculos");
        imprimeLinha("0: Logout");
        printFooter();
    }
    
    public static void menuSolAluguer() {
        printHeader("Solicitar Veiculo");
        imprimeLinha("1: Veiculo mais proximo");
        imprimeLinha("2: Veiculo mais barato");
        imprimeLinha("3: Veiculo mais barato num raio de X");
        imprimeLinha("4: Veiculo especifico");
        imprimeLinha("5: Veiculo com autonomia minima de X");
        imprimeLinha("0: Retroceder");
        printFooter();
    }

    public static void menuSolAdvanced() {
        imprimeLinha("1: Proxima pagina");
        imprimeLinha("2: Retroceder pagina");
        imprimeLinha("3: Mostrar so eletricos");
        imprimeLinha("4: Mostrar so hibridos");
        imprimeLinha("5: Mostrar so gasolina");
        imprimeLinha("6: Mostrar so gasoleo");
        imprimeLinha("7: Ordenar por maior proximidade");
        imprimeLinha("8: Ordenar por preço crescente");
        imprimeLinha("9: Ordenar por autonomia decrescente");
        imprimeLinha("10: Fixar autonomia minima");
        imprimeLinha("11: Fixar distancia maxima");
        imprimeLinha("12: Reset");
        imprimeLinha("0: Cancelar");
        printFooter();
    }
    
    public static void listOptions() {
        imprimeLinha("1: Proxima pagina");
        imprimeLinha("2: Retroceder pagina");
        imprimeLinha("0: Cancelar");
        printFooter();
    }
    
    public static void imprime(String text) {
        out.print(text);
    }

    public static void imprime(String text, String color) {
        out.print(color + text + RESET);
    }

    public static void imprimeLinha(String text) {
        out.println(text);
    }

    public static void imprimeLinha(String text, String color) {
        out.println(color + text + RESET);
    }

    public static void printHeader(String text) {
        printHeader(text, RED);
    }

    public static void printHeader(String text, String color) {
        int i, len1 = (LEN_SEP - text.length()) / 2;
        int len2 = LEN_SEP - len1 - text.length();
        StringBuilder sb = new StringBuilder();

        sb.append(repeat(SEPARATOR, len1 >= 0 ? len1 : 0));
        sb.append(color).append(text).append(RESET);
        sb.append(repeat(SEPARATOR, len2 >= 0 ? len2 : 0));

        imprimeLinha(sb.toString());
    }

    public static void printFooter() {
        imprimeLinha(repeat(SEPARATOR, LEN_SEP));
    }

    private static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(str);
        }

        return sb.toString();
    }
}
