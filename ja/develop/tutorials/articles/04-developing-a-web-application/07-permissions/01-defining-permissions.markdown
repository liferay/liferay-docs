---
header-id: defining-permissions
---

# 許可の定義

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">許可の実装</p><p>手順1/4</p>
</div>

@product@の権限フレームワークは、Service Builderと同様に宣言的に構成されます。 慣例により `default.xml` と呼ばれるXMLファイルですべてのアクセス許可を定義します（ただし、実際には任意の名前で呼び出すことができます）。 次に、コード内の次の場所にアクセス許可チェックを実装します。

  - ビューレイヤーで、保護された機能へのリンクまたはボタンを表示する場合
  - アクションで、保護されたアクションを実行する前に
  - 後で、サービスで、リモートサービスを呼び出す前に

最初に必要な権限を定義する必要があります。 開始するには、アプリケーションの使用例と、その機能へのアクセスをどのように制御するかを考えてください。

  - [ゲストブックの追加]ボタンは、管理者のみが使用できるようにする必要があります。

  - ゲストブックのタブは、管理者がそれらを表示できるユーザーを制御できるように、アクセス許可でフィルタリングする必要があります。

  - 匿名ユーザーがゲストブックをスパムしないようにするには、[エントリの追加]ボタンはサイトメンバーのみが使用できるようにする必要があります。

  - ユーザーは、自分のエントリにアクセス許可を設定できる必要があります。

これで、アクセス許可の構成を作成する準備が整いました。 アプリケーション内のオブジェクト（ `Guestbook` および `Entry`）は *リソース*として定義され、 *リソースアクション* ユーザーがこれらのリソースと対話する方法を管理します。 したがって、ポートレットのアクセス許可とリソース（またはモデル）のアクセス許可の2種類のアクセス許可があります。 ポートレット許可は、 *Add Entry*などのグローバル機能へのアクセスを保護します。 ユーザーがそのグローバル機能にアクセスする権限を持っていない場合、ポートレットの権限がありません。 リソース許可は、 `Guestbook` および `Entry`などのオブジェクトへのアクセスを保護します。 ユーザは、1つ表示する権限有していてもよい `エントリー`、表示と編集別の `入力`、別のアクセスすることができない場合が `項目` 全く。 これは、リソースの許可によるものです。

![図1：ポートレットのアクセス許可とリソースのアクセス許可は、アプリケーションのさまざまな部分をカバーしています。](../../../images/permission-types.png)

最初に、 `guestbook-service` プロジェクトで許可ファイルを作成します。

1.  `META-INF` フォルダーで、 `resource-actions`というサブフォルダーを作成します。

2.  このフォルダーに `default.xml`という新しいファイルを作成します。

3.  [ *ソース* ]タブをクリックします。 次の `DOCTYPE` 宣言をファイルの先頭に追加します。
   
        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action  
        Mapping 7.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_1_0.dtd">

4.  次のラッパータグを `default.xml` ファイルの `DOCTYPE` 宣言の下に配置します。
   
        <resource-action-mapping>
       
        </resource-action-mapping>

    これらのタグ内でリソースとモデルの権限を定義します。

5.  次に、 `com.liferay.docs.guestbook` パッケージのアクセス許可を `<resource-action-mapping>` タグの間に配置します。
   
        <model-resource>
            <model-name>com.liferay.docs.guestbook</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookPortlet</portlet-name>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet</portlet-name>
            </portlet-ref>
<root>本当</root> <permissions>
                <supports>
                    <action-key>ADD_GUESTBOOK</action-key>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>VIEW</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>ADD_ENTRY</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>見る</action-key>
                </guest-defaults>
                <guest-unsupported>
                    <action-key>ADD_GUESTBOOK</action-key>
                    <action-key>ADD_ENTRY</action-key>
                </guest-unsupported>
            </permissions>
     </model-resource>

    これは、 `ゲストブック` および `エントリ` エンティティのベースライン構成を定義します。 サポートされるアクションは `ADD_GUESTBOOK` および `ADD_ENTRY`です。 サイトメンバーはデフォルトで `ADD_ENTRY` を設定できますが、ゲストはどちらのアクションも実行できません（ただし、表示することはできます）。

6.  その下、ただし終了 `</resource-action-mapping>`に、 `Guestbook` モデルのアクセス許可を配置します。
   
        <model-resource>
            <model-name>com.liferay.docs.guestbook.model.Guestbook</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookPortlet</portlet-name>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet</portlet-name>
            </portlet-ref><permissions>
                <supports>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>削除</action-key>
                    <action-key>許可</action-key>
                    <action-key>更新</action-key>
                    <action-key>見る</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>ADD_ENTRY</action-key>
                    <action-key>見る</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>見る</action-key>
                </guest-defaults>
                <guest-unsupported>
                    <action-key>更新</action-key>
                </guest-unsupported>
            </permissions>
     </model-resource>

    これは、追加、削除、更新、表示など、 `ゲストブック` 固有のアクションを定義し` 。 デフォルトでは、サイトメンバーとゲストはゲストブックを表示できますが、ゲストはそれらを更新できません。</p></li>
<li><p spaces-before="0"> <code>Guestbook` モデルのパーミッションの下に、まだ `</resource-action-mapping>`の終了の上に、 `Entry` モデルのパーミッションを配置します。
   
        <model-resource>
            <model-name>com.liferay.docs.guestbook.model.Entry</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookPortlet</portlet-name>
            </portlet-ref><permissions>
                <supports>
                    <action-key>削除</action-key>
                    <action-key>許可</action-key>
                    <action-key>更新</action-key>
                    <action-key>見る</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>見る</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>見る</action-key>
                </guest-defaults>
                <guest-unsupported>
                    <action-key>更新</action-key>
                </guest-unsupported>
            </permissions>
     </model-resource>

    これにより、 `エントリ` 固有のアクションが定義され` 。 デフォルトでは、サイトメンバーはエントリを追加または表示でき、ゲストはエントリのみを表示できます。</p></li>
<li><p spaces-before="0"> ファイルを保存します。</p></li>
</ol>

<p spaces-before="0">次に、許可が定義されている場所をフレームワークに伝える必要があります。 モデルが定義されているモジュールでリソースとモデルのアクセス許可を定義します。</p>

<ol start="1">
<li><p spaces-before="0"> <code>guestbook-service`の `src / main / resources` フォルダーで、 `portlet.properties`というファイルを作成します。

2.  このファイルに、次のプロパティを配置します。
   
        resource.actions.configs=META-INF/resource-actions/default.xml

このプロパティは、権限定義ファイルの名前と場所を定義します。

これでモデルレベルで権限が定義されましたが、ポートレットの権限も定義する必要があります。 これらは、ポートレットクラスを含む `guestbook-web` モジュールで管理されます。 `guestbook-web` モジュールにポートレット権限を追加するには、次の手順に従います。

1.  `src / main / resources / META-INF` フォルダーに `resource-actions` というサブフォルダーを作成します。

2.  このフォルダーに `default.xml`という新しいファイルを作成します。

3.  次の `DOCTYPE` 宣言をファイルの先頭に追加します。
   
        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action  
        Mapping 7.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_1_0.dtd">

4.  `DOCTYPE` 宣言の下に、次の `resource-action-mapping` タグを追加します。
   
        <resource-action-mapping>
       
        </resource-action-mapping>

    これらのタグ内でポートレットの許可を定義します。

5.  次のコードブロックを `resource-action-mapping` タグ内に挿入します。
   
        <portlet-resource>
            <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet</portlet-name><permissions>
                <supports>
                    <action-key>ACCESS_IN_CONTROL_PANEL</action-key>
                    <action-key>構成</action-key>
                    <action-key>見る</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>見る</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>見る</action-key>
                </guest-defaults>
                <guest-unsupported>
                    <action-key>ACCESS_IN_CONTROL_PANEL</action-key>
                    <action-key>構成</action-key>
                </guest-unsupported>
            </permissions>
     </portlet-resource>

    これにより、ゲストブック管理ポートレットのデフォルトの権限が定義されます。 これは、アクションをサポート `ACCESS_IN_CONTROL_PANEL`、 `CONFIGURATION`、および `VIEW`。 誰でもアプリを表示できますが、ゲストとサイトメンバーはコントロールパネルでアプリを構成したりアクセスしたりすることはできません。 これはコントロールパネルポートレットであるため、これは事実上、管理者のみがアクセスできることを意味します。

6.  Guestbook Admin権限の下に、次のコードブロックを挿入します。
   
        <portlet-resource>
            <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookPortlet</portlet-name><permissions>
                <supports>
                    <action-key>ADD_TO_PAGE</action-key>
                    <action-key>構成</action-key>
                    <action-key>見る</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>見る</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>見る</action-key>
                </guest-defaults>
                <guest-unsupported />
            </permissions>
     </portlet-resource>

    これにより、ゲストブックポートレットの権限が定義されます。 これは、アクションをサポート `ADD_TO_PAGE`、 `CONFIGURATION`、および `VIEW`。 サイトのメンバーとゲストは、デフォルトで `VIEW` 許可を取得します。

7.  ファイルを保存します。

8.  `guestbook-web`の `src / main / resources` フォルダーに、 `portlet.properties`というファイルを作成します。

9.  このファイルに、次のプロパティを配置します。
   
        resource.actions.configs=META-INF/resource-actions/default.xml

10. ファイルを保存します。

よくやった\！ これで、アプリケーションのアクセス許可スキームが正常に設計および実装されました。 次に、サービス層で権限をサポートするJavaコードを作成します。
