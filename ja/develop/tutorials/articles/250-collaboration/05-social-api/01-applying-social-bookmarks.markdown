---
header-id: applying-social-bookmarks
---

# ソーシャルブックマークの適用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ソーシャルブックマークを有効にすると、Twitter、Facebook、およびGoogle Plusで共有するアイコンがアプリケーションのコンテンツの下に表示されます。 Taglibは、この機能をアプリに追加するために必要なマークアップを提供します。

![図1：組み込みのブログポートレットでソーシャルブックマークが有効になっている](../../../images/social-bookmarks-inline.png)

アプリにソーシャルブックマークを追加する手順は次のとおりです。

1.  エンティティが [アセット有効](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)ことを確認してください。

2.  プロジェクトの `build.gradle` ファイルで、モジュール [`com.liferay.social.bookmarks.taglib`](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.social.bookmarks.taglib/)依存関係を追加します。
   
        compileOnly group: "com.liferay", name: "com.liferay.social.bookmarks.taglib", version: "1.0.0"

3.  ソーシャルブックマークを表示するビューを選択します。 たとえば、ポートレットのビューの1つでそれらを表示できます。

    | **注：** ポートレットの|にソーシャルブックマークを実装する必要はありません。 [アセットレンダラー](/docs/7-1/tutorials/-/knowledge_base/t/rendering-an-asset)。 | Asset Publisherは、デフォルトでアセットレンダラーにソーシャルブックマークを表示します。

4.  ビューのJSPに、 `liferay-social-bookmarks` taglib宣言を含めます。
   
        <%@ taglib uri="http://liferay.com/tld/social-bookmarks" prefix="liferay-social-bookmarks" %>

5.  エンティティのインスタンスを取得します。 これは自由に行うことができます。 この例では、 `ParamUtil` を使用してレンダーリクエストからエンティティのIDを取得し、エンティティの `-LocalServiceUtil` クラスを使用してエンティティオブジェクトを作成します。
   
        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

6.  `liferay-social-bookmarks：bookmarks` タグを使用して、ソーシャルブックマークコンポーネントを追加します。 その属性は次のとおりです。

`className`：エンティティのクラス名。

`classPK`：エンティティのJavaクラスの主キー。

`displayStyle`：ソーシャルブックマークの表示スタイル。 可能な値は、行</code>に表示する `インライン` 、メニューで非表示にする `メニュー`です。

`title`：共有されているコンテンツのタイトル。

`タイプ`：使用するソーシャルメディアサービスのコンマ区切りリスト（例： `facebook、twitter`）。 ポータルで利用可能なすべてのソーシャルメディアサービスを使用するには、この属性を省略するか、その値に `<％= null％>` を使用します。

`url`：共有されているポータルコンテンツへのURL。

`liferay-social-bookmarks：bookmarks` タグを使用して、ブログアプリのブログエントリにソーシャルブックマークを追加する例を次に示します。

    <liferay-social-bookmarks:bookmarks
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            displayStyle="inline"
            title="<%= entry.getTitle() %>"
            types="facebook,twitter"
            url="<%= PortalUtil.getCanonicalURL(bookmarkURL.toString(), themeDisplay, layout) %>"
    />

この例の `displayStyle` は、インライン</code>に `設定されています。 このチュートリアルの冒頭のスクリーンショットは、これがどのように見えるかを示しています。 最初の3つのソーシャルブックマークは行に表示され、残りは <em x-id="3">シェア</em> メニューに表示されます。 代わりに <code>メニュー` を使用すると、すべてのソーシャルブックマークがメニューに表示されます。

![図2： <code>displayStyle</code> を <code>menu</code>設定すると、ソーシャルブックマークはすべて* Share *メニューに表示されます。](../../../images/social-bookmarks-menu.png)

`タイトル` は、エントリの `getTitle（）` メソッドによって取得されます。 そのエンティティに適したメソッドを使用して、エンティティのタイトルを取得する必要があることに注意してください。

`PortalUtil` メソッド `getCanonicalURL` が `url`に対して呼び出されることに注意してください。 このメソッドは、ページの完全なURLからSEOに優しいURLを構築します。 詳細については、 [メソッドのJavadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/PortalUtil.html#getCanonicalURL-java.lang.String-com.liferay.portal.kernel.theme.ThemeDisplay-com.liferay.portal.kernel.model.Layout-)参照してください。

## 関連トピック

[資産フレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)
