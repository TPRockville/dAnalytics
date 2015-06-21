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
public class SafetyReport
{
	@XmlElement(name="safetyreportversion")
	private String safetyReportVersion;
	@XmlElement(name="safetyreportid")
	private String safetyReportId;
	@XmlElement(name="primarysourcecountry")
	private String primarySourceCountry;
	@XmlElement(name="occurcountry")
	private String occurCountry;
	@XmlElement(name="transmissiondateformat")
	private String transmissionDateFormat;
	@XmlElement(name="transmissiondate")
	private String transmissionDate;
	@XmlElement(name="reporttype")
	private String reportType;
	
	@XmlElement(name="serious")
	private String serious;
	@XmlElement(name="seriousnessdeath")
	private String seriousnessDeath;
	@XmlElement(name="seriousnesslifethreatening")
	private String seriousnessLifeThreat;
	@XmlElement(name="seriousnesshospitalization")
	private String seriousnessHospital;
	@XmlElement(name="seriousnessdisabling")
	private String seriousnessDisabling;
	@XmlElement(name="seriousnesscongenitalanomali")
	private String seriousnessCongAnomali;
	@XmlElement(name="seriousnessother")
	private String seriousnessOthers;
	
	@XmlElement(name="receivedateformat")
	private String receiveDateFormat;
	@XmlElement(name="receivedate")
	private String receiveDate;
	@XmlElement(name="receiptdateformat")
	private String receiptDateFormat;
	@XmlElement(name="receiptdate")
	private String receiptDate;
	@XmlElement(name="fulfillexpeditecriteria")
	private String fulfillExpediteCriteria;
	@XmlElement(name="companynumb")
	private String companyNumb;
	@XmlElement(name="duplicate")
	private String duplicate;
	
	@XmlElement(name="reportduplicate")
	private ReportDuplicate reportDuplicate;
	
	@XmlElement(name="primarysource")
	private PrimarySource primarySource;
	
	@XmlElement(name="sender")
	private Sender sender;
	
	@XmlElement(name="receiver")
	private Receiver receiver;
	
	@XmlElement(name="patient")
	private Patient patient;
	
	
	
	public ReportDuplicate getReportDuplicate() {
		return reportDuplicate;
	}

	public void setReportDuplicate(ReportDuplicate reportDuplicate) {
		this.reportDuplicate = reportDuplicate;
	}

	public PrimarySource getPrimarySource() {
		return primarySource;
	}

	public void setPrimarySource(PrimarySource primarySource) {
		this.primarySource = primarySource;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getPrimarySourceCountry()
	{
		return primarySourceCountry;
	}

	public void setPrimarySourceCountry(String primarySourceCountry)
	{
		this.primarySourceCountry = primarySourceCountry;
	}

	public String getOccurCountry()
	{
		return occurCountry;
	}

	public void setOccurCountry(String occurCountry)
	{
		this.occurCountry = occurCountry;
	}

	public String getTransmissionDateFormat()
	{
		return transmissionDateFormat;
	}

	public void setTransmissionDateFormat(String transmissionDateFormat)
	{
		this.transmissionDateFormat = transmissionDateFormat;
	}


	public String getReceiveDate()
	{
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate)
	{
		this.receiveDate = receiveDate;
	}

	public String getReceiptDateFormat()
	{
		return receiptDateFormat;
	}

	public void setReceiptDateFormat(String receiptDateFormat)
	{
		this.receiptDateFormat = receiptDateFormat;
	}

	public String getReceiptDate()
	{
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate)
	{
		this.receiptDate = receiptDate;
	}

	public String getFulfillExpediteCriteria()
	{
		return fulfillExpediteCriteria;
	}

	public void setFulfillExpediteCriteria(String fulfillExpediteCriteria)
	{
		this.fulfillExpediteCriteria = fulfillExpediteCriteria;
	}

	public String getCompanyNumb()
	{
		return companyNumb;
	}

	public void setCompanyNumb(String companyNumb)
	{
		this.companyNumb = companyNumb;
	}

	public String getSafetyReportVersion() {
		return safetyReportVersion;
	}

	public void setSafetyReportVersion(String safetyReportVersion) {
		this.safetyReportVersion = safetyReportVersion;
	}

	public String getSafetyReportId() {
		return safetyReportId;
	}

	public void setSafetyReportId(String safetyReportId) {
		this.safetyReportId = safetyReportId;
	}

	public String getTransmissionDate() {
		return transmissionDate;
	}

	public void setTransmissionDate(String transmissionDate) {
		this.transmissionDate = transmissionDate;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getSerious() {
		return serious;
	}

	public void setSerious(String serious) {
		this.serious = serious;
	}

	public String getSeriousnessDeath() {
		return seriousnessDeath;
	}

	public void setSeriousnessDeath(String seriousnessDeath) {
		this.seriousnessDeath = seriousnessDeath;
	}

	public String getReceiveDateFormat() {
		return receiveDateFormat;
	}

	public void setReceiveDateFormat(String receiveDateFormat) {
		this.receiveDateFormat = receiveDateFormat;
	}

	public String getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(String duplicate) {
		this.duplicate = duplicate;
	}

	public String getSeriousnessLifeThreat() {
		return seriousnessLifeThreat;
	}

	public void setSeriousnessLifeThreat(String seriousnessLifeThreat) {
		this.seriousnessLifeThreat = seriousnessLifeThreat;
	}

	public String getSeriousnessHospital() {
		return seriousnessHospital;
	}

	public void setSeriousnessHospital(String seriousnessHospital) {
		this.seriousnessHospital = seriousnessHospital;
	}

	public String getSeriousnessDisabling() {
		return seriousnessDisabling;
	}

	public void setSeriousnessDisabling(String seriousnessDisabling) {
		this.seriousnessDisabling = seriousnessDisabling;
	}

	public String getSeriousnessCongAnomali() {
		return seriousnessCongAnomali;
	}

	public void setSeriousnessCongAnomali(String seriousnessCongAnomali) {
		this.seriousnessCongAnomali = seriousnessCongAnomali;
	}

	public String getSeriousnessOthers() {
		return seriousnessOthers;
	}

	public void setSeriousnessOthers(String seriousnessOthers) {
		this.seriousnessOthers = seriousnessOthers;
	}

	@Override
	public String toString()
	{
		return "SafetyReport [safetyReportVersion=" + safetyReportVersion + ", safetyReportId=" + safetyReportId + ", primarySourceCountry=" + primarySourceCountry
				+ ", occurCountry=" + occurCountry + ", transmissionDateFormat=" + transmissionDateFormat + ", transmissionDate=" + transmissionDate + ", reportType="
				+ reportType + ", serious=" + serious + ", seriousnessDeath=" + seriousnessDeath + ", receiveDateFormat=" + receiveDateFormat + ", receiveDate="
				+ receiveDate + ", receiptDateFormat=" + receiptDateFormat + ", receiptDate=" + receiptDate + ", fulfillExpediteCriteria=" + fulfillExpediteCriteria
				+ ", companyNumb=" + companyNumb + ", duplicate=" + duplicate + ", reportDuplicate=" + reportDuplicate + ", primarySource=" + primarySource + ", sender="
				+ sender + ", receiver=" + receiver + ", patient=" + patient + "]";
	}
}
