ALTER TABLE production_records
    ADD COLUMN product_id BIGINT;

ALTER TABLE production_records
    ADD CONSTRAINT fk_production_records_product
        FOREIGN KEY (product_id)
            REFERENCES products(id);