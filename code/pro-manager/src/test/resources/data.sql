-- Create tables
CREATE TABLE project_ci (
  project_id VARCHAR UNIQUE PRIMARY KEY,
  projectname VARCHAR,
  description VARCHAR,
  owner_id INT,
  created_on INT,
  updated_on INT,  
  status VARCHAR,
  type VARCHAR,
  active BOOLEAN
);

CREATE TABLE tasks (
   task_id VARCHAR UNIQUE PRIMARY KEY,
   project_id VARCHAR,
   task_name VARCHAR(100) NOT NULL,
   description TEXT,
   status VARCHAR(20) DEFAULT 'To Do',
   priority VARCHAR(20) DEFAULT 'Medium',
   assigned_user_id INT,
   due_date INT,
   created_on INT,
   updated_on INT
);

CREATE TABLE APP_USER (
  USER_ID INT,
  NAME VARCHAR(255) NOT NULL,
  EMAIL VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(128) NOT NULL,
  PRIMARY KEY (USER_ID),
  UNIQUE (EMAIL)
);

CREATE TABLE PROJECT_USER (
    PROJECT_USER_ID INT,
    PROJECT_ID VARCHAR,
    USER_ID INT,
    ROLE VARCHAR(20) DEFAULT 'Collaborator',
    created_at INT,
    updated_at INT,
    PRIMARY KEY (PROJECT_USER_ID)
);

CREATE TABLE comments (
    comment_id SERIAL PRIMARY KEY,
    project_id VARCHAR NOT NULL,
    comments TEXT NOT NULL,
    user_id INT NOT NULL,
    created_on TIMESTAMP NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project_ci(project_id),
    FOREIGN KEY (user_id) REFERENCES APP_USER(USER_ID)
);

-- Foreign Key Constraints
ALTER TABLE tasks ADD CONSTRAINT FK_PROJECT_TASK FOREIGN KEY (project_id) REFERENCES project_ci(project_id);
ALTER TABLE project_ci ADD CONSTRAINT FK_PROJECT_CI_OWNER FOREIGN KEY (owner_id) REFERENCES APP_USER(USER_ID);
ALTER TABLE tasks ADD CONSTRAINT FK_ASSIGNED_USER FOREIGN KEY (assigned_user_id) REFERENCES APP_USER(USER_ID);
ALTER TABLE PROJECT_USER ADD CONSTRAINT FK_APP_USER_PROJECT_USER FOREIGN KEY (USER_ID) REFERENCES APP_USER(USER_ID);
ALTER TABLE PROJECT_USER ADD CONSTRAINT FK_PROJECT_USER FOREIGN KEY (PROJECT_ID) REFERENCES project_ci(project_id);

-- Insert sample data
INSERT INTO APP_USER (USER_ID, NAME, EMAIL, PASSWORD) VALUES
(1, 'User One', 'user1@example.com', 'password123'),
(2, 'User Two', 'user2@example.com', 'password124');

INSERT INTO project_ci (project_id, projectname, description, owner_id, created_on, updated_on, status, type, active) VALUES
('proj_001', 'Project Alpha', 'This is a test project.', 1, 1622548800, 1622645200, 'ongoing', 'development', true);

INSERT INTO tasks (task_id, project_id, task_name, description, status, priority, assigned_user_id, due_date, created_on, updated_on) VALUES
('task_1', 'proj_001', 'Task 1', 'Task description 1', 'To Do', 'Medium', 1, 1622548800, 1622548800, 1622635200);

INSERT INTO PROJECT_USER (PROJECT_USER_ID, PROJECT_ID, USER_ID, ROLE, created_at, updated_at) VALUES
(1, 'proj_001', 1, 'Collaborator', 1622548800, 1622548800);

INSERT INTO comments (project_id, comments, user_id, created_on) VALUES
('proj_001', 'This is a sample comment', 2, NOW());
