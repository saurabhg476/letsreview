# Letsreview

Letsreview is an open-source platform with an aim to allow anyone to share review on any topic. An android app that demonstrates this idea can be found at https://github.com/saurabhg476/letsreview-android-app.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

1. Mysql version 5.6 
2. Maven 
3. Eclipse IDE 
4. Tomcat Server 7.0

### Installing

* Clone the repo in your local enviroment. Make sure master branch is checked out.
* Setup the database by running the scripts present in the file database_script.sql.
* Import the project as maven project in eclipse.
* Configure your db properties in applicationContext.xml file.
* Open project properties. Go to Deployment Assembly. Click on Add, select Java Build Path Properties, then choose Maven Dependencies.
* Create tomcat server in eclipse.
* Add letsreview war to tomcat
* Start the server.

You can call any api to confirm whether the server is up and running. 
For example:
```
GET http://<serverAddress>:<port>/letsreview/topics?q=amazon
will search for "amazon" in the list of topics.

Depending on whether that topic is present or not you shall got the following output:

If the topic is present:

OUTPUT : 
{
  "topicsList": [
    {
      "name": "amazon",
      "summary": "e-commerce corporation"
    }
  ],
  "status": "SUCCESS",
  "code": "00"
}

If topic is not present:
OUTPUT : 
{
  "topicsList": [],
  "status": "SUCCESS",
  "code": "00"
}

```
## Built With

* [Spring](https://spring.io/docs) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management and Build tool
* [Mysql](https://dev.mysql.com/doc/) - Database

## Contributing

Anybody is more than welcome to contribute

## Authors

See the list of [contributors](https://github.com/your/project/contributors) who participated in this project.


