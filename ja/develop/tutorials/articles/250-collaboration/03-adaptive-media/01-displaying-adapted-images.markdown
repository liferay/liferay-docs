---
header-id: displaying-adapted-images-in-your-app
---

# アプリでの適応画像の表示

[TOC levels=1-4]

表示するには [画像に適した](/docs/7-1/user/-/knowledge_base/u/adapting-your-media-across-multiple-devices) アプリに、適応型メディアはに便利なタグライブラリを提供しています [モジュール `com.liferay.adaptive.media.image.taglib`](https://github.com/liferay/com-liferay-adaptive-media/tree/master/adaptive-media-image-taglib)。 このtaglibには、 `fileVersion`つの必須属性のみがあります。 この属性は、表示する適応画像のファイルバージョンを示します。 また、のような、必要に応じて多くの属性として追加することができます `クラス`、 `スタイル`、 `データ・サンプル`ように、と。 追加した属性は、taglibがレンダリングするマークアップ内の適応画像に追加されます。

このチュートリアルの使用 [適応型メディアサンプルアプリ](https://github.com/sergiogonzalez/adaptive-media-samples) どのようにこのタグライブラリを使用する方法を表示します。 このアプリは、ページに追加されると、現在のサイトのDocuments and Mediaアプリからのすべての適応画像を表示します。ただし、 [適応メディア画像解像度](/docs/7-1/user/-/knowledge_base/u/adding-image-resolutions) およびDocuments and Media画像が存在します。

taglibを使用するには、次の手順に従います。

1.  モジュールtaglib依存関係をプロジェクトに含めます。 たとえば、Gradleを使用している場合、プロジェクトの `build.gradle` ファイルに次の行を追加する必要があります。
   
        provided group: "com.liferay", name: "com.liferay.adaptive.media.image.taglib", version: "1.0.0"

    たとえば、 [はAdaptive Media Samplesアプリの `build.gradle` ファイル](https://github.com/sergiogonzalez/adaptive-media-samples/blob/master/adaptive-media-sample-web/build.gradle) にこのtaglibが含まれています。

2.  JSPでtaglibを宣言します。
   
        <%@ taglib uri="http://liferay.com/tld/adaptive-media-image" prefix="liferay-adaptive-media" %>

    たとえば、 [は、Adaptive Media Samplesアプリの `init.jsp`](https://github.com/sergiogonzalez/adaptive-media-samples/blob/master/adaptive-media-sample-web/src/main/resources/META-INF/resources/init.jsp) は、アプリが必要とするすべてのtaglibを宣言します。

3.  アプリのJSPファイルで適応画像を表示する場合は、taglibを使用します。
   
        <liferay-adaptive-media:img class="img-fluid" fileVersion="<%= fileEntry.getFileVersion() %>" />

    たとえば、 [は、Adaptive Media Samplesアプリの `view.jsp`](https://github.com/sergiogonzalez/adaptive-media-samples/blob/master/adaptive-media-sample-web/src/main/resources/META-INF/resources/view.jsp) はtaglibを使用して、 `col-md-6` [列コンテナークラス](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-manually)グリッドに適応画像を表示します。 アプリが生成するマークアップを見ると、記事 [適応画像を使用したコンテンツの作成](/docs/7-1/user/-/knowledge_base/u/creating-content-with-adapted-images)説明されているように、 `<picture>` タグを使用していることがわかります。

    ![図1：アダプティブメディアサンプルアプリには、サイトのすべての適応画像が表示されます。](../../../images/adaptive-media-sample.png)

よくやった\！ これで、アプリで適応画像を表示する方法がわかりました。

## 関連トピック

[適応画像の検索](/docs/7-1/tutorials/-/knowledge_base/t/finding-adapted-images)

[アダプティブメディアの画像スケーリングの変更](/docs/7-1/tutorials/-/knowledge_base/t/changing-adaptive-medias-image-scaling)

[複数のデバイス間でメディアを適応させる](/docs/7-1/user/-/knowledge_base/u/adapting-your-media-across-multiple-devices)
