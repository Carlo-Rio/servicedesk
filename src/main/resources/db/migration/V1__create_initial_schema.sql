

CREATE SCHEMA IF NOT EXISTS servicedesk;

CREATE TABLE servicedesk.app_users (
                                  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                                  email VARCHAR(255) NOT NULL UNIQUE,

                                  password_hash VARCHAR(255) NOT NULL,

                                  role VARCHAR(20) NOT NULL DEFAULT 'CUSTOMER'
                                      CHECK (role IN ('CUSTOMER', 'AGENT', 'ADMIN')),

                                  enabled BOOLEAN NOT NULL DEFAULT TRUE,

                                  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE servicedesk.tickets (
                                     id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                                     title VARCHAR(150) NOT NULL,

                                     description VARCHAR(5000) NOT NULL,

                                     status VARCHAR(20) NOT NULL DEFAULT 'NEW'
                                         CHECK (
                                             status IN (
                                                        'NEW',
                                                        'OPEN',
                                                        'IN PROGRESS',
                                                        'PENDING',
                                                        'RESOLVED',
                                                        'CLOSED'
                                                 )
                                             ),

                                     priority VARCHAR(20)
                                         CHECK (priority IN ('P1','P2','P3','P4','P5')),

                                     customer_id BIGINT NOT NULL
                                         REFERENCES servicedesk.app_users (id),

                                     assignee_id BIGINT
                                         REFERENCES servicedesk.app_users (id),

                                     version BIGINT NOT NULL DEFAULT 0,

                                     created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                     updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP


);

CREATE TABLE servicedesk.comments (

                                      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                                      ticket_id BIGINT NOT NULL
                                          REFERENCES servicedesk.tickets (id),

                                      author_id BIGINT NOT NULL
                                          REFERENCES servicedesk.app_users (id),

                                      content VARCHAR(5000) NOT NULL
                                          CHECK (char_length(btrim(content)) > 0),

                                      created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP


);

CREATE TABLE servicedesk.attachments (
                                         id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                                         ticket_id BIGINT NOT NULL
                                             REFERENCES servicedesk.tickets (id),

                                         uploaded_by BIGINT NOT NULL
                                             REFERENCES servicedesk.app_users (id),

                                         object_key VARCHAR(250) NOT NULL UNIQUE,

                                         original_filename VARCHAR(255) NOT NULL,

                                         content_type VARCHAR(255) NOT NULL,

                                         size_bytes BIGINT NOT NULL
                                             CHECK (size_bytes > 0),

                                         status VARCHAR(20) NOT NULL DEFAULT 'PENDING'
                                             CHECK (status IN ('PENDING', 'READY', 'FAILED')),

                                         created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE servicedesk.ticket_status_history (

                                                   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                                                   ticket_id BIGINT NOT NULL
                                                       REFERENCES servicedesk.tickets (id),

                                                   changed_by BIGINT NOT NULL
                                                       REFERENCES servicedesk.app_users (id),

                                                   old_status VARCHAR(20) NOT NULL
                                                       CHECK (
                                                           old_status IN (
                                                                          'NEW','OPEN','IN_PROGRSS',
                                                                          'PENDING','RESOLVED','CLOSED'
                                                               )
                                                           ),

                                                   new_status VARCHAR(20) NOT NULL
                                                       CHECK (
                                                           new_status IN (
                                                                          'NEW', 'OPEN', 'IN_PROGRESS',
                                                                          'PENDING', 'RESOLVED', 'CLOSED'
                                                               )
                                                           ),

                                                   changed_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                                   CHECK (old_status <> new_status)

);
CREATE INDEX idx_comments_ticket_created_id
    ON servicedesk.comments (ticket_id, created_at, id);