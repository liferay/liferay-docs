---
header-id: configuring-a-spring-mvc-portlet
---

# Spring MVCポートレットの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

これは、Spring MVCポートレットを構成するための包括的なガイドではありません。 既にSpring MVCに精通していることを前提に、高いポイントをカバーしています。 そうでない場合は、 [LiferayのMVCフレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/liferay-mvc-portlet)の使用を検討する必要があります。

Liferay Spring MVCポートレットはどのようなものですか？ 他のSpring MVCポートレットとほぼ同じです。

## ポートレットの構成

`portlet.xml` ファイルの `portlet-class` 要素で、Springの `DispatcherPortlet`宣言する必要があります。

    <portlet-class>org.springframework.web.portlet.DispatcherPortlet</portlet-class>

Springフロントコントローラーは、アプリケーションコンテキストファイルの場所を知る必要があるため、 `portlet.xml` で初期化パラメーターとして指定します（必要に応じてパスを更新します）。

    <init-param>
        <name>contextConfigLocation</name>
        <value>/WEB-INF/spring/portlet-context.xml</value>
    </init-param>

Spring MVCポートレットで通常行うように指定されたアプリケーションコンテキストファイル（上記の例では`portlet-context.xml` ）を提供します。 次に、Webアプリケーションを構成します。

## Webアプリケーションの構成

あなたがいる場合は [LiferayはあなたのためのWAB生成させる](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) （これは推奨されるアプローチである）、要素が自動展開時に自動的に追加されます。

[OSGi Webアプリケーションバンドル（WAB）](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) 自分で構成している場合、Spring MVCプロジェクトの `web.xml` ファイルを展開する準備が完全に整っている必要があります。 Spring MVC構成に加えて、 `web.xml` は次の要素を含める必要があります。

  - `リスナー` for [`PluginContextListener`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/PluginContextListener.html)

  - `サーブレット` および `servlet-mapping` for [`PortletServlet`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/PortletServlet.html)

要素は次のようになります。

    <listener>
        <listener-class>com.liferay.portal.kernel.servlet.PluginContextListener</listener-class>
    </listener>
    <servlet>
        <servlet-name>Portlet Servlet</servlet-name>
        <servlet-class>com.liferay.portal.kernel.servlet.PortletServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>Portlet Servlet</servlet-name>
        <url-pattern>/portlet-servlet/*</url-pattern>
    </servlet-mapping>

アプリケーションは、 `javax.portlet.PortletRequest`sを `javax.servlet.ServletRequest`sに変換し、再び変換できる必要があります。 これを `web.xml`追加します。

    <servlet>
        <servlet-name>ViewRendererServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.ViewRendererServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>ViewRendererServlet</servlet-name>
        <url-pattern>/WEB-INF/servlet/view</url-pattern>
    </servlet-mapping>

`web.xml`必要な設定はこれだけです。 これで、ビューを構成する準備が整いました。

## 視聴回数

Springビューリゾルバーを構成するには、アプリケーションコンテキストファイルにBeanを追加します（前の例では`portlet-context.xml`）。

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

フロントコントローラー `org.springframework.web.portlet.DispatcherPortlet`がビューレイヤーからリクエストを取得できるようになったので、今度はコントローラークラスを設定してリクエストを処理します。

## コントローラー

Spring MVCを使用すると、コントローラーは便利にポートレットモード（表示、編集、ヘルプ）を処理するクラスに分離されます。

Springの注釈を使用してコントローラーを構成し、 `DispatcherPortlet` にコントローラーがサポートするモードを伝えます。

### ビューモードコントローラー

表示モードをサポートする単純なコントローラークラスは次のようになります。

    @Controller("myAppController")
    @RequestMapping("VIEW")
    public class MyAppController {
    
        @RenderMapping
        public String processRenderRequest(RenderRequest request,
                RenderResponse response) {
    
            return "defaultView";
        }
    }

`return defaultView` ステートメントは、アプリケーションコンテキストファイルのビューリゾルバーBeanの観点から理解する必要があります。これは、ストリング `defaultView` に `WEB-INF / views /`プレフィックスと `.jsp`サフィックスを与えます。 これはパス `WEB-INF / views / defaultView.jsp`にマッピングされ、アプリケーションのデフォルトビューを配置します。

Spring MVCでは、各コントローラーで1つのポートレットフェーズのみをサポートできます。

### 編集モードコントローラー

編集モードコントローラには、レンダリングメソッドとアクションメソッドが含まれる場合があります。

    @Controller("myAppEditController")
    @RequestMapping("EDIT")
    public class MyAppEditController {
    
        @RenderMapping
        public String processRenderRequest(RenderRequest request,
                RenderResponse response) {
    
            return "thisView";
        }
    
        @ActionMapping(params="action=doSomething")
        public void doSomething(Actionrequest request, ActionResponse response){
    
            // Do something here
    
        }
    }

それぞれに `bean` 要素を追加して、アプリケーションコンテキストファイルにコントローラクラスを必ず定義してください。

    <bean class="com.liferay.docs.springmvc.portlet.MyAppController" />
    <bean class="com.liferay.docs.springmvc.portlet.MyAppEditController" />

Spring MVCポートレットで通常行うように、コントローラーとビューを開発します。 Liferayに必要な記述子もいくつか提供する必要があります。

## Liferay記述子

WARファイルとしてパッケージ化されたLiferayポートレットプラグインには、Liferay固有の記述子を含める必要があります。

記述子 `liferay-display.xml` は、@product@の *追加* メニューでポートレットが表示されるカテゴリを制御します。 ここで完全なDTD [を見つけます](@platform-ref@/7.1-latest/definitions/liferay-display_7_1_0.dtd.html)。

Liferayのアプリケーションを追加するメニューでアプリケーションの新しいカテゴリを指定する簡単な例を次に示します。

    <display>
        <category name="New Category">
            <portlet id="example-portlet" />
        </category>
    </display>

記述子 `liferay-portlet.xml` は、ポートレットに関する追加情報（CSSおよびJavaScriptファイルの場所、またはポートレットのアイコンなど）を指定します。 設定できる属性の完全なリストは、ここで [ます](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html)

    <liferay-portlet-app>
        <portlet>
            <portlet-name>example-portlet</portlet-name>
            <instanceable>true</instanceable>
            <render-weight>0</render-weight>
            <ajaxable>true</ajaxable>
            <header-portlet-css>/css/main.css</header-portlet-css>
            <footer-portlet-javascript>/js/main.js</footer-portlet-javascript>
            <footer-portlet-javascript>/js/jquery.foundation.orbit.js</footer-portlet-javascript>
        </portlet>
        <role-mapper>
            <role-name>administrator</role-name>
            <role-link>Administrator</role-link>
        </role-mapper>
        <role-mapper>
            <role-name>guest</role-name>
            <role-link>Guest</role-link>
        </role-mapper>
        <role-mapper>
            <role-name>power-user</role-name>
            <role-link>Power User</role-link>
        </role-mapper>
        <role-mapper>
            <role-name>user</role-name>
            <role-link>User</role-link>
        </role-mapper>
    </liferay-portlet-app>

| **重要：** 方法を考慮して、ポートレット名を一意にします。 [@product@は、名前を使用してポートレットのID](/docs/7-1/reference/-/knowledge_base/r/portlet-descriptor-to-osgi-service-property-map#ten)を作成します。

また、上記の `ロールマッパー` 要素が含まれていることに気付くでしょう。 ポートレットで使用されるLiferayロールを定義します。

`liferay-plugin-package.properties` ファイルは、Liferayプラグインを記述し、そのリソースを宣言し、セキュリティ関連のパラメーターを指定します。 DTDはここでは [です](@platform-ref@/7.1-latest/definitions/liferay-plugin-package_7_1_0.dtd.html)。

    name=example-portlet
    module-group-id=liferay
    module-incremental-version=1
    tags=
    short-description=
    change-log=
    page-url=http://www.liferay.com
    author=Liferay, Inc.
    licenses=LGPL
    version=1

`liferay-plugin-package.properties` ファイルでは、WARファイルをデプロイするときに [Liferay WAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) が `MANIFEST.MF` ファイルに追加するOSGiメタデータを追加することもできます。

LiferayのDTDはすべてここでは [です](@platform-ref@/7.1-latest/definitions/)。

## Spring MVCからのサービスの呼び出し

Spring MVCポートレットからOSGiベースのService Builderサービスを呼び出すには、OSGiサービスレジストリにアクセスできるメカニズムが必要です。

Spring MVCポートレットのコンテキストにいるため、宣言サービスを使用してOSGiランタイムに公開されたサービス（Service Builderサービスを含む）への参照を検索することはできません。 [Service Trackers](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)を使用する必要があります。 いくつかの定型的なコードが関係していますが、OSGiランタイムでサービスを検索する機能は価値があります。

次に、Spring MVCポートレットをパッケージ化してデプロイする方法を検討します。
