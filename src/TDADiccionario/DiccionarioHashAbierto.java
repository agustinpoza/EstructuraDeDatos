package TDADiccionario;
import java.util.Iterator;

import TDALista.*;

public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V>{
	
	protected int size;
	protected int N;
	protected PositionList<Entry<K,V>> [] buckets;
	protected final float FACTOR_CARGA = 0.9F;
	
	public DiccionarioHashAbierto() {
		size = 0;
		N = 13;
		buckets = new ListaDE[N];
		for(int i = 0; i<N; i++){
			buckets[i] = new ListaDE<>();
		}
	}
	
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		int hk = hashKey(key);
		Entry<K,V> toReturn = null;
		boolean encontre = false;
		Iterator<Entry<K,V>> it = buckets[hk].iterator();
		while(it.hasNext() && !encontre) {
			Entry<K,V> e = it.next();
			if(e.getKey().equals(key)) {
				encontre = true;
				toReturn = e;
			}
		}
		
		return toReturn;
	}
	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		int hk = hashKey(key);
		PositionList<Entry<K,V>> toReturn = new ListaDE<Entry<K,V>>();
		for(Entry<K,V> e : buckets[hk]) {
			if(e.getKey().equals(key)) {
				toReturn.addLast(e);
			}
		}
		return toReturn;
	}
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		int hk = hashKey(key);
		Entry<K,V> toReturn = new Entrada<K,V>(key,value);
		buckets[hk].addLast(toReturn);
		size++;
		
		if((float)size/N>=FACTOR_CARGA) reHash();
		
		return toReturn;
	}
	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if(e == null || e.getKey() == null || e.getValue() == null) throw new InvalidEntryException("Entrada Invalida");
		int hk = hashKey(e.getKey());
		Entry<K,V> toReturn = null;
		boolean encontre = false;
		Iterator<Position<Entry<K,V>>> it = buckets[hk].positions().iterator();
		while(it.hasNext() && !encontre) {
			Position<Entry<K,V>> p = it.next();
			if(p.element().equals(e)) {
				try {
				toReturn = p.element();
				encontre = true;
				buckets[hk].remove(p);
				size--;
				} catch (InvalidPositionException e1) {}
			}
		}
		if(!encontre) throw new InvalidEntryException("Entrada no encontrada");
		return toReturn;
	}
	
	public Iterable<Entry<K,V>> eliminarTodas(K c, V v) throws InvalidKeyException{
		
		checkKey(c);//c1
		int hk = hashKey(c);//c2
		
		PositionList<Entry<K,V>> p = new ListaDE<Entry<K,V>>();//c4
		for(Position<Entry<K,V>> e : buckets[hk].positions()) {//n donde n es la cantidad de entradas del bucket
			if(e.element().getKey().equals(c) && e.element().getValue().equals(v)) {//c5
				p.addLast(e.element());//c6
				try {
					buckets[hk].remove(e);//c7
					size--;//c8
				} catch (InvalidPositionException e1) {}
			}
		}
		//T(n) = c1+c2+c3+c4+n*(c5+c6+c7+c8) -> O(n) 
		
		return p;
	}
	
	
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> p = new ListaDE<>();
		for(PositionList<Entry<K,V>> listas : buckets) {
			for(Entry<K,V> e : listas) {
				p.addLast(e);
			}
		}
		return p;
	}
	
	private void checkKey(K key) throws InvalidKeyException{
		if(key == null) throw new InvalidKeyException("clave nula");
	}
	private int hashKey(K key) {
		return Math.abs(key.hashCode()%N);
	}
	
	private int nextPrime(int num){
		int candidato = num + 1; // Empezamos en el siguiente número después de n
	    while (true) { // Iteramos hasta encontrar el siguiente número primo
	        boolean esPrimo = true;
	        for (int i = 2; i <= Math.sqrt(candidato); i++) {
	            if (candidato % i == 0) { // Si encontramos un divisor, no es primo
	                esPrimo = false;
	                break;
	            }
	        }
	        if (esPrimo) { // Si es primo, lo devolvemos
	            return candidato;
	        }
	        candidato++; // Si no es primo, probamos con el siguiente número
	    }
	}
	
	private void reHash() throws InvalidKeyException {
		Iterable<Entry<K,V>> entries = this.entries();
		N = nextPrime(N*2);
		buckets = new ListaDE[N];
		for(int i=0;i<N;i++) {
			buckets[i] = new ListaDE<Entry<K,V>>();
		}
		size = 0;
		for(Entry<K,V> e : entries) {
			this.insert(e.getKey(), e.getValue());
		}
	}
}
