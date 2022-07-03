CREATE TABLE tasks(
    id serial PRIMARY KEY ,
    topic_of_tasks_id INT REFERENCES topic_of_tasks(id) ON DELETE CASCADE ,
    name VARCHAR (40) NOT NULL ,
    description text NOT NULL ,
    date VARCHAR (20) NOT NULL
);