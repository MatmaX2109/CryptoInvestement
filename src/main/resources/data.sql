CREATE TABLE CRYPTO_CSV_PATH (
    ID int NOT NULL,
    SYMBOL varchar(5) NOT NULL,
    PATH varchar(50) NOT NULL

);

INSERT INTO CRYPTO_CSV_PATH VALUES (1, 'btc', 'src/main/resources/static/BTC_values.csv');
INSERT INTO CRYPTO_CSV_PATH VALUES (2, 'doge', 'src/main/resources/static/DOGE_values.csv');
INSERT INTO CRYPTO_CSV_PATH VALUES (3, 'eth', 'src/main/resources/static/ETH_values.csv');
INSERT INTO CRYPTO_CSV_PATH VALUES (4, 'ltc', 'src/main/resources/static/LTC_values.csv');
INSERT INTO CRYPTO_CSV_PATH VALUES (5, 'xrp', 'src/main/resources/static/XRP_values.csv');

