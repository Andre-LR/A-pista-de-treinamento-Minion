package entities;

public class GrafoObstaculo {
    private String obstaculo;
    private String dependente;
    
    public GrafoObstaculo( String obstaculo, String dependente){        
        this.obstaculo = obstaculo;
        this.dependente = dependente;
    }
    
    public String getObstaculo(){
        return this.obstaculo;
    }

    public String getDependente(){
        return this.dependente;
    }
    
    public void setObstaculo(String obstaculo){
        this.obstaculo = obstaculo;
    }

    public void setDependente(String dependente){
        this.dependente = dependente;
    }

    @Override
    public String toString() {
        return "\nObstaculo{" + "obstaculo=" + obstaculo + ", dependente=" + dependente + '}';
    }
}
