package com.tpgsi.jderive;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

/**
 * 
 * @author Shridhar
 *
 * Pig UDF to categorize weight group for a given input weight.
 * 
 */
public class WeightCategory extends EvalFunc<Integer>
{

	public Integer exec(Tuple input) throws IOException
	{
		int weightCategoryId = 1;
		if (input == null || input.size() == 0)
			return null;
		try
		{
			Integer weight = (Integer) input.get(0);
			if (weight != null)
			{
				if (weight <= 50)
				{
					weightCategoryId = 2;
				}

				if (weight > 51 && weight <= 100)
				{
					weightCategoryId = 3;
				}

				if (weight > 101 && weight <= 150)
				{
					weightCategoryId = 4;
				}

				if (weight > 151 && weight <= 200)
				{
					weightCategoryId = 5;
				}

				if (weight > 201 && weight <= 250)
				{
					weightCategoryId = 6;
				}

			} else
			{
				weightCategoryId = 1;
			}

			return weightCategoryId;

		} catch (Exception e)
		{
			throw new IOException("Caught exception processing input row ", e);
		}
	}
}
