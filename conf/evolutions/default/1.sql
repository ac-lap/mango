# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account_details (
  user_id                   varchar(255) not null,
  email_id                  varchar(255),
  password                  varchar(255),
  constraint pk_account_details primary key (user_id))
;

create table book (
  book_id                   varchar(255) not null,
  rate                      integer,
  constraint pk_book primary key (book_id))
;

create table book_details (
  book_id                   varchar(255) not null,
  title                     varchar(255),
  isbn                      varchar(255),
  num_pages                 integer,
  summary                   varchar(255),
  img_location              varchar(255),
  book_location             varchar(255),
  constraint pk_book_details primary key (book_id))
;

create table book_id (
  end_id                    varchar(255) not null,
  constraint pk_book_id primary key (end_id))
;

create table book_review (
  book_id                   varchar(255) not null,
  avg_rating                double,
  count                     integer,
  constraint pk_book_review primary key (book_id))
;

create table credit (
  user_id                   varchar(255) not null,
  coin                      integer,
  constraint pk_credit primary key (user_id))
;

create table open_book (
  user_id                   varchar(255) not null,
  book_id                   varchar(255),
  page_num                  integer,
  sec_left                  integer,
  constraint pk_open_book primary key (user_id))
;

create table review (
  book_id                   varchar(255) not null,
  user_id                   varchar(255),
  review                    varchar(255),
  review_date               datetime,
  rating                    double,
  constraint pk_review primary key (book_id))
;

create table user (
  user_id                   varchar(255) not null,
  constraint pk_user primary key (user_id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account_details;

drop table book;

drop table book_details;

drop table book_id;

drop table book_review;

drop table credit;

drop table open_book;

drop table review;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

