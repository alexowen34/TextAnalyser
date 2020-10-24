package myFirstApplication;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*
 * This class handles extracting contents from text files for the application. This is required if the user selects for their text to be inputted
 * into the application via a text file. It is also required if the user selects to do POS analysis as the file 'POStags.txt' contains relevant 
 * reference data to conduct this analysis.
 */
public class HandleTextFiles
{
	//Below is a range of global private variables that can be accessed across all methods in this class.

	//Below variables are populated via the constructor method.
	private String location;

	//Below variables are populated via methods in this class.
	private int paragraphCounter;
	private String stringContent;
	private HashMap<String, String> fileContent;
	
	//Constructor method that obtains the file name/location and stores this in private String variable 'location'.
	public HandleTextFiles (String location)
	{
		this.location = location;
	}
	
	/* 
	 * This method extracts the content across all lines of the given txt file into a single String variable.
	 * It also counts the number of lines (that contains content) in the text file therefore returning the number of paragraphs.
	 */
	public String fileToString() throws FileNotFoundException
	{
		paragraphCounter = 0;
		Scanner reader1 = new Scanner(new FileInputStream(location));
		String toString = "";
		while(reader1.hasNextLine())
		{
			String nextLineContent = reader1.nextLine();
			toString += nextLineContent;
			if(!nextLineContent.isEmpty())
			{
				toString += " ";
				paragraphCounter++;
			}
		}
		reader1.close();
		return stringContent = toString.trim();
	}
	
	//This method gets the POS tags and descriptions from the hard coded file location txt file and puts them into a HashMap.
	public HashMap<String, String> fileToHashMap() throws FileNotFoundException
	{
		Scanner reader1 = new Scanner(new FileInputStream(location));
		HashMap<String, String> fileContent = new HashMap<String, String>();
		String[] textFileData = new String[72];
		int i = 0;
		while(reader1.hasNextLine())
		{
			textFileData[i] = reader1.nextLine();
			i++;
		}
		for(int j = 1; j < textFileData.length; j = j + 2)
		{
			fileContent.put(textFileData[j-1], textFileData[j]);
		}
		reader1.close();
		this.fileContent = fileContent;
		return this.fileContent;
	}
	
	//Allows the user to access the number of paragraphs in given txt file.
	public int getParagraphCount()
	{
		return paragraphCounter;
	}
	
	//Allows the user to access the String that has been populated with the txt file content in 'fileToString' method.
	public String toString()
	{
		return stringContent;
	}
}
