package TP8Grafos;

import TDAMap.InvalidKeyException;

import TDAGrafoVD.*;
import TDALista.*;
import TDACola.*;

public class DFS<V, E> {

	private final static Object VISITADO = new Object();
	private final static Object NO_VISITADO = new Object();
	private final static Object ESTADO = new Object();
	
	//Recorrido DFS
	public PositionList<V> dfsShell(Graph<V,E> g) throws InvalidKeyException, InvalidVertexException, InvalidEdgeException {
		PositionList<V> l = new ListaDE<>();
		for(Vertex<V> v : g.vertices()) {
			v.put(ESTADO, NO_VISITADO);
		}
		for(Vertex<V> v : g.vertices()) {
			if(v.get(ESTADO) == NO_VISITADO)
				dfs(g,v,l);
		}
		return l;
	}
	private void dfs(Graph<V,E> g, Vertex<V> v,PositionList<V> l) throws InvalidKeyException, InvalidVertexException, InvalidEdgeException {
		//hacemos el procesamiento de V
		System.out.print(v.element());
		v.put(ESTADO, VISITADO);
		Iterable<Edge<E>> adyacentes = g.incidentEdges(v);
		for(Edge<E> e : adyacentes) {
			Vertex<V> w = g.opposite(v, e);
			if(w.get(ESTADO) == NO_VISITADO)
				dfs(g,w,l);
		}
		//aca iria el post-procesamiento de v si fuera necesario
	}
	
	
	
	//Encontrar un camino entre 2 vertices 
	public boolean camino(Graph<V,E> g, Vertex<V> origen, Vertex<V> destino, PositionList<Vertex<V>> camino) {
		for(Vertex<V> v : g.vertices()) {
			try {
				v.put(ESTADO, NO_VISITADO);
			} catch (InvalidKeyException e) {}
		}
		return hallarCamino(g,origen,destino,camino);
	}
	private boolean hallarCamino(Graph<V,E> g, Vertex<V> origen, Vertex<V> destino, PositionList<Vertex<V>> camino) {
		boolean encontre = true;
		try {
		origen.put(ESTADO, VISITADO);
		camino.addLast(origen);
		if(origen == destino) {
			return true;
		}
		else {
			Vertex<V> o = origen;
			for(Edge<E> e : g.incidentEdges(o)) {
				Vertex<V> v = g.opposite(o, e);
				if(v.get(ESTADO) == NO_VISITADO) {
					encontre = hallarCamino(g,v,destino,camino);
					if(encontre)
						return true;
				}
			}
		}
		}catch(InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {}
		return encontre;
	}
	
	
	
	//Recorrido BFS
	public PositionList<V> recorridoBFS(Graph<V,E> g) throws InvalidKeyException{
		PositionList<V> l = new ListaDE<>();
		for(Vertex<V> v : g.vertices()) {
			v.put(ESTADO, NO_VISITADO);
		}
		for(Vertex<V> v : g.vertices()) {
			if(v.get(ESTADO) == NO_VISITADO)
				bfs(l,g,v);
		}
		return l;
	}
	private void bfs(PositionList<V> l, Graph<V,E> g, Vertex<V> v) {
		Queue<Vertex<V>> q = new ColaEnlazada<>();
		q.enqueue(v);
		try {
			v.put(ESTADO, VISITADO);
			Vertex<V> w;
			while(!q.isEmpty()) {
				w = q.dequeue();
				l.addLast(w.element());
				for(Vertex<V> x : g.vertices()) {
					if(g.areAdjacent(w, x)) {
						if(x.get(ESTADO).equals(NO_VISITADO)) {
							x.put(ESTADO, VISITADO);
							q.enqueue(x);
						}
					}
				}
			}
		}catch(InvalidKeyException | EmptyQueueException | InvalidVertexException e) {}
	}


	
	//Encontrar camino minimo 
	public PositionList<Vertex<V>> caminoMinimo(Graph<V,Float> g, Vertex<V> origen, Vertex<V> destino) {
		PositionList<Vertex<V>> camino_actual = new ListaDE<>();
		for(Vertex<V> v : g.vertices()) {
			try {
				v.put(ESTADO, NO_VISITADO);
			} catch (InvalidKeyException e) {}
		}
		Solucion<V> sol = new Solucion<>();
		float costo_camino_actual = 0;
		for(Edge<Float> e : g.edges()) {
			sol.setCosto(sol.getCosto()+ e.element());
		}
		for(Edge<Float> e : g.edges()) {
			sol.setCosto(sol.getCosto()+e.element());
		}
		hallarCaminoMinimo(g,origen,destino,camino_actual,costo_camino_actual,sol);
		PositionList<Vertex<V>> camino_minimo = sol.getCamino_min();
		return camino_minimo;
		
	}
	private void hallarCaminoMinimo(Graph<V,Float> g, Vertex<V> origen, Vertex<V> destino, PositionList<Vertex<V>> camino_actual, float costo_camino_actual, Solucion<V> sol) {
		try {
		origen.put(ESTADO, VISITADO);
		camino_actual.addLast(origen);
		if(origen == destino) {
			if(costo_camino_actual < sol.getCosto()) {
				sol.setCamino_min(camino_actual.clone());
				sol.setCosto(costo_camino_actual);
			}
		}
		else {
			Vertex<V> o = origen;
			for(Edge<Float> e : g.incidentEdges(o)) {
				Vertex<V> v = g.opposite(o, e);
				if(v.get(ESTADO) == NO_VISITADO) {
					hallarCaminoMinimo(g,v,destino,camino_actual,costo_camino_actual + e.element(),sol);
				}
			}
		}
		camino_actual.remove(camino_actual.last());
		origen.put(ESTADO, NO_VISITADO);
		}catch(InvalidKeyException | InvalidVertexException | InvalidEdgeException | InvalidPositionException | EmptyListException e) {}
	}
	private class Solucion<V>{
		//Atributos
		protected float costo;
		protected PositionList<Vertex<V>> camino_min;
		
		//Constructor
		public Solucion() {
			costo = 0;
			camino_min = new ListaDE<>();
		}
		
		//Metodos
		public float getCosto() {
			return costo;
		}

		public void setCosto(float costo) {
			this.costo = costo;
		}

		public PositionList<Vertex<V>> getCamino_min() {
			return camino_min;
		}

		public void setCamino_min(PositionList<Vertex<V>> camino_min) {
			this.camino_min = camino_min;
		}
	}


}
