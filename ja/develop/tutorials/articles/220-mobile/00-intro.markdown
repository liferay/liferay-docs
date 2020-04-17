---
header-id: mobile
---

# モバイル

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferayは、Liferayインスタンスで動作するネイティブAndroidおよびiOSアプリを作成する2つの方法を提供します。LiferayScreensとLiferay Mobile SDKです。 Liferay Screensは、 *Screenlets*と呼ばれるすぐに使用できるコンポーネントを介してこれを行います。 Screenletsには、Liferayインスタンスを呼び出すために必要なコード（および完全なUI）が既に含まれているため、必要なことはAndroidアプリまたはiOSアプリにそれらを挿入して構成するだけです。 Screensは、ログイン、Webコンテンツの表示、DDLレコードの追加などの一般的なタスク用のスクリーンレットを提供します。 特定のニーズに合わせて各スクリーンレットをカスタマイズしたり、独自のスクリーンレットを作成することもできます。 舞台裏では、スクリーンレットはLiferay Mobile SDKを使用してLiferayのリモートサービスを呼び出します。

Liferay Mobile SDKは、Liferayのリモートサービスを手動で呼び出すことができる低レベルのツールです。 Mobile SDKを使用して独自のスクリーンレットを作成するか、Liferayのリモートサービスをスクリーンから独立して呼び出す必要があります。 ほとんどの場合、スクリーンを使用すると時間と労力が節約されることがわかります。 たとえば、Mobile SDKを使用してアプリにログインを実装できますが、Screensは既にログインスクリーンレットを介してこれを提供しています。 ただし、Mobile SDKを使用することが理にかなっている場合があります。 たとえば、1つ以上のLiferayリモートサービスを呼び出す必要があるが、アプリのUIがこれを反映する必要がない場合、この目的でスクリーンレットを使用することは意味がありません。 各スクリーンレットにはUIが含まれている必要があります。

特定のニーズに関係なく、LiferayはLiferay ScreensとLiferay Mobile SDKでカバーしています。 チュートリアルのこのセクションには、両方の使用方法を示す以下のセクションが含まれています。

  - [Liferayスクリーンを備えたAndroidアプリ](/docs/7-1/tutorials/-/knowledge_base/t/android-apps-with-liferay-screens)

  - [Liferayスクリーンを備えたiOSアプリ](/docs/7-1/tutorials/-/knowledge_base/t/ios-apps-with-liferay-screens)

  - [Liferay画面でのXamarinの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-xamarin-with-liferay-screens)

  - [Liferay Mobile SDK](/docs/7-1/tutorials/-/knowledge_base/t/mobile-sdk)

モバイルの第一人者になるために冒険しましょう！
