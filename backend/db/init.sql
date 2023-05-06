SELECT 'CREATE DATABASE checkup_extension'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'checkup_extension')\gexec

create table if not exists problem (
    id serial primary key,
    type smallint,
    title varchar(255),
    description varchar(255),
    content varchar(255),
    active boolean,
    template jsonb
);

create table if not exists submission (
    id serial primary key,
    solution varchar(255),
    status smallint,
    user_id bigint,
    problem_id bigint,

    CONSTRAINT fk_problem
        FOREIGN KEY (problem_id)
        REFERENCES problem(id)
);

create table if not exists verification (
    id serial primary key,
    content varchar(255),
    problem_id bigint,

    CONSTRAINT fk_problem
        FOREIGN KEY (problem_id)
        REFERENCES problem(id)
);