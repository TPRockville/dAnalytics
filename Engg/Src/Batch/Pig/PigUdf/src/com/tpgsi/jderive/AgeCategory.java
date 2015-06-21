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
	public Integer exec(Tuple input) throws IOException
	{
		int ageCategoryId = 1;
		if (input == null || input.size() == 0)
			return null;
		try
		{
			Integer age = (Integer) input.get(0);
			if (age != null)
			{
				if (age <= 20)
				{
					ageCategoryId = 2;
				}

				if (age > 21 && age <= 40)
				{
					ageCategoryId = 3;
				}

				if (age > 41 && age <= 60)
				{
					ageCategoryId = 4;
				}

				if (age > 61 && age <= 80)
				{
					ageCategoryId = 5;
				}

			} else
			{
				ageCategoryId = 1;
			}

			return ageCategoryId;

		} catch (Exception e)
		{
			throw new IOException("Caught exception processing input row ", e);
		}
	}

}
