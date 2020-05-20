---
header-id: using-liferay-push-in-android-apps
---

# AndroidアプリでLiferay Pushを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensは、Androidアプリでのプッシュ通知をサポートしています。 それらを使用するには、いくつかのAPIを構成し、プッシュ通知を使用および/または生成するようにアプリを変更する必要があります。 このチュートリアルでは、これらすべてを行う方法を示します。

## Liferayプッシュ通知を使用するための構成

最初のステップは、Google Cloud Messaging（[GCM](https://developers.google.com/cloud-messaging/)）を使用するようにGoogleプロジェクトを作成および構成することです。 また、プロジェクトのGCM APIを使用するようにLiferay Pushアプリを構成する必要があります。

次の手順に従って、クラウドメッセージングをサポートするGoogleプロジェクトを作成および構成します。

1.  [Google Cloud Messagingページ](https://developers.google.com/cloud-messaging/android/start/)、 *Get a Configuration File*クリックして構成ファイルを作成します。 表示される画面で、 *アプリ名* および *Androidパッケージ名*を設定し、[ *サービスの選択と構成の続行*]をクリックします。 次の画面で、 *Googleクラウドメッセージングを有効にする*クリックします。

2.  提示された *サーバーAPIキー* および *送信者ID* 値をコピーして保存します。 Liferayプッシュのプッシュ通知APIキーとしてこれらの値を後で使用する必要があります。

    ![図1：Liferay Pushを有効にするには、サーバーAPIキーと送信者IDが必要です。](../../../images/screens-android-push-project-and-server-key.png)

Googleプロジェクトをセットアップしたので、Liferayプッシュアプリを構成してプロジェクトのGCM APIを使用できます。 [Liferay Marketplace](http://www.liferay.com/marketplace)からLiferay Pushアプリをインストールします。 @product@インスタンスのコントロールパネルで、 *構成* → *システム設定*に移動し、 *その他* タブを選択してから、 *Androidプッシュ通知送信者*選択します。 プッシュ通知 *APIキー* を、Googleプロジェクトで生成したサーバーAPIキーの値に設定します。 通知の送信が失敗した場合の再試行回数を設定することもできます。

![図2：APIキーと再試行回数を@product@インスタンスに設定します。](../../../images/push-android-settings.png)

すばらしいです\！ これで、@product@インスタンスはAndroidアプリにプッシュ通知を送信する準備が整いました！

## プッシュ通知の受信と送信

[Android](https://github.com/liferay-mobile/liferay-push-android) 用のLiferayプッシュクライアントは、プッシュ通知を送受信するためのポータルへのデバイスの登録を合理化します。 以下の情報にはクライアントを使用するために必要な主な手順が含まれていますが、 [readme](https://github.com/liferay-mobile/liferay-push-android/blob/master/README.md) で詳細に説明しています。

AndroidアプリケーションのGradleビルドファイルで、 [はAndroid用のLiferayプッシュクライアントに新しい依存関係](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies) を追加します。

    dependencies {
    
        ...
    
        implementation 'com.liferay.mobile:liferay-push:1.2.1'
    }

アプリの `liferay-plugin-package.properties` ファイルで、プッシュ通知ポートレットが必須のデプロイメントコンテキストとして指定されていることを確認してください。

    required-deployment-contexts=\
        push-notifications-portlet\
        ...

次に、プッシュ通知のリスナーを登録する方法を学びます。

### プッシュ通知を受信する

最初に、以前に生成した `SENDER_ID` でGCMにデバイスを登録します。

    Session session = new SessionImpl(YOUR_SERVER, new BasicAuthentication(YOUR_USER, YOUR_PASSWORD));
    
    Push.with(session).register(this, YOUR_SENDER_ID);

Liferay Screensを使用してセッションを維持している場合は、新しいセッションを作成する代わりに、セッションを取得して使用できます。

    Push.with(SessionContext.createSessionFromCurrentSession()).register(this, YOUR_SENDER_ID);

あなたは、コードのこれらの例の行を使用している場合は、交換することを確認してください `YOUR_SERVER`、 `YOUR_USER`、 `your_passwordに`、及び `YOUR_SENDER_ID` 、独自の値で。

それでおしまい\！ リスナーを接続して、登録IDを保存したり、アクティビティに送信された通知を処理したりできます（ `onPushNotification（）`）。 受信者とサービスを登録して、通知を処理することもできます。 プッシュ通知の実装例として、 [プッシュ通知プロジェクト](https://github.com/liferay/liferay-screens/tree/master/android/samples/pushnotifications) を参照できます。

次に、プッシュ通知を送信する方法を学びます。

### プッシュ通知を送信する

Liferay Pushアプリを使用すると、アプリのユーザーに通知を簡単に送信できます。 ユーザーIDとメッセージの内容を指定できます。

    PushNotificationsDeviceLocalServiceUtil.sendPushNotification(userIds, content);

この [例のフックプラグイン](https://github.com/nhpatt/push-with-ddl-hook) 、ユーザーが新しいDDLレコードを作成するか、既存のDDLレコードを更新するたびにプッシュ通知を送信します。

アプリの `portal.properties` ファイルで、 [*value.object.listener*](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Value%20Object) プロパティを作成し、目的のリスナークラスのコンマ区切りリストに設定することにより、クラスのリスナーを追加できます。 `個のDDLRecord` オブジェクトのリスナー設定の例を次に示します。

    value.object.listener.com.liferay.portlet.dynamicdatalists.model.DDLRecord=com.liferay.push.hooks.DDLRecordModelListener

すばらしいです\！ これで、@product@からプッシュ通知を受信するようにAndroidアプリを構成する方法がわかりました。

このチュートリアルでは、プッシュ通知に対応するようにポータルを設定し、通知リスナーを登録し、プッシュ通知の送信を実装しました。 行く方法\！

## 関連トピック

[Liferay画面用のAndroidプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-android-projects-for-liferay-screens)

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)
