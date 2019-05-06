package Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.xml.sax.XMLReader;

import VO.verticeInfo;
import View.View;
import model.data_structures.Grafo;
import model.data_structures.Vertice;

public class Controler  {

	private Grafo<Long, verticeInfo, Double> grafo; 
	private View view; 
	public Controler() {
		view= new View(); 
		grafo=new Grafo<>(); 
	}

	public void run() {
		Scanner sc= new Scanner(System.in);
		boolean fin=false; 
		while(!fin) {
			view.printMenu();
			int option=sc.nextInt();
			switch(option) {
			case 1: 
				System.out.println("Ingrese 1 si desea cargar el archico: Washington... ");
				System.out.println("Ingrese 2 si desea cargar el archivo: map.xml");
				int archivo=sc.nextInt(); 
				cargarDatos(archivo); 
				System.out.println(grafo.getVertices().size()+"");
				System.out.println(grafo.darNumArcos()+"");
				break;
			case 2: 
				try {
					grafo.JsonVertices();
				}catch(Exception e) {

				}
			}
		}
	}




	public int[] cargarDatos(int num) {
		int[] retornar=new int[2]; 
		String file=num==1?"Central-WashingtonDC-OpenStreetMap.xml":"map.xml";
		try {
			SAXParserFactory spf= SAXParserFactory.newInstance(); 
			spf.setNamespaceAware(true); 
			SAXParser saxParser= spf.newSAXParser(); 
			XMLReader xmlReader=saxParser.getXMLReader(); 
			xmlReader.setContentHandler(grafo);
			xmlReader.parse("."+File.separator+"data"+File.separator+file);

		}catch(Exception e) {
			System.out.println("Ocurrio un problema leyendo los datos"+e.getStackTrace());
			e.printStackTrace();
		}

		return retornar; 
	}
}
