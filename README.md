#Shoe Store
#### Database set-up for shoe stores and brands
#### January 29, 2016.

#### By Kassidy Douglas

## Description

This repository is a code review for week four of Java. In it, someone could add a list of stores, of brands, and list those brands as being sold at a variety of stores.

## Setup/Installation Requirements

* Be sure to have all technologies used installed.
* Clone this repository
* Run a server in a terminal, using
`postgres`
* In a separate terminal, run:
`psql`
* In PSQL, run these commands:

```
CREATE DATABASE shoe_stores;
CREATE TABLE stores (id serial PRIMARY KEY, name varchar, address varchar, phone_number varchar);
CREATE TABLE brands (id serial PRIMARY KEY, name varchar, specialty varchar);
CREATE TABLE stores_brands (id serial PRIMARY KEY, store_id int, brand_id int);
CREATE DATABASE shoe_stores_test WITH TEMPLATE shoe_stores;
```

* Download this repository
* In a separate terminal, navigate to your project folder then run:

`psql shoe_stores < shoe_stores.sql`

* Finally, in the same terminal in which you are viewing your project folder, run the command:

`gradle run`



## Technologies Used

Java, Spark, JUnit, Velocity, Bootstrap, FluentLenium, Postgres and Sql2o
