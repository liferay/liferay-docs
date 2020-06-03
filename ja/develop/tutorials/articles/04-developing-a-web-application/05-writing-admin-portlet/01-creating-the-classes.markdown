---
header-id: creating-the-classes
---

# クラスを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Guestbook Adminアプリの作成</p><p>手順1/5</p>
</div>

GuestbookおよびGuestbook Adminアプリケーションを一緒にバンドルする必要があるため、ウィザードを使用するのではなく、 `guestbook-web` プロジェクト内で新しいアプリケーションを手動で作成します。 この設計上の決定に同意しない場合は、Guestbook Admin用に別のプロジェクトを作成できます。使用するプロジェクトテンプレートは *panel-app*です。 ただし、現時点では、プロセスを手動で実行して、すべてがどのように機能するかを学習することをお勧めします。

1.  `guestbook-web` プロジェクトの `com.liferay.docs.guestbook.portlet` パッケージを右クリックし、 *New* → *Class*を選択します。

2.  クラスに `GuestbookAdminPortlet`という名前を</code>ます。

3.  スーパークラスの横にある *参照* クリックし、 `MVCPortlet`を検索します。 それをクリックして、 *OK*を選択します。

4.  [ *完了*クリックし* 。</p></li> </ol>

これで、Guestbook Adminアプリケーションのポートレットクラスが作成されました。 ただし、管理アプリケーションの場合、少なくとも1つ以上のコンポーネントが必要です。

## パネルとカテゴリー

[製品メニューチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/customizing-the-product-menu)で説明されているように、以下に示すように製品メニューには3つのセクションがあります。

![図1：製品メニューは、コントロールパネル、ユーザーメニュー、およびサイトメニューの3つのセクションに分かれています。](../../../images/product-menu-parts.png)

各セクションは、 *パネルカテゴリ*と呼ばれます。 パネルカテゴリには、 *パネルアプリ*と呼ばれるさまざまなメニュー項目を保持できます。 上の図では、[サイト]メニューが開いてパネルのアプリとカテゴリが表示されています（はい、ネストできます）。

Guestbook Adminポートレットの最も自然な場所は、@product@の他のコンテンツベースのアプリを含む *コンテンツ* パネルカテゴリです。 これにより、サイト管理者が期待する場所にうまく統合されます。 これは、新しいカテゴリを作成する必要がないことも意味します。パネルエントリを作成するだけで、次はそれを行います。 この後、パネルのカテゴリとアプリについて詳しく知りたい場合は、 [製品メニューチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/customizing-the-product-menu) および [コントロールメニューチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/customizing-the-control-menu)参照してください。

Guestbook Adminポートレットのパネルエントリを作成するには、次の手順に従います。

1.  @product@のパネルカテゴリとアプリを拡張するために必要な依存関係を追加します。 これを行うには、 `guestbook-web`の `build.gradle` ファイルを開き、この依存関係を追加します。
   
        compileOnly group: "com.liferay", name: "com.liferay.application.list.api", version: "2.0.0"

2.  `guestbook-web` を右クリックし、 *Gradle* → *Gradle Projectの更新*を選択します。

3.  `guestbook-web` プロジェクトで `src / main / java` を右クリックし、 *New* → *Package*を選択します。 パッケージに `com.liferay.docs.guestbook.application.list` という名前を付け、 *Finish*をクリックします。

4.  新しいパッケージを右クリックし、 *新規* → *クラス*を選択します。 クラスに `GuestbookAdminPanelApp`という名前を</code>ます。

5.  Superclassの横にある *Browse* クリックし、 `BasePanelApp`検索して選択し、 *OK*をクリックします。 次に、 *完了*クリックします。

すばらしいです\！ 必要なクラスを作成し、それらの作業を開始する準備が整いました。
