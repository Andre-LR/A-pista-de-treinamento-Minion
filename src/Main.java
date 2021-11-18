import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Sistema sistema = new Sistema();

        System.out.println("Qtd minions: ");
        int qtdMinions = in.nextInt();

        sistema.setQtdMinions(qtdMinions);
        sistema.lerArquivo();
        //System.out.println(sistema.showObstaculos());

        /*Graph g = new Graph();
        Vertice v1 = new Vertice("V1", 1);
        Vertice v2 = new Vertice("V2", 2);
        Vertice v3 = new Vertice("V3", 3);
        Vertice v4 = new Vertice("V4", 4);
        Vertice v5 = new Vertice("V5", 5);
        g.addVertice(v1);
        g.addVertice(v2);
        g.addVertice(v3);
        g.addAresta(v1, v2);
        System.out.println(v2.getVerticesAdjacentes());*/
    }
}