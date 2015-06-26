DELIMITER $$

USE `dAnalyticsFinal`$$

DROP PROCEDURE IF EXISTS `populateDurgBasedPeak`$$

CREATE DEFINER=`root`@`%` PROCEDURE `populateDurgBasedPeak`(IN delta_n INT)
Block1: BEGIN
	DECLARE current_max_id INT;
	DECLARE COUNT INT;
	DECLARE max_no INT;
	DECLARE no_more_rows1 BOOLEAN;
	DECLARE current_drug_id BIGINT;
	DECLARE current_start_date DATE;
	DECLARE current_event_count BIGINT;
	DECLARE current_event_avg BIGINT;
	DECLARE avg_count DOUBLE;
	DECLARE median DOUBLE;
	DECLARE drug_cur CURSOR FOR SELECT drug_id FROM drug_list;
		DECLARE CONTINUE HANDLER FOR NOT FOUND 
		SET no_more_rows1 := TRUE; 
	SET COUNT = (SELECT IFNULL(MAX(spike_id),0) AS COUNT  FROM drug_spikes);
    	IF COUNT=0 THEN 
	SET max_no := 1;
	ELSE
		SET max_no := (SELECT MAX(spike_id) FROM drug_spikes);
	END IF;
	SET avg_count := 0;
	OPEN drug_cur;
	loop1: LOOP
	FETCH drug_cur INTO current_drug_id;
	IF no_more_rows1 THEN CLOSE drug_cur;
		LEAVE loop1;
	END IF;
	Block2: BEGIN
		SELECT AVG(event_count) INTO @avg_count FROM drug_summary_month dg2 WHERE dg2.drug_id=current_drug_id;
		SET median := @avg_count + @avg_count * delta_n; 
	END Block2;
	Block3: BEGIN
		DECLARE no_more_rows2 BOOLEAN;
		DECLARE cur CURSOR FOR SELECT start_date, SUM(event_count) event_count FROM drug_summary_month WHERE drug_id=current_drug_id GROUP BY start_date;
		DECLARE CONTINUE HANDLER FOR NOT FOUND
		SET no_more_rows2 := TRUE;
		OPEN cur;
		loop2: LOOP
		FETCH cur INTO current_start_date,current_event_count;
		IF no_more_rows2 THEN
			CLOSE cur;
			LEAVE loop2;
		END IF;	
		IF current_event_count > median THEN
			SET max_no := max_no + 1;
			INSERT INTO drug_spikes (drug_id,spike_date,event_count,spike_id,drug_event_avg,drug_event_threshold) VALUES (current_drug_id,current_start_date,current_event_count,max_no,@avg_count,median);
		END IF;	
		END LOOP loop2;
		COMMIT;
	END Block3;
	END LOOP loop1;
	END Block1$$

DELIMITER ;
