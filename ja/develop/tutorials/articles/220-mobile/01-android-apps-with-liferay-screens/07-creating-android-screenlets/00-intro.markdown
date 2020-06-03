---
header-id: creating-android-screenlets
---

# Androidスクリーンレットの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[Liferay Screensに付属するScreenlets](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) は、Liferayを使用するモバイルアプリの一般的なユースケースをカバーしています。 ユーザーを認証し、ダイナミックデータリストとやり取りし、アセットを表示します。 しかし、どのような何Screenletがそこにいない場合は *、あなたの* の特定のユースケース？ 汗かいていない\！ 独自に作成できます。 拡張性は、Liferay Screensの重要な強みです。

このチュートリアルでは、独自のスクリーンレットを作成する方法について説明します。 例として、LiferayのBookmarksポートレットにブックマークを保存するサンプル [Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/android/samples/addbookmarkscreenlet)コードを参照します。

通常、次の手順を使用してスクリーンレットを作成します。

1.  [スクリーンレットの場所を決定します](#determining-your-screenlets-location)。 スクリーンレットを作成する場所は、使用方法によって異なります。

2.  [スクリーンレットのUI（そのビュー）](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-ui)作成します。 すべての情報は、あなたのScreenletのためのビューを作成する必要があり、このチュートリアルでプレゼントしますが、最初にどのように勉強したいことがあり [ビューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views)。 一般的なビューの詳細については、 [のscreenletsでビューを使用してチュートリアルを](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-android-screenlets)。

3.  [スクリーンレットのインタラクターを作成します](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor)。 インタラクターは、サーバー呼び出しを行うScreenletコンポーネントです。

4.  [スクリーンレットの属性を定義します](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-attributes)。 これらは、スクリーンレットのXMLを挿入するときにアプリ開発者が設定できるXML属性です。 これらの属性は、スクリーンレットの動作の側面を制御します。 Screenletクラスのこれらの属性に機能を追加します。

5.  [Screenletクラス](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-screenlet-class)作成します。 Screenletクラスは、Screenletの中心的なコンポーネントです。 Screenletの動作を制御し、アプリ開発者がScreenletを挿入するときに対話するコンポーネントです。

スクリーンレットを構成するコンポーネントを理解するには、まずAndroid</a>Liferay Screensの

アーキテクチャを学ぶ必要があります。</p> 

さらに苦労せずに、スクリーンレットの作成を始めましょう！



## スクリーンレットの場所の特定

スクリーンレットを作成する場所は、スクリーンレットの使用方法によって異なります。 Screenletを別のアプリで再利用する予定がない場合、または再配布したくない場合は、Androidアプリプロジェクト内の新しいパッケージで作成します。 これにより、インポートしたすべてのビューセットに加えて、Liferay Screensのコアを参照およびアクセスできます。

別のアプリでスクリーンレットを再利用する場合は、新しいAndroidアプリケーションモジュールで作成します。 チュートリアル [Android Screenletsのパッケージ化](/docs/7-1/tutorials/-/knowledge_base/t/packaging-your-screenlets) では、これを行う方法について説明しています。 スクリーンレットのプロジェクトが準備できたら、スクリーンレットのUIを作成することから始めます。
