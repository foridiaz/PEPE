package Controller;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.xml.sax.XMLReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


import VO.verticeInfo;
import View.View;
import model.data_structures.Grafo;
import model.data_structures.Vertice;

public class Controler  {

	private Grafo<Long, verticeInfo, Double> grafo; 
	private View view; 
	public ArrayList<Vertice<verticeInfo,Long,Double>> vertices2; 
	private Vertice<verticeInfo, Long, Double> vertice2; 
	public Controler() {
		view= new View(); 
		grafo=new Grafo<>(); 
		vertices2=new ArrayList<>(); 
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
					break; 
				}catch(Exception e) {

				}
			case 3: 
				cargarDatosJson();
				for(int i=0; i<vertices2.size(); i++){
					System.out.println(vertices2.get(i).darLlave()+"");
				}
				System.out.println("termino");
				break; 
			}
		}
	}




	public int[] cargarDatos(int num) {
		int[] retornar=new int[2]; 
		String file=num==1?"Central-WashingtonDC-OpenStreetMap.xml":"exampleMap.xml";
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
	public void cargarDatosJson(){
		String file="."+File.separator+"data"+File.separator+"JsonVertices.json";  
		try{
			JsonParser parser= new JsonParser(); 
//			Object obj = parser.parse(new FileReader("."+File.separator+"data"+File.separator+file)); 
			JsonArray arr= (JsonArray)parser.parse(new FileReader(file));
			System.out.println(file);
			for (int i=0; i<arr.size()&&arr!=null; i++){
				JsonObject obj=(JsonObject)arr.get(i); 
				Long id=Long.parseLong(obj.get("id").getAsString());
				Double lat=Double.parseDouble(obj.get("lat").getAsString()); 
				Double log=Double.parseDouble(obj.get("lon").getAsString()); 
				vertice2=new Vertice<>(id, new verticeInfo(lat, log)); 
				JsonArray arcos=obj.get("adj").getAsJsonArray(); 
				for (int j=0; j<arcos.size(); j++){
					vertice2.agregarArco(0.0, id, arcos.get(j).getAsLong());
				}
				vertices2.add(vertice2); 				
			}
						
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
