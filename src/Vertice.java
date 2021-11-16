import java.util.ArrayList;

public class Vertice {
    private String nome;
    private int tempo;
    private ArrayList<Vertice> verticesAdj = new ArrayList<>();
    private int posicao;

    public Vertice(String nome, int tempo) {
        this.nome = nome;
        this.tempo = tempo;
    }

    public int getPosicao(){
        return posicao;
    }

    public void setPosicao(int posicao){
        this.posicao = posicao;
    }

    public String getNome() {
        return nome;
    }
    
    public int getTempo() {
        return tempo;
    }
    
    public ArrayList<Vertice> getVerticesAdj() {
        return verticesAdj;
    }

    public ArrayList<Integer> getListaPosicaoVerticesAdj() {
        ArrayList<Integer> posAdj = new ArrayList<>();
        for (Vertice vertice : verticesAdj) {
            posAdj.add(vertice.getPosicao());
        }
        return posAdj;
    }

    public void setVerticesAdj(ArrayList<Vertice> verticesAdj) {
        this.verticesAdj = verticesAdj;
    }

    public String verticesAdjacentesToString(){
        String txt = "["+ nome + "] = ";

        for (Vertice vertice : verticesAdj) {
            txt = txt + "[" + vertice.getNome() + "]" + "; ";
        }
        return txt;
    }
}
