---
header-id: model-listeners
---

# モデルリスナーの作成

[TOC levels=1-4]

モデルリスナーは、[`ModelListener`インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/ModelListener.html)を実装します。
これらは、モデル上の永続イベントをリッスンし、何らかの応答を行う（イベントの前後のいずれか）ために使用されます。

モデルリスナーは、エンティティのデータベーステーブルまたはマッピングテーブル（`users_roles`など）の`作成`、`削除`、または`更新`の試行に応答して軽量アクションを実行するように設計されています。サポートされている使用例を以下に示します。

- 監査リスナー：個別のデータベースに、エンティティのデータベーステーブルの更新に関する情報を記録します。
- キャッシュクリアリスナー：カスタムコードのパフォーマンスを向上させるために追加されたキャッシュをクリアします。
- 検証リスナー：モデルの属性値がデータベースに永続化される前に、追加の検証を実行します。
- エンティティ更新リスナー：エンティティテーブルが更新されたときに、追加の処理を実行します。たとえば、アカウントに変更が加えられたときにユーザーに通知します。

また、予測できない破損や悩みの種になる可能性があるため推奨されない使用例もあります。

- `onBeforeUpdate`呼び出しにおけるモデル属性の設定。モデルリスナーが呼び出される前に、他のデータベーステーブルの値がすでに更新されている場合、データベースは同期しなくなります。エンティティの属性の設定方法を変更するには、代わりに[サービスラッパー](/docs/7-1/tutorials/-/knowledge_base/t/customizing-liferay-services-service-wrappers)の使用を検討してください。
- モデルのラッピング。データベースからレコードを取得する際、モデルリスナーは呼び出されません。
- 並列処理を行うワーカースレッドを作成し、リスナーを介して更新したデータにクエリを行う。モデルリスナーは、データベーストランザクションが完了する**前 に**呼び出されるため（`onAfter...`メソッドでも）、データベーストランザクションが完了する前にクエリを実行できます。

モデルに既存のリスナーがない場合、あなたのモデルリスナーのみが実行されます。ただし、単一のモデルに複数のリスナーが存在する可能性があり、リスナーの実行順序を制御することはできません。

次の2つの簡単な操作を行うことで、モジュール内にモデルリスナーを作成できます。

- `ModelListener`を実装する
- LiferayのOSGiランタイムにサービスを登録する

## モデルリスナークラスの作成

[`BaseModelListener`クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/BaseModelListener.html)を拡張する`-ModelListener` クラスを作成します。

    package ...;
    
    import ...;
    
    public class CustomEntityListener extends BaseModelListener<CustomEntity> {
    
    // Override one or more methods from the ModelListener interface.
    
    }

クラスの本体で、[`ModelListener`インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/ModelListener.html)のメソッドをオーバーライドします。 
使用可能なメソッドについては、この記事の最後にリスト化して説明しています。

モデルリスナークラスでは、パラメーター化された型（たとえば、上記スニペットでは`CustomEntity`）は、リスナーを登録するモデルクラスをリスナーの`ServiceTrackerCustomizer`に伝えます。

## モデルリスナーサービスの登録

LiferayのOSGiランタイムにサービスを登録して、すぐにアクティベートします。Declarative Servicesを使用する場合は、コンポーネントで`service= ModelListener.class`および`immediate=true`を設定します。

    @Component(
    immediate = true,
    service = ModelListener.class
    )

モデルリスナーの準備については以上です。次に、応答できるモデルイベントについて学習します。

## 永続イベントのリスニング

`ModelListener`インターフェイスは、モデルのイベントをリッスンする多くの機会を提供します。

- **`onAfterAddAssociation`**：2つのモデル間に関連がある場合（マッピングテーブルがある場合）、関連レコードを追加した後でこのメソッドを使用して操作を実行します。
- **`onAfterCreate`**：永続レイヤーの`create`メソッドを呼び出した後で、このメソッドを使用して操作を実行します。
- **`onAfterRemove`**： 永続レイヤーの`remove`メソッドを呼び出した後で、このメソッドを使用して操作を実行します。
- **`onAfterRemoveAssociation`**：2つのモデル間に関連がある場合（マッピングテーブルがある場合）、関連レコードを削除した後で操作を実行します。
- **`onAfterUpdate`**：永続レイヤーの`update`メソッドを呼び出した後で、このメソッドを使用して操作を実行します。
- **`onBeforeAddAssociation`**：2つのモデル間に関連がある場合（マッピングテーブルがある場合）、マッピングテーブルを追加する前に操作を実行します。
- **`onBeforeCreate`**：永続レイヤーの`create`メソッドを呼び出す前に、このメソッドを使用して操作を実行します。
- **`onBeforeRemove`**：永続レイヤーの`remove`メソッドを呼び出す前に、このメソッドを使用して操作を実行します。
- **`onBeforeRemoveAssociation`**：2つのモデル間に関連がある場合（マッピングテーブルがある場合）、マッピングテーブルから削除する前に操作を実行します。
- **`onBeforeUpdate`**：永続レイヤーの`update`メソッドを呼び出す前に、このメソッドを使用して操作を実行します。

特に`remove`および`update`メソッドにおいて、Liferayのソースファイル`portal-kernel/src/com/liferay/portal/kernel/service/persistence/impl/BasePersistenceImpl.java`を見てみると、モデルの永続イベントの前（`onBefore...`ケース）と後（`onAfter...`ケース）でモデルリスナーがどのように考慮されているかがわかります。

モデルリスナーの作成方法を理解したところで、モデルリスナーがスタンドアロンプロジェクトとして、またはアプリケーションの内部で有用であることに留意してください。ユーザーがLiferayに追加されるたびにアプリケーションが何らかの処理（カスタムエンティティの追加など）を行う必要がある場合は、アプリケーションの内部にモデルリスナーを含めることができます。

## 関連トピック

[Upgrading Model Listener Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-model-listener-hooks)

[Service Builder](/docs/7-0/tutorials/-/knowledge_base/t/service-builder)

[Service Builder Persistence](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-persistence)
