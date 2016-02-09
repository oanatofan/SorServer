/**
 * 
 */
package com.Sor.Search;

import com.Sor.Model.*;
import com.Sor.Utils.Node;
import com.Sor.Utils.rdfHelper;

import java.text.ParseException;
import java.util.*;

/**
 * @author nightphonix
 *
 */
public class SimpleSearch {

	private rdfHelper helpper;

	public SimpleSearch() {
		helpper = rdfHelper.getHellper();
	}

	public List<Person> sugestPerson(String userId) throws ParseException {
		ArrayList<Node> nodes = helpper.createGraph();
		int n = nodes.size();
		Node currentPers = null;
		ArrayList<Node> curentFriends = new ArrayList<Node>();
		ArrayList<Node> tmpSugestedFriends = new ArrayList<Node>();
		ArrayList<Node> intSugestedFriends = new ArrayList<Node>();
		Node node;
		/*
		 * int[][] dist = new int[n][n]; for (int i = 0; i < n; i++) for (int j
		 * = i; j < n; j++) { dist[i][j] = 0;
		 * 
		 * } for (int i = 0; i < n; i++) for (int j = i; j < n; j++) { if (i !=
		 * j) { Node nodei = nodes.get(i); Node nodej = nodes.get(j); if
		 * (nodei.getValue().equals(nodej.getURI())) dist[i][j] = dist[i][j]
		 * +10;// deja prieteni
		 * 
		 * if (nodei.getValue().equals(nodej.getValue())) dist[i][j] =
		 * dist[i][j] + 1; }
		 * 
		 * } int cnK = 0; for (int i = 0; i < n; i++) { node = nodes.get(i);
		 * String subj = node.getURI(); if (subj.endsWith("#" + userId)) {
		 * currentPers = node; cnK = i; break; } } for (int i = 0; i < n; i++) {
		 * nodes.get(i).setPrioritar(dist[cnK][i]); }
		 *
		 * 
		 * n = nodes.size(); Collections.reverse(nodes);
		 */
		for (int i = 0; i < n; i++) {
			node = nodes.get(i);
			String subj = node.getURI();
			if (subj.endsWith("#" + userId)) {
				currentPers = node;
				break;
			}
		}

		n = currentPers.neighberhours.size();
		for (int i = 0; i < n; i++) {
			node = currentPers.neighberhours.get(i);
			String prop = node.getProperty();
			String subj = node.getURI();
			if (prop.contains("knows")) {
				curentFriends.add(node);
		    if (!subj.endsWith("#" + userId)) {
					tmpSugestedFriends.add(node);
				}
			}
		}
		n = curentFriends.size();
		Node nodej;
		for (int i = 0; i < n; i++) {
			node = curentFriends.get(i);
			int k = node.neighberhours.size();
			for (int j = 0; j < k; j++) {
				nodej = node.neighberhours.get(j);
				String prop = nodej.getProperty();
				String subj = nodej.getURI();
				if (prop.contains("knows")) {
					tmpSugestedFriends.add(0, nodej);
					if (!subj.endsWith("#" + userId)) {
						tmpSugestedFriends.add(nodej);
					}
				}

			}
		}
		n = tmpSugestedFriends.size();
		int nf = curentFriends.size();
		for(int i=0;i<n;i++)
			for(int j=0;j<nf;j++)
			{
				node=tmpSugestedFriends.get(i);
				nodej=curentFriends.get(j);
				if(!node.getURI().equals(nodej.getURI())){
					intSugestedFriends.add(node);
				}
			}
		List<Node> listSugestedFriends = new ArrayList<Node>();
		for (Node nod : intSugestedFriends) {
			if (!listSugestedFriends.contains(nod)&&(nod.getURI()!=currentPers.getURI())) {
				listSugestedFriends.add(nod);
			} else {
				int index = listSugestedFriends.indexOf(nod);
				int prioritar = listSugestedFriends.get(index).getPrioritar();
				listSugestedFriends.get(index).setPrioritar(prioritar + 1);
			}
		}
		Collections.reverse(listSugestedFriends);
		n = listSugestedFriends.size();
	    int contor = 0;
		System.out.println(n);
		List<Person> response = new ArrayList<Person>();
		for (int i = 0; i < n; i++) {

			node = listSugestedFriends.get(i);
			String subj = node.getURI();
			String persID = subj.substring((subj.indexOf("#") + 1));
			System.out.println("persid " + persID);
			// if (node.getPrioritar() > 0) {
			contor++;

			Person user = helpper.getPerson(persID);
			response.add(user);
			// }
			if (contor == 4)
				break;
		}
		return response;
	}

}
