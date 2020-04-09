---
header-id: ios-apps-with-liferay-screens
---

# Liferayスクリーンを備えたiOSアプリ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensは、Liferayを使用するネイティブモバイルアプリの開発を高速化し、簡素化します。 その力は、その *スクリーンレット*ます。 スクリーンレットは、@product@のコンテンツと [サービス](/docs/7-1/tutorials/-/knowledge_base/t/web-services)を活用するためにネイティブアプリに挿入する視覚的なコンポーネントです。 iOSでは、ポータルへのログイン、アカウントの作成、フォームの送信、コンテンツの表示などにスクリーンレットを使用できます。 アプリでは任意の数のスクリーンレットを使用できます。これらは独立しているため、モジュール形式で使用できます。 iOSのスクリーンレットは、エレガントなユーザーインターフェイスを実装するプラグ可能な *テーマ* でUIの柔軟性も提供します。 [参考資料](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios) のiOSのscreenletsためには、各Screenletの機能とテーマを説明しています。

「これらのスクリーンレットは火曜日のタコス以来の最高のもののように聞こえますが、アプリのUIに収まらない場合はどうでしょうか？ 彼らが私が望むように正確に振る舞わない場合はどうなりますか？ やりたいことのスクリーンレットがない場合はどうなりますか？」フレットnot \！ UIと動作を変更または拡張することにより、ニーズに合わせてスクリーンレットをカスタマイズできます。 独自のスクリーンレットを作成することもできます\！ さらに、Screensは既存のiOSプロジェクトとシームレスに統合されます。

![図1：Liferay画面サインアップスクリーンレットにより、ユーザーはポータルでアカウントを作成できます。](../../../images/screens-ios-intro.png)

スクリーンレットは、 [Liferay Mobile SDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview) を利用してサーバー呼び出しを行います。 Mobile SDKは、Liferay JSON APIの上にある低レベルのレイヤーです。 独自のスクリーンレットを作成するには、@product@の [Webサービス](/docs/7-1/tutorials/-/knowledge_base/t/web-services)精通する必要があります。 既存のスクリーンレットがニーズを満たさない場合は、既存のスクリーンレットのカスタマイズ、スクリーンレットの作成、またはMobile SDKの直接使用を検討してください。 スクリーンレットの作成には、モバイルSDK呼び出しの記述とスクリーンレットの構築が含まれます。実装を再利用または配布する予定がない場合は、スクリーンレットの作成を控え、代わりに [Mobile SDK](/docs/7-1/tutorials/-/knowledge_base/t/mobile-sdk)作業することをお勧めします。 ただし、既存のScreenletをアプリに統合する利点は、Mobile SDKの詳細がユーザーから抽象化されることです。

これらのチュートリアルでは、iOSスクリーンレットとそのテーマを使用、カスタマイズ、作成、配布する方法を示します。 Liferay Screensのアーキテクチャを説明するチュートリアルもあります。

開始するには、 [LifeiOS Screensを使用するiOSプロジェクト](/docs/7-1/tutorials/-/knowledge_base/t/preparing-ios-projects-for-liferay-screens) を準備します。
