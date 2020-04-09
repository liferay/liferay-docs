---
header-id: modifying-an-editors-configuration
---

# エディターの構成の変更

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

さまざまな種類のWYSIWYGエディターを使用して、ポートレットのコンテンツを編集できます。 編集しているコンテンツに応じて、エディターを変更して、ニーズに合わせてカスタマイズされた構成を提供することができます。 このチュートリアルでは、@product@のサポートされているWYSIWYGエディターのデフォルト構成を変更して、要件を満たす方法を学習します。

## エディターの構成の更新

エディターの構成を変更するには、 [`EditorConfigContributor`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html) インターフェースを実装するコンポーネントを持つモジュールを作成します。 次の手順に従って、@product@のWYSIWYGエディターのいずれかを変更します。

1.  [OSGiモジュールを作成します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)。

2.  ポートレットの `build.gradle` ファイルを開き、 `com.liferay.portal.kernel` `バージョン` を `3.6.2`更新します。 これは、@product@リリースにバンドルされているバージョンです。

3.  モジュールの `src` ディレクトリに一意のパッケージ名を作成し、そのパッケージに [`BaseEditorConfigContributor`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/BaseEditorConfigContributor.html) クラスを拡張する新しいJavaクラスを作成します。

4.  `EditorConfigContributor` サービスを実装するコンポーネントクラスを作成します。
   
        @Component(
            property = {
       
            },
       
            service = EditorConfigContributor.class
        )

5.  次のインポートを追加します。
   
        import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
        import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
        import com.liferay.portal.kernel.json.JSONArray;
        import com.liferay.portal.kernel.json.JSONFactoryUtil;
        import com.liferay.portal.kernel.json.JSONObject;
        import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
        import com.liferay.portal.kernel.theme.ThemeDisplay;

6.  エディターの名前、エディターの構成キー、および/またはエディターが存在するポートレット名を指定します。 これらの3つのプロパティは、独立して、または任意の順序で一緒に指定できます。 使用可能なプロパティとその使用方法の詳細については、 [`EditorConfigContributor`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html) インターフェイスのJavadocを参照してください。 以下の構成例では、 `contentEditor` 構成キーと `Alloyeditor` 名前キーで識別されるAlloyEditorのコンテンツエディターを変更します。

    | **注：** ポートレットのすべてのエディターをターゲットにしている場合、| `editor.config.key` は必要ありません。 たとえば、単にターゲットにしたい場合| Webコンテンツポートレットのエディターでは、以下の構成を提供できます。 | @Component（| property = {"editor.name = ckeditor"、| "javax.portlet.name = com \ _liferay \ _journal \ _web \ _portlet \ _JournalPortlet"、| "service.ranking：Integer = 100" |}

    2つのポートレット名が宣言され（BlogsとBlogs Admin）、サービスがそれらのポートレットのコンテンツエディターに適用されることを指定します。 最後に、より高い [サービスのランク](/docs/7-1/tutorials/-/knowledge_base/t/fundamentals#services)提供することにより、構成はデフォルトの構成をオーバーライドします。
   
        @Component(
            property = {
                "editor.config.key=contentEditor", "editor.name=alloyeditor",
                "javax.portlet.name=com_liferay_blogs_web_portlet_BlogsPortlet",
                "javax.portlet.name=com_liferay_blogs_web_portlet_BlogsAdminPortlet", 
                "service.ranking:Integer=100"
            },
       
            service = EditorConfigContributor.class
        )

    | **注：** |に適用されるグローバル構成を作成する場合エディターを使用するすべての場所で、2つの別個の構成を作成する必要があります。エディターのみをターゲットとする1つの構成と2番目の構成|これは、ブログおよびブログ管理ポートレットを対象としています。 たとえば、2つの|以下の個別の構成は、すべての場所でAlloyEditorに更新を適用します|使用されている：| |構成1： `java | @Component（| immediate = true、| property = {| "editor.name = alloyeditor"、| "service.ranking：Integer = 100" |}、| | service = EditorConfigContributor.class |）|` | |構成2： `java | @Component（| immediate = true、| property = {| "editor.name = alloyeditor"、| "javax.portlet.name = com_liferay_blogs_web_portlet_BlogsPortlet"、| "javax.portlet.name = com_liferay_blogs_web_portlet_BlogsAdminPortlet"、| "service.ranking：Integer = 100 "|}、| | service = EditorConfigContributor.class |）|`

7.  `populateConfigJSONObject（）` メソッドをオーバーライドして、エディターのカスタム構成を提供します。 このメソッドは、元の構成JSONオブジェクトを更新します。 また、既存の構成、または別の `* EditorConfigContributor`によって導入された他の構成を更新または削除できます。
   
        @Override
        public void populateConfigJSONObject(
            JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
            ThemeDisplay themeDisplay,
            RequestBackedPortletURLFactory requestBackedPortletURLFactory) {
       
        }

8.  `populateConfigJSONObject` メソッドでは、 [`JSONObject`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/json/JSONObject.html) をインスタンス化して、エディターの現在の構成を保持する必要があります。 たとえば、以下のコードスニペットを使用して、エディターで使用可能なツールバーを取得できます。
   
        JSONObject toolbars = jsonObject.getJSONObject("toolbars");

    | **注：** このツールバー構成は、AlloyEditorにのみ適用できます。 |複数のエディターでサポートされている構成を選択した場合、|それらすべてに適用できます。 これを行うには、すべてのエディターを指定できます| （例えば、 `"editor.name = alloyeditor"`、 `"editor.name = CKEditorバージョン"`、| `ckeditor_bbcode` 等）で `@Component` あなたの注釈| `ステップ6で行ったように、EditorConfigContributor` 実装。 |を使用しますこのチュートリアルの下部にリンクして、各エディターの構成を表示します|オプションと要件。

9.  ツールバーを取得したので、変更できます。 以下の例では、カメラボタンをAlloyEditorの[追加]ツールバーに追加します。 ツールバー構成オブジェクトから *Add* ボタンを [`JSONArray`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/json/JSONArray.html)として抽出し、ボタンをその `JSONArray`追加します。
   
        if (toolbars != null) {
            JSONObject toolbarAdd = toolbars.getJSONObject("add");
       
            if (toolbarAdd != null) {
                JSONArray addButtons = toolbarAdd.getJSONArray("buttons");
       
                addButtons.put("camera");
            }
        }

    構成JSONオブジェクトは、 `populateConfigJSONObject` メソッドで実装した変更とともにエディターに渡されます。

10. 最後に、モジュールのJARファイルを生成し、 `deploy` フォルダーにコピーします。 モジュールがインストールされ、サービスレジストリにアクティブ化されると、新しいエディター構成が使用可能になります。

@product@は、いくつかの異なるタイプのWYSIWYGエディターをサポートしています。

  - [AlloyEditor](https://alloyeditor.com/api/1.5.0/Core.html)
  - [CKEditor](http://docs.ckeditor.com/#!/api/CKEDITOR.config)
  - [TinyMCE](http://www.tinymce.com/wiki.php/Configuration)

各エディターの構成APIにアクセスして、各エディターが構成設定に提供するものを確認してください。

## 関連トピック

[エディターに新しい動作を追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)

[AlloyEditorの変更](/docs/7-1/tutorials/-/knowledge_base/t/alloyeditor)

[WYSIWYGエディターのポートレットへの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-a-wysiwyg-editor-to-a-portlet)
