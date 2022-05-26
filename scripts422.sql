CREATE TABLE cars
(
    id        INTEGER PRIMARY KEY,
    car_brand TEXT NOT NULL,
    model     TEXT NOT NULL,
    cost      REAL NOT NULL
);

CREATE TABLE drivers(
                        id INTEGER PRIMARY KEY,
                        name TEXT NOT NULL,
                        age INTEGER NOT NULL,
                        driver_license BOOLEAN NOT NULL,
                        car_id INTEGER REFERENCES cars (id)
)