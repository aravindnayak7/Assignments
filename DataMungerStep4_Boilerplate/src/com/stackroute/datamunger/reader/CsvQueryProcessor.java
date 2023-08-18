package com.stackroute.datamunger.reader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;
public class CsvQueryProcessor extends QueryProcessingEngine {

	

	/*
	 * parameterized constructor to initialize filename. As you are trying to
	 * perform file reading, hence you need to be ready to handle the IO Exceptions.
	 */
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
	BufferedReader br=new BufferedReader(new FileReader(fileName));
	String line="";
	try {
		line=br.readLine();	
	}catch(IOException e) {
		e.printStackTrace();
	}
	}

	/*
	 * implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 */
	@Override
	public Header getHeader() throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("data/ipl.csv"));
		String readlines="";
		String splitBy=",";
		String[] head=br.readLine().split(splitBy);
		Header header=new Header();
		header.setHeaders(head);
		while((br.readLine())!=null) {
			readlines=br.readLine();
			System.out.println(readlines);
			break;
		}
//		System.out.println(header);
//		while((br.readLine())!=null) {
//			readlines=br.readLine();
//			System.out.println(readlines);
//		}
//		System.out.println(readlines);
//		Header header=new Header();
		return header;
	}
	

	/**
	 * This method will be used in the upcoming assignments
	 */
	@Override
	public void getDataRow() {
		
	}

	/*
	 * implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. In
	 * the previous assignment, we have tried to convert a specific field value to
	 * Integer or Double. However, in this assignment, we are going to use Regular
	 * Expression to find the appropriate data type of a field. Integers: should
	 * contain only digits without decimal point Double: should contain digits as
	 * well as decimal point Date: Dates can be written in many formats in the CSV
	 * file. However, in this assignment,we will test for the following date
	 * formats('dd/mm/yyyy',
	 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
	 */
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		// TODO Auto-generated method stub
		
		// checking for Integer
		
		// checking for floating point numbers
				
		// checking for date format dd/mm/yyyy
		
		// checking for date format mm/dd/yyyy
		
		// checking for date format dd-mon-yy
		
		// checking for date format dd-mon-yyyy
		
		// checking for date format dd-month-yy
		
		// checking for date format dd-month-yyyy
		
		// checking for date format yyyy-mm-dd
		BufferedReader br=new BufferedReader(new FileReader("data/ipl.csv"));
		String Header="";
		Header=br.readLine();
		String Datatypes="";
		Datatypes=br.readLine();
		String splitBy=",";
		
		DataTypeDefinitions def=new DataTypeDefinitions();
		String[] dataval=Datatypes.split(splitBy);
		ArrayList<String> li=new ArrayList<>();

		for(int i=0;i<dataval.length;i++)
		{
			if(dataval[i].matches("[0-9]+"))
			{
				li.add("java.lang.Integer");
			}
			else if(dataval[i].matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"))
			{
				li.add("java.util.Date");
			}
			else
				li.add("java.lang.String");
		}
		li.add("java.lang.Object");
		String[] types = new String[li.size()];
		types = li.toArray(types);
		def.setColumn(types);
 		return def;
	}
	
	

	
	

}
