---
header-id: embedding-content-in-the-alloy-editor
---

# AlloyEditorにコンテンツを埋め込む

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

人気のあるストリーミングサービスからのビデオであれ、面白いポッドキャストであれ、埋め込まれたコンテンツはWebで一般的です。 トピックを適切にカバーするには、サードパーティのコンテンツを共有する必要がある場合があります。 `EmbedProvider` メカニズムを使用すると、ブログの投稿、Webコンテンツの記事などを作成しながら、AlloyEditorにサードパーティのコンテンツを埋め込むことができます。 デフォルトでは、 `EmbedProvider` メカニズムは、ビデオコンテンツ（Facebook、Twitch、Vimeo、およびYouTube）をAlloyEditorに埋め込むためにのみ構成されています。 このチュートリアルでは、追加のビデオプロバイダーを追加する方法、さらに追加のコンテンツタイプのサポートを追加する方法を示します。

`EmbedProvider` は4つの情報が必要です。

  - ID：コンテンツのID
  - テンプレート：プロバイダーに必要な埋め込みコード
  - URLスキーム：プロバイダーテンプレートでサポートされているURLパターン
  - タイプ（オプション）：プロバイダーのカテゴリ

サポートされているURLをエディターに追加すると、 `EmbedProvider` はURLを埋め込みコードに変換します。

`* EmbedProvider`を作成するには、次の手順に従います。

1.  [埋め込みプロバイダー用のモジュール](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module) を作成します。

2.  `build.gradle` ファイルに次の依存関係を追加します。
   
        compileOnly group: "com.liferay", name:
        "com.liferay.frontend.editor.api", version: "1.0.1"
       
        compileOnly group: "com.liferay", name: "com.liferay.petra.string",
        version: "2.0.0"

3.  `EditorEmbedProvider` サービスを実装するコンポーネントクラスを作成します。
   
        @Component(
          immediate = true,
          service = EditorEmbedProvider.class
        )

4.  オプションで、 `type` プロパティをコンテンツのタイプに設定します。 ビデオ以外のコンテンツタイプのプロバイダーを作成する場合、新しいタイプ定数を作成し、 [をコンテンツタイプに新しいボタン](/docs/7-1/tutorials/-/knowledge_base/t/creating-new-buttons-for-alloyeditor) を追加できます。 独自のボタンを作成する場合は、既存の [埋め込みビデオボタンのJSXファイル](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-editor/frontend-editor-alloyeditor-web/src/main/resources/META-INF/resources/js/extras/buttons/embed) を例として使用して、独自のファイルを作成することをお勧めします。 デフォルトでは、プロバイダーは `UNKNOWN`分類され` 。 以下の構成例では、 <a href="https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-editor/frontend-editor-api/src/main/java/com/liferay/frontend/editor/api/embed/EditorEmbedProviderTypeConstants.java"><code>EditorEmbedProviderTypeConstants` クラス</a>によって提供される定数を使用して、 `VIDEO` タイプを指定しています。
   
        @Component(
          immediate = true,
          property = "type=" + EditorEmbedProviderTypeConstants.VIDEO,
          service = EditorEmbedProvider.class
        )

5.  [`EditorEmbedProvider` インターフェイス](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-editor/frontend-editor-api/src/main/java/com/liferay/frontend/editor/api/embed/EditorEmbedProvider.java)実装します。 構成例を以下に示します。
   
        public class MyEditorEmbedProvider implements EditorEmbedProvider {
       
        }

6.  必要なインポートを追加します。
   
        import com.liferay.frontend.editor.api.embed.EditorEmbedProvider;
        import com.liferay.frontend.editor.api.embed.EditorEmbedProviderTypeConstants;
        import com.liferay.petra.string.StringBundler;

    `* TypeConstants` インポートは、ビデオタイププロバイダーを追加する場合にのみ必要です。

7.  `* EmbedProvider`の `getId（）` メソッドをオーバーライドして、プロバイダーのIDを返します。 構成例を以下に示します。
   
        @Override
        public String getId() {
            return "providerName";
        }

8.  `* EmbedProvider`の `getTpl（）` メソッドをオーバーライドして、埋め込みテンプレートコード（通常はプロバイダーのiframe）を提供します。 次の例では、ストリーミングビデオサービスのテンプレートを定義しています。 `{embedId}` は、埋め込みコンテンツの一意の識別子のプレースホルダーであることに注意してください。
   
        @Override
        public String getTpl() {
            return StringBundler.concat(
                "<iframe allow=\"autoplay; encrypted-media\" allowfullscreen ",
                "height=\"315\" frameborder=\"0\" ",
                "src=\"https://www.liferaylunarresortstreaming.com/embed/{embedId}?rel=0\" ",
                "width=\"560\" mark="crwd-mark"></iframe>");
        }

9.  `* EmbedProvider`の `getURLSchemes（）` メソッドをオーバーライドして、プロバイダーの埋め込み表現を持つサポートされているURLスキームの配列を返します。 URLスキームは、URLがプロバイダーに一致するかどうかを示すJavaScript正規表現を使用して定義されます。 すべてのURLスキームには、単一の一致グループが含まれている必要があります。 前のステップで定義された `{embedId}` プレースホルダーは、一致するものに置き換えられます。
   
        @Override
        public String[] getURLSchemes() {
            return new String[] {
                "https?:\\/\\/(?:www\\.)?liferaylunarresortstreaming.com\\/watch\\?v=(\\S*)$"
            };
        }

10. モジュールをデプロイし、BlogsなどのAlloyEditorを使用するアプリを開き、新しいエントリーを作成します。 *追加ボタン* をクリックし、ビデオボタンを選択します---または新しいコンテンツタイプボタン---コンテンツのURLを貼り付けます。 *チェックマーク* をクリックして、URLスキームがサポートされていることを確認します。 コンテンツはエディターに埋め込まれます。

これで、AlloyEditorにコンテンツを埋め込む方法がわかりました。 ブログの投稿などの新しいコンテンツエントリを作成し、埋め込みビデオボタン（または作成したボタン）をクリックして、コンテンツのURLを貼り付けます。

## 関連トピック

[AlloyEditorのツールバーへのボタンの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-buttons-to-alloyeditor-toolbars)

[エディターに新しい動作を追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)
