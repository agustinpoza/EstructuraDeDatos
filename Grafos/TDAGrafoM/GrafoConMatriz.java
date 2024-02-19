package TDAGrafoM;


import TDALista.*;

public class GrafoConMatriz<V,E> implements Graph<V,E> {

	protected PositionList<Vertex<V>> vertices;
	protected PositionList<Edge<E>> arcos;
	protected Edge<E> [][] matriz;
	protected int cantVertices;
	
	public GrafoConMatriz(int n){//recibe el tamaño de la matriz 
		vertices = new ListaDE<Vertex<V>>();
		arcos = new ListaDE<Edge<E>>();
		
		matriz = (Edge<E> [][]) new Arco[n][n];
		cantVertices = 0;
	}
	
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new ListaDE<Vertex<V>>();
		for(Vertex<V> v : vertices)
			lista.addLast(v);
		return lista;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDE<Edge<E>>();
		for(Edge<E> e : arcos)
			lista.addLast(e);
		return lista;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		//hay que recorrer toda la fila i donde i = indice de v:
		Vertice<V,E> vv = checkVertice(v);
		int i = vv.getIndice();
		PositionList<Edge<E>> lista = new ListaDE<>();
		for(int j = 0; j<cantVertices;j++) {
			if(matriz[i][j] !=null) {
				lista.addLast(matriz[i][j]);
			}
		}
		return lista;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Arco<V,E> ee = checkArco(e);
		Vertice<V,E> vv = checkVertice(v);
		Vertex<V> toReturn = null;
		if(ee.getv1() == vv) 
			toReturn = ee.getv2();
		if(ee.getv2() == vv)
			toReturn = ee.getv1();
		if(toReturn == null){
            throw new InvalidEdgeException("El arco no es incidente a el vertice.");
        }
		return toReturn;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Vertex<V> [] a = (Vertex<V> []) new Vertice[2];
		Arco<V,E> ee = checkArco(e);
		a[0] = ee.getv1();
		a[1] = ee.getv2();
		return a;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V,E> vv = checkVertice(v);
		Vertice<V,E> ww = checkVertice(w);
		int i = vv.getIndice();
		int j = ww.getIndice();
		return matriz[i][j]!=null;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> vv = checkVertice(v);
		V toReturn = vv.element();
		vv.setRotulo(x);
		return toReturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> vv = new Vertice<V,E>(x, cantVertices++);
		vertices.addLast(vv);
		try {
			vv.setPosicionEnNodos(vertices.last());
		} catch (EmptyListException e) {}
		return vv;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V,E> vv = checkVertice(v);
		Vertice<V,E> ww = checkVertice(w);
		int fila = vv.getIndice();
		int col = ww.getIndice();
		Arco<V,E> arco = new Arco<V,E>(e,vv,ww);
		//Grafo no dirigido => matriz simetrica
		
		matriz[fila][col] = matriz[col][fila] = arco;
		
		//Cargo arco en la lista de arcos
		arcos.addLast(arco);
		try {
			arco.setPosEnArcos(arcos.last());
		} catch (EmptyListException e1) {}
		
		return arco;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> removeVertex = checkVertice(v);
		V toReturn = removeVertex.element();
		try {
			vertices.remove(removeVertex.getPositionEnNodos());
			int i = removeVertex.getIndice();
			for(int j = 0; j<cantVertices; j++) {
				if(matriz[i][j] != null)
					removeEdge(matriz[i][j]);
			}
		}catch(InvalidPositionException | InvalidEdgeException e) {}
		return toReturn;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		try {
			Arco<V,E> ee = checkArco(e);
			int fila = ee.getv1().getIndice();
			int col = ee.getv2().getIndice();
			matriz[fila][col] = matriz[col][fila] = null;
			arcos.remove(ee.getPosicionEnArcos());
			return e.element();
		}catch(InvalidPositionException exc){return null;}
	}

	private Vertice<V,E> checkVertice(Vertex<V> v) throws InvalidVertexException{
		try {
		Vertice<V,E> toReturn = (Vertice<V,E>) v;
		if(toReturn == null) {
			throw new InvalidVertexException("Vertice Incorrecto");
		}
		return toReturn;
		}catch(ClassCastException e) {throw new InvalidVertexException("Vertice Incorrecto");}
	}
	private Arco<V,E> checkArco(Edge<E> v) throws InvalidEdgeException{
		try {
		Arco<V,E> toReturn = (Arco<V,E>) v;
		if(toReturn == null) {
			throw new InvalidEdgeException("Arco Incorrecto");
		}
		return toReturn;
		}catch(ClassCastException e) {throw new InvalidEdgeException("Arco Incorrecto");}
	}
}
