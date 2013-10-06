# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account_details (
  user_id                   varchar(255) not null,
  email_id                  varchar(255),
  password                  varchar(255),
  constraint pk_account_details primary key (user_id))
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

create table user (
  user_id                   varchar(255) not null,
  constraint pk_user primary key (user_id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account_details;

drop table credit;

drop table open_book;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

