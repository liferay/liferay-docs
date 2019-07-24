# Creating Data Record Collections with HTTP

Data Record Collections are used to collect Data Records in the Data Engine. A
Data Definition defines the fields present in the Data Record, while the Data
Record Collection forms a boundary, collecting only records associated with its
Primary Key. The Data Record Collection has a unique ID, a name, and a
description. Each individual Data Record Collection is associated with a single
Data Definition's Primary Key. 

These example calls use cURL, a cross-platform tool for making HTTP calls. Feel
free to use any REST client you're familiar with.

## Adding, Retrieving, and Deleting Data Record Collections

At a bare minimum, you need the Data Definition's ID. If you copied it from the
response message after you added the Data Definition, you're all set. If you
need to look it up, send a GET request for the site's data definitions. In this
case you'll need the site ID, which can be retrieved from Site Administration
&rarr; Configuration &rarr; Settings.

In this example GET call, the site ID is 20123:

```sh
curl -i -X GET   http://localhost:8080/o/data-engine/v1.0/sites/20123/data-definitions   -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'   -H 'Content-Type: application/json' \
```

The response body contains the Data Definition's data, including the `id`. Copy
it.

Now use it to add a Data Record Collection. Here the data definition ID is
`86524`:

```sh
curl -i -X POST \
  http://localhost:8080/o/data-engine/v1.0/data-definitions/86524/data-record-collections \
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

In addition to sending POST calls to add data record collections, you can
retrieve all the site's data record collections.

### Retrieving a Site's Data Record Collections

Pass the site ID in to the GET request:

```sh
curl -i -X GET \
  http://localhost:8080/o/data-engine/v1.0/sites/20123/data-record-collections \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
```

### Deleting a Site's Data Record Collections


Delete data record collections with a DELETE request. You'll need the data
record collection ID:

```sh
```sh
curl -i -X DELETE \
  http://localhost:8080/o/data-engine/v1.0/data-record-collections/85637 \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json'
```

For more examples, paste the contents of
[`data-engine-rest-impl/rest-openapi.yaml`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/data-engine/data-engine-rest-impl/rest-openapi.yaml)
into the [Swagger Editor](https://editor.swagger.io/) and get auto-generated
Open API docs. Better yet, just visit the `liferayinc` section of
[SwaggerHub](https://app.swaggerhub.com/search?type=API&owner=liferayinc) and
see all the headless APIs you can consume.


