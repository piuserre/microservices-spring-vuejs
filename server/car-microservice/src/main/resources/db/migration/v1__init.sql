CREATE TABLE car (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  brand varchar(100) NOT NULL,
  name varchar(100) NOT NULL,
  year int NOT NULL,
  prize decimal(5,2) NOT NULL,
  km bigint NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_username (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE rent (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  car_id bigint(20) NOT NULL,
  user_id bigint(20) NOT NULL,
  start_rent datetime NOT NULL,
  end_rent datetime NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY (id),
  CONSTRAINT fk_car_rent FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into car (brand, name, year, prize, km) values ('audi', 'A4 bella', 2018, 100, 10000);