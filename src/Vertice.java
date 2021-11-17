import java.util.ArrayList;

public class Vertice implements Comparable<Vertice>{
    private String nome;
    private int tempo;
    private ArrayList<Vertice> verticesAdj = new ArrayList<>();
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

    

  
     /*   
    @Override public String compareTo(Vertice outroVertice) {
        Collections.sort(disponiveis, new Comparator<Vertice> () {  
            public String compare (Vertice v1, Vertice v2) {  
                return v1.getNome().toUpperCase().compareTo (v2.getNome().toUpperCase());  
            }  
        }); 
    }//implementação }*/
        
    
}
