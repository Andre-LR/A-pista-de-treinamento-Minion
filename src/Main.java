import java.util.Scanner;

import entidades.Sistema;

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Sistema sistema = new Sistema();

        System.out.println("Qtd minions: ");
        int qtdMinions = in.nextInt();

        //Inicio contagem tempo
        long tempoInicio = System.currentTimeMillis();

        sistema.setQtdMinions(qtdMinions);
        sistema.lerArquivo();

        //Final contagem tempo
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal-tempoInicio;
        System.out.println("Tempo Total: "+(tempoTotal));
    }
}