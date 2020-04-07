---
header-id: upgrading-a-servlet-based-portlet
---

# サーブレットベースのポートレットのアップグレード

[TOC levels=1-4]

このチュートリアルでは、サーブレットベースのポートレットをアップグレードする方法を示します。 *Sample JSON*（project `sample-json-portlet`）と呼ばれる、サンプルのサーブレットベースのポートレットをアップグレードする前後のコードを参照します。 ポートレットには*Click me*リンクが表示され、 ユーザーがリンクをクリックすると、Liferayロゴが表示されます。

![図1：Sample JSONポートレットには*Click me*というテキストが表示され、クリックすると操作を開始できます。](../../../../images/upgrading-portlets-sample-servlet-json-initial-view.png)

このチュートリアルを最大限に活用するには、[オリジナルのサンプルポートレットのソースコード](https://portal.liferay.dev/documents/113763090/114000186/sample-json-portlet-pre-7-0-upgrade.zip)および[アップグレードされたソースコード](https://portal.liferay.dev/documents/113763090/114000653/sample-json-portlet-post-7-1-upgrade.zip)をダウンロードし、参照してください。

サンプルポートレットの特性は次のとおりです。

  - `javax.servlet.HttpServlet`を拡張するサーブレットを使用して、リクエストを処理
  - JSPによって実装されたビューレイヤー
  - JSONオブジェクトを使用してデータを処理
  - マニュアルの依存関係管理に依存
  - Liferay Portal 6.2が提供するサードパーティライブラリに依存
  - `WEB-INF/lib`フォルダに追加の依存関係を埋め込む
  - LiferayプラグインSDK 6.2で開発

サーブレットベースのポートレットをアップグレードするには、Liferay Upgrade Plannerを使用して、コードを@product-ver@のAPIに適合させます。 プランナーの*アップグレードの問題の修復*ステップを実行した時、既存の事象の多くが自動修正またはフラグ付けされました。 残りのエラーについては、記事[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)を参照してください。

Liferayの[Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)は、新しいAPIの影響を受けるコードの特定、APIの変更とそれらに適合させる方法の説明、そして多くの事例でコードを自動的に適合させるオプションを提供します。

サンプルポートレットはLiferay Portalに依存して、いくつかの依存関係JARファイルを提供しました。 以下は、ポートレットの`liferay-plugin-package.properties`ファイルからの`portal-dependency-jars`プロパティです。

    portal-dependency-jars=\
        dom4j.jar,\
        jabsorb.jar,\
        json-java.jar

@product@がエクスポートするパッケージの使用方法については、[こちら](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-packages-portal-exports)をご覧ください。 @product-ver@のコアシステムは、上記の各依存関係JARから、このポートレットに必要なパッケージをエクスポートします。

アップグレードされたサンプルポートレットは、`portal-dependency-jars`プロパティでこれらのJARを指定し続けます。 これらは、コンパイル時にポートレットで使用可能になります。 ただし、コンパイル時のパッケージがコアシステムのエクスポートされたパッケージと競合しないように、LiferayプラグインSDK 7.0はプラグインWARからJARを除外します。

次に、通常通りポートレットをデプロイします。

サーバーは、次のポートレット状態を示すメッセージを出力します。

  - WAR processing
  - WAB startup
  - Availability to users

| 注: サンプルのアップグレードされたポートレットをデプロイすると、 | [WABプロセッサー](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) | が`portal-dependency-jars`プロパティが非推奨であることを警告します。<br><br> | | 21:40:25,347 WARN \[fileinstall-...\]\[WabProcessor:564\] プロパティ"portal-dependency-jars"は非推奨です。 指定されたJARは、クラスパスに含まれない場合があります。 | | @product-ver@で実行する場合は、@product@がエクスポートするパッケージを使用するため、`portal-dependency-jars` | プロパティを | [instructions](resolving-a-plugins-dependencies#using-packages-liferay-portal-exports) | によって指定しても問題ありません。 アップグレード後、プロジェクトでの | [依存関係管理ツールの使用](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-a-dependency-management-tool) | を検討してください。 これは、今後の@product@バージョンに備えるうえで役立ち、| 依存関係の管理を容易にします。

ポートレットはLiferayのOSGiランタイムにインストールされ、ユーザーが利用できるようになります。

![図2：サンプルポートレットのリンク*Click me *をクリックすると、Liferayロゴが表示されます。](../../../../images/upgrading-portlets-sample-servlet-json.png)

これで、サーブレットベースのポートレットの、@product-ver@へのアップグレードとデプロイが完了しました。

**関連トピック**

[プラグインSDKプロジェクトをWorkspaceとGradleに移行する](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)

[依存関係管理ツールの使用](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-a-dependency-management-tool)

[WABジェネレーターの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)

[データアップグレードプロセスの移行](/docs/7-1/tutorials/-/knowledge_base/t/optimizing-app-upgrade-processes)
