---
header-id: generating-apis-with-rest-builder
---

# Generating APIs with REST Builder

[TOC levels=1-4]

| **Note:** This documentation is in beta. Stay tuned for more to come! 

Follow these steps to use REST Builder to create a headless REST API for your 
app: 

1.  Create a [project](/docs/7-2/reference/-/knowledge_base/r/creating-a-project). 

2.  Install REST Builder. For instructions on this, see 
    [REST Builder Gradle Plugin](/docs/7-2/reference/-/knowledge_base/r/rest-builder-gradle-plugin). 

3.  Run `gradlew clean deploy`. Note that your Gradle wrapper may not be in your 
    app's project directory, so you may need to use `..` to locate it (e.g., 
    `../../../gradlew clean deploy`). 

4.  Create the `*-api` and `*-impl` projects with the usual 
    files (`build.gradle`, `bnd.bnd`). Also create a `rest-config.yaml` with the 
    author, paths, and packages. For example, here's the `rest-config.yaml` for 
    Liferay's `headless-delivery` API: 

    ```yaml
    apiDir: "../headless-delivery-api/src/main/java"
    apiPackagePath: "com.liferay.headless.delivery"
    application:
        baseURI: "/headless-delivery"
        className: "HeadlessDeliveryApplication"
        name: "Liferay.Headless.Delivery"
    author: "Javier Gamarra"
    clientDir: "../headless-delivery-client/src/main/java"
    testDir: "../headless-delivery-test/src/testIntegration/java"
    ```

5.  In your `*-impl` module's root folder, write your OpenAPI profile in YAML. 
    You can use the 
    [Swagger Editor](https://editor.swagger.io/) 
    to validate syntax and ensure compliance with the OpenAPI specification. 

6.  In your `*-impl` module or root module folder, run `gradlew buildREST` (make 
    sure you locate your Gradle wrapper as instructed in step two above). 

7.  REST Builder generates the interfaces with the JAX-RS endpoints. It also 
    generates a `*ResourceImpl` class where you must implement the business 
    logic for each service. 

8.  After implementing the business logic for each service, deploy your modules. 
    Your APIs are then available at this URL: 

        http://[host]:[port]/o/[APPLICATION_CLASSNAME]/[OPEN_API_VERSION]/

    You can also execute `jaxrs:check` in the OSGi console to see all the JAX-RS 
    endpoints. 

## Related Topics

[REST Builder](/docs/7-2/appdev/-/knowledge_base/a/rest-builder)

[Headless REST APIs](/docs/7-2/frameworks/-/knowledge_base/f/headless-rest-apis)

[REST Builder Gradle Plugin](/docs/7-2/reference/-/knowledge_base/r/rest-builder-gradle-plugin)
