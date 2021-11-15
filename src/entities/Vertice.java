package entities;

import java.util.ArrayList;

public class Vertice {
    private String nome;
    private int tempo;
    private ArrayList<Vertice> verticesAdj = new ArrayList<>();

    public Vertice(String nome, int tempo) {
        this.nome = nome;
        this.tempo = tempo;
    }

    public String getNome() {
        return nome;
    }
    
    public int getTempo() {
        return tempo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public ArrayList<Vertice> getVerticesAdj() {
        return verticesAdj;
    }

    public void setVerticesAdj(ArrayList<Vertice> verticesAdj) {
        this.verticesAdj = verticesAdj;
    }

    public String getVerticesAdjacentes(){
        String txt = "["+ nome + "] = ";

        for (Vertice vertice : verticesAdj) {
            txt = txt + "[" + vertice.getNome() + "]" + "; ";
        }
        return txt;
    }
}
