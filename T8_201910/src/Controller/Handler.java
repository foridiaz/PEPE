package Controller;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import VO.verticeInfo;
import model.data_structures.Arco;
import model.data_structures.Vertice;

public class Handler extends DefaultHandler {

	private ArrayList<Vertice<verticeInfo,Long,Integer>> vertices; 

	private Vertice<verticeInfo, Long, Integer> vertice; 

	@Override
	public void startDocument() throws SAXException {

		vertices=new ArrayList<>(); 
	}
	public boolean printedFirstNode=false;

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		System.out.println(localName);
	}

	public ArrayList<Vertice<verticeInfo,Long,Integer>> getVertices(){
		return vertices; 
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println(vertices.size()+"");
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch(qName) {
		case "osm version":
			break; 
		case "note":
			break; 
		case "meta":
			break; 
		case "bounds":
			break; 
		case "node":
			verticeInfo info= new verticeInfo(Double.parseDouble(attributes.getValue("lat")),Double.parseDouble(attributes.getValue("lon")));
			vertice=new Vertice<>(Long.parseLong(attributes.getValue("id")), info); 
			vertices.add(vertice);
			System.out.println(vertices.size()+"");
			break; 
		}
	}
}
