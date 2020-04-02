---
header-id: deploying-a-project-built-with-maven-to-product
---

# Mavenでビルドされたプロジェクトを@product@にデプロイする

[TOC levels=1-4]

MavenでビルドされたLiferayプロジェクトをデプロイするには、以下の2つの方法があります。

1. 作成されたMaven JAR/WARを@product@インスタンスの`/deploy`フォルダにコピーをする方法。
2. コマンドラインからMavenコマンドを実行して、@product@インスタンスに自動的にデプロイするようにMavenプロジェクトを設定する方法。

デプロイメントのためにJAR/WARを手動でコピーすることは実行可能なオプションですが、プロジェクトをデプロイするには非効率的な方法です。Maven POMの簡単な設定を使って、1回のコマンド実行でプロジェクトを@product@にデプロイすることができます。

| **注：**Liferay Portalの以前のバージョンでは、|`liferay:deploy`コマンドを実行して、作成されたMavenプロジェクトをLiferay| サーバーにデプロイできました。`liferay-maven-plugin`は| LiferayアーキタイプからビルドされたMavenプロジェクトに適用されないため、この方法はもう使えません。

このチュートリアルでは、モジュールJARをデプロイする場合に、OSGiモジュールJARを作成するようにプロジェクトが設定されていることを前提条件としています。まだこれを行っていない場合は 、[Creating a Module JAR Using Maven](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-module-jar-using-maven)のチュートリアルで詳細を確認してください。

1. Liferay Mavenプロジェクトの親`pom.xml`ファイルに、以下のプラグイン設定を追加します。

       <build>
       <plugins>
       <plugin>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.portal.tools.bundle.support</artifactId>
       <version>3.2.1</version>
       <executions>
       <execution>
       <id>deploy</id>
       <goals>
       <goal>deploy</goal>
       </goals>
       <phase>pre-integration-test</phase>
       </execution>
       </executions>
       </plugin>
       </plugins>
       </build>
   
   このPOM設定は、`groupId`、`artifactId`、および`version`を定義することにより、Liferayの[Bundle Supportプラグイン](/docs/7-1/reference/-/knowledge_base/r/bundle-support-plugin)を適用します。このプラグインの詳細については、[Maven Workspace](/docs/7-1/tutorials/-/knowledge_base/t/maven-workspace)のチュートリアルを参照してください。このロジックは、[executions](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_executions_Tag)タグも定義します。このタグは、Mavenプロジェクトのビルドライフサイクルの`インテグレーション事前テスト`フェーズで、Bundle Supportプラグインを実行するように設定します。`deploy`
   [goal](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals)は、ライフサイクルフェーズ用に定義されています。

2. デフォルトでは、 Bundle Supportプラグインはプラグインの親フォルダにある`bundles`フォルダの[Liferayインストール]にデプロイされます。この方法でプロジェクトを設定していない場合は、POMでLiferayのホームフォルダを定義する必要があります。これを行うには、`plugin`タグ内で`execution`タグの外部に以下のロジックを追加します。

       <configuration>
       <liferayHome>LIFERAY_HOME_PATH</liferayHome>
       </configuration>
   
   設定例は、以下のようになります。

       <configuration>
       <liferayHome>C:/liferay/liferay-ce-portal-7.1-ga1</liferayHome>
       </configuration>
   
   | **注：**以前のLiferay Portalバージョン用にビルドされたMavenアプリケーションは、| さまざまなタスク（例: Liferayサーバーへのデプロイなど）を実行するために| `<liferay.maven.plugin.version>`タグが必要でした。このタグは、古い| `liferay-maven-plugin`が使用されなくなったため、もう必要ありません。

3. 以下のコマンドを実行して、プロジェクトをデプロイします。

       mvn verify
   
 これで、Liferay Mavenプロジェクトがビルドされ、@product@インスタンスに自動的にデプロイされます。
