---
header-id: using-liferays-language-settings
---

# Liferayの言語設定の使用

[TOC levels=1-4]

特定のロケールについて、[@product@のコアUIメッセージをオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-language-keys)できます。
言語キーの値を変更すると、ローカライズの柔軟性が大幅に向上しますが、当社はさらなる制御を可能にする新しい方法を常に模索しています。`Language_xx.properties`ファイルには、さらに多くのローカライズオプションを提供する言語設定があります。

- ユーザーフォームの追加および編集で、表示される名前フィールドと、選択フィールドで使用可能なフィールド値を構成します。たとえば、必要に応じてミドルネームフィールドを省略したり、プレフィックスの選択を変更したりします。

- コンテンツとメッセージの方向を制御します（左から右または右から左）。

これらの設定がどのように構成されているかを確認するには、@product@のコア`Language.properties`ファイルを次の2つの方法のいずれかで開きます。

1. Liferay Portalのソースコードから。以下に移動します。

       liferay-portal/portal-impl/src/content/Language.properties
   
2. バンドルの`portal-impl.jar`から。

       [Liferay Home]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar
   
   JARの`content`フォルダを開いて、言語ファイルを見つけます。

`Language.properties`ファイルの最初のセクションには、*Language Settings*というラベルが付いています。

    ##
    ## Language Settings
    ##
    
    lang.dir=ltr
    lang.line.begin=left
    lang.line.end=right
    lang.user.default.portrait=initials
    lang.user.initials.field.names=first-name,last-name
    lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
    lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
    lang.user.name.required.field.names=last-name
    lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr

**注**：ここで説明した言語設定を使用するには、モジュールが必要です。このモジュールは、コードとリソースを乗せて@product@のOSGiランタイムに移行しやすくする魔法のじゅうたんのようなものです。[言語キーのオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-language-keys)に関するチュートリアルを参照して、次の特性を持つモジュールをセットアップします。

- OSGiランタイムに登録されている`ResourceBundle`の実装が含まれている。
-  プロパティをオーバーライドするロケールの`Language.properties`ファイルが含まれている

ユーザー名のプロパティは、ユーザーのロケールに基づいて、ユーザーフォームの追加および編集の特定のフィールドをカスタマイズするために使用されます。

## ユーザー名のローカライズ

お客様は世界中からアクセスされるため、ロケールによって命名規則が異なります。このため、ユーザー名フィールドは次の方法で構成できます。

- 特定の名前フィールドを削除し、他のフィールドを複数回表示させる。たとえば、一部のロケールでは複数の姓が必要です。

       lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
   
- ロケールのプレフィックスとサフィックスの値を変更する。

       lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
       lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr
   
- 必要なフィールドを指定する。

       lang.user.name.required.field.names=last-name
   
**注**：ユーザーの名は必須です。このため、ロケールのユーザー名設定を構成するときは、次の2つの点を考慮してください。

- `first-name`フィールドはフィールド名リストから削除できません。

         lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix

- 名は必須であるため、**必須フィールド名**プロパティには常に暗黙的に含まれます。

         lang.user.name.required.field.names=last-name

     したがって、ここには名フィールドに**加えて**任意のフィールドを入力します。デフォルトでは姓が必要ですが、プロパティから値を削除することで無効にできます。

         lang.user.name.required.field.names=

その場合は、名のみが必須になります。

ユーザー名の設定を変更するためのプロパティは、ロケールの言語プロパティファイルの言語設定セクションにある`lang.user.name`で始まるプロパティです。

ほとんどの[デフォルトで有効になっているロケール](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)では、ユーザー名のプロパティはその場所に合わせて特別に調整されます。

    locales.enabled=ca_ES,zh_CN,nl_NL,en_US,fi_FI,fr_FR,de_DE,iw_IL,hu_HU,ja_JP,pt_BR,es_ES

たとえば、ユーザー名フィールドを設定するための英語（`Language_en.properties`）のプロパティは次のとおりです。

    lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
    lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
    lang.user.name.required.field.names=last-name
    lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr

![図1：ユーザー名の設定は、Liferayでのユーザー情報とフォームの表示に影響します。](../../images/english-user-name-fields.png)

これらをスペイン語（`Language_es.properties`）の設定と比較します。

    lang.user.name.field.names=prefix,first-name,last-name
    lang.user.name.prefix.values=Sr,Sra,Sta,Dr,Dra
    lang.user.name.required.field.names=last-name

![図2：スペイン語のユーザー名設定では、サフィックスとミドルネームのフィールドが完全に省略されています。](../../images/spanish-user-name-fields.png)

英語とスペイン語のフォームフィールドの最大の違いは、スペイン語の構成ではミドルネームとサフィックスのフィールドが省略されていることです。特定のプレフィックス値の違いもあります。

 ユーザーを追加および編集するためのフォームのローカライズは、@product@のUIメッセージをローカライズするのと同じ方法、つまり`Lanuguage_xx.properties`ファイルの1つをオーバーライドすることで遂行されます。

## ユーザーイニシャルの識別

デフォルトのアバターには、ユーザーのイニシャルが表示されます。文化によってはイニシャルの使い方が違うので、`Language.properties`ファイルにそれらを設定する方法があります。

    lang.user.default.portrait=initials
    lang.user.initials.field.names=first-name,last-name

`lang.user.default.portrait`プロパティは、ユーザーに使用するポートレートのタイプを設定します。これは`initials`または`image`に設定できます。`image`に設定されている場合、`portal.properties`ファイルにある`image.default.user.female.portrait`または`image.default.user.male.portrait`プロパティによって定義されたデフォルトのイメージが使用されます。したがって、`lang.user.initials.field.names`プロパティは無視されます。

![図3：デフォルトでは、ユーザーのイニシャルがアバターに表示されます。](../../images/initials-avatar.png)

デフォルトのアバターにユーザーのイニシャルを利用している場合、`lang.user.initials.field.names`プロパティを使用して、イニシャルの表示方法を整理します。このプロパティの有効な値には、`first-name`、`middle-name`、および`last-name`があり、任意の順序で使用できます。

ユーザーのイニシャルの表示方法を管理できるようになりました。

## 右から左または左から右？

`Language.properties`の言語設定セクションの最初の3つのプロパティは、言語の文字が表示される方向を変更します。
ほとんどの言語は左から右に読まれますが、一部の言語は右から左に読まれます（アラビア語、ヘブライ語、ペルシャ語など）。ユーモアのあるいたずらをするなら、本来は左から右に表示されている言語（英語など）についても変更できます。ここでアイデアを得たことは誰にも言わないでくださいね。

右から左に表示する言語に関連する言語プロパティを以下に示します。

    lang.dir=rtl
    lang.line.begin=right
    lang.line.end=left

これらのカスタマイズにより、ユーザーの出身地に関係なく、UIを使いやすい環境に変換できます。
