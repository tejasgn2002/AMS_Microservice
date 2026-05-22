## User Register
api: POST 
```bash localhost:8082/user/register ```
body: 
```bash
{
    "name":"Tejas",
    "username":"tejas123",
    "password":"Tejas@123",
    "customerCategory":"gold",
    "phone": 9876543212,
    "email":"tejas@gmail.com",
    "address":"Blore",
    "dateOfBirth":"2002-01-08"
}
```

## Users List
api: GET
```bash localhost:8082/user/list ```


## User Login
api: POST
```bash localhost:8082/user/login ```
body:
```bash 
{
    "username":"tejas123",
    "password":"Tejas@12"
}
```
