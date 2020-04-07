---
header-id: theme-layer-of-liferay-screens-for-ios
---

# iOS向けLiferay画面のテーマレイヤー

[TOC levels=1-4]

テーマレイヤーを使用すると、開発者はスクリーンレットのルックアンドフィールを設定できます。 Screenletプロパティ `themeName` は、読み込むテーマを決定します。 これは、Interface Builderのスクリーンレットの *テーマ名* フィールドで設定できます。 テーマは、スクリーンレットの動作のビュークラスとUIのXIBファイルで構成されます。 これらのコンポーネントの1つ以上を別のテーマから継承することにより、異なるテーマ *タイプ* により、スクリーンレットのUIデザインと動作をさまざまなレベルで制御できます。

![図1：iOS向けLiferay画面のテーマレイヤー。](../../../../images/screens-ios-architecture-04.png)

テーマにはいくつかの種類があります。

  - **デフォルトテーマ：** Liferayが提供する標準テーマ。 他のテーマを作成するためのテンプレートとして、または他のテーマの親テーマとして使用できます。 各スクリーンレットの各テーマには、Viewクラスが必要です。 デフォルトテーマのViewクラスの名前は `MyScreenletView_default`で、 `MyScreenlet` はスクリーンレットの名前です。 このクラスは、iOSの標準 `ViewController` に似ています。標準の `@IBAction` および `@IBOutlet`を使用してUIイベントを受信および処理します。 Viewクラスは通常、XIBファイルを使用してUIコンポーネントを構築します。 このXIBファイルはクラスにバインドされています。

  - **子テーマ：** 親テーマと同じUIコンポーネントを表示しますが、UIコンポーネントの外観と位置を変更できます。 子テーマは、独自のXIBファイルで視覚的な変更を指定します。 UIコンポーネントを追加または削除することはできません。 図では、子テーマはデフォルトテーマを継承しています。 子テーマの作成は、既存のテーマに視覚的な変更を加えるだけでよい場合に理想的です。 たとえば、既存のコードを追加または上書きせずに、ログインスクリーンレットのデフォルトテーマの標準テキストボックスの新しい位置とサイズを設定する子テーマを作成できます。

  - **完全：** 完全なスタンドアロンテーマを提供します。 親テーマはなく、スクリーンレットに固有の動作と外観を実装します。 そのViewクラスは、Screensの `BaseScreenletView` クラスを拡張し、Screenletのビューモデルプロトコルに準拠する必要があります。 また、XIBファイルで新しいUIを指定する必要があります。 完全なテーマの例については、 [デフォルトテーマ](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Default) を参照してください。

  - **拡張：** 親テーマの動作と外観を継承しますが、両方にコードを変更および追加できます。 そのためには、新しいXIBファイルと、親テーマのViewクラスを拡張するカスタムViewクラスを作成します。 図では、拡張テーマは完全テーマを継承し、スクリーンレットのビュークラスを拡張しています。 拡張テーマの例については、 [Flat7テーマ](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Flat7) を参照してください。

Liferayスクリーンのテーマは、いくつかのスクリーンレットのテーマを含むセットに整理されています。 Liferayの利用可能なテーマセットは次のとおりです。

  - [**デフォルト：**](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Default) Liferayが提供する必須のテーマセット。 Screenletの `themeName` が指定されていないか無効な場合に使用されます。 デフォルトのテーマは、標準のUIコンポーネントを備えた中立でフラットな白と青のデザインを使用しています。 たとえば、 [ログインスクリーンレット](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Auth/LoginScreenlet) は、ユーザー名とパスワードフィールドに標準のテキストボックスを使用しますが、デフォルトテーマのフラットな白と青のデザインを使用します。

  - [**Flat7：**](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Flat7) 黒と緑のフラットなデザインと、丸いエッジを持つUIコンポーネントを使用するテーマのコレクション。 それらは拡張テーマです。

  - [**Westeros：**](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/WesterosBank/Theme) [Bank of Westeros](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/WesterosBank/App) サンプルアプリのテーマ。

テーマの作成の詳細については、チュートリアルシリーズ [iOSテーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)を参照してください。
