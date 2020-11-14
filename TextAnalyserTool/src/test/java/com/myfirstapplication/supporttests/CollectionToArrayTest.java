package com.myfirstapplication.supporttests;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.myfirstapplication.support.CollectionToArray;

@RunWith(value = Parameterized.class)
public class CollectionToArrayTest 
{
	//Stores the required data, for the @Test method, that is populated by the constructor method.
	private int testSelector;
	private String stringForKey;
	private String stringForValue;
	private String expectedResult1;
	private String expectedResult2;
	
	//Constructor method that takes the required data and passes it to the global variables.
	public CollectionToArrayTest(int testSelector, String stringForKey, String stringForValue, String expectedResult1, String expectedResult2)
	{
		this.testSelector = testSelector;
		this.stringForKey = stringForKey;
		this.stringForValue = stringForValue;
		this.expectedResult1 = expectedResult1;
		this.expectedResult2 = expectedResult2;
	}
	
	/*
	 * This method stores the data parameters for each test case. Each element in the list is passed to
	 * the constructor method. The data parameters consist of both inputs to be passed into the relevant
	 * object and the expected results to be returned.
	 */
	@Parameters
	public static Collection<Object[]> data()
	{
		return Arrays.asList(new Object[][] {
			
			//Test case #0
			{1,"A, B, C, D, E, F, G",
			 "1, 2, 3, 4, 5, 6, 7",
			 "[A, B, C, D, E, F, G]",
			 "[1, 2, 3, 4, 5, 6, 7]"},
			
			//Test case #1
			{1,"A, A, A, B, B, C, D, E, E, F, G",
			 "1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2",
			 "[A, B, C, D, E, F, G]",
			 "[1, 1, 2, 1, 2, 1, 2]"},
			
			//Test case #2
			{2,"A, B, C, D, E, F, G",
			 "A, !, 3, Hello, L, O, D",
			 "[A, B, C, D, E, F, G]",
			 "[A, !, 3, Hello, L, O, D]"},
			
			//Test case #3
			{2,"A, A, A, B, B, C, D, E, E, F, G",
			 "Hello, Hello, World, How, Are, Are, You, Hello, Hello, World, Today",
			 "[A, B, C, D, E, F, G]",
			 "[World, Are, Are, You, Hello, World, Today]"}
			 
		});
	}
	
	/*
	 * This is the method which actually does the testing with the data that has been inputted above.
	 * This @Test method will be called and ran for each parameter entered above.
	 * It does assertEquals statements to check if the two pieces of data (i.e. the expected result and the 
	 * actual result) match each other. If they do match, the result will be a pass, if they do not match then the 
	 * result will be a failure or error.
	 */
	@Test
	public void testCollectionToArray()
	{
		String[] placeholderForKey = stringForKey.trim().split("\\s*,\\s*");
		String[] placeholderForValue = stringForValue.trim().split("\\s*,\\s*");
		String result1 = null;
		String result2 = null;
		if(testSelector == 1)
		{
			int placeholderForIntValue = 0;
			HashMap<String, Integer> hashmapForTest = new HashMap<String, Integer>();
			for(int i = 0; i < placeholderForKey.length; i++)
			{
				placeholderForIntValue = Integer.parseInt(placeholderForValue[i]);
				hashmapForTest.put(placeholderForKey[i], placeholderForIntValue);
			}
			CollectionToArray test1 = new CollectionToArray(hashmapForTest);
			result1 = Arrays.toString(test1.mapKeysAsStringArray());
			result2 = Arrays.toString(test1.mapValuesAsIntArray());
		}
		if(testSelector == 2)
		{
			HashMap<String, String> hashmapForTest = new HashMap<String, String>();
			for(int i = 0; i< placeholderForKey.length; i++)
			{
				hashmapForTest.put(placeholderForKey[i], placeholderForValue[i]);
			}
			CollectionToArray test1 = new CollectionToArray(hashmapForTest);
			result1 = Arrays.toString(test1.hashmapKeysAsStringArray());
			result2 = Arrays.toString(test1.hashmapValuesAsStringArray());
		}
		assertEquals(expectedResult1, result1);
		assertEquals(expectedResult2, result2);
	}
}



