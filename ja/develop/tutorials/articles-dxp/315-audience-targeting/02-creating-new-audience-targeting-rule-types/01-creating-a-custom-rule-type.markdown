---
header-id: creating-a-custom-rule-type
---

# カスタムルールタイプの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

まず、モジュールを作成し、必要なコンテンツターゲティングAPIの依存関係があることを確認する必要があります。

| **注：** れているコンテンツターゲットクラスのJavadocを表示するにはこの記事、ダウンロード| [Javadoc JAR](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)。

1.  [ルールをデプロイするためのモジュール](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module) プロジェクトを作成します。 Blade CLI [content-targeting-rule](/docs/7-1/reference/-/knowledge_base/r/content-targeting-rule-template) テンプレートを使用すると、すぐに始めることができます。 これはデフォルトの構成を設定し、定型コードが含まれているため、ファイル作成手順をスキップしてすぐに開始できます。 これを使用するには、次のブレードコマンドを使用します。

    ``` bash
    blade create -t content-targeting-rule weather-rule
    ```

2.  テンプレートが最新リリースと同期しなくなることがあるので、依存関係が最新であることを確認してください。 Gradleベースのルールで表示される依存バージョンは次のとおりです。

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

    コンテンツターゲットAPIの公開の詳細については、 [コンテンツターゲットAPIへのアクセス](/docs/7-1/tutorials/-/knowledge_base/t/accessing-the-content-targeting-api) チュートリアルをご覧ください。 モジュールを作成し、その依存関係を指定したら、ルールの動作を定義する必要があります。 ルールの動作は、作成したJavaクラスファイルによって制御されます。

3.  モジュールの `src` ディレクトリに、生成されたクラスが表示されます。 命名規則に従うには、クラス名は作成するルール名で始まり、 *ルール* 終わる必要があります（例： `WeatherRule.java`）。 Javaクラスは [`com.liferay.content.targeting.api.model.Rule`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc) インターフェースを実装する必要があります。

    `Rule` インターフェースを実装する必要がありますが、拡張可能な便利なユーティリティを提供する `Rule` 拡張クラスがあります。 たとえば、ルールは [`com.liferay.content.targeting.api.model.BaseJSPRule`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc) クラスを拡張して、JSPを使用したルールのUIの生成をサポートできます。 このチュートリアルでは、JSPを使用してUIを実装する方法を示し、 `BaseJSPRule` クラスを拡張して `Rule` インターフェースを実装することを想定しています。 ルールのUIの選択の詳細については、 [UIテクノロジーの選択](/develop/tutorial/-/knowledge_base/7-1/best-practices-for-audience-targeting#selecting-a-ui-technology)参照してください。

4.  クラスの宣言の真上には、次の注釈があります。

    ``` java
    @Component(immediate = true, service = Rule.class)
    ```

    このアノテーションは、コンポーネントの実装クラスを宣言し、@product@にデプロイされたらすぐにモジュールを開始することを指定します。

Javaクラスが設定されたので、 `Rule` インターフェースのメソッドを実装して、ルールの動作を定義する必要があります。 次に、これらのメソッドの実装を開始します。

| **注：** 分類するソーシャルルールタイプの開発を計画している場合|ユーザーのソーシャルネットワークプロファイルに基づいて、特定のソーシャル|ネットワークのSSO（シングルサインオン）を有効にし、適切に構成する必要があります。 訪問| [ソーシャルルール](/docs/7-1/user/-/knowledge_base/u/liferay-audience-targeting-rules#social-rules) |詳細はセクションをご覧ください。

次に、気象ルールのビュー/保存ライフサイクルを定義します。
