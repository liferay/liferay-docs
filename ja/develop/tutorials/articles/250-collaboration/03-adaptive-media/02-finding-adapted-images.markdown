---
header-id: finding-adapted-images
---

# 適応画像の検索

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ほとんどの場合、あなたはに適応メディアのtaglibに頼ることができます [表示にアプリ内の画像を適応](/docs/7-1/tutorials/-/knowledge_base/t/displaying-adapted-images-in-your-app)。 このtaglibは、指定されたファイルバージョンを使用して、Adaptive Mediaのfinder APIを照会し、リクエストを行っているデバイスに適した適応画像を表示します。 ただし、さらに制御が必要な場合は、taglibを使用する代わりに、APIを使用して独自のクエリを作成できます。 たとえば、特定の次元の特定の画像を必要とするアプリがある場合、Adaptive Mediaのfinder APIを直接クエリすることをお勧めします。 その後、好きなように画像を表示できます（HTML `<img>` タグなど）。

アダプティブメディアのファインダーAPIを使用すると、特定の検索条件とフィルターに基づいて適応画像を取得するクエリを作成できます。 たとえば、ファイルのバージョンや解像度に一致する、または画像の幅などの属性で並べられた適応画像を取得できます。 おおよその属性値（*ファジー* 属性）に一致する適応画像を取得することもできます。

このチュートリアルでは、Adaptive MediaのAPIを呼び出して、アプリで適応画像を取得する方法を示します。最初に、このようなAPI呼び出しを作成する方法を学びます。

## アダプティブメディアのAPIを呼び出す

アダプティブメディアのAPIへのエントリポイントは、 [AM `AMImageFinder` インターフェイス](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/finder/AMImageFinder.java)です。 これを使用するには、まずクラスにOSGiコンポーネントを注入する必要があります。これは、次のようにOSGiコンポーネントでもある必要があります。

    @Reference
    private AMImageFinder _amImageFinder;

これにより、 `AMImageFinder` インスタンスが使用可能になります。 `getAdaptiveMediaStream`つのメソッドがあり、 `AdaptiveMedia` オブジェクトのストリームを返します。 このメソッドは、 `AMImageQueryBuilder`を介して `AMQuery` （適応画像のクエリ）を作成する `関数` を取り、異なる属性（幅、高さ、順序など）に基づいて適応画像を検索できます。 呼び出す `AMImageQueryBuilder` メソッドは、構築する正確なクエリによって異なります。

たとえば、一般的な `getAdaptiveMediaStream` 呼び出しは次のとおりです。

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.methodToCall(arg).done());

`getAdaptiveMediaStream` の引数は、 `AMImageQueryBuilder`構築された `AMQuery` を返すラムダ式です。 `methodToCall（arg）` は、呼び出したい `AMImageQueryBuilder` メソッドとその引数のプレースホルダーです。 正確な呼び出しは、適応画像の選択に使用する基準によって異なります。 ただし、これに続く `done（）` 呼び出しはプレースホルダーではありません。呼び出した `AMImageQueryBuilder` メソッドに関係なく、 `AMQuery` 作成して返します。

作成の詳細については `AMQuery` インスタンスを、参照 [のJavadoc `AMImageQueryBuilder`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/finder/AMImageQueryBuilder.java)。

次に、適応画像を取得する呼び出しを構築する特定の例を見ることができます。

## 特定のファイルバージョンの適応画像の取得

特定のファイルバージョンに適合した画像を取得するには、引数として [`FileVersion` オブジェクト](https://docs.liferay.com/portal/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/model/FileVersion.html) て、FileVersion</code> `AMImageQueryBuilder` メソッド `呼び出す必要があります。</p>

<pre><code>Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
    _amImageFinder.getAdaptiveMediaStream(
        amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion).done());
`</pre>

最新の承認済みファイルバージョンに適合した画像を取得するには、 [`FileEntry` オブジェクト](https://docs.liferay.com/portal/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/model/FileVersion.html)`forFileEntry` メソッドを使用します。

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileEntry(fileEntry).done());

これらの呼び出しは、有効な画像解像度が [適合した画像のみを返すことに注意してください](/docs/7-0/user/-/knowledge_base/u/managing-image-resolutions)。 無効化された解像度に適応した画像は、ストリームに含まれません。 画像解像度のステータスに関係なく、すべての適合画像を取得するには、定数 `AMImageQueryBuilder.ConfigurationStatus.ANY``withConfigurationStatus` メソッドを呼び出す必要もあります。

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.ANY).done());

画像の解像度が無効になっているときに特定のファイルバージョンに適合した画像を取得するには、同じ呼び出しを行い、代わりに定数 `AMImageQueryBuilder.ConfigurationStatus.DISABLED`使用します。

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.DISABLED).done());

次に、特定の画像解像度に適応した画像を取得する方法を学びます。

## 特定の画像解像度に適応した画像を取得する

画像解像度のUUIDを `AMImageFinder`に提供することにより、その解像度の適合画像を取得できます。 このUUIDは、 [がアダプティブメディアアプリで解像度](/docs/7-0/user/-/knowledge_base/u/adding-image-resolutions) を追加するときに定義されます。解像度に適合した画像を取得するには、その解像度のUUIDを `forConfiguration` メソッドに渡す必要があります。

たとえば、このコードは、ファイルバージョンに一致し、UUID `hd-resolution`画像解像度に属する適応画像を取得します。 解像度が有効か無効かに関係なく、適応された画像を返します。

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .forConfiguration("hd-resolution").done());

次に、適合した画像を特定の順序で返す方法を学習します。

## 特定の順序で適応画像を取得する

`getAdaptiveMediaStream` が適応画像を返す順序を定義することもできます。 これを行うには、 `done（）` メソッドを呼び出す直前に、ソート基準で `orderBy` メソッドを呼び出します。 `orderBy` メソッドは2つの引数を取ります。最初の引数は並べ替える画像属性（幅/高さなど）を指定し、2番目の引数は並べ替え順序（昇順/降順など）を指定します。

たとえば、次のコードは、画像の解像度が有効かどうかに関係なく、すべての適応画像を取得し、画像の幅の昇順で並べます。

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinderImpl.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(_fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.ANY)
                .orderBy(AMImageAttribute.AM_IMAGE_ATTRIBUTE_WIDTH, AMImageQueryBuilder.SortOrder.ASC)
                .done());

`orderBy` 引数 `AMImageAttribute.AM_IMAGE_ATTRIBUTE_WIDTH` および `AMImageQueryBuilder.SortOrder.ASC` は、それぞれ画像の幅と昇順の並べ替えを指定します。 または、 `AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT` を使用して画像の高さでソートし、 `AMImageQueryBuilder.SortOrder.DESC` を使用して降順でソートすることもできます。

次に、適応画像を取得するときにおおよその属性値を指定する方法を学習します。

## ファジー属性を持つ適応画像の取得

アダプティブメディアでは、 *ファジィ属性* （近似属性値）に一致する適応画像を取得することもできます。 たとえば、ファジー属性を使用すると、高さが約200ピクセル、サイズが約100 kbの適応画像を要求できます。 APIは、指定された属性にどれだけ近いかによって要素を並べたストリームを返します。 たとえば、高さが150px、350px、600px、および900pxの画像に適合した4つの画像解像度があるとします。 高さが約400pxの適応画像を検索すると、ストリームで次の順序が返されます：350px、600px、150px、900px。

**どれだけ近いのか 属性に依存します。 幅、高さ、および長さの場合、数値比較により画像が順序付けられます。 これらの属性は文字列であり、このように委任するので、コンテンツタイプ、ファイル名、またはUUIDの場合には、比較は、より難しいです [のJava `String.compareTo` 方法](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#compareTo-java.lang.String-)。

ファジー属性を指定するには、 `done（）` メソッドを呼び出す直前に、検索条件で `with` メソッドを呼び出します。 `with` メソッドは、画像属性とその属性のおおよその値の2つの引数を取ります。 たとえば、次のコードは、高さ（`AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT`）が約400pxの適応画像を取得します。

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinderImpl.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(_fileVersion)
                .with(AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT, 400).done());

代わりに画像の幅を検索するには、 `AM` メソッドの最初の引数として `AMImageAttribute.AM_IMAGE_ATTRIBUTE_WIDTH` を使用します。

## アダプティブメディアストリームの使用

`AdaptiveMedia` ストリームを取得したら、そこから必要な情報を取得できます。 たとえば、次のコードは、適応された各画像のURIを出力します。

    adaptiveMediaStream.forEach(
        adaptiveMedia -> {
            System.out.println(adaptiveMedia.getURI());
        }
    );

`AdaptiveMedia` ストリームから他の値と属性を取得することもできます。 以下に例を示します。

    // Get the InputStream 
    adaptiveMedia.getInputStream()
    
    // Get the content length
    adaptiveMedia.getValueOptional(AMAttribute.getContentLengthAMAttribute())
    
    // Get the image height
    adaptiveMedia.getValueOptional(AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT)

驚くばかり\！ これで、適応画像を見つけて使用する方法がわかりました。

## 関連トピック

[アプリでの適応画像の表示](/docs/7-1/tutorials/-/knowledge_base/t/displaying-adapted-images-in-your-app)

[アダプティブメディアの画像スケーリングの変更](/docs/7-1/tutorials/-/knowledge_base/t/changing-adaptive-medias-image-scaling)

[複数のデバイス間でメディアを適応させる](/docs/7-1/user/-/knowledge_base/u/adapting-your-media-across-multiple-devices)
