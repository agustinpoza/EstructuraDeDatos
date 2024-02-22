package TP8Grafos;
import TDAGrafoVD.*;
import TDALista.*;

public class Tester<V,E> {
	public static void main(String s[]) {
		
		DFS<Character,Float> d = new DFS<>();
		try {
			
			Graph<Character,Float> g = new GrafoDecorado<>();
			
			Vertex<Character> v1 = g.insertVertex('a');
			Vertex<Character> v2 = g.insertVertex('b');
			Vertex<Character> v3 = g.insertVertex('c');
			Vertex<Character> v4 = g.insertVertex('d');
			Vertex<Character> v5 = g.insertVertex('e');
			Vertex<Character> v6 = g.insertVertex('f');
			Vertex<Character> v7 = g.insertVertex('g');
			Vertex<Character> v8 = g.insertVertex('h');
			Vertex<Character> v9 = g.insertVertex('i');
			Vertex<Character> v10 = g.insertVertex('j');
			Vertex<Character> v11 = g.insertVertex('k');
			Vertex<Character> v12 = g.insertVertex('l');
			Vertex<Character> v13 = g.insertVertex('m');
			Vertex<Character> v14 = g.insertVertex('n');
			Vertex<Character> v15 = g.insertVertex('o');
			Vertex<Character> v16 = g.insertVertex('p');
			
			Edge<Float> e1 = g.insertEdge(v1, v2, 1f);
			Edge<Float> e2 = g.insertEdge(v1, v3, 1f);
			Edge<Float> e3 = g.insertEdge(v1, v4, 1f);
			Edge<Float> e4 = g.insertEdge(v2, v4, 1f);
			Edge<Float> e5 = g.insertEdge(v2, v3, 1f);
			
			Edge<Float> e6 = g.insertEdge(v2, v9, 1f);
			Edge<Float> e7 = g.insertEdge(v9, v8, 10f);
			Edge<Float> e8 = g.insertEdge(v8, v12, 1f);
			Edge<Float> e9 = g.insertEdge(v12, v13, 1f);
			Edge<Float> e10 = g.insertEdge(v9, v5, 3f);
			
			Edge<Float> e11 = g.insertEdge(v5, v6, 1f);
			Edge<Float> e12 = g.insertEdge(v5, v7, 3f);
			Edge<Float> e13 = g.insertEdge(v6, v7, 1f);
			Edge<Float> e14 = g.insertEdge(v7, v11, 1f);
			Edge<Float> e15 = g.insertEdge(v11, v10, 1f);
			
			Edge<Float> e16 = g.insertEdge(v8, v10, 1f);
			
			
			for(Vertex<Character> v : g.vertices()) {
				System.out.print(v.element());
			}
			System.out.println();
			for(Edge<Float> e : g.edges()) {
				System.out.print(g.endvertices(e)[0].element()+"-"+e.element()+"-"+g.endvertices(e)[1].element());
				System.out.println();
			}
			System.out.println();
			
			PositionList<Character> lista = d.dfsShell(g);
			for(Character vertice : lista) {
				System.out.print(vertice);
			}
			
			System.out.println();
			
			PositionList<Character> lista2 = d.recorridoBFS(g);
			for(Character vertice : lista2) {
				System.out.print(vertice);
			}
			System.out.println();
			PositionList<Vertex<Character>> lista3 = new ListaDE<>();
			d.camino(g, v3, v2, lista3);
			for(Vertex<Character> vertice : lista3) {
				System.out.print(vertice.element());
			}
			System.out.println();
			PositionList<Vertex<Character>> lista4 = d.caminoMinimo(g, v9, v7);
			for(Vertex<Character> vertice : lista4) {
				System.out.print(vertice.element());
			}
			System.out.println();
			System.out.println(d.esConexoDFS(g));
			
		}catch(Exception e) {}
	}
}
