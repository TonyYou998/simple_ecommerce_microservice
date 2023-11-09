# SIMPLE ECOMMERCE
## 1. Brief introduction
This is a simple ecommerce web built with Spring boot, Mysql, ReactJs alongs with some other tools like Keycloak.
This project contains
+ 3 database
+ 3 services 
+ A discovery server
+ An API gateway
+ An Authorization server
+ A configuratiion server
+ An UI web app
## 2. current issue
Cause of urgent time for this project i am unable to delivere this app with my first vision. There still some glitches while using. My app currently has basic features like signin,signout,choose products,add to cart and checkout.
## 3. Guides
All services except frontend are deployable to Docker container.
To run my project simply open a terminal in the root of project then type
```docker-compose up -d```
All containers will be started in minutes, up to your machines the time to start containers could be longer. 
I suggest your machine should have 8GB of ram at least and 16GB of ram for best performance.
The priority to start containers as below:
1. Databases containers
2. Configuration server
3. Gateway server
4. Keycloak server
5. Cart_service,order_service and product_service
6. Web UI
To run the web UI first you have to dowload its dependencies by command
```npm i```
then
```npm start```
to run the web
In fact, with docker-compose command everything will be done for you just make sure all containers are ran in the right order if there is any issue.
The product_service automatically seed some products so that User can use immediatelly.
When user first access the web they will be asked for login/register.

## 4. Config Keycloak
Keycloak is an authentication server, the default username and password for keycloak is admin/admin. 
For simple, i provided 3 keycloak configuration the first one is "master-realm.json" for config keycloak realm which contains react-app client ID matches with the keycloak js library in the web configuration. Make sure the client ID in keycloak matches with client id used in UI config, otherwise, user will not be able to login or register.
The second file is "master-users-0.json" file this file contains all user account i have created in keycloak.
finally, "react-app.json" is keycloak's client config if you were unable to import keycloak config file just use the default real of keycloak and import this file in create client.
In general, for quickly start up please import my real configuration, there is a button to create new realm.
## 5. Reference
My github repo https://github.com/TonyYou998/simple_ecommerce_microservice
DB design https://drive.google.com/file/d/14EV5ys112Bu0Fz2RigLRgLa4b6idBgLi/view?usp=sharing