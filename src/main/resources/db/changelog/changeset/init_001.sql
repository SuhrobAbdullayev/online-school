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

create table if not exists course
(
    id           serial primary key,
    name         varchar(255) not null,
    price        boolean      not null,
    teacher_id   bigint       not null,
    status       boolean   default true,
    created_date timestamp default now()
    );

create table if not exists enrollment
(
    id           serial primary key,
    student_id   bigint not null,
    course_id    bigint not null,
    created_date timestamp default now()
    );

create table if not exists marks
(
    id           serial primary key,
    student_id   bigint not null,
    course_id    bigint not null,
    mark         int    not null,
    created_date timestamp default now()
    )
