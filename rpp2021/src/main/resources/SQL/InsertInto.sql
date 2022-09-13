INSERT INTO "liga"("id", "naziv", "oznaka")
VALUES(nextval('liga_seq'), 'Mala', 'MA');
INSERT INTO "liga"("id", "naziv", "oznaka")
VALUES(nextval('liga_seq'), 'Junior', 'JU');
INSERT INTO "liga"("id", "naziv", "oznaka")
VALUES(nextval('liga_seq'), 'Velika', 'V');

INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
VALUES(nextval('nacionalnost_seq'), 'Srbija', 'SRB');
INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
VALUES(nextval('nacionalnost_seq'), 'Hrvatska', 'HRT');
INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
VALUES(nextval('nacionalnost_seq'), 'Amerika', 'SAD');

INSERT INTO "tim"("id", "naziv", "liga", "osnovan", "sediste")
VALUES (nextval('tim_seq'), 'Srem', 1, to_date('03.08.2011.','dd.mm.yyyy.'), 'Sremska Mitrovica');
INSERT INTO "tim"("id", "naziv", "liga", "osnovan", "sediste")
VALUES (nextval('tim_seq'), 'Crvena Zvezda', 2, to_date('17.02.2013.','dd.mm.yyyy.'), 'Beograd');
INSERT INTO "tim"("id", "naziv", "liga", "osnovan", "sediste")
VALUES (nextval('tim_seq'), 'Partizan', 3, to_date('01.02.2002.','dd.mm.yyyy.'), 'Beograd');

INSERT INTO "igrac"("id", "tim", "broj_reg", "nacionalnost", "prezime", "ime", "datum_rodjenja")
VALUES (nextval('igrac_seq'), 1, 1, 1, 'Markovic', 'Marko', to_date('05.03.1990.','dd.mm.yyyy.') );
INSERT INTO "igrac"("id", "tim", "broj_reg", "nacionalnost", "prezime", "ime", "datum_rodjenja")
VALUES (nextval('igrac_seq'), 2, 2, 2, 'Stepic', 'Stepan', to_date('19.9.1998.', 'dd.mm.yyyy.') );
INSERT INTO "igrac"("id", "tim", "broj_reg", "nacionalnost", "prezime", "ime", "datum_rodjenja")
VALUES (nextval('igrac_seq'), 3, 3, 3, 'Dzejson', 'Dzon', to_date('23.11.1995.', 'dd.mm.yyyy.') );
