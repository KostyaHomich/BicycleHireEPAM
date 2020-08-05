Use this login and password for join to Jenkins server  http://localhost:8090/.
 user name: admin password: admin
 user name: user password: user
 
 If you wanna change from hibernate to jdbc go to service module pom file and uncomment block with jdbc dependencie.
 Although comment hibernate dependencie. Next step is write "mvn clean install tomcat7:redeploy" to the command line.