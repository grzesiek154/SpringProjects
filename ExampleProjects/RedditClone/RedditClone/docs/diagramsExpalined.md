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

# RefreshToken

### How to implement logout?

If you are following this series, you are already familiar that  JWT’s(JSON Web Tokens) are mainly used in the authorization flows in the web application. 

The user first provides the credentials to the server and the server  responds back to the client with a JWT if the credentials are correct. 

So the client uses this token to authorize itself for all the  subsequent requests. Usually, if the client is a web browser, the token  is stored in a form of browser storage.

So the obvious thing to do is to delete this token from the browser storage as part of the Logout implementation

### Is Logout just on the client-side enough?

Imagine if a hacker somehow got access to the token you are using.  Then he/she can impersonate the user and make requests to the backend  even after the user has chosen to logout.

### How can I overcome this?

To overcome this shortcoming, we have below ways to implement Logout/JWT Invalidation in our application.

#### Store JWT inside Database

The next option is to store the token inside the database.This  completely defeats the purpose of using JWT. JWT is by definition  stateless and the advantage of using JWT is to bypass the database  lookup when authorizing the client.

#### Implement Token Blacklisting

We can use a combination of the above-mentioned approaches to implement Token Blacklisting like below.

So the idea is to store the tokens inside an in-memory database like [Redis](https://redis.io/).

When the user log’s out from the browser we delete the token and  store this token inside Redis. On each user request, we perform a lookup against Redis and if the token is found inside, we throw an exception.

We can also improve the performance even more by removing the expired tokens from the Redis database.

#### Introduce Refresh Tokens

The next approach is to introduce a concept called Refresh Tokens.

When the client first authenticates, the server provides an  additional token called a Refresh Token(stored inside our database)  additional to the short-lived JWT.

When our JWT is expired or about to be expired, we will use the refresh token to request a new JWT from the server.

In this way, we can keep on rotating the token until the user decides to logout from the application. Once the user logs out, we will also  delete the refresh token from the database.

This leaves us with a very short window where the user logs out and the token is still valid.

As you can observe, there is no perfect way to implement logout when  using JWT. There are multiple approaches we can choose, but each one of  them has there disadvantages.

![Updated Authentication and Authorization FLow](https://i2.wp.com/programmingtechie.com/wp-content/uploads/2020/03/Refresh-Tokens.png?resize=1920%2C1080&ssl=1)

- In Step 2, after the authentication is successful, we send a Refresh Token along with the JWT back to the client.
- In Step 6, the client understands that the JWT is expired or about to be  expired and request the server to provide a new JWT by including the  Refresh Token inside the request.
- In Step 7, the server then  verifies the Refresh Token by looking it up in the database and if it  matches, generates a new JWT and responds back to the client (Step 8).