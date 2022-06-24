1.先來這裡下載mysql(window和platform都要共下載兩個檔案)
https://dev.mysql.com/downloads/connector/j/

2.在MySQL WorkBench輸入以下程式碼

create database demo;

Use demo;
CREATE TABLE userlist3 (
	id int(10) not null,
    name CHAR(20) NOT NULL, 
    password CHAR(40) not null,
    identity char(20)NOT NULL,
    msg TEXT NOT NULL 
);

Use demo;
CREATE TABLE userData (
	temperature char(20) not null,
    y CHAR(20) NOT NULL, 
    m CHAR(20) not null,
    d char(20) not null,
    currentSynmptom char(60),
    travelHistory char(40),
    msg TEXT NOT NULL,
	name char(20)not null 
);

3.cmd要打這個:

javac -encoding utf-8 -d . -classpath .;ojdbc8.jar;mysql-connector-java-8.0.29.jar *.java

java -classpath .;ojdbc8.jar;mysql-connector-java-8.0.29.jar c.mainTest
