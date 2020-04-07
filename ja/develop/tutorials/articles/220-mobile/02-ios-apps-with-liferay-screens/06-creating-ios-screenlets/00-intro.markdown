---
header-id: creating-ios-screenlets
---

# iOSスクリーンレットの作成

[TOC levels=1-4]

[組み込みのスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios) は、Liferayを使用するモバイルアプリの一般的なユースケースをカバーします。 ユーザーを認証し、ダイナミックデータリストとやり取りし、アセットを表示します。 ただし、</em> ユースケース *のスクリーンレットがない場合はどうなりますか？ 問題ない\！ 独自に作成できます。 拡張性は、Liferay Screensの重要な強みです。</p>

このチュートリアルシリーズでは、独自のスクリーンレットを作成する方法について説明します。 例として、LiferayのBookmarksポートレットにブックマークを保存するサンプル [Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark)コードを参照します。

通常、次の手順を使用してスクリーンレットを作成します。

1.  **スクリーンレットの計画：** スクリーンレットの機能とユースケースにより、スクリーンレットの作成場所と呼び出すポータルサービスが決まります。

2.  **スクリーンレットのUI（テーマ）の作成：** これらのチュートリアルでは、スクリーンレットのテーマを作成するために必要なすべての情報を示しますが、最初に [テーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)手順を学習することをお勧めします。 一般的なテーマの詳細については、以下を参照してください [テーマの使用に関するチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-ios-screenlets)。

3.  **スクリーンレットのインターアクターを作成します** インターアクターは、サーバー呼び出しを行うスクリーンレットコンポーネントです。

4.  **Screenletクラスを作成します：** ザ・ScreenletクラスはScreenletの中心的なコンポーネントです。 スクリーンレットの動作を制御し、アプリ開発者がスクリーンレットを使用するときに対話するコンポーネントです。

以下のチュートリアルでは、これらの手順を順を追って説明します。 始める前に、Liferay Screens</a>アーキテクチャに精通していることを確認してください。</p>
