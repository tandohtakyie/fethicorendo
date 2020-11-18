USE corendon;

# countries
INSERT INTO countries (id, code, name) VALUES (1, "NL", "Nederland");
INSERT INTO countries (id, code, name) VALUES (2, "TR", "Turkije");
INSERT INTO countries (id, code, name) VALUES (3, "CY", "Cyprus");
INSERT INTO countries (id, code, name) VALUES (4, "MR", "Marokko");
INSERT INTO countries (id, code, name) VALUES (5, "GR", "Griekenland");
INSERT INTO countries (id, code, name) VALUES (6, "MK", "Macedonië");
INSERT INTO countries (id, code, name) VALUES (7, "ES", "Spanje");
INSERT INTO countries (id, code, name) VALUES (8, "PT", "Portugal");
INSERT INTO countries (id, code, name) VALUES (9, "EG", "Egypte");
INSERT INTO countries (id, code, name) VALUES (10, "TN", "Tunesië");
INSERT INTO countries (id, code, name) VALUES (11, "AE", "Verenigde Arabische Emiraten");
INSERT INTO countries (id, code, name) VALUES (12, "BG", "Bulgarije");
INSERT INTO countries (id, code, name) VALUES (13, "GM", "Gambia");
INSERT INTO countries (id, code, name) VALUES (14, "IT", "Italië");

# airports
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (1, "Amsterdam", 1, 1, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (2, "Antalya", 2, 3, 0);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (3, "Istanbul", 2, 3, 0);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (4, "Bodrum", 2, 3, 0);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (5, "Dalaman", 2, 3, 0);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (6, "Izmir", 2, 3, 0);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (7, "Gazipasa-Alanya", 2, 3, 0);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (8, "Nicosia-Ercan", 3, 2, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (9,  "Marrakech", 4, 0, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (10, "Crete (Heraklion)", 5, 2, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (11, "Kos", 5, 2, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (12, "Rodes", 5, 2, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (13, "Zakynthos", 5, 2, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (14, "Corfu", 5, 2, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (15, "Mytilene", 5, 2, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (16, "Ohrid", 6, 1, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (17, "Samos", 5, 2, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (18, "Gran Canaria", 7, 0, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (19, "Tenerife", 7, 0, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (20, "Palma de Mallorca", 7, 0, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (21, "Malaga", 7, 0, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (22, "Fuerteventura", 7, 0, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (23, "Faro", 8, 0, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (24, "Lanzarote", 7, 0, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (25, "Hurghada", 9, 2, 0);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (26, "Enfidha", 10, 1, 0);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (27, "Dubai", 11, 4, 0);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (28, "Burgas", 12, 2, 1);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (29, "Banjul", 13, 0, 0);
INSERT INTO airports (id, name, country_id, timezone, daylight_saving) VALUES (30, "Sicily (Catania)", 14, 1, 1);

# luggage_types
INSERT INTO luggage_types (id, name) VALUES (1, "Koffer");
INSERT INTO luggage_types (id, name) VALUES (2, "Tas");
INSERT INTO luggage_types (id, name) VALUES (3, "Rugzak");
INSERT INTO luggage_types (id, name) VALUES (4, "Doos");
INSERT INTO luggage_types (id, name) VALUES (5, "Sporttas");
INSERT INTO luggage_types (id, name) VALUES (6, "Zakenkoffer");
INSERT INTO luggage_types (id, name) VALUES (7, "Kist");
INSERT INTO luggage_types (id, name) VALUES (8, "Anders");

# colors
INSERT INTO colors (id, ral_code, name) VALUES (1, 1003, "Geel");
INSERT INTO colors (id, ral_code, name) VALUES (2, 1024, "Olijf");
INSERT INTO colors (id, ral_code, name) VALUES (3, 2004, "Oranje");
INSERT INTO colors (id, ral_code, name) VALUES (4, 3000, "Rood");
INSERT INTO colors (id, ral_code, name) VALUES (5, 3005, "Donkerrood");
INSERT INTO colors (id, ral_code, name) VALUES (6, 3017, "Roze");
INSERT INTO colors (id, ral_code, name) VALUES (7, 4005, "Paars");
INSERT INTO colors (id, ral_code, name) VALUES (8, 4010, "Violet");
INSERT INTO colors (id, ral_code, name) VALUES (9, 5002, "Blauw");
INSERT INTO colors (id, ral_code, name) VALUES (10, 5015, "Lichtblauw");
INSERT INTO colors (id, ral_code, name) VALUES (11, 5022, "Donkerblauw");
INSERT INTO colors (id, ral_code, name) VALUES (12, 6004, "Blauwgroen");
INSERT INTO colors (id, ral_code, name) VALUES (13, 6002, "Groen");
INSERT INTO colors (id, ral_code, name) VALUES (14, 6022, "Donkergroen");
INSERT INTO colors (id, ral_code, name) VALUES (15, 6038, "Lichtgroen");
INSERT INTO colors (id, ral_code, name) VALUES (16, 7015, "Grijs");
INSERT INTO colors (id, ral_code, name) VALUES (17, 9011, "Donkergrijs");
INSERT INTO colors (id, ral_code, name) VALUES (18, 7000, "Lichtgrijs");
INSERT INTO colors (id, ral_code, name) VALUES (19, 8002, "Bruin");
INSERT INTO colors (id, ral_code, name) VALUES (20, 8011, "Donkerbruin");
INSERT INTO colors (id, ral_code, name) VALUES (21, 8023, "Lichtbruin");
INSERT INTO colors (id, ral_code, name) VALUES (22, 9001, "Wit");
INSERT INTO colors (id, ral_code, name) VALUES (23, 9005, "Zwart");
INSERT INTO colors (id, ral_code, name) VALUES (24, 1015, "Crème");

# brands
INSERT INTO brands (id, name) VALUES (1, "Samsonite");
INSERT INTO brands (id, name) VALUES (2, "Eastpak");
INSERT INTO brands (id, name) VALUES (3, "Kipling");
INSERT INTO brands (id, name) VALUES (4, "Enrico Benetti");

# statuses
INSERT INTO statuses (id, name) VALUES (1, "Being delivered");
INSERT INTO statuses (id, name) VALUES (2, "Delivered");
INSERT INTO statuses (id, name) VALUES (3, "In flight");
INSERT INTO statuses (id, name) VALUES (4, "Found");
INSERT INTO statuses (id, name) VALUES (5, "Lost");
INSERT INTO statuses (id, name) VALUES (6, "Damaged");

# flights
INSERT INTO flights (id, flightnumber) VALUES (1, "CA 1014");
INSERT INTO flights (id, flightnumber) VALUES (2, "CA 1015");
INSERT INTO flights (id, flightnumber) VALUES (3, "CA 7029");
INSERT INTO flights (id, flightnumber) VALUES (4, "CA 1017");
INSERT INTO flights (id, flightnumber) VALUES (5, "CA 1404");
INSERT INTO flights (id, flightnumber) VALUES (6, "CA 2505");

# labels
INSERT INTO labels (id, tag, flight_id) VALUES (1, "TTAAGG", 1);
INSERT INTO labels (id, tag, flight_id) VALUES (2, "OLOLOS", 5);
INSERT INTO labels (id, tag, flight_id) VALUES (3, "QQNORQ", 4);
INSERT INTO labels (id, tag, flight_id) VALUES (4, "BGSJZN", 4);
INSERT INTO labels (id, tag, flight_id) VALUES (5, "LOL04X", 1);

# passengers
INSERT INTO passengers (id, first_name, middle_name, last_name, address, postcode, city, country_id, email, phone)
VALUES (1, "Hans", NULL, "Jong", "Vijverplein 33", "1337LT", "Amsterdam", 3, "Henkjong@hotmail.nl", "+360 12345678");
INSERT INTO passengers (id, first_name, middle_name, last_name, address, postcode, city, country_id, email, phone)
VALUES (2, "Piet", NULL, "Jan", "Wibautsstraat 36", "1337LT", "Amsterdam", 4, "Piet@gmail.com", "+360 87654321");
INSERT INTO passengers (id, first_name, middle_name, last_name, address, postcode, city, country_id, email, phone)
VALUES (3, "Sjors", "de", "Snor", "Kibaan 300", "1234AB", "Hoorn", 2, "Snorren@gmail.com", "+360 12345678");
INSERT INTO passengers (id, first_name, middle_name, last_name, address, postcode, city, country_id, email, phone)
VALUES (4, "Koen", NULL, "Grooteman", "Dam 48", "8008SS", "Utrecht", 2, "Koen@hotmail.com", "+360 12345678");
INSERT INTO passengers (id, first_name, middle_name, last_name, address, postcode, city, country_id, email, phone)
VALUES (5, "Hans", "De", "Groot", "Gemaal 15", "1337LT", "Enkhuizen", 6, "Hans.G@gmail.com", "+360 12345678");

# insurance companies
INSERT INTO insurance_companies(id, name) VALUES (1, "ANWB");
INSERT INTO insurance_companies(id, name) VALUES (2, "Centraal Beheer");
INSERT INTO insurance_companies(id, name) VALUES (3, "Unive");
INSERT INTO insurance_companies(id, name) VALUES (4, "OHRA");
INSERT INTO insurance_companies(id, name) VALUES (5, "Allianz");
INSERT INTO insurance_companies(id, name) VALUES (6, "Nationale Nederlanden");
INSERT INTO insurance_companies(id, name) VALUES (7, "FBTO");
INSERT INTO insurance_companies(id, name) VALUES (8, "Zelf");
INSERT INTO insurance_companies(id, name) VALUES (9, "Reaal");
INSERT INTO insurance_companies(id, name) VALUES (10, "Ditzo");
INSERT INTO insurance_companies(id, name) VALUES (11, "Inshared");
INSERT INTO insurance_companies(id, name) VALUES (12, "Aegon");

# luggages
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (1, "35434", "1993-06-12", 1, 1, 1, 1, 1, 1, 3.1415, 12.34, 44.55, 3.1415, 1, NULL, 11);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (2, "56453", "1992-07-12", 1, 2, 1, 3, 1, 2, 3.1415, 12.34, 44.55, 3.1415, 2, NULL, 5);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (3, "54645", "1989-05-12", 1, 3, 1, 4, 1, 1, 3.1415, 12.34, 44.55, 3.1415, 1, NULL, 2);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (4, "34563", "1987-06-18", 1, 4, 3, 1, 1, 1, 3.1415, 12.34, 44.55, 3.1415, 3,  "Zwarte streep", 8);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (5, "12864", "1979-10-30", 1, 1, 1, 3, 1, 2, 3.1415, 12.34, 44.55, 3.1415, 2,  NULL, 1);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (6, "35434", "1993-06-12", 1, 1, 1, 1, 1, 1, 3.1415, 12.34, 44.55, 3.1415, 1, NULL, 3);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (7, "67546", "1992-08-12", 1, 2, 1, 3, 1, 2, 3.1415, 12.34, 44.55, 3.1415, 2, NULL, 9);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (8, "64568", "1989-05-5", 1, 3, 1, 4, 1, 1, 3.1415, 12.34, 44.55, 3.1415, 1, NULL, 7);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (9, "78935", "1987-06-13", 1, 4, 3, 1, 1, 1, 3.1415, 12.34, 44.55, 3.1415, 3,  "Metalen handvat", 7);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (10, "48654", "1979-10-23", 1, 1, 1, 3, 1, 2, 3.1415, 12.34, 44.55, 3.1415, 2,  NULL, 6);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (11, "34567", "1993-06-12", 1, 1, 1, 1, 1, 1, 3.1415, 12.34, 44.55, 3.1415, 1, NULL, 7);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (12, "87864", "1992-07-13", 1, 2, 1, 3, 1, 2, 3.1415, 12.34, 44.55, 3.1415, 2, NULL, 9);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (13, "54532", "1989-05-04", 1, 3, 1, 4, 1, 1, 3.1415, 12.34, 44.55, 3.1415, 1, NULL, 2);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (14, "78645", "1987-06-11", 1, 4, 3, 1, 1, 1, 3.1415, 12.34, 44.55, 3.1415, 3,  "Groenen lus", 12);
INSERT INTO luggages (id, registration_id, date_found, luggage_type_id, brand_id, label_id, location_found_id, primary_color_id, secondary_color_id, size_width, size_length, size_height, weight, passenger_id, other_characteristics, insurance_company_id)
VALUES (15, "78978", "1979-10-27", 1, 1, 1, 3, 1, 2, 3.1415, 12.34, 44.55, 3.1415, 2,  NULL, 10);

# statuses history
INSERT INTO statuses_history (luggage_id, status_id) VALUES (1, 1);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (1, 2);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (1, 3);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (1, 4);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (1, 5);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (2, 2);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (2, 3);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (2, 4);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (2, 5);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (3, 1);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (3, 3);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (3, 4);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (3, 6);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (4, 1);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (4, 2);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (4, 3);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (4, 4);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (4, 5);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (5, 1);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (5, 2);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (5, 3);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (5, 4);
DO SLEEP(1);
INSERT INTO statuses_history (luggage_id, status_id) VALUES (5, 5);

# functions
INSERT INTO functions (id, name) VALUES (1, "Manager");
INSERT INTO functions (id, name) VALUES (2, "Balie Medewerker");
INSERT INTO functions (id, name) VALUES (3, "Teamleider");

# department locations
INSERT INTO department_locations (id, location) VALUES (1, "Madrid");
INSERT INTO department_locations (id, location) VALUES (2, "Amsterdam");
INSERT INTO department_locations (id, location) VALUES (3, "Tokyo");
INSERT INTO department_locations (id, location) VALUES (4, "China");

# users
INSERT INTO users (id, username, first_name, middle_name, last_name, birth_date, email, function_id, employment_date, country_id, password, image, reset_password)
VALUES (1, "peter", "Peter", "", "Dimitrov", "1998-06-28", "lol@lol.lol", 1, "1998-06-29", 4, "3wordsallcaps", NULL, FALSE);
INSERT INTO users (id, username, first_name, middle_name, last_name, birth_date, email, function_id, employment_date, country_id, password, image, reset_password)
VALUES (2, "tom", "Tom", "", "Wassing", "1998-06-28", "lol@lol.lol", 1, "1998-06-29", 4, "Welkom01", NULL, FALSE);
INSERT INTO users (id, username, first_name, middle_name, last_name, birth_date, email, function_id, employment_date, country_id, password, image, reset_password)
VALUES (3, "vince", "Vince", "de", "Leeuw", "1999-12-04", "vincedeleeuw@gmail.com", 1, "1998-06-29", 4, "Welkom01", NULL, FALSE);
INSERT INTO users (id, username, first_name, middle_name, last_name, birth_date, email, function_id, employment_date, country_id, password, image, reset_password)
VALUES (4, "yessin", "Yessin", "El", "Khaldi", "1998-06-28", "lol@lol.lol", 1, "1998-06-29", 4, "Welkom01", NULL, FALSE);
INSERT INTO users (id, username, first_name, middle_name, last_name, birth_date, email, function_id, employment_date, country_id, password, image, reset_password)
VALUES (5, "dylan", "Dylan", "", "Tweebeeke", "1998-06-28", "lol@lol.lol", 1, "1998-06-29", 4, "Welkom01", NULL, FALSE);
INSERT INTO users (id, username, first_name, middle_name, last_name, birth_date, email, function_id, employment_date, country_id, password, image, reset_password)
VALUES (6, "fethi", "Fethi", "Kidane", "Tewelde", "1998-06-28", "lol@lol.lol", 1, "1998-06-29", 4, "Welkom01", NULL, FALSE);
INSERT INTO users (id, username, first_name, middle_name, last_name, birth_date, email, function_id, employment_date, country_id, password, image, reset_password)
VALUES (7, "hellings", "Jan", "", "Hellings", "1998-06-28", "hellings@hva.nl", 1, "2017-01-01", 1, "Welkom01", NULL, FALSE);
INSERT INTO users (id, username, first_name, middle_name, last_name, birth_date, email, function_id, employment_date, country_id, password, image, reset_password)
VALUES (8, "middendorp", "Henk", "", "Middendorp", "1998-06-28", "middendorp@hva.nl", 1, "2017-01-01", 1, "Welkom01", NULL, FALSE);


