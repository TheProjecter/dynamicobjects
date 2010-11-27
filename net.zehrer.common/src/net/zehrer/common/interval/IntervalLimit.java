/**
 * Copyright (c) 2005 Domain Language, Inc. (http://domainlanguage.com) This
 * free software is distributed under the "MIT" licence. See file licence.txt.
 * For more information, see http://timeandmoney.sourceforge.net.
 */
package net.zehrer.common.interval;

import java.io.*;

class IntervalLimit implements Comparable<IntervalLimit>, Serializable {
    private boolean closed;
    @SuppressWarnings("unchecked")
    private Comparable value;
    private boolean lower;

    static IntervalLimit upper(boolean closed, Comparable<?> value) {
        
 
    	return new IntervalLimit(closed, false, value);
        
    }

    static IntervalLimit lower(boolean closed, Comparable<?> value) {
        return new IntervalLimit(closed, true, value);
    }

    IntervalLimit(boolean closed, boolean lower, Comparable<?> value) {
        super();
        this.closed = closed;
        this.lower = lower;
        this.value = value;
    }

    boolean isLower() {
        return lower;
    }

    boolean isUpper() {
        return !lower;
    }

    boolean isClosed() {
        return closed;
    }

    boolean isOpen() {
        return !closed;
    }

    Comparable<?> getValue() {
        return value;
    }

    public int compareTo(IntervalLimit other) {
        @SuppressWarnings("unchecked")
        Comparable otherValue = other.value;
        if (otherValue == value)
            return 0;
        if (value == null) {
            return lower ? -1 : 1;
        }
        if (otherValue == null) {
            return other.lower ? 1 : -1;
        }
        @SuppressWarnings("unchecked")
        int result = value.compareTo(otherValue);
        return result;
    }


}
