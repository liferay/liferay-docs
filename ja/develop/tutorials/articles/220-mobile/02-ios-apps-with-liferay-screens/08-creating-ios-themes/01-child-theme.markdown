---
header-id: creating-an-ios-child-theme
---

# iOSの子テーマの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

子テーマでは、親テーマの動作とUIコンポーネントを活用しますが、UIコンポーネントの外観と位置を変更できます。 コンポーネントを追加または削除することはできず、親テーマは完全なテーマである必要があることに注意してください。 子テーマは、独自のXIBファイルで視覚的な変更を提示し、親のViewクラスを継承します。

たとえば、図1の子テーマは、 [ログインスクリーンレットの](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Auth/LoginScreenlet) デフォルトテーマと同じUIコンポーネントを示していますが、より大きな画面を持つデバイスで表示するためにそれらを拡大しています。

![図1：UIコンポーネントは、子テーマのXIBファイルの例で拡大されています。](../../../../images/screens-ios-xcode-child-theme.png)

次の手順に従って、子テーマを作成できます。

1.  Xcodeで、ScreenletのViewクラスとテーマにちなんで命名された新しいXIBファイルを作成します。 慣例により、 *FooScreenletView* という名前のViewクラスと *BarTheme* という名前のテーマを持つScreenletのXIBファイルは `FooScreenletView_barTheme.xib`という名前にする必要があります。 親テーマのXIBファイルのコンテンツを、新しいXIBファイルの基盤として使用できます。 新しいXIBでは、UIコンポーネントの視覚的プロパティ（位置やサイズなど）を変更できます。 ただし、XIBファイルのカスタムクラス、アウトレット接続、または `restoreIdentifier`変更しないでください。これらは親のXIBファイルのものと一致する必要があります。

    | **注：** XIBファイル名は、テーマのXcode名として機能します。 たとえば、|図1のテーマは、ログインスクリーンレットのデフォルトテーマを継承しています。 Viewクラス `LoginView_default`を使用します。 新しい子テーマの名前は| *大* はスクリーンレットのUIコンポーネントを拡大することを目的としているため。 で| Xcode、テーマ名 *large*割り当てられています。 XIBファイルの名前は| `LoginView_large.xib`、ログインスクリーンレットのViewクラスと|テーマのXcode名。

あなたは、必要に応じてできる [パッケージ](/docs/7-1/tutorials/-/knowledge_base/t/packaging-ios-themes) あなたのテーマおよび/または開始 [それを使用して](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-ios-screenlets)。
