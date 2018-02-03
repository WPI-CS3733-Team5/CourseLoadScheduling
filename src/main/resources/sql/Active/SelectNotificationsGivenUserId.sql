SELECT *
FROM notifications
WHERE to_user_info_id IN
(
	SELECT id
	FROM user_info
	WHERE username = :username
	AND deleted = false;
);