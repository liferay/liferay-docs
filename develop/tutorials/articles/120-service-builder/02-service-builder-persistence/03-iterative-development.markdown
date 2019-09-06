---
header-id: iterative-development
---

# Iterative Development

[TOC levels=1-4]

During the course of development, you're likely to need to add fields to your
database. This is a normal process of iterative development: you get an idea for
a new feature, or it's suggested to you, and that feature requires additional
data in the database. It's important to note, then, that **new fields added to
`service.xml` are not automatically added to the database.** To add the fields,
you must do one of two things: 

1.  Write an [upgrade process](/docs/7-0/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app) 
    to modify the tables and preserve the data, or

2.  Run the `cleanServiceBuilder`
    [Gradle task](/docs/7-0/reference/-/knowledge_base/r/db-support-gradle-plugin)
    (also supported on Maven and Ant), which drops your tables so they get
    re-created the next time your app is deployed. See the
    [Maven DB Support Plugin](/docs/7-0/reference/-/knowledge_base/r/db-support-plugin)
    reference article for instructions on how to run this command from a Maven
    project.

Use the first option when you have a released application and you must preserve
your users' data. Use the second option when you're in the middle of development
and you're adding new columns during that process. 

## Related Topics

[Upgrade Processes](/docs/7-0/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app)

[Gradle DB Support Plugin](/docs/7-0/reference/-/knowledge_base/r/db-support-gradle-plugin)

[Maven DB Support Plugin](/docs/7-0/reference/-/knowledge_base/r/db-support-plugin)
