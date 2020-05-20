---
header-id: exporting-user-data
---

# ユーザーデータのエクスポート

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ユーザー管理の慣行では、EUの一般データ保護規則を考慮する必要があります。 その原則の1つは、ユーザーがデータの移植性を *する権利を持っていることです*。

データの移植性とは、ユーザーが個人データを機械可読形式で受け取る権利を有することを意味します。

| **注：** 個人データのエクスポートは、企業が満足しようとする際に役立ちます。 GDPRの要件。 ここで説明するエクスポートツールを使用しても何も起こりません| GDPR要件への準拠の保証。 各企業または個人|ウェブサイトがユーザーの個人データを処理し、GDPRの管轄下にある|が完全であることを確認するために必要な正確な手順を慎重に決定する必要があります。 GDPRに準拠。

コントロールパネルのユーザー管理システムは、ユーザーの個人データをダウンロード用のZIPファイルにエクスポートできるようになりました。 データを含むファイルのデータ形式はXMLです。

## エクスポートとダウンロード

ユーザーデータをエクスポートするには、

1.  コントロールパネル→ユーザー→ユーザーと組織に移動します。

2.  ユーザーを見つけて[アクション]ボタン（![Actions](../../../images/icon-actions.png)）をクリックし、[ *個人データのエクスポート*]をクリックします。

    これにより、[ユーザーの個人データのエクスポート]画面が開きます。

3.  表示されている既存のエクスポートプロセスがない場合、実行することは1つだけです。[ *追加* ]ボタン（![Add](../../../images/icon-add.png)）をクリックします。 ユーザーのデータをエクスポートするためのツールが表示されます。

    ![図1：個人データのエクスポートツールでは、ユーザーのデータのすべてまたは一部をエクスポートできます。](../../../images/users-export-data.png)

4.  ほとんどの場合、利用可能なすべてのデータをエクスポートします。 [ *Select Items*クリックすると、ユーザーデータを含むすべてのアプリケーションがUIで選択されます。

5.  [ *エクスポート*クリックし* 。 [ユーザーの個人データのエクスポート]画面に戻りますが、リストにはエクスポートプロセスがあります。</p>

    ![図2：ユーザーデータが正常にエクスポートされると、エクスポートプロセスがユーザーの[個人データのエクスポート]リストに表示されます。](../../../images/users-export-processes.png)</li>

6

データをダウンロードします。 プロセスの[アクション]ボタン（![Actions](../../../images/icon-actions.png)）をクリックし、[ *ダウンロード*を選択します。</ol>

## エクスポートされたデータの調査

では、エクスポートされたデータはどのように見えますか？

    <?xml version="1.0"?>
    
    <model>
        <model-name>com.liferay.message.boards.model.MBMessage</model-name>
        <column>
            <column-name>messageId</column-name>
            <column-value><![CDATA[38099]]></column-value>
        </column>
        <column>
            <column-name>statusByUserId</column-name>
            <column-value><![CDATA[38045]]></column-value>
        </column>
        <column>
            <column-name>statusByUserName</column-name>
            <column-value><![CDATA[Jane Slaughter]]></column-value>
        </column>
        <column>
            <column-name>userId</column-name>
            <column-value><![CDATA[38045]]></column-value>
        </column>
        <column>
            <column-name>userName</column-name>
            <column-value><![CDATA[Jane Slaughter]]></column-value>
        </column>
        <column>
            <column-name>subject</column-name>
            <column-value><![CDATA[Great list. I was thinking of bringing the family,...]]></column-value>
        </column>
        <column>
            <column-name>body</column-name>
            <column-value><![CDATA[<p>Great list. I was thinking of bringing the family, but I don&#39;t
      actually believe humans have ever been to the moon, so I guess it
      would be silly to book a trip! LOL!</p>]]></column-value>
        </column>
    </model>

この例では、ユーザーJane Slaughterがメッセージボードメッセージポストを作成し、彼女のユーザー情報が `MBMessage` モデルのデータベーステーブルに記録されました。

これは実際にはブログエントリのコメントに対応しています。

![図3：ブログ投稿のコメントはユーザー関連データです。](../../../images/users-mbmessage.png)

ユーザーデータをエクスポートすると、サイトの所有者とユーザーに、サイトに含まれる個人データの量が通知されます。
