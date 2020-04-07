---
header-id: rendering-web-content-in-your-android-app
---

# AndroidアプリでのWebコンテンツのレンダリング

[TOC levels=1-4]

@product@は、 `JournalArticle` エンティティとして [Webコンテンツ](/docs/7-1/user/-/knowledge_base/u/creating-web-content) 記事を表し

 。 Liferay Screensは、アプリでこれらのエンティティをレンダリングするいくつかの方法を提供します。</p> 

`JournalArticle`のHTMLをアプリに表示する最も簡単な方法は、 [Webコンテンツ表示スクリーンレット](/docs/7-1/reference/-/knowledge_base/r/webcontentdisplayscreenlet-for-android)を使用することです。 このスクリーンレットは非常に強力で、ニーズに合わせていくつかの複雑なユースケースをサポートしています。 [Webコンテンツリストスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/web-content-list-screenlet-for-android) を使用して、Webコンテンツ記事のリストを表示することもできます。 このチュートリアルでは、両方のスクリーンレットを使用してアプリにWebコンテンツを表示する方法を示します。



## 基本的なWebコンテンツの取得

Webコンテンツ表示スクリーンレットの最も単純な使用例は、 `JournalArticle`のHTMLを [Android `WebView`](http://developer.android.com/guide/webapps/webview.html)レンダリングすることです。 Screenlet XMLで `JournalArticle`の `articleId` を提供するだけで、残りの部分はScreenletが処理します（小さなディスプレイでレンダリングするために必要なCSSで自身を装飾することを含みます）。 次のスクリーンレットXMLはこれを示しています。

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:articleId="YOUR_ARTICLE_ID" />
    

コンテンツレンダリングするには *正確に* 、それはあなたのモバイルサイトに表示されるように、しかし、あなたはCSSをインラインで提供したり、テンプレートを使用する必要があります。 返されるHTMLは、LiferayインスタンスのグローバルCSSを認識しません。

[スクリーンレットリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/webcontentdisplayscreenlet-for-android)説明されているように、リスナーを使用してHTMLを変更することもできます。

デフォルトのセキュリティポリシーでは、Android `WebView` はページのJavaScriptを実行しません。 このようなJavaScriptの実行を有効にするには、XMLを介して `javascriptEnabled` プロパティを設定します。

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:articleId="YOUR_ARTICLE_ID"
        app:javascriptEnabled="true" />
    

または、スクリーンレットを含むアプリのフラグメントまたはアクティビティクラスでこのプロパティを設定できます。

    ...
    screenlet.setJavascriptEnabled(true);
    ...
    

`isJavascriptEnabled（）` メソッドを使用して、このプロパティの設定を確認することもできます。

ご覧のとおり、これはすべて簡単です。 何がおかしいのでしょうか？ 有名な最後の言葉。 よくある間違いは、 `JournalArticle` エンティティを含むサイトのグループIDではなく、デフォルトの `groupId` を使用することです。

アプリの残りの部分でデフォルトの `groupId` を使用する必要があるが、別のサイトのHTMLをレンダリングする必要がある場合は、 `app：groupId` 属性でWebコンテンツ表示スクリーンレットの `groupId` を設定できます。 あるいは、スクリーンレットを使用するアクティビティまたはフラグメントコードで `setGroupId` メソッドを使用できます。



## テンプレートを使用する

Webコンテンツ表示スクリーンレットは、 [テンプレート](/docs/7-1/user/-/knowledge_base/u/designing-web-content-with-templates) を使用して、 `JournalArticle` エンティティをレンダリングすることもできます。 たとえば、Liferayインスタンスには、モバイルデバイスでコンテンツを表示するために特別に設計されたカスタムテンプレートがある場合があります。

テンプレートを使用するには、Screenlet XMLの `templateId` プロパティでIDを指定します。

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:articleId="YOUR_ARTICLE_ID"
        app:templateId="YOUR_TEMPLATE_ID" />
    



## 構造の使用

モバイルデバイスの画面スペースは限られているため、多くの場合、Webコンテンツ記事の最も重要な部分のみを表示する必要があります。 Webコンテンツが [構造化](/docs/7-1/user/-/knowledge_base/u/designing-web-content-with-templates)場合、Webコンテンツ表示スクリーンレットを使用して、 `JournalArticle`の構造から特定のフィールドのみを表示することにより、これを行うことができます。 これを行う最も簡単な方法は、スクリーンレットXMLの `structureId` および `labelFields` 属性で、それぞれ構造のIDおよびフィールドのコンマ区切りリストを指定することです。 以下に例を示します。

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        liferay:articleId="YOUR_ARTICLE_ID"
        liferay:labelFields="YOUR_LABELS"
        liferay:layoutId="@layout/webcontentdisplay_structured_default"
        liferay:structureId="YOUR_STRUCTURE_ID" />
    

独自のレイアウトを使用して、構造フィールドを希望どおりにレンダリングすることもできます。 これを行うには、レイアウトが `WebContentStructuredDisplayView` を継承し、 `webContent` エンティティに解析および保存された情報を読み取る必要があります。 このようなカスタムレイアウトで2つの構造フィールドを表示することにより、 [テストアプリ](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app) にはこの完全な例が含まれます。

1.  [レイアウトファイル `webcontentdisplaystructured_example.xml`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/webcontentdisplaystructured_example.xml) は、カスタムレイアウトを定義します。 
   
   

    ``` 
     <com.liferay.mobile.screens.testapp.webviewstructured.WebContentDisplayView 
         xmlns:android="http://schemas.android.com/apk/res/android"
         android:layout_width="match_parent"
         android:layout_height="match_parent">

         <TextView
             android:id="@+id/web_content_first_field"
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:background="@android:color/holo_red_light" />

         <TextView
             android:id="@+id/web_content_second_field"
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:background="@android:color/holo_green_light" />

     </com.liferay.mobile.screens.testapp.webviewstructured.WebContentDisplayView>
    ```


2.  [`WebContentDisplayView` クラス](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/webviewstructured/WebContentDisplayView.java) は、カスタムレイアウトの機能を設定します。 
   
   

    ``` 
     public class WebContentDisplayView extends WebContentStructuredDisplayView {

         ...

         @Override
         public void showFinishOperation(WebContent webContent) {
             super.showFinishOperation(webContent);

             DDMStructure ddmStructure = webContent.getDDMStructure();

             TextView firstField = (TextView) findViewById(R.id.first_field);
             firstField.setText(String.valueOf(ddmStructure.getField(0).getCurrentValue()));

             TextView secondField = (TextView) findViewById(R.id.second_field);
             secondField.setText(String.valueOf(ddmStructure.getField(1).getCurrentValue()));
         }
     }
    ```


3.  [スクリーンレットXMLの](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/web_content_display_structured.xml) `layoutId` 属性は、使用するカスタムレイアウトを指定します。 
   
   

    ``` 
     <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         liferay:articleId="@string/liferay_article_structured_article_id"
         liferay:labelFields="@string/liferay_article_structured_label_fields_first_field"
         liferay:layoutId="@layout/webcontentdisplaystructured_example"
         liferay:offlinePolicy="REMOTE_FIRST"
         liferay:structureId="@string/liferay_article_structured_structure_id" />
    ```


すばらしいです\！ これで、Webコンテンツ表示スクリーンレットで構造化Webコンテンツを使用する方法がわかりました。 次に、アプリのWebコンテンツ記事のリストを表示する方法を学びます。



## Webコンテンツ記事のリストを表示する

上記の例は、Webコンテンツ表示スクリーンレットを使用して、アプリで単一のWebコンテンツ記事のコンテンツを表示する方法を示しています。しかし、代わりに記事のリストを表示したい場合はどうでしょうか？ 問題ない\！ これには、Webコンテンツリストスクリーンレットを使用できます。 Webコンテンツリストスクリーンレットは、Webコンテンツフォルダーのコンテンツを取得し、必要なラベルのみを表示できます。 スクリーンレットは [構造化](/docs/7-1/user/-/knowledge_base/u/designing-web-content-with-templates) コンテンツも認識しているため、特定の構造フィールドで各行をレンダリングできます。 これは、カスタムレイアウトを介して行うこともできます。

WebコンテンツリストスクリーンレットでWebコンテンツフォルダーを使用するには、スクリーンレットXMLの `folderId` 属性でフォルダーのIDを指定します。 リスト内の各記事の特定の構造フィールドをレンダリングするには、Screenlet XMLの `labelFields` 属性でそのフィールドを指定します。 たとえば、

    <com.liferay.mobile.screens.webcontent.list.WebContentListScreenlet
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:folderId="YOUR_FOLDER_ID"
        app:labelFields="Text" />
    

この例は、テストアプリの [`web_content_display_list.xml` レイアウトファイル](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/web_content_display_list.xml)でも確認できます。

また、 [Screensの `WebContent` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/webcontent/WebContent.java) のいくつかのメソッドは、異なるロケールのコンテンツをレンダリングするのに役立つことに注意してください。 たとえば、 `getLocalized（name）` はフィールドの名前を受け取り、モバイルデバイスの現在のロケールで値を返します。 このようなメソッドは、基になる構造、XML解析、またはHTTP呼び出しを心配することなく、カスタムビューをレンダリングするのに役立ちます。



## アセットのリストを表示する

Webコンテンツ記事など、アプリのさまざまなアセットのリストをレンダリングするには、アセットリストスクリーンレットを使用できます。 アセットリストスクリーンレットは、Liferayインスタンスのアセットのリストを表示できます。 Webコンテンツリストスクリーンレットと同様に、Webコンテンツ記事の構造フィールドにアクセスしたり、カスタムレイアウトを使用して各アセットタイプをレンダリングしたりすることもできます。 詳細については、Asset List Screenlet</a>リファレンスドキュメントを参照してください。</p> 



## 関連トピック

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)

[Androidスクリーンレットでビューを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-android-screenlets)

[Android用のWebコンテンツ表示スクリーンレット](/docs/7-1/reference/-/knowledge_base/r/webcontentdisplayscreenlet-for-android)

[Android用のWebコンテンツリストスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/web-content-list-screenlet-for-android)

[Android用のアセットリストスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/assetlistscreenlet-for-android)
