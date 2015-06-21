package com.tpgsi.jderive.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Akshay
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Ichicsr 
{
	@XmlAttribute(name = "lang")
	private String lang;

	@XmlElement(name = "ichicsrmessageheader")
	private IchicsrMessageHeader ichicsrmessageheader;

	@XmlElement(name = "safetyreport")
	private List<SafetyReport> lisReports;

	public String getLang()
	{
		return lang;
	}

	public void setLang(String lang)
	{
		this.lang = lang;
	}

	public IchicsrMessageHeader getIchicsrmessageheader()
	{
		return ichicsrmessageheader;
	}

	public void setIchicsrmessageheader(IchicsrMessageHeader ichicsrmessageheader)
	{
		this.ichicsrmessageheader = ichicsrmessageheader;
	}

	public List<SafetyReport> getLisReports()
	{
		return lisReports;
	}

	public void setLisReports(List<SafetyReport> lisReports)
	{
		this.lisReports = lisReports;
	}

	@Override
	public String toString()
	{
		return "Ichicsr [lang=" + lang + ", ichicsrmessageheader=" + ichicsrmessageheader + ", lisReports=" + lisReports + "]";
	}
	

}
