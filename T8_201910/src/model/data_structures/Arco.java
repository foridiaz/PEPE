package model.data_structures;

/*
 * Clase que representa el arco, almacena el id V generico del vertice adyacente y su peso A generico
 */
public class Arco<K extends Comparable<K>,A>
{

	//Atributos
	/**
	 * Id generico del segundo vertice
	 */
	private K llaveId;

	/**
	 * Peso del arco
	 */
	private A pesoArco;
	
	private K idInicio; 
	//Constructor
	/**
	 * @param pPeso peso del arco
	 * @param pLlave llave id del vertice adyacente
	 */
	public Arco (A pPeso, K inicio, K pfinal)
	{
		//se inicializan los atributos del arco
		llaveId=pfinal;
		pesoArco=pPeso;
		idInicio=inicio; 
	}
	//Metodos
	/**
	 * Retorna el Id del vertice adyacente
	 */
	public K darAdyacente()
	{
		return llaveId;
	}
	
	/**
	 * Retorna el Peso del arco
	 */
	public A darPeso()
	{
		return pesoArco;
	}
	
	/**
	 * Modificar la informacion del arco con el vertice idVertexFin
	 */
	public void setInfoArc(A infoArc)
	{
		pesoArco=infoArc;
	}
	public K darInicio() {
		return idInicio;  
	}
}
