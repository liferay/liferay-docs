---
header-id: connecting-service-builder-to-external-databases
---

# Service Builderを外部データベースに接続する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product@以外のデータベースを使用したい場合があります。 これを行うには、そのデータソースを `portal-ext.properties` 定義するか、アプリサーバーでJNDIデータソースとして構成する必要があります。 このチュートリアルでは、 [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder) をデータソースに接続する方法を示します。 方法は次のとおりです。

1.  [データソースを指定します。](#step-1-specify-the-data-source)

2.  [データソースを指すSpring Beanを作成します。](#step-2-create-a-spring-bean-that-points-to-the-data-source)

3.  [エンティティのデータソースを `liferayDataSource` エイリアスに設定します。](#step-3-set-your-entitys-data-source-to-the-liferaydatasource-alias)

4.  [Service Builderを実行します。](#step-4-run-service-builder)

| **注**：Service Builderモジュールの `service.xml` ファイルで定義されているすべてのエンティティ|同じデータソースにバインドされています。 異なるエンティティを異なるデータにバインドする|ソースでは、個別のService Builderモジュールでエンティティを定義する必要があります。異なるデータソースを使用するように各モジュールを構成します。

まず、ポータルプロパティを使用してデータソースを設定します。

## ステップ1：データソースを指定する

アプリケーションサーバーがJNDIを使用してデータソースを定義する場合、この手順をスキップします。 それ以外の場合は、 `portal-ext.properties` ファイルでデータソースを指定します。 `jdbc.default以外のプレフィックスを指定して、Liferayのデフォルトデータソースと区別します。` この例では、プレフィックス `jdbc.extを使用しています。`

    jdbc.ext.driverClassName=org.mariadb.jdbc.Driver
    jdbc.ext.password=userpassword
    jdbc.ext.url=jdbc:mariadb://localhost/external?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.ext.username=yourusername

## ステップ2：データソースを指すSpring Beanを作成する

これを行うには、Service Builderモジュールの `src / main / resources / META-INF / spring / parent` フォルダーまたは従来のポートレットの `WEB-INFに親コンテキスト拡張（例：<code>ext-spring.xml`）を作成します/ src / META-INF / parent</code> フォルダー。 このフォルダーが存在しない場合は作成します。

| **注：** Liferay DXP 7.1 Fix Pack 3（Service Pack 1に含まれる）以降| Liferay Portal 7.1 CE GA2は、Springエクステンダーが2つのアプリケーションコンテキストを使用して| Service Builder `*-サービス` モジュール。 これにより、@product@はエクステンダーを登録できます。 Service Builderサービスとは別に、以前のサービスと許可|なくなった可能性がある親アプリケーションコンテキストの機能を無効にする将来必要です。 詳細については、を参照してください| [LPS-85683](https://issues.liferay.com/browse/LPS-85683)。 | |以前のバージョンの@product@ 7.1を使用している場合は、親コンテキストを置きます| Service Builderモジュールの拡張子（例：`ext-spring.xml`） `src / main / resources / META-INF / spring` フォルダーまたは従来のポートレットの| `WEB-INF / src / META-INF` フォルダー。

次の要素を定義します。

1.  データソースのデータソースファクトリSpring Bean。 タイプによって異なります。

      - **JNDI**：任意のプロパティプレフィックスを指定し、プレフィックスをJNDI名プロパティキーの先頭に追加します。 以下に例を示します。

        ``` 
          <bean class="com.liferay.portal.dao.jdbc.spring.DataSourceFactoryBean"
              id="liferayDataSourceFactory">
              <property name="propertyPrefix" value="custom." />
              <property name="properties">
                  <props>
                      <prop key="custom.jndi.name">jdbc/externalDataSource</prop>
                  </props>
              </property>
          </bean>
        ```

      - **ポータルのプロパティの**：（例えば、プレフィックスと一致するプロパティのプレフィックスを指定します `。jdbc.extを`）あなたはで使用される `portal-ext.properties`。

        ``` 
          <bean class="com.liferay.portal.dao.jdbc.spring.DataSourceFactoryBean"
              id="liferayDataSourceFactory">
              <property name="propertyPrefix" value="jdbc.ext." />
          </bean>
        ```

2.  データソースファクトリのSpring Beanを参照するLiferayデータソースBean。

3.  LiferayデータソースBeanのエイリアス。

JNDIデータソースを指す `ext-spring.xml` 例を次に示し` 。</p>

<pre><code><?xml version="1.0"?>

<beans default-destroy-method="destroy" default-init-method="afterPropertiesSet"
   xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

   <!-- To define an external data source, the liferayDataSource Spring bean 
       must be overridden. Other default Spring beans like liferaySessionFactory 
       and liferayTransactionManager may optionally be overridden. 

       liferayDataSourceFactory refers to the data source configured on the
       application server. -->
   <bean class="com.liferay.portal.dao.jdbc.spring.DataSourceFactoryBean"
       id="liferayDataSourceFactory">
       <property name="propertyPrefix" value="custom." />
       <property name="properties">
           <props>
               <prop key="custom.jndi.name">jdbc/externalDataSource</prop>
           </props>
       </property>
   </bean>

   <!-- The data source bean refers to the factory to access the data source.
   -->
   <bean
       class="org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy"
       id="liferayDataSource">
       <property name="targetDataSource" ref="liferayDataSourceFactory" />
   </bean>

   <!-- In service.xml, we associated our entity with the extDataSource. To 
       associate the extDataSource with our overridden liferayDataSource, we define 
       this alias. -->
   <alias alias="extDataSource" name="liferayDataSource" />
</beans>
`</pre>

上記の `liferayDataSourceFactory` は、 `jdbc / externalDataSource`という名前のJNDIデータソースを参照しています。 データソースが `portal-ext.properties` ファイルにある場合、Beanにはデータソースプロパティプレフィックスと一致する `propertyPrefix` プロパティのみが必要です。

データソースBean `liferayDataSource` は、 `liferayDataSourceFactory` Beanを参照するBeanでオーバーライドされます。 オーバーライドは、このバンドル（モジュールまたは [Webアプリケーションバンドル](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)）のみに影響します。

エイリアス `extDataSource` は、 `liferayDataSource` データソースBeanを指します。

| **注**：複数のService Builderで外部データソースを使用するには|バンドルの場合、各バンドルで `liferayDataSource` Beanをオーバーライドする必要があります。

## ステップ3：エンティティのデータソースを `liferayDataSource` エイリアスに設定します

`service.xml` ファイルで、エンティティのデータソースを、 `ext-spring.xml` ファイルで指定した `liferayDataSource` エイリアスに設定します。 以下に例を示します。

    <?xml version="1.0"?>
    <!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.1.0//EN"
        "http://www.liferay.com/dtd/liferay-service-builder_7_1_0.dtd">
    
    <service-builder package-path="com.liferay.example" >
        <namespace>TestDB</namespace>
        <entity local-service="true" name="Foo" table="testdata" data-source="extDataSource"
                remote-service="false" uuid="false">
               <column name="id" db-name="id" primary="true" type="long" />
               <column name="foo" db-name="foo" type="String" />
               <column name="bar" db-name="bar" type="long" />
        </entity>
    </service-builder>

例の `<entity>` タグ属性に注意してください。

*`data-source`*： `liferayDataSource` エイリアス `ext-spring.xml` 指定します。

*`table`*：エンティティのデータベーステーブル。

また、エンティティの `<column>`には、列名に設定された `db-name` 属性が必要であることに注意してください。

## ステップ4：Service Builderを実行する

[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder)実行します。 これで、Service Builderサービスはデータソースを使用します。 あなたことができます [ビジネスロジックでサービスを利用](/docs/7-1/tutorials/-/knowledge_base/t/business-logic-with-service-builder) あなたは関係なく、常に基になるデータソースの持っているとして。

おめでとうございます！ Service Builderを外部データソースに接続しました。

## 関連トピック

[JNDIデータソースへの接続](/docs/7-1/tutorials/-/knowledge_base/t/connecting-to-data-sources-using-jndi)

[サービスビルダー](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)

[Service Builderの実行と生成されたコードの理解](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder)

[Service Builderを使用したビジネスロジック](/docs/7-1/tutorials/-/knowledge_base/t/business-logic-with-service-builder)
