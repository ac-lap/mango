# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account_details (
  user_id                   varchar(255) not null,
  email_id                  varchar(255),
  password                  varchar(255),
  constraint pk_account_details primary key (user_id))
;

create table user (
  user_id                   varchar(255) not null,
  acc_details_user_id       varchar(255),
  constraint pk_user primary key (user_id))
;

alter table user add constraint fk_user_accDetails_1 foreign key (acc_details_user_id) references account_details (user_id) on delete restrict on update restrict;
create index ix_user_accDetails_1 on user (acc_details_user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account_details;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

