---
header-id: creating-an-ios-full-theme
---

# iOSフルテーマの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

フルテーマは、親テーマを使用せずに、スクリーンレットに固有の動作と外観を実装します。 そのViewクラスは、Screensの [`BaseScreenletView`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift) を継承し、ScreenletのView Modelプロトコルに準拠する必要があります。 また、XIBファイルで新しいUIを指定する必要があります。 完全なテーマを作成するときに、チュートリアル [iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets) を参照して、これらのクラスを作成する方法を学ぶことができます。

完全なテーマを作成するには、次の手順に従います。

1.  新しいXIBファイルを作成し、Interface Builderを使用してUIを構築します。 慣例により、 *FooScreenletView* という名前のViewクラスと *BarTheme* という名前のテーマを持つScreenletのXIBファイルは `FooScreenletView_barTheme.xib`という名前にする必要があります。 スクリーンレットのデフォルトテーマのXIBファイルをテンプレートとして使用できます。

    ![図1：ログインScreenletのためのこの完全なテーマは、ユーザー名を入力するためのテキストフィールドを含む、パスワードのUDIDを使用し、同じで*ボタンで*ログインを追加します <code>restorationIdentifier</code> デフォルトのテーマとして。](../../../../images/screens-ios-xcode-full-theme.png)

2.  テーマ用に、作成したXIBファイルにちなんで名前を付けた新しいViewクラスを作成します。 テンプレートとして、スクリーンレットのデフォルトテーマのViewクラスを使用できます。 新しいViewクラスは、 [`BaseScreenletView`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift) を継承し、Screenletの `* ScreenletViewModel` プロトコルに準拠し、対応するゲッターとセッターを実装する必要があります。 また、UIコンポーネントをバインドするために必要なすべての `@IBOutlet` プロパティまたは `@IBAction` メソッドを追加する必要があります。

3.  テーマの新しいViewクラスをXIBファイルのカスタムクラスとして設定し、 `@IBOutlet` および `@IBAction` アクションをクラスにバインドします。

テーマが完成したので、オプションでパッケージ</a> を たり、それを使用して [を開始したりできます](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-ios-screenlets)。 フルテーマは、子テーマおよび拡張テーマの親として機能できることに注意してください。</p>
