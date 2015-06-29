#!/bin/sh
echo " ----------------------- Starting ---------------------"
pig -param input=$1 druglist.pig
pig -param input=$1 countries.pig
pig -param input=$1 drugsubstance.pig
pig -param input=$1 reaction.pig
pig -param input=$1 drugindication.pig
pig -param input=$1 drugsummary.pig
pig -param input=$1 drugreactionsummary.pig
pig -param input=$1 drugcharsummary.pig
pig -param input=$1 drugsummarybymonth.pig
pig -param input=$1 drugonlysummarybymonth.pig
echo " ------------------- Completed ------------------------"
