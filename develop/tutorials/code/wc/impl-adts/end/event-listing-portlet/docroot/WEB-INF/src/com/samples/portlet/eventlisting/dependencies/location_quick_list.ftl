<#if entries?has_content>
	Quick List:
	<ul>
	<#list entries as curLocation>
		<li>${curLocation.name} - ${curLocation.streetAddress}, ${curLocation.city}, ${curLocation.stateOrProvince}</li>
	</#list>
	</ul>
</#if>