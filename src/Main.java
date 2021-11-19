import java.util.Scanner;

import entidades.Sistema;

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Sistema sistema = new Sistema();

        System.out.println("Qtd minions: ");
        int qtdMinions = in.nextInt();

        sistema.setQtdMinions(qtdMinions);
        sistema.lerArquivo();
    }
}