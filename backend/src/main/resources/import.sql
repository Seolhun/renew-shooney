CREATE USER 'developer'@'127.0.0.1' IDENTIFIED BY 'developer@';

CREATE DATABASE shooney_blog;

GRANT DROP ON shooney_blog.* to 'developer'@'127.0.0.1';
GRANT CREATE ON shooney_blog.* to 'developer'@'127.0.0.1';
GRANT ALTER ON shooney_blog.* to 'developer'@'127.0.0.1';
GRANT SELECT ON shooney_blog.* to 'developer'@'127.0.0.1';
GRANT INSERT ON shooney_blog.* to 'developer'@'127.0.0.1';
GRANT UPDATE ON shooney_blog.* to 'developer'@'127.0.0.1';
GRANT DELETE ON shooney_blog.* to 'developer'@'127.0.0.1';

CREATE DATABASE shooney_common;

GRANT DROP ON shooney_common.* to 'developer'@'127.0.0.1';
GRANT CREATE ON shooney_common.* to 'developer'@'127.0.0.1';
GRANT ALTER ON shooney_common.* to 'developer'@'127.0.0.1';
GRANT SELECT ON shooney_common.* to 'developer'@'127.0.0.1';
GRANT INSERT ON shooney_common.* to 'developer'@'127.0.0.1';
GRANT UPDATE ON shooney_common.* to 'developer'@'127.0.0.1';
GRANT DELETE ON shooney_common.* to 'developer'@'127.0.0.1';

flush PRIVILEGES;