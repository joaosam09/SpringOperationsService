package com.criticalsoftware.springboot.operation;

/**
 * Enumeration with supported operations and it's calculation method.
 * 
 * @author João Santos
 * @version 1.0
 */
public enum Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    AVERAGE;

    double calculate(double x, double y) {
        switch (this) {
            case ADD:
                return x + y;
            case SUBTRACT:
                return x - y;
            case MULTIPLY:
                return x * y;
            case DIVIDE:
                return x / y;
            case AVERAGE:
            	return (x + y) / 2;
            default:
                throw new AssertionError("Unknown operation " + this);
        }
    }    
}

