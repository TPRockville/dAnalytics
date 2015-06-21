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
public class PrimarySource
{
	@XmlElement(name="reportercountry")
	private String reporterCountry;
	@XmlElement(name="qualification")
	private String qualification;

	public String getReporterCountry()
	{
		return reporterCountry;
	}

	public void setReporterCountry(String reporterCountry)
	{
		this.reporterCountry = reporterCountry;
	}

	public String getQualification()
	{
		return qualification;
	}

	public void setQualification(String qualification)
	{
		this.qualification = qualification;
	}

	@Override
	public String toString()
	{
		return "PrimarySource [reporterCountry=" + reporterCountry + ", qualification=" + qualification + "]";
	}
	
}
