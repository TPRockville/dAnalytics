package com.tpgsi.jderive.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.tpgsi.jderive.jaxb.Ichicsr;
import com.tpgsi.jderive.jaxb.SafetyReport;
import com.tpgsi.jderive.util.ParserUtility;

/**
 * 
 * @author Akshay
 *
 */
public class XMLParser
{
	/**
	 * Entry point of the program
	 * args[0] - Input XML file name with location 
	 * args[1] - Output CSV file name with location
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		if(args.length<2)
		{
			System.out.println("Please input 2 arguments.");
			System.out.println("Argument 1 : Input XML file name with location");
			System.out.println("Argument 2 : Output CSV file name with location");
			System.exit(-1);
		}
		
		JAXBContext jaxbContext;
		Ichicsr ichicsr;
		final BufferedWriter bout = new BufferedWriter(new FileWriter(args[1]));
		try
		{
			final File fileObject = new File(args[0]);
			jaxbContext = JAXBContext.newInstance(Ichicsr.class);
			final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ichicsr = (Ichicsr) jaxbUnmarshaller.unmarshal(fileObject);
			
			
			final List<SafetyReport> safetyList = ichicsr.getLisReports();
			final ParserUtility utility = new ParserUtility();
			utility.generateReport(safetyList, bout);
				
			bout.flush();
			bout.close();
			System.out.println("Done....");
		}
		catch (final JAXBException e)
		{
			e.printStackTrace();
		}
	}

}
