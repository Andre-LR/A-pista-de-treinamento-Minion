package entities;

import java.util.ArrayList;

public class Graph {
    public ArrayList<Vertice> listaVertices = new ArrayList<>();

    public Graph (){}

    public ArrayList<Vertice> getListaVertices(){
        return listaVertices;
    }

    //Adicionar novo vértice no Grafo
    public void addVertice(Vertice vertice) {
        ArrayList<Vertice> verticesAdj = new ArrayList<Vertice>();
        vertice.setVerticesAdj(verticesAdj);
        listaVertices.add(vertice);
    }

    //Remover vértice do Grafo
    public void removerVertice(Vertice vertice) {
        for (Vertice vAdj : vertice.getVerticesAdj()) {
            vAdj.getVerticesAdj().remove(vertice);
        }
        listaVertices.remove(vertice);
    }
    
    //Adiciona Aresta
    public void addAresta(Vertice vertice01, Vertice vertice02) {
        vertice01.getVerticesAdj().add(vertice02);
        vertice02.getVerticesAdj().add(vertice01);
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
    
    //Retornar lista de vertices adjacentes de um vértice
    public String getVerticesAdj(String nomeVertice) {
        for (Vertice vertice : listaVertices){
            if(vertice.getNome().equals(nomeVertice)){
               return vertice.getVerticesAdjacentes();
            }
        }
        return "Nenhum vértice encontrado com esse nome";
    }

    //retorna todos os vértices do grafo e seus vértices adjacentes
    public String getAllVertices(){
        String txt = "";

        for (Vertice vertice : listaVertices) {
            vertice.getVerticesAdjacentes();
       }
        return txt;
    }
}
