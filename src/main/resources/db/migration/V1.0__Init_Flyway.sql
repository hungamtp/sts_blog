CREATE TABLE translation_string_type
(
    id   VARCHAR(36)  NOT NULL,
    type VARCHAR(255) NULL,
    CONSTRAINT pk_translation_string_type PRIMARY KEY (id)
);

CREATE TABLE tag
(
    id  VARCHAR(36)  NOT NULL,
    tag VARCHAR(255) NULL,
    CONSTRAINT pk_tag PRIMARY KEY (id)
);

CREATE TABLE language
(
    id   VARCHAR(36)  NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_language PRIMARY KEY (id)
);

ALTER TABLE language
    ADD CONSTRAINT uc_language_name UNIQUE (name);

CREATE TABLE post
(
    id          VARCHAR(36)  NOT NULL,
    hidden      BIT(1)       NOT NULL,
    cover_image VARCHAR(255) NULL,
    created_at  datetime     NULL,
    CONSTRAINT pk_post PRIMARY KEY (id)
);

CREATE TABLE post_tag
(
    post_id VARCHAR(36) NOT NULL,
    tag_id  VARCHAR(36) NOT NULL,
    CONSTRAINT pk_post_tag PRIMARY KEY (post_id, tag_id)
);

CREATE INDEX fn_index_hidden ON post (hidden);

ALTER TABLE post_tag
    ADD CONSTRAINT fk_post_tag_on_post FOREIGN KEY (post_id) REFERENCES post (id);

ALTER TABLE post_tag
    ADD CONSTRAINT fk_post_tag_on_tag FOREIGN KEY (tag_id) REFERENCES tag (id);


ALTER TABLE post_tag
    ADD CONSTRAINT unique_post_id_tag_id UNIQUE (post_id, tag_id);

CREATE TABLE translation_string
(
    id                VARCHAR(36)  NOT NULL,
    translated_string VARCHAR(255) NULL,
    language_id       VARCHAR(36)  NULL,
    type_id           VARCHAR(36)  NULL,
    post_id           VARCHAR(36)  NULL,
    CONSTRAINT pk_translation_string PRIMARY KEY (id)
);

ALTER TABLE translation_string
    ADD CONSTRAINT FK_TRANSLATION_STRING_ON_LANGUAGE FOREIGN KEY (language_id) REFERENCES language (id);

CREATE INDEX fk_index_language_id ON translation_string (language_id);

ALTER TABLE translation_string
    ADD CONSTRAINT FK_TRANSLATION_STRING_ON_POST FOREIGN KEY (post_id) REFERENCES post (id);

ALTER TABLE translation_string
    ADD CONSTRAINT FK_TRANSLATION_STRING_ON_TYPE FOREIGN KEY (type_id) REFERENCES translation_string_type (id);

CREATE INDEX fk_index_type_id ON translation_string (type_id);

CREATE TABLE user
(
    id    VARCHAR(36)  NOT NULL,
    email VARCHAR(255) NULL,
    name  VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT unique_email UNIQUE (email);

CREATE INDEX fn_index_email ON user (email);