package myFirstApplication;

/*
 * Abstract class that stores the users text to be analysed. This is extended and used across multiple classes:
 * CharacterAnalysis, WordAnalysis and LanguageAnalysis.
 */
abstract class TextToBeAnalysed 
{
	protected String textToBeAnalysed = GUI.textToBeAnalysed;
}
