package com.criticalsoftware.springboot.operation;

/**
 * Enumeration with supported arithmetic operations and it's calculation method.
 * 
 * @author João Santos
 * @version 1.0
 */
public enum Operation {
    SUM,
    SUBTRACT,
    MULTIPLICATION,
    DIVISION,
    AVG;

    double calculate(double x, double y) {
        switch (this) {
            case SUM:
                return x + y;
            case SUBTRACT:
                return x - y;
            case MULTIPLICATION:
                return x * y;
            case DIVISION:
                return x / y;
            case AVG:
            	return (x + y) / 2;
            default:
                throw new AssertionError("Unknown operation " + this);
        }
    }    
}

