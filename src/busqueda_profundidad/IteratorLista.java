package busqueda_profundidad;
import java.util.*;

public class IteratorLista<T> implements Iterator<T>{
	private Nodo<T> actual;
	private int contador;
	public IteratorLista(Nodo<T> inicio, int c){
		actual = inicio;
		contador = c;
	}
	public boolean hasNext(){
		return actual!=null;
	}
	public T next() throws ElementoNoEncontradoException{
		if(!hasNext())
			throw new ElementoNoEncontradoException();
		T resultado = actual.getElemento();
		actual = actual.getNext();
		return resultado;
	}
	public void remove() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
}
