---
header-id: using-themes-in-xamarin-ios
---

# Xamarin.iOSでテーマを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

*テーマ* Xamarin.iOSではと類似している *閲覧数* Xamarin.Androidインチ ビューと同様に、テーマを使用すると、スクリーンレットのコア機能に依存せずにスクリーンレットの外観を設定できます。 Liferayのスクリーンレットにはいくつかのテーマがあり、Liferayとコミュニティによってさらに多くのテーマが開発されています。 [Screenletリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios) は、Screensに含まれる各Screenletで利用可能なテーマがリストされています。 このチュートリアルでは、Xamarin.iOSでテーマを使用する方法を示します。

## テーマのインストールと使用

テーマをインストールして使用するには、次の手順に従います。

1.  テーマがNuGet依存関係としてパッケージ化されている場合、NuGetを介してプロジェクトにインストールできます。 これを行うには、プロジェクトの *パッケージ* フォルダーを右クリックし、 *パッケージの追加...*選択します。 次に、テーマを検索してインストールします。 テーマがNuGetで使用できない場合は、テーマのフォルダーをプロジェクトに直接ドラッグアンドドロップできます。

2.  インストールされたテーマを使用するには、その名前を、スクリーンレットのデリゲートを実装するView ControllerのScreenletインスタンスの `ThemeName` プロパティに設定します。 すべてのスクリーンレットは、このプロパティを `BaseScreenlet`から継承します。 たとえば、次のコードは、ログインスクリーンレットの `ThemeName` プロパティをMaterial Themeに設定します。
   
        loginScreenlet.ThemeName = "material"

    このプロパティを設定しない場合、または無効または欠落しているテーマを入力した場合、スクリーンレットはデフォルトのテーマを使用します。 各スクリーンレットの利用可能なテーマは、スクリーンレットの [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios)の *テーマ* セクションにリストされてい* 。</p></li> </ol>

素晴らしい、それだけです！ これで、テーマを使用してXamarin.iOSアプリでスクリーンレットをドレスアップする方法がわかりました。

## 関連トピック

[Liferay画面用のXamarinプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-xamarin-projects-for-liferay-screens)

[Xamarinアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-xamarin-apps)

[Xamarin.Androidでビューを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-xamarin-android)

[Xamarinのビューとテーマを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-xamarin-views-and-themes)

[XamarinのLiferay画面のトラブルシューティングとFAQ](/docs/7-1/tutorials/-/knowledge_base/t/liferay-screens-for-xamarin-troubleshooting-and-faqs)
