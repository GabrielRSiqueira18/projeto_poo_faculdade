CREATE TABLE IF NOT EXISTS passages (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_car INTEGER NOT NULL,
    fine_value FLOAT,
    passage_start_time TIMESTAMP WITH TIME ZONE NOT NULL,
    passage_end_time TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (id_car) REFERENCES cars(id)
);

