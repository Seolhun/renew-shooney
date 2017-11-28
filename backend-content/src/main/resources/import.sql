CREATE USER 'developer'@'127.0.0.1' IDENTIFIED BY 'developer@';

CREATE DATABASE shooney_content;

GRANT DROP ON shooney_content.* TO 'developer'@'127.0.0.1';
GRANT CREATE ON shooney_content.* TO 'developer'@'127.0.0.1';
GRANT ALTER ON shooney_content.* TO 'developer'@'127.0.0.1';
GRANT SELECT ON shooney_content.* TO 'developer'@'127.0.0.1';
GRANT INSERT ON shooney_content.* TO 'developer'@'127.0.0.1';
GRANT UPDATE ON shooney_content.* TO 'developer'@'127.0.0.1';
GRANT DELETE ON shooney_content.* TO 'developer'@'127.0.0.1';

FLUSH PRIVILEGES;

CREATE DATABASE shooney_spam;

GRANT DROP ON shooney_spam.* TO 'developer'@'127.0.0.1';
GRANT CREATE ON shooney_spam.* TO 'developer'@'127.0.0.1';
GRANT ALTER ON shooney_spam.* TO 'developer'@'127.0.0.1';
GRANT SELECT ON shooney_spam.* TO 'developer'@'127.0.0.1';
GRANT INSERT ON shooney_spam.* TO 'developer'@'127.0.0.1';
GRANT UPDATE ON shooney_spam.* TO 'developer'@'127.0.0.1';
GRANT DELETE ON shooney_spam.* TO 'developer'@'127.0.0.1';

FLUSH PRIVILEGES;