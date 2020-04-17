---
header-id: actionable-dynamic-queries
---

# 実用的な動的クエリ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

100万人を超えるユーザーがいて、一部のユーザーに対して何らかの一括更新を実行するとします。 1つの方法は、動的クエリを使用して、問題のユーザーのリストを取得することです。 メモリにロードしたら、リストをループして各ユーザーを更新できます。 ただし、100万人を超えるユーザーがいる場合、このような操作のメモリコストは非常に大きくなります。 一般に、動的クエリを使用して多数のService Builderエンティティを取得するには、大量のメモリと時間が必要です。

Liferayアクション可能な動的クエリは、この問題を解決します。 アクション可能な動的クエリは、ページネーション戦略を使用して、一度に少数のエンティティのみをメモリにロードし、各エンティティで処理を実行します（つまり、 *アクション*実行し* ）。 そのため、動的クエリを使用して100万人のユーザーをメモリにロードし、それぞれに対して処理を実行する代わりに、アクション可能な動的クエリを使用する方がはるかに優れた戦略です。 これにより、100万人のユーザーを処理できますが、一度にメモリにロードされるのはごくわずかです。</p>

アクション可能な動的クエリを使用する方法は次のとおりです。

1.  `getActionableDynamicQuery` メソッドを呼び出して、 `* LocalService` から [`ActionableDynamicQuery`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/ActionableDynamicQuery.html) 取得します。

2.  クエリの `setAddCriteriaMethod` および `setAddOrderCriteriaMethod` メソッドを使用して、クエリ条件と制約を追加します。

3.  `setPerformActionMethod`を使用して、一致するエンティティで実行するアクションを設定します。

4.  クエリの `performActions` メソッドを呼び出して、一致する各エンティティでアクションを実行します。

サンプルポートレットのこのメソッドは、アクション可能な動的クエリを作成し、クエリ制限とアクションを追加して、クエリを実行します。

    protected void massUpdate() {
        ActionableDynamicQuery adq = _barLocalService.getActionableDynamicQuery();
    
        adq.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
    
            @Override
            public void addCriteria(DynamicQuery dynamicQuery) {
                dynamicQuery.add(RestrictionsFactoryUtil.lt("field3", 100));
            }
    
        });
    
        adq.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Bar>() {
    
            @Override
            public void performAction(Bar bar) {
                int field3 = bar.getField3();
                field3++;
                bar.setField3(field3);
                _barLocalService.updateBar(bar);
            }
    
        });
    
        try {
            adq.performActions();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

このメソッド例は、特定の条件に一致する `Bar` エンティティでアクション可能な動的クエリを実行する方法を示しています。

1.  ローカルサービス `BarLocalService`から [`ActionableDynamicQuery`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/ActionableDynamicQuery.html) 取得し

 。</p> 
   
        ActionableDynamicQuery adq = _barLocalService.getActionableDynamicQuery();
       
   
   | **注：** Service Builderはメソッドを生成します `getActionableDynamicQuery（）` in |各エンティティの `* LocalService` インターフェイスと、各エンティティの| `* BaseLocalServiceImpl` クラス。 | | @Transactional（propagation = Propagation.SUPPORTS、readOnly = true）| public ActionableDynamicQuery getActionableDynamicQuery（）;</li> 
   
   2  `100`未満の `field3` 値に一致するクエリ条件を設定します。
  
       adq.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
      
            @Override
            public void addCriteria(DynamicQuery dynamicQuery) {
                dynamicQuery.add(RestrictionsFactoryUtil.lt("field3", 100));
            }
      
        });
      

3  実行するアクションを設定します。 アクションは、一致するエンティティの `field3` 値を増分します。
  
       adq.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Bar>() {
      
            @Override
            public void performAction(Bar bar) {
                int field3 = bar.getField3();
                field3++;
                bar.setField3(field3);
                _barLocalService.updateBar(bar);
            }
      
        });
      

4  一致する各エンティティでアクションを実行します。
  
       try {
           adq.performActions();
       }
       catch (Exception e) {
           e.printStackTrace();
       }
      </ol> 

アクション可能な動的クエリを使用すると、小さなグループ内の多数のエンティティを操作できます。 エンティティを更新するための効率的で高性能な方法です。



## 関連トピック

[Service Builder Webサービス](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-web-services)

[ローカルサービスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-local-services)

[ローカルサービスの呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-local-services)
