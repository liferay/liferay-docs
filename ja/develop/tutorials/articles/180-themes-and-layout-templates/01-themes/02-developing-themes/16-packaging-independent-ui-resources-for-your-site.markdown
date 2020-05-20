---
header-id: packaging-independent-ui-resources-for-your-site
---

# サイトの独立したUIリソースのパッケージ化

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

特定のテーマに依存しないUIリソースをパッケージ化し、@product@ページに含める場合は、テーマコントリビューターが最適なオプションです。 代わりに、テーマに関連付けられている@product@ページに個別のUIリソースを含める場合は、 [テーマレット](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)調べる必要があります。

テーマコントリビューターは、@ \<product@\>使用するUIリソースを含む [モジュール](/docs/7-1/tutorials/-/knowledge_base/t/fundamentals#modules) です。 テーマコントリビューターが@product@にデプロイされると、すべての有効なCSSおよびJSファイルがスキャンされ、そのリソースがページに含まれます。 したがって、これらのUIコンポーネントを好きなようにスタイル設定でき、スタイルは現在のテーマに適用されます。

このチュートリアルでは、

  - テーマ投稿者モジュールを特定します。
  - テーマ投稿者モジュールを作成します。

次に、テーマコントリビューターを作成する方法を学びます。

## テーマ投稿者の作成

ユーザーメニューとナビゲーションの標準UIは、テーマコントリビューターとしてパッケージ化されています。 たとえば、コントロールメニュー、製品メニュー、およびシミュレーションパネルは、@product@のテーマコントリビューターモジュールとしてパッケージ化され、テーマから分離されています。 つまり、これらのUIコンポーネントはテーマの外部で処理する必要があります。

![図1：コントロールメニュー、製品メニュー、およびシミュレーションパネルは、テーマコントリビューターモジュールとしてパッケージ化されています。](../../../../images/theme-contributor-menus-diagram.png)

これらの標準UIコンポーネントを編集またはスタイルする場合は、独自のテーマコントリビューターを作成し、その上に変更を追加する必要があります。 テーマコントリビューターを作成して、@product@に新しいUIコンポーネントを追加することもできます。

テーマ投稿者モジュールを作成するには、次の手順を実行します。

1.  お気に入りのサードパーティツールを使用して汎用OSGiモジュールを作成するか、 [Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)使用します。 [ブレードテンプレート](/docs/7-1/reference/-/knowledge_base/r/theme-contributor-template) を使用してモジュールを作成することもできます。この場合、手順2は省略できます。

2.  モジュールをテーマコントリビューターとして識別するには、モジュールの `bnd.bnd` ファイルに、 `Liferay-Theme-Contributor-Type` および `Web-ContextPath` ヘッダーを追加します。 たとえば、 [コントロールメニューモジュールの](https://search.maven.org/search?q=a:com.liferay.product.navigation.control.menu.theme.contributor) `bnd.bnd`参照してください。
   
        Bundle-Name: Liferay Product Navigation Product Menu Theme Contributor
        Bundle-SymbolicName: com.liferay.product.navigation.product.menu.theme.contributor
        Bundle-Version: 3.0.4
        Liferay-Theme-Contributor-Type: product-navigation-product-menu
        Web-ContextPath: /product-navigation-product-menu-theme-contributor

    テーマ投稿者タイプは、@product@がモジュールをよりよく特定するのに役立ちます。 たとえば、既存のテーマコントリビューターをオーバーライドするためにテーマコントリビューターを作成している場合、同じタイプを使用して、将来の開発との互換性を最大化する必要があります。 `Web-ContextPath` ヘッダーは、テーマコントリビューターのリソースがホストされるコンテキストを設定します。

3.  多くの場合、別のテーマコントリビューターのCSSをオーバーライドするため、CSSの後にCSSをロードする必要があります。 これを行うには、テーマコントリビューターの重みを設定します。 `bnd.bnd` ファイルに、次のヘッダーを追加します。
   
        Liferay-Theme-Contributor-Weight: [value]

    値が大きいほど、優先度が高くなります。 テーマ投稿者の重みが100の場合、99の重みを持つテーマ投稿者の後に読み込まれ、CSSがそれらを上書きできるようにします。

4.  モジュールに `src / main / resources / META-INF / resources` フォルダーを作成し、そのフォルダーにリソース（CSSおよびJS）を配置します。

5.  モジュールをビルドおよびデプロイして、@product@ページおよびテーマに適用された変更を確認します。

サイトのテーマコントリビューターを作成するために必要なことはこれだけです。 大きな力には大きな責任が伴うことを忘れないでください。テーマコントリビューターを賢く使用してください。 UIの貢献はすべてのページに影響し、テーマの展開の影響を受けません。

## 関連トピック

[テーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)

[テーマ](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)

[テーマを使用したリソースのインポート](/docs/7-1/tutorials/-/knowledge_base/t/importing-resources-with-a-theme)

[テーマ投稿者テンプレート](/docs/7-1/reference/-/knowledge_base/r/theme-contributor-template)
