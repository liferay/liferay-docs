# Environment Variables [](id=environment-variables)

You can use environment variables to configure @product@, which is especially
useful in cloud-based environments. 

+$$$

**Important**: This capability is for Linux and Unix operating systems only:
it's not available on Windows. 

$$$

+$$$

**Note**: Environment variables can only override portal properties ([defined in
`portal.properties`](@platform-ref@/7.0-latest/propertiesdoc/portal.properties.html));
they cannot override 
[system properties](@platform-ref@/7.0-latest/propertiesdoc/system.properties.html) 
or configurations from `.config` files. 

$$$

## Overriding Portal Properties [](id=overriding-portal-properties)

To override portal properties, follow these operating system (OS) and Liferay
environment variable name guidelines:

OS environment variable names:
-   Consist of uppercase letters, digits, and `_` (underscore) characters only
-   Must not start with a digit

Liferay  environment variable names:
-   Must follow the OS environment variable name guidelines 
-   Must start with the prefix `LIFERAY_`
-   Characters that violate the OS guidelines must be encoded
    using one of the following methods:
    -   [`CharPool` constants](#encoding-with-charpool-constants)
    -   [Unicode code points](#encoding-with-unicode-code-points)

### Encoding with CharPool Constants [](id=encoding-with-charpool-constants)

Follow these rules to encode with
[`CharPool` constants](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/CharPool.html):

-   Use upper case (`CharPool` constant names are uppercase).
-   Remove `_` characters from the `CharPool` constant names.
-   Wrap each constant name in a pair of `_` characters.

Here are some encoding examples.

#### CharPool Constant Example 1 [](id=constant-example-1)

This example involves encoding the period `.` character. 
 
Portal property:

    setup.wizard.enabled=false

Encoding steps:

1.  Prefix the variable with `LIFERAY_`.

    `LIFERAY_setup.wizard.enabled=false`

2.  Encode the period characters with the `CharPool` constant name `PERIOD`,
    surrounded by a pair of `_` characters.

    `LIFERAY_setup_PERIOD_wizard_PERIOD_enabled=false`

3.  Convert all letters to uppercase.

Environment variable:

    LIFERAY_SETUP_PERIOD_WIZARD_PERIOD_ENABLED=false

#### CharPool Constant Example 2 [](id=constant-example-2)

This example uses `CharPool` constants to encode an environment variable name
for a property that includes periods, brackets, and uppercase characters.

Portal property:

    setup.database.driverClassName[mysql]=com.mysql.jdbc.Driver

Encoding steps:
1.  Prefix the variable with `LIFERAY_`.

    `LIFERAY_setup.database.driverClassName[mysql]=com.mysql.jdbc.Driver`

2.  Use `CharPool` constant names surrounded by `_` characters:
    -   `.` &rarr; `_PERIOD_`
    -   `C` &rarr; `_UPPER_CASE_C_`
    -   `N` &rarr; `_UPPER_CASE_N_`
    -   `[` &rarr; `_OPEN_BRACKET_`
    -   `]` &rarr; `_CLOSE_BRACKET_`
 
    `LIFERAY_setup_PERIOD_database_PERIOD_driver_UPPER_CASE_C_lass_UPPER_CASE_N_ame_OPEN_BRACKET_mysql_CLOSE_BRACKET_=com.mysql.jdbc.Driver`

3.  Remove underscores from all constant names.

    -   `_UPPER_CASE_C_` &rarr; `_UPPERCASEC_`
    -   `_UPPER_CASE_N_` &rarr; `_UPPERCASEN_`
    -   `_OPEN_BRACKET_` &rarr; `_OPENBRACKET_`
    -   `_CLOSE_BRACKET_` &rarr; `_CLOSEBRACKET_`
 
    `LIFERAY_setup_PERIOD_database_PERIOD_driver_UPPERCASEC_lass_UPPERCASEN_ame_OPENBRACKET_mysql_CLOSEBRACKET_=com.mysql.jdbc.Driver`

4.  Convert all letters to uppercase.

Environment variable:

    LIFERAY_SETUP_PERIOD_DATABASE_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME_OPENBRACKET_MYSQL_CLOSEBRACKET_=com.mysql.jdbc.Driver

### Encoding with Unicode Code Points [](id=encoding-with-unicode-code-points)

Unicode offers more character encodings than `CharPool` and produces shorter
variable names. But Unicode code points are cryptic. 

Follow these rules to encode with Unicode:

1.  Look up the [character code points](https://unicode-table.com/en/). 

2.  Replace each character with a code point wrapped in a pair of `_`
    characters.

Here's an encoding example that uses Unicode. 

#### Unicode Example [](id=unicode-example)

This example uses code points to encode an environment variable name for a
portal property that contains periods, brackets, and uppercase characters.

Portal property:

    setup.database.driverClassName[mysql]=com.mysql.jdbc.Driver

Encoding steps:

1.  Prefix the variable with `LIFERAY_`.

    `LIFERAY_setup.database.driverClassName[mysql]=com.mysql.jdbc.Driver`

2.  Use `CharPool` constant names surrounded by `_` characters to encode the
    following characters:
    -   `.` &rarr; `_46_`
    -   `C` &rarr; `_67_`
    -   `N` &rarr; `_78_`
    -   `[` &rarr; `_91_`
    -   `]` &rarr; `_93_`

    `LIFERAY_setup_46_database_46_driver_67_lass_78_ame_91_mysql_93_=com.mysql.jdbc.Driver`

3.  Convert all letters to uppercase.

Environment variable:

    LIFERAY_SETUP_46_DATABASE_46_DRIVER_67_LASS_78_AME_91_MYSQL_93_=com.mysql.jdbc.Driver

Congratulations! You know how to use environment variables to configure
@product@. It's easier than ever to configure and deploy @product@ images in
virtual machines and cloud containers. 

## Related Topics [](id=related-topics)

[Configuring @product@](/discover/deployment/-/knowledge_base/7-0/configuring-liferay)

[System Wide Settings](/discover/portal/-/knowledge_base/7-0/system-wide-settings)
