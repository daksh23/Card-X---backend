# Welcome to Card-X

### Card-X is plateform where you can apply degital card or physical card with amazing card designs with your specific name.

## Endpoints (cardx/rest/v1)
### Get Endpoints
1. /welcome
2. /quotes ( caching added 3rd party endpoint calling : https://type.fit/api/quotes )
3. /user/{id}
4. /user/ids
5. /user/emails
6. /card/design/{id}

### Post Endpoints
1. /user/add
2. /product/add
3. /card/design/add
4. /socialmedia/add
5. /user/address/add

## Tables
> need to create tables (Database: cardx)
1. userdetailsrequest
2. eventrequest
3. productrequest
4. socialmediarequest
5. carddesign
6. addressrequest

## Technologies
1. Java
2. Spring Boot
3. maven

## Important Notes

## Application.properties
### For SMTP Server - Need to set you Mail ID and Password
```
>> you can generate app password from google manage account (steps below)
* Login to Gmail
* Manage your Google Account
* Security
* App Passwords
* Provide your login password
* Select app with a custom name
* Click on Generate
```

### Gmail
```
* spring.mail.host=smtp.gmail.com
* spring.mail.port=587
```

### For Caching
```
* spring.cache.jcache.config=classpath:ehcache.xml
```

## UI
> ui repo will create soon ( AngularJs )
