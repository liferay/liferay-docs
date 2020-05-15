---
header-id: using-service-builder-in-a-maven-project
---

# MavenプロジェクトでのService Builderの使用

[TOC levels=1-4]

Liferayの[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder)は、多くのLiferayモジュールプロジェクトで頻繁に使用されるモデル駆動型のサービス作成ツールです。Liferay Mavenプロジェクトがある場合、Service BuilderはMavenプロジェクトで動作します。

Service BuilderをMavenプロジェクトに追加するにあたって、最も簡単な方法は、Liferayが提供しているService Builderアーキタイプを使用して、新しいMavenプロジェクトを作成することです。Service BuilderのMavenプロジェクトを作成する方法は、[Service Builder Template](/docs/7-1/reference/-/knowledge_base/r/using-the-service-builder-template)の記事で説明しています。以下の場合は、様々な理由により、このテンプレートを使用できません。

- OSGiモジュラーアーキテクチャに従うように、レガシーMavenプロジェクトをアップデートしている場合。
- 既存のモジュラーMavenプロジェクトがあり、最初からやり直したくない場合。

レガシーService Builderプロジェクトのアップグレードについて分からない点がある場合は、[From Liferay 6 to 7](/docs/7-1/tutorials/-/knowledge_base/t/from-liferay-6-to-liferay-7)のチュートリアルセクションを参照してください。

以下の手順に従って、MavenプロジェクトでService Builderが使用できるように設定をします。

1. Mavenプロジェクトの`pom.xml`ファイルに、以下のService Builderプラグインを適用します。

       <build>
       <plugins>
       <plugin>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.portal.tools.service.builder</artifactId>
       <version>1.0.182</version>
       <configuration>
       <apiDirName>../basic-api/src/main/java</apiDirName>
       <autoImportDefaultReferences>true</autoImportDefaultReferences>
       <autoNamespaceTables>true</autoNamespaceTables>
       <buildNumberIncrement>true</buildNumberIncrement>
       <hbmFileName>src/main/resources/META-INF/module-hbm.xml</hbmFileName>
       <implDirName>src/main/java</implDirName>
       <inputFileName>service.xml</inputFileName>
       <modelHintsFileName>src/main/resources/META-INF/portlet-model-hints.xml</modelHintsFileName>
       <mergeModelHintsConfigs>src/main/resources/META-INF/portlet-model-hints.xml</mergeModelHintsConfigs>
       <osgiModule>true</osgiModule>
       <propsUtil>com.liferay.blade.samples.servicebuilder.service.util.PropsUtil</propsUtil>
       <resourcesDirName>src/main/resources</resourcesDirName>
       <springFileName>src/main/resources/META-INF/spring/module-spring.xml</springFileName>
       <springNamespaces>beans,osgi</springNamespaces>
       <sqlDirName>src/main/resources/META-INF/sql</sqlDirName>
       <sqlFileName>tables.sql</sqlFileName>
       <testDirName>src/main/test</testDirName>
       </configuration>
       </plugin>
       </plugins>
       </build>
   
   Service Builderは、`groupId`、`artifactId`、および`version`を指定することで適用されます。上記で使用される`configuration`タグは、Service Builderプロジェクトの設定がどのように見えるのかを表す一つの例です。上記のすべての設定属性は、プロジェクトに合わせて変更する必要があります。

   上記のコードは、サンプルの`basic-service`モジュール用にService Builderを設定しています。この設定でService Builderを実行すると、`basic-api`モジュールの`src/main/java`フォルダにプロジェクトのAPIクラスが作成されます。また、ハイバネートモジュールマッピング、実装ディレクトリ名、モデルヒントファイル、Spring設定、SQL設定などを指定することもできます。[Service Builder Plugin](/docs/7-1/reference/-/knowledge_base/r/service-builder-with-maven)のリファレンス記事では、設定可能なすべてのService Builderのプロパティを参照することができます。また、Service Builderを設定するために`service.xml`ファイルを定義する方法の詳細については、[Defining an Object-Relational Map with Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/defining-an-object-relational-map-with-service-builder)のチュートリアルを参照してください。

2. Service Builderプラグイン宣言の直後に、以下のWSDD Builderプラグイン宣言を適用します。

       <plugin>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.portal.tools.wsdd.builder</artifactId>
       <version>1.0.3</version>
       <configuration>
       <inputFileName>service.xml</inputFileName>
       <outputDirName>src/main/java</outputDirName>
       <serverConfigFileName>src/main/resources/server-config.wsdd</serverConfigFileName>
       </configuration>
       </plugin>
   
   プロジェクトのリモートサービスを作成するには、WSDD Builderが必要です。
WSDD（Web Service Deployment Descriptor）の詳細については、[Creating Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/creating-remote-services)のチュートリアルを参照してください。
Service Builderの設定と同様に、`service.xml`ファイルはプロジェクトのリモートサービスを定義するように設定されます。また、`outputDirName`は`*_deploy.wsdd`および`*_undeploy.wsdd`ファイルが作成される場所を定義します。したがって、[`server-config.wsdd`ファイル](http://axis.apache.org/axis/java/reference.html#Global_Axis_Configuration)も必ず定義してください。

 これで、プロジェクトのPOMで`com.liferay.portal.tools.service.builder`および`com.liferay.portal.tools.wsdd.builder`プラグインを適用することにより、MavenプロジェクトがService Builderを使用するように正常に設定されました。Service Builderを実行するには、[Running Service Builder and Understanding the Generated Code](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder)のチュートリアルを参照してください。
