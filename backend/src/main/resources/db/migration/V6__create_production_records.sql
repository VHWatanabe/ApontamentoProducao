CREATE TABLE production_records (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    machine_id BIGINT NOT NULL,
    shift_id BIGINT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP,
    good_quantity INTEGER NOT NULL DEFAULT 0,
    scrap_quantity INTEGER NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL,
    notes TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_production_records_user
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT fk_production_records_machine
        FOREIGN KEY (machine_id)
            REFERENCES machines(id),

    CONSTRAINT fk_production_records_shift
        FOREIGN KEY (shift_id)
            REFERENCES shifts(id)
);