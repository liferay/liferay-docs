---
header-id: servlet-filters
---

# サーブレットフィルター

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

サーブレットフィルターは、リクエストが到着したときに前処理を行い、クライアントブラウザーに進む前に応答を後処理できます。 サーブレットを知らなくても、複数のサーブレットの要求と応答に機能を適用できます。 一般的なフィルターの使用例を次に示します。

  - ロギング
  - 監査
  - トランザクション管理
  - セキュリティ

記述子のパターンを使用して、フィルターをサーブレットURLにマップできます。 これらのURLにリクエストが到着すると、フィルターがそれらを処理します。 フィルターチェーンにより、フィルターを希望の順序で適用できます。 Servlet Filter Hookプラグインを使用すると、Liferay Webアプリケーションを変更せずにフィルターを展開および展開解除できます。 サーブレットフィルターを作成およびデプロイする手順は次のとおりです。

1.  [サーブレットフィルタークラスを作成する](#step-1-create-a-servlet-filter-class)

2.  [URLをサーブレットフィルターにマップする](#step-2-map-urls-to-your-servlet-filter)

3.  [Liferayプラグイン記述子を作成する](#step-3-create-a-liferay-plugin-descriptor)

4.  [プラグインをデプロイする](#step-4-deploy-your-plugin)

参考として、サンプル [サーブレットフィルタープロジェクトコード](https://portal.liferay.dev/documents/113763090/114000653/sample-servlet-filter-hook.zip)ダウンロードできます。

従来のWebアプリケーション（`.war`）プロジェクトでは、サーブレットフィルタークラスの作成から始めます。

| **注：** | [ポートレットフィルター](https://portals.apache.org/pluto/portlet-3.0-apidocs/javax/portlet/filter/PortletFilter.html) |ポートレットのリクエストとレスポンスに機能を適用できます。 | [JSPオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/jsp-overrides-using-portlet-filters) |ポートレットフィルタを使用する1つの方法です。

## ステップ1：サーブレットフィルタークラスを作成する

[`javax.servlet.Filter`](https://tomcat.apache.org/tomcat-9.0-doc/servletapi/index.html)を実装するクラスを作成します。 サーブレットフィルタクラスの例を次に示します。

    package com.liferay.sampleservletfilter.hook.filter;
    
    import com.liferay.portal.kernel.util.WebKeys;
    
    import java.io.IOException;
    
    import javax.servlet.Filter;
    import javax.servlet.FilterChain;
    import javax.servlet.FilterConfig;
    import javax.servlet.ServletException;
    import javax.servlet.ServletRequest;
    import javax.servlet.ServletResponse;
    
    public class SampleFilter implements Filter {
    
        @Override
        public void destroy() {
            System.out.println("Called SampleFilter.destroy()");
        }
    
        @Override
        public void doFilter(
                ServletRequest servletRequest, ServletResponse servletResponse,
                FilterChain filterChain)
            throws IOException, ServletException {
    
            String uri = (String)servletRequest.getAttribute(
                WebKeys.INVOKER_FILTER_URI);
    
            System.out.println(
                "Called SampleFilter.doFilter(" + servletRequest + ", " +
                    servletResponse + ", " + filterChain + ") for URI " + uri);
    
            filterChain.doFilter(servletRequest, servletResponse);
        }
    
        @Override
        public void init(FilterConfig filterConfig) {
            System.out.println(
                "Called SampleFilter.init(" + filterConfig + ") where hello=" +
                    filterConfig.getInitParameter("hello"));
        }
    
    }

実装する [`Filter`](https://tomcat.apache.org/tomcat-9.0-doc/servletapi/index.html) メソッドは次のとおりです。

1.  `init（FilterConfig）`：フィルターを構成し、必要な初期化を実行します。

    たとえば、 `SampleFilter` が初期化されると、その `init（FilterConfig）` メソッドは `FilterConfig` オブジェクトと `hello` パラメーターの値を出力します。
   
        Called SampleFilter.init(com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterConfig@7c953747) where hello=world

2.  `doFilter（ServletRequest、ServletResponse、FilterChain）`：ここでリクエストとレスポンスをフィルタリングします。 フィルターを適用するには、 `filterChain.doFilter（servletRequest、servletResponse）`呼び出します。

    たとえば、ユーザーが `SampleFilter`マップされたURLにアクセスすると、その `doFilter（...）` メソッドは、呼び出しによって制御を次のフィルターに渡す前に、 `ServletResponse` オブジェクト、 `FilterChain` オブジェクト、 `ServletRequest` URIを出力します `filterChain.doFilter（servletRequest、servletResponse）`。
   
        Called SampleFilter.doFilter(org.apache.catalina.connector.RequestFacade@68be71e0, com.liferay.portal.servlet.filters.absoluteredirects.AbsoluteRedirectsResponse@2b598f1a, com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterChain@72220503) for URI /web/guest

3.  `destroy（）`：フィルターの不要なリソースをクリーンアップします。

    `SampleFilter` が破棄されると、その `destroy（）` メソッドは次のメッセージを出力します `Called SampleFilter.destroy（）`。

URLをサーブレットフィルターにマップします。

## ステップ2：URLをサーブレットフィルターにマップする

従来、サーブレットフィルターとそのフィルターマッピングを指定するには、Webアプリケーションの `web.xml` ファイルを変更する必要がありました。 ただし、@product@では、プラグインでそれらを指定できるため、@product@ Webアプリケーションを変更する必要はありません。 サンプルフィルターの場合のように、記述子ファイル `WEB-INF / liferay-hook.xml`でサーブレットフィルターマッピングを指定します。

    <?xml version="1.0"?>
    <!DOCTYPE hook PUBLIC "-//Liferay//DTD Hook 7.1.0//EN" "http://www.liferay.com/dtd/liferay-hook_7_1_0.dtd">
    
    <hook>
        <servlet-filter>
            <servlet-filter-name>Sample Filter</servlet-filter-name>
            <servlet-filter-impl>com.liferay.sampleservletfilter.hook.filter.SampleFilter</servlet-filter-impl>
            <init-param>
                <param-name>hello</param-name>
                <param-value>world</param-value>
            </init-param>
        </servlet-filter>
        <servlet-filter-mapping>
            <servlet-filter-name>Sample Filter</servlet-filter-name>
            <before-filter>SSO Open SSO Filter</before-filter>
            <url-pattern>/group/*</url-pattern>
            <url-pattern>/user/*</url-pattern>
            <url-pattern>/web/*</url-pattern>
            <url-pattern>*.jsp</url-pattern>
            <dispatcher>REQUEST</dispatcher>
            <dispatcher>FORWARD</dispatcher>
        </servlet-filter-mapping>
    </hook>

URLをサーブレットフィルターにマップする方法は次のとおりです。

1.  [Liferay Hook DTD](@platform-ref@/7.1-latest/definitions/liferay-hook_7_1_0.dtd.html)基づいて、記述子ファイル `WEB-INF / liferay-hook.xml`を作成します。

2.  `フック`サブ要素として `servlet-filter` 要素を追加します。 `servlet-filter` サブ要素を指定します。

    `servlet-filter-name`：任意の名前。 （必須）

    `servlet-filter-impl`： `フィルター` 実装クラス。 （必須）

    `init-param` 要素：初期化パラメーター。 （オプション）

3.  `フック`サブ要素として `servlet-filter-mapping` 要素を追加します。

    `servlet-filter-name`： `servlet-filter`使用されているものと一致します。 （必須）

    `after-filter`：このフィルターが追従するための `サーブレットフィルター` 名前。 （オプション）

    `before-filter`：このフィルターが前に行くための `サーブレットフィルター` 名前。 （オプション）

    `url-pattern` elements：要求と応答をフィルターするURLパターン。 （必須）

    `ディスパッチャ` の要素：指定 [`ディスパッチャ` 列挙定数を](https://docs.liferay.com/ce/portal/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/filters/invoker/Dispatcher.html#enum.constant.summary) へ [フィルタをリクエストに適用する方法制約](https://docs.oracle.com/cd/E19798-01/821-1841/bnagf/index.html)。 （オプション）

## ステップ3：Liferayプラグイン記述子を作成する

`WEB-INF / liferay-plugin-package.properties` ファイルで、プラグインがサポートする@product@のバージョンを指定します。

    liferay-versions=7.1.0+

## ステップ4：プラグインをデプロイする

これらの成果物のコンパイル時の依存関係を指定します。

  - `com.liferay.portal.kernel`
  - `javax.servlet-api`

グラドル:

    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.0.0"
    compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"

メイヴン:

    <dependency>
        <groupId>com.liferay.portal</groupId>
        <artifactId>com.liferay.portal.kernel</artifactId>
        <version>3.0.0</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.0.1</version>
    </dependency>

プラグイン `.war` ファイルをビルドし、 `[LIFERAY_HOME]/ deploy` フォルダーにコピーしてデプロイします。 Liferayの自動デプロイヤは、 `.war` を `[LIFERAY_HOME]/ osgi / war` フォルダーにコピーします。 [WAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) は、 `.war` をOSGi Web Application Bundle（WAB）に変換し、@product@のランタイムにインストールします。 コンソールの出力は次のようになります。

    2018-11-03 16:20:09.118 INFO  [fileinstall-C:/workspace_liferay/bundles/osgi/war][BaseAutoDeployListener:43] Copying hook plugin for C:\workspace_liferay\bundles\tomcat-9.0.6\temp\20181103162009108XCJZAKUY\sample-servlet-filter-hook.war
    2018-11-03 16:20:09.390 INFO  [fileinstall-C:/workspace_liferay/bundles/osgi/war][BaseDeployer:880] Deploying sample-servlet-filter-hook.war
    2018-11-03 16:20:10.019 INFO  [fileinstall-C:/workspace_liferay/bundles/osgi/war][BaseAutoDeployListener:50] Hook for C:\workspace_liferay\bundles\tomcat-9.0.6\temp\20181103162009108XCJZAKUY\sample-servlet-filter-hook.war copied successfully
    2018-11-03 16:20:10.730 INFO  [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28][BundleStartStopLogger:35] STARTED sample-servlet-filter-hook_7.1.10.1 [963]
    2018-11-03 16:20:11.050 INFO  [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28][HotDeployImpl:226] Deploying sample-servlet-filter-hook from queue
    2018-11-03 16:20:11.052 INFO  [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28][PluginPackageUtil:1001] Reading plugin package for sample-servlet-filter-hook
    03-Nov-2018 16:20:11.066 INFO [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28] org.apache.catalina.core.ApplicationContext.log Initializing Spring root WebApplicationContext
    2018-11-03 16:20:11.093 INFO  [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28][HookHotDeployListener:457] Registering hook for sample-servlet-filter-hook
    Called SampleFilter.init(com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterConfig@7c953747) where hello=world
    2018-11-03 16:20:11.134 INFO  [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28][HookHotDeployListener:533] Hook for sample-servlet-filter-hook is available for use

サーブレットコンテナは、フィルターの `init` メソッドを呼び出します。 たとえば、 `SampleFilter`デプロイすると、 `init` メソッドが呼び出され、次の出力が出力されます。

    Called SampleFilter.init(com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterConfig@7c953747) where hello=world

サーブレットフィルターにマッピングされたURLにアクセスすると、フィルターの `doFilter` メソッドが呼び出されます。 サンプルのサーブレットフィルターマッピングにはURL `/ web / *`含まれているため、 [http：// localhost：8080 / web / guest](http://localhost:8080/web/guest) アクセスすると `SampleFilter.doFilter`が呼び出され、これが出力されます。

    Called SampleFilter.doFilter(org.apache.catalina.connector.RequestFacade@68be71e0, com.liferay.portal.servlet.filters.absoluteredirects.AbsoluteRedirectsResponse@2b598f1a, com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterChain@72220503) for URI /web/guest

サーブレットフィルター `.war` アンデプロイすると、その `destroy（）` メソッドが呼び出されます。

サイトのURLへのリクエストのフィルタリングおめでとうございます。

## 関連トピック

[依存関係の構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)
