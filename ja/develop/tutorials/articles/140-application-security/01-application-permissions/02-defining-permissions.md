---
header-id: defining-resources-and-permissions
---

# リソースとアクセス許可の定義

[TOC levels=1-4]

アクセス許可を実装する最初のステップは、リソースとそれらを保護するアクセス許可を定義することです。リソースには、*ポートレットリソース*と*モデルリソース*の2種類があります。

ポートレットリソースはポートレットを表します。ポートレットリソースの名前は、ポートレットの`@Component`プロパティのポートレットID、またはWARファイルを使用している場合は`portlet.xml`ファイルのポートレットIDです。モデルリソースとは、通常はデータベースへのエンティティとして永続化されるモデルオブジェクトを指します。モデルリソースの名前は、その完全修飾クラス名です。以下に示すXMLでは、アクセス許可の実装が最初に*ポートレット*リソースに対して定義され、次に*モデル*リソースに対して定義されます。

モデルリソースは、ブログエントリなどのモデルを表します。リソースの名前は、それらが表すエンティティの完全修飾クラス名を使用して名付けられます。

**注**：各リソースには、アクセス許可を適用できる4つのスコープ、company、group、group-template、またはindividualがあります。これらは、本サイトおよびコードでは*ポートレットリソース*と呼ばれるため、混乱を招く可能性があります。その他のスコープは、主にさまざまなLiferayの構成要素（サイトやカテゴリなど）で内部的に使用されます。

XMLファイルを使用してリソースとそのアクセス許可を定義します。慣例により、このファイルは`default.xml`と呼ばれ、モジュールの`src/main/resources/resource-actions`フォルダに存在します。

リソースには2つの異なるタイプがあるため、これらのファイルは2つあります。1つはポートレットモジュールにありポートレットリソースを定義し、もう1つはサービスモジュールにありモデルリソースを定義します。

## ポートレットリソース権限の定義

最初にポートレットリソースを定義します。Liferayのブログアプリケーションを使用した例を次に示します。

1. DTD宣言から始めます。

       <?xml version="1.0"?>
       <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">
   
2. ルートタグには、宣言するすべてのリソースが含まれています。

       <resource-action-mapping>
       
       </resource-action-mapping>
   
3. これらのタグ内で、リソースを定義します。ブログアプリケーションは、次の2つのポートレットリソースを定義します。

       <portlet-resource>
       <portlet-name>com_liferay_blogs_web_portlet_BlogsAdminPortlet</portlet-name>
       <permissions>
       <supports>
       <action-key>ACCESS_IN_CONTROL_PANEL</action-key>
       <action-key>CONFIGURATION</action-key>
       <action-key>VIEW</action-key>
       </supports>
       <site-member-defaults>
       <action-key>VIEW</action-key>
       </site-member-defaults>
       <guest-defaults>
       <action-key>VIEW</action-key>
       </guest-defaults>
       <guest-unsupported>
       <action-key>ACCESS_IN_CONTROL_PANEL</action-key>
       <action-key>CONFIGURATION</action-key>
       </guest-unsupported>
       </permissions>
       </portlet-resource>
       <portlet-resource>
       <portlet-name>com_liferay_blogs_web_portlet_BlogsPortlet</portlet-name>
       <permissions>
       <supports>
       <action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
       <action-key>ADD_TO_PAGE</action-key>
       <action-key>CONFIGURATION</action-key>
       <action-key>VIEW</action-key>
       </supports>
       <site-member-defaults>
       <action-key>VIEW</action-key>
       </site-member-defaults>
       <guest-defaults>
       <action-key>VIEW</action-key>
       </guest-defaults>
       <guest-unsupported>
       <action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
       <action-key>CONFIGURATION</action-key>
       </guest-unsupported>
       </permissions>
       </portlet-resource>
   
ブログアプリケーションは、2つのポートレットで構成されます。ブログポートレット自体と、管理者の[サイト]メニューに表示されるブログ管理ポートレットです。
ポートレットを名前で定義してから、ポートレットに対する権限をリストします。たとえば、ブログポートレットは、ADD_PORTLET_DISPLAY_TEMPLATE、ADD_TO_PAGE、CONFIGURATION、およびVIEWの4つの権限をサポートしています。ブログ管理ポートレットには、ACCESS_IN_CONTROL_PANELという追加の権限があります。これは、[サイト]メニューでエントリを表示できるユーザーを定義します。

ポートレットレベルで権限を定義したら、さまざまなタイプのユーザーにデフォルトの権限を設定できます。DTDでは、サイトメンバーとゲストのデフォルトを使用できます。ゲストはログインしていないユーザーであるため、ゲストが持つことの*できない*権限を定義する`guest-unsupported`タグもあります（言い換えれば、ユーザーはログインして識別可能でなければなりません）。

 次に、モデルリソースの権限を定義します。

## モデルリソースの権限の定義

モデルの権限の定義も同様のプロセスです。サービスモジュールの`src/main/resources/resource-actions`フォルダに`default.xml`ファイルを作成します。このファイルでは、同じ`<model-resource>`タグを使用して、最上位レベルの機能許可と個々のエンティティのアクセス許可を定義する必要があります。

これは混乱を招く可能性があるため、一部を順を追って説明します。Liferayが*ルートモデル*と呼ぶモデル権限は、Liferayが*モデル*と呼ぶストアドエンティティの権限とは別に定義されます。これは、ユーザーが実行できる以下の機能について考えるときに意味があります。

- 何かを新たに作成する
- すでに存在する何かを編集する

新たに何かを作成する（新しいブログエントリの追加など）ことは、すでに存在する何かにアクセスすることとは異なります。ブログの所有者はブログのエントリを作成または編集できる必要がありますが、ユーザーまたはゲストには既存のエントリの読み取り権限のみを付与し、作成する権限は付与しないようにする必要があります。

まだ存在しない新しい何かを作成する権限は、その機能がポートレットで公開されているかどうかに関係なく、*ルートモデル*の権限です。
既存のリソースに対するアクセス許可は、*モデル*の権限です。

これで、ルートモデルとモデルの両方の権限を定義する準備ができました。

1. まず、ファイルのスケルトンを作成します。

       <?xml version="1.0"?>
       <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">
       
       <resource-action-mapping>
       
       </resource-action-mapping>
   
2. `<resource-action-mapping>`タグ内で、`<model-resource>`タグを使用してルートモデルの権限を定義します。

       <model-resource>
       <model-name>com.liferay.blogs</model-name>
       <portlet-ref>
       <portlet-name>com_liferay_blogs_web_portlet_BlogsAdminPortlet</portlet-name>
       <portlet-name>com_liferay_blogs_web_portlet_BlogsPortlet</portlet-name>
       </portlet-ref>
       <root>true</root>
       <weight>1</weight>
       <permissions>
       <supports>
       <action-key>ADD_ENTRY</action-key>
       <action-key>PERMISSIONS</action-key>
       <action-key>SUBSCRIBE</action-key>
       </supports>
       <site-member-defaults>
       <action-key>SUBSCRIBE</action-key>
       </site-member-defaults>
       <guest-defaults />
       <guest-unsupported>
       <action-key>ADD_ENTRY</action-key>
       <action-key>PERMISSIONS</action-key>
       <action-key>SUBSCRIBE</action-key>
       </guest-unsupported>
       </permissions>
       </model-resource>
   
   モデル名（`com.liferay.blogs`）は単なるパッケージ名です。`<root>true</root>`タグは、ルートモデルとしてこれを定義します。`<portlet-ref>`要素は、モデルを使用するポートレットの名前です。`<weight>`タグはGUI内のこれらの権限の順序を定義します。定義されている権限は、ADD_ENTRY（ブログエントリの追加）、PERMISSIONS（ブログエントリでの権限の設定）、およびSUBSCRIBE（ブログエントリの作成時に通知を受け取る）です。これらの機能のいずれにもデータベースのプライマリキーを割り当てることができないため、これらはすべてルートモデルの権限です。`<portlet-ref>`タグで定義されたポートレットが初期化されると、デフォルトの権限（モデルリソースとポートレットリソースの両方）が追加されます。

3. 最後に、モデルの権限を定義します。

       <model-resource>
       <model-name>com.liferay.blogs.model.BlogsEntry</model-name>
       <portlet-ref>
       <portlet-name>com_liferay_blogs_web_portlet_BlogsAdminPortlet</portlet-name>
       <portlet-name>com_liferay_blogs_web_portlet_BlogsPortlet</portlet-name>
       </portlet-ref>
       <weight>2</weight>
       <permissions>
       <supports>
       <action-key>ADD_DISCUSSION</action-key>
       <action-key>DELETE</action-key>
       <action-key>DELETE_DISCUSSION</action-key>
       <action-key>PERMISSIONS</action-key>
       <action-key>UPDATE</action-key>
       <action-key>UPDATE_DISCUSSION</action-key>
       <action-key>VIEW</action-key>
       </supports>
       <site-member-defaults>
       <action-key>ADD_DISCUSSION</action-key>
       <action-key>VIEW</action-key>
       </site-member-defaults>
       <guest-defaults>
       <action-key>ADD_DISCUSSION</action-key>
       <action-key>VIEW</action-key>
       </guest-defaults>
       <guest-unsupported>
       <action-key>DELETE</action-key>
       <action-key>DELETE_DISCUSSION</action-key>
       <action-key>PERMISSIONS</action-key>
       <action-key>UPDATE</action-key>
       <action-key>UPDATE_DISCUSSION</action-key>
       </guest-unsupported>
       </permissions>
       </model-resource>
   
   `<root>`タグ、モデルの完全修飾クラス名、およびプライマリキーを持つエンティティで機能する権限がないことに注意してください。

## アクセス許可の構成を有効にする

最後のステップでは、権限の定義を有効にします。`default.xml`権限定義ファイルを含む各モジュールには、権限定義ファイルの場所を定義するプロパティを持つ`portlet.properties`ファイルも必要です。サービスとWebモジュールに対し、`src/main/resources`内に`portlet.properties`ファイルを作成し、次のプロパティがあることを確認します。

    resource.actions.configs=resource-actions/default.xml

ポートレットの権限、ルートモデルの権限、およびモデルの権限を定義したら、ステップ1（DRACの*D*）は完了です。これで、アクセス許可システムで定義したリソースを*登録*する準備ができました。
