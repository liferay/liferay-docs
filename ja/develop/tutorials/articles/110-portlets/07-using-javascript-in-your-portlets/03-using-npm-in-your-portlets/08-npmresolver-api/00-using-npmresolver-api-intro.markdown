---
header-id: using-the-npmresolver-api-in-your-portlets
---

# ポートレットでNPMResolver APIを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

npmベースのポートレットを開発している場合、OSGiバンドルの `package.json` は情報の宝庫です。 これには、バンドルに関するnpmレジストリに保存されているすべてのもの（デフォルトのエントリポイント、依存関係、モジュール、パッケージ名、バージョンなど）が含まれています。 [`NPMResolver` API](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html) この情報を公開するため、ポートレットでアクセスできます。 OSGiバンドルの `package.json`で定義されている場合、 `NPMResolver` APIを使用してポートレットの情報を取得できます。 たとえば、あなたはこのAPIを使用することができます [NPMパッケージの静的リソース参照](/docs/7-1/tutorials/-/knowledge_base/t/obtaining-dependency-npm-package-descriptors) （等CSSファイルなど）とさえへの [あなたのコードがより保守します](/docs/7-1/tutorials/-/knowledge_base/t/referencing-an-npm-modules-package)。

ポートレットで `NPMResolver` を有効にするには、 `@Reference` アノテーションを使用して、以下に示すように、 `NPMResolver` OSGiコンポーネントをポートレットのコンポーネントクラスに注入します。

    import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
    
    public class MyPortlet extends MVCPortlet {
    
      @Reference
      private NPMResolver `_npmResolver`;
    
    }

| **注：** `NPMResolver` 参照はOSGiに直接結び付けられているため|バンドルの `package.json` ファイルは、npmモジュールを取得するためにのみ使用できます。そのファイルからのパッケージ情報。 `NPMResolver` を使用して取得することはできません。他のOSGiバンドルのnpmパッケージ情報。

`NPMResolver` がポートレットに追加されたので、このセクションのチュートリアルでは、OSGiバンドルのnpmパッケージとモジュール情報の取得について説明します。
