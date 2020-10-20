package codingAssignment;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import javafx.embed.swing.JFXPanel;

@RunWith(value = Parameterized.class)
public class LanguageAnalysisTest 
{
	//Stores the required data, for the @Test method, that is populated by the constructor method.
	private int testSelector;
	private String fileLocation;
	private String userInput;
	private String propertiesForNLP;
	private int analysisOption;
	private int sortingOptionType;
	private String expectedResult1;
	private String expectedResult2;
	private String expectedResult3;
	private String expectedResult4;
	private String expectedResult5; 
	private int expectedResult6;
	private String expectedResult7;
	
    @BeforeClass
    public static void initToolkit() throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });
        if(!latch.await(5L, TimeUnit.SECONDS))
        {
        	throw new ExceptionInInitializerError();
        }
    }

	//Constructor method that takes the required data and passes it to the global variables.
	public LanguageAnalysisTest(int testSelector, String fileLocation, String userInput, int decimalPlaces, String propertiesForNLP, int analysisOption, 
			int sortingOptionType, String expectedResult1, String expectedResult2, String expectedResult3, String expectedResult4,
			String expectedResult5, int expectedResult6, String expectedResult7)
	{
		this.testSelector = testSelector;
		this.fileLocation = fileLocation;
		this.userInput = userInput;
		this.propertiesForNLP = propertiesForNLP;
		this.analysisOption = analysisOption;
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
			
			//SentimentAnalysis

			//Test case #0
			{1,"", "My name is Alex Owen.", 0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[My name is Alex Owen.]", "[Neutral]", "[0, 0, 1, 0, 0]",
			 "[0.00, 0.00, 100.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #1
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase1.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[My name is Alex Owen.]", "[Neutral]", "[0, 0, 1, 0, 0]",
			 "[0.00, 0.00, 100.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #2
			{1,"", "My name is Alex Owen. My name is Alex Owen. My name is Alex Owen.", 0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[My name is Alex Owen.]", "[Neutral]", "[0, 0, 1, 0, 0]",
			 "[0.00, 0.00, 100.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #3
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase3.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[My name is Alex Owen.]", "[Neutral]", "[0, 0, 1, 0, 0]",
			 "[0.00, 0.00, 100.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #4
			{1,"", "Hi there. My name is Alex Owen. How are you today?", 0, "tokenize, ssplit, pos, parse, sentiment", 1, 0,
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[My name is Alex Owen., How are you today?, Hi there.]", 
			 "[Neutral, Neutral, Neutral]", "[0, 0, 3, 0, 0]",
			 "[0.00, 0.00, 100.00, 0.00, 0.00]", 3, "100.00"},
			
			//Test case #5
			{1,"", "I really really love this product!", 0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[I really really love this product!]", "[Very positive]", "[1, 0, 0, 0, 0]",
			 "[100.00, 0.00, 0.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #6
			{1,"", "I really love this product.", 0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[I really love this product.]", "[Positive]", "[0, 1, 0, 0, 0]",
			 "[0.00, 100.00, 0.00, 0.00, 0.00]", 1, "100.00"},
			
			//Test case #7
			{1,"", "I don't really like this product.", 0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[I don't really like this product.]", "[Negative]", "[0, 0, 0, 1, 0]",
			 "[0.00, 0.00, 0.00, 100.00, 0.00]", 1, "100.00"},
			
			//Test case #8
			{1,"", "I really really hated this product!", 0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, 
			 "[Very positive, Positive, Neutral, Negative, Very negative]", "[I really really hated this product!]", "[Very negative]", "[0, 0, 0, 0, 1]",
			 "[0.00, 0.00, 0.00, 0.00, 100.00]", 1, "100.00"},
			
			//Test case #9
			{1,"", "Today I had a really bad experience! The customer services was really good and resolved it quickly. Overall, my experience was average.",
		     0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, "[Very positive, Positive, Neutral, Negative, Very negative]", 
		     "[The customer services was really good and resolved it quickly., Overall, my experience was average., Today I had a really bad experience!]",
		     "[Positive, Neutral, Negative]", "[0, 1, 1, 1, 0]", "[0.00, 33.33, 33.33, 33.33, 0.00]", 3, "100.00"},
	
			//Test case #10
			{1,"", "I really really loved the trip to Spain! Overall, it was great. The flight home was so so terrible, I woulden't recommend the airline to anyone.",
		     0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, "[Very positive, Positive, Neutral, Negative, Very negative]", 
		     "[The flight home was so so terrible, I woulden't recommend the airline to anyone., Overall, it was great., I really really loved the trip to Spain!]",
		     "[Very negative, Positive, Very positive]", "[1, 1, 0, 0, 1]", "[33.33, 33.33, 0.00, 0.00, 33.33]", 3, "100.00"},
			
			//Test case #11
			{1,"", "The service I received was outstanding! All your employees should be proud. I have now returned home. The flight took around 3 hours. I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality! It should be noted that the neighbours were really annoying. It was incredibly dissapointing and I'm very very annoyed! Please can this be passed on to the relevent people. Sadly, I also have a complaint about the food... It was disgusting! I can't believe a 4 star hotel would serve this. Overall, I would this recommend travel company. But this specific hotel resutrant should be avoided at all costs! Thanks, Alex Owen. I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality!",
		     0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, "[Very positive, Positive, Neutral, Negative, Very negative]",
		     "[The service I received was outstanding!, It should be noted that the neighbours were really annoying., I have now returned home., I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality!, I can't believe a 4 star hotel would serve this., Overall, I would this recommend travel company., All your employees should be proud., Sadly, I also have a complaint about the food... It was disgusting!, The flight took around 3 hours., Thanks, Alex Owen., It was incredibly dissapointing and I'm very very annoyed!, But this specific hotel resutrant should be avoided at all costs!, Please can this be passed on to the relevent people.]",
		     "[Very positive, Negative, Neutral, Very positive, Negative, Positive, Positive, Very negative, Neutral, Neutral, Very negative, Negative, Neutral]", 
		     "[2, 2, 4, 3, 2]", "[15.38, 15.38, 30.77, 23.08, 15.38]", 13, "100.00"},
			
			//Test case #12
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase12.txt", "",
		     0, "tokenize, ssplit, pos, parse, sentiment", 1, 0, "[Very positive, Positive, Neutral, Negative, Very negative]",
		     "[The service I received was outstanding!, It should be noted that the neighbours were really annoying., I have now returned home., I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality!, I can't believe a 4 star hotel would serve this., Overall, I would this recommend travel company., All your employees should be proud., Sadly, I also have a complaint about the food... It was disgusting!, The flight took around 3 hours., Thanks, Alex Owen., It was incredibly dissapointing and I'm very very annoyed!, But this specific hotel resutrant should be avoided at all costs!, Please can this be passed on to the relevent people.]",
		     "[Very positive, Negative, Neutral, Very positive, Negative, Positive, Positive, Very negative, Neutral, Neutral, Very negative, Negative, Neutral]", 
		     "[2, 2, 4, 3, 2]", "[15.38, 15.38, 30.77, 23.08, 15.38]", 13, "100.00"},
			
			//Test case #13
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase12.txt", "",
		     0, "tokenize, ssplit, pos, parse, sentiment", 1, 2, "[Very positive, Positive, Neutral, Negative, Very negative]",
		     "[The service I received was outstanding!, It should be noted that the neighbours were really annoying., I have now returned home., I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality!, I can't believe a 4 star hotel would serve this., Overall, I would this recommend travel company., All your employees should be proud., Sadly, I also have a complaint about the food... It was disgusting!, The flight took around 3 hours., Thanks, Alex Owen., It was incredibly dissapointing and I'm very very annoyed!, But this specific hotel resutrant should be avoided at all costs!, Please can this be passed on to the relevent people.]",
		     "[Very positive, Negative, Neutral, Very positive, Negative, Positive, Positive, Very negative, Neutral, Neutral, Very negative, Negative, Neutral]", 
		     "[2, 2, 4, 3, 2]", "[15.38, 15.38, 30.77, 23.08, 15.38]", 13, "100.00"},
			
			//Test case #14
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase12.txt", "",
		     0, "tokenize, ssplit, pos, parse, sentiment", 1, 3, "[Very positive, Positive, Neutral, Negative, Very negative]",
		     "[The service I received was outstanding!, It should be noted that the neighbours were really annoying., I have now returned home., I was throughly impressed on how comfortable the flight was - I'm so so happy with the quality!, I can't believe a 4 star hotel would serve this., Overall, I would this recommend travel company., All your employees should be proud., Sadly, I also have a complaint about the food... It was disgusting!, The flight took around 3 hours., Thanks, Alex Owen., It was incredibly dissapointing and I'm very very annoyed!, But this specific hotel resutrant should be avoided at all costs!, Please can this be passed on to the relevent people.]",
		     "[Very positive, Negative, Neutral, Very positive, Negative, Positive, Positive, Very negative, Neutral, Neutral, Very negative, Negative, Neutral]", 
		     "[2, 2, 4, 3, 2]", "[15.38, 15.38, 30.77, 23.08, 15.38]", 13, "100.00"},
			
			
			//POSanalysis
			
			//Test case #15
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase15.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Wh-pronoun]", "[what, whom, who]", "[Wh-pronoun, Wh-pronoun, Wh-pronoun]", "[3]",
			 "[100.00]", 3, "100.00"},

			//Test case #16
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase16.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Wh-determiner]", "[which, that, whatever]", "[Wh-determiner, Wh-determiner, Wh-determiner]", "[3]",
			 "[100.00]", 3, "100.00"},
			
			//Test case #17
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase17.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Wh-adverb]", "[how, why, where, when]", "[Wh-adverb, Wh-adverb, Wh-adverb, Wh-adverb]", "[4]",
			 "[100.00]", 4, "100.00"},
			
			//Test case #18
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase18.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Verb, past tense]", "[were, came, was, said, did]", "[Verb, past tense, Verb, past tense, Verb, past tense, Verb, past tense, Verb, past tense]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #19
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase19.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Verb, past participle]", "[been, created, driven, done, stated]", "[Verb, past participle, Verb, past participle, Verb, past participle, Verb, past participle, Verb, past participle]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #20
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase20.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Verb, non-3rd person singular present]", "[provide, remain, want, propose, seem]", "[Verb, non-3rd person singular present, Verb, non-3rd person singular present, Verb, non-3rd person singular present, Verb, non-3rd person singular present, Verb, non-3rd person singular present]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #21
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase21.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Verb, gerund or present participle]", "[retiring, killing, replacing, being, saying]", "[Verb, gerund or present participle, Verb, gerund or present participle, Verb, gerund or present participle, Verb, gerund or present participle, Verb, gerund or present participle]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #22
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase22.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Verb, base form]", "[add, bode, die, be, prove]", "[Verb, base form, Verb, base form, Verb, base form, Verb, base form, Verb, base form]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #23
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase23.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Verb, 3rd person singular present]", "[comes, is, has, seems, proves]","[Verb, 3rd person singular present, Verb, 3rd person singular present, Verb, 3rd person singular present, Verb, 3rd person singular present, Verb, 3rd person singular present]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #24
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase24.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[to]", "[na, to, ta]","[to, to, to]", "[3]",
			 "[100.00]", 3, "100.00"},
			
			//Test case #25
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase25.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Symbol]", "[|, \\, ¬, ~, _]","[Symbol, Symbol, Symbol, Symbol, Symbol]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #26
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase26.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Proper noun, singular]", "[saturday, london, july, wednesday, washington]","[Proper noun, singular, Proper noun, singular, Proper noun, singular, Proper noun, singular, Proper noun, singular]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #27
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase27.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Noun, plural]", "[appeals, israelians, forces, muslims, motorsports]","[Noun, plural, Noun, plural, Noun, plural, Noun, plural, Noun, plural]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #28
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase28.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Preposition or subordinating conjunction]", "[though, whether, among, than, between]","[Preposition or subordinating conjunction, Preposition or subordinating conjunction, Preposition or subordinating conjunction, Preposition or subordinating conjunction, Preposition or subordinating conjunction]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #29
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase29.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Personal pronoun]", "[itself, them, he, him, they]","[Personal pronoun, Personal pronoun, Personal pronoun, Personal pronoun, Personal pronoun]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #30
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase30.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Possessive pronoun]", "[his, her, their, its, your]","[Possessive pronoun, Possessive pronoun, Possessive pronoun, Possessive pronoun, Possessive pronoun]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #31
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase31.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Noun, singular or mass]", "[punchline, week, idea, article, story]","[Noun, singular or mass, Noun, singular or mass, Noun, singular or mass, Noun, singular or mass, Noun, singular or mass]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #32
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase32.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Modal]", "[can, would, will, could, should]","[Modal, Modal, Modal, Modal, Modal]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #33
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase33.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Interjection]", "[hi, uh, eh, yes, hello]","[Interjection, Interjection, Interjection, Interjection, Interjection]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #34
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase34.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Foreign word]", "[c'est, etc, déjà, guerre, comme]","[Foreign word, Foreign word, Foreign word, Foreign word, Foreign word]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #35
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase35.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Determiner]", "[some, another, this, an, any]","[Determiner, Determiner, Determiner, Determiner, Determiner]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #36
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase36.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Coordinating conjunction]", "[nor, but, or, and, neither]","[Coordinating conjunction, Coordinating conjunction, Coordinating conjunction, Coordinating conjunction, Coordinating conjunction]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #37
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase37.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Cardinal number]", "[15, 4, 26/07/1998, sixteen, two]","[Cardinal number, Cardinal number, Cardinal number, Cardinal number, Cardinal number]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #38
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase38.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Adverb]", "[very, closely, currently, temporarily, soon]","[Adverb, Adverb, Adverb, Adverb, Adverb]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #39
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase39.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Adjective, superlative]", "[hottest, fewest, slightest, nearest, wildest]","[Adjective, superlative, Adjective, superlative, Adjective, superlative, Adjective, superlative, Adjective, superlative]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #40
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase40.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Adjective, comparative]", "[larger, fatter, cleaner, lower, sadder]","[Adjective, comparative, Adjective, comparative, Adjective, comparative, Adjective, comparative, Adjective, comparative]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #41
			{2,"TestData/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase41.txt", "", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Adjective]", "[eastern, nervous, succesful, spiritual, associate]","[Adjective, Adjective, Adjective, Adjective, Adjective]", "[5]",
			 "[100.00]", 5, "100.00"},
			
			//Test case #42
			{1,"", "Hello World, my name is Alex Owen.", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Interjection, Proper noun, singular, Noun, singular or mass, Possessive pronoun, Verb, 3rd person singular present]",
			 "[Alex, Hello, Owen, name, is, World, my]","[Proper noun, singular, Interjection, Proper noun, singular, Noun, singular or mass, Verb, 3rd person singular present, Proper noun, singular, Possessive pronoun]", 
			 "[1, 3, 1, 1, 1]", "[14.29, 42.86, 14.29, 14.29, 14.29]", 7, "100.00"},

			//Test case #43
			{1,"", "Alex Alex Owen Alex Owen Owen Alex", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Proper noun, singular]", "[Alex, Owen]","[Proper noun, singular, Proper noun, singular]", "[2]",
			 "[100.00]", 2, "100.00"},
						
			//Test case #44
			{1,"", "This application uses parts of speech analysis to gain a deeper understanding of the language used in the given text.", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Noun, singular or mass, Preposition or subordinating conjunction, Determiner, Noun, plural, Verb, base form, to, Verb, past participle, Adjective, comparative, Verb, 3rd person singular present]",
			 "[a, given, in, language, used, analysis, gain, the, application, speech, of, parts, This, understanding, uses, deeper, to, text]",
			 "[Determiner, Verb, past participle, Preposition or subordinating conjunction, Noun, singular or mass, Verb, past participle, Noun, singular or mass, Verb, base form, Determiner, Noun, singular or mass, Noun, singular or mass, Preposition or subordinating conjunction, Noun, plural, Determiner, Noun, singular or mass, Verb, 3rd person singular present, Adjective, comparative, to, Noun, singular or mass]", 
	     	 "[6, 2, 3, 1, 1, 1, 2, 1, 1]", "[33.33, 11.11, 16.67, 5.56, 5.56, 5.56, 11.11, 5.56, 5.56]", 
	     	 18, "100.00"},
			
			//Test case #45
			{1,"", "This application uses parts of speech analysis to gain a deeper understanding of the language used in the given text.", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 2, 
			 "[Noun, singular or mass, Determiner, Preposition or subordinating conjunction, Verb, past participle, Noun, plural, Verb, base form, to, Adjective, comparative, Verb, 3rd person singular present]",
			 "[a, given, in, language, used, analysis, gain, the, application, speech, of, parts, This, understanding, uses, deeper, to, text]",
			 "[Determiner, Verb, past participle, Preposition or subordinating conjunction, Noun, singular or mass, Verb, past participle, Noun, singular or mass, Verb, base form, Determiner, Noun, singular or mass, Noun, singular or mass, Preposition or subordinating conjunction, Noun, plural, Determiner, Noun, singular or mass, Verb, 3rd person singular present, Adjective, comparative, to, Noun, singular or mass]", 
			 "[6, 3, 2, 2, 1, 1, 1, 1, 1]", "[33.33, 16.67, 11.11, 11.11, 5.56, 5.56, 5.56, 5.56, 5.56]",
			 18, "100.00"},
			
			//Test case #46
			{1,"", "This application uses parts of speech analysis to gain a deeper understanding of the language used in the given text.", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 3, 
			 "[Noun, plural, Verb, base form, to, Adjective, comparative, Verb, 3rd person singular present, Preposition or subordinating conjunction, Verb, past participle, Determiner, Noun, singular or mass]",
			 "[a, given, in, language, used, analysis, gain, the, application, speech, of, parts, This, understanding, uses, deeper, to, text]",
			 "[Determiner, Verb, past participle, Preposition or subordinating conjunction, Noun, singular or mass, Verb, past participle, Noun, singular or mass, Verb, base form, Determiner, Noun, singular or mass, Noun, singular or mass, Preposition or subordinating conjunction, Noun, plural, Determiner, Noun, singular or mass, Verb, 3rd person singular present, Adjective, comparative, to, Noun, singular or mass]", 
			 "[1, 1, 1, 1, 1, 2, 2, 3, 6]", "[5.56, 5.56, 5.56, 5.56, 5.56, 11.11, 11.11, 16.67, 33.33]",
			 18, "100.00"},
			
			//Test case #47
			{1,"", "Natural language processing is a subfield of linguistics, computer science, information engineering, and artificial intelligence concerned with the interactions between computers and human languages.", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 1, 
			 "[Noun, singular or mass, Preposition or subordinating conjunction, Adjective, Coordinating conjunction, Determiner, Noun, plural, Verb, past participle, Verb, 3rd person singular present]",
			 "[a, linguistics, concerned, languages, language, is, engineering, Natural, intelligence, interactions, the, with, computer, artificial, subfield, and, of, science, processing, information, human, between, computers]",
			 "[Determiner, Noun, plural, Verb, past participle, Noun, plural, Noun, singular or mass, Verb, 3rd person singular present, Noun, singular or mass, Adjective, Noun, singular or mass, Noun, plural, Determiner, Preposition or subordinating conjunction, Noun, singular or mass, Adjective, Noun, singular or mass, Coordinating conjunction, Preposition or subordinating conjunction, Noun, singular or mass, Noun, singular or mass, Noun, singular or mass, Adjective, Preposition or subordinating conjunction, Noun, plural]", 
			 "[8, 3, 3, 1, 2, 4, 1, 1]", "[34.78, 13.04, 13.04, 4.35, 8.70, 17.39, 4.35, 4.35]",
			 23, "100.00"},
			
			//Test case #48
			{1,"", "Natural language processing is a subfield of linguistics, computer science, information engineering, and artificial intelligence concerned with the interactions between computers and human languages.", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 2, 
			 "[Noun, singular or mass, Noun, plural, Preposition or subordinating conjunction, Adjective, Determiner, Coordinating conjunction, Verb, past participle, Verb, 3rd person singular present]",
			 "[a, linguistics, concerned, languages, language, is, engineering, Natural, intelligence, interactions, the, with, computer, artificial, subfield, and, of, science, processing, information, human, between, computers]",
			 "[Determiner, Noun, plural, Verb, past participle, Noun, plural, Noun, singular or mass, Verb, 3rd person singular present, Noun, singular or mass, Adjective, Noun, singular or mass, Noun, plural, Determiner, Preposition or subordinating conjunction, Noun, singular or mass, Adjective, Noun, singular or mass, Coordinating conjunction, Preposition or subordinating conjunction, Noun, singular or mass, Noun, singular or mass, Noun, singular or mass, Adjective, Preposition or subordinating conjunction, Noun, plural]", 
			 "[8, 4, 3, 3, 2, 1, 1, 1]", "[34.78, 17.39, 13.04, 13.04, 8.70, 4.35, 4.35, 4.35]",
			 23, "100.00"},
			
			//Test case #49
			{1,"", "Natural language processing is a subfield of linguistics, computer science, information engineering, and artificial intelligence concerned with the interactions between computers and human languages.", 0, "tokenize, ssplit, pos, parse, sentiment", 2, 3, 
			"[Coordinating conjunction, Verb, past participle, Verb, 3rd person singular present, Determiner, Preposition or subordinating conjunction, Adjective, Noun, plural, Noun, singular or mass]",
			"[a, linguistics, concerned, languages, language, is, engineering, Natural, intelligence, interactions, the, with, computer, artificial, subfield, and, of, science, processing, information, human, between, computers]",
			"[Determiner, Noun, plural, Verb, past participle, Noun, plural, Noun, singular or mass, Verb, 3rd person singular present, Noun, singular or mass, Adjective, Noun, singular or mass, Noun, plural, Determiner, Preposition or subordinating conjunction, Noun, singular or mass, Adjective, Noun, singular or mass, Coordinating conjunction, Preposition or subordinating conjunction, Noun, singular or mass, Noun, singular or mass, Noun, singular or mass, Adjective, Preposition or subordinating conjunction, Noun, plural]", 
			"[1, 1, 1, 2, 3, 3, 4, 8]", "[4.35, 4.35, 4.35, 8.70, 13.04, 13.04, 17.39, 34.78]",
			23, "100.00"},
		
		});
	}
	
	//SentimentAnalysis
	
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
		if(analysisOption == 1)
		{
			if(testSelector == 2)
			{
				HandleTextFiles text1 = new HandleTextFiles(fileLocation);
				userInput = text1.fileToString();
			}
			LanguageAnalysis test1 = new LanguageAnalysis(userInput, propertiesForNLP, analysisOption, sortingOptionType);
			DecimalFormat round = new DecimalFormat("0.00");
			test1.sentimentAnalysis();
			String result1 = Arrays.toString(test1.getRowValues());
			String result2 = Arrays.toString(test1.getSentence());
			String result3 = Arrays.toString(test1.getSentiment());
			String result4 = Arrays.toString(test1.getSummaryCount());
			double[] doublePlaceholder1 = test1.getSummaryPercentage();
			ArrayList<String> stringPlaceholder1 = new ArrayList<String>();
			for(int i = 0; i < doublePlaceholder1.length; i++)
			{
				stringPlaceholder1.add(round.format(doublePlaceholder1[i]));
			}
			String result5 = stringPlaceholder1.toString();
			int result6 = test1.getTotalCount();		
			String result7 = round.format(test1.getTotalPercentage());
			assertEquals(expectedResult1, result1);
			assertEquals(expectedResult2, result2);
			assertEquals(expectedResult3, result3);
			assertEquals(expectedResult4, result4);
			assertEquals(expectedResult5, result5);
			assertEquals(expectedResult6, result6);
			assertEquals(expectedResult7, result7);
		}
	}
	

	//POSanalysis
	
	/*
	 * This is the method which actually does the testing with the data that has been inputted above.
	 * This @Test method will be called and ran for each parameter entered above.
	 * It does assertEquals statements to check if the two pieces of data (i.e. the expected result and the 
	 * actual result) match each other. If they do match, the result will be a pass, if they do not match then the 
	 * result will be a failure or error.
	 */
	@Test
	public void testPOS() throws FileNotFoundException
	{
		if(analysisOption == 2)
		{
			if(testSelector == 2)
			{
				HandleTextFiles text2 = new HandleTextFiles(fileLocation);
				userInput = text2.fileToString();
			}
			LanguageAnalysis test2 = new LanguageAnalysis(userInput, propertiesForNLP, analysisOption, sortingOptionType);
			DecimalFormat round = new DecimalFormat("0.00");
			test2.partsOfSpeechAnalsis();
			String result1 = Arrays.toString(test2.getArrayOfUniquePOStags());
			String result2 = Arrays.toString(test2.getArrayOfWords());
			String result3 = Arrays.toString(test2.getArrayOfPOS());
			String result4 = Arrays.toString(test2.getSummaryCount());
			double[] doublePlaceholder2 = test2.getSummaryPercentage();
			ArrayList<String> stringPlaceholder2 = new ArrayList<String>();
			for(int i = 0; i < doublePlaceholder2.length; i++)
			{
				stringPlaceholder2.add(round.format(doublePlaceholder2[i]));
			}
			String result5 = stringPlaceholder2.toString();
			int result6 = test2.getTotalCount();
			String result7 = round.format(test2.getTotalPercentage());
			assertEquals(expectedResult1, result1);
			assertEquals(expectedResult2, result2);
			assertEquals(expectedResult3, result3);
			assertEquals(expectedResult4, result4);
			assertEquals(expectedResult5, result5);
			assertEquals(expectedResult6, result6);
			assertEquals(expectedResult7, result7);
		}
	}
}
