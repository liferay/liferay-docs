# Classes Moved from portal-service.jar

<p>
To leverage the benefits of modularization in Liferay 7, classes from former
Liferay 6 JAR file <em>portal-service.jar</em> have been refactored in one of
three ways:
<ol>
  <li>
    <strong>Moved to <em>portal-kernel.jar</em></strong> - Classes that make up
    the kernel have been moved here.
  </li>
  <li>
    <strong>Moved to an app module</strong> - Classes that are a part of an
    application's API have been moved to that app API module.
  </li>
  <li>
    <strong>Removed from Liferay</strong> - Classes no longer needed have been
    removed.
  </li>
</ol>
</p>

<p>
The following sections provide details about classes moved to app modules and
classes removed from Liferay. The information was generated based on comparing
classes in <em>${oldSrcZip}</em> to classes in <em>${newSrcZip}</em>.
</p>

## Classes Moved to App Modules

<p>
The table below lists classes that were in the former portal-service.jar that
are now in application API modules. Each module's symbolic name and version are
included to facilitate configuring dependencies on them.
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

<table>

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

<br>
<br>
## Classes Removed from Liferay

<p>
The table below lists classes that were in the former
<em>portal-service.jar</em> that are no longer needed. They have been removed
from Liferay.
</p>

<table>

  <tr>
    <th>Class</th>
    <th>Package</th>
  </tr>

<#list removedClasses as removedClass>
  <tr>
    <td>${removedClass.name}</td>
    <td>${removedClass.packageOld}</td>
  </tr>
</#list>

</table>

<br>
<br>

# Related Articles

[Migrating a Liferay 6 Application](/develop/tutorials/-/knowledge_base/7-0/migrating-a-liferay-6-application)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)
