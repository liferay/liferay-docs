---
header-id: creating-a-form
---

# フォームの作成

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">初めてのポートレット開発</p><p>手順6/8</p>
</div>

ゲストブックエントリを作成するためのフォームには2つのフィールドがあります。1つはエントリを送信する人の名前用、もう1つはエントリ自体用です。

`edit_entry.jsp`ファイルの末尾に次のタグを追加します。

    <aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">
            <aui:fieldset>
                <aui:input name="name"></aui:input>
                <aui:input name="message"></aui:input>
            </aui:fieldset>
    
            <aui:button-row>
                <aui:button type="submit"></aui:button>
                <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
            </aui:button-row>
    </aui:form>

`edit_entry.jsp`を保存して、アプリケーションを再デプロイします。 ページを更新して*エントリの追加*ボタンをクリックすると、フォームが表示されます。 *キャンセル*ボタンをクリックすると`view.jsp`へ戻りますが、*保存*ボタンはまだ試さないでください。 ゲストブックのエントリを保存する操作をまだ作成していないため、*保存*をクリックするとエラーが発生します。

![図1：これは、エントリを追加するためのゲストブックアプリケーションのフォームです。](../../../images/first-guestbook-portlet-edit-entry.png)

次のタスクは、ポートレット操作の実装（ユーザーが*保存*をクリックする時に起こるもの）です。
