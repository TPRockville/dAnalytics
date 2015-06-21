package com.tpgsi.jderive.jaxb;

import java.util.List;

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
public class Patient
{
	@XmlElement(name = "patientonsetage")
	private String patientOnSetAge;
	@XmlElement(name = "patientonsetageunit")
	private String patientOnSetAgeUnit;
	@XmlElement(name = "patientweight")
	private String patientWeight;
	@XmlElement(name = "patientsex")
	private String patientSex;
	
	@XmlElement(name="reaction")
	private List<Reaction> reaction;
	
	@XmlElement(name="drug")
	private List<Drug> drug;
	
	@XmlElement(name="summary")
	private Summary summary;
	
	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public String getPatientOnSetAge() {
		return patientOnSetAge;
	}

	public void setPatientOnSetAge(String patientOnSetAge) {
		this.patientOnSetAge = patientOnSetAge;
	}

	public String getPatientOnSetAgeUnit() {
		return patientOnSetAgeUnit;
	}

	public void setPatientOnSetAgeUnit(String patientOnSetAgeUnit) {
		this.patientOnSetAgeUnit = patientOnSetAgeUnit;
	}

	public String getPatientWeight() {
		return patientWeight;
	}

	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public List<Reaction> getReaction()
	{
		return reaction;
	}

	public void setReaction(List<Reaction> reaction)
	{
		this.reaction = reaction;
	}

	public List<Drug> getDrug()
	{
		return drug;
	}

	public void setDrug(List<Drug> drug)
	{
		this.drug = drug;
	}

	@Override
	public String toString()
	{
		return "Patient [patientOnSetAge=" + patientOnSetAge + ", patientOnSetAgeUnit=" + patientOnSetAgeUnit + ", patientWeight=" + patientWeight + ", patientSex="
				+ patientSex + ", reaction=" + reaction + ", drug=" + drug + "]";
	}
	
}
