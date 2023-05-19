SELECT 'CREATE DATABASE checkup_extension'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'checkup_extension')\gexec

\c checkup_extension;

create table if not exists problem (
    problem_id serial primary key,
    type smallint,
    max_attempts smallint,
    title varchar(255),
    description varchar(255),
    content varchar(255),
    active boolean,
    template jsonb
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
    content jsonb,
    problem_id bigint,

    CONSTRAINT verification_problem_fk
        FOREIGN KEY (problem_id)
        REFERENCES problem(problem_id)
);
