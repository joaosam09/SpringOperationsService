package com.criticalsoftware.springboot;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.criticalsoftware.springboot.operation.OperationTestSuite;

/**
 * Class used to execute all tests.
 * 
 * @author João Santos
 * @version 1.0
 */
public class CompleteTestExecutor {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(OperationTestSuite.class);
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.getMessage());
		}
		System.out.println(result.wasSuccessful());
	}
}
