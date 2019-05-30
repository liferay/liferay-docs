# Creating Data Record Collections with HTTP

We'll use cURL, a cross-platform tool for making HTTP calls. Feel free to use
any REST client.

## Adding, Retrieving, and Deleting Data Record Collections

At a bare minimum, you need the Data Definition's ID. If you copied it from the
response message after you added the Data Definition, you're all set. If you
need to look it up, send a GET request for the site's data definitions. In this
case you'll need the site ID, which can be retrieved from Site Administration
&rarr; Configuration &rarr; Settings.

In this example, the site ID is 20123:

```sh
curl -i -X GET   http://localhost:8080/o/data-engine/v1.0/sites/20123/data-definitions   -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'   -H 'Content-Type: application/json' \
```

The response body contains the Data Definition's data, including the `id`. Copy
it.

Now use it to add a Data Record Collection:

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

### Retrieving a Site's Data Record Collections

```sh
curl -i -X GET \
  http://localhost:8080/o/data-engine/v1.0/sites/20123/data-record-collections \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
```

### Deleting a Site's Data Record Collections
