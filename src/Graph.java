import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    public ArrayList<Vertice> listaVertices = new ArrayList<>();
    private int qtdVertices = 0;
    //vertices que podem ser executados, pois não possuem dependencias
    private ArrayList<Vertice> disponiveis = new ArrayList<>();
    //vertices que estão em andamento, vértices que estão sendo executados pelos minions
    private ArrayList<Vertice> verticesEmAndamento = new ArrayList<>();

    public Graph (){
        for (Vertice vertice : listaVertices) {
            if(vertice.getListaDependencia().isEmpty()){
                disponiveis.add(vertice);
            }
        }
        Collections.sort(disponiveis);
        
    }

    


    public ArrayList<Vertice> getListaVertices(){
        return listaVertices;
    }

    public int getQtdVertices(){
        return qtdVertices;
    }

    public Vertice getVerticeDaPosicao(int posicao){
        for (Vertice vertice : listaVertices) {
            if(vertice.getPosicao() == posicao){
                return vertice;
            }
        }
        return null;
    }

    //Atualiza os vértices disponiveis para serem executados verificando cada um se possuem algum vertice na sua lista de dependencia
    public void atualizaDisponiveis(){
        for (Vertice vertice : listaVertices) {
            //Se o vertice analisado já não está na lista dos disponiveis
            //Verifica se ele tem algum vertice na sua lista de dependencia
            //Se estiver vazia, ele passa a integrar os vertices disponiveis
            if(!disponiveis.contains(vertice)){
                if(vertice.getListaDependencia().isEmpty()){
                    disponiveis.add(vertice);
                }
            }
        }
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
    
    //Printa lista de vertices adjacentes de um vértice
    public String verticesAdjacentesToString(String nomeVertice) {
        for (Vertice vertice : listaVertices){
            if(vertice.getNome().equals(nomeVertice)){
               return vertice.verticesAdjacentesToString();
            }
        }
        return "Nenhum vértice encontrado com esse nome";
    }

    //Retornar lista da posição dos vertices adjacentes de um vértice
    public ArrayList<Integer> getListaPosicaoVerticesAdj(int posicaovertice) {
        for (Vertice vertice : listaVertices){
            if(vertice.getPosicao() == posicaovertice){
               return vertice.getListaPosicaoVerticesAdj();
            }
        }
        return null;
    }

    //retorna todos os vértices do grafo e seus vértices adjacentes
    public String getAllVertices(){
        String txt = "";

        for (Vertice vertice : listaVertices) {
            txt = txt + vertice.verticesAdjacentesToString();
       }
        return txt;
    }

    public void topoSort() {

        // Fetching the number of nodes in the graph
        int V = listaVertices.size();

        // List where we'll be storing the topological order
        ArrayList<Vertice> order = new ArrayList<> ();
    
        // Map which indicates if a node is visited (has been processed by the algorithm)
        Map<Vertice, Boolean> visited = new HashMap<>();
        for (Vertice v: listaVertices)
            visited.put(v, false);
    
        // We go through the nodes using topoSortRecursivo
        for (Vertice v: listaVertices) {
            if (!visited.get(v))
                topoSortRecursivo(v, visited, order);
        }
    
        // We reverse the order we constructed to get the
        // proper toposorting
        Collections.reverse(order);
        for (Vertice vertice : order) {
            System.out.println(vertice.getNome());
        }
        //System.out.println(order);
    }
    
    public void topoSortRecursivo(Vertice v, Map<Vertice, Boolean> visited, ArrayList<Vertice> order) {
        // Mark the current node as visited
        visited.replace(v, true);
    
        // We reuse the algorithm on all adjacent nodes to the current node
        for (Vertice verticeAdj : v.getVerticesAdj()) {
            if (!visited.get(verticeAdj))
                topoSortRecursivo(verticeAdj, visited, order);
        }
    
        // Put the current node in the array
        order.add(v);
    }
}
