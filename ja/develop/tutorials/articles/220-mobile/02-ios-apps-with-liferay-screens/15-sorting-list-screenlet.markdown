---
header-id: sorting-your-list-screenlet
---

# リストスクリーンレットの並べ替え

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

リストScreenletを並べ替えるには、ポータルの *コンパレータクラス* をポイントする必要があります。 コンパレータクラスは、エンティティを並べ替えるロジックを実装します。 独自のコンパレータクラスを作成するか、ポータルに既に存在するコンパレータクラスを使用できます。 リストを並べ替えたら、セクションに分割できます。 このチュートリアルでは、コンパレータを使用してリストスクリーンレットを並べ替え、並べ替えられたリストのセクションを作成する方法を示します。

| **注：** 新しいコンパレーターを作成するには、|を拡張するクラスを作成する必要があります。ポータルの| [`OrderByComparator` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/OrderByComparator.html) |エンティティを型引数として使用します。 次に、以下のメソッドをオーバーライドする必要があります。ソートを実装します。 たとえば、ポータルの| [`EntryURLComparator` クラス](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/bookmarks/bookmarks-api/src/main/java/com/liferay/bookmarks/util/comparator/EntryURLComparator.java) | LiferayのブックマークポートレットのブックマークをURLでソートします。

最初に、コンパレータを使用してリストのスクリーンレットを並べ替える方法を学習します。

## コンパレータを使用する

コンパレーターを使用するには、リストScreenletの `obcClassName` プロパティをコンパレーターの完全修飾クラス名に設定する必要があります。 他のScreenletプロパティを設定するのと同じように、アプリにScreenletを挿入するときにInterface Builderでこれを行います。 たとえば、サンプルのブックマークリストスクリーンレットを設定して、ブックマークのリストをURLでソートするには、Interface Builderで *Obcクラス名* から *com.liferay.bookmarks.util.comparator.EntryURLComparator* を設定する必要があります。

![図1：コンパレーターを使用するには、Interface Builderで* Obc Class Name *プロパティをコンパレーターの完全修飾クラス名に設定します。](../../../images/screens-ios-obc-ib.png)

それでおしまい\！ すべてのリストのscreenletsが継承するが、注意することを `obcClassName` からプロパティを [ `BaseListScreenlet` クラス](https://github.com/liferay/liferay-screens/blob/develop/ios/Framework/Core/Base/BaseListScreenlet/BaseListScreenlet.swift)、リストScreenletも、このプロパティを使用してそのサービス呼び出しを行う必要があります。 Liferay Screensに含まれるスクリーンレットが `obcClassName` プロパティをサポートするリストを確認するには、 [スクリーンレットリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios) を参照してください。 また、@product@のコンパレータクラスはバージョン間で変更される可能性があります。 これらのコンパレータのいずれかを使用している場合は、@product@バージョンに一致するコンパレータを指定してください。

## リストのセクションを作成する

リストを類似の要素を含むセクションに分割することは、iOSアプリでは一般的です。 リストスクリーンレットでこれを行うには、まずコンパレーターを使用して、セクションの作成に使用する基準でリストを並べ替えます。 次に、リストScreenletのInteractorで [`BookmarkListPageLoadInteractor` クラスの](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/Interactor/BookmarkListPageLoadInteractor.swift) `sectionForRowObject` メソッドをオーバーライドします。 このメソッドは、リスト内の各アイテムに対して呼び出され、セクションにアイテムを配置するために必要な情報を返す必要があります。 例えば、 [サンプルブックマーク一覧Screenletのインターアクター](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/Interactor/BookmarkListPageLoadInteractor.swift) オーバーライド `sectionForRowObject` ホスト名によるグループ業績への方法：

    public override func sectionForRowObject(object: AnyObject) -> String? {
        guard let bookmark = object as? Bookmark else {
            return nil
        }
    
        let host = NSURL(string: bookmark.url)?.host?.lowercaseString
    
        return host?.stringByReplacingOccurrencesOfString("www.", withString: "")
    }

これは、前のセクションで説明したように、ブックマークリストスクリーンレットが `EntryURLComparator` ソートされている場合にのみ、予測可能な結果を生成することに注意してください。

そして、それだけです！ これで、リストスクリーンレットのリストを並べ替えてセクション化する方法がわかりました。

## 関連トピック

[iOSリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-list-screenlets)

[リストスクリーンレットでのカスタムセルの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-custom-cells-with-list-screenlets)

[リストスクリーンレットで複雑なリストを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-complex-lists-in-your-list-screenlet)

[iOSのベストプラクティス](/docs/7-1/tutorials/-/knowledge_base/t/ios-best-practices)
