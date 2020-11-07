package com.myfirstapplication.analysis;

import com.myfirstapplication.GUI;
import com.myfirstapplication.support.CollectionToArray;
import com.myfirstapplication.support.CreatePieChartData;
import com.myfirstapplication.support.DecimalPlaces;
import com.myfirstapplication.support.SortValues;
import javafx.collections.ObservableList;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/*
 * This class does the character analysis. The class extends the abstract class of StringToAnalyse which contains the text the user has inputted
 * to be analysed from the GUI class.
 */
public class CharacterAnalysis extends TextToBeAnalysed
{
	//Below is a range of global private variables that can be accessed across all methods in this class.

	//Below variables are populated via the constructor method.
	private int percentageCalcType;
	private int sortingOptionType;
	private String referenceOption;
	private char[] referenceCharactersForTable;

	//Below variables are populated via methods in this class.
	@SuppressWarnings("unused")
	private int countSum;
	@SuppressWarnings("unused")
	private double percentageSum;
	private int[] countFrequencyOutput;
	private int [] summaryCount;
	private double[] percentageFrequencyOutput;
	private double [] summaryPercentage;
	private String [] rowDescription = {"Letters", "Numbers", "Special Characters"};

	
	//Constructor method that is used by GUI class to pass in required data to this object.
	public CharacterAnalysis (String referenceOption, int percentageCalcType, int sortingOptionType)
	{
		this.referenceOption = referenceOption;
		char [] character = referenceOption.toCharArray();
		referenceCharactersForTable = character;
		this.percentageCalcType = percentageCalcType;
		this.sortingOptionType = sortingOptionType;
	}
	
	//Constructor method that is used to pass in the relevant data for testing (CharacterAnalysisTest.java).
	public CharacterAnalysis (String inputString, String referenceOption, int percentageCalcType, int sortingOptionType, int decimalPlaces)
	{
		textToBeAnalysed = inputString;
		this.referenceOption = referenceOption;
		char [] character = referenceOption.toCharArray();
		referenceCharactersForTable = character;
		this.percentageCalcType = percentageCalcType;
		this.sortingOptionType = sortingOptionType;
		GUI.numberOfDecimalPlaces = decimalPlaces;
	}
	
	//Constructor method that is shortened for 'relativeCharFrequencies' and 'summary' method that is created and called from within this class.
	public CharacterAnalysis (String inputString, String referenceOption)
	{
		char [] character = referenceOption.toCharArray();
		referenceCharactersForTable = character;
		textToBeAnalysed = inputString;
	}
	
	//This method is called from 'countCharFrequencies' method - see comments in this method for more detail.
	private int timesCharOccurs(String str, char character) 
	{
		str = str.toUpperCase();
		char[] stringAsCharArray = str.toCharArray();
		int countOfChar = 0;
		for(int i = 0; i < stringAsCharArray.length; i++)
		{
			if (stringAsCharArray[i] == character)
			{
				countOfChar++;
			}
		}
		return countOfChar;
	}
	
	/* 
	 * This method counts the number of filtered characters. It makes multiple calls to timesCharOccurs method, via a loop, to see if individual
	 * filtered characters match the individual characters in users text input and if so, how many times they occur. If user has selected to
	 * sort the results, then this method will create a new instance of 'SortValues' class to fulfill the sorting requirement. It's worth noting
	 * that it is passing in the reference characters to the SortValues object as a string array rather than char array to save duplicate code 
	 * in the SortValues object as the LanguageAnalysis class needs to pass in reference values as string array. These reference characters are
	 * later transformed back into a char array to it's global variable.
	 */
	public int[] countCharFrequencies()
	{
		int [] output = new int[referenceCharactersForTable.length];
		for(int i = 0; i < output.length; i++)
		{
			output[i]=timesCharOccurs(textToBeAnalysed,referenceCharactersForTable[i]);
		}
		if(sortingOptionType == 2 || sortingOptionType == 3)
		{
			String[] referenceCharactersForSorting = new String[referenceCharactersForTable.length];
			referenceCharactersForSorting = referenceOption.split("(?!^)");
			SortValues sortRefCharsAndCount = new SortValues(referenceCharactersForSorting, output, sortingOptionType);
			HashMap<String, Integer> sortedValues = new HashMap<String, Integer>();
			sortedValues = sortRefCharsAndCount.StringIntArrayToSortedHashMap();
	        CollectionToArray sortedValuesToArray = new CollectionToArray(sortedValues);
	        referenceCharactersForSorting = sortedValuesToArray.mapKeysAsStringArray();
	        output = sortedValuesToArray.mapValuesAsIntArray();
	        String stringArrayToString = referenceCharactersForSorting[0];
	        for(int j = 1; j < referenceCharactersForSorting.length; j++)
	        {
	        	stringArrayToString += referenceCharactersForSorting[j];
	        }
	        referenceCharactersForTable = stringArrayToString.toCharArray();	
		}
		return countFrequencyOutput = output;
	}
	
	//This method returns the reference characters for the table. It is used in this project only for testing purposes.
	public char[] getReferenceCharacters()
	{
		return referenceCharactersForTable;
	}
		
	/*
	 * Method returns either the percentage of how often the characters occur in just the filtered string (i.e. if user has selected to filter by
	 * only letters, numbers or specical characters) or the full string entered. User has the option to select between these two calculations in GUI.
	 * This method is dependant on countCharFrequencies method being ran first so that the relevent variables are populated.
	 */
	public double[] relativeCharFrequencies()
	{	
		CharacterAnalysis percentage = new CharacterAnalysis(textToBeAnalysed, GUI.fullListOfReferenceCharacters);
		int sumOfFullString = Arrays.stream(percentage.countCharFrequencies()).sum();
		int arraySum = 0;
		switch(percentageCalcType)
		{
			case 1:
				arraySum = Arrays.stream(countFrequencyOutput).sum();
				percentageCalcType = arraySum;
			break;
			case 2:
				percentageCalcType = sumOfFullString;
			break;
			default:
				percentageCalcType = sumOfFullString;
		}
		double [] outputPercentage = new double[countFrequencyOutput.length];
		for(int i = 0; i < outputPercentage.length; i++)
		{
			outputPercentage[i] = countFrequencyOutput[i];
			outputPercentage[i] = (outputPercentage[i] / percentageCalcType) * 100;
		}
		return percentageFrequencyOutput = outputPercentage;
	}
	
	/*
	 * This method creates a summary by totaling the number of letters, numbers and special characters and returns a total count and percentage.
	 * Results are only allowed to be displayed in GUI when 'Include all characters' is selected as this is the only filter option where the 
	 * summary would be useful.
	 */
	public void summary()
	{
		int[] countSummaryOutput = new int[3];
		double[] percentageSummaryOutput = new double[3];
		String[] filterCharacters = new String[3];
		filterCharacters[0] = GUI.letters;
		filterCharacters[1] = GUI.numbers;
		filterCharacters[2] = GUI.specialCharacters;
		CharacterAnalysis[] summary = new CharacterAnalysis[3];
		for(int i = 0; i < summary.length; i++)
		{
			summary[i] = new CharacterAnalysis(textToBeAnalysed, filterCharacters[i]);
			for(int j = 0; j < summary[i].countCharFrequencies().length; j++)
			{
				countSummaryOutput[i] += summary[i].countCharFrequencies()[j];
				percentageSummaryOutput[i] += summary[i].relativeCharFrequencies()[j];
			}
		}
		this.summaryCount = countSummaryOutput;
		this.summaryPercentage = percentageSummaryOutput;
	}
	
	//This method returns the row values for the summary chart. Method is used in this project only for testing purposes.
	public String[] getRowValues()
	{
		return rowDescription;
	}
	
	//This method returns the count for the summary chart. Method is used in this project only for testing purposes.
	public int[] getCountSummaryOutput() 
	{
		return summaryCount;
	}
	
	//This method returns the percentage for the summary chart. Method is used in this project only for testing purposes.
	public double[] getPercentageSummaryOutput()
	{
		return summaryPercentage;
	}
	
	//This method returns the total count frequency. It is used in this project only for testing purposes.
	public int getTotalCount()
	{
		return countSum = Arrays.stream(countFrequencyOutput).sum();
	}
	
	//This method returns the total percentage/relative frequency. It is used in this project only for testing purposes.
	public double getTotalPercentage()
	{
		return percentageSum = Arrays.stream(percentageFrequencyOutput).sum();
	}
	
	//This method returns the bar chart data using the variables populated in this class.
	public XYChart.Series<String, Number> barChartData()
	{
		String[] referenceCharactersForBarChart = new String[referenceCharactersForTable.length];
		for(int i = 0; i < referenceCharactersForBarChart.length; i++)
		{
			referenceCharactersForBarChart[i] = String.valueOf(referenceCharactersForTable[i]);
		}
		XYChart.Series<String, Number> barChart = new XYChart.Series<>();	
		for(int i = 0; i < countFrequencyOutput.length; i++)
		{
			if(countFrequencyOutput[i] != 0)
			{
				barChart.getData().add(createData(referenceCharactersForBarChart[i], countFrequencyOutput[i], i));
			}
		}
		return barChart;
	}
	
	//This method gets called in the for loop in the barChartData method. This method returns the data with data labels for each bar.
    private XYChart.Data<String, Number> createData(String text, int value, int counter) 
    {
        XYChart.Data<String, Number> data =  new XYChart.Data<String, Number>(text, value);
		DecimalPlaces stringRep = new DecimalPlaces();
		DecimalFormat round = new DecimalFormat(stringRep.stringRepresentationOfDecimals());
        String infomation = String.valueOf(round.format(percentageFrequencyOutput[counter])+"%");
        StackPane node = new StackPane();
        Label label = new Label(infomation);
        label.setStyle("-fx-font-weight: bold;");
        Group group = new Group(label);
        StackPane.setAlignment(group, Pos.CENTER);
        StackPane.setMargin(group, new Insets(0, 0, 5, 0));
        node.getChildren().add(group);
        data.setNode(node);
        return data;
    }
    
    //Returns the data for the summary pie chart.
	public ObservableList<Data> pieChartData()
	{
		CreatePieChartData populatePieChartData = new CreatePieChartData(rowDescription, summaryPercentage, summaryCount);
		return populatePieChartData.pieChartData();
	}
}



	

