---
header-id: rest-builder-open-api
---

# REST Builder & OpenAPI

[TOC levels=1-4]

REST Builder is based on [OpenAPI](https://swagger.io/specification/), and its
philosophy is "OpenAPI first": first you write the profile and then you use
it as the base of your implementation.

But first you must create a project with two empty bundles (a Blade
template will follow soon). The bundles (-api and -impl) should have the files you
are already used to: a `build.gradle` and a `bnd.bnd`. The novelty is two
YAML files, a configuration file (`rest-config.yaml`) and the OpenAPI profile
(`rest-openapi.yaml`). An example project is
[here](https://github.com/nhpatt/liferay-devcon-appointment). 

Let's see the configuration file in detail. In the root of the -impl project we
have to create a YAML file to specify paths and the basic configuration of our
new API. A sample implementation would be:

```yaml
apiDir: "../headless-test-api/src/main/java"
apiPackagePath: "com.liferay.headless.test"
application:
    baseURI: "/headless-test"
    className: "HeadlessTestApplication"
    name: "Liferay.Headless.Test"
author: "Javier Gamarra"
```

This file specifies the path of the -api bundle, the java package that we will
use across all the bundles and the information of the JAX-RS application: the
path of our application, the name of the class and the JAX-RS name of our API.

I've skipped two advanced features, generating a client and automated tests,
will see them later.

Just one step left, writing our OpenAPI profile.

## OpenAPI profile

The OpenAPI profile will be the source of all our APIs, in this file, we will
add the paths and entities of our API. First, we'll create
a [YAML](https://en.wikipedia.org/wiki/YAML) file called rest-openapi.yaml.
Writing YAML files is tricky so we recommend using the [swagger
editor](http://editor.swagger.io/) to do it, which validates the YAML file
against YAML syntax and the [OpenAPI
specification](https://github.com/OAI/OpenAPI-Specification).

A simple OpenAPI profile that retrieves a fictitious entity might look like this:

```yaml
components:
  schemas:
    Entity:
      description: A very simple entity
      properties:
        name:
          description: The entity name.
          type: string
        id:
          description: The entity ID.
          type: integer
      type: object
info:
  description: ""
  title: "My API"
  version: v1.0
openapi: 3.0.1
paths:
  "/entities/{entityId}":
    get:
      parameters:
        - in: path
          name: entityId
          required: true
          schema:
            type: integer
      responses:
        200:
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Entity"
          description: ""
      tags: ["Entity"]
```

All OpenAPI profiles have three different sections: components, info, and paths.
The easiest one is the information block. It contains the OpenAPI version, the
title and the version of your API:

```yaml
info:
  description: ""
  title: "My API"
  version: v1.0
openapi: 3.0.1
```

Indentations should be spaces. The 
[swagger editor](http://editor.swagger.io/) helps with formatting.

The components section specifies the schemas/entities to return or accept
on your APIs. In this case, you define a schema called _Entity_
that has two string fields: a name and an id. 

```yaml
components:
  schemas:
    Entity:
      description: A very simple entity
      properties:
        name:
          description: The entity name.
          type: string
        id:
          description: The entity ID.
          type: integer
      type: object
```

The OpenAPI specification defines 
[many types and fields](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#schemaObject)
you can use in your schemas. 

The other common type is `$ref`, a 
[reference type](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#referenceObject)
that allows you to refer to an existing type like this: 

```yaml
$ref: '#/components/schemas/Entity'
```

The last block, called paths, defines the URLs that you'll expose in your APIs,
with the type of HTTP verbs, list of parameters, status codes, etc. 

```yaml
paths:
  "/entities/{entityId}":
    get:
      parameters:
        - in: path
          name: entityId
          required: true
          schema:
            type: integer
      responses:
        200:
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Entity"
          description: ""
      tags: ["Entity"]
```

The pattern above, `"/entities/{entity}"`, follows a common pattern in REST
APIs. This is the endpoint that retrieves one element, `"/entities"`. It returns
a list of elements, and a POST request creates one.

For every path, it is mandatory to add a tag that points to an existing schema to
indicate where to generate your code. REST Builder creates a method inside the
class `[TAG]ResourceImpl.java`. 

## Generation

Once you've written your OpenAPI configuration and profile, it's time to
generate your scaffolding for REST and GraphQL.

In the -impl or in the root module folder, execute this command:

```bash
gw buildREST
```

You can use `gw bR` if you want to save a few keystrokes.

If everything's indented properly and the OpenAPI profile validates, REST
Builder generates your JAX-RS resources and the GraphQL endpoint. Next, you'll
see what has been generated and how to implement our business logic.

## Examples

Here's a complete example that defines all CRUD operations in OpenAPI.

### GET Collection

```yaml
paths:
    "/entities":
        get:
            responses:
                200:
                    content:
                        application/json:
                            schema:
                                items:
                                    $ref: "#/components/schemas/Entity"
                                type: array
                    description: ""
            tags: ["Entity"]
```

### DELETE

```yaml
paths:
    "/entities/{entityId}":
        delete:
            parameters:
                - in: path
                  name: entityId
                  required: true
                  schema:
                      type: integer
            responses:
                204:
                    content:
                        application/json: {}
                    description: ""
            tags: ["Entity"]
```

### POST

```yaml
paths:
    "/entities":
        post:
            requestBody:
                content:
                    application/json:
                        schema:
                            $ref: "#/components/schemas/Entity"
            responses:
                200:
                    content:
                        application/json:
                            schema:
                                $ref: "#/components/schemas/Entity"
                    description: ""
            tags: ["Entity"]
```

### PUT

```yaml
paths:
    "/entities/{entityId}":
        put:
            parameters:
                - in: path
                  name: entityId
                  required: true
                  schema:
                      type: integer
            requestBody:
                content:
                    application/json:
                        schema:
                            $ref: "#/components/schemas/Entity"
            responses:
                200:
                    content:
                        application/json:
                            schema:
                                $ref: "#/components/schemas/Entity"
                    description: ""
            tags: ["Entity"]
```

## Summary

There are more examples showcasing all the supported OpenAPI syntax
[here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/headless/headless-delivery/headless-delivery-impl/rest-openapi.yaml)
and
[here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/headless/headless-admin-taxonomy/headless-admin-taxonomy-impl/rest-openapi.yaml). 
Your next step is to [create your API](/docs/7-2/frameworks/-/knowledge_base/f/rest-builder-develop). 

