create KEYSPACE customer1 WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };
create KEYSPACE customer2 WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };

create table customer1.user (
  	username text,
  	primary key (username)
);


create table customer2.user (
  	username text,
  	primary key (username)
);
