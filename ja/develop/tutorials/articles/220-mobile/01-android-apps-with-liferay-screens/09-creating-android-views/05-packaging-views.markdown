---
header-id: packaging-your-views
---

# ビューをパッケージ化する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ビューを配布または再利用する場合は、ビューをモジュールにパッケージ化してから、アプリのプロジェクト依存関係として追加する必要があります。 これを行うには、新しい [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/build.gradle) ファイルのテンプレートとして [material](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material) サブプロジェクトを使用します。

パッケージ化されたビューを使用するには、 [`settings.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/settings.gradle) ファイルで場所を指定して、モジュールをプロジェクトにインポートする必要があります。 Bank of Westerosおよびtest-appプロジェクトは、それぞれカスタムビュー `westeros` および `material`を使用します。 これらのプロジェクトは、プロジェクト内の独立したビューを使用した例です。

ビューを再配布し、他のユーザーがそれを使用できるようにする場合は、jCenterまたはMaven Centralにアップロードできます。 サンプルの [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/build.gradle) ファイルでは、bintray apiキーを入力した後、 `gradlew bintrayupload` を実行してプロジェクトをjCenterにアップロードできます。 終了したら、リポジトリ、アーティファクト、グループID、バージョンをGradleファイルに追加することで、Androidの依存関係と同様に誰でもビューを使用できます。

## 関連トピック

[Androidビューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views)
