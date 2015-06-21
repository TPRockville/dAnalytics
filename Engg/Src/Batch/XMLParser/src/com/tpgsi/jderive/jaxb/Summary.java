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
public class Summary
{
	@XmlElement(name="narrativeincludeclinical")
	private String narrativeInclDecl;

	public String getNarrativeInclDecl() {
		return narrativeInclDecl;
	}

	public void setNarrativeInclDecl(String narrativeInclDecl) {
		this.narrativeInclDecl = narrativeInclDecl;
	}

	
	
}
