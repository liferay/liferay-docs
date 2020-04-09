---
header-id: creating-the-osgi-module-and-configuring-the-editorconfigcontributor
---

# OSGiモジュールの作成とEditorConfigContributorクラスの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

AlloyEditorのツールバーにボタンを追加するには、まずサービスタイプ `EditorConfigContributor.class`OSGiコンポーネントクラスを作成する必要があります。 以下の手順に従って、OSGiモジュールを作成および構成します。

1.  [ [ブレードのポートレットテンプレート](/docs/7-1/reference/-/knowledge_base/r/using-the-portlet-template)を使用して、OSGiモジュール](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)作成します。
   
        blade create -t portlet -p com.liferay.docs.my.button -c 
        MyEditorConfigContributor my-new-button

2.  ポートレットの `build.gradle` ファイルを開き、 `com.liferay.portal.kernel` `バージョン` を `3.6.2`更新します。 これは、@product@リリースにバンドルされているバージョンです。

3.  ステップ1で作成したポートレットクラス（`MyEditorConfigContributor`）を開き、次のインポートを追加します。
   
        import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
        import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
        import com.liferay.portal.kernel.json.JSONArray;
        import com.liferay.portal.kernel.json.JSONFactoryUtil;
        import com.liferay.portal.kernel.json.JSONObject;
        import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
        import com.liferay.portal.kernel.theme.ThemeDisplay;

4.  `@Component` およびプロパティを以下のプロパティに置き換えます。
   
        @Component(
            immediate = true,
            property = {
              "editor.name=alloyeditor",
              "service.ranking:Integer=100"
            },
            service = EditorConfigContributor.class  
        )

    これは、構成のAlloyEditorをターゲットとし、より高い [サービスのランク](/docs/7-1/tutorials/-/knowledge_base/t/fundamentals#services)提供することにより、デフォルトのサービスをオーバーライドします。 より具体的な構成を対象とする場合は、 [`EditorConfigContributor` インターフェースのJavadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html)使用可能なプロパティを見つけることができます。

5.  `GenericPortlet`代わりに `BaseEditorConfigContributor` 拡張し` 。</p></li>
<li><p spaces-before="0"> <code>doView（）` メソッドとコンテンツを、以下に示す `populateConfigJSONObject（）` メソッドに置き換えます。
   
        @Override
        public void populateConfigJSONObject(
                JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
                ThemeDisplay themeDisplay,
                RequestBackedPortletURLFactory requestBackedPortletURLFactory) {
       
        }

7.  `populateConfigJSONObject（）` メソッド内で、AlloyEditorのツールバーを取得します。
   
        JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");
       
        if (toolbarsJSONObject == null) {
                toolbarsJSONObject = JSONFactoryUtil.createJSONObject();
        }

8.  AlloyEditor</a>バンドルされている

CKEditorプラグインの1つにボタンを追加する場合、以下のコードを追加して追加のプラグインを取得し、Aロイエディターの構成にプラグインを追加します。 以下の例は、 `クリップボード` CKEditorプラグインを追加します。</p> 
   
        String extraPlugins = jsonObject.getString("extraPlugins");
       
        if (Validator.isNotNull(extraPlugins)) {
          extraPlugins = extraPlugins + ",ae_uibridge,ae_autolink,
          ae_buttonbridge,ae_menubridge,ae_panelmenubuttonbridge,ae_placeholder,
          ae_richcombobridge,clipboard";
        }
        else {
          extraPlugins = "ae_uibridge,ae_autolink,ae_buttonbridge,ae_menubridge,
          ae_panelmenubuttonbridge,ae_placeholder,ae_richcombobridge,clipboard";
        }
       
        jsonObject.put("extraPlugins", extraPlugins);
       
   
   AlloyEditorには、CKEditorのUIとAlloyEditorのUIの間のギャップを埋めるためのプラグインもいくつか付属しています。 これらには、上記の `ae_` というプレフィックスが付いています。 互換性を確保するためにすべてを含めることをお勧めします。</li> </ol> 
   
   `* EditorConfigContributor` クラスが準備されています。 ここで、ボタンを追加するツールバーを選択する必要があります： [追加ツールバー](/docs/7-1/tutorials/-/knowledge_base/t/adding-a-button-to-the-add-toolbar) または [つのスタイルツールバー](/docs/7-1/tutorials/-/knowledge_base/t/adding-a-button-to-a-styles-toolbar)。
   
   

## 関連トピック

[エディターに新しい動作を追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)

[CKEditorプラグインリファレンスガイド](/docs/7-1/reference/-/knowledge_base/r/ckeditor-plugin-reference-guide)
