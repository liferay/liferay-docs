# Data Records

Once you have a data definition and a Data Record Collection, it's time to start
collecting data.

These example calls use cURL, a cross-platform tool for making HTTP calls. Feel
free to use any REST client you're familiar with.

For more examples, paste the contents of
[`data-engine-rest-impl/rest-openapi.yaml`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/data-engine/data-engine-rest-impl/rest-openapi.yaml)
into the [Swagger Editor](https://editor.swagger.io/) and get auto-generated
Open API docs. Better yet, just visit the `liferayinc` section of
[SwaggerHub](https://app.swaggerhub.com/search?type=API&owner=liferayinc) and
see all the headless APIs you can consume.

## Adding Data Records

Add Data Records using the Data Record Collections API, specifying the record
collection's ID and the `data-records` endpoint in the URL. As usual, send the
requests with the Authorization and Content-Type headers:

```sh
curl -i -X POST \
    http://localhost:8080/o/data-engine/v1.0/data-record-collections/37296/data-records \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
{
    "dataRecordValues":
        {
            "Product": "Bicycle"
        }
}
```

Send the `dataRecordValues` for the form fields in the request payload:

## Retrieving Data Records

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

This returns a String array of the data records, in the format

```json
[{\"Record 1 Field Name\":\"Field Value"},{\"Record 2 Field Name\":\"Field Value"}]
```

This contrasts with the GET call above (that hits the `data-records` endpoint,
instead of `export`), which results in a fuller response of structured JSON.

## Deleting Data Records

### Delete a Data Record

Send a DELETE request to the `data-records` endpoint, adding the data record's
ID as a URL parameter.

```sh
curl -i -X DELETE
    http://localhost:8080/o/data-engine/v1.0/data-records/37296 \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
```

For more endpoints, visit the `liferayinc/data-engine` section of
[SwaggerHub](https://app.swaggerhub.com/apis/liferayinc/data-engine/1.0#/DataRecord).

