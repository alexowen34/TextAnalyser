package myFirstApplication;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

/*
 * This class takes the data required for a pie chart and returns it in a ObservableList.
 * It is used by CharacterAnalysis and LanguageAnalysis classes.
 */
public class CreatePieChartData 
{
	//Below variables are populated via the constructor method.
	private String[] rowDescription;
	private double[] summaryPercentage;
	private int[] summaryCount;
	
	//Constructor method that is used by CharacterAnalysis and LanguageAnalysis classes.
	public CreatePieChartData(String[] rowDescription, double[] summaryPercentage, int[] summaryCount)
	{
		this.rowDescription = rowDescription;
		this.summaryPercentage = summaryPercentage;
		this.summaryCount = summaryCount;
	}
	
	//Used to create the ObservableList for either the character analysis or sentiment analysis pie charts.
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

}
