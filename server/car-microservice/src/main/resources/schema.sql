CREATE TABLE if not exists car (
  id int NOT NULL AUTO_INCREMENT,
  brand varchar(100) NOT NULL,
  name varchar(100) NOT NULL,
  year int NOT NULL,
  prize decimal(5,2) NOT NULL,
  km int NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_username (name)
) ;

CREATE TABLE if not exists rent (
  id int NOT NULL AUTO_INCREMENT,
  car_id int NOT NULL,
  user_id int NOT NULL,
  start_rent datetime NOT NULL,
  end_rent datetime NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY (id),
  CONSTRAINT fk_car_rent FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE ON UPDATE CASCADE
) ;
