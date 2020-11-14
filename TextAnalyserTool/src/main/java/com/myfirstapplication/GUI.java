//Planned features to add in:
//#1: Practice writing and recording data to a DB i.e. the users text and it's results.
//#2: Add option to export to Excel.

package com.myfirstapplication;

import com.myfirstapplication.analysis.CharacterAnalysis;
import com.myfirstapplication.analysis.LanguageAnalysis;
import com.myfirstapplication.analysis.WordAnalysis;
import com.myfirstapplication.support.HandleTextFiles;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application
{
	public static final String fullListOfReferenceCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\1234567890";
	public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String numbers = "1234567890";
	public static final String specialCharacters = "`¬¦!£$%^&*()_-+={[}]~#:;@'<,>.?/|\"\\";
	protected static String charactersInScope = fullListOfReferenceCharacters;
	public static String textToBeAnalysed = "";
	private static LanguageAnalysis langAnalysis;
	private static CharacterAnalysis charAnalysis;
	private boolean decimalPlaceChange;
	private boolean charAnalysisPieChartVisible;
	private boolean percentageOptionsBoxDisable = true;
	private boolean filteringOptionsBoxDisable = false;
	private boolean sortAscDisable = false;
	private boolean sortDescDisable = false;
	private int paragraphCount;
	private int sortingOption = 1;
	private int percentageCalculation =1;
	public static int numberOfDecimalPlaces = 0;
	private XYChart.Series<String, Number> characterAnalysisBarChartData;
	private static TextField textForAnalysis = new TextField();
	private static Label applicationHeader = new Label(), or = new Label(), displayInputMethodHeader = new Label(), displayAnalysisTypeHeader = new Label(), importFileSuccessMessage = new Label(), displayTextHeader = new Label(), characterBarChartHeader = new Label(), sentimentResultsHeader = new Label(), wordAnalysisResultsHeader = new Label(), partsOfSpeechAnalysisResultsHeader = new Label(), characterPieChartHeader = new Label();
	private static TextArea displayText = new TextArea();
	private static PieChart characterAnalysisPieChart = new PieChart(), sentimentAnalysisPieChart = new PieChart();
	private static Rectangle analysisBackgroundBox = new Rectangle(), inputBackgroundBox = new Rectangle();
	private static Button displayCharacterAnalysis = new Button(), displayWordAnalysis = new Button(), displaySentimentAnalysis = new Button(), displayPartsOfSpeechAnalysis = new Button(), getFilePath = new Button(), sortAscButton = new Button(), sortDescButton = new Button(), removeDecimal = new Button(), addDecimal = new Button(), displaySentimentTable = new Button(), displaySentimentPieChart = new Button(), popOutBarChart = new Button(), displayCharacterPieChart = new Button(), displayCharacterBarChart = new Button();
	private static Button[] arrayOfBottomButtons = {sortAscButton, sortDescButton, removeDecimal, addDecimal, displaySentimentTable, displaySentimentPieChart, popOutBarChart, displayCharacterPieChart, displayCharacterBarChart};
	private static String[] blackIconsFilePaths = {"file:Assets/Icons/Black/BLACK_SortASCIcon.png", "file:Assets/Icons/Black/BLACK_SortDESCIcon.png", "", "", "file:Assets/Icons/Black/BLACK_TableIcon.png", "file:Assets/Icons/Black/BLACK_PieChartIcon.png", "file:Assets/Icons/Black/BLACK_MagnifyingGlassIcon.png", "file:Assets/Icons/Black/BLACK_PieChartIcon.png", "file:Assets/Icons/Black/BLACK_BarChartIcon.png"};
	private static String[] whiteIconsFilePaths = {"file:Assets/Icons/White/WHITE_SortASCIcon.png", "file:Assets/Icons/White/WHITE_SortDESCIcon.png", "", "", "file:Assets/Icons/White/WHITE_TableIcon.png", "file:Assets/Icons/White/WHITE_PieChartIcon.png", "file:Assets/Icons/White/WHITE_MagnifyingGlassIcon.png", "file:Assets/Icons/White/WHITE_PieChartIcon.png", "file:Assets/Icons/White/WHITE_BarChartIcon.png"};
	private static ComboBox<String> filteringOptionsBox = new ComboBox<String>(), percentageOptionsBox = new ComboBox<String>();
	private static TableView<LanguageAnalysis> sentimentAnalysisTable = new TableView<LanguageAnalysis>();
	private static TableColumn<LanguageAnalysis, String> sentiment = new TableColumn<LanguageAnalysis, String>(), sentence = new TableColumn<LanguageAnalysis, String>();
	private static TableView<WordAnalysis> wordAnalysisTable = new TableView<WordAnalysis>();
	private static TableColumn<WordAnalysis, String> metric = new TableColumn<WordAnalysis, String>(), value = new TableColumn<WordAnalysis, String>();
    private static CategoryAxis xAxis = new CategoryAxis();
    private static NumberAxis yAxis = new NumberAxis();
	private static BarChart<String, Number> characterAnalysisBarChart = new BarChart<>(xAxis, yAxis);
	private static TableView<LanguageAnalysis> partsOfSpeechAnalysisTable = new TableView<LanguageAnalysis>();
	private static TableColumn<LanguageAnalysis, String> word = new TableColumn<LanguageAnalysis, String>(), POStag = new TableColumn<LanguageAnalysis, String>();
	private ToggleButton darkModeActivate = new ToggleButton();
    private String darkModeFilePathCSS = "file:Styling&Layout/DarkMode.css";
    private static boolean darkModeActivated;
    private AnchorPane root = new AnchorPane();
    
    public static void main(String[] args)
    {
        launch();
    }
    
    private void setupTopButtons()
    {
    	displayCharacterAnalysis.setLayoutX(36);
    	displayCharacterAnalysis.setLayoutY(341);
    	displayCharacterAnalysis.setText("Character Analysis");
    	displayCharacterAnalysis.setOnAction(e -> displayCharacterAnalysis(e));
    	
    	displayWordAnalysis.setLayoutX(194);
    	displayWordAnalysis.setLayoutY(341);
    	displayWordAnalysis.setText("Word Analysis");
    	displayWordAnalysis.setOnAction(e -> displayWordAnalysis(e));
    	
    	displaySentimentAnalysis.setLayoutX(329);
    	displaySentimentAnalysis.setLayoutY(341);
    	displaySentimentAnalysis.setText("Sentiment Analysis");
    	displaySentimentAnalysis.setOnAction(e -> displaySentimentAnalysis(e));
    	
    	displayPartsOfSpeechAnalysis.setLayoutX(491);
    	displayPartsOfSpeechAnalysis.setLayoutY(341);
    	displayPartsOfSpeechAnalysis.setText("Parts of Speech Analysis");
    	displayPartsOfSpeechAnalysis.setOnAction(e -> displayPartsOfSpeechAnalysis(e));
    	
    	getFilePath.setLayoutX(35);
    	getFilePath.setLayoutY(143);
    	getFilePath.setText("Select Text File");
    	getFilePath.setOnAction(e -> {
			try {
				getFilePath(e);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
    }
    
    private void setupBottomButtons()
    {
    	int prefHeight = 32, prefWidth = 54;
    	for(int i = 0; i < arrayOfBottomButtons.length; i++)
    	{
    		arrayOfBottomButtons[i].setPrefHeight(prefHeight);
    		arrayOfBottomButtons[i].setPrefWidth(prefWidth);
    	}
    	
        sortAscButton.setLayoutX(247);
        sortAscButton.setLayoutY(532); 
        sortAscButton.setOnAction(e -> sortAsc(e));
        
        sortDescButton.setLayoutX(178);
        sortDescButton.setLayoutY(532); 
        sortDescButton.setOnAction(e -> sortDesc(e));
        
        removeDecimal.setLayoutX(37); 
        removeDecimal.setLayoutY(532);
        removeDecimal.setText("<- .0");
        removeDecimal.setOnAction(e -> decreaseDecimalPlaces(e));
        
        addDecimal.setLayoutX(108); 
        addDecimal.setLayoutY(532); 
        addDecimal.setText("-> .0");
        addDecimal.setOnAction(e -> increaseDecimalPlaces(e));
        
        displaySentimentTable.setLayoutX(653);
        displaySentimentTable.setLayoutY(586);
        displaySentimentTable.setOnAction(e -> displaySentimentTable(e));
        
        displaySentimentPieChart.setLayoutX(653); 
        displaySentimentPieChart.setLayoutY(586); 
        displaySentimentPieChart.setOnAction(e -> displaySentimentPieChart(e));
        
        popOutBarChart.setLayoutX(37);
        popOutBarChart.setLayoutY(586); 
        popOutBarChart.setOnAction(e -> popOutBarChart(e));
        
        displayCharacterPieChart.setLayoutX(653);
        displayCharacterPieChart.setLayoutY(586);
        displayCharacterPieChart.setOnAction(e -> displayCharacterPieChart(e));
        
        displayCharacterBarChart.setLayoutX(653); 
        displayCharacterBarChart.setLayoutY(586);
        displayCharacterBarChart.setOnAction(e -> displayCharacterBarChart(e));
    }

    private void addIconsToButtons(String[] filePaths)
    {
        Image[] img = new Image[filePaths.length];
        ImageView[] view = new ImageView[filePaths.length];
        for(int i = 0; i < arrayOfBottomButtons.length; i++)
        {
        	//Leaves out buttons at index 2 and 3 in the array as they don't require images.
        	if(i != 2 && i != 3)
        	{
            	img[i] = new Image(filePaths[i]);
            	view[i] = new ImageView(img[i]);
                view[i].setFitHeight(22); view[i].setFitWidth(23);
                if(filePaths[i].contains("SortASC") || filePaths[i].contains("SortDESC"))
                {
                	view[i].setFitHeight(22); view[i].setFitWidth(24);
                }
            	arrayOfBottomButtons[i].setGraphic(view[i]);
        	}
        }
    }
    
    private void setupLabels()
    {
    	applicationHeader.setLayoutY(9);
    	applicationHeader.setPrefHeight(66); applicationHeader.setPrefWidth(743);
    	applicationHeader.setStyle("-fx-font-size: 30px");
    	applicationHeader.setAlignment(Pos.CENTER);
    	applicationHeader.setText("Text Analyser");
    	
    	or.setLayoutX(36); or.setLayoutY(187);
    	or.setStyle("-fx-font-size: 16px");
    	or.setText("OR");

    	displayInputMethodHeader.setLayoutX(36); displayInputMethodHeader.setLayoutY(100);
    	displayInputMethodHeader.setStyle("-fx-font-size: 18px");
    	displayInputMethodHeader.setText("Please select your text import method:");

    	displayAnalysisTypeHeader.setLayoutX(36); displayAnalysisTypeHeader.setLayoutY(297);
    	displayAnalysisTypeHeader.setStyle("-fx-font-size: 18px");
    	displayAnalysisTypeHeader.setText("Please select the type of analysis you would like to perform:");

        importFileSuccessMessage.setLayoutX(167); importFileSuccessMessage.setLayoutY(148);
        importFileSuccessMessage.setStyle("-fx-text-fill: #12a454");
        importFileSuccessMessage.setText("Your text has been imported successfully!");
        
        displayTextHeader.setLayoutX(36); displayTextHeader.setLayoutY(422);
        displayTextHeader.setStyle("-fx-font-size: 18px");
        displayTextHeader.setText("Here is your text:");
        
        characterBarChartHeader.setLayoutX(36); characterBarChartHeader.setLayoutY(589);
        characterBarChartHeader.setPrefHeight(21); characterBarChartHeader.setPrefWidth(673);
        characterBarChartHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        characterBarChartHeader.setAlignment(Pos.CENTER);
        characterBarChartHeader.setText("Number of Individual Characters");
        
        sentimentResultsHeader.setLayoutX(36); sentimentResultsHeader.setLayoutY(589);
        sentimentResultsHeader.setPrefHeight(21); sentimentResultsHeader.setPrefWidth(673);
        sentimentResultsHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        sentimentResultsHeader.setAlignment(Pos.CENTER);
        sentimentResultsHeader.setText("Sentiment Analysis Per Sentence");
        
        wordAnalysisResultsHeader.setLayoutX(36); wordAnalysisResultsHeader.setLayoutY(589);
        wordAnalysisResultsHeader.setPrefHeight(21); wordAnalysisResultsHeader.setPrefWidth(673);
        wordAnalysisResultsHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        wordAnalysisResultsHeader.setAlignment(Pos.CENTER);
        wordAnalysisResultsHeader.setText("Word Analysis Results");
        
        partsOfSpeechAnalysisResultsHeader.setLayoutX(36); partsOfSpeechAnalysisResultsHeader.setLayoutY(589);
        partsOfSpeechAnalysisResultsHeader.setPrefHeight(21); partsOfSpeechAnalysisResultsHeader.setPrefWidth(673);
        partsOfSpeechAnalysisResultsHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        partsOfSpeechAnalysisResultsHeader.setAlignment(Pos.CENTER);
        partsOfSpeechAnalysisResultsHeader.setText("Parts Of Speech Analysis Results");
        
        characterPieChartHeader.setLayoutX(36); characterPieChartHeader.setLayoutY(589);
        characterPieChartHeader.setPrefHeight(21); characterPieChartHeader.setPrefWidth(673);
        characterPieChartHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        characterPieChartHeader.setAlignment(Pos.CENTER);
        characterPieChartHeader.setText("Summary of Character Types");
    }
    
    private void setupCharts()
    {
    	characterAnalysisPieChart.setLayoutX(36); characterAnalysisPieChart.setLayoutY(617);
        characterAnalysisPieChart.setPrefHeight(278); characterAnalysisPieChart.setPrefWidth(673);
        
        characterAnalysisBarChart.setLayoutX(18); characterAnalysisBarChart.setLayoutY(616);
        characterAnalysisBarChart.setPrefHeight(286); characterAnalysisBarChart.setPrefWidth(701);
        
        sentimentAnalysisPieChart.setLayoutX(36); sentimentAnalysisPieChart.setLayoutY(617);
        sentimentAnalysisPieChart.setPrefHeight(278); sentimentAnalysisPieChart.setPrefWidth(673);
    }
    
    @SuppressWarnings("unchecked")
	private void setupTables()
    {
    	sentimentAnalysisTable.setLayoutX(36); sentimentAnalysisTable.setLayoutY(631);
        sentimentAnalysisTable.setPrefHeight(250); sentimentAnalysisTable.setPrefWidth(673);
        sentiment.setPrefWidth(118); sentence.setPrefWidth(554);
        sentiment.setText("Senitment"); sentence.setText("Sentence");
        sentimentAnalysisTable.getColumns().addAll(sentiment, sentence);
        
        wordAnalysisTable.setLayoutX(36); wordAnalysisTable.setLayoutY(631);
        wordAnalysisTable.setPrefHeight(250); wordAnalysisTable.setPrefWidth(673);
        metric.setPrefWidth(179); value.setPrefWidth(493);
        metric.setText("Metric"); value.setText("Value");
        wordAnalysisTable.getColumns().addAll(metric, value);
        
        partsOfSpeechAnalysisTable.setLayoutX(36); partsOfSpeechAnalysisTable.setLayoutY(631);
        partsOfSpeechAnalysisTable.setPrefHeight(250); partsOfSpeechAnalysisTable.setPrefWidth(673);
        POStag.setPrefWidth(179); word.setPrefWidth(493);
        POStag.setText("Parts of Speech"); word.setText("Word");
        partsOfSpeechAnalysisTable.getColumns().addAll(POStag, word);
    }
    
    private void setupComboBoxs()
    {
        filteringOptionsBox.setLayoutX(318); filteringOptionsBox.setLayoutY(533);
        filteringOptionsBox.setPrefHeight(31); filteringOptionsBox.setPrefWidth(163);
        filteringOptionsBox.setPromptText("Filter Characters:");
        filteringOptionsBox.getItems().addAll("All characters", "Only letters", "Only numbers", "Only special characters");
        filteringOptionsBox.setOnAction(e -> filterCharacters(e));
        
        percentageOptionsBox.setLayoutX(497); percentageOptionsBox.setLayoutY(533);
        percentageOptionsBox.setPrefHeight(31); percentageOptionsBox.setPrefWidth(210);
        percentageOptionsBox.setPromptText("Percentage Calculation:");
        percentageOptionsBox.getItems().addAll("Only the filtered characters", "All types of characters");
        percentageOptionsBox.setOnAction(e -> percentageCalculation(e));
    }
    
    private void setupRectangles()
    {
        inputBackgroundBox.setLayoutY(85);
        inputBackgroundBox.setHeight(193); inputBackgroundBox.setWidth(743);
        inputBackgroundBox.setStyle("-fx-fill: #eaeaea");
        
        analysisBackgroundBox.setLayoutY(401);
        analysisBackgroundBox.setHeight(504); analysisBackgroundBox.setWidth(743);
        analysisBackgroundBox.setStyle("-fx-fill: #eaeaea");
    }
    
    private void setupOther()
    {
        darkModeActivate.setText("Dark Mode");
        darkModeActivate.setLayoutX(599); darkModeActivate.setLayoutY(27);
        darkModeActivate.setPrefHeight(20); darkModeActivate.setPrefWidth(110);
        textForAnalysis.setLayoutX(35); textForAnalysis.setLayoutY(221);
        textForAnalysis.setPrefHeight(31); textForAnalysis.setPrefWidth(673);
        textForAnalysis.setPromptText("Enter text");
        displayText.setLayoutX(36); displayText.setLayoutY(467);
        displayText.setPrefHeight(47); displayText.setPrefWidth(673);
    }
    
	@Override
    public void start(Stage primaryStage) throws Exception 
    {
		/*
		 * The below commented out code links to the FXML file incase it's needed in the future.
		 * root = FXMLLoader.load(getClass().getResource("Main.fxml"));
         */
		
        initialize();

		//The below code calls methods to set up the JavaFX components i.e. set sizes, set layout, add images, add action events etc.
        setupTopButtons();
        setupBottomButtons();
        addIconsToButtons(blackIconsFilePaths);
        setupLabels();
        setupCharts();
        setupTables();
        setupComboBoxs();
        setupRectangles();
        setupOther();
        
        //This code adds the JavaFX components, that were setup in the above methods, to the AnchorPane.
        root.setPrefHeight(905);
        root.setPrefWidth(743);
        root.getChildren().add(inputBackgroundBox);
        root.getChildren().add(applicationHeader);
        root.getChildren().add(or);
        root.getChildren().add(displayInputMethodHeader);
        root.getChildren().add(displayAnalysisTypeHeader);
        root.getChildren().add(displayCharacterAnalysis);
        root.getChildren().add(displayWordAnalysis);
        root.getChildren().add(displaySentimentAnalysis);
        root.getChildren().add(displayPartsOfSpeechAnalysis);
        root.getChildren().add(getFilePath);
        root.getChildren().add(analysisBackgroundBox);
        root.getChildren().add(darkModeActivate);
        root.getChildren().add(textForAnalysis);
        root.getChildren().add(importFileSuccessMessage);
        root.getChildren().add(displayTextHeader);
        root.getChildren().add(displayText);
        root.getChildren().add(characterBarChartHeader);
        root.getChildren().add(characterAnalysisPieChart);
        root.getChildren().add(characterAnalysisBarChart);
        root.getChildren().add(sentimentAnalysisPieChart);
        root.getChildren().add(sentimentAnalysisTable);
        root.getChildren().add(sentimentResultsHeader);
        root.getChildren().add(wordAnalysisTable);
        root.getChildren().add(wordAnalysisResultsHeader);
        root.getChildren().add(partsOfSpeechAnalysisTable);
        root.getChildren().add(partsOfSpeechAnalysisResultsHeader);
        root.getChildren().add(filteringOptionsBox);
        root.getChildren().add(percentageOptionsBox);
        root.getChildren().add(sortAscButton);
        root.getChildren().add(sortDescButton);
        root.getChildren().add(removeDecimal);
        root.getChildren().add(addDecimal);
        root.getChildren().add(displaySentimentTable);
        root.getChildren().add(displaySentimentPieChart);
        root.getChildren().add(popOutBarChart);
        root.getChildren().add(displayCharacterPieChart);
        root.getChildren().add(characterPieChartHeader);
        root.getChildren().add(displayCharacterBarChart);
        
        Scene scene = new Scene(root);
        
        //The below code toggles on/off the CSS whenever the darkModeActivate button is clicked.
        darkModeActivate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                if(scene.getStylesheets().isEmpty()) 
                {
                	darkModeActivated = true;
                	scene.getStylesheets().add(darkModeFilePathCSS);
                	darkModeActivate.setText("White Mode");
                	inputBackgroundBox.setStyle("-fx-fill: #283149");
                    analysisBackgroundBox.setStyle("-fx-fill: #283149");
                    
                    //Replaces the black icons on the buttons with the white icons.
                    addIconsToButtons(whiteIconsFilePaths);
                }
                else {
                    darkModeActivated = false;
                    scene.getStylesheets().remove(darkModeFilePathCSS);
                	darkModeActivate.setText("Dark Mode");
                    inputBackgroundBox.setStyle("-fx-fill: #eaeaea");
                    analysisBackgroundBox.setStyle("-fx-fill: #eaeaea");
                    
                    //Replaces the white icons on the buttons with the black icons.
                    addIconsToButtons(blackIconsFilePaths);
                }
            }
        });
        
        primaryStage.setTitle("Text Analyser");
        primaryStage.setScene(scene);
        
        //The below code configures the primary stage so that the width and height can not be made smaller or larger.
        primaryStage.setMaxHeight(905+48);
        primaryStage.setMaxWidth(743+18);
        primaryStage.setMinHeight(905+48);
        primaryStage.setMinWidth(743+18);
        
        primaryStage.show();
    }
    
    private void initialize() 
    {
    	//Hides the relevant JavaFX components for when the application starts. This method is called by the start method.
    	importFileSuccessMessage.setVisible(false);
        analysisBackgroundBox.setVisible(false);
        displayTextHeader.setVisible(false);
        displayText.setVisible(false);
        displaySortDecimalFilterPercentageRibbon(false);
        displaySentimentPieChart.setVisible(false);
        displaySentimentTable.setVisible(false);
        sentimentAnalysisPieChart.setVisible(false);
        sentimentAnalysisTable.setVisible(false);
        sentimentResultsHeader.setVisible(false);
        characterAnalysisBarChart.setVisible(false);
        characterBarChartHeader.setVisible(false);
        popOutBarChart.setVisible(false);
        characterAnalysisPieChart.setVisible(false);
        characterPieChartHeader.setVisible(false);
        displayCharacterBarChart.setVisible(false);
        displayCharacterPieChart.setVisible(false);
        wordAnalysisTable.setVisible(false);
        wordAnalysisResultsHeader.setVisible(false);
        partsOfSpeechAnalysisTable.setVisible(false);
        partsOfSpeechAnalysisResultsHeader.setVisible(false);
    }
   
    private void getFilePath(ActionEvent event) throws FileNotFoundException
    {
    	textForAnalysis.clear();
    	FileChooser fileChoose = new FileChooser(); 
    	
    	//Ensures that only txt files can be selected
    	FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
    	fileChoose.getExtensionFilters().add(filter);
    	
    	File file = fileChoose.showOpenDialog(null);
    	HandleTextFiles handleTxtFile = new HandleTextFiles(file.getAbsolutePath());
		textToBeAnalysed = handleTxtFile.fileToString();
		paragraphCount = handleTxtFile.getParagraphCount();
    }
    
    private void getUsersText()
    {
    	String localTextForAnalysis = textForAnalysis.getText();
    	if(!localTextForAnalysis.isEmpty())
    	{
			importFileSuccessMessage.setVisible(false);
    		textToBeAnalysed = textForAnalysis.getText();
    		
    		//Sets paragraph count to 1 as the user enters text via a TextField so therefore won't be able to enter more than one paragraph.
    		paragraphCount = 1;
    	}
    }
    
    private void displayUsersText()
    {
    	getUsersText();
    	if(!textToBeAnalysed.isEmpty())
    	{
    		displayTextHeader.setVisible(true);
        	displayText.setVisible(true);
        	displayText.setText(textToBeAnalysed);
    	}
    }
    
    private boolean emptyTextError()
    {
    	/*
    	 * Code displays error message to the user if they haven't entered any text. 
    	 * Method is called by the display analysis methods to prevent analysis being undertaken without any text present.
    	 */
    	if(textToBeAnalysed.isEmpty())
    	{
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("No text has been entered");
			errorAlert.setContentText("Please either type your text into the text bar or select a text file.");
			errorAlert.showAndWait();
			return true;
    	}
    	else {
    		return false;
    	}
    }
    
    private void displaySortDecimalFilterPercentageRibbon(boolean enabler)
    {
    	removeDecimal.setVisible(enabler);
        addDecimal.setVisible(enabler);
        sortDescButton.setVisible(enabler);
        sortAscButton.setVisible(enabler);
        filteringOptionsBox.setVisible(enabler);
        percentageOptionsBox.setVisible(enabler);
    }
    
	@SuppressWarnings("unchecked")
	private void displayCharacterAnalysis(ActionEvent event)
    {
		displayUsersText();
    	if(emptyTextError()) {}
    	else {
    		if(event.getSource().toString().contains("Character Analysis"))
    		{
    			sortingOption = 1;
    			characterPieChartHeader.setVisible(false);
    			characterAnalysisPieChart.setVisible(false);
    			displayCharacterBarChart.setVisible(false);
    			displayCharacterPieChart.setVisible(true);
    			sortAscDisable = false;
    			sortDescDisable = false;
    			filteringOptionsBoxDisable = false;
    		}
    		if(charAnalysisPieChartVisible)
    		{
            	characterAnalysisPieChart.setVisible(true);
            	displayCharacterBarChart.setVisible(true);
    		}
    		else {
    	    	characterBarChartHeader.setVisible(true);
            	characterAnalysisPieChart.setVisible(false);
                characterAnalysisBarChart.setVisible(true);
                characterBarChartHeader.setVisible(true);
                popOutBarChart.setVisible(true);
                displayCharacterPieChart.setVisible(true);
    		}
        	sentimentAnalysisPieChart.setVisible(false);
        	sentimentAnalysisTable.setVisible(false);
        	sentimentResultsHeader.setVisible(false);
        	displaySentimentTable.setVisible(false);
            displaySentimentPieChart.setVisible(false);
            wordAnalysisTable.setVisible(false);
            wordAnalysisResultsHeader.setVisible(false);
            partsOfSpeechAnalysisTable.setVisible(false);
            partsOfSpeechAnalysisResultsHeader.setVisible(false);
            removeDecimal.setDisable(false);
            addDecimal.setDisable(false);
            analysisBackgroundBox.setVisible(true);
            displaySortDecimalFilterPercentageRibbon(true);
            percentageOptionsBox.setDisable(percentageOptionsBoxDisable);
            filteringOptionsBox.setDisable(filteringOptionsBoxDisable);
            sortAscButton.setDisable(sortAscDisable);
            sortDescButton.setDisable(sortDescDisable);
            
            //The below if statement ensures that the CharacterAnalysis class isn't run again if only a decimal place is being changed.
            if(decimalPlaceChange == false)
            {
            	charAnalysis = new CharacterAnalysis(charactersInScope, percentageCalculation, sortingOption);
        		charAnalysis.countCharFrequencies();
        		charAnalysis.relativeCharFrequencies();
    			charAnalysis.summary();
            }
            
    		characterAnalysisBarChartData = charAnalysis.barChartData();
    		characterAnalysisBarChart.setLegendVisible(false);
    		characterAnalysisBarChart.setVerticalGridLinesVisible(false);
    		characterAnalysisBarChart.getData().clear();
    		
    		/*
    		 * This method call is important to re-set the layout of the bar chart to ensure that it doesn't remain 
    		 * the same if the user selects the bar chart in either ASC or DESC order.
    		 */
    		characterAnalysisBarChart.layout();
    		
    		characterAnalysisBarChart.getData().addAll(characterAnalysisBarChartData);
    		characterAnalysisBarChart.setAnimated(false);
        	ObservableList<PieChart.Data> pieChartData = charAnalysis.pieChartData();
        	characterAnalysisPieChart.layout();
        	characterAnalysisPieChart.setData(pieChartData);
        	characterAnalysisPieChart.setLegendVisible(false);
        	charAnalysisPieChartVisible = false;
    	}
    }

    private void displayWordAnalysis(ActionEvent event)
    {
    	displayUsersText();
    	if(emptyTextError()){}
    	else {
			characterPieChartHeader.setVisible(false);
			characterAnalysisPieChart.setVisible(false);
			displayCharacterBarChart.setVisible(false);
			displayCharacterPieChart.setVisible(false);
        	characterAnalysisPieChart.setVisible(false);
        	displayCharacterBarChart.setVisible(false);
	    	characterBarChartHeader.setVisible(false);
        	characterAnalysisPieChart.setVisible(false);
            characterAnalysisBarChart.setVisible(false);
            characterBarChartHeader.setVisible(false);
            popOutBarChart.setVisible(false);
            displayCharacterPieChart.setVisible(false);
            sentimentAnalysisPieChart.setVisible(false);
            sentimentAnalysisTable.setVisible(false);
            sentimentResultsHeader.setVisible(false);
            displaySentimentTable.setVisible(false);
            displaySentimentPieChart.setVisible(false);
            partsOfSpeechAnalysisTable.setVisible(false);
            partsOfSpeechAnalysisResultsHeader.setVisible(false);
            analysisBackgroundBox.setVisible(true);
            wordAnalysisResultsHeader.setVisible(true);
            wordAnalysisTable.setVisible(true);
            displaySortDecimalFilterPercentageRibbon(true);
            removeDecimal.setDisable(true);
            addDecimal.setDisable(true);
            sortDescButton.setDisable(true);
            sortAscButton.setDisable(true);
            filteringOptionsBox.setDisable(true);
            percentageOptionsBox.setDisable(true);
    		WordAnalysis wordAnalysis = new WordAnalysis("tokenize, ssplit", paragraphCount);
			wordAnalysis.wordCount();
			wordAnalysis.averageWordLength();
			wordAnalysis.mostFrequentWord();
			wordAnalysis.sentencesListAndCount();
    		metric.setCellValueFactory(new PropertyValueFactory<WordAnalysis, String>("metric"));
            value.setCellValueFactory(new PropertyValueFactory<WordAnalysis, String>("value"));
            wordAnalysisTable.setItems(wordAnalysis.wordAnalysisTableData());
    	}
    }
    
    private void displaySentimentAnalysis(ActionEvent event)
    {
    	displayUsersText();
    	if(emptyTextError()) {}
    	else {
    		if(event.getSource().toString().contains("Sentiment Analysis"))
    		{
    			addDecimal.setDisable(false);
    			removeDecimal.setDisable(false);
    		}
            characterAnalysisBarChart.setVisible(false);
            characterBarChartHeader.setVisible(false);
            characterPieChartHeader.setVisible(false);
            popOutBarChart.setVisible(false);
            displayCharacterBarChart.setVisible(false);
            displayCharacterPieChart.setVisible(false);
            characterAnalysisPieChart.setVisible(false);
            sentimentAnalysisTable.setVisible(false);
            wordAnalysisTable.setVisible(false);
            wordAnalysisResultsHeader.setVisible(false);
            partsOfSpeechAnalysisTable.setVisible(false);
            partsOfSpeechAnalysisResultsHeader.setVisible(false);
            analysisBackgroundBox.setVisible(true);
            sentimentAnalysisPieChart.setVisible(true);
            displaySortDecimalFilterPercentageRibbon(true);
            sentimentResultsHeader.setVisible(true);
            displaySentimentTable.setVisible(true);
            sortDescButton.setDisable(true);
            sortAscButton.setDisable(true);
            filteringOptionsBox.setDisable(true);
            percentageOptionsBox.setDisable(true);
        	if(decimalPlaceChange == false)
        	{
            	langAnalysis = new LanguageAnalysis("tokenize, ssplit, pos, parse, sentiment", 1, 0);
            	langAnalysis.sentimentAnalysis();
        	}
        	ObservableList<PieChart.Data> pieChartData = langAnalysis.pieChartData();
        	sentimentAnalysisPieChart.setData(pieChartData);
        	
        	/*
        	 * for loop sets the colour of the sentiment labels on the pie chart i.e. very positive = dark green, positive = green, 
        	 * neutral = amber, negative = red, very negative = dark red.
        	 */
        	for(PieChart.Data data : pieChartData)
        	{
        		if(data.getName().contains("Very negative"))
            		data.getNode().setStyle("-fx-pie-color: #A10000");
        		if(data.getName().contains("Negative"))
            		data.getNode().setStyle("-fx-pie-color: #D30000");
        		if(data.getName().contains("Neutral"))
            		data.getNode().setStyle("-fx-pie-color: #FF7400");
        		if(data.getName().contains("Positive"))
            		data.getNode().setStyle("-fx-pie-color: #30E000");
        		if(data.getName().contains("Very positive"))
            		data.getNode().setStyle("-fx-pie-color: #23A400");
        	}
        	sentimentAnalysisPieChart.setLegendVisible(false);
            sentiment.setCellValueFactory(new PropertyValueFactory<LanguageAnalysis, String>("sentiments"));
            sentence.setCellValueFactory(new PropertyValueFactory<LanguageAnalysis, String>("sentences"));
            sentimentAnalysisTable.setItems(langAnalysis.sentimentTableData());
    	}
    }
    
    private void displayPartsOfSpeechAnalysis(ActionEvent event)
    {
    	displayUsersText();
    	if(emptyTextError()) {}
    	else {
            characterAnalysisBarChart.setVisible(false);
            characterBarChartHeader.setVisible(false);
            characterPieChartHeader.setVisible(false);
            popOutBarChart.setVisible(false);
            displayCharacterBarChart.setVisible(false);
            displayCharacterPieChart.setVisible(false);
            characterAnalysisPieChart.setVisible(false);
            sentimentAnalysisTable.setVisible(false);
            wordAnalysisTable.setVisible(false);
            wordAnalysisResultsHeader.setVisible(false);
            sentimentAnalysisPieChart.setVisible(false);
            sentimentResultsHeader.setVisible(false);
            displaySentimentTable.setVisible(false);
            displaySentimentPieChart.setVisible(false);
			addDecimal.setDisable(true);
			removeDecimal.setDisable(true);
            sortDescButton.setDisable(true);
            sortAscButton.setDisable(true);
            filteringOptionsBox.setDisable(true);
            percentageOptionsBox.setDisable(true);
            analysisBackgroundBox.setVisible(true);
            partsOfSpeechAnalysisTable.setVisible(true);
            partsOfSpeechAnalysisResultsHeader.setVisible(true);
            displaySortDecimalFilterPercentageRibbon(true);
        	if(decimalPlaceChange == false)
        	{
            	langAnalysis = new LanguageAnalysis("tokenize, ssplit, pos", 2, sortingOption);
            	try {
                	langAnalysis.partsOfSpeechAnalsis();
            	}
            	catch(FileNotFoundException e) {
            		Alert errorAlert = new Alert(AlertType.ERROR);
        			errorAlert.setHeaderText("An internal application error has occured");
        			errorAlert.setContentText("Please report this issue.");
        			errorAlert.showAndWait();
            	}
                word.setCellValueFactory(new PropertyValueFactory<LanguageAnalysis, String>("word"));
                POStag.setCellValueFactory(new PropertyValueFactory<LanguageAnalysis, String>("POStag"));
                partsOfSpeechAnalysisTable.setItems(langAnalysis.partsOfSpeechTableData());
        	}
    	}
    }
    
    private void displaySentimentTable(ActionEvent event)
    {
        removeDecimal.setDisable(true);
        addDecimal.setDisable(true);
    	displaySentimentTable.setVisible(false);
    	displaySentimentPieChart.setVisible(true);
    	sentimentAnalysisTable.setVisible(true);
    	sentimentAnalysisPieChart.setVisible(false);
    }
    
    private void displaySentimentPieChart(ActionEvent event)
    {
        removeDecimal.setDisable(false);
        addDecimal.setDisable(false);
    	displaySentimentPieChart.setVisible(false);
    	displaySentimentTable.setVisible(true);
    	sentimentAnalysisTable.setVisible(false);
    	sentimentAnalysisPieChart.setVisible(true);
    }
    
    private void displayCharacterBarChart(ActionEvent event)
    {
    	characterPieChartHeader.setVisible(false);
    	characterBarChartHeader.setVisible(true);
    	displayCharacterBarChart.setVisible(false);
    	displayCharacterPieChart.setVisible(true);
    	characterAnalysisPieChart.setVisible(false);
    	characterAnalysisBarChart.setVisible(true);
    	popOutBarChart.setVisible(true);
    	sortAscButton.setDisable(false);
    	sortDescButton.setDisable(false);
    	filteringOptionsBox.setDisable(false);
    	
    	//Disables percentage calculation drop down if 'All characters' are selected to be displayed.
    	try {
        	if(filteringOptionsBox.getValue().equals("All characters"))
        	{
            	percentageOptionsBox.setDisable(true);
        	}
        	else {
            	percentageOptionsBox.setDisable(false);
        	}
    	}
    	catch(NullPointerException e) {
        	percentageOptionsBox.setDisable(true);
    	}
    }
    
    private void displayCharacterPieChart(ActionEvent event)
    {
    	characterBarChartHeader.setVisible(false);
    	characterPieChartHeader.setVisible(true);
    	displayCharacterBarChart.setVisible(true);
    	displayCharacterPieChart.setVisible(false);
    	characterAnalysisPieChart.setVisible(true);
    	characterAnalysisBarChart.setVisible(false);
    	popOutBarChart.setVisible(false);
    	sortAscButton.setDisable(true);
    	sortDescButton.setDisable(true);
    	filteringOptionsBox.setDisable(true);
    	percentageOptionsBox.setDisable(true);
    }
   
    private void decreaseDecimalPlaces(ActionEvent event)
    {
    	decimalPlaceChange = true;
    	if(numberOfDecimalPlaces > 0)
    	{
        	numberOfDecimalPlaces--;
        	if(sentimentAnalysisPieChart.isVisible())
        	{
            	sentimentAnalysisPieChart.setAnimated(false);
            	displaySentimentAnalysis(event);
        	}
        	if(characterAnalysisBarChart.isVisible() || characterAnalysisPieChart.isVisible())
        	{
        		if(characterAnalysisPieChart.isVisible())
        		{
            		characterAnalysisPieChart.setAnimated(false);
        			charAnalysisPieChartVisible = true;
        			filteringOptionsBoxDisable = true;
        			sortAscDisable = true;
        			sortDescDisable = true;
        		}
        		else {
        			charAnalysisPieChartVisible = false;
        			filteringOptionsBoxDisable = false;
        			sortAscDisable = false;
        			sortDescDisable = false;
        		}
            	displayCharacterAnalysis(event);
        	}
    	}
    	decimalPlaceChange = false;
    }
    
    private void increaseDecimalPlaces(ActionEvent event)
    {
    	decimalPlaceChange = true;
    	
    	//Limits the number of places that can be shown to 10
    	if(numberOfDecimalPlaces >= 0 && numberOfDecimalPlaces < 10)
    	{
        	numberOfDecimalPlaces++;
        	if(sentimentAnalysisPieChart.isVisible())
        	{
            	sentimentAnalysisPieChart.setAnimated(false);
            	displaySentimentAnalysis(event);
        	}
        	if(characterAnalysisBarChart.isVisible() || characterAnalysisPieChart.isVisible())
        	{
        		if(characterAnalysisPieChart.isVisible())
        		{
            		characterAnalysisPieChart.setAnimated(false);
        			charAnalysisPieChartVisible = true;
        			filteringOptionsBoxDisable = true;
        			sortAscDisable = true;
        			sortDescDisable = true;
        		}
        		else {
        			charAnalysisPieChartVisible = false;
        			filteringOptionsBoxDisable = false;
        			sortAscDisable = false;
        			sortDescDisable = false;
        		}
            	displayCharacterAnalysis(event);
        	}
    	}
    	decimalPlaceChange = false;
    }
   
	private void sortDesc(ActionEvent event)
    {
    	sortingOption = 2;
    	try {
            if(filteringOptionsBox.getValue().equals("All characters"))
            {
               	percentageOptionsBoxDisable = true;
            }
            else {
               	percentageOptionsBoxDisable = false;
            }
    	}
    	catch(NullPointerException e) {
           	percentageOptionsBoxDisable = true;
    	}
    	finally {
        	filteringOptionsBoxDisable = false;
        	sortAscDisable = false;
        	sortDescDisable = false;
        	displayCharacterAnalysis(event);
    	}
    }
	
	private void sortAsc(ActionEvent event)
    {
    	sortingOption = 3;
    	try {
            if(filteringOptionsBox.getValue().equals("All characters"))
            {
               	percentageOptionsBoxDisable = true;
            }
            else {
               	percentageOptionsBoxDisable = false;
            }
    	}
    	catch(NullPointerException e) {
           	percentageOptionsBoxDisable = true;
    	}
    	finally {
        	filteringOptionsBoxDisable = false;
        	sortAscDisable = false;
        	sortDescDisable = false;
        	displayCharacterAnalysis(event);
    	}
    }
	
	private void filterCharacters(ActionEvent event)
	{
		filteringOptionsBoxDisable = false;
		sortAscDisable = false;
		sortDescDisable = false;
		switch(filteringOptionsBox.getValue())
		{
			case "All characters": charactersInScope = fullListOfReferenceCharacters;
			characterBarChartHeader.setText("Number of Individual Characters");
			displayCharacterPieChart.setDisable(false);
			percentageOptionsBoxDisable = true;
			break;
			case "Only letters": charactersInScope = letters;
			characterBarChartHeader.setText("Number of Individual Letters");
			displayCharacterPieChart.setDisable(true);
			percentageOptionsBoxDisable = false;
			break;
			case "Only numbers": charactersInScope = numbers;
			characterBarChartHeader.setText("Number of Individual Numbers");
			displayCharacterPieChart.setDisable(true);
			percentageOptionsBoxDisable = false;
			break;
			case "Only special characters": charactersInScope = specialCharacters;
			characterBarChartHeader.setText("Number of Individual Special Characters");
			displayCharacterPieChart.setDisable(true);
			percentageOptionsBoxDisable = false;
			break;
		}
		displayCharacterAnalysis(event);
	}
	
	private void percentageCalculation(ActionEvent event)
	{
		switch(percentageOptionsBox.getValue())
		{
			case "Only the filtered characters": percentageCalculation = 1;
			break;
			case "All types of characters": percentageCalculation = 2;
			break;
		}
		displayCharacterAnalysis(event);
	}

	@SuppressWarnings("unchecked")
	private void popOutBarChart(ActionEvent event)
	{
		CategoryAxis x = new CategoryAxis();
		NumberAxis y = new NumberAxis();
		BarChart<String, Number> popOutBarChart = new BarChart<String, Number>(x, y);
		characterAnalysisBarChartData = charAnalysis.barChartData();
		popOutBarChart.getData().addAll(characterAnalysisBarChartData);
		popOutBarChart.setPrefHeight(1080);
		popOutBarChart.setPrefWidth(1920);
		popOutBarChart.setLegendVisible(false);
		popOutBarChart.setVerticalGridLinesVisible(false);
		Stage stage = new Stage();
		VBox vbox = new VBox(popOutBarChart);
		Scene scene = new Scene(vbox, 0, 0);
		if(darkModeActivated)
		{
			scene.getStylesheets().add(darkModeFilePathCSS);
		}
		stage.setTitle("Bar Chart");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}
	
}



