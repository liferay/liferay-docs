---
header-id: packaging-a-jsf-application
---

# JSFアプリケーションのパッケージ化

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product-ver@のポートレットを作成する開発者は、ポートレットをJava EEスタイルのWebアプリケーションARchive（WAR）アーティファクトまたはJava ARchive（JAR）OSGiバンドルアーティファクトとしてパッケージ化できます。 ただし、JSFフレームワークはWARレイアウトを想定し、多くの場合 `WEB-INF / faces-config.xml` 記述子と `WEB-INF /などの他のJava EEリソースを必要とするため、JSFポートレット開発者はポートレットをWARアーティファクトとしてパッケージ化する必要があります。 web.xml` 記述子。

Liferayは、LiferayのOSGiランタイムによって、これらのWARスタイルのポートレットをOSGiモジュールのようにデプロイおよび処理する方法を提供します。 WAB Generatorは、展開時にWARアーティファクトをWABに変換することにより、これを自動的に行います。 [WAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) 使用チュートリアルで、WABとWAB Generatorの詳細を確認できます。

JSF WARアーティファクトの構造は次のとおりです。

  - `jsf-portlet`
      - `src`
          - `main`
              - `java`
                  - Javaクラス
              - `resources`
                  - プロパティファイル
              - `webapp`
                  - `WEB-INF/`
                      - `classes/`
                          - クラスファイルと関連プロパティ
                      - `lib/`
                          - JAR依存関係
                      - `resources/`
                          - CSS、XHTML、PNGまたはその他のフロントエンドファイル
                      - `views/`
                          - XHTMLビュー
                      - `faces-config.xml`
                      - `liferay-display.xml`
                      - `liferay-plugin-package.properties`
                      - `liferay-portlet.xml`
                      - `portlet.xml`
                      - `web.xml`

次に、@product@にデプロイ可能な単純なJSFアプリケーションの作成を開始します。
