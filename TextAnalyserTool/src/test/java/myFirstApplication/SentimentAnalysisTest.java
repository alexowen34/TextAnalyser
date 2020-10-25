package myFirstApplication;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import myFirstApplication.HandleTextFiles;
import myFirstApplication.LanguageAnalysis;

@RunWith(value = Parameterized.class)
public class SentimentAnalysisTest 
{
	//Stores the required data, for the @Test method, that is populated by the constructor method.
	private int testSelector;
	private String fileLocation;
	private String userInput;
	private String propertiesForNLP;
	private int sortingOptionType;
	private String expectedResult1;
	private String expectedResult2;
	private String expectedResult3;
	private String expectedResult4;
	private String expectedResult5; 
	private int expectedResult6;
	private String expectedResult7;

	//Constructor method that takes the required data and passes it to the global variables.
	public SentimentAnalysisTest(int testSelector, String fileLocation, String userInput, int decimalPlaces, String propertiesForNLP, 
			int sortingOptionType, String expectedResult1, String expectedResult2, String expectedResult3, String expectedResult4,
			String expectedResult5, int expectedResult6, String expectedResult7)
	{
		/*
		 * As the LanguageAnalysis class is instantiated in @Test methods, that class references the GUI class which uses JavaFX so 
		 * therefore the JavaFX enviroment needs to be initialized.
		 */
		JavaFXInitializer.initializeToolkit();
		
		this.testSelector = testSelector;
		this.fileLocation = fileLocation;
		this.userInput = userInput;
		this.propertiesForNLP = propertiesForNLP;
		this.sortingOptionType = sortingOptionType;
		this.expectedResult1 = expectedResult1;
		this.expectedResult2 = expectedResult2;
		this.expectedResult3 = expectedResult3;
		this.expectedResult4 = expectedResult4;
		this.expectedResult5 = expectedResult5;
		this.expectedResult6 = expectedResult6;
		this.expectedResult7 = expectedResult7;
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
			{1,"", "My name is Alex Owen.", 0, "tokenize, ssplit, pos, parse, sentiment", 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[My name is Alex Owen.]", "[Neutral]", "[0, 0, 1, 0, 0]",
			 "[0.00, 0.00, 100.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #1
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase1.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[My name is Alex Owen.]", "[Neutral]", "[0, 0, 1, 0, 0]",
			 "[0.00, 0.00, 100.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #2
			{1,"", "My name is Alex Owen. My name is Alex Owen. My name is Alex Owen.", 0, "tokenize, ssplit, pos, parse, sentiment", 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[My name is Alex Owen.]", "[Neutral]", "[0, 0, 1, 0, 0]",
			 "[0.00, 0.00, 100.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #3
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase3.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[My name is Alex Owen.]", "[Neutral]", "[0, 0, 1, 0, 0]",
			 "[0.00, 0.00, 100.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #4
			{1,"", "Hi there. My name is Alex Owen. How are you today?", 0, "tokenize, ssplit, pos, parse, sentiment", 0,
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[My name is Alex Owen., How are you today?, Hi there.]", 
			 "[Neutral, Neutral, Neutral]", "[0, 0, 3, 0, 0]",
			 "[0.00, 0.00, 100.00, 0.00, 0.00]", 3, "100.00"},
			
			//Test case #5
			{1,"", "I really really love this product!", 0, "tokenize, ssplit, pos, parse, sentiment", 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[I really really love this product!]", "[Very positive]", "[1, 0, 0, 0, 0]",
			 "[100.00, 0.00, 0.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #6
			{1,"", "I really love this product.", 0, "tokenize, ssplit, pos, parse, sentiment", 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[I really love this product.]", "[Positive]", "[0, 1, 0, 0, 0]",
			 "[0.00, 100.00, 0.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #7
			{1,"", "I don't really like this product.", 0, "tokenize, ssplit, pos, parse, sentiment", 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[I don't really like this product.]", "[Negative]", "[0, 0, 0, 1, 0]",
			 "[0.00, 0.00, 0.00, 100.00, 0.00]", 1, "100.00"},
			
			//Test case #8
			{1,"", "I really really hated this product!", 0, "tokenize, ssplit, pos, parse, sentiment", 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[I really really hated this product!]", "[Very negative]", "[0, 0, 0, 0, 1]",
			 "[0.00, 0.00, 0.00, 0.00, 100.00]", 1, "100.00"},
			
			//Test case #9
			{1,"", "Today I had a really bad experience! The customer services was really good and resolved it quickly. Overall, my experience was average.",
		     0, "tokenize, ssplit, pos, parse, sentiment", 0, "[Very positive, Positive, Neutral, Negative, Very negative]", 
		     "[The customer services was really good and resolved it quickly., Overall, my experience was average., Today I had a really bad experience!]",
		     "[Positive, Neutral, Negative]", "[0, 1, 1, 1, 0]", "[0.00, 33.33, 33.33, 33.33, 0.00]", 3, "100.00"},
	
			//Test case #10
			{1,"", "I really really loved the trip to Spain! Overall, it was great. The flight home was so so terrible, I woulden't recommend the airline to anyone.",
		     0, "tokenize, ssplit, pos, parse, sentiment", 0, "[Very positive, Positive, Neutral, Negative, Very negative]", 
		     "[The flight home was so so terrible, I woulden't recommend the airline to anyone., Overall, it was great., I really really loved the trip to Spain!]",
		     "[Very negative, Positive, Very positive]", "[1, 1, 0, 0, 1]", "[33.33, 33.33, 0.00, 0.00, 33.33]", 3, "100.00"},
			
			//Test case #11
			{1,"", "The service I received was outstanding! All your employees should be proud. I have now returned home. The flight took around 3 hours. I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality! It should be noted that the neighbours were really annoying. It was incredibly dissapointing and I'm very very annoyed! Please can this be passed on to the relevent people. Sadly, I also have a complaint about the food... It was disgusting! I can't believe a 4 star hotel would serve this. Overall, I would this recommend travel company. But this specific hotel resutrant should be avoided at all costs! Thanks, Alex Owen. I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality!",
		     0, "tokenize, ssplit, pos, parse, sentiment", 0, "[Very positive, Positive, Neutral, Negative, Very negative]",
		     "[The service I received was outstanding!, It should be noted that the neighbours were really annoying., I have now returned home., I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality!, I can't believe a 4 star hotel would serve this., Overall, I would this recommend travel company., All your employees should be proud., Sadly, I also have a complaint about the food... It was disgusting!, The flight took around 3 hours., Thanks, Alex Owen., It was incredibly dissapointing and I'm very very annoyed!, But this specific hotel resutrant should be avoided at all costs!, Please can this be passed on to the relevent people.]",
		     "[Very positive, Negative, Neutral, Very positive, Negative, Positive, Positive, Very negative, Neutral, Neutral, Very negative, Negative, Neutral]", 
		     "[2, 2, 4, 3, 2]", "[15.38, 15.38, 30.77, 23.08, 15.38]", 13, "100.00"},
			
			//Test case #12
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase12.txt", "",
		     0, "tokenize, ssplit, pos, parse, sentiment", 0, "[Very positive, Positive, Neutral, Negative, Very negative]",
		     "[The service I received was outstanding!, It should be noted that the neighbours were really annoying., I have now returned home., I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality!, I can't believe a 4 star hotel would serve this., Overall, I would this recommend travel company., All your employees should be proud., Sadly, I also have a complaint about the food... It was disgusting!, The flight took around 3 hours., Thanks, Alex Owen., It was incredibly dissapointing and I'm very very annoyed!, But this specific hotel resutrant should be avoided at all costs!, Please can this be passed on to the relevent people.]",
		     "[Very positive, Negative, Neutral, Very positive, Negative, Positive, Positive, Very negative, Neutral, Neutral, Very negative, Negative, Neutral]", 
		     "[2, 2, 4, 3, 2]", "[15.38, 15.38, 30.77, 23.08, 15.38]", 13, "100.00"},
			
			//Test case #13
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase12.txt", "",
		     0, "tokenize, ssplit, pos, parse, sentiment", 2, "[Very positive, Positive, Neutral, Negative, Very negative]",
		     "[The service I received was outstanding!, It should be noted that the neighbours were really annoying., I have now returned home., I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality!, I can't believe a 4 star hotel would serve this., Overall, I would this recommend travel company., All your employees should be proud., Sadly, I also have a complaint about the food... It was disgusting!, The flight took around 3 hours., Thanks, Alex Owen., It was incredibly dissapointing and I'm very very annoyed!, But this specific hotel resutrant should be avoided at all costs!, Please can this be passed on to the relevent people.]",
		     "[Very positive, Negative, Neutral, Very positive, Negative, Positive, Positive, Very negative, Neutral, Neutral, Very negative, Negative, Neutral]", 
		     "[2, 2, 4, 3, 2]", "[15.38, 15.38, 30.77, 23.08, 15.38]", 13, "100.00"},
			
			//Test case #14
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase12.txt", "",
		     0, "tokenize, ssplit, pos, parse, sentiment", 3, "[Very positive, Positive, Neutral, Negative, Very negative]",
		     "[The service I received was outstanding!, It should be noted that the neighbours were really annoying., I have now returned home., I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality!, I can't believe a 4 star hotel would serve this., Overall, I would this recommend travel company., All your employees should be proud., Sadly, I also have a complaint about the food... It was disgusting!, The flight took around 3 hours., Thanks, Alex Owen., It was incredibly dissapointing and I'm very very annoyed!, But this specific hotel resutrant should be avoided at all costs!, Please can this be passed on to the relevent people.]",
		     "[Very positive, Negative, Neutral, Very positive, Negative, Positive, Positive, Very negative, Neutral, Neutral, Very negative, Negative, Neutral]", 
		     "[2, 2, 4, 3, 2]", "[15.38, 15.38, 30.77, 23.08, 15.38]", 13, "100.00"},
			
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
	public void testSentiment() throws FileNotFoundException
	{
		if(testSelector == 2)
		{
			HandleTextFiles text = new HandleTextFiles(fileLocation);
			userInput = text.fileToString();
		}
		LanguageAnalysis test = new LanguageAnalysis(userInput, propertiesForNLP, 1, sortingOptionType);
		DecimalFormat round = new DecimalFormat("0.00");
		test.sentimentAnalysis();
		String result1 = Arrays.toString(test.getRowValues());
		String result2 = Arrays.toString(test.getSentence());
		String result3 = Arrays.toString(test.getSentiment());
		String result4 = Arrays.toString(test.getSummaryCount());
		double[] doublePlaceholder = test.getSummaryPercentage();
		ArrayList<String> stringPlaceholder = new ArrayList<String>();
		for(int i = 0; i < doublePlaceholder.length; i++)
		{
			stringPlaceholder.add(round.format(doublePlaceholder[i]));
		}
		String result5 = stringPlaceholder.toString();
		int result6 = test.getTotalCount();		
		String result7 = round.format(test.getTotalPercentage());
		assertEquals(expectedResult1, result1);
		assertEquals(expectedResult2, result2);
		assertEquals(expectedResult3, result3);
		assertEquals(expectedResult4, result4);
		assertEquals(expectedResult5, result5);
		assertEquals(expectedResult6, result6);
		assertEquals(expectedResult7, result7);
	}
}
