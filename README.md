# Game-Of-Three

The Game Of Three is a game who two players play, who start choose a number to start, and the next one choose a small number to increment to the result by division per 3, who win is the player that getting the result 1


![](screen.gif)


Project using Spring boot and node server to run the client.

### Client

At first do you need install all node dependencies and run the server

    `cd client`
    `npm install`
    `np start` 

After the client is running you can access your 
    
    `localhost:9090`


### Server

At first you need install all maven dependencies 

    `cd server`
    `mvn install`
    
    
## URLs and Ports
Each of the modules is it's own Spring Boot Application which can be accessed as follows:

<table>
    <tr>
        <th>Name</th>
        <th>Application / Enpoint Type</th>
        <th>Port</th>
        <th>URL</th>
    </tr>
    <tr>
        <td>Client</td>
        <td>Web App</td>
        <td>9090</td>
        <td>http://localhost:9090/</td>
    </tr>
    <tr>
        <td>Server</td>
        <td>Spring Boot REST</td>
        <td>8080</td>
        <td>http://localhost:9090/ check the route in the `IndexController` file</td>
    </tr>
</table>