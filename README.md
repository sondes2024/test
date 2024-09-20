 

# Courses manager application



the courses manager application permits to manage the life cycle of a race and its runners(horses)

 * Follow this steps to create a topic called "course-topic":

1\ Start your containers using the following command:
   docker-compose up -d 

2\ Enter the kafka-cli container using the following command:
docker exec -it coursesmanager-kafka-cli-1 bash

3\ Run the following command to list topics using the following command:
kafka-topics --list --bootstrap-server broker:9092

4\ if you don't have s topic called "test-topic" ,lets create one
using the following command:
kafka-topics --create --topic test-topic --bootstrap-server broker:9092 --partitions 1 --replication-factor 1

5\ After creating the topic, try listing all the topics again to ensure it has been created successfully:
kafka-topics --list --bootstrap-server broker:9092

  
 