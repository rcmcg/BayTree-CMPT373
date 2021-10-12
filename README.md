**Frontend**

    **_User-Frontend_**

   _ **Admin-Frontend**_


**Backend**

- Load the directory named API on IntelliJ
- Run the application on IntelliJ
    - To run the application open the file BaytreeMentoringApplication (api > baytree_mentoring > src > main > java > )
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


** Send GET Request through POSTMAN** 
(Make sure you are connected to the SFU VPN)


- `http://localhost:8080/session/get/all`
Displays all the data stored for the sessions on our backend MySql database (hosted on the VM)


- `http://localhost:8080/user/get/all`
Displays all the username and passwords stored for the test users on our backend MySql database (hosted on the VM)


**Send DELETE Request through POSTMAN**
(Make sure you are connected to the SFU VPN)

- `http://localhost:8080/session/delete/{sessionid}`

Example: "http://localhost:8080/session/delete/1", if I want to delete the first item

