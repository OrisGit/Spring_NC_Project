create table PHARM_TER_GROUPS
(
  ID RAW(255) not null
    primary key,
  NAME VARCHAR2(255 char) not null,
  DESCRIPTION VARCHAR2(255 char)
)
/

create table PRODUCERS
(
  ID RAW(255) not null
    primary key,
  NAME VARCHAR2(255 char) default NULL not null,
  DESCRIPTION VARCHAR2(255 char)
)
/

create table DRUGSTORES
(
  DRUGSTORE_ID RAW(255) not null
    primary key,
  ADDRESS_BUILDING VARCHAR2(255 char),
  ADDRESS_DISTRICT VARCHAR2(255 char),
  IS_ROUND_THE_CLOCK NUMBER(5),
  SNAME VARCHAR2(255 char) not null,
  PHONE VARCHAR2(255 char) default NULL not null,
  ADDRESS_STREET VARCHAR2(255 char),
  WORKING_HOURS VARCHAR2(255 char)
)
/

create table DRUGSTORES
(
  DRUGSTORE_ID RAW(255) not null
    primary key,
  ADDRESS_BUILDING VARCHAR2(255 char),
  ADDRESS_DISTRICT VARCHAR2(255 char),
  IS_ROUND_THE_CLOCK NUMBER(5),
  NAME VARCHAR2(255 char) not null,
  PHONE VARCHAR2(255 char) default NULL  not null,
  ADDRESS_STREET VARCHAR2(255 char),
  WORKING_HOURS VARCHAR2(255 char)
)
/


create table DRUGS
(
  DRUG_ID RAW(255) not null
    primary key,
  NAME VARCHAR2(255 char) not null,
  RELEASE_FORM VARCHAR2(255 char) not null,
  FARM_TER_GROUP RAW(255) not null
    constraint DRUGS_FARM_TER_GROUPS_ID_FK
    references PHARM_TER_GROUPS,
  IND_FOR_USE VARCHAR2(255 char) not null,
  PRODUCER RAW(255) not null
    constraint DRUGS_PRODUCERS_ID_FK
    references PRODUCERS,
  ACTIVE_INGREDIENT VARCHAR2(255 char) not null,
  DESCRIPTION VARCHAR2(255 char)
)
/
