# Creating Data Definitions

We'll use `curl` since it's a cross-platform tool. Feel free to use any REST
client.

### Adding DataDefinition

Get the `siteId` from the Site Menu &rarr; Configuration &rarr; Site Settings.

In my case it's 20123.

```sh
curl -i -X POST \
  http://localhost:8080/o/data-engine/v1.0/sites/20123/data-definitions \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
  -d '{
    "dataDefinitionFields": [
        {
            "fieldType": "text",
            "name": "product"
        }
    ],
    "description": {
            "value": "Description"
    },
    "name": {
            "value": "Order"
    }
}'
```

Here you're specifying the endpoint for the data definitions API, passing in
the `siteId`. Then you're creating a request body that includes:

- `dataDefinitiionFields` 
- `description` 
- `name`

There are plenty more elements you can add to the request body. 

<!--Put them in here, with their functions-->

This returns a DataDefinition in the body. You will need its `id` in the next
request.

```sh
HTTP/1.1 200 
X-Content-Type-Options: nosniff
X-Frame-Options: SAMEORIGIN
X-XSS-Protection: 1
Set-Cookie: JSESSIONID=19FBDA81A72F8EA1F9668A153E9F2FBA; Path=/; HttpOnly
Expires: Thu, 01 Jan 1970 00:00:00 GMT
Cache-Control: private, no-cache, no-store, must-revalidate
Pragma: no-cache
Set-Cookie: COOKIE_SUPPORT=true; Max-Age=31536000; Expires=Fri, 22-May-2020 20:18:15 GMT; Path=/; HttpOnly
Set-Cookie: GUEST_LANGUAGE_ID=en_US; Max-Age=31536000; Expires=Fri, 22-May-2020 20:18:15 GMT; Path=/; HttpOnly
Date: Thu, 23 May 2019 20:18:16 GMT
Content-Type: application/json
Content-Length: 307


```json
{
  "dataDefinitionFields" : [ {
    "fieldType" : "text",
    "indexable" : true,
    "localizable" : false,
    "name" : "product",
    "repeatable" : false
  } ],
  "dateCreated" : "2019-05-23T20:18:16Z",
  "dateModified" : "2019-05-23T20:18:16Z",
  "id" : 39108,
  "siteId" : 20123,
  "userId" : 20129
}
```

## CURL Commands


### Data Definition 

Create a data definition with the POST method using the siteId (20123 here):

```sh
curl -i -X POST \
  http://localhost:8080/o/data-engine/v1.0/sites/20123/data-definitions \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
  -d '{
    "dataDefinitionFields": [
        {
            "fieldType": "text",
            "name": "product"
        }
    ],
    "description": {
            "value": "Description"
    },
    "name": {
            "value": "Order"
    }
}'
```

Get the site's data definitions with the GET method (20123 is siteId):

```sh
curl -i -X GET \
  http://localhost:8080/o/data-engine/v1.0/sites/20123/data-definitions \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
```

Delete a data definition by its ID (39108 here):

```sh
curl -i -X DELETE \
  http://localhost:8080/o/data-engine/v1.0/data-definitions/39108 \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
  -d '{
    "dataDefinitionFields": [
        {
            "fieldType": "text",
            "name": "product"
        }
    ]}'
```

### Data Record Collection

Add a data record collection

```sh
curl -i -X POST \
  http://localhost:8080/o/data-engine/v1.0/data-definitions/39207/data-record-collections \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
  -d '{
    "description": {
        	"key": "en_US",
          "value": "description"
    },
    "name": {
         	"key": "en_US",
          "value": "All Orders"
    }
}'
```

```sh
curl -i -X GET \
  http://localhost:8080/o/data-engine/v1.0/sites/20123/data-record-collections \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
```

### Data Records

#### app-builder

```sh
curl -i -X POST \
  http://localhost:8080/o/data-engine/v1.0/data-record-collections/39212/data-records \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
  -d '{

        "dataRecordValues": {

            "name": "product",
            "value": "Bicycle"
        }
}'
```

#### Guestbook

```sh
curl -i -X POST \
  http://localhost:8080/o/data-engine/v1.0/data-record-collections/${dataRecordCollectionId}/data-records \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
  -d '{
    "dataRecordValues": [
        {
            "key": "name",
            "value": "Russ"
        },
        {
            "key": "message",
            "value": "The Lunar Luau was Amazing!"
        }
    ]
}'
```



















