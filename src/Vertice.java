import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class Vertice implements Comparable<Vertice>{
    private String nome;
    private int tempo;
    //Vertices adjacentes
    private ArrayList<Vertice> verticesAdj = new ArrayList<>();
    //Posição do vértice no grafo
    private int posicao;
    //Vértices que precisam ser destruidos antes de ficar habilitado
    private ArrayList<Vertice> listaDependencias = new ArrayList<>(); 

    public Vertice(String nome, int tempo) {
        this.nome = nome;
        this.tempo = tempo;
    }

    public ArrayList<Vertice> getListaDependencia() {
        return listaDependencias;
    }

    public void setListaDependencia(Vertice verticePai) {
        listaDependencias.add(verticePai);
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

    public void setTempo(int novoTempo){
        this.tempo = novoTempo;
    }
    
    public ArrayList<Vertice> getVerticesAdj() {
        return verticesAdj;
    }

    public void setVerticesAdj(ArrayList<Vertice> verticesAdj) {
        this.verticesAdj = verticesAdj;
    }

    public void atualizaTempoRestante(int tempoExecutado){
        tempo = tempo-tempoExecutado;
    }

    public String verticesAdjacentesToString(){
        String txt = "["+ nome + "] = ";

        for (Vertice vertice : verticesAdj) {
            txt = txt + "[" + vertice.getNome() + "]" + "; ";
        }
        return txt;
    }

    //Método para ordenar o Objeto Vértice pelo atributo NOME
    @Override
    public int compareTo(Vertice v) {
        Collator cot = Collator.getInstance(new Locale("pt","BR"));
        if(v != null){
            return cot.compare(this.getNome(),v.getNome());
        }else{
            return 0;
        }
    }
}
