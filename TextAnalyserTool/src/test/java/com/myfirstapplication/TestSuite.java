package com.myfirstapplication;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.myfirstapplication.analysistests.CharacterAnalysisTest;
import com.myfirstapplication.analysistests.PartsOfSpeechAnalysisTest;
import com.myfirstapplication.analysistests.SentimentAnalysisTest;
import com.myfirstapplication.analysistests.WordAnalysisTest;
import com.myfirstapplication.supporttests.CollectionToArrayTest;
import com.myfirstapplication.supporttests.HandleTextFilesTest;
import com.myfirstapplication.supporttests.SortValuesTest;

/*
 * This class is the main test class which calls each of the individual test classes for the application.
 * This saves time as if you want to run all the tests you can do it from this one class rather than each of
 * the individual classes.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	CharacterAnalysisTest.class,
	CollectionToArrayTest.class,
	HandleTextFilesTest.class,
	PartsOfSpeechAnalysisTest.class,
	SentimentAnalysisTest.class,
	SortValuesTest.class,
	WordAnalysisTest.class
})

public class TestSuite {

}
