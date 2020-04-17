---
header-id: graphql-multipart-requests
---

# Multipart Requests

[TOC levels=1-4]

Several mutations accept a binary file via a multipart request. For example, the
definition for posting a file to a `DocumentFolder` specifies a multipart
request, `Upload` type in GraphQL:

![Figure 1: Create Document accepts a `multipartBody`.](../../../images/graphql-mutation-upload.png)

The GraphQL specification doesn't support natively multipart uploads, but an 
[extension](https://github.com/jaydenseric/graphql-multipart-request-spec)
contributed by the community covers that use case. 

Liferay's implementation includes that extension and allows uploading files.

Multipart support in GraphQL is disabled by default. To enable it, add the
configuration to upload multipart files in the Liferay application's `web.xml`
file:

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

To test, use the Altair configuration to upload files. Use the selector to
upload one or multiple files and define a variable in the query.

![Figure 2: Creating a Document in Altair is easy with the selector.](../../../images/graphql-mutation-upload-altair.png)

```
mutation($file: [Upload]) {
  createSiteDocument(multipartBody: $file, siteKey: "guest") {
    id
    title
  }
}
```

The variable above is `file` because there's only one. If you wanted to upload
several files, name the variable `$files` and each file should have
a numeric sequence: `files.0`, `files.1`, `files.2`, etc.

All multipart APIs allow sending a JSON file containing the file's metadata
(title, description, etc.). That parameter should be the second file uploaded
(defined using the `file.0`, `file.1` syntax). 

For example, 

```
document={\"title\": \"Alternative name\"}"
```

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

The cURL request is slightly different (Altair fills out the variables):

```bash
curl 'http://localhost:8080/o/graphql' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' \
-F operations='{"query":"mutation($files: [Upload]) {createSiteDocument(multipartBody: $files, siteId: 20122) {id}}","variables": { "files": [null] } }' \
-F map='{ "0": ["variables.files.0"]}' \
-F 0=@"99-rest-generator.markdown"
```

