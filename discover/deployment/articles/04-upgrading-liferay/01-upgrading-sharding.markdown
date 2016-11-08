# Upgrading a Sharded environment [](id=upgrading-sharded-environment)

If you come from a sharded installation of @product@ you have to follow this tutorial, in which we are going to upgrade an environment with 3 shards.

## Upgrading default shard

We should configure the upgrade client tool properties with all shards, because the Upgrade process needs to copy the control tables from the default shard to the other shards.

  jdbc.default.driverClassName=com.mysql.jdbc.Driver
  jdbc.default.url=jdbc:mysql://localhost/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
  jdbc.default.username=
  jdbc.default.password=

  jdbc.one.driverClassName=com.mysql.jdbc.Driver
  jdbc.one.url=jdbc:mysql://localhost/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
  jdbc.one.username=
  jdbc.one.password=

  jdbc.two.driverClassName=com.mysql.jdbc.Driver
  jdbc.two.url=jdbc:mysql://localhost/lportal_two?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
  jdbc.two.username=
  jdbc.two.password=

## Upgrading second Shard

Once the default shard is upgraded, we have to upgrade the second shard, which database name is `lportal_one`. Then, the `default` JDBC properties will relate to the second shard properties.

  jdbc.default.driverClassName=com.mysql.jdbc.Driver
  jdbc.default.url=jdbc:mysql://localhost/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
  jdbc.default.username=
  jdbc.default.password=

## Upgrading third shard

Once the second shard is upgraded, we have to upgrade the third shard, which database name is `lportal_two`. Again, the `default` JDBC properties will relate to the third shard properties.

  jdbc.default.driverClassName=com.mysql.jdbc.Driver
  jdbc.default.url=jdbc:mysql://localhost/lportal_two?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
  jdbc.default.username=
  jdbc.default.password=
