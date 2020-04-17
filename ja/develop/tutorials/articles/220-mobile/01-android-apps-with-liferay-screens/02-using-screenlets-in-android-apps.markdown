---
header-id: using-screenlets-in-android-apps
---

# Androidアプリでスクリーンレットを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

あなたがしたらあなたはのscreenletsを使用して起動することができます [準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-android-projects-for-liferay-screens) Liferayの画面を使用するようにプロジェクトを。 多数のLiferayスクリーンレットが利用可能であり、それらはスクリーンレット [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android)説明されています。 スクリーンレットを使用するのは非常に簡単です。 このチュートリアルでは、Androidアプリにスクリーンレットを挿入して構成する方法を示します。 あなたはすぐにスクリーンレットマスターになります\！ <iframe width="560" height="315" src="https://www.youtube.com/embed/TZ09fbV9UuU" frameborder="0" allowfullscreen mark="crwd-mark"></iframe>

まず、Android Studioのビジュアルレイアウトエディターまたはお気に入りのエディターで、アプリのレイアウトXMLファイルを開き、アクティビティまたはフラグメントレイアウトにスクリーンレットを挿入します。 たとえば、次のスクリーンショットは、アクティビティの `FrameLayout`挿入されたログインスクリーンレットを示しています。

![図1：Android Studioのアクティビティのレイアウトにあるログインスクリーンレットです。](../../../images/screens-android-insert-screenlet.png)

次に、スクリーンレットの属性を設定します。 Liferayスクリーンレットの場合、スクリーンレット [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) を参照して、スクリーンレットの必須およびサポートされる属性を確認してください。 このスクリーンショットは、設定されるログインスクリーンレットの属性を示しています。

![図2：アプリのレイアウトXMLファイルを介してスクリーンレットの属性を設定できます。](../../../images/screens-android-screenlet-attributes.png)

Screenletがトリガーするイベントをリッスンするようにアプリを構成するには、アクティビティまたはフラグメントクラスにScreenletのリスナーインターフェイスを実装します。 Screenletのドキュメントを参照して、リスナーインターフェースを学習します。 次に、アクティビティまたはフラグメントをスクリーンレットのリスナーとして登録します。 たとえば、以下のスクリーンショットのアクティビティクラスは、Login Screenletの `LoginListener` インターフェイスを実装することを宣言し、Screenletのイベントをリッスンするように自身を登録します。

![図3：アクティビティクラスまたはフラグメントクラスにScreenletのリスナーを実装します。](../../../images/screens-android-screenlet-listener.png)

Screenletのリスナーインターフェイスに必要なすべてのメソッドを実装してください。 Liferayのスクリーンレットの場合、リスナーメソッドは各スクリーンレットの [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android)リストされています。 これで完了です。 驚くばかり\！ これで、Androidアプリでスクリーンレットを使用する方法がわかりました。

**関連トピック**

[Liferay画面用のAndroidプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-android-projects-for-liferay-screens)

[Androidスクリーンレットでビューを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-android-screenlets)

[Androidスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-screenlets)

[iOSアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-ios-apps)
