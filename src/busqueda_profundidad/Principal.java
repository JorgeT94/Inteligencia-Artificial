package busqueda_profundidad;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Principal extends JFrame{
	private JTextArea consolaIzq;
	private JTextArea consolaDer;
	private JTextField ini00,ini01,ini02,ini10,ini11,ini12,ini20,ini21,ini22;
	private JTextField fin00,fin01,fin02,fin10,fin11,fin12,fin20,fin21,fin22;
	private JButton buscar;
	
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
		//panelMedio.add(puzzleInicio);
		panelMedio.add(buscar);
		//panelMedio.add(puzzleFinal);
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
}