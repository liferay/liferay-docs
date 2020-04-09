---
header-id: using-developer-mode-with-themes
---

# テーマで開発者モードを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

変更を確認するために再デプロイすることなくテーマを開発しますか？ 開発者モードを使用してください！ 開発者モードでは、すべてのキャッシュが削除されるため、加えた変更はすぐに表示されます。 また、開発者モードで頻繁にサーバーを再起動する必要はありません。

開発者モードを使用すると、変更をより迅速に確認できますか？ デフォルトでは、@product@はパフォーマンスが最適化されています。 開発者モードは、代わりに開発用に構成を最適化します。 開発者モードの主要な動作の変更と、それらをトリガーする [ポータルプロパティ](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html) オーバーライド設定のリストを以下に示します（該当する場合）。

  - CSSファイルは、単一のCSSファイル（`theme.css.fast.load = false`）として結合およびロードされるのではなく、個別にロードされます。
  - レイアウトテンプレートのキャッシュが無効になっています（`layout.template.cache.enabled = false`）。
  - サーバーは起動時にブラウザーを起動しません（`browser.launcher.url =`）。
  - テーマおよびWebコンテンツのFreeMarkerテンプレートはキャッシュされないため、変更はすぐに適用されます（@product@インスタンスのシステム設定を使用）。
  - CSSおよびJavaScriptリソースの縮小化は無効になっています（`minifier.enabled = false`）。

スタイリングと動作の個々のファイルの読み込みと、レイアウトおよびFreeMarkerテンプレートの無効化されたキャッシュとの組み合わせにより、変更をより迅速に確認できます。 これらの開発者設定は、 [`portal-developer.properties` ファイル](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-impl/src/portal-developer.properties)定義されています。 これらの設定を使用するには、 `portal-ext.properties` ファイルに設定を含めるか、 `portal-ext.properties` ファイルにコピーして、必要に応じて特定のプロパティをオーバーライドできます。 このチュートリアルでは、これらの構成について説明します。

まず、@ide@でそれがどのように行われるかを調べることができます。

## Dev Studioでサーバーの開発者モードを設定する

@ide@でサーバーの開発者モードを有効にするには、次の手順を実行します。

1.  *サーバー* ウィンドウでサーバーをダブルクリックし、 *Liferay Launch* セクションを開きます。

2.  *カスタム起動設定* を選択し、 *[開発者モード* 使用]オプションをチェックします。

3.  変更を保存し、サーバーを起動します。

![図1：[開発者モードを使用]オプションを使用すると、@ide@でサーバーの開発者モードを有効にできます。](../../../../images/developer-mode-ide.png)

| **警告：** ランタイム環境のサーバー設定のみを変更します。 Liferay起動セクション。

サーバーを初めて起動すると、サーバーのディレクトリに `portal-ext.properties` ファイルが作成されます。 このプロパティファイルには、開発者モードを有効にするプロパティ設定 `include-and-override = portal-developer.properties`が含まれています。 ほとんどの構成は `portal-developer.properties` ファイルによって提供されますが、FreeMarkerテンプレート設定を構成する必要があります。 [FreeMarkerシステム設定の構成](/docs/7-1/tutorials/-/knowledge_base/t/using-developer-mode-with-themes#configuring-freemarker-system-settings) セクションの手順に従って、FreeMarkerテンプレートキャッシュを構成します。

@ide@を使用していない場合は、開発者モードの手動設定について次に説明します。

## portal-developer.propertiesを使用してサーバーの開発者モードを設定する

開発者モードを手動で設定するには、最後のセクションに示すように、 `portal-developer.properties` をポイントする必要があります。 `portal-ext.properties` ファイルをアプリサーバーのバンドルのルートフォルダーに追加し、次の行を追加します。

    include-and-override=portal-developer.properties

開発者モードは、アプリサーバーの起動時に有効になります。 `portal-developer.properties` は、スムーズな開発に必要な設定の大部分を提供します。 FreeMarkerテンプレートのキャッシュを無効にするには、次のセクションで説明するシステム設定を更新する必要があります。

## FreeMarkerシステム設定の構成

テーマおよびWebコンテンツ用のFreeMarkerテンプレートは、デフォルトでキャッシュされます。 したがって、FreeMarkerテーマテンプレートに加えた変更はすぐには表示されません。 この動作は、システム設定から変更できます。 次の手順を実行します：

1.  コントロールパネルを開き、 *構成* → *システム設定*進みます。

2.  *PLATFORM* 見出しの下で *Template Engines* 選択し* 。</p></li>

3

デフォルトでは、 *リソース変更チェック* （テンプレートがキャッシュされるミリ秒単位の時間）は `60000`設定されています。 キャッシュを無効にするには、この値を `0` に設定します。</ol>

FreeMarkerテンプレートは開発の準備ができています。 次に、開発用のJavaScriptファイルの読み込みを改善する方法を学びます。

## JavaScript高速読み込み

デフォルトでは、JavaScript高速読み込みは開発者モード（`javascript.fast.load = true`）で有効になっています。 これにより、 [ポータルプロパティ](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#JavaScript) `javascript.barebone.files` または `javascript.everything.files`リストされているファイルのパックバージョンがロードされます。 ただし、JavaScriptの高速読み込みを無効にして、開発のデバッグを容易にすることができます。 `portal.properties`で `javascript.fast.load` から `false` を設定するか、URLパラメーター `js_fast_load` から `0`を設定して高速ロードを無効にすることができます。

| **注：** JavaScript高速読み込みは、次の3つの場所のいずれかから取得されます。リクエスト（現在のURLで決定： `http：// localhost：8080 / web / guest / home？js_fast_load = 1`（オン）または| `...？js_fast_load = 0`（オフ））、セッション、またはポータルプロパティ| （`javascript.fast.load = true`）。 優先度はリクエストの順番で与えられます|セッション、次にポータルプロパティ。 これにより、 `js_fast_load`の値を変更できます| `portal.properties` デフォルトから、手動で再入力する必要なし| `新しいページがロードされるたびに、js_fast_load` がURLに追加されます。

すばらしいです\！ サーバーを開発者モードに設定しました。 これで、バンドル内でテーマのファイルを直接変更すると、テーマの再デプロイ時に変更がすぐに適用されたことがわかります！

## 関連トピック

[レイアウトテンプレートを手動で作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-manually)

[@ide@を使用したテーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes-with-liferay-ide)
