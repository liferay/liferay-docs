# Multipart Requests

Several operations accept a binary file via a multipart request. For example, 
the definition for posting a file to a `DocumentFolder` specifies a multipart 
request: 

    post:
        operationId: postDocumentFolderDocument
        parameters:
            - in: path
              name: documentFolderId
              required: true
              schema:
                  format: int64
                  type: integer
        requestBody:
            content:
                multipart/form-data:
                    schema:
                        properties:
                            document:
                                $ref: "#/components/schemas/Document"
                            file:
                                format: binary
                                type: string
                        type: object
        responses:
            200:
                content:
                    application/json:
                        schema:
                            $ref: "#/components/schemas/Document"
                    application/xml:
                        schema:
                            $ref: "#/components/schemas/Document"
                description: ""
        tags: ["Document"]

This operation returns a `Document` (in JSON or XML). To create this `Document`, 
you must supply the operation's multipart request with 2 components: 

-   A binary file (bytes) via the `file` property
-   A JSON string with the binary file's metadata, via the `document` property

To send this request, the `Content-Type` must be `multipart/form-data`, and you 
must also specify a boundary name (the boundary name can be arbitrary). 

Here's an example request (without the file's bytes) that creates a document in 
the folder with the ID `38549`: 

    curl -X "POST" "http://localhost:8080/o/headless-delivery/v1.0/document-folders/38549/documents" \
         -H 'Accept: application/json' \
         -H 'Content-Type: multipart/form-data; boundary=PART' \
         -u 'test@liferay.com:test' \
         -F "file=" \
         -F "document={\"title\": \"podcast\"}"

And here's the response: 

    {
      "contentUrl": "/documents/20123/38549/podcast.mp3/e978e316-620c-df9f-e0bd-7cc0447cca49?version=1.0&t=1556100111417",
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20129,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-24T10:01:51Z",
      "dateModified": "2019-04-24T10:01:51Z",
      "documentFolderId": 38549,
      "encodingFormat": "audio/mpeg",
      "fileExtension": "mp3",
      "id": 38553,
      "numberOfComments": 0,
      "sizeInBytes": 28482097,
      "title": "podcast"
    }

## Related Topics [](id=related-topics)
