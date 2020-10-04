# TextAnalyser

The Text Analyser project is the first application that I have ever created. I built this for a 1st-year university project.

The application focuses heavily on string manipulation and natural language processing to display a range of information on text the user provides. This includes:

- Parts of Speech Analysis
- Sentiment Analysis
- Word Level Analysis
- Character Level Analysis

Parts of this application use StanfordCoreNLP (a machine learning library developed by the University of Stanford). This is also why Maven is used to bring this dependency into the project.

The application also uses a GUI for its user interface which has been developed using JavaFX.

Over 150 unit tests have been written for this application to test the accuracy of its algorithms.

---------------------------------------------------------------

Known improvements required:

- Code refactoring. The class which builds and handles the logic of the GUI (GUI.java) calls many visibility and disable methods on most GUI components (i.e. buttons, text boxes, labels etc.). This can be refactored to make the code more maintainable.
