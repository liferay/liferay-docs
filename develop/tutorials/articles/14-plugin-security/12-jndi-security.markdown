# JNDI Security [](id=jndi-security)

Specify which services the plugin can look up. You can use regular expressions
to make this dynamic. 

*Example:*

Using the sample values below, the plugin can look up objects for key names
`matthew`, `mark`, `Mark`, `luke`, and `Luke`. In addition, the plugin can
look-up objects for key names containing `john` with zero or more characters
preceding and/or trailing `john`. 

	security-manager-jndi-names=\
		test-pacl-matthew,\
		test-pacl-[mM]ark|test-pacl-[lL]uke,\
		test-pacl-.*john.*
