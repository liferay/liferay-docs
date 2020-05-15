---
header-id: managing-users
---

# ユーザーの管理

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

小売業者があなたが望むもののために「ワンストップショップ」として宣伝しているのを聞いたことがありますか？ アイデアは、彼らがあなたが望むものはおそらくそこにあるほど多くのものを持っているということです。 Liferayのコントロールパネルはこんな感じ。 ユーザー、組織、またはサイトをどこに作成しますか？ 権限やプラグインなど、他に何を設定しますか？ コントロールパネルから行います。 管理者としてサインインすると、[ *]コントロールパネル*クリックして、製品メニューからコントロールパネルにアクセスできます。

![図1：管理者は、製品メニューからコントロールパネルにアクセスできます。](../../images/usrmgmt-control-panel.png)

コントロールパネルは、ユーザー、サイト、アプリ、構成の4つの主要な領域に分かれています。 [ユーザー]セクションでは、ユーザー、組織、ユーザーグループ、役割、およびパスワードポリシーを作成および管理できます。 ポータルで監視が有効になっている場合は、ユーザーのすべてのライブポータルセッションを表示することもできます。


<!--That's what this section of articles describes: User Management. Use these links
to navigate quickly to the content you want:

- [Users](/docs/7-1/user/-/knowledge_base/u/users-and-organizations)
    - [Adding, Editing, and Deleting Users](/docs/7-1/user/-/knowledge_base/u/adding-editing-and-deleting-users)
    - [Important Additional User Management Topics](/docs/7-1/user/-/knowledge_base/u/user-management-additional-topics)
- [Organizations](/docs/7-1/user/-/knowledge_base/u/organizations)
    - [Managing Organizations](/docs/7-1/user/-/knowledge_base/u/managing-organizations)
- [Roles and Permissions](/docs/7-1/user/-/knowledge_base/u/roles-and-permissions)
    - [Managing Roles](/docs/7-1/user/-/knowledge_base/u/managing-roles)
    - [Defining Role Permissions](/docs/7-1/user/-/knowledge_base/u/defining-role-permissions)
- [User Groups](/docs/7-1/user/-/knowledge_base/u/user-groups)

These articles are planned but not yet written:

- Password Policies(/docs/7-1/user/-/knowledge_base/u/password-policies)
- Monitoring Users(/docs/7-1/user/-/knowledge_base/u/monitoring-users)
- Auditing Users(/docs/7-1/user/-/knowledge_base/u/auditing-users)
-->

| **匿名ユーザー：** *匿名匿名* が新しい| [ユーザーデータの管理](/docs/7-1/user/-/knowledge_base/u/managing-user-data) |機能性。 管理者が初めて *をクリックしたときに作成されます*データ* ユーザーのために、 *匿名匿名* 割り当てられた非アクティブユーザーであります| [匿名化された資産](/docs/7-1/user/-/knowledge_base/u/managing-user-data#anonymizing-data)。 |匿名ユーザーは構成可能であるため、名前と構成の詳細は|仮想インスタンスでは異なります。</p>

## ユーザー管理の新機能

@product@バージョン7.0では、ユーザー管理の機能と動作は以前のバージョンとほとんど変わりませんでした。 既存の動作の改善が導入されましたが、将来の拡張を行うことができるように、ほとんどの作業はバックエンドコードに組み込まれました。 @ product-ver @では、ユーザー管理者向けの新機能をいくつか発表します。

1.  数値のスクリーン名をそのまま使用できます。 読む [ここで](/docs/7-1/user/-/knowledge_base/u/user-management-additional-topics#numeric-screen-names) 詳細については。

2.  システム設定から組織タイプを追加します。 ポータルのプロパティや、必要なシステムの再起動は必要ありません。 読む [ここで](/docs/7-1/user/-/knowledge_base/u/managing-organizations#organization-types) 詳細については。

3.  システム設定からデフォルトのユーザープロファイル画像を構成します。 読む [ここで](/docs/7-1/user/-/knowledge_base/u/user-management-additional-topics#user-profile-pictures) 詳細については。

ユーザーの追加と編集について読むことから、Liferayのユーザー管理機能を探索してください。


<!-- Perhaps make a table showing similarities and differences between the
parallel sites vs. portal-wide user functionality
-->
