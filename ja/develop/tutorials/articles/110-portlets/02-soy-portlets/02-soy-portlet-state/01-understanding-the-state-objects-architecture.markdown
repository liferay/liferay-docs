---
header-id: understanding-the-state-object-architecture
---

# 状態オブジェクトのアーキテクチャを理解する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

`STATE` オブジェクト構成の例を以下に示します。

    View.STATE {
      myStateProperty: {
        setter: 'setterFunction',
        validator: val => val === expected value,
        value: default value,
        valueFn: val => default value,
        writeOnce: true
      }
    }

状態プロパティには次の構成オプションがあります。

**setter：** 状態キーの値を正規化します。 setter関数は、設定された新しい値を受け取り、保存する必要がある値を返します。

**検証：** 状態キーの値を検証します。 `false`返す場合、新しい値は無視されます。

**値：** 状態キーのデフォルト値。 または、 `valueFn` プロパティでデフォルト値を設定できます。 これをオブジェクトに設定すると、すべてのクラスインスタンスがオブジェクトへの同じ参照を使用します。 各インスタンスでオブジェクトの異なる参照を使用するには、代わりに `valueFn` オプションを使用します。 ポートレットテンプレートパラメータの値（該当する場合）は、この値よりも優先されることに注意してください。

**valueFn：** 状態キーのデフォルト値を返す関数。 または、 `値` プロパティでデフォルト値を設定できます。 ポートレットテンプレートパラメータの値（該当する場合）は、この値よりも優先されることに注意してください。

**writeOnce：** 状態キーが読み取り専用かどうか、つまり初期値が最終値であることを示します。

これで、 `STATE` オブジェクトのアーキテクチャとその構成方法がわかりました！

## 関連トピック

[ポートレットテンプレートパラメータ状態プロパティの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-portlet-template-parameter-state-properties)

[クライアント側での大豆ポートレットテンプレートパラメータの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-soy-portlet-template-parameters-on-the-client-side)
