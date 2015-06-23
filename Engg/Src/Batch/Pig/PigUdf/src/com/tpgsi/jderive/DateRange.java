package com.tpgsi.jderive;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

/**
 * 
 * @author Shridhar
 *
 * Pig UDF to format date yyymmdd to yyyy-mm-dd and get first date of the Month
 * 
 */
public class DateRange extends EvalFunc<String>
{
	
	private static final String DATE_FORMAT = "MM/dd/yyyy";

	public String exec(Tuple input) throws IOException
	{
		if (input == null || input.size() == 0)
			return null;
		try
		{
			String inputDate = (String) input.get(0);

			if (inputDate != null && !inputDate.isEmpty())
			{

				String date = inputDate.substring(4, 6) + "/" + inputDate.substring(6, 8) + "/" + inputDate.substring(0, 4);
				SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
				Date convertedDate = dateFormat.parse(date);
				Calendar c = Calendar.getInstance();
				c.setTime(convertedDate);
				String startDate = inputDate.substring(0, 4) + "-" + inputDate.substring(4, 6) + "-" + c.getActualMinimum(Calendar.DAY_OF_MONTH);

				return startDate;
			} else
			{
				return null;
			}

		} catch (Exception e)
		{
			throw new IOException("Caught exception processing input row " + input.get(0), e);
		}
	}

}
