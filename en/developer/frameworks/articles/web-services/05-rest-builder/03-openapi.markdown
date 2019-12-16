---
header-id: rest-builder-open-api
---

# REST Builder & OpenAPI

[TOC levels=1-4]

REST Builder is based on [OpenAPI](https://swagger.io/specification/), and it follows an approach of OpenAPI first: first we write the profile and then we use it as the base of our implementation.

But first of all, we have to create a project, with two empty bundles (a blade template will follow soon). The bundles (-api and -impl) will have the files you are already used to: a build.gradle and a bnd.bnd (you can see an example [here](https://github.com/nhpatt/liferay-devcon-appointment). The novelty is two YAML files, a configuration file (rest-config.yaml) and the OpenAPI profile (rest-openapi.yaml). 

Let's see the configuration file in detail. In the root of the -impl project we have to create a YAML file to specify paths and the basic configuration of our new API. A sample implementation would be:

```yaml
apiDir: "../headless-test-api/src/main/java"
apiPackagePath: "com.liferay.headless.test"
application:
    baseURI: "/headless-test"
    className: "HeadlessTestApplication"
    name: "Liferay.Headless.Test"
author: "Javier Gamarra"
```

This file specifies the path of the -api bundle, the java package that we will use across all the bundles and the information of the JAX-RS application: the path of our application, the name of the class and the JAX-RS name of our API.

I've skipped two advanced features, generating a client and automated tests, will see them later.

Just one step left, writing our OpenAPI profile.

## OpenAPI profile

The OpenAPI profile will be the source of all our APIs, in this file, we will add the paths and entities of our API. To write we'll create a [YAML](https://en.wikipedia.org/wiki/YAML) file called rest-openapi.yaml. Writing YAML files is tricky so we recommend using the [swagger editor](http://editor.swagger.io/) to do it, which validates the YAML file against YAML syntax and the [OpenAPI specification](https://github.com/OAI/OpenAPI-Specification).

A very simple OpenAPI profile that retrieves a fictitious entity would be:

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

All OpenAPI profiles have 3 different sections. The easier one is the information block, that contains the OpenAPI version, the title and the version of our API:

```yaml
info:
  description: ""
  title: "My API"
  version: v1.0
openapi: 3.0.1
```

Watch out with the indentations (should be spaces!). The first time will be the hardest but after that, it's easy to get into the flow using the [swagger editor](http://editor.swagger.io/).

The other section specifies the schemas/entities that we will return or accept on our APIs. In this particular case, we are defining a schema called _Entity_ that has two fields, a name and an id, both of type string.

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

The OpenAPI specification defines many types and fields we can use in our schemas. We have a comprehensive list [here](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#schemaObject).

The other common type is $ref, a [reference type](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md#referenceObject) that allows us to refer to an existing type like this: 

    $ref: '#/components/schemas/Entity'
    
The last block defines our paths, the URLs that we will expose in our APIs, with the type of HTTP verbs, list of parameters, status codes...

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

Here we have defined a path like _"/entities/{entity}"_. Following a very common pattern in REST APIs, this will be the endpoint to retrieve one element,  _"/entities"_ will return a list of elements and with a POST request we will create one...
  
We have to add a tag, pointing to an existing schema, to indicate where to generate our code. The REST builder will create a method inside the class [TAG]ResourceImpl.java. 

## Generation

We've written our OpenAPI configuration and profile... how do we generate our scaffolding for Headless and GraphQL?

It's really easy, just execute (in the -impl or in the root module folder) this command:

    gw buildREST
                       
Or _gw bR_ if you want to save a few keystrokes.

If all the spaces are Ok and the OpenAPI profile validates, REST Builder will generate our JAX-RS resources and the GraphQL endpoint. We'll see in the next section what has been generated and how to implement our business logic.

## Examples

For the sake of completion, let's see how we would define the full CRUD in OpenAPI.

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

#### More examples

There are more examples showcasing all the supported OpenAPI syntax [here](https://github.com/brianchandotcom/liferay-portal/blob/master/modules/apps/headless/headless-delivery/headless-delivery-impl/rest-openapi.yaml) and [here](https://github.com/brianchandotcom/liferay-portal/blob/master/modules/apps/headless/headless-admin-taxonomy/headless-admin-taxonomy-impl/rest-openapi.yaml)