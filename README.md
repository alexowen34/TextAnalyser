# TextAnalyser

*If cloning into an IDE, please ensure Java 8 (JDK 8) and JavaFX for Java 8 is installed.*

The Text Analyser project is the first application that I have ever created. I built this for a 1st-year university project.

The application focuses heavily on string manipulation and natural language processing to display a range of information on text the user provides. This includes:

- Parts of Speech Analysis
- Sentiment Analysis
- Word Level Analysis
- Character Level Analysis

Parts of this application use StanfordCoreNLP (a machine learning library developed by the University of Stanford). This is one reason why Maven is used to bring this dependency into the project.

The application also uses a GUI which I have developed using JavaFX.

I have written 100+ JUnit tests for this application to test the accuracy of its core algorithms.

---------------------------------------------------------------

Known improvements required:

- Code refactoring. The class which builds and handles the logic of the GUI (GUI.java) calls many visibility and disable methods on most GUI components (i.e. buttons, text boxes, labels etc.). This results in the class containing almost 1,000 lines of code. This could be refactored to make the code more maintainable.
