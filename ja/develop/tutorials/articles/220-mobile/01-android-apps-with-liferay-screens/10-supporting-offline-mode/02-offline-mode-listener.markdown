---
header-id: update-the-listener
---

# リスナーを更新する

[TOC levels=1-4]

基本的なスクリーンレット作成チュートリアルから、リスナーインターフェイスが成功メソッドと失敗メソッドを定義していることを思い出してください。 これにより、実装クラスは、サーバー呼び出しの成功または失敗に応答できます。 オフラインモードをサポートするリスナーは、機能は異なりますが、同じ機能を提供します。 オフラインモードリスナーは `BaseCacheListener`拡張する必要があります`これは、この <code>エラー` メソッドのみを定義します。

    void error(Exception e, String userAction);

`BaseCacheListener`拡張することにより、 `エラー` メソッドを代わりに継承するため、リスナーは明示的な失敗メソッドを必要としなくなりました。 この `エラー` メソッドには、例外をトリガーしたユーザーアクションの引数も含まれています。

したがって、 `BaseCacheListener` を拡張し、失敗メソッドを削除することにより、リスナーを更新してオフラインモードをサポートできます。 たとえば、オフラインモードをサポートするために更新された後、ブックマークスクリーンレットのリスナー `AddBookmarkListener`追加します。

    public interface AddBookmarkListener extends BaseCacheListener {
    
        onAddBookmarkSuccess(); 
    }

また、障害メソッドの実装（リスナーを実装するアクティビティやフラグメントなど）を削除し、障害メソッドの呼び出しをエラー</code> メソッド呼び出し 置き換える必要があることに注意してください。 Interactorクラスを更新するときに、後者を次に実行します。</p>

<h2 spaces-before="0">関連トピック</h2>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/create-or-update-the-event-class">イベントクラスを作成または更新する</a></p>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/update-the-interactor-class">インタラクタークラスを更新する</a></p>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/update-the-screenlet-class">スクリーンレットクラスを更新する</a></p>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/sync-the-cache-with-the-server">キャッシュをサーバーと同期する</a></p>
