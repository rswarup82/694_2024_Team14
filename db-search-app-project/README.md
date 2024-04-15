# Final Project - Search Application

### Build and run the stack
You can run the application stack (Flask application and Redis) with Docker, respectively `docker-compose`. 
```docker
docker-compose up -d --build
```

### Inorder to build searchapp separately
```
docker build -t searchapp .
```

### run a new docker container named searchapp
```
docker run --name searchapp \
    -d -p 5000:5000 \
    searchapp
```

### Test the application (API)
We can use `curl` to make requests to our API. There is one endpoint `/universities`, so let's test that out.

```
curl localhost:5000/universities?country=Germany
```

### fetch incomes from the dockerized instance
```
curl http://localhost:5000/incomes/
```

### add identity
```
curl -X POST -H "Content-Type: application/json" -d '{
    "_name": "Roger",
    "surname": "Water"
}' http://localhost:5000/identity
```

### find an identity
```
curl http://localhost:5000/identity?_name=Roger
```

### samples taken from following website

https://auth0.com/blog/developing-restful-apis-with-python-and-flask/

https://www.geeksforgeeks.org/dockerizing-a-python-flask-application-with-a-database/

https://geshan.com.np/blog/2021/12/docker-postgres/

https://realpython.com/lru-cache-python/

### List of command for psql

https://hasura.io/blog/top-psql-commands-and-flags-you-need-to-know-postgresql

https://www.geeksforgeeks.org/postgresql-psql-commands/
