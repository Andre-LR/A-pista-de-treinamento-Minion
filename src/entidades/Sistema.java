package entidades;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Sistema {
    private int qtdMinionsDefinida = 1;
    private int melhorTempo=0;

    Scanner in = new Scanner(System.in);
    //Cria o grafo com qtd minions zerada
    Graph graph = new Graph(0);
    
    public void setQtdMinions(int minions){
        this.qtdMinionsDefinida = minions;
    }

    public boolean ignoraPrimeiraUltimaLinha(String valor){
        if(valor.contains("digraph G {") || valor.contains("} ")){
            return true;
        }else{
            return false;
        }
        
    }

    public void lerArquivoMinionFixo(){    
        //Atualizar qtd minions do grafo            
        graph.setQtdMinions(qtdMinionsDefinida);

        System.out.println("Escolha um arquivo de obstáculos para ser lido");
        System.out.println("Exemplos de arquivo:\ncinco.txt\ndez.txt\ncinquenta.txt\ncem.txt\nquinhentos.txt\nmil.txt");

        String path = in.next();
        
        System.out.println("\nLEITURA DOS DADOS DO ARQUIVO");

        try{
            FileReader arquivo = new FileReader(path);
            BufferedReader lerArquivo = new BufferedReader(arquivo);

            String linha = lerArquivo.readLine();
            

            //CRIAÇÃO DO GRAFO
            while(linha != null){

                //IGNORA LINHAS QUE NÃO SÃO VÉRTICE
                if(linha.contains("digraph G {")){
                    linha = lerArquivo.readLine();
                }else if(linha.contains("}")){
                    break;
                }

                //System.out.printf("%s\n",linha); 
            
                String vertices[] = linha.split(" -> ");                    
                
                String nome_VerticePai = vertices[0].split("_")[0];
                int tempo_VerticePai = Integer.parseInt(vertices[0].split("_")[1]);

                String nome_VerticeFilho = vertices[1].split("_")[0];
                int tempo_VerticeFilho = Integer.parseInt(vertices[1].split("_")[1]);

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
            System.out.println("\nMONTAGEM DO GRAFO FINALIZADA...");

            //Alimenta a lista de vértices disponiveis
            graph.atualizaDisponiveis();
            //Alimenta a lista de vértices em andamento
            graph.atualizaVerticesEmAndamento();
            //Define o vértice inicial para começar o caminhamento pelo grafo
            int verticeInicial = graph.getPosicaoPrimeiroVerticeEmAndamento(); 
            //Executa o caminhamento do GRAFO pelo VERTICE INICIAL
            ExecutaCaminhamento cp = new ExecutaCaminhamento(graph, verticeInicial);
        }
    }



    public void lerArquivoMelhorDesemepnho(){    

        System.out.println("Escolha um arquivo de obstáculos para ser lido");
        System.out.println("Exemplos de arquivo:\ncinco.txt\ndez.txt\ncinquenta.txt\ncem.txt\nquinhentos.txt\nmil.txt");

        String path = in.next();
        
        System.out.println("\nLEITURA DOS DADOS DO ARQUIVO");

        while(true){
            Graph graph = new Graph(qtdMinionsDefinida);

            //Atualizar qtd minions do grafo            
//            graph.setQtdMinions(qtdMinionsDefinida);

            try{
                FileReader arquivo = new FileReader(path);
                BufferedReader lerArquivo = new BufferedReader(arquivo);

                String linha = lerArquivo.readLine();


                //CRIAÇÃO DO GRAFO
                while(linha != null){

                    //IGNORA LINHAS QUE NÃO SÃO VÉRTICE
                    if(linha.contains("digraph G {")){
                        linha = lerArquivo.readLine();
                    }else if(linha.contains("}")){
                        break;
                    }

                    //System.out.printf("%s\n",linha); 
                
                    String vertices[] = linha.split(" -> ");                    

                    String nome_VerticePai = vertices[0].split("_")[0];
                    int tempo_VerticePai = Integer.parseInt(vertices[0].split("_")[1]);

                    String nome_VerticeFilho = vertices[1].split("_")[0];
                    int tempo_VerticeFilho = Integer.parseInt(vertices[1].split("_")[1]);

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
                System.out.println("\nMONTAGEM DO GRAFO FINALIZADA...");

                //Alimenta a lista de vértices disponiveis
                graph.atualizaDisponiveis();

              /*  for (Vertice vertice : graph.getVerticesDisponiveis()) {
                    System.out.println("\n" + vertice.getNome() + ": " + vertice.getTempo());
                }*/
                                    
                //Alimenta a lista de vértices em andamento
                graph.atualizaVerticesEmAndamento();
                //Define o vértice inicial para começar o caminhamento pelo grafo
                int verticeInicial = graph.getPosicaoPrimeiroVerticeEmAndamento(); 
                //Executa o caminhamento do GRAFO pelo VERTICE INICIAL
                ExecutaCaminhamento cp = new ExecutaCaminhamento(graph, verticeInicial);

        
                  if(melhorTempo > cp.getTempoTotal() || melhorTempo == 0){
                        melhorTempo = cp.getTempoTotal();                                  
                  }else if(melhorTempo == cp.getTempoTotal()){
                      qtdMinionsDefinida = qtdMinionsDefinida -1;
                    System.out.println("\n\nQtd Minions: " + qtdMinionsDefinida);  
                    break;
                  }                        
                  
                qtdMinionsDefinida++;

            }
        }
    }
}

