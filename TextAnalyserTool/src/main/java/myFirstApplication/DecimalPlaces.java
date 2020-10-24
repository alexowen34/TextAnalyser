package myFirstApplication;

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
