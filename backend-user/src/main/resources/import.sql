CREATE USER 'developer'@'127.0.0.1'
  IDENTIFIED BY 'developer@';

CREATE DATABASE shooney_user;

GRANT DROP ON shooney_user.* TO 'developer'@'127.0.0.1';
GRANT CREATE ON shooney_user.* TO 'developer'@'127.0.0.1';
GRANT ALTER ON shooney_user.* TO 'developer'@'127.0.0.1';
GRANT SELECT ON shooney_user.* TO 'developer'@'127.0.0.1';
GRANT INSERT ON shooney_user.* TO 'developer'@'127.0.0.1';
GRANT UPDATE ON shooney_user.* TO 'developer'@'127.0.0.1';
GRANT DELETE ON shooney_user.* TO 'developer'@'127.0.0.1';

CREATE DATABASE shooney_pay;

GRANT DROP ON shooney_pay.* TO 'developer'@'127.0.0.1';
GRANT CREATE ON shooney_pay.* TO 'developer'@'127.0.0.1';
GRANT ALTER ON shooney_pay.* TO 'developer'@'127.0.0.1';
GRANT SELECT ON shooney_pay.* TO 'developer'@'127.0.0.1';
GRANT INSERT ON shooney_pay.* TO 'developer'@'127.0.0.1';
GRANT UPDATE ON shooney_pay.* TO 'developer'@'127.0.0.1';
GRANT DELETE ON shooney_pay.* TO 'developer'@'127.0.0.1';

FLUSH PRIVILEGES;