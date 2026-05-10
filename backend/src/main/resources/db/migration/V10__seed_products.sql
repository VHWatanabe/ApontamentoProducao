INSERT INTO products (code, name, units_per_box, active)
VALUES
    ('GUA-500-145X295', 'Guardanapo Embalado 14,5 x 29,5 500 sachês', 500, true),
    ('GUA-1000-145X295', 'Guardanapo Embalado 14,5 x 29,5 1000 sachês', 1000, true),
    ('GUA-2000-145X295', 'Guardanapo Embalado 14,5 x 29,5 2000 sachês', 2000, true),
    ('GUA-1000-095X195', 'Guardanapo Embalado 9,5 x 19,5 1000 sachês', 1000, true),
    ('GUA-2000-095X195', 'Guardanapo Embalado 9,5 x 19,5 2000 sachês', 2000, true)
    ON CONFLICT (code) DO NOTHING;