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
import myFirstApplication.CharacterAnalysis;
import myFirstApplication.HandleTextFiles;

@RunWith(value = Parameterized.class)
public class CharacterAnalysisTest
{
	
	//Variables stores the different types of reference characters which are used in each parameterized test.
	private static String fullListOfReferenceCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\1234567890";
	private static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String numbers = "1234567890";
	private static String specialCharacters = "`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\";
	
	//Variables stores the required data, for the @Test methods, that is populated by the constructor method.
	private int testSelector;
	private String fileLocation;
	private String inputString;
	private String referenceOption;
	private int percentageCalcType;
	private int sortingOptionType;
	private int decimalPlaces;
	private String expectedResult1;
	private String expectedResult2;
	private String expectedResult3;
	private String expectedResult4;
	private String expectedResult5;
	private String expectedResult6;
	private String expectedResult7;
	private String expectedResult8;
	
	//Constructor method that takes the required data and passes it to the global variables.
	public CharacterAnalysisTest(int testSelector, String fileLocation, String inputString, String referenceOption, int percentageCalcType, int sortingOptionType, 
			int decimalPlaces, String expectedResult1, String expectedResult2, 
			String expectedResult3, String expectedResult4, String expectedResult5, String expectedResult6, String expectedResult7, 
			String expectedResult8)
	{
		/*
		 * As the CharacterAnalysis class is instantiated in @Test methods, that class references the GUI class which uses JavaFX so 
		 * therefore the JavaFX enviroment needs to be initialized.
		 */
		JavaFXInitializer.initializeToolkit();
		
		this.testSelector = testSelector;
		this.fileLocation = fileLocation;
		this.inputString = inputString;
		this.referenceOption = referenceOption;
		this.percentageCalcType = percentageCalcType;
		this.sortingOptionType = sortingOptionType;
		this.decimalPlaces = decimalPlaces;
		this.expectedResult1 = expectedResult1;
		this.expectedResult2 = expectedResult2;
		this.expectedResult3 = expectedResult3;
		this.expectedResult4 = expectedResult4;
		this.expectedResult5 = expectedResult5;
		this.expectedResult6 = expectedResult6;
		this.expectedResult7 = expectedResult7;
		this.expectedResult8 = expectedResult8;		
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
			{1,"","ABCDEFGHIJKLMNOPQRSTUVWXYZ`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\1234567890", fullListOfReferenceCharacters, 2, 1, 1,
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\1234567890",
			"[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]",
			"[1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41]",
			"[1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41]",
			"",
			"71", "100.00", "100.00"},
			
			//Test case #1
			{1,"","ABCDEFGHIJKLMNOPQRSTUVWXYZ`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\1234567890", letters, 2, 1, 1, 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]",
			"[3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85]",
			"[1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41]",
			"",
			"26", "100.00", "36.62"},
			
			//Test case #2
			{1,"","ABCDEFGHIJKLMNOPQRSTUVWXYZ`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\1234567890", numbers, 2, 1, 1,
			"1234567890",
			"[1, 1, 1, 1, 1, 1, 1, 1, 1, 1]",
			"[10.00, 10.00, 10.00, 10.00, 10.00, 10.00, 10.00, 10.00, 10.00, 10.00]",
			"[1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41]",
			"",
			"10", "100.00", "14.08"},			
			
			//Test case #3
			{1,"","ABCDEFGHIJKLMNOPQRSTUVWXYZ`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\1234567890", specialCharacters, 2, 1, 1,
			"`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\",
			"[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]",
			"[2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86, 2.86]",
			"[1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41, 1.41]",
			"",
			"35", "100.00", "49.30"},
			
			//Test case #4
			{1,"","ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", letters, 2, 1, 1, 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]",
			"[3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85]",
			"[3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85, 3.85]",
			"",
			"52", "100.00", "100.00"},
			
			//Test case #5
			{1,"","DddD cCC bB a * && £££ !!!! 4444 333 22 1", fullListOfReferenceCharacters, 2, 1, 1,
			"ABCD!£&*1234",
			"[1, 2, 3, 4, 4, 3, 2, 1, 1, 2, 3, 4]",
			"[3.33, 6.67, 10.00, 13.33, 13.33, 10.00, 6.67, 3.33, 3.33, 6.67, 10.00, 13.33]",
			"[3.33, 6.67, 10.00, 13.33, 13.33, 10.00, 6.67, 3.33, 3.33, 6.67, 10.00, 13.33]",
			"",
			"30", "100.00", "100.00"},
			
			//Test case #6
			{1,"","dDDD CcC Bb A * && £££ !!!! 4444 333 22 1", fullListOfReferenceCharacters, 2, 2, 1,
			"!4D£3C&2B*1A",
			"[4, 4, 4, 3, 3, 3, 2, 2, 2, 1, 1, 1]",
			"[13.33, 13.33, 13.33, 10.00, 10.00, 10.00, 6.67, 6.67, 6.67, 3.33, 3.33, 3.33]",
			"[13.33, 13.33, 13.33, 10.00, 10.00, 10.00, 6.67, 6.67, 6.67, 3.33, 3.33, 3.33]",
			"",
			"30", "100.00", "100.00"},
			
			//Test case #7
			{1,"","dddD cCc BB a * && £££ !!!! 4444 333 22 1", fullListOfReferenceCharacters, 2, 3, 1,
			"*1A&2B£3C!4D",
			"[1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4]",
			"[3.33, 3.33, 3.33, 6.67, 6.67, 6.67, 10.00, 10.00, 10.00, 13.33, 13.33, 13.33]",
			"[3.33, 3.33, 3.33, 6.67, 6.67, 6.67, 10.00, 10.00, 10.00, 13.33, 13.33, 13.33]",
			"",
			"30", "100.00", "100.00"},
			
			//Test case #8
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", fullListOfReferenceCharacters, 2, 1, 1,
			"ADHNRSTWX!&@.130",
			"[2, 1, 2, 1, 1, 3, 3, 2, 1, 2, 1, 1, 3, 4, 4, 3]",
			"[5.88, 2.94, 5.88, 2.94, 2.94, 8.82, 8.82, 5.88, 2.94, 5.88, 2.94, 2.94, 8.82, 11.76, 11.76, 8.82]",
			"[5.88, 2.94, 5.88, 2.94, 2.94, 8.82, 8.82, 5.88, 2.94, 5.88, 2.94, 2.94, 8.82, 11.76, 11.76, 8.82]",
			"",
			"34", "100.00", "100.00"},
			
			//Test case #9
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", fullListOfReferenceCharacters, 2, 2, 1,
			"13.0ST!AHW&@DNRX",
			"[4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1]",
			"[11.76, 11.76, 8.82, 8.82, 8.82, 8.82, 5.88, 5.88, 5.88, 5.88, 2.94, 2.94, 2.94, 2.94, 2.94, 2.94]",
			"[11.76, 11.76, 8.82, 8.82, 8.82, 8.82, 5.88, 5.88, 5.88, 5.88, 2.94, 2.94, 2.94, 2.94, 2.94, 2.94]",
			"",
			"34", "100.00", "100.00"},
			
			//Test case #10
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", fullListOfReferenceCharacters, 2, 3, 1,
			"&@DNRX!AHW.0ST13",
			"[1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4]",
			"[2.94, 2.94, 2.94, 2.94, 2.94, 2.94, 5.88, 5.88, 5.88, 5.88, 8.82, 8.82, 8.82, 8.82, 11.76, 11.76]",
			"[2.94, 2.94, 2.94, 2.94, 2.94, 2.94, 5.88, 5.88, 5.88, 5.88, 8.82, 8.82, 8.82, 8.82, 11.76, 11.76]",
			"",
			"34", "100.00", "100.00"},
			
			//Test case #11
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", letters, 2, 1, 1,
			"ADHNRSTWX",
			"[2, 1, 2, 1, 1, 3, 3, 2, 1]",
			"[12.50, 6.25, 12.50, 6.25, 6.25, 18.75, 18.75, 12.50, 6.25]",
			"[5.88, 2.94, 5.88, 2.94, 2.94, 8.82, 8.82, 5.88, 2.94]",
			"",
			"16", "100.00", "47.06"},
			
			//Test case #12
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", letters, 2, 2, 1,
			"STAHWDNRX",
			"[3, 3, 2, 2, 2, 1, 1, 1, 1]",
			"[18.75, 18.75, 12.50, 12.50, 12.50, 6.25, 6.25, 6.25, 6.25]",
			"[8.82, 8.82, 5.88, 5.88, 5.88, 2.94, 2.94, 2.94, 2.94]",
			"",
			"16", "100.00", "47.06"},
			
			//Test case #13
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", letters, 2, 3, 1,
			"DNRXAHWST",
			"[1, 1, 1, 1, 2, 2, 2, 3, 3]",
			"[6.25, 6.25, 6.25, 6.25, 12.50, 12.50, 12.50, 18.75, 18.75]",
			"[2.94, 2.94, 2.94, 2.94, 5.88, 5.88, 5.88, 8.82, 8.82]",
			"",
			"16", "100.00", "47.06"},
			
			//Test case #14
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", numbers, 2, 1, 1,
			"130",
			"[4, 4, 3]",
			"[36.36, 36.36, 27.27]",
			"[11.76, 11.76, 8.82]",
			"",
			"11", "100.00", "32.35"},
			
			//Test case #15
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", numbers, 2, 2, 1,
			"130",
			"[4, 4, 3]",
			"[36.36, 36.36, 27.27]",
			"[11.76, 11.76, 8.82]",
			"",
			"11", "100.00", "32.35"},
			
			//Test case #16
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", numbers, 2, 3, 1,
			"013",
			"[3, 4, 4]",
			"[27.27, 36.36, 36.36]",
			"[8.82, 11.76, 11.76]",
			"",
			"11", "100.00", "32.35"},
		
			//Test case #17
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", specialCharacters, 2, 1, 1,
			"!&@.",
			"[2, 1, 1, 3]",
			"[28.57, 14.29, 14.29, 42.86]",
			"[5.88, 2.94, 2.94, 8.82]",
			"",
			"7", "100.00", "20.59"},
			
			//Test case #18
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", specialCharacters, 2, 2, 1,
			".!@&",
			"[3, 2, 1, 1]",
			"[42.86, 28.57, 14.29, 14.29]",
			"[8.82, 5.88, 2.94, 2.94]",
			"",
			"7", "100.00", "20.59"},
			
			//Test case #19
			{1,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", specialCharacters, 2, 3, 1,
			"@&!.",
			"[1, 1, 2, 3]",
			"[14.29, 14.29, 28.57, 42.86]",
			"[2.94, 2.94, 5.88, 8.82]",
			"",
			"7", "100.00", "20.59"},
			
			//Test case #20
			{2,"","ABCDEFGHIJKLMNOPQRSTUVWXYZ`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\1234567890", fullListOfReferenceCharacters, 0, 1, 1,
				"[Letters, Numbers, Special Characters]",
				"[26, 10, 35]",
				"[36.62, 14.08, 49.30]",
				"",
				"71", "100.00", "", ""},
			
			//Test case #21
			{2,"","ABCDEFGHIJKLMNOPQRSTUVWXYZ", fullListOfReferenceCharacters, 0, 1, 1,
				"[Letters, Numbers, Special Characters]",
				"[26, 0, 0]",
				"[100.00, 0.00, 0.00]",
				"",
				"26", "100.00", "", ""},
			
			//Test case #22
			{2,"","1234567890", fullListOfReferenceCharacters, 0, 1, 1,
				"[Letters, Numbers, Special Characters]",
				"[0, 10, 0]",
				"[0.00, 100.00, 0.00]",
				"",
				"10", "100.00", "", ""},
			
			//Test case #23
			{2,"","`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\", fullListOfReferenceCharacters, 0, 1, 1,
				"[Letters, Numbers, Special Characters]",
				"[0, 0, 35]",
				"[0.00, 0.00, 100.00]",
				"",
				"35", "100.00", "", ""},
			
			//Test case #24
			{2,"","ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", fullListOfReferenceCharacters, 0, 1, 1,
				"[Letters, Numbers, Special Characters]",
				"[26, 10, 0]",
				"[72.22, 27.78, 0.00]",
				"",
				"36", "100.00", "", ""},
			
			//Test case #25
			{2,"","ABCDEFGHIJKLMNOPQRSTUVWXYZ`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\", fullListOfReferenceCharacters, 0, 1, 1,
				"[Letters, Numbers, Special Characters]",
				"[26, 0, 35]",
				"[42.62, 0.00, 57.38]",
				"",
				"61", "100.00", "", ""},
			
			//Test case #26
			{2,"","1234567890`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\", fullListOfReferenceCharacters, 0, 1, 1,
				"[Letters, Numbers, Special Characters]",
				"[0, 10, 35]",
				"[0.00, 22.22, 77.78]",
				"",
				"45", "100.00", "", ""},
			
			//Test case #27
			{2,"","Th!s !s a t3st & H3110 W0R1d... @A13X 0W3N", fullListOfReferenceCharacters, 0, 1, 1,
				"[Letters, Numbers, Special Characters]",
				"[16, 11, 7]",
				"[47.06, 32.35, 20.59]",
				"",
				"34", "100.00", "", ""},
			
			//Test case #28
			{2,"TestData&Documentation/CharacterAnalysisTestFiles/CharacterAnalysis-TestCase28.txt", "", fullListOfReferenceCharacters, 0, 1, 1,
				"[Letters, Numbers, Special Characters]",
				"[16, 11, 7]",
				"[47.06, 32.35, 20.59]",
				"",
				"34", "100.00", "", ""},
			});
	}
	
	
	/*
	 * This is the method which actually does the testing with the data that has been inputted above.
	 * The @Test methods will be called and ran for each parameter entered above.
	 * Depending on the testSelctor condition/value, the method does assertEquals statements to check if the 
	 * two pieces of data (i.e. the expected result and the actual result) match each other. If they do match, 
	 * the result will be a pass, if they do not match then the result will be a failure or error.
	 */
	@Test
	public void testResultsForLowLevelTable()
	{
		if(testSelector == 1)
		{
			for(int i = 1; i <= percentageCalcType; i++)
			{
				CharacterAnalysis test1 = new CharacterAnalysis(inputString, referenceOption, i, sortingOptionType, decimalPlaces);
				int[] intPlaceholder = test1.countCharFrequencies();
				char[] charPlaceholder = test1.getReferenceCharacters();
				double[] doublePlaceholder = test1.relativeCharFrequencies();
				ArrayList<Character> charValidPlaceholder = new ArrayList<Character>();
				ArrayList<Integer> intValidPlaceholder = new ArrayList<Integer>();
				ArrayList<Double> doubleValidPlaceholder = new ArrayList<Double>();
				ArrayList<String> stringArrayForRoundedDouble = new ArrayList<String>();
				DecimalFormat round = new DecimalFormat("0.00");
				int counter = 0;
				for(int j = 0; j < intPlaceholder.length; j++)
				{
					if(intPlaceholder[j] != 0)
					{
						charValidPlaceholder.add(charPlaceholder[j]);
						intValidPlaceholder.add(intPlaceholder[j]);
						doubleValidPlaceholder.add(doublePlaceholder[j]);
						stringArrayForRoundedDouble.add(round.format(doubleValidPlaceholder.get(counter)));
						counter++;
					}
				}
				char[] charRefPlaceholder = expectedResult1.toCharArray();
				String result1 = charValidPlaceholder.toString();
				String expectedResult1 = Arrays.toString(charRefPlaceholder);
				String result2 = intValidPlaceholder.toString();
				String result3 = stringArrayForRoundedDouble.toString();
				String result6 = String.valueOf(test1.getTotalCount());
				String result7 = round.format(test1.getTotalPercentage());
				assertEquals(expectedResult1, result1);
				assertEquals(expectedResult2, result2);
				if(i == 1)
				{
					assertEquals(expectedResult3, result3);
					assertEquals(expectedResult7, result7);
				}
				if(i == 2)
				{
					assertEquals(expectedResult4, result3);
					assertEquals(expectedResult8, result7);
				}
				assertEquals(expectedResult6, result6);
				
			}
		}
	}
	
	@Test
	public void testResultsForHighLevelTable() throws FileNotFoundException
	{
		if(testSelector == 2)
		{
			if(inputString.isEmpty())
			{
				HandleTextFiles test2File = new HandleTextFiles(fileLocation);
				test2File.fileToString();
				inputString = test2File.toString();
			}
			CharacterAnalysis test2 = new CharacterAnalysis(inputString, referenceOption, percentageCalcType, sortingOptionType, decimalPlaces);
			test2.countCharFrequencies();
			test2.relativeCharFrequencies();
			test2.summary();
			String [] stringPlaceholder1 = test2.getRowValues();
			int[] intPlaceholder = test2.getCountSummaryOutput();
			double [] doublePlaceholder = test2.getPercentageSummaryOutput();
			ArrayList<String> stringValidPlaceholder1 = new ArrayList<String>();
			ArrayList<Integer> intValidPlaceholder = new ArrayList<Integer>();
			ArrayList<Double> doubleValidPlaceholder = new ArrayList<Double>();
			ArrayList<String> stringArrayForRoundedDouble = new ArrayList<String>();
			DecimalFormat round = new DecimalFormat("0.00");
			int counter = 0;
			for(int i = 0; i < intPlaceholder.length; i++)
			{
				stringValidPlaceholder1.add(stringPlaceholder1[i]);
				intValidPlaceholder.add(intPlaceholder[i]);
				doubleValidPlaceholder.add(doublePlaceholder[i]);
				stringArrayForRoundedDouble.add(round.format(doubleValidPlaceholder.get(counter)));
				counter++;
			}
			String result1 = stringValidPlaceholder1.toString();
			String result2 = intValidPlaceholder.toString();
			String result3 = stringArrayForRoundedDouble.toString();
			String result5 = String.valueOf(test2.getTotalCount());
			String result6 = round.format(test2.getTotalPercentage());
			assertEquals(expectedResult1, result1);
			assertEquals(expectedResult2, result2);
			assertEquals(expectedResult3, result3);
			assertEquals(expectedResult5, result5);
			assertEquals(expectedResult6, result6);
		}
	}
}
