---
header-id: data-layouts
---

# Data Layouts

[TOC levels=1-4]

Data Layouts control the display of a form backed by the data engine. The basic
functionality is to lay the fields out in _rows_ and _columns_.

## Adding Data Layouts

Add data layouts by making a POST request to the `data-layouts` endpoint,
passing in the ID of the data definition the data layout is to be created for:

```sh
curl -i -X POST \
    http://localhost:8080/o/data-engine/v1.0/data-definitions/{data_definition_id}/data-layouts \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
{
    "dataLayoutPages": [
        {
            "dataLayoutRows": [
                {
                    "dataLayoutColums": [
                        {
                            "columnSize": "1",
                            "fieldNames": [
                                "fieldName"
                            ]
                        }
                    ]
                }
            ],
            "description": {"en_US":"description"},
            "title": {"en_US":"title"}
        }
    ],
    "description": {"en_US":"description"},
    "name": {"en_US":"name"},
    "paginationMode": "pagination"
}
```

Here you can create multiple pages, laying out your fields in column and rows.
The `paginationMode` element can be set to `pagination` (for numbered pages) or
`wizard` (for navigation buttons, like arrows). Each page can have a title and
description, and the `dataLayout` itself can have a name and description (a name
is required).

## Retrieving Data Layouts

### Retrieving all the Data Layouts for a Data Definition

Send a GET request to the `data-definitions` resource's `data-layouts` endpoint,
passing in the data definition ID as a URL parameter: 

```sh
curl -i -X GET \
    http://localhost:8080/o/data-engine/v1.0/data-definitions/{data_definition_id}/data-layouts \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
```

### Retrieving the Data Layout Matching a Key

Send a GET request to the `data-layouts` endpoint, passing in both the
`{site_id}` and the `{data_layout_key}` as URL parameters:

```sh
curl -i -X GET \
    http://localhost:8080/o/data-engine/v1.0/sites/{site_id}/data-layouts/{data_layout_key} \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
```

`dataLayoutKey` is a String that can be set when adding a data layout via
POST. Think of it like an identifier for data layouts that you, can control. And
here, in this GET request, you can retrieve data layouts by the same key.

### Retrieving a Paginated List of Data Layouts Matching Keywords

To retrieve data layouts matching some keywords (perhaps passed via user input,
like a search), send a GET request to the `data-layout` endpoint:

```sh
curl -i -X GET \
    http://localhost:8080/o/data-engine/v1.0/sites/{site_id}/data-layout?keywords=dog&page=1&pageSize=5
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
```
Notice that the keywords, page number, and page size are added as query
parameters.

`keywords` are the search keywords to match data layouts by. The `name` and

`description` Data layout fields are matched to the keywords sent here.

`page` specifies the page of results to return (as an integer), if there are
more layouts returned than the `pageSize`.

`pageSize` is the number of layouts to include on each page. 

## Deleting Data Layouts

Send a DELETE request to the `data-layouts` endpoint, passing the ID of the
layout to delete as the concluding URL parameter:

```sh
curl -i -X DELETE \
    http://localhost:8080/o/data-engine/v1.0/data-layouts/{data_layout_id} \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
```

## Updating Data Layouts

To update an existing data layout, send a PUT request to the `data-layouts`
endpoint, using the `dataLayoutId` as a URL parameter:

```sh
curl -i -X PUT \
    http://localhost:8080/o/data-engine/v1.0/data-layouts/{data_layout_id} \
    -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
    -H 'Content-Type: application/json' \ 
{
    "dataLayoutPages": [
        {
            "dataLayoutRows": [
                {
                    "dataLayoutColums": [
                        {
                            "columnSize": "1",
                            "fieldNames": [
                                "fieldName"
                            ]
                        }
                    ]
                }
            ],
            "description": {"en_US":"alternate description"},
            "title": {"en_US":"alternate title"}
        }
    ],
    "description": {"en_US":"description"},
    "name": {"en_US":"name"},
    "paginationMode": "pagination"
}
```

This example just changes the description and title for the layout added in the
original POST request.

Now you can get started laying out your forms (data definitions) for the best
user experience possible.
