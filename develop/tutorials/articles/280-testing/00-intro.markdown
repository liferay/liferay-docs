# Testing [](id=testing)

Assuring top quality is paramount in producing awesome software. Test driven
development plays a key role in this process. Liferay's tooling and integration
with standard test frameworks support test driven development and help you
reach quality milestones. Here are the ways Liferay facilitates testing:

- [Unit testing](/develop/tutorials/-/knowledge_base/7-0/unit-testing-with-junit):
    Using JUnit to unit test @product@ modules in Gradle and Maven build
    environments and in IDEs that have JUnit plugins is seamless. 
- [Integration testing](/develop/tutorials/-/knowledge_base/7-0/arquillian-integration-test-example):
    The
    [Arquillian Extension for Liferay](/develop/tutorials/-/knowledge_base/7-0/arquillian-extension-for-liferay-example)
    lets you spin up a @product@ instance, deploy modules whose components
    provide and consume services, and exercise their APIs.
    [Liferay's `@Inject` annotation](/develop/tutorials/-/knowledge_base/7-0/injecting-service-components-into-tests)
    allows you to inject service instances into tests. 
- [Functional testing](/develop/tutorials/-/knowledge_base/7-0/arquillian-functional-test-example):
    Selenium and the Arquillian Extension for Liferay support functional UI
    testing. 
- Code Coverage:
    JaCoCo analyzes and reports test code coverage. 
- [Slim Runtime](/develop/tutorials/-/knowledge_base/7-0/liferay-slim-runtime): 
    Liferay Slim Runtime facilitates testing modules (including Service Builder
    modules) in a fast, lightweight environment. 

Unit testing is the first step in test driven development.
