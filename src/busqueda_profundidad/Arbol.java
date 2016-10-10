package busqueda_profundidad;

import java.util.Iterator;

public class Arbol<T>{
	protected NodoArbol<T> raiz;
	protected int contador;
	
	public Arbol(){
		raiz = null;
		contador = 0;
	}
	public Arbol(T e){
		raiz = new NodoArbol<T>(e);
		contador = 1;
	}
	public void add(Arbol<T> i, Arbol<T> ci, Arbol<T> cd, Arbol<T> d){
		if(i!=null){
			if(i.getRaiz().getElemento()!=null){
				System.out.println("WOOP");
				raiz.setHijoI(i.getRaiz());
				contador = contador+i.size();
			}
		}
		if(ci!=null){
			if(ci.getRaiz().getElemento()!=null){
				raiz.setHijoCI(ci.getRaiz());
				contador = contador+ci.size();
			}
		}
		if(cd!=null){
			if(cd.getRaiz().getElemento()!=null){
				raiz.setHijoCD(cd.getRaiz());
				contador = contador+cd.size();
			}
		}
		if(d!=null){
			if(d.getRaiz().getElemento()!=null){
				raiz.setHijoD(d.getRaiz());
				contador = contador+d.size();
			}
		}
	}
	public NodoArbol<T> getRaiz(){
		return raiz;
	}
	public void removeAllElements(){
		raiz = null;
		contador = 0;
	}
	public boolean isEmpty(){
		return size()==0;
	}
	public int size(){
		return contador;
	}
	public boolean contains(T e){
		//No FUNCIONA
		if(isEmpty()) return false;
		boolean encontrado = false;
		Iterator<T> iterador = iteratorInOrden();
		while(iterador.hasNext()){
			T temporal = iterador.next();
			if(temporal.equals(e)){
				encontrado = true;
				break;
			}
		}
		return encontrado;
	}
	public Iterator<T> iteratorInOrden(){
		ListaDesordenada<T> lista = new ListaDesordenada<T>();
		inOrden(getRaiz(),lista);
		return lista.iterator();
	}
	public void inOrden(NodoArbol<T> r, ListaDesordenada<T> l){
		if(r!=null){
			inOrden(r.getHijoI(),l);
			l.addToRear(r.getElemento());
			inOrden(r.getHijoCI(),l);
			l.addToRear(r.getElemento());
			inOrden(r.getHijoCD(),l);
			l.addToRear(r.getElemento());
			inOrden(r.getHijoD(),l);
		}
	}
	public String toString(){
		String resultado = "";
		return resultado;
	}
}