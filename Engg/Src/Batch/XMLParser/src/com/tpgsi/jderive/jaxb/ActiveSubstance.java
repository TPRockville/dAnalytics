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
public class ActiveSubstance 
{
	@XmlElement(name ="activesubstancename")
	private String activeSubstanceName;

	public String getActiveSubstanceName() {
		return activeSubstanceName;
	}

	public void setActiveSubstanceName(String activeSubstanceName) {
		this.activeSubstanceName = activeSubstanceName;
	}
}
