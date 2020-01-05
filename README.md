**Rest service to read csv data and store it to database

** prerequisite

- java8 or higher
- spring boot 2
- Helm chart (only for deployment on higher environment)
- gitlab configuration (only for deployment on higher environment)
- docker
- intellij or eclipse
- postman (to test endpoints) or you can use swagger url


** Run from application framework 
 
 a. Git clone -> https://github.com/shadabkhan151/csvreader-service.git <br/>
 b. Import application as maven project in intellij framework, it will take some time to install dependency <br/>
 c. Go to -> ./readnstore_csv_service/src/main/java/com/csv/CSVApplication.java <br/> and right click - choose run as java application <br/>
 d. open swagger url http://localhost:8080/readnsatore/swagger-ui.html#/ or you can use postman to test endpoints <br/>
 
 Endpoints : <br/>
     &nbsp;&nbsp;&nbsp;c.1 :  getall()   - http://localhost:8080/readnstore/v1/data <br/>
     &nbsp;&nbsp;&nbsp;c.2 :  getbyid()  - http://localhost:8080/readnstore/v1/data/{id} <br/>
     &nbsp;&nbsp;&nbsp;c.3 :  postdata() - http://localhost:8080/readnstore/v1/store/csv <br/>
 
 
 
 ** Run by docker build and run
 
 from command line go to path where Dockerfile available and run following command <br/>
 
   > docker build -t csvreaderservice:v1 . <br/>
   > docker run -d -p <target port>:<internal port> csvreaderservice:v1 <br/>
   
   Here, 'target port' = 8080 and 'internal port' = 8080
   
   Now, service available to use or test
   
   
 ** Run with existing docker image on docker hub
    
    > docker run -d -p 8081:8080 shadabkhan1407/shkhan:csvservice_v1
    
    Above command will fetch docker image in your local environment and run image