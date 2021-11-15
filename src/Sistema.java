import entities.GrafoObstaculo;
import entities.Graph;
import entities.Vertice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    Scanner in = new Scanner(System.in);   
    Graph graph = new Graph(); 
    List<GrafoObstaculo> listaObstaculos = new ArrayList<>();
    
    public boolean ignoraPrimeiraLinha(String valor){
        try {
            "digraph G {".matches("digraph G {");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String showObstaculos(){
        String txt="";
        for (GrafoObstaculo obstaculo : listaObstaculos) {
            txt = txt + obstaculo.toString();
        }

        return txt;
    }

    public void lerArquivo(){                

        System.out.println("Escolha um arquivo para ser lido");
        System.out.println("cem.txt\ncinco.txt\ndez.txt\n");

        String path = in.next();
        
        try{
            FileReader arquivo = new FileReader(path);
            BufferedReader lerArquivo = new BufferedReader(arquivo);

            String linha = lerArquivo.readLine();

            if(ignoraPrimeiraLinha(linha)== true){
                linha = lerArquivo.readLine();
            }
                while(linha != null){
                    System.out.printf("%s\n",linha); 
                
                    String vertices[] = linha.split(" -> ");                    
                    
                    String nome_VerticePai = vertices[0].split("_")[0];
                    int tempo_VerticePai = Integer.parseInt(vertices[0].split("_")[1]);

                    String nome_VerticeFilho = vertices[1].split("_")[0];
                    int tempo_VerticeFilho = Integer.parseInt(vertices[1].split("_")[1]);

                    //GrafoObstaculo obstaculo = new GrafoObstaculo(verticePai, VerticeFilho);
                    //listaObstaculos.add(obstaculo);

                    //Cria os vértices
                    Vertice verticePai = new Vertice(nome_VerticePai, tempo_VerticePai);
                    Vertice verticeFilho = new Vertice(nome_VerticeFilho, tempo_VerticeFilho);

                    //Adiciona os vértices no grafo
                    //Se o vértice já existir, não adiciona novamente
                    boolean verticeExistentePai = false;
                    for(Vertice v : graph.getListaVertices()){
                        if(v.getNome().equals(verticePai.getNome())){
                            verticeExistentePai = true;
                            
                            //Atualiza o "novo" vertice apontando para o vertice ja existente
                            for (Vertice vertice : graph.listaVertices) {
                                if(vertice.getNome().equals(verticePai.getNome())){
                                    verticePai = vertice;
                                }
                            }
                            break;
                        }
                    }
                    boolean verticeExistenteFilho = false;
                    for(Vertice v : graph.getListaVertices()){
                        if(v.getNome().equals(verticeFilho.getNome())){
                            verticeExistenteFilho = true;

                            //Atualiza o "novo" vertice apontando para o vertice ja existente
                            for (Vertice vertice : graph.listaVertices) {
                                if(vertice.getNome().equals(verticeFilho.getNome())){
                                    verticeFilho = vertice;
                                }
                            }
                            break;
                        }
                    }
                    if(!verticeExistentePai){
                        graph.addVertice(verticePai);
                        
                    }
                    if(!verticeExistenteFilho){
                        graph.addVertice(verticeFilho);
                        
                    }

                    //Adiciona a aresta entre os vértices criados
                    graph.addAresta(verticePai, verticeFilho);

                    //próxima linha do arquivo
                    linha = lerArquivo.readLine();
            }                   
            arquivo.close();
            
        }catch (IOException e){
            System.err.println("Erro no carregamento: " + e.getMessage());
        }
        finally {
            //System.out.println(graph.getAllVertices());
            System.out.println(graph.getVerticesAdj("mhaik"));
            System.out.println("Operação finalizada...");
        }
    }
}

