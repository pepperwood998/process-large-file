package com.moron.project.plf;

public class Word{
    public String value;
    public Integer frequency;

    public Word(String value, Integer frequency) {
	this.value = value;
	this.frequency = frequency;
    }
    
    @Override
    public boolean equals(Object obj) {

	Word other = (Word) obj;
	
	return this.value.equals(other.value);
    }
}
