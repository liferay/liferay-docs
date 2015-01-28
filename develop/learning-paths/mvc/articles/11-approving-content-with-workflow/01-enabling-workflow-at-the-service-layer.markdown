



Need these columns, added to the entity's table:
        <column name="status" type="int" />
		<column name="statusByUserId" type="long" />
		<column name="statusByUserName" type="String" />
		<column name="statusDate" type="Date" />
		<column name="userName" type="String" />


Make sure of references to AssetEntry and AssetLink (should be there since
already asset-enabled): 
    <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
	<reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

