---
header-id: upgrading-app-jsp-hook-plugins
---

# アプリケーションJSPフックのアップグレード

[TOC levels=1-4]

OSGiモジュールのJSPは、モジュールフラグメントを使用してカスタマイズできます。 モジュールフラグメントはホストモジュールに接続して、JSPを変更します。 OSGiランタイムにとって、フラグメントはホストモジュールの一部です。 [OSGi Alliance's core specification document](https://www.osgi.org/developer/downloads/release-6/)のセクション3.14で、モジュールフラグメントの詳細について説明しています。 このチュートリアルでは、アプリケーションJSPフックを@product-ver@にアップグレードする方法を示します。

Liferay Upgrade Plannerの[*アップグレードの問題の修復*ステップ](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems)は、アプリケーションJSPフックプラグインからモジュールフラグメントを生成します。 このツールは、フックがプラグインSDKにある場合はプラグインSDKルートと同じフォルダに、またはフックがLiferay Workspaceにある場合は`[liferay_workspace]/ modules`フォルダにモジュールフラグメントを作成します。

モジュールフラグメントは、このJAに従います。：`[plugin_name]-[app]-fragment` たとえば、プラグインの名前が`app-jsp-hook`で、それがブログアプリケーションのJSPを変更する場合、Upgrade Plannerは`app-jsp-hook-blogs-fragment`という名前のモジュールフラグメントを生成します。

アプリケーションJSPフックプラグインをアップグレードする手順は次のとおりです。

1.  [フラグメントホストの宣言](#declare-the-fragment-host)
2.  [JSPをアップデートする](#update-the-jsp)

## フラグメントホストの宣言

モジュールフラグメントの`bnd.bnd`ファイルは、ホストモジュール名とバージョンに設定されたOSGiヘッダー`Fragment-Host`を指定する必要があります。

ホストモジュールが@product@のアプリ・スイートのいずれかに属している場合、Code Upgrade Toolは、適切な`Fragment-Host`ヘッダーを自動的に指定する`bnd.bnd`ファイルを生成します。

例として、Blogs Webモジュールにモジュールフラグメントを接続する`Fragment-Host`を次に示します。

    Fragment-Host: com.liferay.blogs.web;bundle-version="1.1.9"

JSPのアップデートも同様に簡単に行えます。

## JSPをアップデートする

Upgrade Plannerは、カスタムアプリケーションJSPのアップグレードバージョンを含むモジュールフラグメントを作成します。

次の表は、古いJSPパスと新しいJSPパスを示しています。

| Liferay Portalバージョン | JSPファイルのパス                                                  |
| ------------------- | ----------------------------------------------------------- |
| **6.2**             | `docroot/META-INF/custom_jsps/html/portlet/[jsp_file_path]` |
| **7.1**             | `src/main/resources/META-INF/resources/[jsp_file_path]`     |

たとえば、Upgrade Plannerは、ブログアプリケーションの`init-ext.jsp`ファイルのカスタマイズバージョンを次のように生成します。

    src/main/resources/META-INF/resources/blogs/init-ext.jsp

ツールの[*アップグレードの問題の修復*ステップ](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems)では、カスタムJSPと元のJSPを比較できます。

  - カスタム6.2 JSPを元の6.2 JSPと比較します。
  - カスタム7.1 JSPとカスタム6.2 JSPを比較します。

7.1カスタムJSPで必要な追加の変更を加えます。 次に、モジュールフラグメントを[デプロイ](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)します。 これにより、ホストモジュールの一時的停止、フラグメントのホストへの接続、ホストモジュールの再起動が行われます。 コンソール出力はこのプロセスを反映しています。

以下は、ブログWebモジュールに接続するモジュールフラグメントのデプロイからの出力です。

    19:23:11,740 INFO  [Refresh Thread: Equinox Container: 00ce6547-2355-0017-1884-846599e789c4][BundleStartStopLogger:38] STOPPED com.liferay.blogs.web_1.1.9 [535]
    19:23:12,910 INFO  [Refresh Thread: Equinox Container: 00ce6547-2355-0017-1884-846599e789c4][BundleStartStopLogger:35] STARTED com.liferay.blogs.web_1.1.9 [535]

これでカスタムJSPは本番環境になりました。

## 関連トピック

[JSPのカスタマイズ](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps)

[コアJSPフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-jsp-hooks)

[プラグインの依存関係の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Liferay Mavenビルドのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
