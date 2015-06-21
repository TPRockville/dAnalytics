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
public class Receiver
{
	@XmlElement(name="receivertype")
	private String receiverType;
	@XmlElement(name="receiverorganization")
	private String receiverOrganization;

	public String getReceiverType()
	{
		return receiverType;
	}

	public void setReceiverType(String receiverType)
	{
		this.receiverType = receiverType;
	}

	public String getReceiverOrganization()
	{
		return receiverOrganization;
	}

	public void setReceiverOrganization(String receiverOrganization)
	{
		this.receiverOrganization = receiverOrganization;
	}

	@Override
	public String toString()
	{
		return "Receiver [receiverType=" + receiverType + ", receiverOrganization=" + receiverOrganization + "]";
	}
	
}
