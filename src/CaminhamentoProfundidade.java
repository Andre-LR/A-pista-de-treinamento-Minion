public class CaminhamentoProfundidade {
	private boolean[] listaVerticesVisitados;
	private int[] edgeTo;
	private int verticeInicial; 
	private int tempo = 0; //tempo total executado
	
	public CaminhamentoProfundidade(Graph grafo, int vInicial) {
		this.verticeInicial = vInicial;
		listaVerticesVisitados = new boolean[grafo.getQtdVertices()]; // inicializa todos os vértices com false(não visitados)
		edgeTo = new int[grafo.getQtdVertices()]; // e com zero
		dfs(grafo, vInicial);
	}

	private void dfs(Graph grafo, int posicaoVertice) {
		Vertice v = grafo.getVerticeDaPosicao(posicaoVertice);

		System.out.println("Entrei em " + posicaoVertice + "--> "+ v.getNome());

		//marca o vértice como visitado
		listaVerticesVisitados[posicaoVertice] = true;
		for (int posicaoAdjacente : grafo.getListaPosicaoVerticesAdj(posicaoVertice)) {
			
			Vertice vAdj = grafo.getVerticeDaPosicao(posicaoAdjacente);
			System.out.println("Adjacente: " + posicaoAdjacente + "--> "+ vAdj.getNome());
			if (!listaVerticesVisitados[posicaoAdjacente]) {
				edgeTo[posicaoAdjacente] = posicaoVertice;
				dfs(grafo, posicaoAdjacente);
			}
		}
		System.out.println("Terminei " + posicaoVertice + "--> "+ v.getNome());
	}

	
	public static void main(String[] args) {
		// Graph G = new Graph(4);
		// G.addEdge(0, 1);
		// G.addEdge(1, 2);
		// G.addEdge(0, 3);
		Graph g = new Graph();
		Vertice v0 = new Vertice("V0", 0);
        Vertice v1 = new Vertice("V1", 1);
        Vertice v2 = new Vertice("V2", 2);
        Vertice v3 = new Vertice("V3", 3);
        Vertice v4 = new Vertice("V4", 4);
        g.addVertice(v0);
        g.addVertice(v1);
        g.addVertice(v2);
        g.addAresta(v0, v1);
		g.addAresta(v1, v2);
		g.addAresta(v0, v2);
		//In in = new In(args[0]);
		//StdOut.println(g);
		CaminhamentoProfundidade cp = new CaminhamentoProfundidade(g, 0);
		/*System.out.println("Caminhos:");
		for (int v = 1; v < g.getQtdVertices(); v++) {
			if (cp.hasPathTo(v)) {
				System.out.print(v + ": ");
				for (int w : cp.pathTo(v)) {
					System.out.print(w + " ");
				}
				System.out.println();
			}
		}*/
	}
}
