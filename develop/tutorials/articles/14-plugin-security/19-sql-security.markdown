# SQL Security [](id=sql-security)

Specify tables in the Liferay database on which the plugin is permitted to
perform the applicable operations. These property names use the following
convention:

	security-manager-sql-tables-[operation]    
 
Specify tables on which the plugin can do any and all operations.

*Example:*

	security-manager-sql-tables-all=\
		TestPACL_Foo

Specify tables the plugin can create.

*Example:*

	security-manager-sql-tables-create=\
		TestPACL_CreateSuccess,\
		TestPACL_DropSuccess,\
		TestPACL_InsertSuccess,\
		TestPACL_ReplaceSuccess,\
		TestPACL_TruncateSuccess

Specify tables from which the plugin can delete records.

*Example:*

	security-manager-sql-tables-delete=\
		ClassName_

Specify tables the plugin can drop.

*Example:*

	security-manager-sql-tables-drop=\
		TestPACL_CreateSuccess,\
		TestPACL_DropSuccess,\
		TestPACL_InsertSuccess,\
		TestPACL_ReplaceSuccess,\
		TestPACL_TruncateSuccess

Specify tables into which the plugin can insert records.

*Example:*

	security-manager-sql-tables-insert=\
		TestPACL_InsertSuccess

Specify tables in which the plugin can replace records.

*Example:*

	security-manager-sql-tables-replace=\
		TestPACL_ReplaceSuccess

Specify tables the plugin can query.

*Example:*

	security-manager-sql-tables-select=\
		Counter,\
		TestPACL_Bar

Specify tables from which the plugin can delete all records.

*Example:*

	security-manager-sql-tables-truncate=\
		TestPACL_TruncateSuccess

Specify tables the plugin can update.

*Example:*

	security-manager-sql-tables-update=\
		ListType
