package codingAssignment;

import java.util.HashMap;
import java.util.Map;

/*
 * This class transforms the keys and values in collections such as maps and hashmaps into arrays so they can be easily
 * displayed and worked within the classes that uses it (CharacterAnalysis, WordAnalysis & LanguageAnalysis).
 */

public class CollectionToArray 
{
	//Below is a range of global private variables that can be accessed across all methods in this class.

	//Below variables are populated via the constructor method.
	private Map<String, Integer> mapCollection;
	private HashMap<String, String> hashmapCollection;
	
	//Below variables are populated via methods in this class.
	private String[] mapKeysAsStringArray;
	private int[] mapValuesAsIntArray;
	private String[] hashmapKeysAsStringArray;
	private String[] hashmapValuesAsStringArray;	
	
	
	//Constructor method that is used to collect a Map of type string for it's keys and of type integer for it's values.
	public CollectionToArray(Map<String, Integer> mapCollection)
	{
		this.mapCollection = mapCollection;
	}
	
	//Constructor method that is used to collect a HashMap of type string both for it's keys and values.
	public CollectionToArray(HashMap<String, String> hashmapCollection)
	{
		this.hashmapCollection = hashmapCollection;
	}
	
	//Transforms Map<String, Integer> keys into a string array.
	public String[] mapKeysAsStringArray()
	{
        Object[] objectForStringArray = mapCollection.keySet().toArray();
		String[] mapKeysAsStringArray = new String[objectForStringArray.length];
        for(int i = 0; i < objectForStringArray.length; i++)
        {
        	mapKeysAsStringArray[i] = (String) objectForStringArray[i];
        }
        this.mapKeysAsStringArray = mapKeysAsStringArray;
		return this.mapKeysAsStringArray;
	}
	
	//Transforms Map<String, Integer> values into a int array.
	public int[] mapValuesAsIntArray()
	{
		Object[] objectForIntArray = mapCollection.values().toArray();
		int[] mapValuesAsIntArray = new int[objectForIntArray.length];
		for(int i = 0; i < objectForIntArray.length; i++)
		{
			mapValuesAsIntArray[i] = (Integer) objectForIntArray[i];
		}
		this.mapValuesAsIntArray = mapValuesAsIntArray;
		return this.mapValuesAsIntArray;
	}
	
	//Transforms HashMap<String, String> keys into a string array.
	public String[] hashmapKeysAsStringArray()
	{
		Object[] objectForStringArray = hashmapCollection.keySet().toArray();
		String[] hashmapKeysAsStringArray = new String[objectForStringArray.length];
		for(int i = 0; i < objectForStringArray.length; i++)
		{
			hashmapKeysAsStringArray[i] = (String) objectForStringArray[i];
		}
		this.hashmapKeysAsStringArray = hashmapKeysAsStringArray;
		return this.hashmapKeysAsStringArray;
	}
	
	//Transforms HashMap<String, String> values into a string array.
	public String[] hashmapValuesAsStringArray()
	{
		Object[] objectForStringArray = hashmapCollection.values().toArray();
		String[] hashmapValuesAsStringArray = new String[objectForStringArray.length];
		for(int i = 0; i < objectForStringArray.length; i++)
		{
			hashmapValuesAsStringArray[i] = (String) objectForStringArray[i];
		}
		this.hashmapValuesAsStringArray = hashmapValuesAsStringArray;
		return this.hashmapValuesAsStringArray;
	}
}
