package busqueda_profundidad;

public class ListaDesordenada<T> extends ListaEnlazada<T>{
	public void addToFront(T e){
		Nodo<T> nuevo = new Nodo<T>(e);
		nuevo.setNext(inicio);
		inicio = nuevo;
		contador++;
	}
	public void addToRear(T e){
		if(isEmpty()){
			addToFront(e);
			return;
		}
		Nodo<T> nuevo = new Nodo<T>(e);
		Nodo<T> actual = inicio;
		while(actual.getNext()!=null)
			actual = actual.getNext();
		actual.setNext(nuevo);
		contador++;
	}
	public void addAfter(T e, T anterior) throws ElementoNoEncontradoException{
		if(isEmpty())
			throw new ElementoNoEncontradoException();
		Nodo<T> actual = inicio;
		boolean encontrado = false;
		while(actual!=null){
			if(anterior.equals(actual.getElemento())){
				encontrado = true;
				break;
			}
			actual = actual.getNext();
		}
		if(!encontrado)
			throw new ElementoNoEncontradoException();
		Nodo<T> nuevo = new Nodo<T>(e);
		nuevo.setNext(actual.getNext());
		actual.setNext(nuevo);
		contador++;
	}
}