---
header-id: setting-proxy-requirements-for-liferay-workspace
---

# Liferay Workspaceのプロキシ要件の設定

[TOC levels=1-4]

プロキシサーバーを使用して、外部リポジトリにアクセスする必要がある企業のファイアウォールのバックグラウンドで作業している場合、Liferay Workspaceを環境内で動作させるために追加設定を行う必要があります。ここでは、Gradle環境とMaven環境の両方にプロキシ要件を設定する方法を説明します。

## Gradleを使用する

1. `/.gradle/gradle.properties`ファイルを開きます。このファイルが存在しない場合は、作成します。

2. ファイルに以下のプロパティを追加します。

       systemProp.http.proxyHost=www.somehost.com
       systemProp.http.proxyPort=1080
       systemProp.https.proxyHost=www.somehost.com
       systemProp.https.proxyPort=1080
   
   プロキシのホストとポートの値を独自のものに置き換えてください。

3. プロキシサーバーで認証が必要な場合は、以下のプロパティも追加します。

       systemProp.http.proxyUser=userId
       systemProp.http.proxyPassword=yourPassword
       systemProp.https.proxyUser=userId
       systemProp.https.proxyPassword=yourPassword
   
 プロキシ設定は、Liferay WorkspaceのGradle環境で設定されます。

## Mavenを使用する

1. `~/.m2/settings.xml`ファイルを開きます。このファイルが存在しない場合は作成します。

2. 以下のXMLスニペットをファイルに追加します。

       <?xml version="1.0" encoding="UTF-8"?>
       <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
       <proxies>
       <proxy>
       <id>httpProxy</id>
       <active>true</active>
       <protocol>http</protocol>
       <host>www.somehost.com</host>
       <port>1080</port>
       </proxy>
       <proxy>
       <id>httpsProxy</id>
       <active>true</active>
       <protocol>https</protocol>
       <host>www.somehost.com</host>
       <port>1080</port>
       </proxy>
       </proxies>
       </settings>
   
   プロキシのホストとポートの値を独自のものに置き換えてください。

3. プロキシサーバーで認証が必要な場合は、`username`および` password`のプロキシプロパティも追加します。たとえば、HTTPプロキシの認証設定は以下のようになります。

       <proxy>
       <id>httpProxy</id>
       <active>true</active>
       <protocol>http</protocol>
       <host>www.somehost.com</host>
       <port>1080</port>
       <username>userID</username>
       <password>somePassword</password>
       </proxy>
   
 これで、Mavenのプロキシ設定が完了しました。
