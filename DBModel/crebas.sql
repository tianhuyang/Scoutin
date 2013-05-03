/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     4/28/2013 11:51:52 PM                        */
/*==============================================================*/


drop index TWITTER_ID_UNIQUE on ACCOUNT;

drop index FACEBOOK_ID_UNIQUE on ACCOUNT;

drop index EMAIL_UNIQUE on ACCOUNT;

drop table if exists ACCOUNT;

drop table if exists ACCOUNTSTAT;

drop table if exists ALBUM;

drop table if exists ALBUMCARD;

drop table if exists BLOCKEDALBUM;

drop table if exists CARD;

drop index CARDBODYCREATEDBYACCOUNT_FK on CARDBODY;

drop table if exists CARDBODY;

drop table if exists CARDCATEGORY;

drop table if exists CARDLIKE;

drop table if exists CARDREPOST;

drop table if exists CATEGORY;

drop table if exists COMMENT;

drop table if exists FOLLOWER;

drop table if exists MESSAGE;

drop table if exists NOTIFICATION;

drop table if exists PROFILE;

/*==============================================================*/
/* Table: ACCOUNT                                               */
/*==============================================================*/
create table ACCOUNT
(
   ACCOUNT_ID           int not null auto_increment,
   PASSWORD             char(15) not null,
   EMAIL                varchar(255),
   FACEBOOK_ID          varchar(20),
   TWITTER_ID           varchar(20),
   CREATED_TIME         timestamp default '0000-00-00 00:00:00',
   UPDATED_TIME         timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   FIRSTNAME            varchar(35) not null,
   LASTNAME             varchar(35) not null,
   SEX                  tinyint not null,
   VERSION              bigint not null default 0,
   primary key (ACCOUNT_ID)
);

/*==============================================================*/
/* Index: EMAIL_UNIQUE                                          */
/*==============================================================*/
create unique index EMAIL_UNIQUE on ACCOUNT
(
   EMAIL
);

/*==============================================================*/
/* Index: FACEBOOK_ID_UNIQUE                                    */
/*==============================================================*/
create unique index FACEBOOK_ID_UNIQUE on ACCOUNT
(
   FACEBOOK_ID
);

/*==============================================================*/
/* Index: TWITTER_ID_UNIQUE                                     */
/*==============================================================*/
create unique index TWITTER_ID_UNIQUE on ACCOUNT
(
   TWITTER_ID
);

/*==============================================================*/
/* Table: ACCOUNTSTAT                                           */
/*==============================================================*/
create table ACCOUNTSTAT
(
   ACCOUNT_ID           int not null auto_increment,
   FOLLOWING_COUNT      int not null default 0,
   FOLLOWERS_COUNT      int not null default 0,
   LAST_RECMD_VIEW      datetime,
   UNVIEW_RECMD_COUNT   int not null default 0,
   VERSION              bigint not null default 0,
   primary key (ACCOUNT_ID)
);

/*==============================================================*/
/* Table: ALBUM                                                 */
/*==============================================================*/
create table ALBUM
(
   ALBUM_ID             bigint not null auto_increment,
   ACCOUNT_ID           int,
   NAME                 varchar(35),
   COVER_PATH           varchar(255),
   CREATED_TIME         timestamp default '0000-00-00 00:00:00',
   UPDATED_TIME         timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   FOLLOW_COUNT         int not null default 0,
   VERSION              bigint not null default 0,
   primary key (ALBUM_ID)
);

/*==============================================================*/
/* Table: ALBUMCARD                                             */
/*==============================================================*/
create table ALBUMCARD
(
   ALBUM_ID             bigint not null,
   CARD_ID              bigint not null,
   primary key (ALBUM_ID, CARD_ID)
);

/*==============================================================*/
/* Table: BLOCKEDALBUM                                          */
/*==============================================================*/
create table BLOCKEDALBUM
(
   ALBUM_ID             bigint not null,
   FOLLOWED_ID          int not null,
   FOLLOWING_ID         int not null,
   CREATED_TIME         timestamp default '0000-00-00 00:00:00',
   primary key (ALBUM_ID, FOLLOWED_ID, FOLLOWING_ID)
);

/*==============================================================*/
/* Table: CARD                                                  */
/*==============================================================*/
create table CARD
(
   CARD_ID              bigint not null auto_increment,
   CARDBODY_ID          bigint not null,
   DESCRIPTION          varchar(255),
   RATING               int,
   COMMENTS_COUNT       int not null default 0,
   LIKES_COUNT          int not null default 0,
   CREATED_TIME         timestamp default '0000-00-00 00:00:00',
   UPDATED_TIME         timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   TAG                  text,
   VERSION              bigint not null default 0,
   RATING_COUNT         int default 0,
   primary key (CARD_ID)
);

/*==============================================================*/
/* Table: CARDBODY                                              */
/*==============================================================*/
create table CARDBODY
(
   CARDBODY_ID          bigint not null auto_increment,
   ACCOUNT_ID           int not null,
   RATING               int,
   COMMENTS_COUNT       int not null default 0,
   REPOSTS_COUNT        int not null default 0,
   LIKES_COUNT          int not null default 0,
   CREATED_TIME         timestamp default '0000-00-00 00:00:00',
   UPDATED_TIME         timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   LATITUDE             decimal(10,6),
   LONGITUDE            decimal(10,6),
   ADDRESS              varchar(255),
   URL                  text,
   TITLE                varchar(35),
   VERSION              bigint not null default 0,
   RATING_COUNT         int default 0,
   primary key (CARDBODY_ID)
);

/*==============================================================*/
/* Index: CARDBODYCREATEDBYACCOUNT_FK                           */
/*==============================================================*/
create index CARDBODYCREATEDBYACCOUNT_FK on CARDBODY
(
   ACCOUNT_ID
);

/*==============================================================*/
/* Table: CARDCATEGORY                                          */
/*==============================================================*/
create table CARDCATEGORY
(
   CATEGORY_ID          smallint not null,
   CARD_ID              bigint not null,
   primary key (CATEGORY_ID, CARD_ID)
);

/*==============================================================*/
/* Table: CARDLIKE                                              */
/*==============================================================*/
create table CARDLIKE
(
   ACCOUNT_ID           int not null,
   CARD_ID              bigint not null,
   primary key (ACCOUNT_ID, CARD_ID)
);

/*==============================================================*/
/* Table: CARDREPOST                                            */
/*==============================================================*/
create table CARDREPOST
(
   CARDBODY_ID          bigint not null,
   ACCOUNT_ID           int not null,
   COUNT                int not null default 1,
   primary key (CARDBODY_ID, ACCOUNT_ID)
);

/*==============================================================*/
/* Table: CATEGORY                                              */
/*==============================================================*/
create table CATEGORY
(
   CATEGORY_ID          smallint not null auto_increment,
   NAME                 varchar(35),
   VERSION              bigint not null default 0,
   primary key (CATEGORY_ID)
);

/*==============================================================*/
/* Table: COMMENT                                               */
/*==============================================================*/
create table COMMENT
(
   COMMENT_ID           bigint not null auto_increment,
   CARD_ID              bigint not null,
   ACCOUNT_ID           int not null,
   CONTENT              varchar(255) not null,
   UPDATED_TIME         timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_TIME         timestamp default '0000-00-00 00:00:00',
   VERSION              bigint not null default 0,
   primary key (COMMENT_ID)
);

/*==============================================================*/
/* Table: FOLLOWER                                              */
/*==============================================================*/
create table FOLLOWER
(
   FOLLOWED_ID          int not null,
   FOLLOWING_ID         int not null,
   CREATED_TIME         timestamp default '0000-00-00 00:00:00',
   primary key (FOLLOWED_ID, FOLLOWING_ID)
);

/*==============================================================*/
/* Table: MESSAGE                                               */
/*==============================================================*/
create table MESSAGE
(
   MESSAGE_ID           bigint not null auto_increment,
   primary key (MESSAGE_ID)
);

/*==============================================================*/
/* Table: NOTIFICATION                                          */
/*==============================================================*/
create table NOTIFICATION
(
   NOTIFICATION_ID      bigint not null auto_increment,
   primary key (NOTIFICATION_ID)
);

/*==============================================================*/
/* Table: PROFILE                                               */
/*==============================================================*/
create table PROFILE
(
   ACCOUNT_ID           int not null auto_increment,
   MIDDLENAME           varchar(35),
   BIRTHDAY             date,
   MOBILE               varchar(20),
   CREATED_TIME         timestamp default '0000-00-00 00:00:00',
   UPDATED_TIME         timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   VERSION              bigint not null default 0,
   primary key (ACCOUNT_ID)
);

alter table ACCOUNTSTAT add constraint FK_ACCOUNTOWNSTAT foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table ALBUM add constraint FK_ACCOUNTHAVEALBUM foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table ALBUMCARD add constraint FK_ALBUMHAVECARD foreign key (ALBUM_ID)
      references ALBUM (ALBUM_ID) on delete cascade on update cascade;

alter table ALBUMCARD add constraint FK_CARDOWNEDBYALBUM foreign key (CARD_ID)
      references CARD (CARD_ID) on delete cascade on update cascade;

alter table BLOCKEDALBUM add constraint FK_ALBUMFOLLOWED foreign key (ALBUM_ID)
      references ALBUM (ALBUM_ID) on delete cascade on update cascade;

alter table BLOCKEDALBUM add constraint FK_FOLLOWERFOLLOWALBUM foreign key (FOLLOWED_ID, FOLLOWING_ID)
      references FOLLOWER (FOLLOWED_ID, FOLLOWING_ID) on delete cascade on update cascade;

alter table CARD add constraint FK_CARDBODYHAVECARD foreign key (CARDBODY_ID)
      references CARDBODY (CARDBODY_ID) on delete cascade on update cascade;

alter table CARDBODY add constraint FK_CARDBODYCREATEDBYACCOUNT foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table CARDCATEGORY add constraint FK_CARDBELONGCATEGORY foreign key (CARD_ID)
      references CARD (CARD_ID) on delete cascade on update cascade;

alter table CARDCATEGORY add constraint FK_CATEGORYHAVECARD foreign key (CATEGORY_ID)
      references CATEGORY (CATEGORY_ID) on delete cascade on update cascade;

alter table CARDLIKE add constraint FK_ACCOUNTLIKECARD foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table CARDLIKE add constraint FK_CARDLIKEDBYACCOUNT foreign key (CARD_ID)
      references CARD (CARD_ID) on delete cascade on update cascade;

alter table CARDREPOST add constraint FK_CARDHAVEREPOSTS foreign key (CARDBODY_ID)
      references CARDBODY (CARDBODY_ID) on delete cascade on update cascade;

alter table CARDREPOST add constraint FK_CARDREPOSTEDBYACCOUNT foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table COMMENT add constraint FK_CARDHAVECOMMENT foreign key (CARD_ID)
      references CARD (CARD_ID) on delete cascade on update cascade;

alter table COMMENT add constraint FK_COMMENTACCOUNT foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table FOLLOWER add constraint FK_FOLLOWED foreign key (FOLLOWED_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table FOLLOWER add constraint FK_FOLLOWING foreign key (FOLLOWING_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table PROFILE add constraint FK_ACCOUNTOWNPROFILE foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

