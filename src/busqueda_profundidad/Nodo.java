package busqueda_profundidad;

public class Nodo<T> {
	private T elemento;
	private Nodo<T> next;
	//Constructor
	public Nodo(){
		elemento=null;
		next=null;
	}
	public Nodo(T e){
		elemento=e;
		next=null;
	}
	//Metodos Set
	public void setElemento(T e){
		elemento=e;
	}
	public void setNext(Nodo<T> n){
		next=n;
	}
	//Metodos Get
	public T getElemento(){
		return elemento;
	}
	public Nodo<T> getNext(){
		return next;
	}
}