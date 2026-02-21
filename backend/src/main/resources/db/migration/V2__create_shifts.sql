CREATE TABLE shifts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    machine_id BIGINT NOT NULL REFERENCES machines(id),
    status VARCHAR(30) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP
);