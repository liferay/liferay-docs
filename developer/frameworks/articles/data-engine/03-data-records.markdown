---
header-id: data-records
---

# Data Records

[TOC levels=1-4]

Once you have a data definition and a Data Record Collection, it's time to start
collecting data.

The example calls in this article use [cURL](https://curl.haxx.se/), a cross-platform tool for making
HTTP calls. Feel free to use any REST client you're familiar with.

For more API documentation, paste the contents of
[`data-engine-rest-impl/rest-openapi.yaml`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/data-engine/data-engine-rest-impl/rest-openapi.yaml)
into the [Swagger Editor](https://editor.swagger.io/) and get auto-generated
Open API docs. Better yet, visit the `liferayinc` section of
[SwaggerHub](https://app.swaggerhub.com/search?type=API&owner=liferayinc) and
see all the headless APIs you can consume, including the Data Engine.

## Adding Data Records

Add Data Records using a POST call to the Data Record Collections API,
specifying the record collection's ID and the `data-records` endpoint in the
URL. As usual, send the requests with the Authorization and Content-Type
headers:

```sh
curl -i -X POST \
    http://localhost:8080/o/data-engine/v1.0/data-record-collections/{data_record_collection_id}/data-records \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
{
    "dataRecordValues":
        {
            "Product": "Bicycle"
        }
}
```

The `dataRecordValues` must contain the field names with their values. In
addition, send any other required properties in the request body:

## Retrieving Data Records

Construct GET requests to retrieve data records.

### Retrieving all the Data Records for a Data Record Collection

Send a GET request to the `data-record-collections` service, adding the
collection's ID as a URL parameter:

```sh
curl -i -X GET \
    http://localhost:8080/o/data-engine/v1.0/data-record-collections/37296/data-records \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
```

Structured JSON is returned in the response:

```json
{
  "items" : [ {
    "dataRecordCollectionId" : 37296,
    "dataRecordValues" : {
      "Product" : "Skateboard"
    },
    "id" : 37300
  }, {
    "dataRecordCollectionId" : 37296,
    "dataRecordValues" : {
      "Product" : "Bicycle"
    },
    "id" : 37303
  } ],
  "lastPage" : 1,
  "page" : 1,
  "pageSize" : 20,
  "totalCount" : 2
} 
```

### Retrieving Data Records as a String: Export

Exporting data is a common requirement for applications. To retrieve an
unstructured String of data records, send the same GET request to retrieve the
data records of a data record collection, but specify the `export` endpoint:

```sh
curl -i -X GET \
    http://localhost:8080/o/data-engine/v1.0/data-record-collections/37296/data-records/export \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
```

This returns a String array of the data records for the record collection with
the ID `37296`, in the format

```json
[{\"Record 1 Field Name\":\"Field Value"},{\"Record 2 Field Name\":\"Field Value"}]
```

This contrasts with the GET call above (that hits the `data-records` endpoint,
instead of `export`), which results in a fuller response of structured JSON.

### Retrieve a Data Record by Its ID

Send a GET request to the `data-records` endpoint, adding the data record's ID
as a URL parameter:

```sh
curl -i -X GET \
    http://localhost:8080/o/data-engine/v1.0/data-records/37602 \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
```

The JSON for the record is returned:

```json
{
  "dataRecordCollectionId": 37296,
  "dataRecordValues": {
    "Product": "Roller Skates"
  },
  "id": 37602
}
```

## Deleting Data Records

Send a DELETE request to the `data-records` endpoint, adding the data record's
ID as a URL parameter.

```sh
curl -i -X DELETE
    http://localhost:8080/o/data-engine/v1.0/data-records/37602 \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
```

This request deletes the record with the ID `37602`. To delete all the records
associated with a record collection, instead delete the record collection
itself.

## Updating Data Records

Send a PUT request to the `data-records` endpoint, passing the record ID as a URL
parameter:

```sh
curl -i -X PUT
    http://localhost:8080/o/data-engine/v1.0/data-records/37303 \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
```

Create the request body with the JSON to send to the service:

```json
{
    "dataRecordValues": {
        "Product":"Bike"
    }
}
```

If  there's an existing record with the ID sent in the URL parameter (`37303` in
this case), it will be updated with the data sent in the request body. If record
`37303` was created with a _Product_ field name of _Bicycle_, this request would
change the value to _Bike_.

For more endpoints and details, visit the `liferayinc/data-engine` section of
[SwaggerHub](https://app.swaggerhub.com/apis/liferayinc/data-engine/1.0#/DataRecord).

