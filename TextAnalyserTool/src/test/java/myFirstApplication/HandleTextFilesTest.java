package myFirstApplication;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import myFirstApplication.HandleTextFiles;

@RunWith(value = Parameterized.class)
public class HandleTextFilesTest
{
	//Stores the required data, for the @Test method, that is populated by the constructor method.
	private int testSelector;
	private String fileLocation;
	private String expectedResult1;
	private int expectedResult2;
	
	//Constructor method that takes the required data and passes it to the global variables.
	public HandleTextFilesTest(int testSelector, String fileLocation, String expectedResult1, int expectedResult2)
	{
		this.testSelector = testSelector;
		this.fileLocation = fileLocation;
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
			{1,"TestData/HandleTextFilesTestFiles/HandleTextFiles-TestCase0.txt", "Hello World", 1},
			
			//Test case #1
			{1,"TestData/HandleTextFilesTestFiles/HandleTextFiles-TestCase1.txt", "Hello World. My name is Alex Owen. Whats your name?", 3},
			
			//Test case #2
			{1,"TestData/HandleTextFilesTestFiles/HandleTextFiles-TestCase2.txt", "Hi there, How are you today. I am good thanks. How are you? I am well thankyou. Kind regards.", 6},
			
			//Test case #3
			{1,"TestData/HandleTextFilesTestFiles/HandleTextFiles-TestCase3.txt", "Hello World. My name is Alex Owen. Whats your name?", 3},
		
			//Test case #4
			{2,"AppInputData/POStags.txt", "{JJ=Adjective, "
					+ "NN=Noun, "
					+ "singular or mass, "
					+ "WRB=Wh-adverb, "
					+ "LS=List item marker, "
					+ "PRP=Personal pronoun, "
					+ "DT=Determiner, "
					+ "FW=Foreign word, "
					+ "NNP=Proper noun, singular, "
					+ "JJS=Adjective, superlative, "
					+ "NNS=Noun, plural, "
					+ "JJR=Adjective, comparative, "
					+ "UH=Interjection, "
					+ "MD=Modal, "
					+ "VBD=Verb, past tense, "
					+ "WP=Wh-pronoun, "
					+ "VBG=Verb, gerund or present participle, "
					+ "CC=Coordinating conjunction, "
					+ "CD=Cardinal number, "
					+ "PDT=Predeterminer, "
					+ "RBS=Adverb, superlative, "
					+ "RBR=Adverb, comparative, "
					+ "VBN=Verb, past participle, "
					+ "IN=Preposition or subordinating conjunction, "
					+ "VBP=Verb, non-3rd person singular present, "
					+ "SYM=Symbol, "
					+ "WDT=Wh-determiner, "
					+ "NNPS=Proper noun, plural, "
					+ "WP$=Possessive wh-pronoun, "
					+ "VB=Verb, base form, "
					+ "VBZ=Verb, 3rd person singular present, "
					+ "RB=Adverb, "
					+ "EX=Existential there, "
					+ "PRP$=Possessive pronoun, "
					+ "POS=Possessive ending, "
					+ "TO=to, "
					+ "RP=Particle}", 0},
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
	public void testBarChart() throws FileNotFoundException
	{
		HandleTextFiles test1 = new HandleTextFiles(fileLocation);
		String result1 = null;
		int result2 = 0;
		if(testSelector == 1)
		{
			test1.fileToString();
			result1 = test1.toString();
			result2 = test1.getParagraphCount();
			assertEquals(expectedResult1, result1);
			assertEquals(expectedResult2, result2);
		}
		if(testSelector == 2)
		{
			result1 = test1.fileToHashMap().toString();
			assertEquals(expectedResult1, result1);
		}
	}
}