package myFirstApplication;

/*
 * This class is used to create a string representation of how many decimal places a number should be rounded to.
 * It is used by CharacterAnalysis and CreatePieChartData classes.
 */
public class DecimalPlaces 
{	
	public String stringRepresentationOfDecimals()
	{
		String decimalPlacesString = "0";
		if(GUI.numberOfDecimalPlaces > 0)
		{
			decimalPlacesString = "0.";
			for(int i = 1; i <= GUI.numberOfDecimalPlaces; i++)
			{
				decimalPlacesString += "0";
			}
		}
		return decimalPlacesString;
	}
}
