package busqueda_profundidad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Principal extends JFrame{
	private JTextArea consolaIzq;
	private JTextArea consolaDer;
	private JTextField ini00,ini01,ini02,ini10,ini11,ini12,ini20,ini21,ini22;
	private JTextField fin00,fin01,fin02,fin10,fin11,fin12,fin20,fin21,fin22;
	private JButton buscar;
	MiOyente miOyente;
	Arbol<int[][]> arbol;
	
	public Principal(String titulo){
		super(titulo);
		hazInterfaz();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		setVisible(true);
	}
	
	public void hazInterfaz(){
		JPanel panelBase = new JPanel();
		panelBase.setLayout(new GridLayout(3,0,1,0));
		this.add(panelBase);
		
		//Panel con Puzzles
		JPanel panelArriba = new JPanel();
		panelArriba.setLayout(new BorderLayout());
		//Puzzle Inicial
		JPanel puzzleInicio = new JPanel();
		puzzleInicio.setLayout(new GridLayout(3,3,2,2));
		ini00 = new JTextField(5);ini01 = new JTextField(5);ini02 = new JTextField(5);
		ini10 = new JTextField(5);ini11 = new JTextField(5);ini12 = new JTextField(5);
		ini20 = new JTextField(5);ini21 = new JTextField(5);ini22 = new JTextField(5);
		//puzzleInicio.setSize(150, 150);
		puzzleInicio.add(ini00);puzzleInicio.add(ini01);puzzleInicio.add(ini02);
		puzzleInicio.add(ini10);puzzleInicio.add(ini11);puzzleInicio.add(ini12);
		puzzleInicio.add(ini20);puzzleInicio.add(ini21);puzzleInicio.add(ini22);
		panelArriba.add(puzzleInicio, BorderLayout.WEST);
		//BOTON
		//JButton buscar2 = new JButton("BUSCAR");
		//buscar2.setBounds(50, 50, 100, 100);
		//panelArriba.add(buscar2);
		//Puzzle Meta
		JPanel puzzleFinal = new JPanel();
		puzzleFinal.setLayout(new GridLayout(3,3,2,2));
		fin00 = new JTextField(5);fin01 = new JTextField(5);fin02 = new JTextField(5);
		fin10 = new JTextField(5);fin11 = new JTextField(5);fin12 = new JTextField(5);
		fin20 = new JTextField(5);fin21 = new JTextField(5);fin22 = new JTextField(5);
		puzzleFinal.add(fin00);puzzleFinal.add(fin01);puzzleFinal.add(fin02);
		puzzleFinal.add(fin10);puzzleFinal.add(fin11);puzzleFinal.add(fin12);
		puzzleFinal.add(fin20);puzzleFinal.add(fin21);puzzleFinal.add(fin22);
		panelArriba.add(puzzleFinal, BorderLayout.EAST);
		panelBase.add(panelArriba);

		//Panel con Botón
		JPanel panelMedio = new JPanel();
		panelMedio.setLayout(new FlowLayout());
		panelMedio.setSize(200,200);
		buscar = new JButton("Buscar");
		buscar.setActionCommand("Buscar");
		miOyente = new MiOyente();
		buscar.addActionListener(miOyente);
		panelMedio.add(buscar);
		panelBase.add(panelMedio);
		
		//Panel con TextAreas
		JPanel panelAbajo = new JPanel();
		panelAbajo.setLayout(new BorderLayout());
		consolaIzq = new JTextArea(10,55);
		consolaIzq.setEditable(false);
		panelAbajo.add(new JScrollPane(consolaIzq), BorderLayout.WEST);
		consolaDer = new JTextArea(10,55);
		consolaDer.setEditable(false);
		panelAbajo.add(new JScrollPane(consolaDer), BorderLayout.EAST);
		panelBase.add(panelAbajo);
		this.setSize(980, 650);
	}
	
	public static void main(String[] args) {
		new Principal("Busqueda de Profundidad");
	}
	
	class MiOyente implements ActionListener{
		int matrizInicial[][];
		int matrizMeta[][];
		Vector<int[][]> visitados = new Vector<int[][]>();
		Stack<NodoArbol> pila = new Stack<NodoArbol>();
		boolean terminado = false;
		public void actionPerformed(ActionEvent e){
			String comando = e.getActionCommand();
			if(comando.equals("Buscar")){
				bloquearCamposTexto();
				visitados.clear();
				pila.clear();
				matrizInicial = new int[3][3];
				matrizMeta = new int[3][3];
				llenarMatrices(matrizInicial,matrizMeta);
				arbol = new Arbol<int[][]>(matrizInicial);
				visitados.add(matrizInicial);
				System.out.println("Empieza a generar hijos");
				generarHijos(arbol.getRaiz());
				System.out.println("Termina de generar hijos");
				if(terminado) 
					consolaDer.append("\nSI SE ENCONTRÓ");
				else
					consolaDer.append("\nNO SE ENCONTRÓ");
			}
		}
		public void generarHijos(NodoArbol<int[][]> nodo){
			imprimir(nodo.getElemento());
			if(nodo.getElemento()==null) return;
			System.out.println("Apunto de entrar al while(true)");
			while(true){
				System.out.println("Entra al while");
				if(nodo.getElemento()!=matrizMeta){
					//Añade hijos de "nodo"
					System.out.println("Empieza a obtener movimientos");
					getMovimientos(nodo.getElemento());
					System.out.println("Termina de obtener movimientos");
					//Añade hermanos de nodo izquierdo a pila para futuro backtracking
					if(nodo.getHijoI()!=null){
						if(nodo.getHijoD().getElemento()!=null) pila.push(nodo.getHijoD());
						if(nodo.getHijoCD().getElemento()!=null) pila.push(nodo.getHijoCD());
						if(nodo.getHijoCI().getElemento()!=null) pila.push(nodo.getHijoCI());
						System.out.println("Empieza a generar hijos del hijo izq");
						generarHijos(nodo.getHijoI());
					} else if(nodo.getHijoCI()!=null){
						if(nodo.getHijoD().getElemento()!=null) pila.push(nodo.getHijoD());
						if(nodo.getHijoCD().getElemento()!=null) pila.push(nodo.getHijoCD());
						generarHijos(nodo.getHijoCI());
					} else if(nodo.getHijoCD()!=null){
						if(nodo.getHijoD().getElemento()!=null) pila.push(nodo.getHijoD());
						generarHijos(nodo.getHijoCD());
					} else if(nodo.getHijoD()!=null){
						generarHijos(nodo.getHijoD());
					} else{
						if(!pila.isEmpty())generarHijos(pila.pop());
					}
					return;
				} else if(nodo.getElemento()==matrizMeta){
					System.out.println("Cambia terminado a true");
					terminado = true;
				}
				return;
			}
		}
		public void getMovimientos(int[][] m){
			int[][] temporalMI;
			int[][] temporalMCI;
			int[][] temporalMCD = null;
			int[][] temporalMD;
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					if(m[i][j]==0){
						temporalMI = m;
						imprimir(temporalMI);
						temporalMCI = m;
						temporalMD = m;
						if(i==0 && j==0){ // 2 movimientos:
							temporalMI[i][j] = temporalMI[i+1][j];
							temporalMI[i+1][j] = 0;
							temporalMD[i][j] = temporalMD[i][j+1];
							temporalMD[i][j+1] = 0;
							temporalMCI = null;
						} else if(i==0 && j==2){
							temporalMI[i][j] = temporalMI[i+1][j];
							temporalMI[i+1][j] = 0;
							temporalMD[i][j] = temporalMD[i][j-1];
							temporalMD[i][j-1] = 0;
							temporalMCI = null;
						} else if(i==2 && j==0){
							temporalMI[i][j] = temporalMI[i-1][j];
							temporalMI[i-1][j] = 0;
							temporalMD[i][j] = temporalMD[i][j+1];
							temporalMD[i][j+1] = 0;
							temporalMCI = null;
						} else if(i==2 && j==2){
							temporalMI[i][j] = temporalMI[i][j-1];
							temporalMI[i][j-1] = 0;
							imprimir(temporalMI);
							temporalMD[i][j] = temporalMD[i-1][j];
							temporalMD[i-1][j] = 0;
							temporalMCI = null;
						} else if(i==0 && j==1){ // 3 movimientos:
							temporalMI[i][j] = temporalMI[i][j-1];
							temporalMI[i][j-1] = 0;
							temporalMCI[i][j] = temporalMCI[i+1][j];
							temporalMCI[i+1][j] = 0;
							temporalMD[i][j] = temporalMD[i][j+1];
							temporalMD[i][j+1] = 0;
						} else if(i==1 && j==0){
							temporalMI[i][j] = temporalMI[i-1][j];
							temporalMI[i-1][j] = 0;
							temporalMCI[i][j] = temporalMCI[i][j+1];
							temporalMCI[i][j+1] = 0;
							temporalMD[i][j] = temporalMD[i+1][j];
							temporalMD[i+1][j] = 0;
						} else if(i==1 && j==2){
							temporalMI[i][j] = temporalMI[i+1][j];
							temporalMI[i+1][j] = 0;
							temporalMCI[i][j] = temporalMCI[i][j-1];
							temporalMCI[i][j-1] = 0;
							temporalMD[i][j] = temporalMD[i-1][j];
							temporalMD[i-1][j] = 0;
						} else if(i==2 && j==1){
							temporalMI[i][j] = temporalMI[i][j-1];
							temporalMI[i][j-1] = 0;
							temporalMCI[i][j] = temporalMCI[i-1][j];
							temporalMCI[i-1][j] = 0;
							temporalMD[i][j] = temporalMD[i][j+1];
							temporalMD[i][j+1] = 0;
						} else if(i==1 && j==1){ //4 Movimientos:
							temporalMI[i][j] = temporalMI[i][j-1]; temporalMI[i][j-1] = 0;
							temporalMCI[i][j] = temporalMCI[i-1][j]; temporalMCI[i-1][j] = 0;
							temporalMCD = m;
							temporalMCD[i][j] = temporalMCD[i+1][j]; temporalMCD[i+1][j] = 0;
							temporalMD[i][j] = temporalMD[i][j+1]; temporalMD[i][j+1] = 0;
						}
						if(esVisitado(temporalMI) && temporalMI!=null){
							System.out.println("Agrega temporalMI a visitados");
							visitados.add(temporalMI);
						} else{
							temporalMI = null;
						}
						System.out.println("El vector visitados tiene "+visitados.size()+" elementos");
						if(!visitados.contains(temporalMI) && temporalMI!=null){ 
							System.out.println("Agrega temporalMI a visitados");
							visitados.add(temporalMI);
						}else{
							System.out.println("Hace temporalMI = null");
							temporalMI = null; 
						}
						/*if(visitados.contains(temporalMCI) || temporalMCI == null){ 
							temporalMCI = null;
						}else{
							visitados.add(temporalMCI);
						}
						if(visitados.contains(temporalMCD) || temporalMCD == null){ 
							temporalMCD = null;
						}else{
							visitados.add(temporalMCD);
						}
						if(visitados.contains(temporalMD) || temporalMD == null){ 
							temporalMD = null;
						}else{
							visitados.add(temporalMD);
						}*/
						consolaIzq.setText(""+visitados.size());
						if(temporalMI == null && temporalMCI==null && temporalMCD==null && temporalMD==null) return;
						arbol.add(new Arbol<int[][]>(temporalMI), new Arbol<int[][]>(temporalMCI), new Arbol<int[][]>(temporalMCD),new Arbol<int[][]>(temporalMD));
						consolaDer.append("El tamaño del árbol es: "+arbol.size());
						System.out.println("Hasta aquí añadió el arboliyo");
						return;
					}
				}
			}
		}
		private boolean esVisitado(int[][] temporalMI) {
			for(int i=0; i<visitados.size(); i++){
				if(visitados.elementAt(i).equals(temporalMI)) return true;
			}
			return false;
		}
		public void imprimir(int[][] m){
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					consolaDer.append(""+m[i][j]);
				}
				consolaDer.append("\n");
			}
		}
		public void llenarMatrices(int[][] mI, int[][] mM){
			//Llenar Matriz Inicial
			mI[0][0]=Integer.parseInt(ini00.getText());mI[0][1]=Integer.parseInt(ini01.getText());mI[0][2]=Integer.parseInt(ini02.getText());
			mI[1][0]=Integer.parseInt(ini10.getText());mI[1][1]=Integer.parseInt(ini11.getText());mI[1][2]=Integer.parseInt(ini12.getText());
			mI[2][0]=Integer.parseInt(ini20.getText());mI[2][1]=Integer.parseInt(ini21.getText());mI[2][2]=Integer.parseInt(ini22.getText());
			//Llenar Matriz Meta
			mM[0][0]=Integer.parseInt(fin00.getText());mM[0][1]=Integer.parseInt(fin01.getText());mM[0][2]=Integer.parseInt(fin02.getText());
			mM[1][0]=Integer.parseInt(fin10.getText());mM[1][1]=Integer.parseInt(fin11.getText());mM[1][2]=Integer.parseInt(fin12.getText());
			mM[2][0]=Integer.parseInt(fin20.getText());mM[2][1]=Integer.parseInt(fin21.getText());mM[2][2]=Integer.parseInt(fin22.getText());
		}
		public void bloquearCamposTexto(){
			ini00.setEditable(false);ini01.setEditable(false);ini02.setEditable(false);
			ini10.setEditable(false);ini11.setEditable(false);ini12.setEditable(false);
			ini20.setEditable(false);ini21.setEditable(false);ini22.setEditable(false);
			fin00.setEditable(false);fin01.setEditable(false);fin02.setEditable(false);
			fin10.setEditable(false);fin11.setEditable(false);fin12.setEditable(false);
			fin20.setEditable(false);fin21.setEditable(false);fin22.setEditable(false);
		}
	}
}