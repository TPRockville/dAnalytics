DROP procedure IF EXISTS `sp_GetERSummary`;
DELIMITER $$
CREATE PROCEDURE `sp_GetERSummary`()
BEGIN
insert into er_summary(
select id, year,UPPER(episode_disease_category) as episode_disease_category,sum(Total_ER_Visits) as Total_ER_Visits
from  medicaid group by episode_disease_category,year);
END$$
DELIMITER ;
