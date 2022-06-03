--warehouses


INSERT INTO warehouses (name,address)
VALUES
    ('Shakespeares corner','Nice Street 10'),
    ('Balzacs warehouse','Friance Avenue 15'),
    ('Sofa of Jules Verne','Direct Street 44A'),
    ('Hemingways cherry','Github Street 1'),
    ('Orwell 1984','Processor Street 456');

--accounts

INSERT INTO accounts (name,surname, valid_until)
VALUES
    ('Lois','Brock', date '2000-01-01'),
    ('Hanna','Pruitt', date '2000-01-01'),
    ('Bruno','Zimmerman',date '2000-01-01'),
    ('Dexter','Bender',date '2000-01-01'),
    ('Rama','Bolton',date '2000-01-01'),
    ('Hamilton','Pace',date '2000-01-01'),
    ('Quamar','Cross',date '2000-01-01'),
    ('Bernard','Wyatt',date '2000-01-01'),
    ('Boris','Hyde',date '2000-01-01'),
    ('Claudia','Bradshaw',date '2000-01-01'),
    ('Yoshio','Mckenzie',date '2000-01-01'),
    ('Orli','Vargas',date '2000-01-01'),
    ('Griffith','Stewart',date '2000-01-01'),
    ('Lacey','French',date '2000-01-01'),
    ('Lewis','Cline',date '2000-01-01'),
    ('Emi','Little',date '2000-01-01'),
    ('Dalton','Pugh',date '2000-01-01'),
    ('Dieter','Davenport',date '2000-01-01'),
    ('Cooper','Ramos',date '2000-01-01'),
    ('Cyrus','May',date '2000-01-01'),
    ('Adena','Moses',date '2000-01-01'),
    ('Daria','Stewart',date '2000-01-01'),
    ('Rana','Lynch',date '2000-01-01'),
    ('Sage','Wood',date '2000-01-01'),
    ('Jenette','Sherman',date '2000-01-01'),
    ('Tanner','Henry',date '2000-01-01'),
    ('Cole','Forbes',date '2000-01-01'),
    ('Vladimir','Cruz',date '2000-01-01'),
    ('Kane','Blake',date '2000-01-01'),
    ('Jonas','Frank',date '2000-01-01'),
    ('Slade','Reed',date '2000-01-01'),
    ('Chandler','Patterson',date '2000-01-01'),
    ('Zenia','Mooney',date '2000-01-01'),
    ('Shafira','Walton',date '2000-01-01'),
    ('Ainsley','Giles',date '2000-01-01'),
    ('Camille','Stevenson',date '2000-01-01'),
    ('Omar','Graves',date '2000-01-01'),
    ('Melvin','Franco',date '2000-01-01'),
    ('Walter','Austin',date '2000-01-01'),
    ('Samson','Warner',date '2000-01-01'),
    ('Aurelia','Kirk',date '2000-01-01'),
    ('Ayanna','Hoover',date '2000-01-01'),
    ('Dara','Fox',date '2000-01-01'),
    ('Harding','Huff',date '2000-01-01'),
    ('Yardley','Lara',date '2000-01-01'),
    ('Kuame','Little',date '2000-01-01'),
    ('Ainsley','Wilcox',date '2000-01-01'),
    ('Hu','Howe',date '2000-01-01'),
    ('Martin','Ewing',date '2000-01-01'),
    ('Judah','Taylor',date '2000-01-01'),
    ('Lacota','Schroeder',date '2000-01-01'),
    ('Upton','Fields',date '2000-01-01'),
    ('Kennan','Rush',date '2000-01-01'),
    ('Barry','Benton',date '2000-01-01'),
    ('Darius','Miranda',date '2000-01-01'),
    ('Cameran','Mcintyre',date '2000-01-01'),
    ('Shoshana','Kennedy',date '2000-01-01'),
    ('Lawrence','Dorsey',date '2000-01-01'),
    ('Wynter','Clark',date '2000-01-01'),
    ('Regina','Zamora',date '2000-01-01'),
    ('Tucker','Reeves',date '2000-01-01'),
    ('Lamar','Horn',date '2000-01-01'),
    ('Hyacinth','Ayala',date '2000-01-01'),
    ('Maile','Wells',date '2000-01-01'),
    ('Melissa','Adkins',date '2000-01-01'),
    ('Cassidy','Leach',date '2000-01-01'),
    ('Xandra','Stark',date '2000-01-01'),
    ('Kiara','Lyons',date '2000-01-01'),
    ('Alana','Oneil',date '2000-01-01'),
    ('Miriam','Pearson',date '2000-01-01'),
    ('Nehru','Hopkins',date '2000-01-01'),
    ('Illiana','Wheeler',date '2000-01-01'),
    ('Ira','Hines',date '2000-01-01'),
    ('Logan','George',date '2000-01-01'),
    ('Colleen','Rose',date '2000-01-01'),
    ('Liberty','Yates',date '2000-01-01'),
    ('Ezekiel','Frazier',date '2000-01-01'),
    ('Trevor','Griffin',date '2000-01-01'),
    ('Genevieve','Christensen',date '2000-01-01'),
    ('Jescie','Roberson',date '2000-01-01'),
    ('Josephine','Hutchinson',date '2000-01-01'),
    ('Elmo','Vega',date '2000-01-01'),
    ('Hanna','Estrada',date '2000-01-01'),
    ('Troy','Matthews',date '2000-01-01'),
    ('Porter','Briggs',date '2000-01-01'),
    ('Mira','Maddox',date '2000-01-01'),
    ('Reece','Cook',date '2000-01-01'),
    ('Jolie','Workman',date '2000-01-01'),
    ('Edan','Carpenter',date '2000-01-01'),
    ('Tanek','Livingston',date '2000-01-01'),
    ('Holmes','Campos',date '2000-01-01'),
    ('Idola','Davidson',date '2000-01-01'),
    ('Imani','Weaver',date '2000-01-01'),
    ('Octavia','Ford',date '2000-01-01'),
    ('Hadley','Suarez',date '2000-01-01'),
    ('Robin','Nguyen',date '2000-01-01'),
    ('James','Middleton',date '2000-01-01'),
    ('Keiko','Clemons',date '2000-01-01'),
    ('Ryder','Huff',date '2000-01-01'),
    ('Peter','Chan',date '2000-01-01');

UPDATE accounts
SET
    is_admin = case when random() < 0.9 then 0::BOOLEAN else 1::BOOLEAN end,
    valid_until= (date '2000-01-01' + floor(random()*365*20)::integer)::date;

-- books

INSERT INTO books (title, author_name, author_surname)
VALUES
    ('The Old Maid', 'Menard', 'Nehemiah'),
    ('Offside', 'Rolf', 'Shaughn'),
    ('En rachâchant', 'Emlyn', 'Elisabeth'),
    ('That''s Life!', 'Lance', 'Elladine'),
    ('Made in Hong Kong (Xiang Gang zhi zao)', 'Patience', 'Ardyth'),
    ('Jaws: The Revenge', 'Boyce', 'Fraser'),
    ('Before Flying Back to Earth (Pries parskrendant i zeme)', 'Kathy', 'Peg'),
    ('Art and Craft', 'Leslie', 'Kimberlyn'),
    ('Class', 'Allistir', 'Tara'),
    ('Blind Sunflowers, The (Los girasoles ciegos)', 'Caryn', 'Raine'),
    ('Ella Lola, a la Trilby', 'Lavina', 'Melvyn'),
    ('How She Move', 'Sissie', 'Ginger'),
    ('Brutal Beauty: Tales of the Rose City Rollers', 'Gisella', 'Lucky'),
    ('Show Boat', 'Tabor', 'Romonda'),
    ('Tale of Two Cities, A', 'Dolley', 'Yanaton'),
    ('Dr. Bronner''s Magic Soapbox', 'Yolanthe', 'Michael'),
    ('Sealed Cargo', 'Maudie', 'Maribelle'),
    ('Night of the Demons 2', 'Tomkin', 'Daphna'),
    ('Band Wagon, The', 'Lionel', 'Dorian'),
    ('Dishonored', 'Clifford', 'Emlyn'),
    ('Me and you (io e te)', 'Ax', 'Hersch'),
    ('West Is West', 'Cobby', 'Rolando'),
    ('The Land Before Time VI: The Secret of Saurus Rock', 'Faustine', 'Layton'),
    ('Mabel''s Married Life', 'Marlin', 'Tamara'),
    ('Viva', 'Kev', 'Kym'),
    ('Little Boy Blue', 'Jeth', 'Kameko'),
    ('Joyriders, The', 'Timothy', 'Tatiana'),
    ('Barefoot (Barfuss)', 'Xenia', 'Skippie'),
    ('Heartburn', 'Alan', 'Rosana'),
    ('Incredible Rocky Mountain Race', 'Bertrand', 'Marcelia'),
    ('See You Tomorrow, Everyone', 'Reade', 'Benny'),
    ('Last Days, The (Últimos días, Los)', 'Alis', 'Caesar'),
    ('Love Letters', 'Con', 'Anjela'),
    ('Shed No Tears (Känn ingen sorg)', 'Aldis', 'Zed'),
    ('Undercover Blues', 'Salomone', 'Kary'),
    ('Udaan', 'Shaun', 'Ronda'),
    ('Maybe, Maybe Not (Bewegte Mann, Der)', 'Selinda', 'Theodore'),
    ('Criminals', 'Pavel', 'Charlie'),
    ('Charly', 'Talbert', 'Licha'),
    ('Jerry Springer: Ringmaster', 'Jenna', 'Olivero'),
    ('Two Ninas', 'Nollie', 'Karna'),
    ('St. Valentine''s Day Massacre, The', 'Alverta', 'Cristy'),
    ('Desert Heat (Inferno)', 'Seumas', 'Zebedee'),
    ('Lucifer Rising', 'Neil', 'Isidro'),
    ('Priest', 'Stanleigh', 'Estele'),
    ('Bad Ass', 'Devlen', 'Jessa'),
    ('Dragon Ball: Sleeping Princess in Devil''s Castle (Doragon bôru: Majinjô no nemuri hime)', 'Pattin', 'Ardelia'),
    ('Heading South (Vers le sud)', 'Urban', 'Charlie'),
    ('Gregory Crewdson: Brief Encounters', 'Elenore', 'Clarita'),
    ('Happy Endings', 'Morna', 'Hildegaard'),
    ('Texas Chainsaw Massacre: The Next Generation (a.k.a. The Return of the Texas Chainsaw Massacre)', 'Dita', 'Rhea'),
    ('Girl from Monday, The', 'Bryana', 'Joellyn'),
    ('Return to Never Land', 'Neill', 'Taddeo'),
    ('Daddy''s Dyin''... Who''s Got the Will?', 'Andie', 'Odilia'),
    ('Wild One, The', 'Forest', 'Jarrett'),
    ('Permanent Midnight', 'Gregorius', 'Edna'),
    ('Concussion', 'Christye', 'Lucienne'),
    ('Walking Dead, The', 'Neill', 'Sinclare'),
    ('Operation Homecoming: Writing the Wartime Experience', 'Edvard', 'Neda'),
    ('Dead Man on Campus', 'Yehudi', 'Oriana'),
    ('LEGO Batman: The Movie - DC Heroes Unite', 'Edan', 'Gauthier'),
    ('Satan''s Blood (Escalofrío)', 'Vidovik', 'Emanuel'),
    ('Basquiat', 'Jacquelynn', 'Vidovik'),
    ('Hyenas (Hyènes)', 'Glory', 'Alvinia'),
    ('Home Sweet Hell', 'Edwin', 'Gunther'),
    ('Big Hit, The', 'Roxine', 'Taddeo'),
    ('James Dean Story, The', 'Laird', 'Andreana'),
    ('Mouchette', 'Levon', 'Alvin'),
    ('Fuehrer''s Face, Der', 'Matilda', 'Phoebe'),
    ('Hope Floats', 'Bowie', 'Nicol'),
    ('Hangar 18', 'Carmon', 'Hilde'),
    ('Happy Campers', 'Phillipp', 'Umeko'),
    ('3 Blind Mice', 'Esmaria', 'Geralda'),
    ('Hollywood Shuffle', 'Callie', 'Nelli'),
    ('Last Frontier, The', 'Nicholas', 'Paxon'),
    ('The Time Being', 'Lenard', 'Kean'),
    ('Out 1: Spectre', 'Faina', 'Sutherlan'),
    ('Atlantic City', 'Regan', 'Xylia'),
    ('Coco Chanel', 'Kendricks', 'Kennie'),
    ('Dear Me', 'Gerrard', 'Weston'),
    ('Blood and Concrete (Blood & Concrete: A Love Story)', 'Chlo', 'Gale'),
    ('It''s My Party', 'Winne', 'Ellwood'),
    ('Sekirei', 'Dido', 'Rubetta'),
    ('Fear Strikes Out', 'Anitra', 'Corby'),
    ('American Me', 'Hernando', 'Guthry'),
    ('Grand Illusion (La grande illusion)', 'Cullie', 'Sibby'),
    ('Scandal', 'Keary', 'Debbi'),
    ('Quest', 'Renell', 'Zondra'),
    ('Reaching for the Moon', 'Clarisse', 'Jobye'),
    ('Buckskin Frontier', 'Ranique', 'Michaeline'),
    ('Lost for Life', 'Geri', 'Barny'),
    ('Something Wicked This Way Comes', 'Aeriel', 'Brandice'),
    ('Ring, The', 'Lurette', 'Guthrey'),
    ('Remember the Night', 'Fabiano', 'Amargo'),
    ('United', 'Axe', 'Farrell'),
    ('Nobody''s Children (I figli di nessuno)', 'Winny', 'Jonas'),
    ('The Land Before Time VI: The Secret of Saurus Rock', 'Bo', 'Janet'),
    ('Azumi 2: Death or Love', 'Jarrett', 'Phaedra'),
    ('Strange Voyage', 'Elset', 'Kayne'),
    ('Aces and Eights', 'Leeann', 'Sheena');

--categories

INSERT INTO categories (category) VALUES
                                      ('Comedy'),
                                      ('Romance'),
                                      ('Documentary'),
                                      ('Crime'),
                                      ('Drama'),
                                      ('Fiction'),
                                      ('Fantasy'),
                                      ('Sci-Fi'),
                                      ('Mystery'),
                                      ('Thriller'),
                                      ('Romance'),
                                      ('Westerns'),
                                      ('Dystopian'),
                                      ('Contemporary');



UPDATE categories
SET term = ((random() * 100)+ 10)::int;



--copies

DROP FUNCTION IF EXISTS random_category;
CREATE FUNCTION random_category() returns table (id int) LANGUAGE SQL AS
$$
SELECT id FROM categories ORDER BY RANDOM() LIMIT 1
$$;

DROP FUNCTION IF EXISTS random_warehouse;
CREATE FUNCTION random_warehouse() returns table (id int) LANGUAGE SQL AS
$$
SELECT id FROM warehouses ORDER BY RANDOM() LIMIT 1
$$;

INSERT INTO copies (is_in_library,is_in_warehouse,copy_state, is_lendable, book_id, category_id,warehouse_id )
SELECT case when random() < 0.5 then 0::BOOLEAN else 1::BOOLEAN end,false,5,false,id,random_category(),random_warehouse() from books cross join generate_series(1, 10) as seq(i);

UPDATE copies
SET is_lendable = case when random() < 0.2 then 0::BOOLEAN else 1::BOOLEAN end,
    is_in_warehouse = case when random() < 0.3 then 0::BOOLEAN else 1::BOOLEAN end,
    copy_state = floor(random()*10)+1
WHERE is_in_library is false;

UPDATE copies
SET is_lendable = case when random() < 0.2 then 0::BOOLEAN else 1::BOOLEAN end,
    copy_state = floor(random()*10)+1
WHERE is_in_library is true;

UPDATE copies
SET warehouse_id = null
WHERE is_in_warehouse is false;

--penalties

DROP FUNCTION IF EXISTS random_reader;
CREATE FUNCTION random_reader() returns table (id int) LANGUAGE SQL AS
$$
SELECT id FROM accounts WHERE is_admin IS false ORDER BY RANDOM() LIMIT 1
$$;


INSERT INTO penalties (delay,is_damaged,amount, is_paid, account_id)
SELECT
    ((floor(random()*3)+1)*5),
    CASE floor(random()*2)
        WHEN 0 THEN false
        WHEN 1 THEN true
        END,
    floor(random()*30),
    CASE WHEN random() < 0.3 then 0::BOOLEAN else 1::BOOLEAN END,
    random_reader()

from generate_series(1, 20) as seq(i);



--rentals

DROP FUNCTION IF EXISTS random_copy;
CREATE FUNCTION random_copy() returns table (id int) LANGUAGE SQL AS
$$
SELECT id FROM copies WHERE is_lendable IS TRUE ORDER BY RANDOM() LIMIT 1
$$;


insert into rentals (date_from, date_to, is_returned, account_id, copy_id )
select d, d + interval '1 months' , case when random() < 0.2 then 0::BOOLEAN else 1::BOOLEAN end,random_reader(), random_copy()
from (select date '2020-01-01'  + (random() * (interval '890 days')) as d
      from generate_series(1, 800) as seq(i)) as tmp;

insert into rentals (date_from, date_to, is_returned, account_id, copy_id )
select d, d + interval '1 months' , false,random_reader(), random_copy()
from (select date '2021-04-01'  + (random() * (interval '15 days')) as d
      from generate_series(1, 50) as seq(i)) as tmp;


--requests
insert into requests (date_from, date_to, is_rented, account_id, copy_id )
select d, d + interval '3 days' , case when random() < 0.9 then 0::BOOLEAN else 1::BOOLEAN end,random_reader(), random_copy()
from (select date '2015-01-01'  + (random() * (interval '5 years')) as d
      from generate_series(1, 1000) as seq(i)) as tmp2;










