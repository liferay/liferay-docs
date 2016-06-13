# Classes Moved from portal-service.jar

<p>
To leverage the benefits of modularization in Liferay 7, many classes from
former Liferay 6 JAR file <em>portal-service.jar</em> have been moved into
application and framework API modules. The following section provides details
about these classes and the modules they've moved to.
</p>

## Classes Moved to API Modules

<p>
The table below lists classes moved to API modules. Each module's symbolic name
and version are included to facilitate configuring dependencies on them. This
information was generated based on comparing classes in <em>${oldSrcZip}</em> to
classes in <em>${newSrcZip}</em>.
</p>

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
caption {
    text-align: left;
}
</style>
<table style="width:100%">

  <tr>
    <th>Class</th>
    <th>New Package</th>
    <th>Module Symbolic Name</th>
    <th>Module Version</th>
    <th>Old Package</th>
  </tr>

<#list movedClasses as movedClass>
  <tr>
    <td>${movedClass.name}</td>
    <td>${movedClass.packageNew}</td>
    <td>${movedClass.module}</td>
    <td>${movedClass.version}</td>
    <td>${movedClass.packageOld}</td>
  </tr>
</#list>

</table>

## Related Articles

[Migrating a Liferay 6 Application](/develop/tutorials/-/knowledge_base/7-0/migrating-a-liferay-6-application)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)
