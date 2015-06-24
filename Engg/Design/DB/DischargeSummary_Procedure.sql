DROP procedure IF EXISTS `sp_GetDischargeSummary`;
DELIMITER $$
CREATE PROCEDURE `sp_GetDischargeSummary` ()
BEGIN
insert into discharge_summary (select id,discharge_year,apr_drg_description,count(*) as total_count from hospital_discharge_details
group by discharge_year,apr_drg_description);
END$$
DELIMITER ;