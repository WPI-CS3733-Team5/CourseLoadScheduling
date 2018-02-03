SELECT *
FROM section_info
WHERE deleted = FALSE
AND calendar_info_id IN 
(
    SELECT id
    FROM calendar_info
    WHERE term = :term
);