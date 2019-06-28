create table message_likes(
user_id int8 not null references usr,
message_id int8 not null references message,
primary key(message_id, user_id)
);