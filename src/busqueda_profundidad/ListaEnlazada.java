package busqueda_profundidad;
import java.util.*;

public class ListaEnlazada<T> implements Iterable<T>{
	protected Nodo<T> inicio;
	protected int contador;
	//Constructor
	public ListaEnlazada(){
		inicio=null;
		contador=0;
	}
	public T removeFirst() throws ListaVaciaException{
		if(isEmpty())
			throw new ListaVaciaException();
		Nodo<T> temporal = inicio;
		inicio = inicio.getNext();
		contador--;
		return temporal.getElemento();
	}
	public T removeLast() throws ListaVaciaException{
		if(isEmpty())
			throw new ListaVaciaException();
		if(size()==1)
			return removeFirst();
		//Buscar el ultimo elemento atraves de los enlaces.
		Nodo<T> actual = inicio;
		Nodo<T> previo = null;
		while(actual.getNext()!=null){
			previo=actual;
			actual=actual.getNext();
		}
		previo.setNext(null);
		contador--;
		return actual.getElemento();
	}
	public T remove(T elemento) throws ListaVaciaException, ElementoNoEncontradoException{
		if(isEmpty())
			throw new ListaVaciaException();
		if(elemento.equals(inicio.getElemento()))
			return removeFirst();
		//Buscar elemento y eliminarlo
		Nodo<T> actual = inicio;
		Nodo<T> previo = null;
		boolean encontrado = false;
		while(actual!=null){
			if(elemento.equals(actual.getElemento())){
				encontrado = true;
				break;
			}
			previo = actual;
			actual = actual.getNext();
		}
		if(!encontrado)
			throw new ElementoNoEncontradoException();
		//Eliminar actual
		previo.setNext(actual.getNext());
		contador--;
		return actual.getElemento();
	}
	public T first() throws ListaVaciaException{
		if(isEmpty())
			throw new ListaVaciaException();
		return inicio.getElemento();
	}
	public T last() throws ListaVaciaException{
		if(isEmpty())
			throw new ListaVaciaException();
		Nodo<T> actual = inicio;
		Nodo<T> previo = null;
		while(actual.getNext()!=null){
			previo = actual;
			actual = actual.getNext();
		}
		return actual.getElemento();
	}
	public boolean contains(T e){
		if(isEmpty())
			return false;
		Nodo<T> actual = inicio;
		boolean encontrado = false;
		while(actual!=null){
			if(e.equals(actual.getElemento())){
				encontrado = true;
				break;
			}
			actual = actual.getNext();
		}
		return encontrado;
	}
	public boolean isEmpty(){
		return contador==0;//inicio==null
	}
	public int size(){
		return contador;
	}
	public String toString(){
		String resultado = "";
		Nodo<T> actual = inicio;
		while(actual!=null){
			resultado = resultado+actual.getElemento().toString()+"\n";
			actual = actual.getNext();
		}
		return resultado;
	}
	public Iterator<T> iterator(){
		return new IteratorLista<T>(inicio, contador);
	}
}
