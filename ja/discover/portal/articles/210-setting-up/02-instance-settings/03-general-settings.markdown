---
header-id: general-settings
---

# 一般設定

[TOC levels=1-4]

一般的な仮想インスタンス設定は、 *コントロールパネル* → *構成* → *インスタンス設定*ます。 一般設定は、最初のタブである構成の上部にあります。

Generalセクションには、Main Configuration、Navigation、Additional Informationという3つの見出しがあります。 メイン構成の下で、

  - 仮想インスタンスの実行を担当するエンティティの名前を設定します。

  - メールドメインを設定します。

  - 仮想ホストを設定します。

  - 静的リソースを提供するように構成されたCDNにURLを設定します。

ナビゲーションの見出しの下で、仮想インスタンスのホームページと、デフォルトのランディングページおよびログアウトページを設定します。 これらのページを設定するには、ドメインに続くページのアドレスの部分を使用します。 たとえば、デフォルトのランディングページを `http：// localhost：8080 / web / guest / login`に設定するには、 `/ web / guest / login`ます。 変数 `${liferay:screenName}` をアドレスの一部として使用します。 これは、ログイン時にユーザーを個人用ページにリダイレクトする場合に便利です。 たとえば、User `marvin` は、個人用ページへの次のデフォルトURLがあります。

    http://localhost:8080/user/marvin

ログイン時に彼がそこに誘導されるようにするには、これを[デフォルトのランディングページ]フィールドに配置します。

    /user/${liferay:screenName}

これらのURLは、システムスコープの `portal-ext.properties` ファイルでも設定できます。

    default.landing.page.path=
    default.logout.page.path=
    company.default.home.url=

すべての仮想インスタンスは、プロパティファイルで指定された値を共有します。 インスタンス設定で行われた変更は、プロパティファイルで設定された値を上書きします。

詳細については、 [ポータルプロパティのドキュメント](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)参照してください。

[追加情報]見出しの下で、法的名称、ID、会社タイプ、SICコード、ティッカーシンボル、業界および業界タイプを指定できます。
