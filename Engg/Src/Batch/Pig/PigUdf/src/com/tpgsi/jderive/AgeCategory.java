package com.tpgsi.jderive;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

/**
 * 
 * @author Shridhar 
 * 
 * Pig UDF to categorize age group for a given input age.
 */
public class AgeCategory extends EvalFunc<Integer>
{
	
	private static final int UNKNOWN = 1;
	private static final int BELOW_20 = 2;
	private static final int TWENTY_TO_FOURTY = 3;
	private static final int FOURTY_TO_SIXTY = 4;
	private static final int SIXTY_TO_EIGHTY = 5;
	
	public Integer exec(Tuple input) throws IOException
	{
		int ageCategoryId = UNKNOWN;
		if (input == null || input.size() == 0)
			return null;
		try
		{
			Integer age = (Integer) input.get(0);
			if (age != null)
			{
				if (age <= 20)
				{
					ageCategoryId = BELOW_20;
				}

				if (age > 21 && age <= 40)
				{
					ageCategoryId = TWENTY_TO_FOURTY;
				}

				if (age > 41 && age <= 60)
				{
					ageCategoryId = FOURTY_TO_SIXTY;
				}

				if (age > 61 && age <= 80)
				{
					ageCategoryId = SIXTY_TO_EIGHTY;
				}

			} else
			{
				ageCategoryId = UNKNOWN;
			}

			return ageCategoryId;

		} catch (Exception e)
		{
			throw new IOException("Caught exception processing input row ", e);
		}
	}

}
