---
header-id: packaging-your-views
---

# ビューをパッケージ化する

[TOC levels=1-4]

ビューを配布または再利用する場合は、ビューをモジュールにパッケージ化してから、アプリのプロジェクト依存関係として追加する必要があります。 これを行うには、新しい [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/build.gradle) ファイルのテンプレートとして [material](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material) サブプロジェクトを使用します。

パッケージ化されたビューを使用するには、 [`settings.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/settings.gradle) ファイルで場所を指定して、モジュールをプロジェクトにインポートする必要があります。 Bank of Westerosおよびtest-appプロジェクトは、それぞれカスタムビュー `westeros` および `material`を使用します。 これらのプロジェクトは、プロジェクト内の独立したビューを使用した例です。

ビューを再配布し、他のユーザーがそれを使用できるようにする場合は、jCenterまたはMaven Centralにアップロードできます。 サンプルの [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/build.gradle) ファイルでは、bintray apiキーを入力した後、 `gradlew bintrayupload` を実行してプロジェクトをjCenterにアップロードできます。 終了したら、リポジトリ、アーティファクト、グループID、バージョンをGradleファイルに追加することで、Androidの依存関係と同様に誰でもビューを使用できます。

## 関連トピック

[Androidビューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views)
