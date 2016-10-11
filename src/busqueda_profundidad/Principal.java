package busqueda_profundidad;

import java.awt.*;
import java.awt.event.*;
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
		agregarListeners();
			
		
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
	private void agregarListeners() {
		ini00.addKeyListener(miOyente);
		ini01.addKeyListener(miOyente);
		ini02.addKeyListener(miOyente);
		ini10.addKeyListener(miOyente);
		ini11.addKeyListener(miOyente);
		ini12.addKeyListener(miOyente);
		ini20.addKeyListener(miOyente);
		ini21.addKeyListener(miOyente);
		ini22.addKeyListener(miOyente);
		fin00.addKeyListener(miOyente);
		fin01.addKeyListener(miOyente);
		fin02.addKeyListener(miOyente);
		fin10.addKeyListener(miOyente);
		fin11.addKeyListener(miOyente);
		fin12.addKeyListener(miOyente);
		fin20.addKeyListener(miOyente);
		fin21.addKeyListener(miOyente);
		fin22.addKeyListener(miOyente);

	}
	
	class MiOyente implements ActionListener, KeyListener{		
		int matrizInicial[][];
		int matrizMeta[][];
		Vector<int[][]> visitados = new Vector<int[][]>();
		Stack<int[][]> pila = new Stack<int[][]>();
		boolean terminado = false;
		public void actionPerformed(ActionEvent e){
			String comando = e.getActionCommand();
			if(comando.equals("Buscar")){
				bloquearCamposTexto();
				consolaIzq.setText("");
				consolaDer.setText("");
				visitados.clear();
				pila.clear();
				matrizInicial = new int[3][3];
				matrizMeta = new int[3][3];
				llenarMatrices(matrizInicial,matrizMeta);
				visitados.add(matrizInicial);
				generarHijos(visitados.lastElement());
				desbloquearCamposTexto();
				if(terminado){ 
					mostrarVisitados();
					mostrarCerrados();
				}else{
					consolaDer.append("\nNO SE ENCONTRÓ");
				}
			}			
		}
		public void generarHijos(int[][] nodo){
			if(visitados.lastElement()==null) return;
			while(true){
				/*consolaDer.append("Entró al while (nodo a evaluar y matrizMeta)\n");
				imprimirD(visitados.lastElement());
				imprimirD(matrizMeta);*/
				if(!sonIguales(visitados.lastElement(),matrizMeta)){
					//Añade hijos de "nodo"
					int[][] temp = new int[3][3];
					temp = copiarMatriz(visitados.lastElement());
					getMovimientos(visitados.lastElement());
					if(!sonIguales(temp,visitados.lastElement())){
						generarHijos(visitados.lastElement());
					} else if(!pila.isEmpty()){
						generarHijos(pila.pop()); 
					}
					//generarHijos(visitados.lastElement());
					return;
				} else if(sonIguales(visitados.lastElement(), matrizMeta)){
					terminado = true;
				}
				return;
			}
		}
		public void getMovimientos(int[][] m){
			int[][] temporalMI = new int[3][3];
			temporalMI = copiarMatriz(m);
			int[][] temporalMCI = new int[3][3];
			temporalMCI = copiarMatriz(m);
			int[][] temporalMCD = new int[3][3];
			temporalMCD = null;
			int[][] temporalMD = new int[3][3];
			temporalMD = copiarMatriz(m);
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					if(m[i][j]==0){
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
							temporalMD[i][j] = temporalMD[(i-1)][j];
							temporalMD[(i-1)][j] = 0;
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
							temporalMCD = copiarMatriz(m);
							temporalMCD[i][j] = temporalMCD[i+1][j]; temporalMCD[i+1][j] = 0;
							temporalMD[i][j] = temporalMD[i][j+1]; temporalMD[i][j+1] = 0;
						}
						if(!esVisitado(temporalMI) && temporalMI!=null){
							visitados.add(temporalMI);
						} else{
							temporalMI = null;
						}
						if(!esVisitado(temporalMCI) && temporalMCI!=null && temporalMI==null){ 
							visitados.add(temporalMCI);
						}else{
							if(esVisitado(temporalMCI) || temporalMCI==null) temporalMCI = null; 
						}
						if(!esVisitado(temporalMCD)&&temporalMCD!=null&&temporalMI==null&&temporalMCI==null){ 
							visitados.add(temporalMCD);
						}else{
							if(esVisitado(temporalMCD) || temporalMCD==null) temporalMCD = null;
						}
						if(!esVisitado(temporalMD)&&temporalMD!=null&&temporalMI==null&&temporalMCI==null&&temporalMCD==null){ 
							visitados.add(temporalMD);
						}else{
							if(esVisitado(temporalMD) || temporalMD==null) temporalMD = null;
						}
						if(temporalMI == null && temporalMCI==null && temporalMCD==null && temporalMD==null) return;
						//arbol.add(new Arbol<int[][]>(temporalMI), new Arbol<int[][]>(temporalMCI), new Arbol<int[][]>(temporalMCD),new Arbol<int[][]>(temporalMD));
						agregarCerrados(temporalMI,temporalMCI,temporalMCD,temporalMD);
						return;
					}
				}
			}
		}
		public void agregarCerrados(int[][] temporalMI, int[][] temporalMCI, int[][] temporalMCD, int[][] temporalMD){
			if(temporalMD!=null && !esVisitado(temporalMD)){
				pila.push(temporalMD);
			}
			if(temporalMCD!=null && !esVisitado(temporalMCD)){
				pila.push(temporalMCD);
			}
			if(temporalMCI!=null && !esVisitado(temporalMCI)){
				pila.push(temporalMCI);
			}
		}
		private boolean esVisitado(int[][] m) {
			if(m==null) return false;
			boolean resultado = true;
			for(int i=0; i<visitados.size(); i++){
				if(i>0 && resultado) return true;
				resultado = sonIguales(visitados.elementAt(i), m);
			}
			return resultado;
		}
		public boolean sonIguales(int[][] m1, int[][] m2){
			boolean resultado = true;
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					if(m1[i][j] != m2[i][j]){
						resultado = false;
						break;
					}
				}
			}
			return resultado;
		}
		public int[][] copiarMatriz(int[][] m){
			int[][] matriz = new int[3][3];
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					matriz[i][j] = m[i][j];
				}
			}
			return matriz;
		}
		public void imprimirD(int[][] m){
			consolaDer.append("|---|---|---|\n");
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					consolaDer.append("| "+m[i][j]+" ");
					if(j==2) consolaDer.append("|");
				}
				consolaDer.append("\n");
			}
			consolaDer.append("|---|---|---|\n\n");
		}
		public void imprimirI(int[][] m){
			consolaIzq.append("|---|---|---|\n");
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++){
					consolaIzq.append("| "+m[i][j]+" ");
					if(j==2) consolaIzq.append("|");
				}
				consolaIzq.append("\n");
			}
			consolaIzq.append("|---|---|---|\n\n");
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
		public void desbloquearCamposTexto(){
			ini00.setEditable(true);ini01.setEditable(true);ini02.setEditable(true);
			ini10.setEditable(true);ini11.setEditable(true);ini12.setEditable(true);
			ini20.setEditable(true);ini21.setEditable(true);ini22.setEditable(true);
			fin00.setEditable(true);fin01.setEditable(true);fin02.setEditable(true);
			fin10.setEditable(true);fin11.setEditable(true);fin12.setEditable(true);
			fin20.setEditable(true);fin21.setEditable(true);fin22.setEditable(true);
		}
		public void mostrarVisitados(){
			for(int i=0; i<visitados.size(); i++){
				imprimirI(visitados.elementAt(i));
			}
		}
		public void mostrarCerrados(){
			consolaDer.append("IMPRIMIENDO CERRADOS\n");
			while(!pila.isEmpty()){
				imprimirD(pila.pop());
			}
		}
		public void keyPressed(KeyEvent arg0) {
			
			
		}
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		public void keyTyped(KeyEvent ke) {
			char c=ke.getKeyChar();

            if(ke.getSource() instanceof JTextField ){
            	if(((JTextField)(ke.getSource())).getText().length()==1)
            		ke.consume(); 
            	
            }
	          if(!Character.isDigit(c)) { 
	              getToolkit().beep(); 
	               
	              ke.consume(); 
	               
	          }	
	          
		}
	}
}