---
header-id: using-the-wab-generator
---

# WABジェネレーターの使用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product@のアプリケーションは、Java EEスタイルのWebアプリケーションARchive（WAR）アーティファクトまたはJava ARchive（JAR）OSGiバンドルアーティファクトとして作成できます。 ただし、一部のポートレットタイプでは柔軟性が制限されます。 Spring MVCやJSFなどのポートレットは、フレームワークがJava EE用に設計されているため、WARアーティファクトとしてパッケージ化する必要があります。 したがって、WARレイアウトを想定しており、 `WEB-INF / web.xml` 記述子などのJava EEリソースが必要です。

Liferayは、LiferayのOSGiランタイムによって、これらのWARスタイルのプラグインをOSGiモジュールのように展開および処理する方法を提供します。 *WAB*変換できます。

@product@は、Java EEスタイルのWARの展開のためのOSGi Web Application Bundle（WAB）標準をサポートしています。 簡単に言えば、WABはWARレイアウトを持ち、 `Bundle-SymbolicName` OSGiディレクティブを持つ `META-INF / MANIFEST.MF` ファイルを含むアーカイブです。 WABはOSGiバンドルです。 プロジェクトソースにはWARレイアウトがありますが、アーティファクトファイル名は `.jar` または `.war` 拡張子で終わる場合があります。

Liferayは、WAB Generatorによって自動生成されたWABの使用のみをサポートします。 WAB Generatorは、デプロイ中に従来のWARスタイルのプラグインをWABに変換します。 では、WABジェネレーターは、WARファイルをWABに変換するために正確に何をするのでしょうか？

WAB Generatorは、プラグインWARのJSP、記述子ファイル、およびクラス（ `WEB-INF / classes` および埋め込みJAR）で参照されているパッケージを検出します。 記述子ファイルは、 `のweb.xml`、 `のLiferay-web.xmlの`、 `のportlet.xml`、 `のLiferay-のportlet.xml`、および `のLiferay-hook.xml`。 WABジェネレーターは、検出されたパッケージがプラグインの `WEB-INF / classes` フォルダーにあるか、 `WEB-INF / lib` フォルダーに埋め込まれたJARにあるかを確認します。 どちらの場所にも見つからないパッケージは、WABの `META-INF / MANIFEST.MF` ファイルの `Import-Package` ヘッダーに追加されます。

次のタイプの場所でのみ参照されるパッケージをインポートするには、 `Import-Package` OSGiヘッダーをプラグインの `WEB-INF / liferay-plugin-package.properties` ファイルに追加し、そのヘッダーにパッケージを追加する必要があります値のリスト。

  - 認識されない記述子ファイル
  - カスタムまたは認識されない記述子要素または属性
  - 反射コード
  - クラスローダーコード

WABフォルダー構造とWARフォルダー構造は異なります。 WARスタイルのポートレットの次のフォルダ構造を考慮してください。

  - 私の戦争ポートレット
      - src
          - メイン
              - java
              - webapp
                  - WEB-INF
                      - classes
                      - lib
                      - リソース
                      - 景色
                      - faces-config.xml
                      - liferay-display.xml
                      - liferay-plugin-package.properties
                      - liferay-portlet.xml
                      - portlet.xml
                      - web.xml

WARスタイルのポートレットがLiferayにデプロイされ、WABジェネレーターによって処理されると、ポートレットのフォルダー構造は次のように変換されます

  - 私の戦争ポートレット、それは今、ワブ
      - META-INF
          - MANIFEST.MF
      - WEB-INF
          - classes
          - lib
          - リソース
          - 景色
          - faces-config.xml
          - liferay-display.xml
          - liferay-plugin-package.properties
          - liferay-portlet.xml
          - portlet.xml
          - web.xml

主な違いは、 `META-INF / MANIFEST.MF` ファイルの追加です。 WAB Generatorは、OSGi対応 `MANIFEST.MF` ファイルを自動的に生成します。 マニフェストファイルのコンテンツに影響を与えたい場合は、bndディレクティブとOSGiヘッダーをプラグインの `liferay-plugin-package.properties` ファイルに直接配置できます。 `bnd.bnd` ファイルまたはビルド時プラグイン（ `bnd-maven-plugin`）をWARプラグインに追加しても意味がありません。生成されたWABはそれらを使用できないためです。

WABを生成してみますか？ 以下の手順に従って、WAB Generatorの動作を確認してください。

1.  上記で概説したものと同様の構造に従うWARスタイルのプラグインを作成します。 デモンストレーション用に、サンプルのWARスタイルポートレット [ここ](https://portal.liferay.dev/documents/113763090/114000186/com.liferay.hello.user.jsf.portlet-1.0-SNAPSHOT.war)からダウンロードできます。

2.  ファイルエクスプローラーで@product@インスタンスを開き、次のプロパティを含む `portal-ext.properties` ファイルを追加します。
   
        module.framework.web.generator.generated.wabs.store=true
        module.framework.web.generator.generated.wabs.store.dir=${module.framework.base.dir}/wabs

    これらのプロパティは、生成されたWABをLiferayインスタンスの `osgi / wabs` フォルダーに保存します。 これらのプロパティの詳細については、 [モジュールフレームワークWebアプリケーションバンドル](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework%20Web%20Application%20Bundles) プロパティセクションをご覧ください。 Liferayを再起動して、これらのプロパティを使用します。

3.  Liferayインスタンスの `deploy` フォルダーにWARプラグインをコピーします。

4.  Liferayインスタンスの `osgi / wabs` フォルダーに移動し、生成されたWABを調べます。

驚くばかり\！ WAB Generatorが動作しているのを見ました！

## 関連トピック


<!-- TODO: add back, when available. -Cody
[Generating a JSF Application](develop/tutorials/-/knowledge_base/7-1/generating-a-jsf-application)
-->


<!-- Uncomment in Product Nav Branch
[Customizing the Product Menu](develop/tutorials/-/knowledge_base/7-1/customizing-the-product-menu)
-->

[構成可能なアプリケーション](/docs/7-1/tutorials/-/knowledge_base/t/configurable-applications)
