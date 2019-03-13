# Important Attributes in Staging [](id=important-attributes-in-staging)

If you'd like to generate your staged models using
[Service Builder](/developer/frameworks/-/knowledge_base/7-2/service-builder),
you must define the proper attributes in your project's `service.xml` file. If
you'd like more detail on how this is done, see the
[Generating Staged Models using Service Builder](/develop/tutorials/-/knowledge_base/7-1/generating-staged-models-using-service-builder)
article. You'll learn some general information about this process next.

One of the most important attributes used by the Staging framework is the UUID
(Universally Unique Identifier). This attribute must be set to `true` in your
`service.xml` file for Service Builder to recognize your model as an eligible
staged model. The UUID is used to differentiate entities between environments.
Because the UUID always remains the same, it's unique across multiple systems.
Why is this so important?

Suppose you're using
[remote staging](/discover/portal/-/knowledge_base/7-1/enabling-remote-live-staging)
and you create a new entity on your local staging site and publish it to your
remote live site. What happens when you go back to modify the entity on your
local site and want to publish those changes? Without a UUID, the Staging
framework has no way to know the local and remote entities are the same. To
publish entities properly, the Staging framework needs entities uniquely
identified across systems to recognize the original entity on the remote site
and update it. The UUID provides that. 

In addition to the UUID, there are several columns that must be defined in your
`service.xml` file for Service Builder to define your model as a staged model:

- `companyId`
- `createDate`
- `modifiedDate`

If you want a staged grouped model, also include the `groupId` and
`lastPublishDate` columns. If you want a staged audited model, include the
`userId` and `userName` columns.
