package codingAssignment;

/*
 * This is the super class which stores the text that the user has inputted. This is stored in this super class as it is a common variable that
 * is used across the analysis objects i.e. CharacterAnalysis, LanguageAnalysis and WordAnalysis all inherit/extend this super class. 
 * It is an abstract class as it will never need to be instantiated.
 */
public abstract class StringToAnalyse extends Object
{
	//This is the variable which is common across the analysis objects.
	private String inputFromUser;
	
	//This constructor method declares it is the super class and stores the common variable 
	public StringToAnalyse (String inputFromUser)
	{
		super();
		this.inputFromUser = inputFromUser;
	}
	
	//This is the method used to get/access the common variable from other classes.
	public String getInputFromUser()
	{
		return inputFromUser;
	}
}
