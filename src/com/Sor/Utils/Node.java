package com.Sor.Utils;

import java.util.ArrayList;

public class Node {
	private String URI;
	private String Property;
	private String Value;
	private Boolean prioritar;
	public ArrayList<Node> neighberhours;
	
	public  Node(String Uri,String property, String value)
	{
		setURI(Uri);
		setProperty(property);
		setValue(value);
		setPrioritar(false);
		neighberhours=new ArrayList<Node>();
	}
	public void addNode(Node nod){
		neighberhours.add(nod);
	}
	
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public String getProperty() {
		return Property;
	}
	public void setProperty(String property) {
		Property = property;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public Boolean getPrioritar() {
		return prioritar;
	}
	public void setPrioritar(Boolean prioritar) {
		this.prioritar = prioritar;
	}
}