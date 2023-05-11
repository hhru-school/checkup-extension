SELECT 'CREATE DATABASE checkup_extension'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'checkup_extension')\gexec

create table if not exists problem (
    problem_id serial primary key,
    condition varchar(255),
    max_attempts smallint,
    type smallint
);

create table if not exists submission (
    submission_id serial primary key,
    user_id bigint,
    problem_id bigint,
    status smallint,
    creation_datetime timestamp,
    solution jsonb,

    CONSTRAINT submission_problem_fk
        FOREIGN KEY (problem_id)
        REFERENCES problem(problem_id)
);

create table if not exists verification (
    id serial primary key,
    content varchar(255),
    problem_id bigint,

    CONSTRAINT verification_problem_fk
        FOREIGN KEY (problem_id)
        REFERENCES problem(problem_id)
);
