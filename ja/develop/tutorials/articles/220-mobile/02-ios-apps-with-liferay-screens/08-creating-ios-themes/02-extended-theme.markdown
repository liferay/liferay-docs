---
header-id: creating-an-ios-extended-theme
---

# iOS拡張テーマの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

拡張テーマは、別のテーマのUIコンポーネントと動作を継承しますが、親テーマのViewクラスを拡張して新しいXIBファイルを作成することにより、追加または変更できます。 拡張テーマの親は完全テーマでなければなりません。 [Flat7テーマ](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Flat7) は拡張テーマです。

これらの手順は、拡張テーマを作成する方法を説明しています。

1.  Xcodeで、スクリーンレットのViewクラスとテーマにちなんで命名された新しいXIBファイルを作成します。 慣例により、 *FooScreenletView* という名前のViewクラスと *BarTheme* という名前のテーマを持つScreenletのXIBファイルは `FooScreenletView_barTheme.xib`という名前にする必要があります。 親テーマのXIBファイルをテンプレートとして使用できます。 Interface Builderを使用して、新しいXIBファイルでUIの変更をビルドします。

    ![図1：この例の拡張テーマのXIBファイルは、ユーザーがパスワードフィールド値を表示または非表示にできるスイッチを使用して、ログインポートレットのUIと動作を拡張します。](../../../../images/screens-ios-xcode-ext-theme.png)

2.  親テーマのViewクラスを拡張する新しいViewクラスを作成します。 このクラスには、作成したXIBファイルにちなんで名前を付ける必要があります。 親テーマのViewクラスの機能を追加またはオーバーライドできます。

3.  新しいViewクラスをテーマのXIBファイルのカスタムクラスとして設定します。 `@IBOutlet` または `@IBAction` アクションを追加した場合、それらをクラスにバインドします。

よくやった\！ あなたは、必要に応じてできる [パッケージ](/docs/7-1/tutorials/-/knowledge_base/t/packaging-ios-themes) あなたのテーマおよび/または開始 [それを使用して](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-ios-screenlets)。
