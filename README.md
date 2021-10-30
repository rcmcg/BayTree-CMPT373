**Intro**

<p>
An application for managing mentor/mentee relationships at the Baytree
Centre in London. There are 3 components to the application. The
<b>user-frontend</b> is seen by the mentor when they want to upload information regarding mentoring sessions or access resources. The <b>admin-frontend</b> allows an administrator at Baytree to manage accounts, access statistics, etc. The backend is used to receive and process API requests from the frontends as well as manage the database.
</p>

**User-Frontend**
<p>
Make sure you have Node Package Manager installed before attempting to run the user-frontend. To start the user-frontend, change directory to user-frontend in your terminal. Then enter npm install in terminal. Then enter npm start. The application should start in a browser.  
</p>

**Admin-Frontend**
<p>
Make sure you have Node Package Manager installed before attempting to run the admin-frontend. To start the admin-frontend, change directory to admin-frontend in your terminal. Then enter npm install in terminal. Then enter npm start. The application should start in a browser.
</p>


****Backend****

- Load the directory named API on IntelliJ
- Before running the application make sure you have JDK and maven installed on your machine
- Run the application on IntelliJ
    - Build the project using `mvn dependency:tree` Make sure you in the directory "baytree_mentoring" which has the pom file   
    - To run the application open the file BaytreeMentoringApplication (api > baytree_mentoring > src > main > java > )
        - When in file "BaytreeMentoringApplication" to run the application simply click the run button on the line number 9.    
    - The server should be up and running 



**Send POST Request through POSTMAN**
(Make sure you are connected to the SFU VPN)


- `http://localhost:8080/session/add`

Formatting Example: { {"menteeId":7145682,"clockInTimeLocal":"2021-10-20 06:21:55 -0800","clockOutTimeLocal":"2021- 10-25 11:25:38 -0800", "sessionNotes":"Notes3"}

Test if the added session is returend using the GET API mentioned below

- `http://localhost:8080/session/add`

Formatting Example: 
`{ "username": "anonsnx", "password": "testtwo" }`

Test if the added users username and password is retrived using the GET API mentioned below



**Send GET Request through POSTMAN**
(Make sure you are connected to the SFU VPN)


- `http://localhost:8080/session/get/all`
Displays all the data stored for the sessions on our backend MySql database (hosted on the VM)


- `http://localhost:8080/user/get/all`
Displays all the username and passwords stored for the test users on our backend MySql database (hosted on the VM)


**Send DELETE Request through POSTMAN**

(Make sure you are connected to the SFU VPN)

- `http://localhost:8080/session/delete/{sessionid}`

Example: "http://localhost:8080/session/delete/1", if I want to delete the first item

