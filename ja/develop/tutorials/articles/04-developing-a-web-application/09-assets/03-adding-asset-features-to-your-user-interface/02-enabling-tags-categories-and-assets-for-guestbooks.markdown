---
header-id: enabling-tags-categories-and-related-assets-for-guestbooks
---

# ゲストブックのタグ、カテゴリ、および関連アセットの有効化

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">UIへのアセット機能の追加</p><p>手順3/5</p>
</div>

サービスレイヤーで既にアセット対応のゲストブックを使用しているため、ゲストブックエンティティはタグとカテゴリをサポートできるようになりました。 あとは、UIでそれらを有効にするだけです。 この手順では、ゲストブック管理ポートレットの `edit_guestbook.jsp` 更新して、管理者がゲストブックを追加または更新するときにタグとカテゴリを追加、編集、または削除できるようにします。

## アセット機能を有効にする

次の手順を実行します：

1.  `guestbook-web` モジュールの `/guestbookadminportlet/edit_guestbook.jsp`で、タグ `<liferay-ui:asset-categories-error />` および `<liferay-ui:asset-tags-error/>` を、終了 `</aui:button-row>` タグの下の `aui：form` に追加します。
   
        <liferay-ui:asset-categories-error />
        <liferay-ui:asset-tags-error />

    フォームで送信されたタグまたはカテゴリでエラーが発生した場合、これらのタグはエラーメッセージを表示します。

2.  エラータグの下に、次の属性を持つ `<liferay-ui:panel>` タグを追加します。
   
        <liferay-ui:panel defaultState="closed" extended="<%= false %>"
          id="guestbookCategorizationPanel" persistState="<%= true %>"
          title="categorization">
       
        </liferay-ui:panel>

    `<liferay-ui:panel>` タグは、折りたたみ可能なセクションを生成します。

3.  作成したパネルセクション内にタグとカテゴリの入力フィールドを追加します。 `<aui:input />` タグに `assetCategories` および `assetTags` タイプを指定します。 これらの入力タグは、資産カテゴリと資産タグを表します。 関連する入力フィールドを `<aui:fieldset>` タグでグループ化できます。 タグは、タグとカテゴリの適切なセレクターを生成し、ゲストブックに既に追加されているものを表示します。
   
        <aui:fieldset>
            <liferay-asset:asset-categories-selector className="<%= Guestbook.class.getName() %>" classPK="<%= guestbook %>" />
            <liferay-asset:asset-tags-selector className="<%= Guestbook.class.getName() %>" classPK="<%= guestbook %>" />
        </aui:fieldset>

4.  既存のタグの下に</code> 番目の `<liferay-ui:panel>`タグを追加します。 この新しいタグに、 `<liferay-ui:asset-links>` タグを含む `<aui:fieldset>` タグを追加し` 。 正しいアセットリンク（選択したゲストブックの関連アセット）を表示するには、 <code>className` および `classPK` 属性を設定します：
   
        <liferay-ui:panel defaultState="closed" extended="<%= false %>"
          id="guestbookAssetLinksPanel" persistState="<%= true %>"
          title="related-assets">
          <aui:fieldset>
            <liferay-ui:input-asset-links
              className="<%= Guestbook.class.getName() %>"
              classPK="<%= guestbookId %>" />
          </aui:fieldset>
        </liferay-ui:panel>

更新された `edit_guestbook.jsp` ページをテストするには、コントロールパネルのGuestbook Adminポートレットに移動し、 *Guestbook*追加をクリックします。 タグを追加するためのフィールドと、関連するアセットを選択するためのセレクターが表示されます。

![図1：Guestbook Adminポートレットの <code>edit_guestbook.jsp</code> ページを更新すると、タグを追加し、関連するアセットを選択するためのフォームが表示されます。](../../../../images/guestbook-tags-related-assets.png)

アセットの実装が完了していないため、これらのフィールドではまだ何もしないでください。 次に、ゲストブックエントリのタグとカテゴリを有効にします。
