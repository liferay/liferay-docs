---
header-id: upgrading-portlet-language-key-hooks
---

# ポートレット言語キーフックのアップグレード

[TOC levels=1-4]

次の手順に従って、ポートレット言語キーフックを@product-ver@にアップグレードできます。

1.  Bladeサンプル`resource-bundle`（[Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/extensions/resource-bundle)および[Maven](https://github.com/liferay/liferay-blade-samples/tree/7.1/maven/extensions/resource-bundle)プロジェクト）に基づいて、新しいモジュールを作成します。

    モジュールフォルダ構造のメインファイルは次のとおりです。

      - `src/main/java/[resource bundle path]` → [`ResourceBundleLoader`拡張](@platform-ref@/7.1-latest/javadocs/portal-kernel/)はここに入ります。
      - `src/main/resources/content`
          - `Language.properties`
          - `Language_xx.properties`
          - ...

2.  言語プロパティファイルをモジュールフォルダ`src/main/resources/content/`の中へコピーします。

3.  `bnd.bnd`ファイルで、ポートレットモジュールのリソースバンドルをターゲットにしながら、自分のものを優先させる[OSGiマニフェストヘッダーを指定](/docs/7-1/tutorials/-/knowledge_base/t/overriding-a-modules-language-keys)します。

4.  [モジュールをデプロイします](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)。

ポートレット言語キーのカスタマイゼーションは、@product-ver@の新しいモジュールにデプロイされます。

## 関連トピック

[モジュールの言語キーのオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-a-modules-language-keys)

[コア言語キーフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-language-key-hooks)

[プラグインの依存関係の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Liferay Mavenビルドのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
