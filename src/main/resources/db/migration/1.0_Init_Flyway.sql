CREATE TABLE post
(
    id         VARCHAR(36)  NOT NULL,
    hidden     BIT(1)       NULL,
    coverImage VARCHAR(255) NULL,
    createdAt  datetime     NULL,
    CONSTRAINT pk_post PRIMARY KEY (id)
);

CREATE INDEX fn_index_hidden ON post (hidden);


CREATE TABLE language
(
    id   VARCHAR(36)  NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_language PRIMARY KEY (id)
);

ALTER TABLE language
    ADD CONSTRAINT uc_language_name UNIQUE (name);

CREATE TABLE tag
(
    id  VARCHAR(36)  NOT NULL,
    tag VARCHAR(255) NULL,
    CONSTRAINT pk_tag PRIMARY KEY (id)
);

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

CREATE TABLE translation_string_type
(
    id   VARCHAR(36)  NOT NULL,
    type VARCHAR(255) NULL,
    CONSTRAINT pk_translation_string_type PRIMARY KEY (id)
);

CREATE TABLE TranslationString
(
    id               VARCHAR(36)  NOT NULL,
    translatedString VARCHAR(255) NULL,
    language_id      char(36)     NULL,
    post_id          char(36)     NULL,
    type_id          char(36)     NULL,
    CONSTRAINT pk_translationstring PRIMARY KEY (id)
);

ALTER TABLE TranslationString
    ADD CONSTRAINT FK_TRANSLATIONSTRING_ON_LANGUAGE FOREIGN KEY (language_id) REFERENCES Language (id);

CREATE INDEX fk_index_language_id ON TranslationString (language_id);

ALTER TABLE TranslationString
    ADD CONSTRAINT FK_TRANSLATIONSTRING_ON_POST FOREIGN KEY (post_id) REFERENCES Post (id);

CREATE INDEX fk_index_post_id ON TranslationString (post_id);

ALTER TABLE TranslationString
    ADD CONSTRAINT FK_TRANSLATIONSTRING_ON_TYPE FOREIGN KEY (type_id) REFERENCES TranslationStringType (id);

CREATE INDEX fk_index_type_id ON TranslationString (type_id);
