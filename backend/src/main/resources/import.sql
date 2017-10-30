CREATE DATABASE shooney;

CREATE USER 'developer'@'127.0.0.1' IDENTIFIED BY 'developer@';

GRANT SELECT ON shooney.* to 'developer'@'127.0.0.1';
GRANT INSERT ON shooney.* to 'developer'@'127.0.0.1';
GRANT UPDATE ON shooney.* to 'developer'@'127.0.0.1';
GRANT DELETE ON shooney.* to 'developer'@'127.0.0.1';


CREATE DATABASE shooney_common;

CREATE USER 'developer'@'127.0.0.1' IDENTIFIED BY 'developer@';

GRANT SELECT ON shooney_common.* to 'developer'@'127.0.0.1';
GRANT INSERT ON shooney_common.* to 'developer'@'127.0.0.1';
GRANT UPDATE ON shooney_common.* to 'developer'@'127.0.0.1';
GRANT DELETE ON shooney_common.* to 'developer'@'127.0.0.1';

flush PRIVILEGES;