package busqueda_profundidad;

public class NodoArbol<T> {
	private T elemento;
	private NodoArbol<T> hijoI;
	private NodoArbol<T> hijoCI;
	private NodoArbol<T> hijoCD;
	private NodoArbol<T> hijoD;
	
	public NodoArbol(T e){
		elemento = e;
		hijoI = null;
		hijoCI = null;
		hijoCD = null;
		hijoD = null;
	}
	public void setElemento(T e){
		elemento = e;
	}
	public void setHijoI(NodoArbol<T> i){
		hijoI = i;
	}
	public void setHijoCI(NodoArbol<T> ci){
		hijoCI = ci;
	}
	public void setHijoCD(NodoArbol<T> cd){
		hijoCD = cd;
	}
	public void setHijoD(NodoArbol<T> d){
		hijoD = d;
	}
	public NodoArbol<T> getHijoI(){
		return hijoI;
	}
	public NodoArbol<T> getHijoCI(){
		return hijoCI;
	}
	public NodoArbol<T> getHijoCD(){
		return hijoCD;
	}
	public NodoArbol<T> getHijoD(){
		return hijoD;
	}
	public T getElemento(){
		return elemento;
	}
	public int numeroDeHijos(){
		int hijos = 0;
		if(getHijoI()!=null)
			hijos = hijos+1+getHijoI().numeroDeHijos();
		if(getHijoCI()!=null)
			hijos = hijos+1+getHijoCI().numeroDeHijos();
		if(getHijoCD()!=null)
			hijos = hijos+1+getHijoCD().numeroDeHijos();
		if(getHijoD()!=null)
			hijos = hijos+1+getHijoD().numeroDeHijos();
		return hijos;
	}
}
