package com.myfirstapplication.analysistests;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.myfirstapplication.analysis.WordAnalysis;
import com.myfirstapplication.support.HandleTextFiles;

@RunWith(value = Parameterized.class)
public class WordAnalysisTest
{
	//Stores the required data, for the @Test method, that is populated by the constructor method.
	private int testSelector;
	private String fileLocation;
	private String userInput;
	private String propertiesForNLP;
	private int paragraphCounter;
	private int expectedResult1;
	private String expectedResult2;
	private String expectedResult3;
	private int expectedResult4;
	private int expectedResult5;
	
	//Constructor method that takes the required data and passes it to the global variables.
	public WordAnalysisTest(int testSelector, String fileLocation, String userInput, String propertiesForNLP, int paragraphCounter, int expectedResult1, 
			String expectedResult2, String expectedResult3, int expectedResult4, int expectedResult5)
	{
		/*
		 * As the WordAnalysis class is instantiated in @Test methods, that class references the GUI class which uses JavaFX so 
		 * therefore the JavaFX enviroment needs to be initialized.
		 */
		JavaFXInitializer.initializeToolkit();
		
		this.testSelector = testSelector;
		this.fileLocation = fileLocation;
		this.userInput = userInput;
		this.propertiesForNLP = propertiesForNLP;
		this.paragraphCounter = paragraphCounter;
		this.expectedResult1 = expectedResult1;
		this.expectedResult2 = expectedResult2;
		this.expectedResult3 = expectedResult3;
		this.expectedResult4 = expectedResult4;
		this.expectedResult5 = expectedResult5;
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
			{1, "", "Hello World", "tokenize, ssplit", 1, 2, "5.00", "Text does not contain a distinct most frequent word",
			 1, 1},
			
			//Test case #1
			{1, "", "Hello World!       Hello World.                Hello World?", "tokenize, ssplit", 1, 6, "5.00", 
			 "Text does not contain a distinct most frequent word", 1, 3},

			//Test case #2
			{1, "", "From the Wright brothers' first flight at Kitty Hawk in 1903 to the vertical take-off 'air taxis' showcased at this year's CES, the story of aviation is a story of invention.",
			 "tokenize, ssplit", 1, 30, "4.43", "the", 1, 1},

			//Test case #3
			{1, "", "Hello World. World hello. Hello. World hello.", "tokenize, ssplit", 1, 7, "5.00", 
			 "hello", 1, 4},
			
			//Test case #4
			{1, "", "Hello World. World hello. World hello.", "tokenize, ssplit", 1, 6, "5.00", 
			 "Text does not contain a distinct most frequent word", 1, 3},
			
			//Test case #5
			{1, "", "Alex alex Alex Alex alex.", "tokenize, ssplit", 1, 5, "4.00", 
			 "alex", 1, 1},
			
			//Test case #6
			{1, "", "Dear Mr. Owen, how are you today? You are 21.5 years old!", "tokenize, ssplit", 1, 11, "3.45",
			 "Text does not contain a distinct most frequent word", 1, 2},
			
			//Test case #7
			{2, "src/test/resources/TestData&Documentation/WordAnalysisTestFiles/WordAnalysis-TestCase6.txt", "", "tokenize, ssplit", 0, 23, "4.13",
			 "i", 3, 3},
			 
			//Test case #8
			{2, "src/test/resources/TestData&Documentation/WordAnalysisTestFiles/WordAnalysis-TestCase7.txt", "", "tokenize, ssplit", 0, 12, "3.83",
			 "is", 1, 1},
			
			//Test case #9
			{2, "src/test/resources/TestData&Documentation/WordAnalysisTestFiles/WordAnalysis-TestCase8.txt", "", "tokenize, ssplit", 0, 128, "4.53",
			 "the", 3, 6},

			//Test case #10
			{1, "", "1234567890", "tokenize, ssplit", 0, 0, "0.00",
			 "Text does not include any valid words", 0, 0},
			 
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
		if(testSelector == 2)
		{
			HandleTextFiles text1 = new HandleTextFiles(fileLocation);
			userInput = text1.fileToString();
			paragraphCounter = text1.getParagraphCount();
		}
		WordAnalysis test1 = new WordAnalysis(userInput, propertiesForNLP, paragraphCounter);
		int wordCount = test1.wordCount();
		String mostFrequentWord = test1.mostFrequentWord();
		if(testSelector == 1)
		{
			paragraphCounter = test1.getParagraphCount();
		}
		test1.sentencesListAndCount();
		int sentenceCount = test1.getSentenceCount();
		DecimalFormat round = new DecimalFormat("0.00");
		String averageWordLengthString = round.format(test1.averageWordLength());
		assertEquals(expectedResult1, wordCount);
		assertEquals(expectedResult2, averageWordLengthString);
		assertEquals(expectedResult3, mostFrequentWord);
		assertEquals(expectedResult4, paragraphCounter);
		assertEquals(expectedResult5, sentenceCount);
	}
}
