package com.criticalsoftware.springboot.operation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite that runs every test of the operation package.
 * @author João Santos
 * @version 1.0
 */
@RunWith(Suite.class)
@SuiteClasses({OperationTest.class, HttpRequestWithRunningServerTest.class, OperationControllerMockMvcTest.class})
public class OperationTestSuite {
}
