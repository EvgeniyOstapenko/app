delete from user_role;
delete from usr;

insert into usr(id, active, password, username) values
(1, true, '$2a$08$anc97QHvKt5MK4DILmAaLujQR93OJyuHiH1fu9VeCjzyNV7Nu7DWS', 'dru'),
(2, true, '$2a$08$anc97QHvKt5MK4DILmAaLujQR93OJyuHiH1fu9VeCjzyNV7Nu7DWS', 'mike');

insert into user_role(user_id, roles) values
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER');