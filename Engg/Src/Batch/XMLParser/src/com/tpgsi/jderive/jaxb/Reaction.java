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
public class Reaction
{
	@XmlElement(name = "reactionmeddraversionpt")
	private String reactionMeddraversionpt;
	@XmlElement(name = "reactionmeddrapt")
	private String reactionMeddrapt;
	@XmlElement(name = "reactionoutcome")
	private String reactionOutcome;

	public String getReactionMeddraversionpt()
	{
		return reactionMeddraversionpt;
	}

	public void setReactionMeddraversionpt(String reactionMeddraversionpt)
	{
		this.reactionMeddraversionpt = reactionMeddraversionpt;
	}

	public String getReactionMeddrapt()
	{
		return reactionMeddrapt;
	}

	public void setReactionMeddrapt(String reactionMeddrapt)
	{
		this.reactionMeddrapt = reactionMeddrapt;
	}

	public String getReactionOutcome()
	{
		return reactionOutcome;
	}

	public void setReactionOutcome(String reactionOutcome)
	{
		this.reactionOutcome = reactionOutcome;
	}

}
