# Santander bootcamp 2024
Java Restful API desenvovida no Santander Bootcamp 2024

## Diagrama de Classes

``` mermaid
classDiagram
    class User {
        +String name
    }
    class Account {
        +String number
        +String agency
        +float balance
        +float limit
    }
    class Feature {
        +String icon
        +String description
    }
    class Card {
        +String number
        +float limit
    }
    class News {
        +String icon
        +String description
    }

    User --> Account : has
    User --> Feature : has many
    User --> Card : has
    User --> News : has many
```


#Rodar com profile de produção
java -jar -Dspring.profiles.active=prod target\bootcamp-2024-0.0.1-SNAPSHOT.jar
#Rodar com profile de Desenvolvimento
java -jar -Dspring.profiles.active=dev target\bootcamp-2024-0.0.1-SNAPSHOT.jar

https://pt.stackoverflow.com/questions/287187/usar-profiling-do-maven-ou-do-spring-boot