<h1>
Classes removed from Liferay
</h1>

<p>
The following table lists classes formerly in <em>portal-service.jar</em> that
have been removed from Liferay. This information was generated based on
comparing classes in <em>${oldSrcZip}</em> to classes in <em>${newSrcZip}</em>.
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
    <th>Package</th>
  </tr>

<#list removedClasses as removedClass>
  <tr>
    <td>${removedClass.name}</td>
    <td>${removedClass.packageOld}</td>
  </tr>
</#list>

</table>
