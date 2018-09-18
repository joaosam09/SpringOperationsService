package com.criticalsoftware.springboot.operation;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests "Operation" class.
 * 
 * @author João Santos
 * @version 1.0
 */
public class OperationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger("TestsFileLogger"); //Logger declared in resources/logback.xml
	private double minRangeRandomValues = 1;
	private double maxRangeRandomValues = 100;
	
	/**
	 * Tests result of sum operation.
	 */
	@Test
	public void testSumOperation() {		
		Random rnd = new Random();		
		double randomValue1 = minRangeRandomValues + (maxRangeRandomValues - minRangeRandomValues) * rnd.nextDouble();
		double randomValue2 = minRangeRandomValues + (maxRangeRandomValues - minRangeRandomValues) * rnd.nextDouble();		
				
		Calculator calculator = new Calculator();
		double sumResult = calculator.calculate(randomValue1, randomValue2, "sum");
		
		LOGGER.info(String.format("SUM between %f and %f equals %f", randomValue1, randomValue2, sumResult));
		assertEquals("Failed SUM operation", randomValue1 + randomValue2, sumResult, 0);						
	}
	
	/**
	 * Tests result of subtraction operation.
	 */
	@Test
	public void testSubtractOperation() {					
		Random rnd = new Random();		
		double randomValue1 = minRangeRandomValues + (maxRangeRandomValues - minRangeRandomValues) * rnd.nextDouble();
		double randomValue2 = minRangeRandomValues + (maxRangeRandomValues - minRangeRandomValues) * rnd.nextDouble();		
			
		Calculator calculator = new Calculator();
		double subResult = calculator.calculate(randomValue1, randomValue2, "subtract");
		
		LOGGER.info(String.format("SUBTRACTION between %f and %f equals %f", randomValue1, randomValue2, subResult));
		assertEquals("Failed SUBTRACT operation", randomValue1 - randomValue2, subResult, 0);		
	}
	
	/**
	 * Tests result of multiplication operation.
	 */
	@Test
	public void testMultiplicationOperation() {					
		Random rnd = new Random();		
		double randomValue1 = minRangeRandomValues + (maxRangeRandomValues - minRangeRandomValues) * rnd.nextDouble();
		double randomValue2 = minRangeRandomValues + (maxRangeRandomValues - minRangeRandomValues) * rnd.nextDouble();		
				
		Calculator calculator = new Calculator();
		double multiplyResult = calculator.calculate(randomValue1, randomValue2, "multiplication");	
		
		LOGGER.info(String.format("MULTIPLICATION between %f and %f equals %f", randomValue1, randomValue2, multiplyResult));
		assertEquals("Failed MULTIPLICATION operation", randomValue1 * randomValue2, multiplyResult, 0);	
	}
	
	/**
	 * Tests result of division operation.
	 */
	@Test
	public void testDivisionOperation() {						
		Random rnd = new Random();		
		double randomValue1 = minRangeRandomValues + (maxRangeRandomValues - minRangeRandomValues) * rnd.nextDouble();
		double randomValue2 = minRangeRandomValues + (maxRangeRandomValues - minRangeRandomValues) * rnd.nextDouble();				
		
		Calculator calculator = new Calculator();
		double divideResult = calculator.calculate(randomValue1, randomValue2, "division");
		
		LOGGER.info(String.format("DIVISION between %f and %f equals %f", randomValue1, randomValue2, divideResult));
		assertEquals("Failed DIVIDE operation", randomValue1 / randomValue2, divideResult, 0);						
	}
	
	/**
	 * Tests result of average operation.
	 */
	@Test
	public void testAvgOperation() {						
		Random rnd = new Random();		
		double randomValue1 = minRangeRandomValues + (maxRangeRandomValues - minRangeRandomValues) * rnd.nextDouble();
		double randomValue2 = minRangeRandomValues + (maxRangeRandomValues - minRangeRandomValues) * rnd.nextDouble();				
		
		Calculator calculator = new Calculator();
		double avgResult = calculator.calculate(randomValue1, randomValue2, "avg");
		
		LOGGER.info(String.format("AVERAGE between %f and %f equals %f", randomValue1, randomValue2, avgResult));
		assertEquals("Failed AVERAGE operation", (randomValue1 + randomValue2) / 2, avgResult, 0);						
	}	
}
