CREATE TABLE IF NOT EXISTS parkings (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  person_name VARCHAR(100) NOT NULL,
  license_plate varchar(7) NOT NULL,
  cpf varchar(11) NOT NULL,
  started_date TIMESTAMP WITH TIME ZONE NOT NULL,
  ended_date TIMESTAMP WITH TIME ZONE NOT NULL,
  occopied INTEGER NOT NULL,
  password_to_pay VARCHAR(5),
  price_value DOUBLE NOT NULL,
  i INTEGER,
  j INTEGER
);