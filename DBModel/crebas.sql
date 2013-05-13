/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     5/12/2013 6:43:39 PM                         */
/*==============================================================*/


drop index CREATED_TIME_INDEX on ACCOUNT;

drop index UPDATED_TIME_INDEX on ACCOUNT;

drop index TWITTER_ID_UNIQUE on ACCOUNT;

drop index FACEBOOK_ID_UNIQUE on ACCOUNT;

drop index EMAIL_UNIQUE on ACCOUNT;

drop table if exists ACCOUNT;

drop index CREATED_TIME_INDEX on ACCOUNT_CLUSTER;

drop table if exists ACCOUNT_CLUSTER;

drop table if exists ACCOUNT_STAT;

drop index CREATED_TIME_INDEX on ALBUM;

drop index UPDATED_TIME_INDEX on ALBUM;

drop table if exists ALBUM;

drop table if exists ALBUM_CARD;

drop index CREATED_TIME_INDEX on BLOCKED_ALBUM;

drop table if exists BLOCKED_ALBUM;

drop index CREATED_TIME_INDEX on CARD;

drop index UPDATED_TIME_INDEX on CARD;

drop table if exists CARD;

drop index CREATED_TIME_INDEX on CARD_BODY;

drop index UPDATED_TIME_INDEX on CARD_BODY;

drop index CARDBODYCREATEDBYACCOUNT_FK on CARD_BODY;

drop table if exists CARD_BODY;

drop table if exists CARD_CATEGORY;

drop index CREATED_TIME_INDEX on CARD_ENDORSE;

drop table if exists CARD_ENDORSE;

drop table if exists CARD_REPOST;

drop table if exists CATEGORY;

drop index CREATED_TIME_INDEX on CLUSTER;

drop index UPDATED_TIME_INDEX on CLUSTER;

drop table if exists CLUSTER;

drop index CREATED_TIME_INDEX on COMMENT;

drop index UPDATED_TIME_INDEX on COMMENT;

drop table if exists COMMENT;

drop index CREATED_TIME_INDEX on FOLLOWER;

drop table if exists FOLLOWER;

drop table if exists MESSAGE;

drop table if exists NOTIFICATION;

drop index CREATED_TIME_INDEX on PROFILE;

drop index UPDATED_TIME_INDEX on PROFILE;

drop table if exists PROFILE;

drop index CREATED_TIME_INDEX on RECOMMENDATION;

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
   FULL_NAME            varchar(70) not null,
   SEX                  tinyint not null default 0,
   STATUS               tinyint not null default 0,
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
/* Index: UPDATED_TIME_INDEX                                    */
/*==============================================================*/
create index UPDATED_TIME_INDEX on ACCOUNT
(
   UPDATED_TIME
);

/*==============================================================*/
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on ACCOUNT
(
   CREATED_TIME
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
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on ACCOUNT_CLUSTER
(
   CREATED_TIME
);

/*==============================================================*/
/* Table: ACCOUNT_STAT                                          */
/*==============================================================*/
create table ACCOUNT_STAT
(
   ACCOUNT_ID           int not null auto_increment,
   FOLLOWING_COUNT      int not null default 0,
   FOLLOWERS_COUNT      int not null default 0,
   LAST_NEWS_VISITED    datetime,
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
/* Index: UPDATED_TIME_INDEX                                    */
/*==============================================================*/
create index UPDATED_TIME_INDEX on ALBUM
(
   UPDATED_TIME
);

/*==============================================================*/
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on ALBUM
(
   CREATED_TIME
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
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on BLOCKED_ALBUM
(
   CREATED_TIME
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
   STATUS               tinyint not null default 0,
   primary key (CARD_ID)
);

/*==============================================================*/
/* Index: UPDATED_TIME_INDEX                                    */
/*==============================================================*/
create index UPDATED_TIME_INDEX on CARD
(
   UPDATED_TIME
);

/*==============================================================*/
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on CARD
(
   CREATED_TIME
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
/* Index: UPDATED_TIME_INDEX                                    */
/*==============================================================*/
create index UPDATED_TIME_INDEX on CARD_BODY
(
   UPDATED_TIME
);

/*==============================================================*/
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on CARD_BODY
(
   CREATED_TIME
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
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on CARD_ENDORSE
(
   CREATED_TIME
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
/* Index: UPDATED_TIME_INDEX                                    */
/*==============================================================*/
create index UPDATED_TIME_INDEX on CLUSTER
(
   UPDATED_TIME
);

/*==============================================================*/
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on CLUSTER
(
   CREATED_TIME
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
/* Index: UPDATED_TIME_INDEX                                    */
/*==============================================================*/
create index UPDATED_TIME_INDEX on COMMENT
(
   UPDATED_TIME
);

/*==============================================================*/
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on COMMENT
(
   CREATED_TIME
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
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on FOLLOWER
(
   CREATED_TIME
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
/* Index: UPDATED_TIME_INDEX                                    */
/*==============================================================*/
create index UPDATED_TIME_INDEX on PROFILE
(
   UPDATED_TIME
);

/*==============================================================*/
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on PROFILE
(
   CREATED_TIME
);

/*==============================================================*/
/* Table: RECOMMENDATION                                        */
/*==============================================================*/
create table RECOMMENDATION
(
   RECOMMENDATION_ID    bigint not null auto_increment,
   IS_VIEWED            tinyint not null default 0,
   ACCOUNT_ID           int not null,
   CARD_ID              bigint not null,
   CREATED_TIME         datetime not null,
   primary key (RECOMMENDATION_ID)
);

/*==============================================================*/
/* Index: CREATED_TIME_INDEX                                    */
/*==============================================================*/
create index CREATED_TIME_INDEX on RECOMMENDATION
(
   CREATED_TIME
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

