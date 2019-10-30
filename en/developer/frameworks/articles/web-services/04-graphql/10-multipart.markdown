---
header-id: multipart-requests
---

# Multipart Requests

[TOC levels=1-4]

Several mutations accept a binary file via a multipart request. For example, the definition for posting a file to a `DocumentFolder` specifies a multipart request, `Upload` type in GraphQL:

![Figure 1: Create Document](../../../images/graphql-mutation-upload.png)

The GraphQL specification doesn't support natively multipart uploads but there is an [extension](https://github.com/jaydenseric/graphql-multipart-request-spec) contributed by the community that covers that use case. 

Liferay's implementation follows that extension and allows uploading files with cURL/Altair/multipart requests.

Due to a bug, we have to enable multipart file uploading in a Liferay servlet, adding this to the web.xml of the server:

```xml
<servlet>
        <servlet-name>Module Framework Servlet</servlet-name>
        <servlet-class>
            com.liferay.portal.module.framework.ModuleFrameworkServletAdapter
        </servlet-class>
        <load-on-startup>1</load-on-startup>
        <async-supported>true</async-supported>
        <multipart-config>
            <location>/tmp</location>
            <max-file-size>20848820</max-file-size>
            <max-request-size>418018841</max-request-size>
            <file-size-threshold>1048576</file-size-threshold>
        </multipart-config>
</servlet>
```

Altair configuration to upload files is simple, using the selector to upload one or multiple files and define a variable in the query.

![Figure 1: Create Document](../../../images/graphql-mutation-upload-altair.png)

```
mutation($file: [Upload]) {
  createSiteDocument(multipartBody: $file, siteKey: "guest") {
    id
    title
  }
}
```

We've defined the variable `file` because we are only uploading one. If we wanted to define several, the variable should be called $files and each file should have a numeric sequence, like: files.0, files.1, files.2...

All multipart APIs allow sending a JSON file with the metadata of the file (title, description...). That parameter should be the second file uploaded, with content similar to:

    document={\"title\": \"Alternative name\"}"

And here's the response: 

```json
{
  "data": {
    "createSiteDocument": {
      "id": 37701,
      "title": "99-rest-generator.markdown"
    }
  }
}
```

The cURL request is slightly different (Altair is filling the variables for us):

```bash
curl 'http://localhost:8080/o/graphql' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
-F operations='{"query":"mutation($files: [Upload]) {createSiteDocument(multipartBody: $files, siteId: 20122) {id}}","variables": { "files": [null] } }' \
-F map='{ "0": ["variables.files.0"]}' \
-F 0=@"99-rest-generator.markdown"
```

## Related Topics
