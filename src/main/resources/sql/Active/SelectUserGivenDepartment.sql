SELECT *
FROM user_info
WHERE deleted = FALSE
AND id IN
(
	SELECT user_info_id
	FROM instructor_info
	WHERE department = :department
);