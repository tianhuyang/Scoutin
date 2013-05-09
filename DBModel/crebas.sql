/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     5/9/2013 4:20:29 AM                          */
/*==============================================================*/


drop index TWITTER_ID_UNIQUE on ACCOUNT;

drop index FACEBOOK_ID_UNIQUE on ACCOUNT;

drop index EMAIL_UNIQUE on ACCOUNT;

drop table if exists ACCOUNT;

drop table if exists ACCOUNT_CLUSTER;

drop table if exists ACCOUNT_STAT;

drop table if exists ALBUM;

drop table if exists ALBUM_CARD;

drop table if exists BLOCKED_ALBUM;

drop table if exists CARD;

drop index CARDBODYCREATEDBYACCOUNT_FK on CARD_BODY;

drop table if exists CARD_BODY;

drop table if exists CARD_CATEGORY;

drop table if exists CARD_ENDORSE;

drop table if exists CARD_REPOST;

drop table if exists CATEGORY;

drop table if exists CLUSTER;

drop table if exists COMMENT;

drop table if exists FOLLOWER;

drop table if exists MESSAGE;

drop table if exists NOTIFICATION;

drop table if exists PROFILE;

drop table if exists RECOMMENDATION;

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
   CREATED_TIME         datetime not null,
   UPDATED_TIME         datetime not null,
   FIRSTNAME            varchar(35) not null,
   LASTNAME             varchar(35) not null,
   SEX                  tinyint not null,
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
/* Table: ACCOUNT_CLUSTER                                       */
/*==============================================================*/
create table ACCOUNT_CLUSTER
(
   CLUSTER_ID           bigint not null default 0,
   ACCOUNT_ID           int not null,
   CREATED_TIME         datetime not null,
   primary key (CLUSTER_ID, ACCOUNT_ID)
);

/*==============================================================*/
/* Table: ACCOUNT_STAT                                          */
/*==============================================================*/
create table ACCOUNT_STAT
(
   ACCOUNT_ID           int not null auto_increment,
   FOLLOWING_COUNT      int not null default 0,
   FOLLOWERS_COUNT      int not null default 0,
   LAST_RECMD_VIEW      datetime,
   UNVIEW_RECMD_COUNT   int not null default 0,
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
   CREATED_TIME         datetime not null,
   UPDATED_TIME         datetime not null,
   FOLLOW_COUNT         int not null default 0,
   primary key (ALBUM_ID)
);

/*==============================================================*/
/* Table: ALBUM_CARD                                            */
/*==============================================================*/
create table ALBUM_CARD
(
   ALBUM_ID             bigint not null,
   CARD_ID              bigint not null,
   primary key (ALBUM_ID, CARD_ID)
);

/*==============================================================*/
/* Table: BLOCKED_ALBUM                                         */
/*==============================================================*/
create table BLOCKED_ALBUM
(
   ALBUM_ID             bigint not null,
   FOLLOWED_ID          int not null,
   FOLLOWING_ID         int not null,
   CREATED_TIME         datetime not null,
   primary key (ALBUM_ID, FOLLOWED_ID, FOLLOWING_ID)
);

/*==============================================================*/
/* Table: CARD                                                  */
/*==============================================================*/
create table CARD
(
   CARD_ID              bigint not null auto_increment,
   CARD_BODY_ID         bigint not null,
   DESCRIPTION          varchar(255),
   RATING               int not null default 0,
   COMMENTS_COUNT       int not null default 0,
   ENDORSES_COUNT       int not null default 0,
   CREATED_TIME         datetime not null,
   UPDATED_TIME         datetime not null,
   TAG                  text,
   primary key (CARD_ID)
);

/*==============================================================*/
/* Table: CARD_BODY                                             */
/*==============================================================*/
create table CARD_BODY
(
   CARD_BODY_ID         bigint not null auto_increment,
   ACCOUNT_ID           int not null,
   COMMENTS_COUNT       int not null default 0,
   REPOSTS_COUNT        int not null default 0,
   ENDORSES_COUNT       int not null default 0,
   CREATED_TIME         datetime not null,
   UPDATED_TIME         datetime not null,
   LATITUDE             decimal(10,6),
   LONGITUDE            decimal(10,6),
   ADDRESS              varchar(255),
   URL                  text not null,
   TITLE                varchar(35) not null,
   primary key (CARD_BODY_ID)
);

/*==============================================================*/
/* Index: CARDBODYCREATEDBYACCOUNT_FK                           */
/*==============================================================*/
create index CARDBODYCREATEDBYACCOUNT_FK on CARD_BODY
(
   ACCOUNT_ID
);

/*==============================================================*/
/* Table: CARD_CATEGORY                                         */
/*==============================================================*/
create table CARD_CATEGORY
(
   CATEGORY_ID          smallint not null,
   CARD_ID              bigint not null,
   primary key (CATEGORY_ID, CARD_ID)
);

/*==============================================================*/
/* Table: CARD_ENDORSE                                          */
/*==============================================================*/
create table CARD_ENDORSE
(
   ACCOUNT_ID           int not null,
   CARD_ID              bigint not null,
   CREATED_TIME         datetime not null,
   primary key (ACCOUNT_ID, CARD_ID)
);

/*==============================================================*/
/* Table: CARD_REPOST                                           */
/*==============================================================*/
create table CARD_REPOST
(
   CARD_BODY_ID         bigint not null,
   ACCOUNT_ID           int not null,
   COUNT                int not null default 1,
   primary key (CARD_BODY_ID, ACCOUNT_ID)
);

/*==============================================================*/
/* Table: CATEGORY                                              */
/*==============================================================*/
create table CATEGORY
(
   CATEGORY_ID          smallint not null auto_increment,
   NAME                 varchar(35),
   primary key (CATEGORY_ID)
);

/*==============================================================*/
/* Table: CLUSTER                                               */
/*==============================================================*/
create table CLUSTER
(
   OWNER_ID             int not null,
   NAME                 varchar(50) not null,
   CREATED_TIME         datetime not null,
   UPDATED_TIME         datetime not null,
   CLUSTER_ID           bigint not null auto_increment,
   primary key (CLUSTER_ID)
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
   UPDATED_TIME         datetime not null,
   CREATED_TIME         datetime not null,
   primary key (COMMENT_ID)
);

/*==============================================================*/
/* Table: FOLLOWER                                              */
/*==============================================================*/
create table FOLLOWER
(
   FOLLOWED_ID          int not null,
   FOLLOWING_ID         int not null,
   CREATED_TIME         datetime not null,
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
   CREATED_TIME         datetime not null,
   UPDATED_TIME         datetime not null,
   primary key (ACCOUNT_ID)
);

/*==============================================================*/
/* Table: RECOMMENDATION                                        */
/*==============================================================*/
create table RECOMMENDATION
(
   ACCOUNT_ID           int not null,
   CARD_ID              bigint not null,
   CREATED_TIME         datetime not null,
   primary key (ACCOUNT_ID, CARD_ID)
);

alter table ACCOUNT_CLUSTER add constraint FK_ACCOUNTBELONGTOCLUSTER foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete restrict on update restrict;

alter table ACCOUNT_CLUSTER add constraint FK_CLUSTERHAVEACCOUNT foreign key (CLUSTER_ID)
      references CLUSTER (CLUSTER_ID) on delete restrict on update restrict;

alter table ACCOUNT_STAT add constraint FK_ACCOUNTOWNSTAT foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table ALBUM add constraint FK_ACCOUNTHAVEALBUM foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table ALBUM_CARD add constraint FK_ALBUMHAVECARD foreign key (ALBUM_ID)
      references ALBUM (ALBUM_ID) on delete cascade on update cascade;

alter table ALBUM_CARD add constraint FK_CARDOWNEDBYALBUM foreign key (CARD_ID)
      references CARD (CARD_ID) on delete cascade on update cascade;

alter table BLOCKED_ALBUM add constraint FK_ALBUMFOLLOWED foreign key (ALBUM_ID)
      references ALBUM (ALBUM_ID) on delete cascade on update cascade;

alter table BLOCKED_ALBUM add constraint FK_FOLLOWERFOLLOWALBUM foreign key (FOLLOWED_ID, FOLLOWING_ID)
      references FOLLOWER (FOLLOWED_ID, FOLLOWING_ID) on delete cascade on update cascade;

alter table CARD add constraint FK_CARDBODYHAVECARD foreign key (CARD_BODY_ID)
      references CARD_BODY (CARD_BODY_ID) on delete cascade on update restrict;

alter table CARD_BODY add constraint FK_CARDBODYCREATEDBYACCOUNT foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table CARD_CATEGORY add constraint FK_CARDBELONGCATEGORY foreign key (CARD_ID)
      references CARD (CARD_ID) on delete cascade on update cascade;

alter table CARD_CATEGORY add constraint FK_CATEGORYHAVECARD foreign key (CATEGORY_ID)
      references CATEGORY (CATEGORY_ID) on delete cascade on update cascade;

alter table CARD_ENDORSE add constraint FK_ACCOUNTLIKECARD foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table CARD_ENDORSE add constraint FK_CARDLIKEDBYACCOUNT foreign key (CARD_ID)
      references CARD (CARD_ID) on delete cascade on update cascade;

alter table CARD_REPOST add constraint FK_CARDHAVEREPOSTS foreign key (CARD_BODY_ID)
      references CARD_BODY (CARD_BODY_ID) on delete cascade on update cascade;

alter table CARD_REPOST add constraint FK_CARDREPOSTEDBYACCOUNT foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete cascade on update cascade;

alter table CLUSTER add constraint FK_ACCOUNTHAVEGROUPS foreign key (OWNER_ID)
      references ACCOUNT (ACCOUNT_ID) on delete restrict on update restrict;

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

alter table RECOMMENDATION add constraint FK_ACCOUNTHAVERECOMMENDATIONS foreign key (ACCOUNT_ID)
      references ACCOUNT (ACCOUNT_ID) on delete restrict on update cascade;

alter table RECOMMENDATION add constraint FK_CARDHAVERECOMMENDATIONS foreign key (CARD_ID)
      references CARD (CARD_ID) on delete restrict on update cascade;

