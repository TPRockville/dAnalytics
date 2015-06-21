package com.tpgsi.jderive.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Akshay
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ReportDuplicate
{
	@XmlElement(name="duplicatesource")
	private String duplicateSource;
	@XmlElement(name="duplicatenumb")
	private String duplicateNumb;

	public String getDuplicateSource()
	{
		return duplicateSource;
	}

	public void setDuplicateSource(String duplicateSource)
	{
		this.duplicateSource = duplicateSource;
	}

	public String getDuplicateNumb()
	{
		return duplicateNumb;
	}

	public void setDuplicateNumb(String duplicateNumb)
	{
		this.duplicateNumb = duplicateNumb;
	}

	@Override
	public String toString()
	{
		return "ReportDuplicate [duplicateSource=" + duplicateSource + ", duplicateNumb=" + duplicateNumb + "]";
	}
	
}
