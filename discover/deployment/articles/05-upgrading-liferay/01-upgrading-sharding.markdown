# Upgrading a Sharded environment [](id=upgrading-sharded-environment)

If you come from a sharded installation of Liferay Portal 6.2, you can upgrade to
@product-ver@ by following these steps. Please note that after the upgrade, your
installation will no longer be sharded. The example below upgrades an
installation with three shards. 

## Upgrading default shard [](id=upgrading-default-shard)

Configure the upgrade client tool properties with all shards, because the
Upgrade process needs to copy the control tables from the default shard to the
other shards.

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

## Upgrading second Shard [](id=upgrading-second-shard)

Once the default shard is upgraded, you must upgrade the second shard. In this
example, its database name is `lportal_one`. The `default` JDBC properties
map to the second shard's properties.

      jdbc.default.driverClassName=com.mysql.jdbc.Driver
      jdbc.default.url=jdbc:mysql://localhost/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
      jdbc.default.username=
      jdbc.default.password=

## Upgrading third shard [](id=upgrading-third-shard)

Once the second shard is upgraded, upgrade the third shard, which has the
database name `lportal_two`. Again, the `default` JDBC properties map to the
third shard's properties.

      jdbc.default.driverClassName=com.mysql.jdbc.Driver
      jdbc.default.url=jdbc:mysql://localhost/lportal_two?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
      jdbc.default.username=
      jdbc.default.password=

Once you've upgraded your last shard, you're finished! This procedure lets you
migrate old sharded Liferay Portal 6.2 installations to @product-ver@. 

