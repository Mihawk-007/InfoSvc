# Info Svc
This service provides with api that can be used by the Video Stream providers, to determine the most played sections
of the video. 

#### Design Considerations
The service is designed in a way such that the code is packaged appropriately into individual packages.
The service makes use of :

* The service make use of Bean Validation to perform the request data validation for the api.
  * It makes use of built-in constraints as well as custom constraints.
* Entities are present in the entity package.
* Request and response dto is present in the dto package.
* The service makes use DAO design pattern.
* Controller and service should also be present in their individual packages.
## Api

Endpoint :
Rest api -  "/playBackInfoApi"
Method Type - "POST"

### Request Structure
```json
{
  "clientId": "",
  "videoId": "",
  "vidPlayData": [],
  "isRepeatCall": true
}
```

Few things to consider before sending values for the above json fields:

- clientId can not be null or empty & it's length should be between four and sixteen.
- videoId - can not be null or empty. Should start with `vid`. For more info look into the `RequestDto`.
- vidPlayData - List of long which indicates playback times of the video. Api assumes that a playback 
  time be first converted into "seconds" and then sent as list of long.
- isRepeatCall - To indicate if it's a repeat call with the same video and client id. If it set to true 
  the service will just update the existing record in the database.
### Response Structure
```json
{
    "clientId": "12345",
    "videoId": "vid-1",
    "mostPlayedSection": "1",
    "details": null,
    "errorMessages": null
}
```
Few Details to consider here:

- clientId - Id passed in the request.
- videoId - Id passed in the request.
- mostPlayedSection - Would process the `vidPlayData` to retrieve the mostPlayedSection.
- details - Future use case. When a new get endpoint will be created, this field can be used to return all the details of video playback. Some modifications may be needed.
- errorMessages - Returns a list of string which represents errors, which occurs either during
  request validation or during db operation.

In case of successful api call a status of `200` is returned, in case of a incorrect data `400`
is returned along with the request body.

## Testing
Due to time limitations I am unable to write unit and integration tests. However, I have attached the 
Outbox testing evidence in [here](src/main/resources/static/evidences) that I have done.

## Steps to run the service
In order the build and run the service you would need :

- Jdk 17 or higher
- Maven v3.0 higher
- Mysql db & mysql workbench

If you have all three, then follow the following steps :

- Open the project in the ide.
- Open mysql workbench
  - You can use the root connection here, but if you want to create another connection do it just remember the username and password and the port you used.
  - Now log in to the connection and create a db. Just run the following command in 
    as a sql script in workbench.
    - `CREATE DATABASE  IF NOT EXISTS 'give a name of you choice'`.
- Go [here](src/main/resources/application.properties).This is where we are configuring our data source.
- To configure the url - just replace the part after the last slash with whatever name you have given in the above sql query.
  - I am hoping this is all you need, but if you have give another port to the connection, then you will have to change the "url" slightly.
- In username and password, use the ones that you have used to login to workbench connection.
- Now, after all the above steps, un-comment the commented out line in the `application.properties`file and run the application.
  - Tables will be created automatically, just make sure to comment that line again, otherwise whenever you will run it will drop the tables first and then recreate it.
- That's it, you can hit the api on postman.

### Documentation
I have added adequate code and class comments, you should not have much problem.



