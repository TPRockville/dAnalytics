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
public class IchicsrMessageHeader
{
	@XmlElement(name="messagetype")
	private String messageType;
	@XmlElement(name="messageformatversion")
	private double messageFormatVersion;
	@XmlElement(name="messageformatrelease")
	private double messageFormatRelease;
	@XmlElement(name="messagenumb")
	private String messageNumb;
	@XmlElement(name="messagesenderidentifier")
	private String messageSenderIdentifier;
	@XmlElement(name="")
	private String messageReceiverIdentifier;
	@XmlElement(name="messagedateformat")
	private int messageEDateFormat;
	@XmlElement(name="messagedate")
	private String messageDate;

	public String getMessageType()
	{
		return messageType;
	}

	public void setMessageType(String messageType)
	{
		this.messageType = messageType;
	}

	public double getMessageFormatVersion()
	{
		return messageFormatVersion;
	}

	public void setMessageFormatVersion(double messageFormatVersion)
	{
		this.messageFormatVersion = messageFormatVersion;
	}

	public double getMessageFormatRelease()
	{
		return messageFormatRelease;
	}

	public void setMessageFormatRelease(double messageFormatRelease)
	{
		this.messageFormatRelease = messageFormatRelease;
	}

	public String getMessageNumb()
	{
		return messageNumb;
	}

	public void setMessageNumb(String messageNumb)
	{
		this.messageNumb = messageNumb;
	}

	public String getMessageSenderIdentifier()
	{
		return messageSenderIdentifier;
	}

	public void setMessageSenderIdentifier(String messageSenderIdentifier)
	{
		this.messageSenderIdentifier = messageSenderIdentifier;
	}

	public String getMessageReceiverIdentifier()
	{
		return messageReceiverIdentifier;
	}

	public void setMessageReceiverIdentifier(String messageReceiverIdentifier)
	{
		this.messageReceiverIdentifier = messageReceiverIdentifier;
	}

	public int getMessageEDateFormat()
	{
		return messageEDateFormat;
	}

	public void setMessageEDateFormat(int messageEDateFormat)
	{
		this.messageEDateFormat = messageEDateFormat;
	}

	public String getMessageDate()
	{
		return messageDate;
	}

	public void setMessageDate(String messageDate)
	{
		this.messageDate = messageDate;
	}

	@Override
	public String toString()
	{
		return "IchicsrMessageHeader [messageType=" + messageType + ", messageFormatVersion=" + messageFormatVersion + ", messageFormatRelease=" + messageFormatRelease
				+ ", messageNumb=" + messageNumb + ", messageSenderIdentifier=" + messageSenderIdentifier + ", messageReceiverIdentifier=" + messageReceiverIdentifier
				+ ", messageEDateFormat=" + messageEDateFormat + ", messageDate=" + messageDate + "]";
	}
	
}
