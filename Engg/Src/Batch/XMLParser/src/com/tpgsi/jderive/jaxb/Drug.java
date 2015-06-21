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
public class Drug
{
	@XmlElement(name = "drugcharacterization")
	private String drugCharacterization;
	@XmlElement(name = "medicinalproduct")
	private String medicinalProduct;
	@XmlElement(name = "drugdosagetext")
	private String drugDosageText;
	@XmlElement(name = "drugauthorizationnumb")
	private String drugAuthorizationNumb;
	@XmlElement(name = "drugstructuredosagenumb")
	private String drugStructureDosageNumb;
	@XmlElement(name = "drugstructuredosageunit")
	private String drugstructureDosageUnit;
	@XmlElement(name = "drugseparatedosagenumb")
	private String drugSeparateDosageNumb;
	@XmlElement(name = "drugintervaldosageunitnumb")
	private String drugIntervalDosageUnitNumb;
	@XmlElement(name = "drugintervaldosagedefinition")
	private String drugIntervalDosageDefinition;
	@XmlElement(name = "drugadministrationroute")
	private String drugaAdministrationRoute;
	@XmlElement(name = "drugindication")
	private String drugIndication;
	@XmlElement(name = "drugstartdateformat")
	private String drugStartDateFormat;
	@XmlElement(name = "drugstartdate")
	private String drugStartDate;
	@XmlElement(name = "drugenddateformat")
	private String drugEndDateFormat;
	@XmlElement(name = "drugenddate")
	private String drugEndDate;
	@XmlElement(name = "actiondrug")
	private String actionDrug;
	@XmlElement(name = "drugbatchnumb")
	private String drugBatchNumb;
	@XmlElement(name = "drugdosageform")
	private String drugDosageForm;
	@XmlElement(name ="drugrecurreadministration")
	private String drugRecurreAdministration;
	@XmlElement(name ="activesubstance")
	private ActiveSubstance activeSubstance;
	
 	public ActiveSubstance getActiveSubstance() {
		return activeSubstance;
	}

	public void setActiveSubstance(ActiveSubstance activeSubstance) {
		this.activeSubstance = activeSubstance;
	}
	
	public String getDrugDosageText() {
		return drugDosageText;
	}

	public void setDrugDosageText(String drugDosageText) {
		this.drugDosageText = drugDosageText;
	}

	public String getDrugEndDateFormat() {
		return drugEndDateFormat;
	}

	public void setDrugEndDateFormat(String drugEndDateFormat) {
		this.drugEndDateFormat = drugEndDateFormat;
	}

	public String getDrugEndDate() {
		return drugEndDate;
	}

	public void setDrugEndDate(String durgEndDate) {
		this.drugEndDate = durgEndDate;
	}

	public String getDrugDosageForm()
	{
		return drugDosageForm;
	}

	public void setDrugDosageForm(String drugDosageForm)
	{
		this.drugDosageForm = drugDosageForm;
	}
	public String getDrugIndication()
	{
		return drugIndication;
	}

	public void setDrugIndication(String drugIndication)
	{
		this.drugIndication = drugIndication;
	}

	public String getDrugStartDateFormat()
	{
		return drugStartDateFormat;
	}

	public void setDrugStartDateFormat(String drugStartDateFormat)
	{
		this.drugStartDateFormat = drugStartDateFormat;
	}

	public String getDrugStartDate()
	{
		return drugStartDate;
	}

	public void setDrugStartDate(String drugStartDate)
	{
		this.drugStartDate = drugStartDate;
	}

	public String getMedicinalProduct()
	{
		return medicinalProduct;
	}

	public void setMedicinalProduct(String medicinalProduct)
	{
		this.medicinalProduct = medicinalProduct;
	}

	public String getDrugCharacterization() {
		return drugCharacterization;
	}

	public void setDrugCharacterization(String drugCharacterization) {
		this.drugCharacterization = drugCharacterization;
	}

	public String getDrugBatchNumb() {
		return drugBatchNumb;
	}

	public void setDrugBatchNumb(String drugBatchNumb) {
		this.drugBatchNumb = drugBatchNumb;
	}

	public String getDrugStructureDosageNumb() {
		return drugStructureDosageNumb;
	}

	public void setDrugStructureDosageNumb(String drugStructureDosageNumb) {
		this.drugStructureDosageNumb = drugStructureDosageNumb;
	}

	public String getDrugstructureDosageUnit() {
		return drugstructureDosageUnit;
	}

	public void setDrugstructureDosageUnit(String drugstructureDosageUnit) {
		this.drugstructureDosageUnit = drugstructureDosageUnit;
	}

	public String getDrugSeparateDosageNumb() {
		return drugSeparateDosageNumb;
	}

	public void setDrugSeparateDosageNumb(String drugSeparateDosageNumb) {
		this.drugSeparateDosageNumb = drugSeparateDosageNumb;
	}

	public String getDrugIntervalDosageUnitNumb() {
		return drugIntervalDosageUnitNumb;
	}

	public void setDrugIntervalDosageUnitNumb(String drugIntervalDosageUnitNumb) {
		this.drugIntervalDosageUnitNumb = drugIntervalDosageUnitNumb;
	}

	public String getDrugIntervalDosageDefinition() {
		return drugIntervalDosageDefinition;
	}

	public void setDrugIntervalDosageDefinition(String drugIntervalDosageDefinition) {
		this.drugIntervalDosageDefinition = drugIntervalDosageDefinition;
	}

	public String getDrugAuthorizationNumb() {
		return drugAuthorizationNumb;
	}

	public void setDrugAuthorizationNumb(String drugAuthorizationNumb) {
		this.drugAuthorizationNumb = drugAuthorizationNumb;
	}

	public String getDrugaAdministrationRoute() {
		return drugaAdministrationRoute;
	}

	public void setDrugaAdministrationRoute(String drugaAdministrationRoute) {
		this.drugaAdministrationRoute = drugaAdministrationRoute;
	}

	public String getActionDrug() {
		return actionDrug;
	}

	public void setActionDrug(String actionDrug) {
		this.actionDrug = actionDrug;
	}

	public String getDrugRecurreAdministration() {
		return drugRecurreAdministration;
	}

	public void setDrugRecurreAdministration(String drugRecurreAdministration) {
		this.drugRecurreAdministration = drugRecurreAdministration;
	}

	@Override
	public String toString()
	{
		return "Drug [drugCharacterization=" + drugCharacterization + ", medicinalProduct=" + medicinalProduct + ", drugBatchNumb=" + drugBatchNumb
				+ ", drugStructureDosageNumb=" + drugStructureDosageNumb + ", drugstructureDosageUnit=" + drugstructureDosageUnit + ", drugseparateDosageNumb="
				+ drugSeparateDosageNumb + ", drugIntervalDosageUnitNumb=" + drugIntervalDosageUnitNumb + ", drugIntervalDosageDefinition="
				+ drugIntervalDosageDefinition + ", drugAuthorizationNumb=" + drugAuthorizationNumb + ", drugDosageForm=" + drugDosageForm
				+ ", drugaAdministrationRoute=" + drugaAdministrationRoute + ", drugIndication=" + drugIndication + ", drugStartDateFormat=" + drugStartDateFormat
				+ ", drugStartDate=" + drugStartDate + ", actionDrug=" + actionDrug + ", drugRecurreAdministration=" + drugRecurreAdministration + "]";
	}
	
}
