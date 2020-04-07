---
header-id: creating-custom-item-selector-entities
---

# カスタムアイテムセレクタエンティティの作成

[TOC levels=1-4]

アプリでは、アイテムセレクターが構成されていないアイテムをユーザーが選択する必要がありますか？ 問題ない。 新しいエンティティを作成できます。

このチュートリアルでは、アイテムセレクタの新しいエンティティを作成する方法について説明します。

## アイテムセレクタ条件の作成

最初に、エンティティの新しい基準を作成する必要があります。

1.  [`BaseItemSelectorCriterion` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterion.html)を拡張するクラスを作成します。 このクラスは、ユーザーが選択するエンティティの種類と、アイテムセレクタが返す情報を指定します。 `BaseItemSelectorCriterion` から継承されたメソッドは、この情報を取得するためのロジックを提供します。

    必要に応じて、このクラスを使用してビューに情報を渡すことができます。 たとえば、 [`JournalItemSelectorCriterion` クラス](@app-ref@/web-experience/latest/javadocs/com/liferay/journal/item/selector/criterion/JournalItemSelectorCriterion.html) は、ビューが使用できるように主キーに関する情報を渡します。
   
        public class JournalItemSelectorCriterion extends BaseItemSelectorCriterion {
       
                public JournalItemSelectorCriterion() {
                }
       
                public JournalItemSelectorCriterion(long resourcePrimKey) {
                        _resourcePrimKey = resourcePrimKey;
                }
       
                public long getResourcePrimKey() {
                        return _resourcePrimKey;
                }
       
                public void setResourcePrimKey(long resourcePrimKey) {
                        _resourcePrimKey = resourcePrimKey;
                }
       
                private long _resourcePrimKey;
       
        }

    | **注：** Criterionフィールドはシリアル化可能で、|を公開する必要がありますパブリックの空のコンストラクター（上記を参照）。

2.  [`BaseItemSelectorCriterionHandler` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterionHandler.html)を実装するOSGiコンポーネントクラスを作成します。 各基準には、適切な選択ビューの取得を担当する基準ハンドラーが必要です。

    この例では、 `TaskItemSelectorCriterion` クラスの基準ハンドラーを作成します。
   
        @Component(service = ItemSelectorCriterionHandler.class)
        public class TaskItemSelectorCriterionHandler extends 
            BaseItemSelectorCriterionHandler<TaskItemSelectorCriterion> {
       
            public Class <TaskItemSelectorCriterion> getItemSelectorCriterionClass() {
                return TasksItemSelectorCriterionHandler.class;
            }
       
            @Activate
            @Override
            protected void activate(BundleContext bundleContext) {
                    super.activate(bundleContext);
            }
       
        }

    このOSGiコンポーネントをアクティブ化するには、 `@Activate` および `@Override` トークンが必要です。

アプリのニーズによっては、戻り値の型を作成する必要がない場合があります。 エンティティが既存の戻り値の型で既に定義されている情報を返す場合、代わりにその戻り値の型を使用できます。 参照ドキュメント [Item Selector Criterion and Return Types](/docs/7-1/reference/-/knowledge_base/r/item-selector-criterion-and-return-types)利用可能なデフォルトの基準を表示できます。 ただし、既存の戻り値型でカバーされていない情報をエンティティが返す場合は、次に新しい戻り値型を作成する必要があります。

## アイテムセレクターの戻り値の型の作成

戻り値の型を作成するには、 [`ItemSelectorReturnType` インターフェイス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html)を実装するクラスを作成する必要があります。 このようなクラスには、そのエンティティにちなんで名前を付け、 `ItemSelectorReturnType`接尾辞を付ける必要があります。 たとえば、タスクアイテムの戻り値の型を作成する場合、その戻り値の型クラスは `TaskItemSelectorReturnType`ます。 このような `* ItemSelectorReturnType` クラスは、アイテムセレクターによって識別子として使用され、情報自体を返しません。 戻り型クラスは、戻り型をアイテムセレクタビューに接続するAPIです。 戻り値の型が使用されるたびに、ビューは適切な情報が返されることを保証する必要があります。 Javadocとして、戻り値の型が返す情報と形式を指定することをお勧めします。 たとえば、戻り型クラス `TaskItemSelectorReturnType`例を次に示します。

    /**
    * This return type should return the task ID and the user who
    * created the task as a string.
    * 
    * @author Joe Bloggs
    */
    public class TaskItemSelectorReturnType implements ItemSelectorReturnType{
    
    }

よくやった\！ 新しいエンティティの基準クラスと戻り値型クラスをアプリで使用して、アイテムセレクターのURLを作成できます。 アイテムセレクターURLを取得する方法については、チュートリアル [アイテムセレクターを使用したエンティティの選択](/docs/7-1/tutorials/-/knowledge_base/t/selecting-entities-using-the-item-selector)を参照してください。

アイテムセレクターのURLを取得すると、選択ビューは、戻り値の型で指定された適切なエンティティ情報を返す役割を果たします。 エンティティのこのような選択ビューを作成するには、チュートリアル [カスタムアイテムセレクタビューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-item-selector-views)参照してください。

## 関連トピック

[Item Selector APIのコンポーネントについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-item-selector-apis-components)

[アイテムセレクターを使用したエンティティの選択](/docs/7-1/tutorials/-/knowledge_base/t/selecting-entities-using-the-item-selector)

[カスタムアイテムセレクタービューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-item-selector-views)
