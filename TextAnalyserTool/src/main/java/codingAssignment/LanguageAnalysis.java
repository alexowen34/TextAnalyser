package codingAssignment;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

/*
 * This class does the more advanced language analysis. The class extends the abstract super class of StringToAnalyse which contains the text the 
 * user has inputted to be analysed.
 */
public class LanguageAnalysis extends TextToBeAnalysed
{
	//Below is a range of global private variables that can be accessed across all methods in this class.

	//Below variables are populated via the constructor method.
	private int analysisOption;
	private int sortingOptionType;
	private String propertiesForNLP;

	//Below variables are populated via methods in this class.
	@SuppressWarnings("unused")
	private int countSum;
	@SuppressWarnings("unused")
	private double percentageSum;
	private int [] summaryCount;
	private double [] summaryPercentage;
	private String[] sentencesArray;
	private String[] sentimentsArray;
	private String [] rowDescription = {"Very positive", "Positive", "Neutral", "Negative", "Very negative"};
	private String [] arrayOfWords;
	private String [] arrayOfPOS;
	private String [] arrayOfUniquePOStags;
	private SimpleStringProperty sentences;
	private SimpleStringProperty sentiments;
	private SimpleStringProperty POStag;
	private SimpleStringProperty word;
	
	//Constructor method that is used by TUI to pass in required data to this object.
	public LanguageAnalysis(String propertiesForNLP, int analysisOption, 
							int sortingOptionType) 
	{
		this.propertiesForNLP = propertiesForNLP;
		this.analysisOption = analysisOption;
		this.sortingOptionType = sortingOptionType;
	}
	
	//Contructor method that is used by the LanguageAnalysisTest class in order to pass the users input to the abstract 'TextToBeAnalysed' class.
	public LanguageAnalysis(String userInput, String propertiesForNLP, int analysisOption, 
							int sortingOptionType) 
	{
		textToBeAnalysed = userInput;
		this.propertiesForNLP = propertiesForNLP;
		this.analysisOption = analysisOption;
		this.sortingOptionType = sortingOptionType;
	}
	
	//Contructor method that is used soley for GUI table creation.
	public LanguageAnalysis(String sentences, String sentiments)
	{
		this.sentences = new SimpleStringProperty(sentences);
		this.sentiments = new SimpleStringProperty(sentiments);		
	}
	
	public LanguageAnalysis(String POStag, String word, String NA)
	{
		this.POStag = new SimpleStringProperty(POStag);
		this.word = new SimpleStringProperty(word);
	}
	
	public String getSentences()
	{
		return sentences.get();		
	}
	
	public String getSentiments()
	{
		return sentiments.get();
	}
	
	public String getPOStag()
	{
		return POStag.get();
	}
	
	public String getWord()
	{
		return word.get();
	}
	
	/* 
	 * This method returns the sentiments and the unique sentences they belong to. Sentiment analysis is calculated using StanfordCoreNLP
	 * libary. It puts the sentiments and unique sentences into an array so it can be printed out in the 'toTable' method. Finally, the 
	 * method then passes the required data into the 'summary' method to create a overall summary of which sentiments have been observed.
	 */
	public void sentimentAnalysis()
	{
		WordAnalysis sentences = new WordAnalysis(textToBeAnalysed, propertiesForNLP, "");
		List<CoreSentence> listOfSentences = sentences.sentencesListAndCount();
		HashMap<String, String> sentenceAndSentiment = new HashMap<String,String>();
		for(CoreSentence sentence : listOfSentences)
		{
			sentenceAndSentiment.put(sentence.toString(), sentence.sentiment());
		}
        String[] sentence = new String[sentenceAndSentiment.size()];
        String[] sentiment = new String[sentenceAndSentiment.size()];
        CollectionToArray sentenceAndSentimentToArray = new CollectionToArray(sentenceAndSentiment);
        sentence = sentenceAndSentimentToArray.hashmapKeysAsStringArray();
        sentiment = sentenceAndSentimentToArray.hashmapValuesAsStringArray();
        this.sentencesArray = sentence;
        this.sentimentsArray = sentiment;
		summary(rowDescription, sentiment);
	}
	
	//This method returns the different types of sentiment. It is used in this project only for testing purposes.
	public String[] getRowValues()
	{
		return rowDescription;
	}
	
	//This method returns the distinct sentences the user has inputted. It is used in this project only for testing purposes.
	public String[] getSentence()
	{
		return sentencesArray;
	}
	
	/*
	 * This method returns the sentiment of each distinct sentence the user has inputted. It is used in this project only for 
	 * testing purposes.
	 */
	public String[] getSentiment()
	{
		return sentimentsArray;
	}
	
	/* This method returns the parts of speech (POS) analysis (i.e. nouns, adjectives, adverbs using the StanfordCoreNLP libary) and
	 * each unique word (as HashMap removes duplicate keys/words).POS tags are transformed into descriptions which are contained in the POStags.txt file. 
	 * The method also puts the POS description and unique words into an array so it can be printed out in the 'toTable' method. Finally, the 
	 * method then passes the required data into the 'summary' method to create a overall summary of which POS descriptions have been observed.
	*/
	public void partsOfSpeechAnalsis() throws FileNotFoundException
	{	
		HashMap<String, String> POStagsAndDescriptions = new HashMap<String, String>();
		HandleTextFiles POStagsFromTxtFile = new HandleTextFiles("AppInputData/POStags.txt");
		POStagsAndDescriptions = POStagsFromTxtFile.fileToHashMap();
		NLPpipeline pipeline = new NLPpipeline(propertiesForNLP);
		StanfordCoreNLP grammar = pipeline.getPipeline();
		CoreDocument coreDocument = new CoreDocument(textToBeAnalysed);
		grammar.annotate(coreDocument);
		List<CoreLabel> words = coreDocument.tokens();
		HashMap<String, String> wordsAndPOS = new HashMap<String, String>();
		HashMap<String, String> uniquePOStags = new HashMap<String, String>();
		for(CoreLabel index : words)
		{
			String word = index.originalText();
			String POStag = index.get(CoreAnnotations.PartOfSpeechAnnotation.class);
			wordsAndPOS.put(word, POStagsAndDescriptions.get(POStag));
			uniquePOStags.put(POStagsAndDescriptions.get(POStag), "");
			wordsAndPOS.values().remove(null);
			uniquePOStags.keySet().remove(null);
		}
        String[] arrayOfWords = new String[wordsAndPOS.size()]; 
        String[] arrayOfPOS = new String[wordsAndPOS.size()];
        String[] arrayOfUniquePOStags = new String[uniquePOStags.size()];
        CollectionToArray wordsAndPOStoArray = new CollectionToArray(wordsAndPOS);
        arrayOfWords = wordsAndPOStoArray.hashmapKeysAsStringArray();
        arrayOfPOS = wordsAndPOStoArray.hashmapValuesAsStringArray();
        CollectionToArray uniquePOStagsToArray = new CollectionToArray(uniquePOStags);
        arrayOfUniquePOStags = uniquePOStagsToArray.hashmapKeysAsStringArray();
		this.arrayOfWords = arrayOfWords;
		this.arrayOfPOS = arrayOfPOS;
		this.arrayOfUniquePOStags = arrayOfUniquePOStags;
		summary(arrayOfUniquePOStags, arrayOfPOS);
	}
	
	//This method returns the distinct POS tags. It is used in this project only for testing purposes.	
	public String[] getArrayOfUniquePOStags()
	{
		return arrayOfUniquePOStags;
	}
	
	//This method returns the distinct words the user has inputted. It is used in this project only for testing purposes.
	public String[] getArrayOfWords()
	{
		return arrayOfWords;
	}
	
	/*
	 * This method returns the POS tags related to the distinct words the user has inputted. It is used in this project 
	 * only for testing purposes.	
	 */
	public String[] getArrayOfPOS()
	{
		return arrayOfPOS;
	}
	
	/*
	 * This method returns the required data (count, percentage and chart) to create a summary for both the POS and sentiment analysis.
	 * For POS analysis in particular, it sorts the values in DESC or ASC order depending on what the user selects in TUI menu option.
	 * Sentiment analysis is not sorted as the number of sentiment categories/descriptions is fixed and sequential i.e. Very positive, Positive etc.
	 * so it make sense to keep in hard coded order to make summary easier to read for user.
	 */
	private void summary(String[] descriptionsForSummary, String[] values)
	{
		int[] counter = new int[descriptionsForSummary.length];
		for(int i = 0; i < descriptionsForSummary.length; i++)
		{
			for(int j = 0; j < values.length; j++)
			{
				if(descriptionsForSummary[i].equals(values[j]))
				{
					counter[i]++;
				}
			}
		}
		if(analysisOption == 2)
		{
			if(sortingOptionType == 2 || sortingOptionType == 3)
			{
				SortValues sortRefStringAndCount = new SortValues(descriptionsForSummary, counter, sortingOptionType);
				HashMap<String, Integer> sortedValues = sortRefStringAndCount.StringIntArrayToSortedHashMap();
		        CollectionToArray summarySortedValuesToArray = new CollectionToArray(sortedValues);
		        arrayOfUniquePOStags = summarySortedValuesToArray.mapKeysAsStringArray();
		        counter = summarySortedValuesToArray.mapValuesAsIntArray();
			}
		}
		int sumOfCount = Arrays.stream(counter).sum();
		double[] percentage = new double[descriptionsForSummary.length];
		for(int i = 0; i < percentage.length; i++)
		{
			percentage[i] = counter[i];
			percentage[i] = (percentage[i]/sumOfCount)*100;
		}
		summaryCount = counter;
		summaryPercentage = percentage;
	}
	
	//This method returns the count array for the summary table. It is used in this project only for testing purposes.
	public int[] getSummaryCount()
	{
		return summaryCount;
	}
	
	//This method returns the percentage array for the summary table. It is used in this project only for testing purposes.
	public double[] getSummaryPercentage()
	{
		return summaryPercentage;
	}
	
	//This method returns the total count frequency for the choosen analysis type. It is used in this project only for testing purposes.
	public int getTotalCount()
	{
		return countSum = Arrays.stream(summaryCount).sum();
	}
	
	//This method returns the total percentage/relative frequency for the choosen analysis type. It is used in this project only for testing purposes.
	public double getTotalPercentage()
	{
		return percentageSum = Arrays.stream(summaryPercentage).sum();
	}
	
	public ObservableList<Data> pieChartData()
	{
		DecimalPlaces stringRep = new DecimalPlaces();
		DecimalFormat round = new DecimalFormat(stringRep.stringRepresentationOfDecimals());		
		for(int i = 0; i < rowDescription.length; i++)
		{
			rowDescription[i] += " ("+String.valueOf(round.format(summaryPercentage[i]))+"%)";
		}
		ArrayList<PieChart.Data> data = new ArrayList<PieChart.Data>();
		for(int i = 0; i < summaryCount.length; i++)
		{
			if(summaryCount[i] != 0)
			{
				data.add(new PieChart.Data(rowDescription[i], summaryCount[i]));
			}
		}
		ObservableList<Data> list = FXCollections.observableArrayList();
		list.addAll(data);
		for(int i = 0; i < rowDescription.length; i++)
		{
			String length = "";
			length = String.valueOf((int) summaryPercentage[i]);
			int charsToRemove = GUI.numberOfDecimalPlaces + length.length() + 4;
			StringBuilder removeDecimals = new StringBuilder(rowDescription[i]);
			removeDecimals.reverse();
			removeDecimals.delete(0, charsToRemove);
			removeDecimals.reverse();
			rowDescription[i] = removeDecimals.toString();
			rowDescription[i] = rowDescription[i].trim();
		}
		return list;
	}
	
	public ObservableList<LanguageAnalysis> sentimentTableData()
	{
		ArrayList<LanguageAnalysis> data = new ArrayList<LanguageAnalysis>();
		for(int i = 0; i < sentencesArray.length; i++)
		{
			data.add(new LanguageAnalysis(sentencesArray[i], sentimentsArray[i]));
		}
		ObservableList<LanguageAnalysis> list = FXCollections.observableArrayList();
		list.addAll(data);
		return list;
	}
	
	public ObservableList<LanguageAnalysis> partsOfSpeechTableData()
	{
		ArrayList<LanguageAnalysis> data = new ArrayList<LanguageAnalysis>();
		for(int i = 0; i < arrayOfPOS.length; i++)
		{
			data.add(new LanguageAnalysis(arrayOfPOS[i], arrayOfWords[i], ""));
		}
		ObservableList<LanguageAnalysis> list = FXCollections.observableArrayList();
		list.addAll(data);
		return list;
	}
}
