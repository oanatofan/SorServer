/**
 * 
 */
package com.Sor.Search;

import com.Sor.Model.*;
import com.Sor.Utils.Constants;
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
		Node currentPers=null;
		ArrayList<Node> curentFriends = new ArrayList<Node>();
		ArrayList<Node> sugestedFriends = new ArrayList<Node>();
		Node node;
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
					sugestedFriends.add(node);
				}
			}
		}
		n = curentFriends.size();
		for (int i = 0; i < n; i++) {
			node = curentFriends.get(i);
			int k = node.neighberhours.size();
			for (int j = 0; j < k; j++) {
				Node nodej = node.neighberhours.get(j);
				String prop = nodej.getProperty();
				String subj = nodej.getURI();
				if (prop.contains("knows")) {
					sugestedFriends.add(0, nodej);
					if (!subj.endsWith("#" + userId)) {
						sugestedFriends.add(nodej);
					}
				}

			}
		}
		n = sugestedFriends.size();
		List<Person> response = new ArrayList<Person>();
		for (int i = 0; i < n; i++) {
			node = sugestedFriends.get(i);
			String subj = node.getURI();
			String persID = subj.substring((subj.indexOf("#") + 1));
			Person user = helpper.getPerson(persID);
			response.add(user);
		}
		return response;
	}

}
