delete from message;

insert into message(id, tag, text, user_id) values
(1, 'my-tag', 'first', 1),
(2, 'more', 'second', 1),
(3, 'my-tag', 'third', 1),
(4, 'another', 'fourth', 2);

alter sequence hibernate_sequence restart with 10;