---
header-id: upgrading-a-liferay-jsf-portlet
---

# Liferay JSFポートレットのアップグレード

[TOC levels=1-4]

Liferay JSFポートレットは簡単にアップグレードでき、ほとんど必要を変更としません。 それらは@product@のJava APIとJavaScriptコードをカプセル化する[Liferay Faces](/docs/7-1/reference/-/knowledge_base/r/liferay-faces)プロジェクトと、インターフェースで接続します。 このため、JSFポートレットを@product-ver@にアップグレードするには、依存関係のアップデートのみが必要となります。

@product-ver@用のJSFポートレットの依存関係を見つける方法は2つあります。

  - ホームページ<http://liferayfaces.org/>では、@product@バージョン、JSFバージョン、およびコンポーネントスイートごとに依存関係（GradleまたはMaven）を調べることができます。
  - 記事[Liferay Faces Version Scheme](/docs/7-1/reference/-/knowledge_base/r/liferay-faces-version-scheme)のテーブルでは、@product@バージョン、JSFバージョン、ポートレットバージョン、およびAlloyUIとMetalコンポーネントスイートバージョンごとに、アーティファクトをリストしています。

このチュートリアルでは、サンプルJSF Applicantポートレットをアップグレードすることにより、いかに簡単にLiferay Portal 6.2 JSFポートレット（JSF 2.2）を@product-ver@へアップグレードできるかがわかるようになっています。 このポートレットは、ユーザーが送信できるジョブアプリケーションを提供しています。

![図1：JSF Applicantポートレットは、ユーザーが送信するジョブアプリケーションを提供します。](../../../../images/jsf-applicant-6-2.png)

参考として、[事前アップグレードされたポートレットコード](https://portal.liferay.dev/documents/113763090/114000186/jsf-applicant-portlet-6.2.zip)と[ポートレットコードのアップグレード](https://portal.liferay.dev/documents/113763090/114000653/jsf-applicant-portlet-7.1.zip)をダウンロードできます。 このサンプルプロジェクトではMavenを使用します。

Liferay JSFポートレットをアップグレードするには、次の手順に従います。

1.  Liferay JSFポートレットのビルドファイル（例: `pom.xml`, `build.gradle`）を、依存関係が設定されている場所へ開きます。

2.  サイト<http://liferayfaces.org/>に移動し、ポートレットをアップグレードする環境を選択して依存関係リストを生成します。

    ![図2：Liferay Facesのサイトには、多くの環境に適した依存関係を生成するオプションがあります。](../../../../images/jsf-dependency-generation.png)

3.  生成された依存関係をポートレットの依存関係と比較し、必要なアップデートを行います。 サンプルJSF Applicantポートレットの場合、Mojarra依存関係と2つのLiferay Faces依存関係をアップデートする必要があります。
<dependency> <groupId>org.glassfish</groupId> <artifactId>javax.faces</artifactId> <version>2.2.13</version> <scope>runtime</scope> </dependency> <dependency> <groupId>com.liferay.faces</groupId> <artifactId>com.liferay.faces.bridge.ext</artifactId> <version>3.0.0</version> </dependency> <dependency> <groupId>com.liferay.faces</groupId> <artifactId>com.liferay.faces.bridge.impl</artifactId> <version>4.0.0</version> </dependency>

    Update the dependencies according to the <http://liferayfaces.org/> dependency list. For example,
    
        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>javax.faces</artifactId>
            <version>2.2.18</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.ext</artifactId>
            <version>5.0.3</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.impl</artifactId>
            <version>4.1.2</version>
        </dependency>

これで完了です。\ Liferay JSFポートレットがアップグレードされ、@product-ver@\にデプロイ可能となりました。

Liferay JSFポートレットは、Portal 6.xにデプロイするのと同じ方法で@product-ver@にデプロイします。プラグインWARファイルが`[Liferay_Home]/ deploy`フォルダに到着すると、@product@のプラグイン互換レイヤーがWARをWebアプリケーションバンドル（WAB）に変換し、@product@のOSGiランタイムへのWABとしてポートレットをインストールします。

アップグレードされたポートレットをデプロイすると、サーバーは次のポートレット状態を示すメッセージを出力します。

  - WAR processing
  - WAB startup
  - Availability to users

Liferay JSFポートレットをデプロイすると、次のようなメッセージが生成されます。

    13:41:43,690 INFO ... [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:252] Processing com.liferay.faces.demo.jsf.applicant.portlet-1.0.war
    ...
    13:42:03,522 INFO  [fileinstall-C:/liferay-ce-portal-7.1-ga1/osgi/war][BundleStartStopLogger:35] STARTED com.liferay.faces.demo.jsf.applicant.portlet-1.0_4.1.0 [503]
    ...
    13:42:05,169 INFO  [fileinstall-C:/liferay-ce-portal-7.1-ga1/osgi/war][PortletHotDeployListener:293] 1 portlet for com.liferay.faces.demo.jsf.applicant.portlet-1.0 is available for use

ポートレットのデプロイが完了すると、@product@で利用可能になります。

![図3：@product-ver@\用のJSF Applicantポートレットが正常にアップデートされました。](../../../../images/jsf-applicant-7-1.png)

Liferay JSFポートレットをアップグレードおよびデプロイする方法を学習しました。 依存関係を解決し、通常通りポートレットをデプロイしました。 これらは簡単に行えます。
