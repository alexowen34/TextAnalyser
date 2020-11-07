package com.myfirstapplication.analysis;

import com.myfirstapplication.GUI;
import com.myfirstapplication.support.CollectionToArray;
import com.myfirstapplication.support.NLPpipeline;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * This class does the word analysis. The class extends the abstract class of TextToBeAnalysed which contains the text the user has inputted
 * to be analysed from the GUI class.
 */

public class WordAnalysis extends TextToBeAnalysed
{
	//Below is a range of global private variables that can be accessed across all methods across this class.

	//Below variables are populated via the constructor method.
	private String propertiesForNLP;
	private int paragraphCount;

	//Below variables are populated via methods in this class.
	private int wordCount;
	private int sentenceCount;
	private double averageWordLength;
	private String mostFrequentWord;
	private ArrayList<String> wordArray = new ArrayList<String>();
	private SimpleStringProperty metric;
	private SimpleStringProperty value;
	
	//Contructor method that is used by the GUI to pass in requried data to this object.
	public WordAnalysis(String propertiesForNLP, int paragraphCount)
	{
		this.propertiesForNLP = propertiesForNLP;
		this.paragraphCount = paragraphCount;
	}
	
	//Contructor method that is used by the WordAnalysisTest class in order to pass the users input to the abstract 'TextToBeAnalysed' class.
	public WordAnalysis(String userInput, String propertiesForNLP, int paragraphCount)
	{
		textToBeAnalysed = userInput;
		this.propertiesForNLP = propertiesForNLP;
		this.paragraphCount = paragraphCount;
	}
	
	/*
	 * Constructor method that is used by sentimentAnalysis method in LanguageAnalysis object to pass in required data to this object.
	 * It passes the usersInput to the textToBeAnalysed variable in the abstract class as this needs to happen for the test to run successfully
	 * otherwise it woulden't be stored in memory.
	 */
	public WordAnalysis(String userInput, String propertiesForNLP, String NA)
	{
		textToBeAnalysed = userInput;
		this.propertiesForNLP = propertiesForNLP;
	}
	
	//Contructor method that is used soley for GUI table creation.
	public WordAnalysis(String metric, String value)
	{
		this.metric = new SimpleStringProperty(metric);
		this.value = new SimpleStringProperty(value);		
	}
	
	public String getMetric()
	{
		return metric.get();
	}
	
	public String getValue()
	{
		return value.get();
	}
	
	/* 
	 * Turns users input into a String array removing any spaces in the String. Then removes any special characters or numbers from String array - this
	 * is important for when averageWordLength is calculated in a later method. This method then counts the number of indexes in the String 
	 * therefore returning the number of words.
	*/
	public int wordCount()
	{
		String[] arrayForValidWords = textToBeAnalysed.trim().split("\\s+");
		String charactersToRemove = GUI.specialCharacters += GUI.numbers;
		char[] charsToRemove = charactersToRemove.toCharArray();
		String[] charsToRemoveString = new String[charsToRemove.length];
		for(int i = 0; i < charsToRemoveString.length; i++)
		{
			charsToRemoveString[i] = String.valueOf(charsToRemove[i]);
		}
		for(int i = 0; i < arrayForValidWords.length; i++)
		{
			for(int j = 0; j < charsToRemoveString.length; j++)
			{
				arrayForValidWords[i] = arrayForValidWords[i].replace(charsToRemoveString[j], "");
			}
		}
		for(int i = 0; i < arrayForValidWords.length; i++)
		{
			if(!arrayForValidWords[i].isEmpty())
			{
				wordArray.add(arrayForValidWords[i].toLowerCase());
			}
		}
		return wordCount = wordArray.size();
	}
	
	/*
	 * This method calculates the average number of letters per word by calculating the sum of letters in 'wordArray' and dividing it by the 
	 * total number of words. Special characters and numbers are removed from any words in the 'wordCount' method as these will impact the accurary
	 * of the average word length i.e. if there's a full stop at the end of a sentence this would have counted towards the length of a word.
	 */
	public double averageWordLength()
	{
		double averageWordLength;
		int[] numberOfCharsInWord = new int [wordArray.size()];
		if(wordCount <= 0)
		{
			averageWordLength = 0.00;
		}
		else {
			for(int i = 0; i < numberOfCharsInWord.length; i++)
			{
				numberOfCharsInWord[i] = wordArray.get(i).length();
			}
			averageWordLength = Arrays.stream(numberOfCharsInWord).average().getAsDouble();
		}
		return this.averageWordLength = averageWordLength;
	}
	
	/*
	 * This method calculates the most frequency word. It returns the most frequent word if it is 'greater than' the count of others words rather than 
	 * 'equal to and greater than'. If there are two or more different words with the same highest frequency then an information message will be 
	 * printed out as the result. An information message will also be printed out if there are no valid words.
	 */
	public String mostFrequentWord()
	{
		HashMap<String, String> uniqueWordsHashMap = new HashMap<String, String>();
		for(int i = 0; i < wordArray.size(); i++)
		{
			uniqueWordsHashMap.put(wordArray.get(i), "");
		}
		String[] uniqueWords = new String[uniqueWordsHashMap.size()];
	    CollectionToArray uniqueWordsToArray = new CollectionToArray(uniqueWordsHashMap);
	    uniqueWords = uniqueWordsToArray.hashmapKeysAsStringArray();
		int[] wordCount = new int[uniqueWords.length];
		for(int i = 0; i < uniqueWords.length; i++)
		{
			for(int j = 0; j < wordArray.size(); j++)
			{
				if(uniqueWords[i].equals(wordArray.get(j)))
				{
					wordCount[i]++;
				}
			}
		}
		String mostFrequentWord = null;
		if(this.wordCount <= 0)
		{
			mostFrequentWord = "Text does not include any valid words";
		}
		else
		{
			int largestCount = wordCount[0];
			for(int i = 0; i < wordCount.length; i++)
			{
				if(wordCount[i] > largestCount)
				{
					largestCount = wordCount[i];
				}
			}
			int counter = 0;
			int indexNumber = 0;
			for(int i = 0; i < wordCount.length; i++)
			{
				if(largestCount == wordCount[i])
				{
					indexNumber = i;
					counter++;
				}
			}
			if(counter > 1)
			{
				mostFrequentWord = "Text does not contain a distinct most frequent word";
			}
			else {
				mostFrequentWord = uniqueWords[indexNumber];
			}
		}
		return this.mostFrequentWord = mostFrequentWord;
	}
	
	/* 
	 * This method finds the number of sentences using the StanfordCoreNLP library. This library is brought in to my Maven project through 
	 * the POM.xml file where it is listed as a dependency.
	*/
	public List<CoreSentence> sentencesListAndCount()
	{
		NLPpipeline pipeline = new NLPpipeline(propertiesForNLP);
		StanfordCoreNLP sentenceAnalysis = pipeline.getPipeline();
		CoreDocument coreDocument = new CoreDocument(textToBeAnalysed);
		sentenceAnalysis.annotate(coreDocument);
		List<CoreSentence> sentenceList = coreDocument.sentences();
		if(wordCount <= 0)
		{
			sentenceCount = 0;
		}
		else {
			sentenceCount = sentenceList.size();
		}
		return sentenceList;
	}
	
	
	//This method returns the sentence count. Method is used in this project only for testing purposes.
	public int getSentenceCount()
	{
		return sentenceCount;
	}
	
	//This method returns the sentence count. Method is used in this project only for testing purposes.
	public int getParagraphCount()
	{
		return paragraphCount;
	}
	
	//Used to create the ObservableList that stores the word analysis results for the table.
	public ObservableList<WordAnalysis> wordAnalysisTableData()
	{
		String[] arrayOfMetrics = new String[5];
		arrayOfMetrics[0] = "Number of words";
		arrayOfMetrics[1] = "Average word length";
		arrayOfMetrics[2] = "Most frequent word";
		arrayOfMetrics[3] = "Number of sentences";
		arrayOfMetrics[4] = "Number of paragraphs";
		String[] arrayOfValues = new String[5];
		arrayOfValues[0] = String.valueOf(wordCount);
		DecimalFormat format = new DecimalFormat("0.00");
		arrayOfValues[1] = String.valueOf(format.format(averageWordLength));
		arrayOfValues[2] = mostFrequentWord;
		arrayOfValues[3] = String.valueOf(sentenceCount);
		arrayOfValues[4] = String.valueOf(paragraphCount);
		ArrayList<WordAnalysis> data = new ArrayList<WordAnalysis>();
		for(int i = 0; i < arrayOfMetrics.length; i++)
		{
			data.add(new WordAnalysis(arrayOfMetrics[i], arrayOfValues[i]));
		}
		ObservableList<WordAnalysis> list = FXCollections.observableArrayList();
		list.addAll(data);
		return list;
	}
	
}

