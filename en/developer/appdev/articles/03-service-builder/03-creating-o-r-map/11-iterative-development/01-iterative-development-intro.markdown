---
header-id: iterative-development
---

# Iterative Development

[TOC levels=1-4]

As you develop an application, you might need to add fields to your
database. This is a normal process of iterative development: you get an idea for
a new feature, or it's suggested to you, and that feature requires additional
data in the database. **New fields added to `service.xml` are not automatically
added to the database.** To add the fields, you must do one of two things: 

1.  Write an
    [upgrade process](/docs/7-2/frameworks/-/knowledge_base/f/upgrade-processes) 
    to modify the tables and preserve the data, or

2.  Run the `cleanServiceBuilder`
    [Gradle task](/docs/7-2/reference/-/knowledge_base/r/db-support-gradle-plugin)
    (also supported on Maven and Ant), which drops your tables so they get
    re-created the next time your app is deployed. The
    [Maven DB Support Plugin](/docs/7-2/reference/-/knowledge_base/r/db-support-plugin)
    reference article explains how to run this command from a Maven
    project.

Use the first option if you have a released application and you must preserve
user data. Use the second option if you're adding new columns during
development. 

## Related Topics

[Upgrade Processes](/docs/7-2/frameworks/-/knowledge_base/f/upgrade-processes) 

[Gradle DB Support Plugin](/docs/7-2/reference/-/knowledge_base/r/db-support-gradle-plugin)

[Maven DB Support Plugin](/docs/7-2/reference/-/knowledge_base/r/db-support-plugin)
