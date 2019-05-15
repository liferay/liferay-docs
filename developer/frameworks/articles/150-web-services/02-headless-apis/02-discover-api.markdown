---
header-id: get-started-discover-the-api
---

# Get Started: Discover the API

[TOC levels=1-4]

To begin consuming web services, you must first know where they are (e.g., a 
service catalog), what operations you can invoke, and how to invoke them. 
Because @product@'s headless REST APIs leverage 
[OpenAPI](https://en.wikipedia.org/wiki/OpenAPI_Specification) 
(originally known as Swagger), you don't need a service catalog. You only need 
to know the OpenAPI profile from which to discover the rest of the API. 

@product@'s headless APIs are available in SwaggerHub at 
[`https://app.swaggerhub.com/organizations/liferayinc`](https://app.swaggerhub.com/organizations/liferayinc). 
Each API has its own URL in SwaggerHub. For example, you can access the delivery 
API definition at 
[`https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0`](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0). 

Each OpenAPI profile is also deployed dynamically in your portal instance under 
this schema: 

    http://[host]:[port]/o/[insert-headless-api]/[version]/openapi.yaml

For example, if you're running @product@ locally on port `8080`, the home URL 
for discovering the headless delivery API is: 

    http://localhost:8080/o/headless-delivery/v1.0/openapi.yaml

You must be logged in to access this URL, or use basic authentication and a 
browser or other tool like 
[Postman](https://www.getpostman.com), 
[Advanced REST Client](https://install.advancedrestclient.com/install), 
or even the `curl` command in your system console. 

For simplicity, the examples in this documentation use the `curl` command and 
send requests to a @product@ instance running locally on port `8080`. 

Run this `curl` command to access the home URL: 

    curl http://localhost:8080/o/headless-delivery/v1.0/openapi.yaml -u test@liferay.com:test

You should get a response like this: 

    openapi: 3.0.1
    info:
      title: Headless Delivery
      version: v1.0
    paths:
      /v1.0/blog-posting-images/{blogPostingImageId}:
        get:
          tags:
          - BlogPostingImage
          operationId: getBlogPostingImage
          parameters:
          - name: blogPostingImageId
            in: path
            required: true
            schema:
              type: integer
              format: int64
          responses:
            default:
              description: default response
              content:
                application/json:
                  schema:
                    $ref: '#/components/schemas/BlogPostingImage'
    (...)

This response follows the OpenAPI version 3.0 syntax to specify the endpoints 
(URLs) of the API and schemas returned. You can also open the OpenAPI profile in 
an OpenAPI editor like the 
[Swagger Editor](https://editor.swagger.io). 
You can use this editor to inspect the documentation and parameters, and make 
requests to the API. 

There are also many other tools that support OpenAPI, such as client generators, 
validators, parsers, and more. See 
[OpenAPI.Tools](https://openapi.tools/) 
for a comprehensive list. Leveraging OpenAPI provides standards support, 
extensive 
[documentation](https://swagger.io/docs/), 
and industry-wide conventions. 

## Related Topics

[Get Started: Invoke a Service](invoke-service)
