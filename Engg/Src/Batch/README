# dAnalytics Bacth Process- Hadoop, Pig & Sqoop

TODO: project description goes here

## Components
    - XML Parser (Java based tool to converts input XML to CSV)
    - Hadoop
    - Pig Latin Scripts & UDFs (Transforms & aggregates the data)
    - Sqoop Scripts (Dumps the data to Star schema in MariaDB)

## Installation
    TODO: Installation details goes here

## Usage
1. Build the [XML Parser](https://github.com/pradeepkumardv/jDerive/tree/master/Engg/Src/Batch/XMLParser) and run the below command to convert input XML to CSV. <br/> 
    `java -cp :data-loader.jar:/home/hadoop/data_loader/csvgenerator/lib/* com.tpgsi.parser.XMLParser <Argument 1 : CSV file> <Argument 2 : XML file>`
2. Put the input csv file into hdfs using `hadoop fs –put [INPUT.CSV]` in a terminal. This will put the INPUT.CSV into /user/[username]/[INPUT.CSV] in hdfs.
2. Copy the Pig directory from the [Batch](https://github.com/TPRockville/dAnalytics/tree/master/Engg/Src/Batch/), place it in any folder on a Linux machine.
3. Build the [PigUdf](https://github.com/TPRockville/dAnalytics/tree/master/Engg/Src/Batch/Pig/PigUdf) and place the jar in the same folder above. <br />
    `cd PigUdf` <br />
    `ant`
4. Run the shell [pigscripts.sh](https://github.com/TPRockville/dAnalytics/blob/master/Engg/Src/Batch/Pig/pigscripts.sh) with parameter as path of the input csv in hdfs as /user/[username]/[INPUT.CSV]
5. Run sqoop commands from the attached file. Mariadb connection configuration for each script needs to be changed.<br />
    Eg: <br />
    `sqoop export --connect jdbc:mysql://[IP]:3306/[schema] --username [username] --password [password] --table drug_list --columns "drug_id,drug_name"  --export-dir /user/[username]/CompleteDS/druglist/part-* --verbose`
