import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
    }

    public int escolherForma() {
        System.out.println("===== PREENCHIMENTO DE FORMAS =====");
        System.out.println("Escolha a forma que deseja pintar:");
        System.out.println("1 - Quadrado");
        System.out.println("2 - Triângulo");
        System.out.println("3 - Hexágono");
        System.out.println("4 - Círculo");
        System.out.print("Digite a opção: ");
        return scanner.nextInt();
    }

    public int escolherEstrutura() {
        System.out.println();
        System.out.println("Escolha a estrutura:");
        System.out.println("1 - FIFO (Fila)");
        System.out.println("2 - LIFO (Pilha)");
        System.out.print("Digite a opção: ");
        return scanner.nextInt();
    }
}