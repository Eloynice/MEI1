CREATE TABLE IF NOT EXISTS adj(
	ug1 INTEGER NOT NULL,
	ug2 INTEGER NOT NULL,
	front REAL NOT NULL,
	PRIMARY KEY(ug1,ug2)
	FOREIGN KEY(ug1) REFERENCES ug(ug)
	FOREIGN KEY(ug2) REFERENCES ug(ug));

CREATE TABLE IF NOT EXISTS area (
	ug	INTEGER NOT NULL UNIQUE,
	area	REAL NOT NULL,
	PRIMARY KEY(ug)
	FOREIGN KEY(ug) REFERENCES ug(ug));
	
CREATE TABLE IF NOT EXISTS per (
	ug	INTEGER NOT NULL UNIQUE,
	per	REAL NOT NULL,
	PRIMARY KEY(ug)
	FOREIGN KEY(ug) REFERENCES ug(ug));	

CREATE TABLE IF NOT EXISTS action(
          ug	INTEGER NOT NULL,
          presc	INTEGER NOT NULL,
          thin	REAL,
          harv	REAL,
          wood	REAL,
          cork   REAL,
          period	INTEGER NOT NULL,
          species    TEXT,
          PRIMARY KEY(ug,presc,period)
          FOREIGN KEY(ug) REFERENCES ug(ug),
          FOREIGN KEY(ug) REFERENCES area(ug)
          FOREIGN KEY(ug) REFERENCES per(ug));

CREATE TABLE IF NOT EXISTS symb(
          ug	INTEGER NOT NULL,
          presc	INTEGER NOT NULL,
          thin	int,
          harv	int,
          cork   int,
          period	INTEGER NOT NULL,
          species    TEXT,
          PRIMARY KEY(ug,presc,period)
          FOREIGN KEY(ug) REFERENCES ug(ug),
          FOREIGN KEY(ug) REFERENCES area(ug)
          FOREIGN KEY(ug) REFERENCES per(ug));

CREATE TABLE IF NOT EXISTS presc_ids (
	external	TEXT NOT NULL UNIQUE,
	internal	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE
);
CREATE TABLE IF NOT EXISTS ug_ids (
	external	TEXT NOT NULL UNIQUE,
	internal	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE
);

CREATE TABLE IF NOT EXISTS hascut (
	ug	INTEGER NOT NULL,
	presc	INTEGER NOT NULL,
	period	INTEGER NOT NULL,
	PRIMARY KEY(ug,presc,period)
);

CREATE TABLE adj_area (
	ug_id	INTEGER NOT NULL UNIQUE,
	adj_area	REAL NOT NULL,
	PRIMARY KEY(ug_id,adj_area)
);

