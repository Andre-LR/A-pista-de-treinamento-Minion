import java.util.ArrayList;
import java.util.Collections;

public class Graph {
    //Qtd de minions que irão executar a pista
    private int minions;
    //Todos os vértices do grafo
    public ArrayList<Vertice> listaVertices = new ArrayList<>();
    private int qtdVertices = 0;
    //vertices que podem ser executados, pois não possuem dependencias
    private ArrayList<Vertice> verticesDisponiveis = new ArrayList<>();
    //vertices que estão em andamento, vértices que estão sendo executados pelos minions
    private ArrayList<Vertice> verticesEmAndamento = new ArrayList<>();
    //vertices ja executados
    private ArrayList<Vertice> verticesExecutados = new ArrayList<>();

    //Costrutor do Grafo
    public Graph (int qtdMinions){
        this.minions = qtdMinions;
    }

    //Adicionar novo vértice no Grafo
    public void addVertice(Vertice vertice) {
        vertice.setPosicao(qtdVertices);
        ArrayList<Vertice> verticesAdj = new ArrayList<Vertice>();
        vertice.setVerticesAdj(verticesAdj);
        listaVertices.add(vertice);
        qtdVertices++;
    }

    //Remover vértice do Grafo
    public void removerVertice(Vertice vertice) {
        for (Vertice vAdj : vertice.getVerticesAdj()) {
            vAdj.getVerticesAdj().remove(vertice);
        }
        listaVertices.remove(vertice);
        qtdVertices--;
    }
    
    //Adiciona Aresta
    public void addAresta(Vertice vertice01, Vertice vertice02) {
        vertice01.getVerticesAdj().add(vertice02);
        vertice02.getVerticesAdj().add(vertice01);

        vertice02.setListaDependencia(vertice01);
    }

    //Remove Aresta
    public void removeAresta(Vertice vertice01, Vertice vertice02) {
        //verifica se há algum vertice adjacente
        //Se tiver, remove o vértice da lista
        if (vertice01.getVerticesAdj() != null)
            vertice01.getVerticesAdj().remove(vertice02);
        if (vertice02.getVerticesAdj() != null)
            vertice02.getVerticesAdj().remove(vertice01);
    }

    public void atualizaVerticesEmAndamento(){
        //A qtd de vertices em execução deve ser a msm qtd de minions na pista
        int qtdParaExecutar = minions;

        //Enquanto haver vagas em vertices andamento
        //Adicionar os primeiros vertices disponiveis em vertices andamento
        //Remove os vertices que passaram para "em andamento" da lista de disponiveis
        //Se haver mais minions do que vertices para executar, mandar parar após passar por toda a lista de disponiveis
        while(verticesEmAndamento.size() < qtdParaExecutar){
            if(verticesDisponiveis.size() <= 0){
                System.out.println("Não há vertices disponiveis no momento");
                break;
            }else{
                verticesEmAndamento.add(verticesDisponiveis.get(0));
                verticesDisponiveis.remove(0);
            }
        }
    }

    //Atualiza os vértices disponiveis em ordem alfabetica
    //Um vértice entra para a lista dos disponiveis se
    //O vertice analisado já não está na lista dos disponiveis
    //Nem na lista dos Em Execução
    //Nem na lista dos ja executados
    //Nem se possui algum vertice na sua lista de dependencia
    public void atualizaDisponiveis(){
        for (Vertice vertice : listaVertices) {
            //Verifica se ele tem algum vertice na sua lista de dependencia
            //Se estiver vazia, ele passa a integrar os vertices disponiveis
            if(!verticesDisponiveis.contains(vertice) && !verticesEmAndamento.contains(vertice) && !verticesExecutados.contains(vertice) ){
                if(vertice.getListaDependencia().isEmpty()){
                    verticesDisponiveis.add(vertice);
                }
            }
        }
        //Ordena em ordem alfabetica os vertices disponiveis
        Collections.sort(verticesDisponiveis);
    } 
    
    //Printa lista de vertices adjacentes de um vértice
    public String verticesAdjacentesToString(String nomeVertice) {
        for (Vertice vertice : listaVertices){
            if(vertice.getNome().equals(nomeVertice)){
               return vertice.verticesAdjacentesToString();
            }
        }
        return "Nenhum vértice encontrado com esse nome";
    }

    //retorna todos os vértices do grafo e seus vértices adjacentes
    public String getAllVertices(){
        String txt = "";

        for (Vertice vertice : listaVertices) {
            txt = txt + vertice.verticesAdjacentesToString();
       }
        return txt;
    }

    public int getPosicaoPrimeiroVerticeEmAndamento(){
        return verticesEmAndamento.get(0).getPosicao();
    }

    public Vertice getPrimeiroVerticeEmAndamento(){
        return verticesEmAndamento.get(0);
    }

    public ArrayList<Vertice> getVerticesEmAndamento(){
        return verticesEmAndamento;
    }

    public ArrayList<Vertice> getVerticesDisponiveis(){
        return verticesDisponiveis;
    }

    public ArrayList<Vertice> getVerticesExecutados(){
        return verticesExecutados;
    }

    public ArrayList<Vertice> getListaVertices(){
        return listaVertices;
    }

    public void setQtdMinions(int minions){
        this.minions = minions;
    }
}
