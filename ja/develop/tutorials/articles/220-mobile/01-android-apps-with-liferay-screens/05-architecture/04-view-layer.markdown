---
header-id: view-layer
---

# レイヤーを表示

[TOC levels=1-4]

ビューレイヤーを使用すると、開発者はスクリーンレットのルックアンドフィールを設定できます。 各スクリーンレットの `liferay：layoutId` 属性は、そのビューを指定します。 ビューは、スクリーンレットクラス、ビュークラス、およびレイアウトXMLファイルで構成されます。 レイアウトXMLファイルはUIコンポーネントを指定し、Screenletクラスとビュークラスはビューの動作を制御します。 これらの1つ以上のビューレイヤーコンポーネントを別のビューから継承することにより、異なるビュー *タイプ* により、スクリーンレットのUIデザインと動作をさまざまなレベルで制御できます。

いくつかの異なるビュータイプがあります。

**テーマ：** は、現在のビューと同じ構造を示しますが、ビューのリソースのテーマの色と色合いを変更します。 既存のすべてのビューは、さまざまなスタイルでテーマ設定できます。 ビューの色は、Android [カラーパレット](https://www.google.com/design/spec/style/color.html#color-color-palette)の現在の値を反映しています。 あなたが別のビューセットの色で1セットのビューを使用する場合は、（例えば、アプリのテーマでこれらの色を使用することができます `colorPrimary_default`、 `colorPrimary_material`、 `colorPrimary_westeros`）。

**子：** は親と同じ動作とUIコンポーネントを提示しますが、UIコンポーネントの外観と位置を変更できます。 子ビューは、独自のレイアウトXMLファイルで視覚的な変更を指定します。親のビュークラスとスクリーンレットクラスを継承します。 UIコンポーネントを追加または削除することはできません。 親はフルビューである必要があります。 子ビューの作成は、既存のビューに視覚的な変更のみを行う必要がある場合に理想的です。 たとえば、ログインスクリーンレットのデフォルトビューの子ビューを作成して、標準のテキストボックスの新しい位置とサイズを設定できます。

**拡張：** は親ビューの動作と外観を継承しますが、両方を変更および追加できます。 これを行うには、カスタムビュークラスと新しいレイアウトXMLファイルを作成します。 拡張ビューは、スクリーンレット、リスナー、インタラクターなど、親ビューの他のすべてのクラスを継承します。それらのいずれかをカスタマイズする必要がある場合は、フルビューを作成してカスタマイズする必要があります。 拡張ビューの親はフルビューである必要があります。 拡張ビューの作成は、既存のビューのUIコンポーネントを追加、削除、または変更するのに理想的です。 たとえば、ログインスクリーンレットのデフォルトビューを拡張して、ユーザー名とパスワードのフィールドに異なるUIコンポーネントを表示できます。

**完全：** は完全なスタンドアロンビューを提供します。 別のビューのUIコンポーネントまたは動作を継承しません。 したがって、フルビューを作成するときは、そのスクリーンレットクラス、ビュークラス、およびレイアウトXMLファイルを作成する必要があります。 別のビューを継承する必要がない場合、またはリスナーをカスタマイズしたり、カスタムインタラクターを呼び出してスクリーンレットのコア動作を変更する必要がある場合は、フルビューを作成する必要があります。 たとえば、別のLiferayポータルインスタンスを呼び出すための新しいインタラクターでフルビューを実装できます。 デフォルトビューはフルビューです。

![図1：この図は、Liferay Screens for Androidのビューレイヤーを示しています。](../../../../images/screens-android-architecture-04.png)

Liferayスクリーンビューは、複数のスクリーンレットのビューを含む *ビューセット* 整理されています。 Liferayの利用可能なビューセットは次のとおりです。

  - [*デフォルト：*](https://github.com/liferay/liferay-screens/tree/master/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews) Liferayが提供する必須のビューセット。 レイアウトIDが指定されていない場合、またはレイアウトIDを持つビューが見つからない場合、スクリーンレットによって使用されます。 デフォルトビューセットは、標準のUIコンポーネントを備えた、ニュートラルでフラットな白と青のデザインを使用します。 たとえば、 [ログインスクリーンレット](https://github.com/liferay/liferay-screens/tree/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login)では、デフォルトビューはユーザー名とパスワードに標準のテキストボックスを使用しますが、テキストボックスはデフォルトビューの平らな白と青のデザインでスタイル設定されています。 コンポーネントの色、位置、サイズなど、このビューセットのプロパティをカスタマイズできます。 特定の値については、デフォルトビューセットの [`styles.xml`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/res/values/styles.xml) ファイルを参照してください。 デフォルトビューセットにはフルビューが含まれているため、それらを使用して独自のカスタムの子ビューおよび拡張ビューを作成できます。

  - [*素材：*](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material) Androidの [素材設計ガイドライン](https://developer.android.com/design/material/index.html)準拠するビューを含むビューセット。

  - [*Westeros：*](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros) [Bank of Westeros](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) サンプルアプリのビューを含むビューセット。

ビューの作成またはカスタマイズの詳細については、チュートリアル [Androidビューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views)を参照してください。

すばらしいです\！ これで、Liferay Screens for Androidの構成方法がわかりました。 ただし、先に進む前に知っておくべきことがあります。スクリーンレットとAndroidライフサイクルとの相互作用です。

## 関連トピック

[高レベルのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/high-level-architecture)

[コア層](/docs/7-1/tutorials/-/knowledge_base/t/core-layer)

[スクリーンレットレイヤー](/docs/7-1/tutorials/-/knowledge_base/t/screenlet-layer)

[スクリーンレットのライフサイクル](/docs/7-1/tutorials/-/knowledge_base/t/screenlet-lifecycle)
