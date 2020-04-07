---
header-id: creating-android-list-screenlets
---

# Androidリストスクリーンレットの作成

[TOC levels=1-4]

モバイルアプリがリストを表示することは非常に一般的です。 Liferay Screensを使用すると、 [Asset List Screenlet](/docs/7-1/reference/-/knowledge_base/r/assetlistscreenlet-for-android) および [DDL List Screenlet](/docs/7-1/reference/-/knowledge_base/r/ddllistscreenlet-for-android)をそれぞれ使用して、Androidアプリで資産リストとDDLリストを表示できます。 画面には、Webコンテンツ記事、画像など、他のLiferayエンティティのリストを表示するリストスクリーンレットも含まれています。 [スクリーンレットリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) は、Liferayスクリーンに含まれるすべてのスクリーンレットをリストしています。 リストに表示するエンティティのリストスクリーンレットがない場合は、独自のスクリーンレットを作成する必要があります。 リストスクリーンレットは、Liferayインスタンスの任意のエンティティを表示できます。 たとえば、 `User`などの標準Liferayエンティティ、またはカスタムLiferayアプリのカスタムエンティティを表示するリストスクリーンレットを作成できます。

このチュートリアルでは、サンプルのブックマークリストスクリーンレットのコードを使用して、独自のリストスクリーンレットを作成する方法を示します。 このスクリーンレットには、Liferayのブックマークポートレットのブックマークのリストが表示されます。 このスクリーンレットの完全なコード [は、GitHub](https://github.com/liferay/liferay-screens/tree/master/android/samples/listbookmarkscreenlet)ます。

このチュートリアルではリストScreenletの作成に焦点を当てているため、Screenletの一般的な概念とコンポーネントについては説明していません。 したがって、始める前に、次のチュートリアルを読む必要があります。

  - [スクリーンアーキテクチャのチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-android)
  - [基本的なスクリーンレット作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-screenlets)

次の手順に従って、リストスクリーンレットを作成します。

1.  [モデルクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-model-class)
2.  [ビューを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-view)
3.  [インタラクターの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor-0)
4.  [スクリーンレットクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-screenlet-class-0)

ただし、最初に、リストのスクリーンレットでページネーションがどのように機能するかを理解する必要があります。

## ページネーション

ユーザーがアイテムの大きなリストをスムーズにスクロールできるように、リストスクリーンレットは流なページネーションをサポートします。 このサポートは、リストスクリーンレットフレームワークに組み込まれています。 これは、リストスクリーンレットを作成するときに表示されます。

これで開始する準備ができました\！
