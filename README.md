[![Codacy Badge](https://api.codacy.com/project/badge/Grade/29dd0bb705bc4df386ae2eb38bd69ca4)](https://www.codacy.com/app/shun10114/renew-shooney?utm_source=github.com&utm_medium=referral&utm_content=Seolhun/renew-shooney&utm_campaign=badger)
[![Waffle.io - Columns and their card count](https://badge.waffle.io/Seolhun/renew-shooney.svg?columns=all)](https://waffle.io/Seolhun/renew-shooney)
---
# Hi-Cord Blog Project
- Author : [Seol Hun](https://github.com/Seolhun)
- StartedDate : 2017.09.09

## Goal of Hi-Cord
Why develop this project?
- I want that developer connect with others interested in common stacks each other.
    - We already have a nice code hub like 'Git-hub'. so, I want to integrate code and blog information at the same time to manage easily.
    - We will serve the developer-blog based on 'Git-Hub' service. Never have a blog not based on 'Git-Hub'.
    - You can follow good project and developer easily and message, chatting, etc many things can use in Hi-Cord Blog project.
    - Just write a code on 'Git-hub', 'Hi-Cord' serve information for others automatically.

## Enviroment
### Back-end
- Java
    - Spring Boot
    - Spring Security, Oauth2
    - Spring Stomp, Websocket, SockJS
    - Spring Actuator
    - JPA, Hibernate

- NodeJS
    - Express
    - SockerIO

- GraphQL
    - Apollo

- Cache
    - Redis or Elastic Cache

- Python (Data Analysis Module)
    - Flask
    - NLP

- AWS
    - Bean Stalk
    - Lambda
    - S3
    - RDB
    - DynamoDB
    - Kinesis

### Front-end
- Vue2 Cli
- Vuex
- TypeScript
- D3.js

---
### Build & Integration System
- Gradle
- Docker
- Travis
- Codacy
- Waffle.io

### Util
- Post Man

---
## Module
- User
    - Group
- Pay
- Content
    - File
    - Comment
- Log
- NLP

---
## Architecture
### BackEnd Service Architecture
<img src="readme/architecture.png" width="900" height="600">

### FrontEnd Components Architecture
<img src="frontend/readmeImage/ItemPage.png" width="900" height="600">

---
### Job CheckList doing now.
- Backend
    - Java
        - User Module
            - [ ] Oauth2

        - Content Module
            - Content
                - [X] Content CRUD
                    - [ ] Insert Thread Executor 
                - [ ] 
            - Comment
                - [ ] Comment CRUD          
            - File
                - [X] File Upload
            - Spam
                - [ ] Spam CRUD
                - [X] Spam Processing
            - Tag
                - [X] Tag Insert with Content
                - [ ] Tag Visualization Chart   
        - Log Module
            - [ ] Common Accessed LOG

    - NodeJS
        - Message
            - 
        - Git Hub API
            - 
        - Slack API
        - KaKao API

- Frontend
    - User Module
        - [ ] User CRUD
    - Content Module
        - Content
            - [X] Content CRUD
                - [ ] Insert Thread Executor 
        - Comment
            - [ ] Comment CRUD          
        - File
            - [X] File Upload
        - Spam
            - [ ] Spam CRUD
            - [X] Spam Processing
        - Tag
            - [ ] Tag CRUD
            - [ ] Tag Visualization Chart   

---
### Reference Link
##### BackEnd
- [Spring boot - Reference Documentation](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/)
    - [Spring Data JPA - Reference Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Hibernate 5 - Reference Documentation](https://docs.jboss.org/hibernate/orm/5.0/devguide/en-US/html/)
    - [Hibernate 5 - About Batch - Reference Documentation](https://docs.jboss.org/hibernate/orm/5.0/devguide/en-US/html/ch04.html)
- [AWS - Reference Documentation](https://aws.amazon.com/ko/documentation/)

- Thrid Parties
    - [Lombok](https://projectlombok.org/)
    - [Gson](https://github.com/google/gson)

---
##### FrontEnd
- [Vue - Reference Documentation](https://kr.vuejs.org/v2/guide/)
    - [Vue - API - Reference Documentation](https://kr.vuejs.org/v2/api/)
- [TypeScript - Reference Documentation](https://www.typescriptlang.org/docs/handbook/basic-types.html)
- [ECMA6 - Reference Documentation](http://es6-features.org/#Constants)
- [Webpack - Reference Documentation](https://webpack.github.io/)
- [Babel - Reference Documentation](https://babeljs.io/)

- Thrid Parties
    - [Axios - Reference Documentation](https://github.com/axios/axios)
    - [Animate CSS - Reference Documentation](https://daneden.github.io/animate.css/)
