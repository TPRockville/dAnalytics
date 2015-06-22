REGISTER HashUDF.jar;

inputfile = load '/user/hadoop/test.csv' USING PigStorage(',') as (SAFETYREPORTVERSION:chararray,SAFETYREPORTID:chararray,PRIMARYSOURCECOUNTRY:chararray,OCCURCOUNTRY:chararray,TRANSMISSIONDATEFORMAT:chararray,TRANSMISSIONDATE:chararray,REPORTTYPE:chararray,RECEIVEDATEFORMAT:chararray,RECEIVEDATE:chararray,RECEIPTDATEFORMAT:chararray,RECEIPTDATE:chararray,FULFILLEXPEDITECRITERIA:chararray,COMPANYNUMB:chararray,DUPLICATE:chararray,DUPLICATESOURCE:chararray,DUPLICATENUMB:chararray,REPORTERCOUNTRY:chararray,QUALIFICATION:chararray,SENDERTYPE:chararray,SENDERORGANIZATION:chararray,RECEIVERTYPE:chararray,RECEIVERORGANIZATION:chararray,PATIENTONSETAGE:int,PATIENTONSETAGEUNIT:chararray,PATIENTWEIGHT:int,PATIENTSEX:chararray,DRUGCHARACTERIZATION:chararray,MEDICINALPRODUCT:chararray,DRUGDOSAGETEXT:chararray,DRUGDOSAGEFORM:chararray,DRUGAUTHORIZATIONNUMB:chararray,DRUGSTRUCTUREDOSAGENUMB:chararray,DRUGSTRUCTUREDOSAGEUNIT:chararray,DRUGSEPARATEDOSAGENUMB:chararray,DRUGINTERVALDOSAGEUNITNUMB:chararray,DRUGINTERVALDOSAGEDEFINITION:chararray,DRUGAADMINISTRATIONROUTE:chararray,DRUGINDICATION:chararray,DRUGSTARTDATEFORMAT:chararray,DRUGSTARTDATE:chararray,DRUGENDDATEFORMAT:chararray,DRUGENDDATE:chararray,ACTIONDRUG:chararray,ACTIVESUBSTANCENAME:chararray,REACTIONMEDDRAVERSIONPT:chararray,REACTIONMEDDRAPT:chararray,REACTIONOUTCOME:chararray,NARRATIVEINCLUDECLINICAL:chararray);

notnulldrugs = FILTER inputfile by MEDICINALPRODUCT is not null OR MEDICINALPRODUCT != '';

drugs = foreach notnulldrugs generate com.tpgsi.pigudf.StringHashCode(MEDICINALPRODUCT),MEDICINALPRODUCT;

uniqudrug = DISTINCT drugs; 

STORE uniqudrug INTO 'druglist' USING PigStorage (',');