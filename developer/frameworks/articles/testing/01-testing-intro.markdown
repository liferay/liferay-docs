---
header-id: testing
---

# Testing

[TOC levels=1-4]

Assuring top quality is paramount in producing awesome software. Test driven 
development plays a key role in this process. Liferay's tooling and integration 
with standard test frameworks support test driven development and help you reach 
quality milestones. Liferay lets you use whatever testing framework you want. 
There's [JUnit](https://junit.org) for unit testing, [Arquillian](http://arquillian.org/) 
plus JUnit annotations for integration testing, and more; consult the testing 
framework's documentation for details. 

Here are the ways Liferay facilitates testing:

- [Injecting Service Components into Tests](/docs/7-2/frameworks/-/knowledge_base/f/injecting-service-components-into-tests): 
    Liferay's `@Inject` annotation lets you inject service instances into tests. 