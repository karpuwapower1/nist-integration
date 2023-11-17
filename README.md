# nist-integration
run docker-compose up -d and then go to localhost:8080. On the first start, the application will upload data.
GET localhost:8080/cpe/{id} will return record by id
GET localhost:8080/cpe/ids/?ids=&ids= will return records with ids
GET localhost:8080/cpe/names/?names=&names= will return records with ids

GET localhost:8080/ will start loading data (bypass)
GET localhost:8080/update will run update job (bypass)
