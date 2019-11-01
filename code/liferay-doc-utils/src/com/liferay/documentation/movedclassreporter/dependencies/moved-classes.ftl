
To leverage the benefits of modularization, many classes from <em>${oldJar}</em>
have been moved into application and framework API modules. The table below
provides details about these classes and the modules they've moved to. Package
changes and each module's group, artifact ID, and version are listed, to
facilitate 
<a href="/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies">configuring dependencies</a>. 

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

  <caption>
    <b>Classes Moved from ${oldJar} to Modules</b>
	<p>
      This information was generated based on comparing classes in
      <em>${oldSrc}</em> to classes in <em>${newSrc}</em>.
    </p>
  </caption>
  <tr>
    <th>Class</th>
    <th>Package</th>
    <th>Group ID, Artifact ID,<br>
        and Version</th>
  </tr>

<#list movedClasses as movedClass>
  <tr>
    <td>${movedClass.name}</td>
    <td>
	  <em>Old:</em> ${movedClass.packageOld}<br>
	  <em>New:</em> ${movedClass.packageNew}
	</td>
    <td>${movedClass.group}<br>
        ${movedClass.module}<br>
        ${movedClass.moduleVersion}
    </td>
  </tr>
</#list>

</table>
