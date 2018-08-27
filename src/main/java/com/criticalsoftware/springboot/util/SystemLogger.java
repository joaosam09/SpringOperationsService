package com.criticalsoftware.springboot.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Class used to log info into files.
 * 
 * @author Jo�o Santos
 * @version 1.0
 */
public class SystemLogger {
	
	private volatile static Logger logger = null;
		
	/**
	 * Initializes the logger and creates the log directory if it doesn't exist.	 
	 */
	private static void initialize() {		
		try {
			new File("./logs").mkdir(); //Creates the log directory if it does not exist							
		} catch (SecurityException e) {
			System.out.println("Could not create logs directory: " + e.getMessage());;
		}								
		
		logger = Logger.getLogger(Logger.class.getName());													
		logger.setLevel(Level.ALL);						
		addNewHandler();			
	}
	
	/**
	 * Initializes the logger if necessary or just adds a new file handler to it and then retrieves it.
	 * Prepared to work with multi-thread.
	 * @param name String containing the intended name for the logger subsystem.
	 * @return Logger Returns the logger instance.
	 */
	public static Logger getLogger(String name) {
        if(logger==null)
        {
           synchronized(Logger.class) 
           {
              if(logger == null) {
            	  SystemLogger.initialize();
              }
           }
        }else {					
        	addNewHandler();
        }
        
        Logger.getLogger(name);
        return logger;
    }
	
	/**
	 * Adds a new file handler to the current logger.	 
	 */
	private static void addNewHandler(){
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");			
			FileHandler fileHandler = new FileHandler("./logs/log_" + dateFormat.format(new Date()) + ".log", 10485760, 1, true);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			
		} catch (IOException | SecurityException e) {			
			e.printStackTrace();
		}			
	}
}
