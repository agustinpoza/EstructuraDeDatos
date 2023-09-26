
public interface Conjunto<E> {
	public int size();
	public int capacity();
	public boolean isEmpty();
	public E get(int e); 
	public void put(E e);
	public boolean pertenece(E elem);
	public Conjunto<E> interseccion(Conjunto<E> c);
}
