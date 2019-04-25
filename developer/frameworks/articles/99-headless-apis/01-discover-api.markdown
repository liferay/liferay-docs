# Get Started: Discover the API [](id=get-started-discover-the-api)

To begin consuming web services, you must first know where they are (e.g.,
a service catalog), what operations you can invoke, and how to invoke them.
Because Headless REST APIs leverage
[OpenAPI](https://en.wikipedia.org/wiki/OpenAPI_Specification) (originally known as Swagger), you don't need a service
catalog. You only need to know the OpenAPI profile from which to discover the rest of the API.

Liferay Headless APIs are available in SwaggerHub under this [URL](https://app.swaggerhub.com/organizations/liferay6). For example, the delivery API definition is available [here](https://app.swaggerhub.com/apis/liferay6/headless-delivery/v1.0). 

The OpenAPI profile is also deployed dynamically in your liferay instance, under this schema: http://[host]:[port]/o/[headless-api]/[version]/openapi.yaml.

For example, if you're running @product@ locally on port `8080`, the home
URL for discovering the Headless APIs is `http://localhost:8080/o/headless-delivery/v1.0/openapi.yaml`. To access this URL, you will have to be logged in or provide basic authentication and use a browser or
other tools like [Postman](https://www.getpostman.com), [Advanced REST
Client](https://install.advancedrestclient.com/#/install), or even the `curl`
command in your system console. 

For simplicity, the examples in this
documentation use the `curl` command and send requests to a @product@ instance
running locally on port `8080`. 

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

This response follows the **OpenAPI version 3.0** syntax to specify the endpoints (urls) of the API and the schemas returned. You can also open the OpenAPI profile in an OpenAPI editor like the [swagger editor](https://editor.swagger.io). This editor allows to inspect the documentation, parameters and make requests against the API.

There are also **many** tools that support OpenAPI, from client generators, validators, parsers... there is a comprehensive list here: https://openapi.tools/. By leveraging OpenAPI we get the power of a standard supported by the main players in the API space, extensive documentation (like the specification [here](https://swagger.io/specification/)) and industry-wide conventions.

## Related Topics [](id=related-topics)

[Get Started: Invoke a Service](invoke-service)