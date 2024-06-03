create database promanager;
create user cap password 'cap@123';

grant all privileges on database promanager to cap;

\c postgres

CREATE TABLE "project_ci" (
  "projectid" varchar UNIQUE PRIMARY KEY,
  "projectname" varchar,
  "userid" varchar,  
  "taskid" float,
  "description" varchar,
  "created_on" int,
  "updated_on" int,
  "status" varchar,
  "type" varchar,
  "active" boolean
);

# CONSTRAINTS make active default true

INSERT INTO "project_ci" (
  "projectid", 
  "projectname", 
  "userid", 
  "taskid", 
  "description", 
  "created_on", 
  "updated_on", 
  "status", 
  "type"
) VALUES (
  'proj_001', 
  'Project Alpha', 
  'user_123', 
  101.1, 
  'This is a test project.', 
  1622548800, 
  1622635200, 
  'ongoing', 
  'development'
);

-- create table APP_USER
-- (
--   USER_ID           serial,
--   NAME         VARCHAR(255) not null,
--   EMAIL        VARCHAR(255) not null,
--   PASSWORD     VARCHAR(128) not null,
-- ) ;

-- ALTER TABLE APP_USER ALTER COLUMN ENABLED SET DEFAULT 1;

-- alter table APP_USER add constraint APP_USER_PK primary key (USER_ID);
 
-- alter table APP_USER add constraint APP_USER_UK unique (EMAIL);

-- create table APP_ROLE
-- (
--   ROLE_ID   BIGINT not null,
--   ROLE_NAME VARCHAR(30) not null
-- ) ;

-- alter table APP_ROLE add constraint APP_ROLE_PK primary key (ROLE_ID);
 
-- alter table APP_ROLE add constraint APP_ROLE_UK unique (ROLE_NAME);
  
-- create table USER_ROLE
-- (
--   ID      SERIAL,
--   USER_ID	  BIGINT not null,
--   ROLE_ID BIGINT
-- );

-- ALTER TABLE USER_ROLE ALTER COLUMN ROLE_ID SET DEFAULT 2;

-- alter table USER_ROLE add constraint USER_ROLE_PK primary key (ID);
 
-- alter table USER_ROLE add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);
 
-- alter table USER_ROLE add constraint USER_ROLE_FK1 foreign key (USER_ID) references APP_USER (USER_ID);
 
-- alter table USER_ROLE add constraint USER_ROLE_FK2 foreign key (ROLE_ID) references APP_ROLE (ROLE_ID);


-- insert into app_role (ROLE_ID, ROLE_NAME)
-- values (1, 'ROLE_SUPERADMIN');
 
-- insert into app_role (ROLE_ID, ROLE_NAME)
-- values (2, 'ROLE_APP_MANAGER');

-- insert into app_role (ROLE_ID, ROLE_NAME)
-- values (3, 'ROLE_APP_STUDENT');


-- GRANT ALL PRIVILEGES ON TABLE user_role to pro;
GRANT ALL PRIVILEGES ON TABLE project_ci to cap;

-- GRANT ALL PRIVILEGES ON TABLE app_role to pro;
-- GRANT ALL PRIVILEGES ON TABLE app_user to pro;

