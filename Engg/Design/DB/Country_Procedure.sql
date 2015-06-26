DROP procedure IF EXISTS `sp_populateCountryName`;
DELIMITER $$
CREATE PROCEDURE `sp_populateCountryName`()
BEGIN
update country_list,(select * from (select cl.country_id,cl.country_code as country_code_list,cl.country_name as country_name_list,
cc.id,cc.country_name,cc.country_code
from country_list cl left join country_code cc on 
cl.country_code=cc.country_code) as result)code set country_list.country_name=
( 
case when char_length(code.country_code_list)=2 then CONCAT(UCASE(LEFT(code.country_name, 1)), LCASE(SUBSTRING(code.country_name, 2)))
end) where country_list.country_code=code.country_code;

update country_list set country_list.country_name=CONCAT(UCASE(LEFT(country_list.country_code, 1)), LCASE(SUBSTRING(country_list.country_code, 2)))
where char_length(country_list.country_code)>2;
END$$
DELIMITER ;