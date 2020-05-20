---
header-id: action-show-and-hide
---

# アクション：表示と非表示

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

表示と非表示のルールでは、1つ以上の条件を使用して、条件が *true*評価された場合にフィールドを表示するか非表示にするかを決定します。

この例を設定するには、次のフィールドをフォームに追加します。

  - *私は18歳以上だ*：2つのオプションで必要な単一選択フィールド *はい* および *はありません*。

  - *法的保護者の電子メールアドレス*、有効な電子メールアドレスを受け入れるテキストフィールド（テキストフィールドの検証を使用して入力タイプを指示します）。

| **例：** 18歳未満の場合は、法的承認が必要です。そり犬レースでそりを運転する保護者（犬ではなく鶏をレースしている場合でも）| あなたのチキンチームを登録するためのフォームはあなたに|の年齢を尋ねます運転者。 18未満の数字を入力すると、法的保護者の電子メールアドレス|フィールドが表示されます。 | |表示/非表示ルールを構成するには、| | 1。 [フォームの編集]ページの[ルール]タブを開き、[追加]をクリックします。 （![Add](../../../images/icon-add.png)）ボタン。 | | 2。 ルールを定義します。| *私が18歳以上である場合* フィールドが *No*等しい場合、|を表示します。 *Legal Guardian Email Address* フィールド。 | | ![Figure 1: Build form rules quickly by defining your conditions and actions.](../../../images/forms-rule-development.png) | | -ルールを保存します。 | | ![Figure 2: Once a rule is saved, it is displayed so that you can easily understand what it does.](../../../images/forms-rule-list.png) | |現在、 *Legal Guardian Email Address* フィールドは、|の場合にのみフォームに表示されます。ユーザーは *私が18歳以上* フィールドで *いいえ* を選択します。

表示ルールでは、条件が満たされるまでフィールドを非表示にしておくことができます。
