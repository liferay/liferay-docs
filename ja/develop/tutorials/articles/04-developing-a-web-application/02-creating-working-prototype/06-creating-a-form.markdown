---
header-id: creating-a-form
---

# フォームの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

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
