# Creating Data Definitions

We'll use cURL, a cross-platform tool for making HTTP calls. Feel free to use
any REST client.

### Adding Data Definitions

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

- `dataDefinitionFields`: The example is simple, with just a `fieldType` and a
    `name` element for one field. Here's the complete list of elements that each
    field can have:
    - `customProperties` <!-- DESCRIBE -->
    - `defaultValue`: provide a default value for a field. 
    - `id` <!-- is this a key that you can set yourself? Like the old
        DDMTemplateKey that coudl be configured in the UI? DESCRIBE --> 
    - `indexable` can be set to `true` (default)  or `false` to control whether the
        fields is indexed in the search engine.
    - `label`: set a human readable label for each of the custom properties the
        fields has.
    - `localizable`: can be set to `true` (default) or `false` to control
        whether the locale of the User is appended when he or she adds a data
        record.
    - `repeatable`: set to `true` or `false` to control whether the field
        accepts multiple values per data record.
    - `tip`: provide help text for the field's properties.
    - `name`: a required element that names the field. This defaults to `string`
        if left blank.
- `description`: use this optional element to describe the data definition.
- `name`: name the data definition.
- `dataDefinitionRules`: set validation rules for the data definition.
- `dateCreated` and `dateModified`: these are set by the system, so it's not
    necessary to do anything here.

<!--saved in the ddmStructure table. Record Collections in the ddlRecordSet
table. -->

```json
{
  "dataDefinitionFields": [
    {
      "customProperties": {
        "additionalProp1": {},
        "additionalProp2": {},
        "additionalProp3": {}
      },
      "defaultValue": {
        "additionalProp1": {},
        "additionalProp2": {},
        "additionalProp3": {}
      },
      "fieldType": "string",
      "id": 0,
      "indexable": true,
      "label": {
        "additionalProp1": {},
        "additionalProp2": {},
        "additionalProp3": {}
      },
      "localizable": true,
      "name": "string",
      "repeatable": true,
      "tip": {
        "additionalProp1": {},
        "additionalProp2": {},
        "additionalProp3": {}
      }
    }
  ],
  "dataDefinitionRules": [
    {
      "dataDefinitionFieldNames": [
        "string"
      ],
      "dataDefinitionRuleParameters": {
        "additionalProp1": {},
        "additionalProp2": {},
        "additionalProp3": {}
      },
      "name": "string",
      "ruleType": "string"
    }
  ],
  "dateCreated": "2019-05-30T20:48:25.939Z",
  "dateModified": "2019-05-30T20:48:25.939Z",
  "description": {
    "additionalProp1": {},
    "additionalProp2": {},
    "additionalProp3": {}
  },
  "id": 0,
  "name": {
    "additionalProp1": {},
    "additionalProp2": {},
    "additionalProp3": {}
  },
  "siteId": 0,
  "storageType": "string",
  "userId": 0
}
```

For more elements that can be added to your data definitions, see the
`data-engine-rest-impl/rest-openapi.yaml` file.

One your POST call is processed, a `DataDefinition` is returned in the body. You
will need its `id` to create data record collections for the definition's
records.

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

Here you can see that some informationw as added by the data engine itself:

- `id` <!-- is this a key that you can set yourself? Like the old
    DDMTemplateKey that coudl be configured in the UI? DESCRIBE --> 
- `indexable` is set to `true` by default, so the field is indexed in the search
    engine.
- `localizable` is set to `false` by default.
- `repeatable` is set to `false` by default, so submitting the same filed
    multiple times for the same data record is not allowed.
- `dateCreated` and `dateModified`: the data engine updates this metadata when
    the data definition is first created and when it's updated, respectively.
- `userId` is set to the numeric ID of the User that created the definition.

Before we move on, it's important to link the data definition and the data
record, since they're closely related. A data record must conform to the fields
defined in this data definition creation step. Therefore, when you add a data
record in a later step your request will need to use a `dataRecordValues`
structure like this:

```json
    "dataRecordValues": {

        "product": { 
            "en_US": "Bicycle"
        }
    }
```

### Retrieving Data Definitions

```ah
curl -i -X GET \
  http://localhost:8080/o/data-engine/v1.0/sites/20123/data-definitions \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
```


### Deleting Data Definitions

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
  http://localhost:8080/o/data-engine/v1.0/data-record-collections/86531/data-records \
  -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
  -H 'Content-Type: application/json' \
  -d '{

        "dataRecordValues": {

            "product": { 
                "en_US": "Bicycle"
            }
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



















