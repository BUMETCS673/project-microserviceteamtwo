create database promanager;
create user cap password 'cap@123';

grant all privileges on database promanager to cap;

\c postgres

CREATE TABLE "project_ci" (
  "project_id" varchar UNIQUE PRIMARY KEY,
  "projectname" varchar,
  "description" varchar,
  "owner_id"    int,
  "created_on"  int,
  "updated_on"  int,  
  "status"      varchar,
  "type"        varchar,
  "active"      boolean
);

CREATE TABLE "tasks" (
   "task_id"          VARCHAR UNIQUE PRIMARY KEY,
   "project_id"       VARCHAR,
   "task_name"        VARCHAR(100) NOT NULL,
   "description"      TEXT,
   "status"           VARCHAR(20) DEFAULT 'To Do',
   "priority"         VARCHAR(20) DEFAULT 'Medium',
   "assigned_user_id" INT,
   "due_date"         INT,
   "created_on"       INT,
   "updated_on"       INT
);

CREATE TABLE APP_USER
(
  USER_ID      INT,
  NAME         VARCHAR(255) not null,
  EMAIL        VARCHAR(255) not null,
  PASSWORD     VARCHAR(128) not null
) ;

CREATE TABLE PROJECT_USER (
    PROJECT_USER_ID INT,
    PROJECT_ID      VARCHAR,
    USER_ID         INT,
    ROLE            VARCHAR(20) DEFAULT 'Collaborator',
    created_at      INT,
    updated_at      INT
);

CREATE TABLE comments (
    comment_id SERIAL PRIMARY KEY,
    project_id VARCHAR NOT NULL,
    comments TEXT NOT NULL,
    user_id INT NOT NULL,
    created_on TIMESTAMP NOT NULL
);

ALTER TABLE APP_USER ADD CONSTRAINT PK_APP_USER PRIMARY KEY (USER_ID);

alter table APP_USER add constraint APP_USER_UK unique (EMAIL);

ALTER TABLE PROJECT_USER ADD CONSTRAINT PK_PROJECT_USERS PRIMARY KEY (PROJECT_USER_ID);

ALTER TABLE tasks ADD CONSTRAINT FK_PROJECT_TASK FOREIGN KEY (project_id) REFERENCES project_ci(project_id);

ALTER TABLE project_ci ADD CONSTRAINT FK_PROJECT_CI_OWNER FOREIGN KEY (owner_id) REFERENCES APP_USER(USER_ID);

ALTER TABLE tasks ADD CONSTRAINT FK_ASSIGNED_USER FOREIGN KEY (assigned_user_id) REFERENCES APP_USER(USER_ID);


ALTER TABLE PROJECT_USER ADD CONSTRAINT FK_APP_USER_PROJECT_USER FOREIGN KEY (USER_ID) REFERENCES APP_USER(USER_ID);

ALTER TABLE PROJECT_USER ADD CONSTRAINT FK_PROJECT_USER FOREIGN KEY (PROJECT_ID) REFERENCES project_ci(project_id);

ALTER TABLE comments ADD CONSTRAINT FK_PROJECT_ID FOREIGN KEY (project_id) REFERENCES project_ci(project_id);
ALTER TABLE comments ADD CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES APP_USER(USER_ID);

GRANT ALL PRIVILEGES ON TABLE project_ci to cap;
GRANT ALL PRIVILEGES ON TABLE tasks to cap;
GRANT ALL PRIVILEGES ON TABLE PROJECT_USER to cap;
GRANT ALL PRIVILEGES ON TABLE APP_USER to cap;

-- Inserting data into APP_USER
INSERT INTO APP_USER (USER_ID, NAME, EMAIL, PASSWORD) VALUES
(1, 'User One', 'user1@example.com', 'password123'),
(2, 'User Two', 'user2@example.com', 'password124');



INSERT INTO project_ci (project_id, projectname, description, owner_id, created_on, updated_on, status, type, active) VALUES
('proj_001', 'Project Alpha', 'This is a test project.', 123, 1622548800, 1622645200, 'ongoing', 'development', true);

INSERT INTO project_ci (project_id, projectname, description, owner_id, created_on, updated_on, status, type, active) VALUES
('proj_002', 'Project Beta', 'This is a test project.', 123, 1622548801, 1622645201, 'ongoing', 'development', true);


INSERT INTO project_ci (project_id, projectname, description, owner_id, created_on, updated_on, status, type, active) VALUES
('proj_003', 'Proeject Gamma', 'This is a test project.', 1, 1622548802, 1622645201, 'closed', 'development', true);

INSERT INTO project_ci (project_id, projectname, description, owner_id, created_on, updated_on, status, type, active) VALUES
('proj_004', 'Project Theta', 'This is a test project.', 1, 1622548804, 1622645202, 'closed', 'development', true);


INSERT INTO tasks (task_id, project_id, task_name, description, status, priority, assigned_user_id, due_date, created_on, updated_on) VALUES
('task_5', 'proj_001', 'Task 2', 'Task description 2', 'To Do', 'Medium', 1, 1622548800, 1622548800, 1622635200);


INSERT INTO tasks (task_id, project_id, task_name, description, status, priority, assigned_user_id, due_date, created_on, updated_on) VALUES
('task_1', 'proj_001', 'Task 1', 'Task description 1', 'To Do', 'Medium', 123, 1622548800, 1622548800, 1622635200),
('task_2', 'proj_002', 'Task 1 Create', 'Task description 2', 'To Do', 'Medium', 123, 1622548800, 1622548800, 1622635200),
('task_3', 'proj_003', 'Task 1 Edit', 'Task description 3', 'To Do', 'Medium', 1, 1622548800, 1622548800, 1622548800),
('task_4', 'proj_003', 'Task 1 Create', 'Task description 4', 'To Do', 'Medium', 1, 1622548800, 1622548800, 1622548800);



-- Inserting data into PROJECT_USER
INSERT INTO PROJECT_USER (PROJECT_USER_ID, PROJECT_ID, USER_ID, ROLE, created_at, updated_at) VALUES
(1, 'proj_001', 1, 'Collaborator', 1622548800, 1622548800),
(2, 'proj_002', 1, 'Collaborator', 1622548800, 1622548800),
(3, 'proj_003', 123, 'Collaborator', 1622548800, 1622548800),
(4, 'proj_004', 123, 'Collaborator', 1622548800, 1622548800);


INSERT INTO comments (project_id, comments, user_id, created_on)
VALUES (1, 'This is a sample comment', 2, NOW());
