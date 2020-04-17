---
header-id: create-or-update-the-event-class
---

# イベントクラスを作成または更新する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

スクリーンレットコンポーネント間の通信を処理するにはイベントクラスが必要であることを、基本的なスクリーンレット作成チュートリアルから思い出してください。 また、多くのScreenletsは、Screensに含まれるイベントクラス `BasicEvent`イベントクラスとして使用できることを思い出してください。 ただし、オフラインモードが機能するには、 `CacheEvent` を拡張するイベントクラスを作成する必要があります（[ここをクリックして](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/event/CacheEvent.java) をクリックして `CacheEvent`を表示します）。 イベントクラスには、Interactorに渡された引数へのアクセスを格納および提供するという1つの主要な責任があります。 これを実現するには、イベントクラスで次のことを行う必要があります。

  - `CacheEvent`拡張し` 。 引数については、変数とパブリックgetterメソッドを定義します。</li>
<li>対応するスーパークラスコンストラクターのみを呼び出す引数なしのコンストラクターを定義します。</li>
<li>Interactorの引数を設定するコンストラクターを定義します。</li>
</ul>

<p spaces-before="0">ブックマークスクリーンレットの追加の場合、引数はブックマークのURL、フォルダーID、およびタイトルです。 たとえば、このScreenletのイベントクラス <code>BookmarkEvent`の完全なコードは次のとおりです。</p> 
        public class BookmarkEvent extends CacheEvent {
        
            private String url; 
            private String title; 
            private long folderId; 
        
            public BookmarkEvent() { 
                super(); 
            }
        
            public BookmarkEvent(String url, String title, long folderId) {
        
                this.url = url; 
                this.title = title; 
                this.folderId = folderId; 
            }
        
            public String getURL() { 
                return url; 
            }
        
            public String getTitle() { 
                return title; 
            }
        
            public long getFolderId() { 
                return folderId; 
            } 
        }

次に、リスナーを更新します。

## 関連トピック

[リスナーを更新する](/docs/7-1/tutorials/-/knowledge_base/t/update-the-listener)

[インタラクタークラスを更新する](/docs/7-1/tutorials/-/knowledge_base/t/update-the-interactor-class)

[スクリーンレットクラスを更新する](/docs/7-1/tutorials/-/knowledge_base/t/update-the-screenlet-class)

[キャッシュをサーバーと同期する](/docs/7-1/tutorials/-/knowledge_base/t/sync-the-cache-with-the-server)
