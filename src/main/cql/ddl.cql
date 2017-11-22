create KEYSPACE customer1 WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };
create KEYSPACE customer2 WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };

create table customer1.user (
  	username text,
  	email text,
  	primary key (username, email)
);


create table customer2.user (
  	username text,
  	email text,
  	primary key (username, email)
);
