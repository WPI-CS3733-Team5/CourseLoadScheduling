SELECT *
FROM user_info
WHERE deleted = FALSE 
AND username = :username;