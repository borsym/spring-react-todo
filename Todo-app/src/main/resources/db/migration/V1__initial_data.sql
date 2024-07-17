CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
    );

-- Create Roles table if it doesn't exist
CREATE TABLE IF NOT EXISTS roles (
                                     id SERIAL PRIMARY KEY,
                                     role_name VARCHAR(50) NOT NULL
    );

-- Create Priorities table if it doesn't exist
CREATE TABLE IF NOT EXISTS priorities (
                                          id SERIAL PRIMARY KEY,
                                          priority_name VARCHAR(50) NOT NULL
    );

-- Create Projects table if it doesn't exist
CREATE TABLE IF NOT EXISTS projects (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL,
    description TEXT,
    created_by INTEGER REFERENCES users(id)
    );

-- Create Status table if it doesn't exist
CREATE TABLE IF NOT EXISTS status (
                                      id SERIAL PRIMARY KEY,
                                      status_name VARCHAR(50) NOT NULL
    );

-- Create Tasks table if it doesn't exist
CREATE TABLE IF NOT EXISTS tasks (
                                     id SERIAL PRIMARY KEY,
                                     title VARCHAR(100) NOT NULL,
    description TEXT,
    project_id INTEGER REFERENCES projects(id),
    assigned_to INTEGER REFERENCES users(id),
    status INTEGER REFERENCES status(id),
    priority INTEGER REFERENCES priorities(id),
    created_by INTEGER REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- Create Comments table if it doesn't exist
CREATE TABLE IF NOT EXISTS comments (
                                        id SERIAL PRIMARY KEY,
                                        task_id INTEGER REFERENCES tasks(id),
    user_id INTEGER REFERENCES users(id),
    comment_text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- Create Attachments table if it doesn't exist
CREATE TABLE IF NOT EXISTS attachments (
                                           id SERIAL PRIMARY KEY,
                                           task_id INTEGER REFERENCES tasks(id),
    file_path VARCHAR(255) NOT NULL,
    uploaded_by INTEGER REFERENCES users(id),
    uploaded_at TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- Create Teams table if it doesn't exist
CREATE TABLE IF NOT EXISTS teams (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
    created_by INTEGER REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- Create TeamMembers table if it doesn't exist
CREATE TABLE IF NOT EXISTS team_members (
                                            id SERIAL PRIMARY KEY,
                                            team_id INTEGER REFERENCES teams(id),
    user_id INTEGER REFERENCES users(id)
    );

-- Create UserRoles table if it doesn't exist
CREATE TABLE IF NOT EXISTS user_roles (
                                          id SERIAL PRIMARY KEY,
                                          user_id INTEGER REFERENCES users(id),
    role_id INTEGER REFERENCES roles(id)
    );

INSERT INTO users (username, password, email) VALUES ('alice', 'hashed_password1', 'alice@example.com'), ('bob', 'hashed_password2', 'bob@example.com'), ('charlie', 'hashed_password3', 'charlie@example.com');
INSERT INTO roles (role_name) VALUES ('Developer'), ('Team Lead'), ('QA');
INSERT INTO priorities (priority_name) VALUES ('Low'), ('Medium'), ('High');
INSERT INTO projects (name, description, created_by) VALUES ('Project Alpha', 'First project description', 1), ('Project Beta', 'Second project description', 2);
INSERT INTO status (status_name) VALUES ('OPEN'), ('In progress'), ('Closed');
INSERT INTO tasks (title, description, project_id, assigned_to, status, priority, created_by, created_at, updated_at) VALUES ('Task 1', 'Description for task 1', 1, 1, 1, 2, 1, NOW(), NOW()), ('Task 2', 'Description for task 2', 1, 2, 2, 3, 1, NOW(), NOW()), ('Task 3', 'Description for task 3', 2, 1, 3, 1, 1, NOW(), NOW());
INSERT INTO comments (task_id, user_id, comment_text, created_at) VALUES
                                                                      (1, 2, 'This is a comment by Bob.', NOW()),
                                                                      (2, 1, 'This is a comment by Alice.', NOW()),
                                                                      (3, 3, 'This is a comment by Charlie.', NOW());
INSERT INTO attachments (task_id, file_path, uploaded_by, uploaded_at) VALUES
                                                                           (1, '/files/task1_attachment.txt', 1, NOW()),
                                                                           (2, '/files/task2_attachment.jpg', 2, NOW());
INSERT INTO teams (name, created_by, created_at, updated_at) VALUES
                                                                 ('Development Team', 1, NOW(), NOW()),
                                                                 ('QA Team', 2, NOW(), NOW());
INSERT INTO team_members (team_id, user_id) VALUES
                                               (1, 1),
                                               (1, 2),
                                               (2, 3);
