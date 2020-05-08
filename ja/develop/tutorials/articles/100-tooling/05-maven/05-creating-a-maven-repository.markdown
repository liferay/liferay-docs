---
header-id: creating-a-maven-repository
---

# Mavenリポジトリの作成

[TOC levels=1-4]

Liferayのアーティファクトとモジュールをチームメイトと共有したり、GUIを使用してリポジトリを管理したりしたい場合が多々出てくるでしょう。そういった場合には、[Sonatype Nexus](http://www.sonatype.org/nexus/)を使用して行います。これは、リリースサーバー、スナップショットサーバー、およびプロキシサーバーの作成と管理を容易にする、Mavenリポジトリの管理サーバーです。使用できるMavenリポジトリの管理サーバーは他にもいくつかありますが（例: [Artifactory](https://www.jfrog.com/artifactory/)）、このチュートリアルではNexusに焦点を当てています。

Nexusを使用してMavenリポジトリを作成するには、[Nexus](https://help.sonatype.com/display/NXRM2/Download)をダウンロードして、Nexusの[Installationページ](https://help.sonatype.com/display/NXRM2/Installing+and+Running)にある指示に従ってインストールし、起動させてください。

Nexusを使用して独自のリポジトリを作成するには、以下の手順を実行します。

1. Webブラウザを開いて、Nexusリポジトリサーバー（例: [http://localhost:8081/nexus](http://localhost:8081/nexus)）に移動し、ログインします。デフォルトのユーザー名は`admin`で、パスワードは`admin123`です。

2. *[リポジトリ]*をクリックして、*[追加...]* → *[ホストリポジトリ]*に移動します。

   ![図1：Liferayアーティファクトを保持するリポジトリの追加は、Nexusを使用すると簡単です。](../../../images/maven-nexus-create-repo.png)

   Nexusリポジトリの各タイプの詳細については、Sonatypeの[Managing Repositories](http://books.sonatype.com/nexus-book/reference/confignx-sect-manage-repo.html)のガイドを参照してください。

3. 保持するアーティファクトのタイプに適した、リポジトリプロパティを入力します。リリースバージョンのアーティファクトをリポジトリにインストールする場合は、リポジトリポリシーとして*Release*を指定します。以下は、リポジトリプロパティ値の例です。
   - **リポジトリID:** *liferay-releases*
   - **リポジトリ名:** *Liferay Release Repository*
   - **プロバイダー:** *Maven2*
   - **リポジトリポリシー:** *Release*

4. *[保存]*をクリックします。

これで、Nexusのリポジトリサーバーからアクセス可能なLiferay Mavenリポジトリが作成されます。

Mavenリポジトリを作成すると、作成したLiferayプロジェクトのそれぞれのスナップショットを保持することにも役立ちます。スナップショットリポジトリの作成は、リリースリポジトリの作成とほぼ同じです。唯一異なるのは、*Snapshot*をリポジトリポリシーとして指定する点です。以下は、スナップショットリポジトリのプロパティ値の例です。

- **リポジトリID：** *liferay-snapshots*
- **リポジトリ名：** *Liferay Snapshot Repository*
- **プロバイダー:** *Maven2*
- **リポジトリポリシー： ** *Snapshot*

 これで、Liferayリリース（つまり、`liferay-releases`）およびLiferayスナップショット（つまり、`liferay-snapshots`）のリポジトリが作成されます。LiferayのMavenアーティファクトをNexusリポジトリにデプロイする方法については、[Deploying Liferay Maven Artifacts to a Repository](/docs/7-1/tutorials/-/knowledge_base/t/deploying-liferay-maven-artifacts-to-a-repository)のチュートリアルを参照してください。

次に、Maven設定で新しいリポジトリサーバーを設定して、アーティファクトをインストールします。

## ローカルMaven設定の設定方法

リポジトリサーバーを使用する前に、Maven環境設定でリポジトリサーバーを指定しておく必要があります。リポジトリの設定により、Mavenがリポジトリを見つけ、アーティファクトを取得およびインストールします。Maven設定は、`[USER_HOME]/.m2/settings.xml`ファイルで行うことができます。

アーティファクト（例: Liferayアーティファクトやモジュールなど）を他のユーザーと共有している場合は、リポジトリサーバーを設定するだけで済みます。セントラルリポジトリやLiferayリポジトリからLiferayアーティファクトを自動でインストールし、アーティファクトの共有をするつもりがない場合は、Maven設定で指定されたリポジトリサーバーは必要ありません。セントラルリポジトリまたはLiferay独自のNexusリポジトリからアーティファクトをインストールするための詳細については、[Installing Liferay Maven Artifacts](/docs/7-1/tutorials/-/knowledge_base/t/installing-liferay-maven-artifacts)のチュートリアルを参照してください。

Maven環境が、`liferay-releases`および`liferay-snapshots`リポジトリサーバーにアクセスするように設定するには、以下の手順を実行します。

1. `[USER_HOME]/.m2/settings.xml`ファイルに移動します。このファイルがまだ存在していない場合は、作成します。

2. リポジトリサーバーの設定を提供します。`liferay-releases`および`liferay-snapshots`リポジトリサーバーが設定されている、`settings.xml`ファイルの内容は以下のとおりです。

       <?xml version="1.0"?>
       <settings>
       <servers>
       <server>
       <id>liferay-releases</id>
       <username>admin</username>
       <password>admin123</password>
       </server>
       <server>
       <id>liferay-snapshots</id>
       <username>admin</username>
       <password>admin123</password>
       </server>
       </servers>
       </settings>
   
ユーザー名の`admin`およびパスワードの`admin123`は、Nexusの管理者アカウント用のデフォルトの資格情報です。Nexusサーバーのこれらの資格情報を変更した場合は、必ず`settings.xml`にこれらの変更を提供してアップデートしてください。

これでリポジトリが設定されたので、ダウンロードするすべてのLiferay Mavenアーティファクトとこれから作成するLiferayモジュールアーティファクトを受け取る準備ができました。
