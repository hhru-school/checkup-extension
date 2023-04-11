SELECT 'CREATE DATABASE test'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'test')\gexec

create table if not exists test_table (
    id primary key,
    taskdata varchar(100) not null,
);
