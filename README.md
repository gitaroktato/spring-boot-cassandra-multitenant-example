# Multitenancy wih spring-boot-cassandra
Example for switching Cassandra keyspace on a per-request basis.

# Project Structure
 - `src/main/cql` - Contains CQL scripts for setting up multi-tenant environment
 - `src/main/jmeter` - Simple JMeter test for verifying if implementation is correct
 - `vagrant` - Stock Cassandra VM configuration for VirtualBox (Doesn't install CQL scripts automatically)


# Side notes

This is started from the following Stackoverflow question:
https://stackoverflow.com/questions/46964654/cassandra-customer-data-per-keyspace