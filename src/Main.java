import java.util.Scanner;

import entidades.Sistema;
//ORIGINAAAAAAAAAAAAAAAAAL//
public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Sistema sistema = new Sistema();
        
        System.out.println("Descobrir o resultado mais efetivo [1]\nDescobrir o resultado de uma quantidade especifica de minions [2] ");
       
        int r = in.nextInt();

        if(r == 1) {
            sistema.lerArquivoMelhorDesemepnho();
        }else if(r == 2){
            System.out.println("Qtd minions: ");
            int qtdMinions = in.nextInt();
    
            sistema.setQtdMinions(qtdMinions);
            sistema.lerArquivoMinionFixo();
        }
    }
}