---
header-id: upgrading-core-language-key-hooks
---

# コア言語キーフックのアップグレード

[TOC levels=1-4]

コア言語のキーフックを@product-ver@にアップグレードする手順は次のとおりです。

1.  [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/extensions/resource-bundle)および[Maven](https://github.com/liferay/liferay-blade-samples/tree/7.1/maven/extensions/resource-bundle)にあるBladeサンプル`resource-bundle`に基づいて、新しいモジュールを作成してください。

    モジュールフォルダ構造の主要部分は次のとおりです。

      - `src/main/java/[resource bundle path]` → カスタムリソースバンドルクラスはここに入ります。
      - `src/main/resources/content`
          - `Language.properties`
          - `Language_xx.properties`
          - ...

2.  すべてのプラグインの言語プロパティファイルをモジュールフォルダ`src/main/resources/content/`の中へコピーします。

3.  [リソースバンドルローダーを作成します](/docs/7-1/tutorials/-/knowledge_base/t/overriding-global-language-keys#create-a-resource-bundle-service-component)。

4.  [モジュールをデプロイします](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)。

コア言語キーのカスタマイゼーションは、@product-ver@にデプロイされます。

## 関連トピック

[グローバル言語キーのオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-global-language-keys)

[ポートレット言語キーフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portlet-language-key-hooks)

[プラグインの依存関係の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Liferay Mavenビルドのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
