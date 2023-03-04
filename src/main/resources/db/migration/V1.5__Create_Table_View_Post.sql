CREATE TABLE view_post
(
    id        VARCHAR(36) NOT NULL,
    viewed_at datetime    NULL,
    post_id   VARCHAR(36) NULL,
    CONSTRAINT pk_view_post PRIMARY KEY (id)
);

ALTER TABLE view_post
    ADD CONSTRAINT FK_VIEW_POST_ON_POST FOREIGN KEY (post_id) REFERENCES post (id);

CREATE INDEX fn_index_post_id ON view_post (post_id);