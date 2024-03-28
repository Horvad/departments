CREATE TABLE IF NOT EXISTS depar.location
(
    id bigint NOT NULL DEFAULT nextval('depar.location_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT location_pkey PRIMARY KEY (id)
)