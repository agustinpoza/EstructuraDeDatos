package TDADgrafo;


import TDALista.*;

public class DGrafo<V,E> implements GraphD<V,E>{
	
	protected PositionList<Vertex<V>> vertices;
	protected PositionList<Edge<E>> arcos;
	protected Edge<E> [][] matriz;
	protected int cantVertices;
	
	public DGrafo(int n) {
		vertices = new ListaDE<>();
		arcos = new ListaDE<>();
		matriz = (Edge<E>[][]) new ArcoD[n][n];
		cantVertices = 0;
	}
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> l = new ListaDE<>();
		for(Vertex<V> v : vertices) {
			l.addLast(v);
		}
		return l;
	}
	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> l = new ListaDE<>();
		for(Edge<E> e : arcos) {
			l.addLast(e);
		}
		return l;
	}
	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		VerticeD<V,E> vertice = checkVertice(v);
		PositionList<Edge<E>> list = new ListaDE<>();
		int i = vertice.getIndice();
		for(int j = 0; j<cantVertices; j++) {
			if(matriz[i][j] != null) {
				list.addLast(matriz[i][j]);
			}
		}
		return null;
	}
	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		VerticeD<V,E> vertice = checkVertice(v);
        PositionList<Edge<E>> list = new ListaDE<>();
        int j = vertice.getIndice();
        for(int i = 0; i<cantVertices; i++){
            if(matriz[i][j] != null) {
                list.addLast(matriz[i][j]);
            }
        }
        return list;
	}
	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		VerticeD<V,E> vertice = checkVertice(v);
        ArcoD<V,E> arco = checkArco(e);
        Vertex<V> toReturn = null;
        if(arco.getVerticeD() == vertice){
            toReturn = arco.getVerticeO();
        }
        if(arco.getVerticeO() == vertice){
            toReturn = arco.getVerticeD();
        }
        if(toReturn == null){
            throw new InvalidEdgeException("El arco no es incidente a el vertice.");
        }
        return toReturn;
	}
	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		 Vertex<V>[] array = (Vertex<V>[]) new Vertex[2];
	        ArcoD<V,E> arco = checkArco(e);
	        array[0] =  arco.getVerticeO();
	        array[1] =  arco.getVerticeD();
	        return array;
	}
	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		 VerticeD<V,E> vertice1 = checkVertice(v);
	     VerticeD<V,E> vertice2 = checkVertice(w);
	     int i = vertice1.getIndice();
	     int j = vertice2.getIndice();
	     /*
	     En un grafo dirigido, la relación de adyacencia es unidireccional.
	                                      A
	                                      |
	                                      B
	                                     / \
	                                    C   D
	     A es adyacente a B, B es adyacente a C y D. Si A es adyacente a B,
	     no necesariamente B es adyacente a A.
	     */
	     return matriz[i][j] != null;
	}
	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
        VerticeD<V,E> vertice = checkVertice(v);
        V toReturn = vertice.element();
        vertice.setRotulo(x);
        return toReturn;

	}
	@Override
	public Vertex<V> insertVertex(V x) {
        VerticeD<V,E> insert = new VerticeD<>(x, cantVertices++);
        try {
            vertices.addLast(insert);
            insert.setPosicionEnNodos(vertices.last());
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
        return insert;
	}
	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		VerticeD<V,E> vv = checkVertice(v);
		VerticeD<V,E> ww = checkVertice(v);
		ArcoD<V,E> insert = new ArcoD<>(e,vv,ww);
		int i = vv.getIndice();
		int j = ww.getIndice();
		
		try {
			arcos.addLast(insert);
			insert.setPosicionListaArcos(arcos.last());
			matriz[i][j] = insert;
		}catch(EmptyListException ex) {}
		
		return insert ;
	}
	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		VerticeD<V,E> remove = checkVertice(v);
		V toReturn = remove.element();
		int i = remove.getIndice();
		try {
			vertices.remove(remove.getPositionEnNodos());
			for(int j = 0; j<cantVertices; j++) {
				//elimino los arcos emergentes a v
				if(matriz[i][j] != null) {
					removeEdge(matriz[i][j]);
				}
				//elimino los arcos incidentes a v
				if(matriz[j][i] != null) {
					removeEdge(matriz[i][j]);
				}
			}
			cantVertices--;
		}catch(InvalidPositionException | InvalidEdgeException e) {}
		
		return toReturn;
	}
	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		ArcoD<V,E> remove = checkArco(e);
		E toReturn = remove.getRotulo();
		int i = remove.getVerticeO().getIndice();
		int j = remove.getVerticeD().getIndice();
		try {
			arcos.remove(remove.getPosicionListaArcos());
			matriz[i][j] = null;
		}catch(InvalidPositionException ex) {}
		
		return toReturn;
	}
	
	 private VerticeD<V,E> checkVertice(Vertex<V> v) throws InvalidVertexException {
	        VerticeD<V,E> toReturn = (VerticeD<V,E>) v;
	        if(toReturn == null){
	            throw new InvalidVertexException("Vertice incorrecto.");
	        }
	        return toReturn;
	    }

	    private ArcoD<V,E> checkArco(Edge<E> e) throws InvalidEdgeException {
	        ArcoD<V,E> toReturn = (ArcoD<V, E>) e;
	        if(toReturn == null){
	            throw new InvalidEdgeException("Arco incorrecto.");
	        }
	        return toReturn;
	    }

}
