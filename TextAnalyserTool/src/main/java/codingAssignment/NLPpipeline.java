package codingAssignment;

import java.util.Properties;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/*
 * This class creates a pipeline to ease the creation of the StanfordCoreNLP object which is used in both WordAnalysis and LanguageAnalysis objects.
 * It handles what properties to pass into the object. The properties are specified when a new instance of this object is created. Properties are
 * important for the StanfordCoreNLP object as it tells the object what features it needs to load and use for the analysis the application is trying 
 * to perform.
 */
public class NLPpipeline 
{
	//Below is a range of global private variables that can be accessed across all methods across this class.
	
	//Below variables are populated via the constructor method.
	private String propertyType;
	
	//Below variables are populated via methods in this class.
	private Properties property;
	private StanfordCoreNLP object;
	
	//Constructor method that is used by WordAnalysis and LanguageAnalysis objects to pass in required data to this object.
	public NLPpipeline(String listOfProperties)
	{
		propertyType = listOfProperties;
	}
	
	/*
	 * This method passes in the properties to the StanfordCoreNLP object that have been passed into the constructor method.
	 * It then returns the StanfordCoreNLP object with the properties loaded it to it.
	 */
	public StanfordCoreNLP getPipeline()
	{
		property = new Properties();
		property.setProperty("annotators", propertyType);
		object = new StanfordCoreNLP(property);
		return object;
	}
}
