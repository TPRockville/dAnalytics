DELIMITER $$
CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `drug_quarter_test`.`populateDurgBasedPeak`(IN delta_n INT)
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    Block1: BEGIN
	DECLARE current_max_id INT;
	DECLARE no_more_rows1 BOOLEAN;
	DECLARE current_drug_id BIGINT;
	DECLARE current_start_date DATE;
	DECLARE current_event_count BIGINT;
	/*SET no_more_rows1 := FALSE; */
	DECLARE drug_cur CURSOR FOR SELECT drug_id FROM drug_list;
		DECLARE CONTINUE HANDLER FOR NOT FOUND 
		SET no_more_rows1 := TRUE; 
	OPEN drug_cur;
	loop1: LOOP
	FETCH drug_cur INTO current_drug_id;
	IF no_more_rows1 THEN CLOSE drug_cur;
		LEAVE LOOP1;
	END IF;
	Block2: BEGIN
		DECLARE no_more_rows2 BOOLEAN;
		DECLARE cur CURSOR FOR SELECT start_date, SUM(event_count) event_count FROM drug_summary WHERE drug_id=current_drug_id AND event_count > (SELECT AVG(event_count)*delta_n+AVG(event_count) average_events FROM drug_summary WHERE drug_id=current_drug_id) GROUP BY start_date ORDER BY event_count DESC;
		DECLARE CONTINUE HANDLER FOR NOT FOUND
		SET no_more_rows2 := TRUE;
		OPEN cur;
		loop2: LOOP
		FETCH cur INTO current_start_date,current_event_count;
		IF no_more_rows THEN
			CLOSE cur;
			LEAVE loop2;
		END IF;	
		INSERT INTO drug_spikes VALUES (current_drug_id,current_start_date,current_event_count);
		CLOSE cur;
		END LOOP loop2;
	END Block2;
	CLOSE drug_cur ;
	END LOOP loop1;
	END Block1;
