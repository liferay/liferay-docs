---
header-id: creating-the-ios-list-screenlets-delegate
---

# iOSリストスクリーンレットのデリゲートの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Screenletのアクションに他のクラスが応答するようにするには、デリゲートが必要であることを思い出してください。 最初のステップ以下でデリゲートを作成します [Screenletのデリゲートの追加のチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/add-a-screenlet-delegate)。 リストスクリーンレットのデリゲートは、リストアイテムの選択に応答するためのメソッドも定義する必要があります。 たとえば、ブックマークリストスクリーンレットのデリゲートには次のメソッドが必要です。

  - `screenlet（_：onBookmarkListResponse :)`：サーバー呼び出しが成功した場合、 `ブックマーク` 結果を返します。
  - `screenlet（_：onBookmarkListError :)`：サーバー呼び出しが失敗したときに `NSError` オブジェクトを返します。
  - `screenlet（_：onBookmarkSelected :)`：ユーザーがリストで選択すると、 `ブックマーク` 返します。

`BookmarkListScreenletDelegate` プロトコルはこれらのメソッドを定義します：

    @objc public protocol BookmarkListScreenletDelegate : BaseScreenletDelegate {
    
        optional func screenlet(screenlet: BookmarkListScreenlet,
                                onBookmarkListResponse bookmarks: [Bookmark])
    
        optional func screenlet(screenlet: BookmarkListScreenlet,
                                onBookmarkListError error: NSError)
    
        optional func screenlet(screenlet: BookmarkListScreenlet,
                                onBookmarkSelected bookmark: Bookmark)
    
    }

よくやった\！ 次に、Screenletクラスを作成します。
