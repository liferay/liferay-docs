---
header-id: upgrading-sharded-environment
---

# シャード環境のアップグレード

[TOC levels=1-4]

@product@ 7.0以降、Liferayはデータベースベンダーによってネイティブに提供されていた機能を支持するために、独自の物理パーティションの実装（シャーディングとも呼ばれる）を削除しました。当分は、論理パーティション機能（インスタンスとも呼ばれる）を引き続きサポートします。

Liferay Portal 6.2のシャードインストールの場合は、次の手順に従って@product-ver@にアップグレードできます。アップグレード後は、インストールはシャード環境ではなくなります。その結果、過去に使用したシャードと同じ数の独立したデータベースが作成されることに注意してください。以下の例では、3つのシャードでインストールをアップグレードします。

## デフォルトのシャードのアップグレード

アップグレードプロセスでは、コントロールテーブルをデフォルトのシャードから他のシャードへコピーする必要があるため、デフォルトのシャードの`[Liferay Home] /tools/portal-tools-db-upgrade-client/portal-upgrade-database.properties]`ファイルにすべてのシャードの[[JDBCプロパティー]](/discover/deployment/-/knowledge_base/7-1/running-the-upgrade-ja#configuring-portal-upgrade-database-properties)を設定します。 

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

## 2番目のシャードのアップグレード

デフォルトのシャードがアップグレードされたら、2番目のシャードをアップグレードする必要があります。以下の例では、データベース名は`lportal_one`です。`デフォルト`のJDBCプロパティは、2番目のシャードのプロパティにマップされます。

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.username=
    jdbc.default.password=

## 3番目のシャードのアップグレード

2番目のシャードがアップグレードされたら、`lportal_two`というデータベース名を持つ、3番目のシャードをアップグレードします。この場合も、`デフォルト`のJDBCプロパティは3番目のシャードのプロパティにマップされます。

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/lportal_two?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.username=
    jdbc.default.password=

最後のシャードをアップグレードしたら、完了です。この手順により、古いシャード環境のLiferay Portal 6.2インストールを@product-ver@に移行できます。
