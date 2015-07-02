package org.jderive.dto;

import java.util.ArrayList;
import java.util.List;

import org.jderive.api.TopDrugs;

public class TopDrugsDTO {

	public static List<TopDrugs> toTopDrugs(List<Object[]> objects)
	{
		List<TopDrugs> topDrugs = new ArrayList<TopDrugs>();
		
		for (Object[] object : objects) {
			TopDrugs topDrug = new TopDrugs();
			topDrug.setDrugName(object[1] == null ?null: object[1].toString());
			topDrug.setCount(object[0] == null ?null: object[0].toString());
			topDrugs.add(topDrug);
		}
		
		return topDrugs;
	}
}
