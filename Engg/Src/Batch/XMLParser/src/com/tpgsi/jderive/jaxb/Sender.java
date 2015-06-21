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
public class Sender
{
	@XmlElement(name="sendertype")
	private String senderType;
	@XmlElement(name="senderorganization")
	private String senderOrganization;

	public String getSenderType()
	{
		return senderType;
	}

	public void setSenderType(String senderType)
	{
		this.senderType = senderType;
	}

	public String getSenderOrganization()
	{
		return senderOrganization;
	}

	public void setSenderOrganization(String senderOrganization)
	{
		this.senderOrganization = senderOrganization;
	}

	@Override
	public String toString()
	{
		return "Sender [senderType=" + senderType + ", senderOrganization=" + senderOrganization + "]";
	}
	
}
