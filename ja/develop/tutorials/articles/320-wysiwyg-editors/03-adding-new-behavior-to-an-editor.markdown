---
header-id: adding-new-behavior-to-an-editor
---

# エディターに新しい動作を追加する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ユーザー用にいくつかの異なるWYSIWYGエディターから選択できます。各エディターは構成可能で、長所と短所があります。 ただし、構成だけでは、必要な機能が常に公開されるとは限りません。 これらの場合、 `liferay-util：dynamic-include` JavaScript拡張ポイントを使用して、エディターインスタンスにプログラムでアクセスし、必要なエディターエクスペリエンスを作成できます。 エディターを構成/変更するために、エディターのインスタンス化の直後にJavaScriptコードを挿入します。

| **注：** デフォルトでは、CKEditorは空の `<i>` タグ（使用されているタグなど）を除去します。コードを切り替える際の、公開コンテンツからのFont Awesomeアイコンの使用|エディターのビューとソースビュー。 |を使用して、この動作を無効にできます。 `ckeditor＃additionalResources` または `Alloyeditor＃additionalResources` | [拡張ポイント](/docs/7-1/tutorials/-/knowledge_base/t/wysiwyg-editor-dynamic-includes) |に| [次のコードを追加します](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor) |編集者へ：| | CKEDITOR.dtd。$removeEmpty.i = 0

このチュートリアルでは、このJavaScript拡張ポイントの使用方法を学習します。

## JavaScriptをWYSIWYGエディターに挿入する

`liferay-util：dynamic-include` 拡張ポイントは、構成可能なエディターのJSPファイルにあります。これは、エディターインスタンスにJavaScriptを挿入するためのゲートウェイです。

1.  モジュールにファイルを登録する必要があるため、参照する意味のあるフォルダーにエディター機能を含むJSファイルを作成します。 拡張ポイントは、エディターの初期化直後にJavaScriptコードを挿入します。

    Liferayは、一部のアプリケーションにJavaScriptコードを挿入します。

      - [creole \ _dialog \ _definition.js wikiの場合](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-editor/frontend-editor-ckeditor-web/src/main/resources/META-INF/resources/_diffs/extension/creole_dialog_definition.js)
      - [creole \ _dialog \ _show.js](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-editor/frontend-editor-ckeditor-web/src/main/resources/META-INF/resources/_diffs/extension/creole_dialog_show.js) ウィキ用
      - [dialog \ _definition.js](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-editor/frontend-editor-ckeditor-web/src/main/resources/META-INF/resources/_diffs/extension/dialog_definition.js) さまざまなアプリケーション用

    これらのJSファイルは、選択した言語（HTML、BBCode、Creole）がサポートするものに応じて、ダイアログに表示されるフィールドを再定義します。 たとえば、Creoleはテーブルセルの背景色をサポートしていないため、Creoleモードで実行しているときにユーザーに表示されるオプションからテーブルセルが削除されます。

2.  [新しいJSファイルを登録してエディターインスタンスに挿入できるモジュール](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module) を作成します。

3.  モジュールの `src` ディレクトリに一意のパッケージ名を作成し、そのパッケージに新しいJavaクラスを作成します。 命名規則に従うために、クラス名は変更するエディターで始まり、カスタム属性が続き、 *DynamicInclude* 終わる必要があります（例： `CKEditorCreoleOnEditorCreateDynamicInclude.java`）。 Javaクラスは、 [`DynamicInclude`](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-kernel/src/com/liferay/portal/kernel/servlet/taglib/DynamicInclude.java) インターフェイスを実装する必要があります。

4.  クラスの宣言のすぐ上に、次の注釈を挿入します。

        @Component（immediate = true、service = DynamicInclude.class）

    これにより、コンポーネントの実装クラスが宣言され、ポータルにデプロイされるとモジュールが開始されます。

5.  `DynamicInclude`抽象メソッドをまだオーバーライドしていない場合は、今すぐオーバーライドします。 編集する実装された2つのメソッドがあります： `include（...）` および `register（...）`。

6.  `include（...）` メソッドで、カスタムJSファイルを含むバンドルを取得します。 JSファイルをURLとして取得し、そのコンテンツをエディターに注入します。 `creole_dialog_definition.js` ファイルに対してこれを行うコードは次のとおりです。

        バンドルbundle = _bundleContext.getBundle（）;

        URL entryURL = bundle.getEntry（ "/ META-INF / resources / html / editors / ckeditor / extension" + "/creole_dialog_definition.js"）;

        StreamUtil.transfer（entryURL.openStream（）、response.getOutputStream（））;

    `include（...）` メソッドでは、エディター構成を取得し、ユーザーが選択した構成に基づいて注入するJSファイルを選択することもできます。 たとえば、これは、表のセルに背景色を表示するというクレオールの欠陥を以前に扱った提案されたユースケースに適用可能です。 Liferayのは、これを実現 `（...）を含む` における方法 [`CKEditorCreoleOnEditorCreateDynamicInclude`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-editor/frontend-editor-ckeditor-web/src/main/java/com/liferay/frontend/editor/ckeditor/web/internal/servlet/taglib/CKEditorCreoleOnEditorCreateDynamicInclude.java) クラス。

7.  バンドルを正常に取得できるように、バンドルのコンテキストをインスタンス化してください。 ベストプラクティスとして、アクティベーションメソッドを作成し、プライベートフィールドとして `BundleContext` を設定してこれを行います。 以下に例を示します。

        @Activate protected void activate（BundleContext bundleContext）{ _bundleContext = bundleContext; }

        private BundleContext _bundleContext;

    このメソッドは `@Activate` アノテーションを使用します。これは、サービスコンポーネントが要件を満たしたときに呼び出す必要があることを指定します。 このデフォルトの例では、 `include（...）` メソッドで `_bundleContext` が使用されました。

8.  次に、カスタマイズしているエディターを登録します。 たとえば、JSコードをCKEditorのJSPファイルに挿入する場合、コードは次のようになります。

        dynamicIncludeRegistry.register（ "com.liferay.frontend.editor.ckeditor.web＃ckeditor＃onEditorCreate"）;

    これにより、CKEditorがDynamic Includeレジストリに登録され、作成されたJSコードがエディターに挿入されるように指定されます。

    利用可能なWYSIWYGエディターの特定の実装を使用するように個々のJSPページを構成できるのと同様に、登録プロセスにも同じ実装オプションを使用できます。 詳細については、 `portal.properties` の [Editors](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Editors) セクションをご覧ください。 たとえば、CKEditorのCreole実装を構成するには、次のキーを使用できます。

        「com.liferay.frontend.editor.ckeditor.web＃ckeditor_creole＃onEditorCreate」

それでおしまい\！ これで、作成したJSコードが、指定したエディターインスタンスに挿入されます。 JavaScriptを使用して、@product@がサポートするWYSIWYGエディターに新しい動作を追加できるようになりました！

## 関連トピック

[エディターに新しい動作を追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)

[テーマへのポートレットの埋め込み](/docs/7-1/tutorials/-/knowledge_base/t/embedding-portlets-in-themes)

[ポートレット](/docs/7-1/tutorials/-/knowledge_base/t/portlets)
