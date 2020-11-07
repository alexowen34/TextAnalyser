package com.myfirstapplication.supporttests;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.myfirstapplication.support.SortValues;

@RunWith(value = Parameterized.class)
public class SortValuesTest
{

	//Stores the required data, for the @Test method, that is populated by the constructor method.
	private String referenceString;
	private String values;
	private int orderType;
	private String expectedResult1;
	private String expectedResult2;
	
	//Constructor method that takes the required data and passes it to the global variables.
	public SortValuesTest(String referenceString, String values, int orderType, String expectedResult1, String expectedResult2)
	{
		this.referenceString = referenceString;
		this.values = values;
		this.orderType = orderType;
		this.expectedResult1 = expectedResult1;
		this.expectedResult2 = expectedResult2;
	}
	
	/*
	 * This method stores the data parameters for each test case. Each element in the list is passed to
	 * the constructor method. The data parameters consist of both inputs to be passed into the relevent
	 * object and the expected results to be returned.
	 */
	@Parameters
	public static Collection<Object[]> data()
	{
		return Arrays.asList(new Object[][] {
			
			//Test case #0
			{"Alex, Owen, 1234, !&\"*, A, B, C, D", "1000, 200, 5000, 4, 80, 2, 56, 1", 2,
			 "[1234, Alex, Owen, A, C, !&\"*, B, D]", "[5000, 1000, 200, 80, 56, 4, 2, 1]"},
			
			//Test case #1
			{"Alex, Owen, 1234, !&\"*, A, B, C, D", "1000, 200, 5000, 4, 80, 2, 56, 1", 3,
			 "[D, B, !&\"*, C, A, Owen, Alex, 1234]", "[1, 2, 4, 56, 80, 200, 1000, 5000]"},
			
			//Test case #2
			{"Alex, Owen, 1234, !&\"*, A, B, C, D", "1000, 200, 5000, 4, 80, 2, 56, 1", 4,
			 "[1234, Alex, Owen, A, C, !&\"*, B, D]", "[5000, 1000, 200, 80, 56, 4, 2, 1]"},
			
			//Test case #3
			{"Alex, Owen, 1234, !&\"*, A, B, C, D", "1000, 200, 5000, 4, 80, 2, 56, 1", 1,
			 "[1234, Alex, Owen, A, C, !&\"*, B, D]", "[5000, 1000, 200, 80, 56, 4, 2, 1]"},
			
			//Test case #4
			{"Alex, Owen, 1234, !&\"*, A, B, C, D", "1000, 200, 5000, 4, 80, 2, 56, 1", 20,
			 "[1234, Alex, Owen, A, C, !&\"*, B, D]", "[5000, 1000, 200, 80, 56, 4, 2, 1]"},
			
			//Test case #5
			{"Alex, Owen, 1234, !&\"*, A, B, C, D", "1000, 200, 5000, 4, 80, 2, 56, 1", -20,
			 "[1234, Alex, Owen, A, C, !&\"*, B, D]", "[5000, 1000, 200, 80, 56, 4, 2, 1]"},
			
			//Test case #6
			{"Hello, World, My, Name, Is, Alex Owen, How, Are, You, Today, I, Am, Good, Thank, Ta", 
   			 "100, -100, -50, 3, -1, -30, 50, 8, -70, 2, -20, 0, 90, 1, -3", 2,
			 "[Hello, Good, How, Are, Name, Today, Thank, Am, Is, Ta, I, Alex Owen, My, You, World]", 
			 "[100, 90, 50, 8, 3, 2, 1, 0, -1, -3, -20, -30, -50, -70, -100]"},
			
			//Test case #7
			{"Hello, World, My, Name, Is, Alex Owen, How, Are, You, Today, I, Am, Good, Thank, Ta", 
   			 "100, -100, -50, 3, -1, -30, 50, 8, -70, 2, -20, 0, 90, 1, -3", 3,
			 "[World, You, My, Alex Owen, I, Ta, Is, Am, Thank, Today, Name, Are, How, Good, Hello]", 
			 "[-100, -70, -50, -30, -20, -3, -1, 0, 1, 2, 3, 8, 50, 90, 100]"},
			
			//Test case #8
			{"Hello, Hello, Hello, Hello, Hi, My, Name, Is, Alex, Hello, World", 
   			 "10, 30, 20, 40, 2, 5, 9, 1, 8, 27, 21", 2,
			 "[Hello, World, Name, Alex, My, Hi, Is]", 
			 "[27, 21, 9, 8, 5, 2, 1]"},
			
			//Test case #9
			{"Hello, Hello, Hello, Hello, Hi, My, Name, Is, Alex, Hello, World", 
   			 "10, 30, 20, 40, 2, 5, 9, 1, 8, 27, 21", 3,
			 "[Is, Hi, My, Alex, Name, World, Hello]", 
			 "[1, 2, 5, 8, 9, 21, 27]"},
			
			//Test case #10
			{"Hi, there, my, name, is, alex, owen, how, are, you, today", 
   			 "10, 10, 10, 40, 20, 5, 9, 1, 40, 27, 20", 2,
			 "[are, name, you, today, is, Hi, there, my, owen, alex, how]", 
			 "[40, 40, 27, 20, 20, 10, 10, 10, 9, 5, 1]"},
			
			//Test case #11
			{"Hi, there, my, name, is, alex, owen, how, are, you, today", 
   			 "10, 10, 10, 40, 20, 5, 9, 1, 40, 27, 20", 3,
			 "[how, alex, owen, Hi, there, my, today, is, you, are, name]", 
			 "[1, 5, 9, 10, 10, 10, 20, 20, 27, 40, 40]"},
			
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
	public void testResultsForSummaryTable() throws FileNotFoundException
	{
		String[] referenceStringForSorting = referenceString.trim().split("\\s*,\\s*");
		String[] placeholder = values.trim().split("\\s*,\\s*");
		int[] valuesForSorting = new int[placeholder.length];
		for(int i = 0; i < valuesForSorting.length; i++)
		{
			valuesForSorting[i] = Integer.parseInt(placeholder[i]);
		}
		SortValues test1 = new SortValues(referenceStringForSorting, valuesForSorting, orderType);
		Map<String, Integer> sortedValues;
		sortedValues = test1.StringIntArrayToSortedHashMap();
        Object[] objectForStringArray = sortedValues.keySet().toArray();
        Object[] objectForCountArray = sortedValues.values().toArray();
        ArrayList<String> placeholderResult1 = new ArrayList<String>();
        ArrayList<Integer> placeholderResult2 = new ArrayList<Integer>();
        for(int i = 0; i < objectForStringArray.length; i++)
        {
        	if(placeholderResult1 != null)
        	{
            	placeholderResult1.add((String) objectForStringArray[i]);
            	placeholderResult2.add((Integer) objectForCountArray[i]);
        	}
        }
		String result1 = placeholderResult1.toString();
		String result2 = placeholderResult2.toString();
		assertEquals(expectedResult1, result1);
		assertEquals(expectedResult2, result2);
	}
}