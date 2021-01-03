![img](https://i0.wp.com/programmingtechie.com/wp-content/uploads/2019/11/Login-Flow.png?fit=1024%2C576&ssl=1)

1. So it starts with the Client sending a login request to the server.
2. The server checks the credentials provided by the user, if the credentials are right, it creates a JSON Web Token (JWT).
3. It responds with a success message (HTTP Status 200) and the JWT.
4. The client uses this JWT in all the subsequent requests to the user, it  provides this JWT as an Authorization header with Bearer authentication  scheme.
5. When the server, receives a request against a secured  endpoint, it checks the JWT and validates whether the token is generated and signed by the server or not.
6. If the validation is successful, the server responds accordingly to the client.

![img](https://i0.wp.com/programmingtechie.com/wp-content/uploads/2019/11/Spring-Login-Flow.png?fit=1024%2C575&ssl=1)

- The login request is received by **AuthenticationController** and is passed on to the **AuthService** class.
- This class creates an object of type **UserNamePasswordAuthenticationToken** which encapsulates the username and password provided by the user as part of the login request.
- Then this is passed on to **AuthenticationManager** which takes care of the authentication part when using Spring Security. It implements lot of functionality in the background and provides us  nice API we can use.
- The **AuthenticationManager** further interacts with an interface called **UserDetailsService**, this interface as the name suggests deals with user data. There are  several implementations that can be used depending on the kind of  authentication we want. There is support for in-memory authentication,  database-authentication, LDAP based authentication.
- As we store  our user information inside the Database, we used Database  authentication, so the implementation access the database and retrieves  the user details and passes **UserDetails** back to **AuthenticationManager**.
- The **AuthenticationManger** now checks the credentials, and if they match it creates an object of type **Authentication** and passes it back to the **AuthService** class.
- Then we create the JWT and respond back to the user.