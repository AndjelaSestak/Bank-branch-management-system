insert into banka(id, pib, kontakt, naziv)
values(nextval('banka_seq'),21000,'021450565', 'UniCredit');
insert into banka(id, pib, kontakt, naziv)
values(nextval('banka_seq'),25230,'025456599', 'ErsteBank');
insert into banka(id, pib, kontakt, naziv)
values(nextval('banka_seq'),21770,'021652232', 'Postanska stedionica');
insert into banka(id, pib, kontakt, naziv)
values(nextval('banka_seq'),26767,'021878900', 'Komercijalna');

insert into korisnik_usluge(id, ime, prezime, maticni_broj)
values(nextval('korisnikusluge_seq'), 'Ana', 'Petrovic', '9689098765439');
insert into korisnik_usluge(id, ime, prezime, maticni_broj)
values(nextval('korisnikusluge_seq'), 'Marija', 'Jovanic', '1202987065439');
insert into korisnik_usluge(id, ime, prezime, maticni_broj)
values(nextval('korisnikusluge_seq'), 'Luka', 'Obradovic', '1308998709435');
insert into korisnik_usluge(id, ime, prezime, maticni_broj)
values(nextval('korisnikusluge_seq'), 'Stefan', 'Nikolic', '2703987609845');

insert into filijala(id, broj_pultova, poseduje_sef, adresa, banka)
values(nextval('filijala_seq'), 1, true, 'Nikole Pasica 13', 1);
insert into filijala(id, broj_pultova, poseduje_sef, adresa, banka)
values(nextval('filijala_seq'), 2, true, 'Vuka Karadzica 15', 2);
insert into filijala(id, broj_pultova, poseduje_sef, adresa, banka)
values(nextval('filijala_seq'), 3, true, 'Marsala Tita 132', 3);
insert into filijala(id, broj_pultova, poseduje_sef, adresa, banka)
values(nextval('filijala_seq'), 4, true, 'Zeleznicka 23', 4);

insert into usluga(id, naziv, opis_usluge, datum_ugovora, provizija, filijala, korisnik)
values(nextval('usluga_seq'), 'Stambeni kredit', 'Ugovaranje stambenog kredita', to_date('24.03.2023.', 'dd.mm.yyyy.'), 0.15, 1, 1);
insert into usluga(id, naziv, opis_usluge, datum_ugovora, provizija, filijala, korisnik)
values(nextval('usluga_seq'), 'Transfer sredstava', 'Transfer sredstava sa jednog racuna na drugi racun', to_date('31.03.2020.', 'dd.mm.yyyy.'), 0.10, 2, 2);
insert into usluga(id, naziv, opis_usluge, datum_ugovora, provizija, filijala, korisnik)
values(nextval('usluga_seq'), 'Placanje', 'Izvrsavanje uplate na racun', to_date('22.07.2007.', 'dd.mm.yyyy.'), 0.01, 3, 3);
insert into usluga(id, naziv, opis_usluge, datum_ugovora, provizija, filijala, korisnik)
values(nextval('usluga_seq'), 'Stednja', 'Pokretanje orocene stednje', to_date('29.06.2015.', 'dd.mm.yyyy.'), 0.15, 4, 4);