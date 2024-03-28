CREATE TABLE IF NOT EXISTS depar.department
(
    id bigint NOT NULL DEFAULT nextval('depar.department_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    phone text COLLATE pg_catalog."default",
    id_location bigint,
    CONSTRAINT department_pkey PRIMARY KEY (id),
    CONSTRAINT location_fkey FOREIGN KEY (id_location)
        REFERENCES depar.location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
