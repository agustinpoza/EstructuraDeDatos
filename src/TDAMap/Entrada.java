package TDAMap;

public class Entrada<K,V> implements Entry<K,V> {
	
	protected K key;
	protected V value;
	
	public Entrada() {
		key = null;
		value = null;
	}
	public Entrada(K k, V v) {
		key = k;
		value = v;
	}
	
	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	public void setValue(V v) {
		this.value = v;
	}
	public void setKey(K k) {
		this.key = k;
	}

}
