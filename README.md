# MessageVerifier

Message Verifier app verifies json message structure, saves the message to Postgres and sends to Kafka. 

### How to run:
1. Go to the app folder
1. Run `mvn clean package`
2. Run `docker build -t message-sender .`
3. Run `docker compose up`

With default configuration server will be run on `localhost:8080`

### How to send a message:
To send a message you can send POST request to `/api/message/` endpoint with following body:
```json
{
    "ts": "1530228282",
    "sender": "testy-test-service",
    "message": {
        "foo": "bar",
        "baz": "bang"
    },
    "sent-from-ip": "1.2.3.4",
    "priority": 2
}
```


