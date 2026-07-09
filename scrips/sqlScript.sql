create table if not exists tb_borrower(
    id serial primary key,
    name varchar(255),
    email_address varchar(255)
);


create table if not exists tb_book(
    id serial primary key,
    isbn_number varchar(13),
    title varchar(255),
    author varchar(255),
    date_borrowed date,
    date_returned date
);