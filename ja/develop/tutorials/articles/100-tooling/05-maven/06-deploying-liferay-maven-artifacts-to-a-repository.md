---
header-id: deploying-liferay-maven-artifacts-to-a-repository
---

# Liferay Mavenアーティファクトのリポジトリへのデプロイ

[TOC levels=1-4]

Mavenプロジェクトを他の人と共有する場合は、アーティファクトをリモートリポジトリにデプロイすることが重要になります。そのためには、まず、デプロイされたMavenアーティファクトを保持できるリモートリポジトリが必要です。現時点でリモートリポジトリがない場合は、[Creating a Maven Repository](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-maven-repository)のチュートリアルを参照して、Nexusリポジトリを設定する方法を確認してください。また、`[USER_HOME]/.m2/settings.xml`ファイルがリモートリポジトリのID、ユーザー名、およびパスワードを指定していることも確認してください。

リモートリポジトリにデプロイするには、Mavenを使用してLiferayプロジェクトをパッケージ化する必要があります。Mavenは、リモートリポジトリに簡単にデプロイできるアーティファクト（JAR）を作成するパッケージングコマンドを提供します。ここでは、Liferayポートレットモジュールを使用して、これを行う方法を説明します。

デプロイ可能なアーティファクトを作成したら、モジュールプロジェクトを設定してリモートリポジトリと通信し、Mavenの`deploy`コマンドを使用して送信します。モジュールプロジェクトがリモートリポジトリに配置されると、他の開発者は使っているプロジェクトでリモートリポジトリを設定し、プロジェクトPOMでそれを参照するように依存関係を設定することができます。

このチュートリアルを実行するには、MavenでビルドされたLiferayモジュールが必要です。このチュートリアルではデモンストレーションのために、`portlet.ds`サンプルモジュールプロジェクトを使用します。このモジュールを実行するには、[portlet.ds](https://portal.liferay.dev/documents/113763090/114000186/portlet.ds.zip)Zipをダウンロードしてください。

1. マシンの任意の場所に、Liferayモジュールの親フォルダとして機能するフォルダを作成します。`portlet.ds`モジュールプロジェクトをそのフォルダに解凍します。

2. このフォルダ内に、`pom.xml`ファイルを作成します。以下のロジックを親POMにコピーします。

       <?xml version="1.0" encoding="UTF-8"?>
       <project
       xmlns="http://maven.apache.org/POM/4.0.0"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"
       >
       
       <modelVersion>4.0.0</modelVersion>
       <groupId>liferay.sample</groupId>
       <artifactId>liferay.sample.maven</artifactId>
       <version>1.0.0</version>
       <name>Liferay Maven Module Projects</name>
       <packaging>pom</packaging>
       
       <distributionManagement>
       <repository>
       <id>liferay-releases</id>
       <url>http://localhost:8081/nexus/content/repositories/liferay-releases</url>
       </repository>
       </distributionManagement>
       
       <modules>
       <module>portlet.ds</module>
       </modules>
       </project>
   
   `<modelVersion>`から`<packaging>`までのタグは、親POMで頻繁に使用されるPOMタグです。詳細については、Mavenの[POM Reference](https://maven.apache.org/pom.html)のドキュメンテーションを参照してください。

   `<distributionManagement>`タグは、親フォルダに存在するすべてのモジュールプロジェクトのデプロイリポジトリを指定します。ここでは、リポジトリのIDとURLを含める必要があります。上記の`distributionManagement`宣言は、[Creating a Maven Repository](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-maven-repository)のチュートリアルで作成されたLiferay Nexusリポジトリに対して設定されます。このチュートリアルでは、リモートリポジトリのID、ユーザー名、およびパスワードを指定する`[USER_HOME]/.m2/settings.xml`も作成しました。モジュールをリモートリポジトリにデプロイするには、親POMと`settings.xml`ファイルのリポジトリ宣言の両方が必要です。

   最後に、`<modules>`タグを使用して、デプロイする親フォルダにあるモジュールをリストする必要があります。`portlet.ds`モジュールは、そのタグ内に指定されています。

3. `portlet.ds`モジュールの`pom.xml`ファイルを開きます。`portlet.ds`モジュールプロジェクトのZipをダウンロードしていない場合は、以下のPOMを参照してください。

       <project
       xmlns="http://maven.apache.org/POM/4.0.0"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"
       >
       
       <modelVersion>4.0.0</modelVersion>
       <artifactId>portlet.ds</artifactId>
       <version>1.0.0</version>
       <packaging>jar</packaging>
       
       <parent>
       <groupId>liferay.sample</groupId>
       <artifactId>liferay.sample.maven</artifactId>
       <version>1.0.0</version>
       <relativePath>../pom.xml</relativePath>
       </parent>
       
       <dependencies>
       <dependency>
       <groupId>javax.portlet</groupId>
       <artifactId>portlet-api</artifactId>
       <version>2.0</version>
       <scope>provided</scope>
       </dependency>
       <dependency>
       <groupId>org.osgi</groupId>
       <artifactId>org.osgi.service.component.annotations</artifactId>
       <version>1.3.0</version>
       <scope>provided</scope>
       </dependency>
       </dependencies>
       </project>
   
   `portlet.ds`モジュールのPOMは、最初に独自の属性を指定し、親POMの属性が続きます。上記のように`<parent>`タグを宣言すると、`portlet.ds`モジュールがその親POMにリンクされます。これは、リモートリポジトリにデプロイするために必要です。次に、モジュールの依存関係がリストされます。
これらの依存関係は、`portlet.ds`モジュールをパッケージ化するときにセントラルリポジトリからダウンロードされ、ローカルの`.m2`リポジトリにインストールされます。

4. これで、親POMとモジュールPOMが設定できたので、Mavenプロジェクトをパッケージ化します。コマンドラインを使用して、モジュールプロジェクト（例: `project.ds`）に移動し、以下のMavenパッケージコマンドを実行します。

       mvn package
   
   これにより、モジュールのすべての依存関係がダウンロードおよびインストールされ、プロジェクトがJARファイルにパッケージ化されます。モジュールプロジェクトの作成されたビルドフォルダ（例: `/target`）に移動します。そこに新しく作成されたJARファイルがあるはずです。
これは、Nexusリポジトリにデプロイするアーティファクトです。

5. Mavenのデプロイコマンドを実行して、モジュールプロジェクトのアーティファクトを設定済みのリモートリポジトリにデプロイします。

       mvn deploy
   
   コンソールには、リポジトリサーバーにデプロイされているアーティファクトからの出力が表示されます。

アーティファクトがデプロイされていることを確認するには、Nexusサーバーの*[リポジトリ]*ページに移動して、リポジトリを選択します。リポジトリにデプロイされたLiferayアーティファクトを示すウィンドウが下に表示されます。

![図1：リポジトリサーバーは、Liferay Mavenアーティファクトへのアクセスを提供します。](../../../images/maven-verify-deployment.png)

 Liferayモジュールプロジェクトをリモートリポジトリにアーティファクトとしてデプロイしたことで、誰とでも共有できるようになりました。
