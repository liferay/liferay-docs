---
header-id: creating-ios-list-screenlets
---

# iOSリストスクリーンレットの作成

[TOC levels=1-4]

モバイルアプリがリストを表示することは非常に一般的です。 Liferay Screens</a>、 [Asset List Screenlet](/docs/7-1/reference/-/knowledge_base/r/assetlistscreenlet-for-ios) および [DDL List Screenlet ](/docs/7-1/reference/-/knowledge_base/r/ddllistscreenlet-for-ios)をそれぞれ使用して、iOSアプリでアセットリストとDDLリストを表示できます。 画面には、Webコンテンツ記事、画像など、他のLiferayエンティティのリストを表示するリストスクリーンレットも含まれています。 [スクリーンレットリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios) は、Liferayスクリーンに含まれるすべてのスクリーンレットをリストしています。 リストに表示するエンティティのリストスクリーンレットがない場合は、独自のリストスクリーンレットを作成する必要があります。 リストスクリーンレットは、Liferayインスタンスの任意のエンティティを表示できます。 たとえば、 `User`などの標準Liferayエンティティ、またはカスタムLiferayアプリのカスタムエンティティを表示するリストスクリーンレットを作成できます。

このセクションのチュートリアルでは、サンプルのブックマークリストスクリーンレットのコードを使用して、独自のリストスクリーンレットを作成する方法を示します。 このスクリーンレットには、Liferayのブックマークポートレットのブックマークのリストが表示されます。 このスクリーンレットの完全なコード [は、GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/BookmarkListScreenlet)ます。

このチュートリアルではリストScreenletの作成に焦点を当てているため、Screenletの一般的な概念とコンポーネントについては説明していません。 したがって、始める前に、以下をお読みください。

  - [iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)
  - [スクリーンレットで複数のテーマをサポートする](/docs/7-1/tutorials/-/knowledge_base/t/supporting-multiple-themes-in-your-ios-screenlet)
  - [スクリーンレットでコネクタを作成して使用する ](/docs/7-1/tutorials/-/knowledge_base/t/create-and-use-a-connector-with-your-screenlet)
  - [スクリーンレットデリゲートを追加する](/docs/7-1/tutorials/-/knowledge_base/t/add-a-screenlet-delegate)
  - [スクリーンレットのモデルクラスの作成と使用](/docs/7-1/tutorials/-/knowledge_base/t/creating-and-using-your-screenlets-model-class)

次の手順に従って、リストスクリーンレットを作成します。

1.  Modelクラスの作成
2.  テーマを作成する
3.  コネクターの作成
4.  インタラクターの作成
5.  デリゲートを作成する
6.  Screenletクラスの作成

ただし、最初に、リストのスクリーンレットでページネーションがどのように機能するかを理解する必要があります。

## ページネーション

ユーザーがアイテムの大きなリストをスムーズにスクロールできるように、リストスクリーンレットは [流paなページネーション](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination)サポートし

 。 このサポートは、リストスクリーンレットフレームワークに組み込まれています。 これは、リストスクリーンレットを作成するときに表示されます。</p> 



## モデルクラスの作成

モデルクラスは、それぞれの `[String：AnyObject]` エンティティScreensが、対応するLiferayエンティティを表すモデルオブジェクトに変換することを思い出してください。 お使いのモデルクラスを作成する手順については、チュートリアルを参照 [あなたのScreenletのモデルクラスの作成と使用](/docs/7-1/tutorials/-/knowledge_base/t/creating-and-using-your-screenlets-model-class)。 このチュートリアルのモデルクラスの例は、ブックマークリストスクリーンレットのものと同じです。

次に、リストScreenletのテーマを作成します。
