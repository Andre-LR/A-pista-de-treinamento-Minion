package entidades;
public class ExecutaCaminhamento {
	//tempo total executado
	private int tempoTotal = 0; 

	public ExecutaCaminhamento(Graph grafo, int posicaoVertice) {
		
		//Executa caminhamento enquanto haver vértices EM EXECUÇÂO
		while(grafo.getVerticesEmAndamento().size() > 0){

			//Cria variavel menor tempo e Vertice com menor tempo
			int menorTempo = grafo.getPrimeiroVerticeEmAndamento().getTempo();
			Vertice verticeMenorTempo = null;

			//Descobre qual vertice tem o menor tempo
			for (Vertice vertice : grafo.getVerticesEmAndamento()) {
				System.out.println("\nO vértice " + vertice.getNome() + " está em execução");

				if(vertice.getTempo() <= menorTempo){
					menorTempo = vertice.getTempo();
					verticeMenorTempo = vertice;
				}
			}

			//O tempo de execução do caminhamento recebe + tempo do vertice de menor tempo
			tempoTotal = tempoTotal + menorTempo;

			//Retira o vértice executado da lista de VerticeEmAndamento e coloca na lista de vertices executados
			System.out.println("Vértice " + verticeMenorTempo.getNome() + " executado com sucesso!!!!");
			grafo.getVerticesEmAndamento().remove(verticeMenorTempo);
			grafo.getVerticesExecutados().add(verticeMenorTempo);

			//Verifica nos vertices adjacentes do vertice que foi executado se ele é um dos vertices "Pai"
			//Se for, retira ele da lista de dependencias
			for (Vertice vAdj : verticeMenorTempo.getVerticesAdj()) {
				if(vAdj.getListaDependencia().contains(verticeMenorTempo)){
					vAdj.getListaDependencia().remove(verticeMenorTempo);
				}
			}

			
			//Atualiza o tempo que falta executar dos vértices remanescentes que estavam sendo executados
			//Pega o tempo de cada vertice remanescente, e subtrai pelo menor tempo(tempo do vertice que foi retirado da lista de executados)
			for (Vertice vertice : grafo.getVerticesEmAndamento()) {
				int tempoAtual = vertice.getTempo();
				int tempoRestante = tempoAtual - menorTempo;
				vertice.setTempo(tempoRestante);
			}
			
			//Atualiza os vértices disponiveis
			grafo.atualizaDisponiveis();
			//Atualiza vertices em andamento
			grafo.atualizaVerticesEmAndamento();
			//Executa novamente enquanto haver vértices EM EXECUÇÂO
		}
		System.out.println("Caminhamento Finalizadp...");
		System.out.println("Tempo total de execução da pista: " + tempoTotal);
	}

}
