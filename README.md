# NMM Mill Service

## Docker

* clone repo
* start docker (vm)
* build docker image
    * run inside repo:
    * `docker build -t nmm:mill-service .`
* run docker container
    * start in background:
    ```
    docker run -d -i -p 8081:8081 --name mill-srv  nmm:mill-service
    ```
    
    * start in foreground and interactive: 
    ```
    docker run -i -a stdout -p 8081:8081 --name mill-srv-i  nmm:mill-service
    ```
* stop docker container
    ```
    docker stop mill-srv[-i]
    ```
* start docker container again
    * non-interactive
    ```
    docker start mill-srv
    ```
    * interactive
    ```
    docker start -a mill-srv-i
    ```
