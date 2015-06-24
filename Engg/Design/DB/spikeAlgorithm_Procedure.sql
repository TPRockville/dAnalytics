DROP procedure IF EXISTS `populateDurgBasedPeak`;
DELIMITER $$
CREATE PROCEDURE `populateDurgBasedPeak`(IN delta_n INT)
Block1: BEGIN
	DECLARE current_max_id INT;
    DECLARE count int;
	DECLARE max_no INT;
	DECLARE no_more_rows1 BOOLEAN;
	DECLARE current_drug_id BIGINT;
	DECLARE current_start_date DATE;
	DECLARE current_event_count BIGINT;
	DECLARE current_event_avg BIGINT;
	DECLARE drug_cur CURSOR FOR SELECT drug_id FROM drug_list;
		DECLARE CONTINUE HANDLER FOR NOT FOUND 
		SET no_more_rows1 := TRUE; 
	SET no_more_rows1 := FALSE;
    SET count = (select ifnull(max(spike_id),0) as count  from drug_spikes);
    
    if count=0 then 
    SET max_no := 1;
    else
	SET max_no := (select max(spike_id) from drug_spikes);
    end if;
    
	OPEN drug_cur;
	loop1: LOOP
	FETCH drug_cur INTO current_drug_id;
	IF no_more_rows1 THEN CLOSE drug_cur;
		LEAVE loop1;
	END IF;
	Block2: BEGIN
		DECLARE no_more_rows2 BOOLEAN;
		DECLARE avg_count DOUBLE;
		DECLARE median DOUBLE;
		DECLARE cur CURSOR FOR SELECT start_date, SUM(event_count) event_count, AVG(event_count) event_avg FROM drug_summary_month dg WHERE drug_id=current_drug_id AND event_count > median GROUP BY dg.start_date ORDER BY dg.event_count DESC;
		DECLARE CONTINUE HANDLER FOR NOT FOUND
		SET no_more_rows2 := TRUE;
		SELECT 
    AVG(event_count)
INTO @avg_count FROM
    drug_summary dg2
WHERE
    dg2.drug_id = current_drug_id;
		SET median := @avg_count + @avg_count * delta_n; 
		OPEN cur;
		loop2: LOOP
		FETCH cur INTO current_start_date,current_event_count,current_event_avg;
		IF no_more_rows2 THEN
			CLOSE cur;
			LEAVE loop2;
		END IF;	
		SET max_no := max_no + 1;
		INSERT INTO drug_spikes (drug_id,spike_date,event_count,spike_id,drug_event_avg,drug_event_threshold) VALUES (current_drug_id,current_start_date,current_event_count,max_no,@avg_count,median);
		END LOOP loop2;
		COMMIT;
	END Block2;
	END LOOP loop1;
	END Block1$$

DELIMITER ;

