CREATE TABLE IF NOT EXISTS depar.dep_cross
(
    id_parent bigint NOT NULL,
    id_children bigint NOT NULL,
    CONSTRAINT uniq UNIQUE (id_parent)
        INCLUDE(id_parent),
    CONSTRAINT child_fkey FOREIGN KEY (id_children)
        REFERENCES depar.department (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT parent_fkey FOREIGN KEY (id_parent)
        REFERENCES depar.department (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
