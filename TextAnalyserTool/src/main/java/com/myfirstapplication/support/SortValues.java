package com.myfirstapplication.support;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

/*
 * This class sorts the results in DESC or ASC order depending on what the user has specified in the GUI. Both the CharacterAnalysis class and 
 * 'partsOfSpeechAnalsis' method within the LanguageAnalysis class use this object.
 */
public class SortValues
{
	//Below is a range of global private variables that can be accessed across all methods across this class.

	//Below variables are populated via the constructor method.
	private int[] values;
	private int orderType;
	private String[] referenceString;

	//Below variables are populated via methods in this class.
	private HashMap<String , Integer> sortedReferenceStringAndValues;
	
	/*
	 * Constructor method that is used by CharacterAnalysis class and 'partsOfSpeechAnalsis' method within the LanguageAnalysis class to 
	 * pass in required data to this object.
	 */
	public SortValues(String[] referenceString, int[] values, int orderType)
	{
		this.referenceString = referenceString;
		this.values = values;
		this.orderType = orderType;
	}
	    
	/*
	 * Populates HashMap with reference string and count/values array. Calls sortByValue method to sort HashMap using a LinkedList and the Comparator function 
	 * to do a Collections.sort in either DESC or ASC order depending on user option. It then returns the HashMap in the specified sorted order by using a
	 * LinkedHashMap to maintain the sorted insertion order.
	 */
	public HashMap<String, Integer> StringIntArrayToSortedHashMap()
	{
		boolean sort = false;
		switch(orderType)
		{
			case 2: sort = false;
			break;
			case 3: sort = true;
			break;
		}
		HashMap<String, Integer> stringAndCount = new HashMap<String, Integer>();
		for(int i = 0; i < values.length; i++)
		{
			stringAndCount.put(referenceString[i], values[i]);
		}
		HashMap<String, Integer> sortedHashMap = sortByValue(stringAndCount, sort);
		sortedReferenceStringAndValues = sortedHashMap;
		return sortedReferenceStringAndValues;
	}
	
	//Private method that is called from 'StringIntArrayToSortedHashMap' method - see comments above this method for more detail.
    private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hashMapForSorting, boolean sortOrder)
    {
    	LinkedList<Entry<String, Integer>> sortedList = new LinkedList<Entry<String, Integer>>(hashMapForSorting.entrySet());
    	Collections.sort(sortedList, new Comparator<Entry<String, Integer>>()
    	{
    		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)
    		{
    			if (sortOrder == true)
    			{
    				return o1.getValue().compareTo(o2.getValue());
    			}
    			else
    			{
    				return o2.getValue().compareTo(o1.getValue());
    			}
	       }
    	}
    	);
    	HashMap<String, Integer> sortedHashMap = new LinkedHashMap<String, Integer>();
    	for (Entry<String, Integer> entry : sortedList)
    	{
    		sortedHashMap.put(entry.getKey(), entry.getValue());
    	}
    	return sortedHashMap;
    }
}
