package com.stackroute.datamunger.reader;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		BufferedReader br=new BufferedReader(new FileReader(fileName));
		String[] nextLine;
		String line;
		try {	
			line=br.readLine();
			System.out.println(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
	 */
	
	@Override
	public Header getHeader() throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("data/ipl.csv"));
		String readlines;
		readlines=br.readLine();
		System.out.println(readlines);
		String splitBy=",";
		String[] head=readlines.split(splitBy);
		Header header=new Header();
		header.setHeaders(head);
		return header;
		// read the first line

		// populate the header object with the String array containing the header names
//		return null;
	}

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */
	
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */
	
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		
		BufferedReader br=new BufferedReader(new FileReader("data/ipl.csv"));
		String Header="";
		Header=br.readLine();
		String Datatypes="";
		Datatypes=br.readLine();
		String splitBy=(",");
		String[] fields=Datatypes.split(splitBy,18);
		String[] dataTypes=new String[fields.length];
		int i=0;
		for(String s:fields) {
			if(s.matches("[0-9]+")) {
				dataTypes[i] = "java.lang.Integer";
				i++;
			}else {
				dataTypes[i] = "java.lang.String";
				i++;
			}
		}
		DataTypeDefinitions def=new DataTypeDefinitions(dataTypes);
		return def;
//		return null;
	}
}
