---
header-id: upgrading-portlets-that-use-service-builder
---

# Service Builderを使用するポートレットのアップグレード

[TOC levels=1-4]

@product-ver@は引き続き[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)を使用するため、永続性の詳細ではなく、アプリケーションのビジネスロジックに集中できます。 モデルクラス、ローカルおよびリモートサービス、および永続性は引き続き生成されます。

このチュートリアルでは、Guestbookポートレット（プロジェクト`guestbook-portlet`）と呼ばれる、LiferayプラグインSDK 6.2ポートレットのアップグレードについて説明します。 Liferay Portal 6.2ラーニングパス[Writing a Liferay MVC Application](/docs/6-2/tutorials/-/knowledge_base/t/writing-a-liferay-mvc-application)の、[Writing a Data-Driven Application](/docs/6-2/tutorials/-/knowledge_base/t/writing-a-data-driven-application)セクションからのチュートリアルです。

![図1：Guestbookポートレットは、ゲストブックとゲストブックエントリをモデル化します。](../../../../images/upgrade-service-builder-portlet-guestbook.png)

このチュートリアルを最大限に活用するには、[オリジナルポートレットのソースコード](https://portal.liferay.dev/documents/113763090/114000186/guestbook-portlet-pre-7-0-upgrade.zip)および[アップグレードされたソースコード](https://portal.liferay.dev/documents/113763090/114000653/guestbook-portlet-post-7-1-upgrade.zip)をダウンロードして参照できます。

Guestbookポートレットには、次の特性があります。

  - `MVCPortlet`を拡張
  - モデル、ビュー、コントローラーレイヤーを分離
  - Service BuilderでのHibernateによる永続性
  - JSPによって実装された表示レイヤー
  - マニュアルの依存関係管理に依存
  - LiferayプラグインSDK 6.2で開発

ほとんどのService Builderポートレットのアップグレードには、次の手順が含まれます。

1.  [@product-ver@のAPIにコードを適合させる](#1-adapt-the-code-to-product-vers-api)
2.  [依存関係を解決する](#2-resolve-dependencies)
3.  [サービスを構築する](#3-build-the-services)

まずはコードを適応させることから始めます。

## 1\. @product-ver@のAPIにコードを適合させる

[Liferay Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)を使用してコードをアップデートし、コンパイルの事象をすばやく解決します。 次に、残っているコンパイルエラーをマニュアルで修正します。

Guestbookポートレットには、次のコンパイルエラーがあります。

    /html/guestbook/view.jsp(58,1) PWC6131: Attribute total invalid for tag search-container-results according to TLD

`view.jsp`ファイルは、@product-ver@の`liferay-ui`タグライブラリに存在しないタグライブラリ属性`total`を指定します。 2番目の属性`total`に注意してください。

    <liferay-ui:search-container-results
        results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                        guestbookId, searchContainer.getStart(),
                        searchContainer.getEnd())%>"
        total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
                        guestbookId)%>" />

このようなタグを作るため、`total`属性の割り当てを削除します。

    <liferay-ui:search-container-results
        results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                        guestbookId, searchContainer.getStart(),
                        searchContainer.getEnd())%>" />

これは、マニュアルで修正する必要があるGuestbookポートレットの唯一のコンパイルエラーです。

## 2\. 依存関係を解決する

Guestbookポートレットの依存関係は変更されていないため、解決する依存関係はありません。

ポートレットの依存関係を適合させる必要がある場合は、チュートリアル[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)を参照してください。

## 3\. サービスを構築する

Liferay Portal 6.2で行ったように、[サービスを構築](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder)します。

Guestbookポートレットの`service.xml`ファイルは、`exception`要素で例外クラス名を指定します。

    <service-builder package-path="com.liferay.docs.guestbook">
        ...
        <exceptions>
            <exception>GuestbookName</exception>
            <exception>EntryName</exception>
            <exception>EntryMessage</exception>
            <exception>EntryEmail</exception>
        </exceptions>
    </service-builder>

Liferay Portal6.2では、Service Builderは`package-path`が指定するパス属性に、例外クラスを生成します。 @product-ver@では、Service Builderはそれらを`[package-path]/exception`に生成します。

古いパス：

    [package-path]

新しいパス：

``` 
[package-path]/exception 
```

例として、Guestbookポートレットのパッケージパスは`com.liferay.docs.guestbook`です。 `exception`要素`GuestbookName`用の例外クラスは、`docroot/WEB-INF/service/com/liferay/docs/guestbook/exception`へ生成されます。 例外を使用するクラスは、`com.liferay.docs.guestbook.exception.GuestbookNameException`をインポートする必要があります。

ポートレットの例外クラスへの参照をアップデートします。

通常通りにポートレットをデプロイします。 サーバーは、次のポートレット状態を示すメッセージを出力します。

  - WAR processing
  - WAB startup
  - Availability to users

Guestbookポートレットをデプロイすると、次のメッセージが生成されます。

    2018-03-21 18:23:10.032 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:262] Processing guestbook-portlet-7.1.0.1.war
    2018-03-21 18:23:15.355 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseAutoDeployListener:43] Copying portlets for C:\portals\liferay-ce-portal-7.1-m1\tomcat-8.0.32\temp\20180321182315333UGEPAGTR\guestbook-portlet.war
    2018-03-21 18:23:15.829 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseDeployer:876] Deploying guestbook-portlet.war
    2018-03-21 18:23:17.797 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseAutoDeployListener:50] Portlets for C:\portals\liferay-ce-portal-7.1-m1\tomcat-8.0.32\temp\20180321182315333UGEPAGTR\guestbook-portlet.war copied successfully
    2018-03-21 18:23:19.621 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][HotDeployImpl:226] Deploying guestbook-portlet from queue
    2018-03-21 18:23:19.621 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PluginPackageUtil:1003] Reading plugin package for guestbook-portlet
    2018-03-21 18:23:19.642 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][ServiceComponentLocalServiceImpl:598] Running GB SQL scripts
    21-Mar-2018 18:23:19.669 INFO [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war] org.apache.catalina.core.ApplicationContext.log Initializing Spring root WebApplicationContext
    2018-03-21 18:23:20.066 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PortletHotDeployListener:186] Registering portlets for guestbook-portlet
    2018-03-21 18:23:20.271 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PortletHotDeployListener:298] 1 portlet for guestbook-portlet is available for use
    2018-03-21 18:23:20.468 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BundleStartStopLogger:35] STARTED guestbook-portlet_7.1.0.1 [657]

これで、ポートレットが@product@で利用可能になりました。

Service Builderを使用したポートレットのアップグレードおよびデプロイが完了しました。

## 関連トピック

[プラグインSDKプロジェクトをWorkspaceとGradleに移行する](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)

[依存関係管理ツールの使用](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-a-dependency-management-tool)

[WABジェネレーターの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)

[データアップグレードプロセスの移行](/docs/7-1/tutorials/-/knowledge_base/t/optimizing-app-upgrade-processes)
