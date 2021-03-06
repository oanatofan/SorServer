package com.Sor.Utils;

import java.util.*;
import java.lang.*;

public class Node implements Comparable<Node> {
	private String URI;
	private String Property;
	private String Value;
	private int prioritar;
	public ArrayList<Node> neighberhours;

	public Node(String Uri, String property, String value) {
		setURI(Uri);
		setProperty(property);
		setValue(value);
		setPrioritar(0);
		neighberhours = new ArrayList<Node>();
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Node)) {
			return false;
		}
		Node other = (Node) obj;
		return this.URI.equals(other.getURI());
	}

	public void addNode(Node nod) {
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

	public int getPrioritar() {
		return prioritar;
	}

	public void setPrioritar(int i) {
		this.prioritar = i;
	}

	@Override
	public int compareTo(Node other) {
			
		return Integer.compare(prioritar, other.getPrioritar());
	}
}