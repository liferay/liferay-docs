---
header-id: android-apps-with-liferay-screens
---

# Liferayスクリーンを備えたAndroidアプリ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensは、Liferayを使用するネイティブモバイルアプリの開発を高速化し、簡素化します。 その力は、その *スクリーンレット*ます。 スクリーンレットは、Liferayポータルのコンテンツと [サービス](/docs/7-1/tutorials/-/knowledge_base/t/web-services)を活用するためにネイティブアプリに挿入する視覚的なコンポーネントです。 Androidでは、ポータルへのログイン、アカウントの作成、フォームの送信、コンテンツの表示などにスクリーンレットを使用できます。 アプリでは任意の数のスクリーンレットを使用できます。これらは独立しているため、モジュール形式で使用できます。 Androidのスクリーンレットは、エレガントなユーザーインターフェイスを実装するプラグ可能な *ビュー* でUIの柔軟性も提供します。 Liferayの [Android](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) Screenletsのリファレンスドキュメントでは、各Screenletの機能とビューについて説明しています。

「これらのスクリーンレットは火曜日のタコス以来の最高のもののように聞こえますが、アプリのUIに収まらない場合はどうでしょうか？ 彼らが私が望むように正確に振る舞わない場合はどうなりますか？ やりたいことのスクリーンレットがない場合はどうなりますか？」フレットnot \！ UIと動作を変更または拡張することにより、ニーズに合わせてスクリーンレットをカスタマイズできます。 独自のスクリーンレットを作成することもできます\！ さらに、Screensは既存のAndroidプロジェクトとシームレスに統合されます。

![図1：Liferay Screensサインアップスクリーンレットを使用するアプリです。](../../../images/screens-android-intro.png)

スクリーンレットは、 [Liferay Mobile SDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview) を利用してサーバー呼び出しを行います。 Mobile SDKは、Liferay JSON APIの上にある低レベルのレイヤーです。 独自のスクリーンレットを作成するには、Liferayの [リモートサービス](/docs/7-1/tutorials/-/knowledge_base/t/web-services)慣れる必要があります。 既存のスクリーンレットがニーズを満たさない場合は、既存のスクリーンレットのカスタマイズ、スクリーンレットの作成、またはMobile SDKの直接使用を検討してください。 スクリーンレットの作成には、モバイルSDK呼び出しの記述とスクリーンレットの構築が含まれます。実装を再利用または配布する予定がない場合は、スクリーンレットの作成を控え、代わりに [Mobile SDK](/docs/7-1/tutorials/-/knowledge_base/t/mobile-sdk)で作業することをお勧めします。 ただし、既存のScreenletをアプリに統合する利点は、Mobile SDKの詳細がユーザーから抽象化されることです。

これらのチュートリアルでは、Android用スクリーンレットの使用、カスタマイズ、作成、配布の方法を示します。 ビューの作成方法も示します。 Liferay Screensアーキテクチャの要点を説明するチュートリアルもあります。 どれだけ深く行っても、スクリーンレットをすぐに使用できます。 Liferayスクリーンを使用するために [Androidプロジェクト](/docs/7-1/tutorials/-/knowledge_base/t/preparing-android-projects-for-liferay-screens) を準備することから始めます。
