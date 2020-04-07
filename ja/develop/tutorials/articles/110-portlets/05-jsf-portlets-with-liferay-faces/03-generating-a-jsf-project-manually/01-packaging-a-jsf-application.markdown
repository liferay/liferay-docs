---
header-id: packaging-a-jsf-application
---

# JSFアプリケーションのパッケージ化

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
