package com.stackroute.datamunger.query;

//this class contains the data type definitions
public class DataTypeDefinitions {

	/*
	 * this class should contain a member variable which is a String array, to hold
	 * the data type for all columns for all data types and should override toString() method as well.
	 */	
	private String[] columns;
	public void setColumn(String[] columns) {
		this.columns=columns;
	}
	public String[] getDataTypes() {
		return columns;
	}

	
}
