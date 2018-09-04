package com.criticalsoftware.springboot.operation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({OperationTest.class, CalculateHttpRequestWithRunningServerTest.class})
public class OperationTestSuite {
}
