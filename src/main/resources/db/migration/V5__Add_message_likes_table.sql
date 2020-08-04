create table message_likes (
  user_id int8 not null references usr,
  message_id int8 not null references message,
  primary key (user_id, message_id)
)