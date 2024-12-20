# Welcome to Card-X

### Card-X is plateform where you can apply degital card or physical card with amazing card designs with your specific name.

## Endpoints (cardx/rest/v1)
### Get Endpoints
1. /welcome
2. /card/designs
3. /card/features
4. /user/emails
5. user/login
6. user/profile
7. health/status
8. /quotes

### Post Endpoints
1.  /card/help/add
2. /user/add
3. /user/changePassword
4. /mail/send
5. /upload
6. /payment/create
7. /payment/execute

## Tables - Database - sql file.
``` 
    > need to create tables (Database: cardx)
    > carddesigns
    > features
    > help
    > helpmessage
    > users
```
> Find in SQL-File-backup branch  (https://github.com/daksh23/Card-X---backend/tree/SQL-FIle-backup)


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

### Logging
``` Location: cardx/logs ```

### Upload Images - Profile Picture
``` Angular asset directory path required as user.profile.image.location== in cardx.properties file```

### Paypal
``` need to add own paypal sdk details - client id and secret in  sdk_config.properties ```

## UI - Angular
> https://github.com/daksh23/Card-X-UI