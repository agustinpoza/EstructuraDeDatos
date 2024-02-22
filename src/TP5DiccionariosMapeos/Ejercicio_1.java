package TP5DiccionariosMapeos;

import java.util.Iterator;

import TDADiccionario.*;
import TDADiccionario.Entry;
import TDALista.*;
import TDAMap.InvalidKeyException;
import TDAMap.*;

public class Ejercicio_1<K, V> {
	
	
	public PositionList<TDAMap.Entry<Integer,Integer>> eja(Map<Integer,Integer> m1, Map<Integer,Integer> m2){
		PositionList<TDAMap.Entry<Integer,Integer>> plAux = new ListaDE<>();
		
		Iterator<TDAMap.Entry<Integer, Integer>> it1 = m1.entries().iterator();
		Iterator<TDAMap.Entry<Integer, Integer>> it2 = m2.entries().iterator();
		boolean coincide = false;
		TDAMap.Entry<Integer, Integer> e1;
		TDAMap.Entry<Integer,Integer> e2;
		while(it1.hasNext()) {
			e1 = it1.next();
			while(it2.hasNext() && !coincide) {
				e2 = it2.next();
				if(e1.getKey().equals(e2.getKey())) {
					coincide = true;
					plAux.addLast(e1);
					plAux.addLast(e2);
				}
			}
		}
		return plAux;
	}
	
	
	public boolean ejb(Map<K,V> m1, Map<K,V> m2) {
		boolean contiene = true; // c1
		if(m1.size()>m2.size()) return false; // c2
		try {
			Iterator<K> it = m1.keys().iterator(); // n iteraciones 
			while(it.hasNext() && contiene) { //m iteraciones
				K key = it.next();//c3
				if(m2.get(key)==null)//c4
					contiene = false;//c5
			}
		}catch(InvalidKeyException e) {}
		return contiene;
		//sea n la cantidad de claves en el mapa y m la cantidad de de claves a recorrer hasta encontrar la que buscamos
		//T(n,m) = c1+c2+n+m*(c3+c4+c5) -> O(n+m) = O(max(n,m)) -> O(n) 
	}
	
	public Dictionary<K,V> acomodar(Dictionary<K,V> d){
		Dictionary<K,V> dAux = new DiccionarioHashAbierto<>();
		Map<K,V> mAux = new MapeoHashAbierto<>();
		try {
			for(Entry<K,V> e : d.entries()) {
				mAux.put(e.getKey(), e.getValue());
			}
			for(TDAMap.Entry<K,V> e : mAux.entries()) {
				dAux.insert(e.getKey(), e.getValue());
			}
			
		}catch(InvalidKeyException | TDADiccionario.InvalidKeyException e) {}
		return dAux;
	}
	
	public Map<Character,Integer> ejd(PositionList<Character> l){
		Map<Character,Integer> m = new MapeoHashAbierto<>();
		try {
			for(Character c : l) {
				if(m.get(c) == null)
					m.put(c, 1);
				else
					m.put(c, m.get(c)+1);
			}
		}catch(InvalidKeyException e) {}
		return m;
	}
	
	
}
