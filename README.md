# Scala Akka Http and Slick Demo based CRUD REST operations


### Create a Customer

Using CURL:

[source, java]
----
curl -X "POST" "http://127.0.0.1:8080/v1/customers" \
       -H "Accept: application/json" \
       -H "Content-Type: application/json" \
       -d $'{
  "name": "Customer1",
  "age": 21,
  "gender": 2
    }'
----

### Get All Customers

[source, scala]
----
curl -X "GET" "http://127.0.0.1:8080/v1/customers"
----

### Get A Customer

----
curl -X "GET" "http://127.0.0.1:8080/v1/customers/1"
----

### Update Customer

----
curl -X "PUT" "http://127.0.0.1:8080/v1/customers/1" \
     -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     -d $'{
  "id": 1,
  "name": "Customer1",
  "age": "21",
  "gender": "2"
}'

----

### Delete Customer
----
curl -X "DELETE" "http://127.0.0.1:8080/v1/customers/1" \
     -H "Accept: application/json" \
     -H "Content-Type: application/json"
----

### Author - Sergiy Koyev

