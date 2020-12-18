package com.myfirstapplication.analysistests;

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

import com.myfirstapplication.analysis.LanguageAnalysis;
import com.myfirstapplication.support.HandleTextFiles;

@RunWith(value = Parameterized.class)
public class PartsOfSpeechAnalysisTest {
	
	/*
	 * Stores the required data, for the @Test method, that is populated by the
	 * constructor method.
	 */
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

	/* 
	 * Constructor method that takes the required data and passes it to the global
	 * variables.
	 */ 
	public PartsOfSpeechAnalysisTest(int testSelector, String fileLocation, String userInput, int decimalPlaces,
			String propertiesForNLP, int sortingOptionType, String expectedResult1, String expectedResult2,
			String expectedResult3, String expectedResult4, String expectedResult5, int expectedResult6,
			String expectedResult7) {
		
		/*
		 * As the LanguageAnalysis class is instantiated in @Test methods, that class
		 * references the GUI class which uses JavaFX so therefore the JavaFX enviroment
		 * needs to be initialized.
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
	 * This method stores the data parameters for each test case. Each element in
	 * the list is passed to the constructor method. The data parameters consist of
	 * both inputs to be passed into the relevant object and the expected results to
	 * be returned.
	 */
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {

				// POSanalysis

				// Test case #0
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase15.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Wh-pronoun]", "[what, whom, who]",
						"[Wh-pronoun, Wh-pronoun, Wh-pronoun]", "[3]", "[100.00]", 3, "100.00" },

				// Test case #1
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase16.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Wh-determiner]", "[which, that, whatever]",
						"[Wh-determiner, Wh-determiner, Wh-determiner]", "[3]", "[100.00]", 3, "100.00" },

				// Test case #2
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase17.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Wh-adverb]", "[how, why, where, when]",
						"[Wh-adverb, Wh-adverb, Wh-adverb, Wh-adverb]", "[4]", "[100.00]", 4, "100.00" },

				// Test case #3
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase18.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Verb, past tense]", "[were, came, was, said, did]",
						"[Verb, past tense, Verb, past tense, Verb, past tense, Verb, past tense, Verb, past tense]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #4
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase19.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Verb, past participle]",
						"[been, created, driven, done, stated]",
						"[Verb, past participle, Verb, past participle, Verb, past participle, Verb, past participle, Verb, past participle]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #5
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase20.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Verb, non-3rd person singular present]",
						"[provide, remain, want, propose, seem]",
						"[Verb, non-3rd person singular present, Verb, non-3rd person singular present, Verb, non-3rd person singular present, Verb, non-3rd person singular present, Verb, non-3rd person singular present]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #6
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase21.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Verb, gerund or present participle]",
						"[retiring, killing, replacing, being, saying]",
						"[Verb, gerund or present participle, Verb, gerund or present participle, Verb, gerund or present participle, Verb, gerund or present participle, Verb, gerund or present participle]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #7
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase22.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Verb, base form]", "[add, bode, die, be, prove]",
						"[Verb, base form, Verb, base form, Verb, base form, Verb, base form, Verb, base form]", "[5]",
						"[100.00]", 5, "100.00" },

				// Test case #8
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase23.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Verb, 3rd person singular present]",
						"[comes, is, has, seems, proves]",
						"[Verb, 3rd person singular present, Verb, 3rd person singular present, Verb, 3rd person singular present, Verb, 3rd person singular present, Verb, 3rd person singular present]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #9
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase24.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[to]", "[na, to, ta]", "[to, to, to]", "[3]", "[100.00]", 3,
						"100.00" },

				// Test case #10
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase25.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Symbol]", "[|, \\, ¬, ~, _]",
						"[Symbol, Symbol, Symbol, Symbol, Symbol]", "[5]", "[100.00]", 5, "100.00" },

				// Test case #11
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase26.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Proper noun, singular]",
						"[saturday, london, july, wednesday, washington]",
						"[Proper noun, singular, Proper noun, singular, Proper noun, singular, Proper noun, singular, Proper noun, singular]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #12
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase27.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Noun, plural]",
						"[appeals, israelians, forces, muslims, motorsports]",
						"[Noun, plural, Noun, plural, Noun, plural, Noun, plural, Noun, plural]", "[5]", "[100.00]", 5,
						"100.00" },

				// Test case #13
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase28.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Preposition or subordinating conjunction]",
						"[though, whether, among, than, between]",
						"[Preposition or subordinating conjunction, Preposition or subordinating conjunction, Preposition or subordinating conjunction, Preposition or subordinating conjunction, Preposition or subordinating conjunction]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #14
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase29.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Personal pronoun]", "[itself, them, he, him, they]",
						"[Personal pronoun, Personal pronoun, Personal pronoun, Personal pronoun, Personal pronoun]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #15
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase30.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Possessive pronoun]", "[his, her, their, its, your]",
						"[Possessive pronoun, Possessive pronoun, Possessive pronoun, Possessive pronoun, Possessive pronoun]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #16
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase31.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Noun, singular or mass]",
						"[punchline, week, idea, article, story]",
						"[Noun, singular or mass, Noun, singular or mass, Noun, singular or mass, Noun, singular or mass, Noun, singular or mass]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #17
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase32.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Modal]", "[can, would, will, could, should]",
						"[Modal, Modal, Modal, Modal, Modal]", "[5]", "[100.00]", 5, "100.00" },

				// Test case #18
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase33.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Interjection]", "[hi, uh, eh, yes, hello]",
						"[Interjection, Interjection, Interjection, Interjection, Interjection]", "[5]", "[100.00]", 5,
						"100.00" },

				// Test case #19
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase34.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Foreign word]", "[c'est, etc, déjà, guerre, comme]",
						"[Foreign word, Foreign word, Foreign word, Foreign word, Foreign word]", "[5]", "[100.00]", 5,
						"100.00" },

				// Test case #20
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase35.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Determiner]", "[some, another, this, an, any]",
						"[Determiner, Determiner, Determiner, Determiner, Determiner]", "[5]", "[100.00]", 5,
						"100.00" },

				// Test case #21
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase36.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Coordinating conjunction]", "[nor, but, or, and, neither]",
						"[Coordinating conjunction, Coordinating conjunction, Coordinating conjunction, Coordinating conjunction, Coordinating conjunction]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #22
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase37.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Cardinal number]", "[15, 4, 26/07/1998, sixteen, two]",
						"[Cardinal number, Cardinal number, Cardinal number, Cardinal number, Cardinal number]", "[5]",
						"[100.00]", 5, "100.00" },

				// Test case #23
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase38.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Adverb]", "[very, closely, currently, temporarily, soon]",
						"[Adverb, Adverb, Adverb, Adverb, Adverb]", "[5]", "[100.00]", 5, "100.00" },

				// Test case #24
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase39.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Adjective, superlative]",
						"[hottest, fewest, slightest, nearest, wildest]",
						"[Adjective, superlative, Adjective, superlative, Adjective, superlative, Adjective, superlative, Adjective, superlative]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #25
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase40.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Adjective, comparative]",
						"[larger, fatter, cleaner, lower, sadder]",
						"[Adjective, comparative, Adjective, comparative, Adjective, comparative, Adjective, comparative, Adjective, comparative]",
						"[5]", "[100.00]", 5, "100.00" },

				// Test case #26
				{ 2, "src/test/resources/TestData&Documentation/LanguageAnalysisTestFiles/LanguageAnalysis-TestCase41.txt",
						"", 0, "tokenize, ssplit, pos", 1, "[Adjective]",
						"[eastern, nervous, succesful, spiritual, associate]",
						"[Adjective, Adjective, Adjective, Adjective, Adjective]", "[5]", "[100.00]", 5, "100.00" },

				// Test case #27
				{ 1, "", "Hello World, my name is Alex Owen.", 0, "tokenize, ssplit, pos", 1,
						"[Interjection, Proper noun, singular, Noun, singular or mass, Possessive pronoun, Verb, 3rd person singular present]",
						"[Alex, Hello, Owen, name, is, World, my]",
						"[Proper noun, singular, Interjection, Proper noun, singular, Noun, singular or mass, Verb, 3rd person singular present, Proper noun, singular, Possessive pronoun]",
						"[1, 3, 1, 1, 1]", "[14.29, 42.86, 14.29, 14.29, 14.29]", 7, "100.00" },

				// Test case #28
				{ 1, "", "Alex Alex Owen Alex Owen Owen Alex", 0, "tokenize, ssplit, pos", 1, "[Proper noun, singular]",
						"[Alex, Owen]", "[Proper noun, singular, Proper noun, singular]", "[2]", "[100.00]", 2,
						"100.00" },

				// Test case #29
				{ 1, "", "This application uses parts of speech analysis to gain a deeper understanding of the language used in the given text.",
						0, "tokenize, ssplit, pos", 1,
						"[Noun, singular or mass, Preposition or subordinating conjunction, Determiner, Noun, plural, Verb, base form, to, Verb, past participle, Adjective, comparative, Verb, 3rd person singular present]",
						"[a, given, in, language, used, analysis, gain, the, application, speech, of, parts, This, understanding, uses, deeper, to, text]",
						"[Determiner, Verb, past participle, Preposition or subordinating conjunction, Noun, singular or mass, Verb, past participle, Noun, singular or mass, Verb, base form, Determiner, Noun, singular or mass, Noun, singular or mass, Preposition or subordinating conjunction, Noun, plural, Determiner, Noun, singular or mass, Verb, 3rd person singular present, Adjective, comparative, to, Noun, singular or mass]",
						"[6, 2, 3, 1, 1, 1, 2, 1, 1]", "[33.33, 11.11, 16.67, 5.56, 5.56, 5.56, 11.11, 5.56, 5.56]", 18,
						"100.00" },

				// Test case #30
				{ 1, "", "This application uses parts of speech analysis to gain a deeper understanding of the language used in the given text.",
						0, "tokenize, ssplit, pos", 2,
						"[Noun, singular or mass, Determiner, Preposition or subordinating conjunction, Verb, past participle, Noun, plural, Verb, base form, to, Adjective, comparative, Verb, 3rd person singular present]",
						"[a, given, in, language, used, analysis, gain, the, application, speech, of, parts, This, understanding, uses, deeper, to, text]",
						"[Determiner, Verb, past participle, Preposition or subordinating conjunction, Noun, singular or mass, Verb, past participle, Noun, singular or mass, Verb, base form, Determiner, Noun, singular or mass, Noun, singular or mass, Preposition or subordinating conjunction, Noun, plural, Determiner, Noun, singular or mass, Verb, 3rd person singular present, Adjective, comparative, to, Noun, singular or mass]",
						"[6, 3, 2, 2, 1, 1, 1, 1, 1]", "[33.33, 16.67, 11.11, 11.11, 5.56, 5.56, 5.56, 5.56, 5.56]", 18,
						"100.00" },

				// Test case #31
				{ 1, "", "This application uses parts of speech analysis to gain a deeper understanding of the language used in the given text.",
						0, "tokenize, ssplit, pos", 3,
						"[Noun, plural, Verb, base form, to, Adjective, comparative, Verb, 3rd person singular present, Preposition or subordinating conjunction, Verb, past participle, Determiner, Noun, singular or mass]",
						"[a, given, in, language, used, analysis, gain, the, application, speech, of, parts, This, understanding, uses, deeper, to, text]",
						"[Determiner, Verb, past participle, Preposition or subordinating conjunction, Noun, singular or mass, Verb, past participle, Noun, singular or mass, Verb, base form, Determiner, Noun, singular or mass, Noun, singular or mass, Preposition or subordinating conjunction, Noun, plural, Determiner, Noun, singular or mass, Verb, 3rd person singular present, Adjective, comparative, to, Noun, singular or mass]",
						"[1, 1, 1, 1, 1, 2, 2, 3, 6]", "[5.56, 5.56, 5.56, 5.56, 5.56, 11.11, 11.11, 16.67, 33.33]", 18,
						"100.00" },

				// Test case #32
				{ 1, "", "Natural language processing is a subfield of linguistics, computer science, information engineering, and artificial intelligence concerned with the interactions between computers and human languages.",
						0, "tokenize, ssplit, pos", 1,
						"[Noun, singular or mass, Preposition or subordinating conjunction, Adjective, Coordinating conjunction, Determiner, Noun, plural, Verb, past participle, Verb, 3rd person singular present]",
						"[a, linguistics, concerned, languages, language, is, engineering, Natural, intelligence, interactions, the, with, computer, artificial, subfield, and, of, science, processing, information, human, between, computers]",
						"[Determiner, Noun, plural, Verb, past participle, Noun, plural, Noun, singular or mass, Verb, 3rd person singular present, Noun, singular or mass, Adjective, Noun, singular or mass, Noun, plural, Determiner, Preposition or subordinating conjunction, Noun, singular or mass, Adjective, Noun, singular or mass, Coordinating conjunction, Preposition or subordinating conjunction, Noun, singular or mass, Noun, singular or mass, Noun, singular or mass, Adjective, Preposition or subordinating conjunction, Noun, plural]",
						"[8, 3, 3, 1, 2, 4, 1, 1]", "[34.78, 13.04, 13.04, 4.35, 8.70, 17.39, 4.35, 4.35]", 23,
						"100.00" },

				// Test case #33
				{ 1, "", "Natural language processing is a subfield of linguistics, computer science, information engineering, and artificial intelligence concerned with the interactions between computers and human languages.",
						0, "tokenize, ssplit, pos", 2,
						"[Noun, singular or mass, Noun, plural, Preposition or subordinating conjunction, Adjective, Determiner, Coordinating conjunction, Verb, past participle, Verb, 3rd person singular present]",
						"[a, linguistics, concerned, languages, language, is, engineering, Natural, intelligence, interactions, the, with, computer, artificial, subfield, and, of, science, processing, information, human, between, computers]",
						"[Determiner, Noun, plural, Verb, past participle, Noun, plural, Noun, singular or mass, Verb, 3rd person singular present, Noun, singular or mass, Adjective, Noun, singular or mass, Noun, plural, Determiner, Preposition or subordinating conjunction, Noun, singular or mass, Adjective, Noun, singular or mass, Coordinating conjunction, Preposition or subordinating conjunction, Noun, singular or mass, Noun, singular or mass, Noun, singular or mass, Adjective, Preposition or subordinating conjunction, Noun, plural]",
						"[8, 4, 3, 3, 2, 1, 1, 1]", "[34.78, 17.39, 13.04, 13.04, 8.70, 4.35, 4.35, 4.35]", 23,
						"100.00" },

				// Test case #34
				{ 1, "", "Natural language processing is a subfield of linguistics, computer science, information engineering, and artificial intelligence concerned with the interactions between computers and human languages.",
						0, "tokenize, ssplit, pos", 3,
						"[Coordinating conjunction, Verb, past participle, Verb, 3rd person singular present, Determiner, Preposition or subordinating conjunction, Adjective, Noun, plural, Noun, singular or mass]",
						"[a, linguistics, concerned, languages, language, is, engineering, Natural, intelligence, interactions, the, with, computer, artificial, subfield, and, of, science, processing, information, human, between, computers]",
						"[Determiner, Noun, plural, Verb, past participle, Noun, plural, Noun, singular or mass, Verb, 3rd person singular present, Noun, singular or mass, Adjective, Noun, singular or mass, Noun, plural, Determiner, Preposition or subordinating conjunction, Noun, singular or mass, Adjective, Noun, singular or mass, Coordinating conjunction, Preposition or subordinating conjunction, Noun, singular or mass, Noun, singular or mass, Noun, singular or mass, Adjective, Preposition or subordinating conjunction, Noun, plural]",
						"[1, 1, 1, 2, 3, 3, 4, 8]", "[4.35, 4.35, 4.35, 8.70, 13.04, 13.04, 17.39, 34.78]", 23,
						"100.00" },

		});
	}

	// POSanalysis

	/*
	 * This is the method which actually does the testing with the data that has
	 * been inputted above. This @Test method will be called and ran for each
	 * parameter entered above. It does assertEquals statements to check if the two
	 * pieces of data (i.e. the expected result and the actual result) match each
	 * other. If they do match, the result will be a pass, if they do not match then
	 * the result will be a failure or error.
	 */
	@Test
	public void testPOS() throws FileNotFoundException {
		if (testSelector == 2) {
			HandleTextFiles text = new HandleTextFiles(fileLocation);
			userInput = text.fileToString();
		}
		LanguageAnalysis test = new LanguageAnalysis(userInput, propertiesForNLP, 2, sortingOptionType);
		DecimalFormat round = new DecimalFormat("0.00");
		test.partsOfSpeechAnalsis();
		String result1 = Arrays.toString(test.getArrayOfUniquePOStags());
		String result2 = Arrays.toString(test.getArrayOfWords());
		String result3 = Arrays.toString(test.getArrayOfPOS());
		String result4 = Arrays.toString(test.getSummaryCount());
		double[] doublePlaceholder = test.getSummaryPercentage();
		ArrayList<String> stringPlaceholder = new ArrayList<String>();
		for (int i = 0; i < doublePlaceholder.length; i++) {
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
