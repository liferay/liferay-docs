---
header-id: changing-adaptive-medias-image-scaling
---

# アダプティブメディアの画像スケーリングの変更

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

記載のように [適応型メディアユーザーガイド](/docs/7-1/user/-/knowledge_base/u/adapting-your-media-across-multiple-devices)、適応型メディアは、管理者@物によって定義される画像の解像度に一致するように画像をスケーリングします。 通常、デフォルトのスケーリングが適していますが、必要に応じてカスタマイズすることもできます。 ただし、その前に、このスケーリングの仕組みを理解する必要があります。

## アダプティブメディアの画像スケーリングについて

アダプティブメディアには、画像のスケーリング方法を置き換えることができる拡張ポイントが含まれています。 [ `AMImageScaler` インターフェース](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/scaler/AMImageScaler.java) を定義適応型メディアのイメージスケーリングロジック。 デフォルトでは、Adaptive Mediaはこのインターフェイスの2つの実装を提供します。

  - [`AMDefaultImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMDefaultImageScaler.java)：デフォルトの画像スケーラー。 常に有効であり、画像処理とスケーリングに `java.awt` を使用します。

  - [`AMGIFImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMGIFImageScaler.java)：GIF画像でのみ機能するスケーラー。 @product@インスタンスの外部ツール [gifsicle](https://www.lcdf.org/gifsicle/) のインストールに依存します。 このスケーラーは、 *コントロールパネル* → *システム設定*で有効にする必要があります。

`AMImageScaler` インターフェイスを使用して、@product@のOSGiコンテナーにイメージスケーラーを登録する必要があります。 各スケーラーは、 `mime.type` プロパティを、処理するMIMEタイプに設定する必要もあります。 たとえば、スケーラーのMIMEタイプを `image / jpeg`と、そのスケーラーは `イメージ/ jpeg` イメージのみを処理できます。 特別なMIMEタイプ `*`を指定すると、スケーラーは任意の画像を処理できます。 [の場合 `AMDefaultImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMDefaultImageScaler.java) は `mime.type = *`を使用して登録され、 [の場合 `AMGIFImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMGIFImageScaler.java) は `mime.type = image / gif`を使用して登録されます。 すべてのスケーラーと同様に、両方のスケーラーは `AMImageScaler`実装し` 。</p>

<p spaces-before="0">同じMIMEタイプであっても、必要な数のイメージスケーラーを追加できます。 それでも、Adaptive Mediaは画像ごとに1つのスケーラーのみを使用し、このプロセスを使用して最適なスケーラーを決定します。</p>

<ol start="1">
<li><p spaces-before="0"> 画像と同じMIMEタイプで登録された画像スケーラーのみを選択します。</p></li>
<li><p spaces-before="0"> 最初のステップで選択されたスケーラーから有効なスケーラーを選択します（<a href="https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/scaler/AMImageScaler.java">は <code>AMImageScaler`</a> メソッド `isEnabled（）` は有効なスケーラーに対して `true` を返します）。</li>

3

2番目のステップで選択したスケーラーのうち、最高の `service.ranking`持つスケーラーを選択します。</ol>

これらの手順で結果が返されない場合は繰り返されますが、最初の手順では特別なMIMEタイプ `*`ます。 また、イメージスケーラーが特定のMIMEタイプに登録されており、 `service.ranking`が高い場合、特別なMIMEタイプ `*` 登録されているか、 `service.ranking`低い場合よりも選択される可能性が高いことに注意してください。 。

## イメージスケーラーの作成

アダプティブメディアが画像を拡大縮小する方法を理解したので、この拡大縮小をカスタマイズする方法を学習します。 例として、PNG画像のスケーリングをカスタマイズするサンプル画像スケーラーが表示されます。

カスタムイメージスケーラーを作成するには、次の手順に従います。

1.  `AMImageScaler`を実装するスケーラークラスを作成します。 また、スケーラークラスに `@Component`注釈を付け、スケーラーのMIMEタイプごとに `mime.type` プロパティを設定し、 `AMImageScaler` サービスを登録する必要があります。 同じMIMEタイプに複数のスケーラーがある場合は、 `@Component` アノテーションの `service.ranking` プロパティも設定する必要があります。 スケーラーが同じMIMEタイプの他のスケーラーより優先されるためには、そのサービスのランキングプロパティが他のスケーラーのそれよりも高くなければなりません。 `service.ranking` が設定されていない場合、デフォルトは `0`ます。

    | **注：** `service.ranking` プロパティは、イメージスケーラーに対して設定されていません。アダプティブメディアに付属（`AMDefaultImageScaler` および `AMGIFImageScaler`）。 したがって、サービスのランキングはデフォルトで `0`ます。 へ|いずれかのスケーラーを交換するには、スケーラーを同じMIMEタイプに設定する必要があります。 `0`よりも高いサービスランキングを指定します。

    たとえば、このサンプル画像スケーラーはPNGおよびx-PNG画像をスケーリングし、 `100`サービスランキングを持っています：
   
        @Component(
            immediate = true,
            property = {"mime.type=image/png", "mime.type=image/x-png", "service.ranking:Integer=100"},
            service = {AMImageScaler.class}
        )
        public class SampleAMPNGImageScaler implements AMImageScaler {...

    これには、次のインポートが必要です。
   
        import com.liferay.adaptive.media.image.scaler.AMImageScaler;
        import org.osgi.service.component.annotations.Component;

2.  スケーラーを有効にする場合は、 `isEnabled（）` メソッドを実装して `true` を返します。 多くの場合、常にスケーラーを有効にする必要があるため、このメソッドで `true` を返すだけで済みます。 これは、サンプル `SampleAMPNGImageScaler`です：
   
        @Override
        public boolean isEnabled() {
            return true;
        }

    この方法は、スケーラーが他のツールまたは機能に依存している場合にさらに興味深いものになります。 たとえば、 `AMGIFImageScaler` の `isEnabled（）` メソッドは、gifsicleが有効かどうかを決定します。 このスケーラーは、依存するツールであるgifsicleも有効になっている場合にのみ有効にする必要があります。
   
        @Override
        public boolean isEnabled() {
            return _amImageConfiguration.gifsicleEnabled();
        }

3.  `scaleImage` メソッドを実装し` 。 このメソッドにはスケーラーのビジネスロジックが含まれ、 <code>AMImageScaledImage` インスタンスを返す必要があります。 たとえば、 `SampleAMPNGImageScaler` のサンプル `scaleImage` 実装では、 `AMImageConfigurationEntry` を使用してスケーリングされた画像の最大の高さと幅の値を取得し、 `FileVersion` を使用して画像をスケーリングします。 スケーリングは、方法と仮定し、プライベート内部クラスの助けを借りて行われる `_scalePNG`、 `_getScalePNGHeight`、 `_getScalePNGWidth`、及び `_getScalePNGSize` 実際のスケーリングを実現します。
   
        @Override
        public AMImageScaledImage scaleImage(FileVersion fileVersion,
            AMImageConfigurationEntry amImageConfigurationEntry) {
       
            Map<String, String> properties = amImageConfigurationEntry.getProperties();
       
            int maxHeight = GetterUtil.getInteger(properties.get("max-height"));
            int maxWidth = GetterUtil.getInteger(properties.get("max-width"));
       
            try {
                InputStream inputStream = 
                    _scalePNG(fileVersion.getContentStream(false), maxHeight, maxWidth);
       
                int height = _getScalePNGHeight();
                int width = _getScalePNGWidth();
                long size = _getScalePNGSize();
       
                return new AMImageScaledImageImpl(inputStream, height, width, size);
            }
            catch (PortalException pe) {
                throw new AMRuntimeException.IOException(pe);
            }
        }
       
        private class AMImageScaledImageImpl implements AMImageScaledImage {
       
            @Override
            public int getHeight() {
                return _height;
            }
       
            @Override
            public InputStream getInputStream() {
                return _inputStream;
            }
       
            @Override
            public long getSize() {
                return _size;
            }
       
            @Override
            public int getWidth() {
                return _width;
            }
       
            private AMImageScaledImageImpl(InputStream inputStream, int height, 
                int width, long size) {
       
                _inputStream = inputStream;
                _height = height;
                _width = width;
                _size = size;
            }
       
            private final int _height;
            private final InputStream _inputStream;
            private final long _size;
            private final int _width;
       
        }

    これには、次のインポートが必要です。
   
        import com.liferay.adaptive.media.exception.AMRuntimeException;
        import com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry;
        import com.liferay.adaptive.media.image.scaler.AMImageScaledImage;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.repository.model.FileVersion;
        import com.liferay.portal.kernel.util.GetterUtil;
        import java.io.InputStream;
        import java.util.Map;

すばらしいです\！ これで、独自のイメージスケーラーの作成方法がわかりました。

## 関連トピック

[アプリでの適応画像の表示](/docs/7-1/tutorials/-/knowledge_base/t/displaying-adapted-images-in-your-app)

[適応画像の検索](/docs/7-1/tutorials/-/knowledge_base/t/finding-adapted-images)

[複数のデバイス間でメディアを適応させる](/docs/7-1/user/-/knowledge_base/u/adapting-your-media-across-multiple-devices)
