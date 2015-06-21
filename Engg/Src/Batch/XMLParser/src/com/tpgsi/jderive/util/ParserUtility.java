package com.tpgsi.jderive.util;

import java.io.BufferedWriter;
import java.util.List;

import com.tpgsi.jderive.jaxb.ActiveSubstance;
import com.tpgsi.jderive.jaxb.Drug;
import com.tpgsi.jderive.jaxb.Patient;
import com.tpgsi.jderive.jaxb.PrimarySource;
import com.tpgsi.jderive.jaxb.Reaction;
import com.tpgsi.jderive.jaxb.Receiver;
import com.tpgsi.jderive.jaxb.ReportDuplicate;
import com.tpgsi.jderive.jaxb.SafetyReport;
import com.tpgsi.jderive.jaxb.Sender;
import com.tpgsi.jderive.jaxb.Summary;

/**
 * 
 * @author Akshay
 *
 */
public class ParserUtility 
{
	private static final String DELIMMITER = ",";
	private static final String COLUMN_NAMES = "SAFETYREPORTVERSION,SAFETYREPORTID,PRIMARYSOURCECOUNTRY,OCCURCOUNTRY,TRANSMISSIONDATEFORMAT,TRANSMISSIONDATE,REPORTTYPE,RECEIVEDATEFORMAT,RECEIVEDATE,RECEIPTDATEFORMAT,RECEIPTDATE,FULFILLEXPEDITECRITERIA,COMPANYNUMB,DUPLICATE,DUPLICATESOURCE,DUPLICATENUMB,REPORTERCOUNTRY,QUALIFICATION,SENDERTYPE,SENDERORGANIZATION,RECEIVERTYPE,RECEIVERORGANIZATION,PATIENTONSETAGE,PATIENTONSETAGEUNIT,PATIENTWEIGHT,PATIENTSEX,DRUGCHARACTERIZATION,MEDICINALPRODUCT,DRUGDOSAGETEXT,DRUGDOSAGEFORM,DRUGAUTHORIZATIONNUMB,DRUGSTRUCTUREDOSAGENUMB,DRUGSTRUCTUREDOSAGEUNIT,DRUGSEPARATEDOSAGENUMB,DRUGINTERVALDOSAGEUNITNUMB,DRUGINTERVALDOSAGEDEFINITION,DRUGAADMINISTRATIONROUTE,DRUGINDICATION,DRUGSTARTDATEFORMAT,DRUGSTARTDATE,DRUGENDDATEFORMAT,DRUGENDDATE,ACTIONDRUG,ACTIVESUBSTANCENAME,REACTIONMEDDRAVERSIONPT,REACTIONMEDDRAPT,REACTIONOUTCOME,NARRATIVEINCLUDECLINICAL\n";
	
	/**
	 * Denormalizing the XML file by multiplying the number of Drugs with number of reactions and then adding into a csv file
	 * @param safetyList
	 * @param bout
	 * @throws Exception
	 */
	public void generateReport(final List<SafetyReport> safetyList, final BufferedWriter bout) throws Exception 
	{
		bout.write(COLUMN_NAMES);
		Patient patient;
		ReportDuplicate reportDuplicate;
		PrimarySource primarySource;
		Sender sender; 
		Receiver receiver; 
		Summary summary; 
		ActiveSubstance activeSubstance;
		
		List<Reaction> reactions;
		List<Drug> drugs ;
		final StringBuilder csvBuilder = new StringBuilder();
		for(final SafetyReport sr: safetyList)
		{
			patient = sr.getPatient();
			reactions = patient.getReaction();
			drugs = patient.getDrug();
			for(final Drug drug : drugs)
			{
				for(final Reaction react : reactions)
				{
					csvBuilder.append(returnBlankIfNull(sr.getSafetyReportVersion())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getSafetyReportId())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getPrimarySourceCountry())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getOccurCountry())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getTransmissionDateFormat())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getTransmissionDate())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getReportType())).append(DELIMMITER);
					
					csvBuilder.append(returnBlankIfNull(sr.getReceiveDateFormat())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getReceiveDate())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getReceiptDateFormat())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getReceiptDate())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getFulfillExpediteCriteria())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(sr.getCompanyNumb())).append(DELIMMITER);
					
					csvBuilder.append(returnBlankIfNull(sr.getDuplicate())).append(DELIMMITER);
					
					reportDuplicate = sr.getReportDuplicate();
					if(reportDuplicate != null)
					{
						csvBuilder.append(returnBlankIfNull(reportDuplicate.getDuplicateSource())).append(DELIMMITER);
						csvBuilder.append(returnBlankIfNull(reportDuplicate.getDuplicateNumb())).append(DELIMMITER);
					}
					else
					{
						csvBuilder.append(DELIMMITER).append(DELIMMITER);
					}

					primarySource = sr.getPrimarySource();
					
					if(primarySource != null)
					{
						csvBuilder.append(returnBlankIfNull(primarySource.getReporterCountry())).append(DELIMMITER);
						csvBuilder.append(returnBlankIfNull(primarySource.getQualification())).append(DELIMMITER);
					}
					else
					{
						csvBuilder.append(DELIMMITER).append(DELIMMITER);
					}
					
					sender = sr.getSender();
					if(sender != null)
					{
						csvBuilder.append(returnBlankIfNull(sender.getSenderType())).append(DELIMMITER);
						csvBuilder.append(returnBlankIfNull(sender.getSenderOrganization())).append(DELIMMITER);
					}
					else
					{
						csvBuilder.append(DELIMMITER).append(DELIMMITER);
					}
					
					receiver = sr.getReceiver();
					if(receiver != null)
					{
						csvBuilder.append(returnBlankIfNull(receiver.getReceiverType())).append(DELIMMITER);
						csvBuilder.append(returnBlankIfNull(receiver.getReceiverOrganization())).append(DELIMMITER);
					}
					else
					{
						csvBuilder.append(DELIMMITER).append(DELIMMITER);
					}
					
					csvBuilder.append(returnBlankIfNull(patient.getPatientOnSetAge())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(patient.getPatientOnSetAgeUnit())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(patient.getPatientWeight())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(patient.getPatientSex())).append(DELIMMITER);
					
					csvBuilder.append(returnBlankIfNull(drug.getDrugCharacterization())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getMedicinalProduct())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugDosageText())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugDosageForm())).append(DELIMMITER);
					
					csvBuilder.append(returnBlankIfNull(drug.getDrugAuthorizationNumb())).append(DELIMMITER);

					csvBuilder.append(returnBlankIfNull(drug.getDrugStructureDosageNumb())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugstructureDosageUnit())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugSeparateDosageNumb())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugIntervalDosageUnitNumb())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugIntervalDosageDefinition())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugaAdministrationRoute())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugIndication())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugStartDateFormat())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugStartDate())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugEndDateFormat())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getDrugEndDate())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(drug.getActionDrug())).append(DELIMMITER);
					
					activeSubstance = drug.getActiveSubstance();
					if(activeSubstance != null)
					{
						csvBuilder.append(returnBlankIfNull(activeSubstance.getActiveSubstanceName())).append(DELIMMITER);
					}
					else
					{
						csvBuilder.append(DELIMMITER);
					}
					
					csvBuilder.append(returnBlankIfNull(react.getReactionMeddraversionpt())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(react.getReactionMeddrapt())).append(DELIMMITER);
					csvBuilder.append(returnBlankIfNull(react.getReactionOutcome())).append(DELIMMITER);
					
					summary = patient.getSummary();
					if(summary != null)
					{
						csvBuilder.append(returnBlankIfNull(summary.getNarrativeInclDecl())).append(DELIMMITER);
					}
					else
					{
						csvBuilder.append(DELIMMITER);
					}

					csvBuilder.append("\n");
					bout.write(csvBuilder.toString());
					csvBuilder.setLength(0);
				}
			}
		}
	}
	
	/**
	 * Return Blank value if the value is null 
	 * Else if the value contains a comma, add space instead
	 * @param value
	 * @return
	 */
	private String returnBlankIfNull(final String value)
	{
		if(value == null)
			return "";
		else
			return value.replaceAll(",", " ");
	}
}
