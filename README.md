## Sellers Client Application

#### Technology stack: 
SpringBoot(Spring/SpringMVC), Thymeleaf, SellersJsonProcessing library

#### Environmnet requirement:

At least JDK11, Maven 3.6.0+

#### How to install and use:

Access to project directory and type  ```mvn clean install``` , then type ```java -jar target/sellersclient-1.0.jar``` . When client app has been running, open your browser and visit  ```http://localhost:8080/``` 

#### Usage intruduction:

* Load JSON data from given url and show on the browser

  * Require a valid sellers.json url, click load button to get json data
  * Click JSON editor button to verify sellers.json raw string
* List domains from given sellers.json file
  * Upload a valid sellers.json file, click upload button to get list of domain
* List common sellers between two given sellers.json files
  * Upload two valid sellers.json files, click upload button
  * Click JSON editor button to verify common sellers json string

#### Utility Application:

There is an utility application under the project folder named crawler. The crawler will get JSON urls from  ```https://www.sellersjsons.com/#section-faq``` then download sellers.json and save in local folder.

* Crawler env requirement and usage:
  * At least Python 3.6 with pip
  * Pip install requests, beautifulsoup4
  * Run crawler.py in the crawler folder

There already has some pre-downloaded sellers.json by this application, located within jsonfiles folder.


