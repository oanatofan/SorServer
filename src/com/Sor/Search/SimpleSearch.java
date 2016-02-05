/**
 * 
 */
package com.Sor.Search;

import com.Sor.Model.*;
import com.Sor.Utils.rdfHelper;

import java.util.*;
/**
 * @author nightphonix
 *
 */
public class SimpleSearch {

	private rdfHelper helpper;
	public SimpleSearch(){
		helpper=rdfHelper.getHellper();
	}
	public ArrayList<Person> sugestPerson(String userId)
	{
		ArrayList<Person> rdf= helpper.getRdf();
		for (Person person : rdf) {
			
		}
		return rdf;
		}
}
