---
header-id: creating-a-metric
---

# メトリックの作成

[TOC levels=1-4]

すべての基準が定義されたので、実際のメトリックの開発を開始できます。

| **注：** れているコンテンツターゲットクラスのJavadocを表示するにはこの記事、ダウンロード| [Javadoc JAR](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)。

1.  [メトリックをデプロイするためのモジュール](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module) プロジェクトを作成します。 Blade CLI [content-targeting-tracking-action](/docs/7-1/reference/-/knowledge_base/r/content-targeting-tracking-action-template) テンプレートを使用すると、すぐに始めることができます。 これはデフォルトの構成を設定し、定型コードが含まれているため、ファイル作成手順をスキップしてすぐに開始できます。

2.  モジュールがオーディエンスターゲティング指標に必要な依存関係を指定していることを確認してください。 たとえば、コンテンツターゲットAPIと必要なLiferayパッケージを指定する必要があります。 たとえば、次の例は、Gradleベースのメトリックから使用される `build.gradle` ファイルです。

    ``` groovy
    dependencies {
        compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.analytics.api", version: "5.0.0"
        compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.anonymous.users.api", version: "3.0.0"
        compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.api", version: "5.0.0"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.6.2"
        compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib", version: "2.0.0"
        compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }
    ```

    コンテンツターゲットAPIの公開の詳細については、 [コンテンツターゲットAPIへのアクセス](/docs/7-1/tutorials/-/knowledge_base/t/accessing-the-content-targeting-api) チュートリアルをご覧ください。 モジュールを作成してその依存関係を指定したら、メトリックの動作を定義する必要があります。 メトリックの動作は、作成したJavaクラスファイルによって制御されます。

3.  モジュールの `src` ディレクトリに一意のパッケージ名を作成し、そのパッケージに新しいJavaクラスを作成します。 命名規則に従うには、クラス名は作成するメトリックの名前で始まり、 *TrackingAction* 終わる必要があります（例： `NewsletterTrackingAction.java`）。 あなたのJavaクラスが実装する必要があります `com.liferay.content.targeting.api.model.TrackingAction` インターフェイスを。

    `TrackingAction` インターフェースを実装する必要がありますが、拡張可能な便利なユーティリティを提供する `TrackingAction` 拡張クラスがあります。 たとえば、メトリックは `BaseJSPTrackingAction` クラスを拡張して、JSPを使用したメトリックのUIの生成をサポートできます。 このチュートリアルでは、JSPを使用してUIを実装する方法を説明し、 `BaseJSPTrackingAction` クラスを拡張して `TrackingAction` インターフェースを実装することを前提としています。 メトリックのUIの選択の詳細については、「 [UIテクノロジの選択](/develop/tutorial/-/knowledge_base/7-1/best-practices-for-audience-targeting#selecting-a-ui-technology) セクションを参照してください。

4.  クラスの宣言のすぐ上に、次の注釈を挿入します。

    ``` java
    @Component(immediate = true, service = TrackingAction.class)
    ```

    これにより、コンポーネントの実装クラスが宣言され、@ product @にデプロイされるとすぐに開始するように構成されます。

Javaクラスが設定されたので、 `TrackingAction` インターフェースのメソッドを実装して、メトリックがどのように機能するかを定義する必要があります。 次に、これらのメソッドの実装を開始します。
