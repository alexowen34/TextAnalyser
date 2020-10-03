package codingAssignment;

import java.text.DecimalFormat;

/*
 * This class creates the Table for the results of an object. It displays percentages (doubles) to the number of decimal places specified 
 * by user in TUI. The table also automatically expands based on the values in the last column of the table.
 */
public class Table
{
	//Below is a range of global private variables that can be accessed across all methods in this class.

	//Below variables are populated via the constructor method.
	private static int columnSize = 25;
	private int decimalPlaces;
	private int countTotal;
	private double percentageTotal;
	private String analysisType;
	private int[] count;
	private double [] percentage;
	private char[] rowValuesChar;
	private String[] lastColumnValues;
	private String[] rowValues;
	private String[] headers;

	//Below variables are populated via methods in this class.
	private String decimalPlacesString;
	
	//Constructor method that collects the data for the CharacterAnalysis and LanguageAnalysis high level table.
	public Table(int decimalPlaces, int[] count, double[] percentage, String[] lastColumnValues, int countTotal, double percentageTotal, String[] rowValues, 
			   	 String[] headers, int columnSize)
	{
		this.decimalPlaces = decimalPlaces;
		this.count = count;
		this.percentage = percentage;
		this.lastColumnValues = lastColumnValues;
		this.countTotal = countTotal;
		this.percentageTotal = percentageTotal;
		this.rowValues = rowValues;
		this.headers = headers;
		Table.columnSize = columnSize;
	}
	
	//Constructor method that collects the data for the low level CharacterAnalysis table.
	public Table(int decimalPlaces, char[] rowValuesChar, int[] count, double[] percentage, String[] lastColumnValues, int countTotal, double percentageTotal,
			 String analysisType, String[] headers)
	{
		this.decimalPlaces = decimalPlaces;
		this.rowValuesChar = rowValuesChar;
		this.count = count;
		this.percentage = percentage;
		this.lastColumnValues = lastColumnValues;
		this.countTotal = countTotal;
		this.percentageTotal = percentageTotal;
		this.analysisType = analysisType;
		this.headers = headers;
	}
	
	//Constructor method that collects the data for the low level LanguageAnalysis table and the WordAnalysis summary table.
	public Table(String[] rowValues, String[] lastColumnValues, String analysisType, String[] headers, int columnSize)
	{
		this.rowValues = rowValues;
		this.lastColumnValues = lastColumnValues;
		this.analysisType = analysisType;
		this.headers = headers;
		Table.columnSize = columnSize;
	}
	
	//Constructor method that passes in data to test the methods that return results for testing purposes.
	public Table(int decimalPlaces)
	{
		this.decimalPlaces = decimalPlaces;
	}
	
	/*
	 * This method handles how many decimal places to round to by adding zeros to the end of the string if user asks for more than 0 decimal places.
	 * It returns the string with the number of decimal places in which is then used by the 'highLevelTable' and 'lowLevelTable' methods.
	 * The string returned will be used as a parameter when creating a new instance of the DecimalFormat class.
	 */
	public String decimalPlace()
	{
		String decimalPlacesString = "0";
		if(decimalPlaces > 0)
		{
			decimalPlacesString = "0.";
			for(int i = 1; i <= decimalPlaces; i++)
			{
				decimalPlacesString += "0";
			}
		}
		return this.decimalPlacesString = decimalPlacesString;
	}
	
	/*
	 * This method resizes each column in the table so that they all align to be the same size regardless of the text that is inside it.
	 * The data parameter is passed into this method in the 'highLevelTable' and 'lowLevelTable' methods.
	 */
	public static String resizeColumn(String text)
	{
		while(text.length() < columnSize)
		{
			text+= " ";
		}
		return text;	
	}
	
	/*
	 * This method creates the table header and footer that will dynamically adjust to cover up to the longest bar in the bar chart if it goes past
	 * the standard column size. The data parameters are passed into this method in the 'highLevelTable' and 'lowLevelTable' methods.
	 */
	public static String tableHeaderAndFooter(String[] lastColumnValues, int initalHeader, int startAfter)
	{
		initalHeader = initalHeader + startAfter;
		int maxLength = lastColumnValues[0].length();
		for(int i = 0; i < lastColumnValues.length; i++)
		{
			if(lastColumnValues[i].length() > maxLength)
			{
				maxLength = lastColumnValues[i].length();
			}
		}
		String tableHeader1 = "";
		for(int i = 0; i <= initalHeader; i++)
		{
			tableHeader1 += "=";
		}
		for(int i = 0; i <= maxLength-startAfter; i++)
		{
			tableHeader1 += "=";
		}
		return tableHeader1;
	}
	
	/*
	 * This method returns the high level results, in a table, with the variables passed into the constructor method to the number of 
	 * decimal places specified by the user in TUI. Method is used by CharacterAnalysis and LanguageAnalysis. 
	 */
	public void highLevelTable()
	{
		DecimalFormat round = new DecimalFormat(decimalPlacesString);
		System.out.println("\r\rHIGH LEVEL RESULTS:\r");
		System.out.println(tableHeaderAndFooter(lastColumnValues, columnSize*(headers.length-1), columnSize)+"\r");
		for(int i = 0; i < headers.length; i++)
		{
			System.out.print(resizeColumn("| "+headers[i]));
		}
		System.out.println("");
		for(int i = 0; i < headers.length; i++)
		{
			System.out.print(resizeColumn("| "));
		}
		System.out.println("");
		for(int i = 0; i < rowValues.length; i++)
		{
			System.out.print(resizeColumn("| "+rowValues[i]));
			System.out.print(resizeColumn("| "+count[i]));
			System.out.print(resizeColumn("| "+round.format(percentage[i])+"%"));
			if(lastColumnValues[i].length() == 0)
			{
				System.out.println(resizeColumn("| -"));
			}
			else {
			System.out.println(resizeColumn("| "+lastColumnValues[i]));
			}
		}
		for(int i = 0; i < headers.length; i++)
		{
			System.out.print(resizeColumn("|"));
		}
		System.out.println("");
		System.out.print(resizeColumn("| TOTAL"));
		System.out.print(resizeColumn("| "+countTotal));
		System.out.print(resizeColumn("| "+round.format(percentageTotal)+"%"));
		System.out.println(resizeColumn("| -"));
		System.out.println("\r"+tableHeaderAndFooter(lastColumnValues, columnSize*(headers.length-1), columnSize));
	}
	
	/*
	 * This method returns the low level results, in a table, with the variables passed into the constructor method to the number of 
	 * decimal places specified by the user in TUI. Method is used by CharacterAnalysis, LanguageAnalysis and WordAnalysis. For WordAnalysis,
	 * the table is classed as a summary table.
	 */
	public void lowLevelTable()
	{
		DecimalFormat round = new DecimalFormat(decimalPlacesString);
		if(analysisType.equals("CharacterAnalysis") || analysisType.equals("LanguageAnalysis"))
		{
			System.out.println("\r\rLOW LEVEL RESULTS:\r");
		}
		else if(analysisType.equals("WordAnalysis"))
		{
			System.out.println("\r\rSUMMARY:\r");
		}
		System.out.println(tableHeaderAndFooter(lastColumnValues, columnSize*(headers.length-1), columnSize)+"\r");
		for(int i = 0; i < headers.length; i++)
		{
			System.out.print(resizeColumn("| "+headers[i]));
		}
		System.out.println("");
		for(int i = 0; i < headers.length; i++)
		{
			System.out.print(resizeColumn("|"));
		}
		System.out.println("");
		if(analysisType.equals("CharacterAnalysis"))
		{
			for(int i = 0; i < rowValuesChar.length; i++)
			{
				if(count[i] != 0)
				{
					System.out.print(resizeColumn("| "+rowValuesChar[i]));
					System.out.print(resizeColumn("| "+count[i]));
					System.out.print(resizeColumn("| "+round.format(percentage[i])+"%"));
					System.out.println(resizeColumn("| "+lastColumnValues[i]));
				}
			}
			for(int i = 0; i < headers.length; i++)
			{
				System.out.print(resizeColumn("|"));
			}
			System.out.println("");
			System.out.print(resizeColumn("| TOTAL"));
			System.out.print(resizeColumn("| "+countTotal));
			System.out.print(resizeColumn("| "+round.format(percentageTotal)+"%"));
			System.out.println(resizeColumn("| -"));
		}
		else if(analysisType.equals("LanguageAnalysis") || analysisType.equals("WordAnalysis"))
		{
			for(int i = 0; i < rowValues.length; i++)
			{
				System.out.print(resizeColumn("| "+rowValues[i]));
				System.out.println(resizeColumn("| "+lastColumnValues[i]));
			}
		}
		System.out.println("\r"+tableHeaderAndFooter(lastColumnValues, columnSize*(headers.length-1), columnSize)+"\r");
	}
}
