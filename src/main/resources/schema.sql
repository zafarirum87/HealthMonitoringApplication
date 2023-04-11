CREATE Table If Not EXITS BloodPressure(
bloodPressureId Long (5) NOT NULL AUTO-INCREMENT,
systolic int(5) default NULL,
diastolic int (5) default NULL,
pulseRate int (5) default NULL,
date varchar(50) DEFAULT NULL,
time varchar(50) DEFAULT NULL,
PRIMARY KEY(bloodPressureId)
);
CREATE Table If Not EXITS Weight(
weightId Long (5) NOT NULL AUTO-INCREMENT,
weight double(5) default NULL,
date varchar(50) DEFAULT NULL,
PRIMARY KEY(bloodPressureId)
);
CREATE Table If Not EXITS Exercise(
exerciseId Long (5) NOT NULL AUTO-INCREMENT,
exerciseName varchar(50) DEFAULT NULL,
exerciseHours varchar(50) DEFAULT NULL,
exerciseDate varchar(50) DEFAULT NULL,
PRIMARY KEY(bloodPressureId)
);
CREATE Table If Not EXITS AppUser(
id Long (5) NOT NULL AUTO-INCREMENT,
name varchar(50) DEFAULT NULL,
password varchar(50) DEFAULT NULL,
gender varchar(50) DEFAULT NULL,
age int (5) default NULL,
role varchar(50) DEFAULT NULL,
PRIMARY KEY(id)
);