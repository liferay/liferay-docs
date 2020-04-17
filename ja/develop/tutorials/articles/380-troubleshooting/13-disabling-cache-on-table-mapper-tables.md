---
header-id: disabling-cache-for-table-mapper-tables
---

# テーブルマッパーテーブルのキャッシュを無効にする

[TOC levels=1-4]

Service Builderは、[エンティティ間のリレーショナルマッピング](/docs/7-1/tutorials/-/knowledge_base/t/defining-relationships-between-service-entities)を作成します。 
マッピングテーブルを使用してエンティティを関連付けます。`service.xml`ファイルでは、両方のエンティティに`mapping-table="table1_table2"`形式の`mapping-table`列属性があります。たとえば、`AssetEntry`を`AssetCategory`にマップする`service.xml`には、次の列を持つ`AssetCategory`エンティティがあります。

    <column entity="AssetEntry"
    mapping-table="AssetEntries_AssetCategories"
    name="entries" type="Collection" />

`AssetEntry`エンティティ要素には次の列があります。

    <column entity="AssetCategory"
    mapping-table="AssetEntries_AssetCategories"
    name="categories" type="Collection" />

デフォルトでは、テーブルマッパーキャッシュは各マッピングテーブルに関連付けられています。キャッシュはオブジェクトの取得を最適化します。ただし、場合によっては、テーブルマッパーキャッシュを無効にしたほうがいい場合もあります。

## テーブルマッパーでキャッシュを無効にしたほうがいいのはなぜですか？

超大規模なエンティティテーブルは、メモリを大量に消費するテーブルマッパーキャッシュにつながる可能性があります。このため、テーブルマッパーでキャッシュを無効にすることを検討してください。

[`table.mapper.cacheless.mapping.table.names`ポータルプロパティ](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Table%20Mapper)は、指定されたマッピングテーブルに関連付けられたテーブルマッパーのキャッシュを無効にします。
 デフォルトのプロパティ設定は次のとおりです。

    ##
    ## Table Mapper
    ##
    
    #
    # Set a list of comma delimited mapping table names that will not be using
    # cache in their table mappers.
    #
    table.mapper.cacheless.mapping.table.names=\
    Users_Groups,\
    Users_Orgs,\
    Users_Roles,\
    Users_Teams,\
    Users_UserGroups

上記の無効化されたキャッシュはすべて、`User`オブジェクトに関係します。これは、テーブルマッパーが大きくなりすぎて有用なキャッシュを持たない傾向があるためです。各`User`は、関連する各テーブルに複数のエントリを持つことができます。

キャッシュからオブジェクトを取得する潜在的な競合条件も、テーブルマッパーを無効にする理由の1つです。

たとえば、[LPS-84374](https://issues.liferay.com/browse/LPS-84374)は、カスタムエンティティのテーブルマッパーキャッシュが使用中にクリアされ、トランザクションのロールバックが発生する競合状態を説明しています。`AssetEntry`を公開すると、関連するすべてのテーブルマッパーキャッシュがクリアされます。ゲッターメソッドが`AssetEntries_AssetCategories`マッピングテーブルからオブジェクトを取得すると同時にそれらが公開された場合、トランザクションのロールバックが発生します。

## テーブルマッパーキャッシュを無効にする

マッピングテーブル名を`table.mapper.cacheless.mapping.table.names`ポータルプロパティに追加すると、関連するテーブルマッパーキャッシュが無効になります。

1. [`[Liferay_Home]/portal-ext.properties`ファイル](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home)に、現在の`table.mapper.cacheless.mapping.table.names`プロパティ設定を追加します。この設定は、@product@インストールの`portal-impl.jar/portal.properties`ファイルにあります。

2. マッピングテーブル名をリストに追加しますたとえば、`AssetEntries_AssetCategories`という名前のマッピングテーブルに関連付けられているキャッシュを無効にするには、その名前をリストに追加します。

       table.mapper.cacheless.mapping.table.names=\
       Users_Groups,\
       Users_Orgs,\
       Users_Roles,\
       Users_Teams,\
       Users_UserGroups,\
       AssetEntries_AssetCategories
   
3. @product@インスタンスを再起動して、テーブルマッパーキャッシュを削除します。

不要なテーブルマッパーキャッシュを無効にしました。
