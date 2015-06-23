package com.tpgsi.jderive;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

/**
 * 
 * @author Shridhar
 *
 * Pig UDF to format date yyymmdd to yyyy-mm-dd
 * 
 */
public class FormatDate extends EvalFunc<String>
{

	public String exec(Tuple input) throws IOException
	{
		if (input == null || input.size() == 0)
			return null;
		try
		{
			String date = (String) input.get(0);
			
			if (date != null)
			{
				String formatedDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
						+ date.substring(6, 8);
				return formatedDate;
			} else
			{
				return null;
			}

		} catch (Exception e)
		{
			throw new IOException("Caught exception processing input row "+input.get(0), e);
		}
	}

}