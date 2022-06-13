# BusTicketBooking-CRUD

Overview-
  In this application we have used technologies java springboot, Jpa, Graphql, Postgres etc and version of java is 11. In this application we have done crud operations on bus, passanger and booking using Rest controller as well as using graphql also for more practice i have added autor and tutorials entity and perform operations using graphql. I have checked the user inputs and validate it. If user send a request which is not satisfied then we have handled the exception so we are able to send user friendly message if requirement will not fulfilled.
  
Flow of Application-
  In our application there are multiple layers like controller, model, service, etc. When user send a request then first request come to particular controller. Based on user request controller send the request on particular method. We have used service layer to execute the logic so this request is executed in paricular service class. To perform the crud operation we have used the jpaRepository. JpaRepository provides us built in methods which we can directly use to perform our operations. once we have done operation we have send the proper response to the user and if operation is not succedded the we have handle the exception and send a user friendly message.
  
About Graphql-
  BusTicketBooking application has two more entities author and tutorials. We have done their functionalities using graphql. The main purpose of implementation of graphql is to understand the difference between rest api and graphql. So i have seen the differences like 
  1. In rest api we can perform different operations using multiple endpoints where in case of graphql we can do different operations using single endpoint.
  2. The GET request of rest api gives us all data where we want or not but in graphql we can retrieve the data which we want.
  3. Rest api Uses a server-driven architecture while graphql uses a client-driven architecture.

Graphql flow-
  Under resources folder we have created the folder graphql. In this folder we have define the graphql schema files which has extension .graphqls example- author.graphqls. In this file we have defined the schema like type, query and mutation. Generally we are using query for data retrieval like get all authors, count all tutorials like this example query is below
            query{
               findAllAuthors{
               Id
               name
               age
               }
            }
  and if we want to post some data the we have used the mutation. means we want to create, update or delete any resource the we use this. below example shows the mutation to create author.
            mutation{
              createAuthor(name : "XYZ", age:27)
              {
                Id
                name
                age
              }
            }
            
Once we done with our schema then it is mapped to Resolver layer where we defined this methods seperately in query and mutation class which implements the GraphqlQueryResolver and GraphqlMutationResolver accordingly. In this classes we again used the our repository and perform the paricular operation.
