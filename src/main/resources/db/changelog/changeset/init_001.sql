create table if not exists students
(
    id           serial primary key,
    name         varchar(255) not null,
    phone_number varchar(255) not null,
    status       boolean   default true,
    created_date timestamp default now()
);

create table if not exists teachers
(
    id           serial primary key,
    name         varchar(255) not null,
    created_date timestamp default now()
);

CREATE TABLE IF NOT EXISTS course
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255)     NOT NULL,
    price        DOUBLE PRECISION NOT NULL,
    teacher_id   BIGINT           NOT NULL,
    status       BOOLEAN   DEFAULT TRUE,
    created_date TIMESTAMP DEFAULT NOW()
);


create table if not exists enrollment
(
    id           serial primary key,
    user_id      bigint not null,
    course_id    bigint not null,
    created_date timestamp default now()
);

create table if not exists marks
(
    id           serial primary key,
    user_id      bigint not null,
    course_id    bigint not null,
    mark         int    not null,
    created_date timestamp default now()
)
