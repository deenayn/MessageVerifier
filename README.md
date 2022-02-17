# MessageVerifier

Message Verifier app verifies json message structure, saves the message to Postgres and sends to Kafka. 

###How to run:
1. Go to the app folder
1. Run `mvn clean package`
2. Run `docker build -t message-sender .`
3. Run `docker compose up`


