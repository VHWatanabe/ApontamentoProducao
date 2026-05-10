CREATE TABLE production_box_records (
    id BIGSERIAL PRIMARY KEY,
    production_record_id BIGINT NOT NULL,
    box_number INTEGER NOT NULL,
    recorded_at TIMESTAMP NOT NULL,
    notes TEXT,

    CONSTRAINT fk_production_box_records_production_record
        FOREIGN KEY (production_record_id)
            REFERENCES production_records(id),

    CONSTRAINT uk_production_box_records_box_number
        UNIQUE (production_record_id, box_number)
);