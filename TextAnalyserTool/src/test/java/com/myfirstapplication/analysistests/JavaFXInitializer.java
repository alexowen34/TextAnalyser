package com.myfirstapplication.analysistests;

import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;
import javafx.embed.swing.JFXPanel;

/*
 * As the some of the test classes instantiate other analysis classes that references the GUI class (the GUI class uses JavaFX) it requires 
 * the JavaFX enviroment to be initialized (which is what the below code does).
 */
public class JavaFXInitializer
{
    public static void initializeToolkit()
    {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });
    }
}