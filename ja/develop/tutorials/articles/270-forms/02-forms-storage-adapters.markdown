---
header-id: forms-storage-adapters
---

# フォームストレージアダプター

[TOC levels=1-4]

ユーザーがフォームレコードを追加すると、Forms APIはストレージアダプターAPIを介してリクエストの処理をルーティングします。 フォームエントリに対して実行される他の *CRUD* 操作（読み取り、更新、および削除操作）についても同様です。 ストレージサービスのデフォルトの実装は `JSONStorageAdapter`と呼ばれ、その名前が示すように、 `StorageAdapter` インターフェースを実装してフォームエントリデータのJSONストレージを提供します。

DDMバックエンドはでき *適応* フォームレコードの他のデータ・ストレージ・フォーマットに。 データをXMLで保存したいですか？ YAML？ 問題ない。 ストレージAPIは、フォームエントリのデータベーステーブルに入力するために使用される通常のサービスコールから分離されているため、開発者はLiferayデータベースの外部にフォームデータを保存することもできます。

`StorageAdapter` インターフェースを実装するOSGiコンポーネント</a> を

書くことにより、フォームエントリを保存する独自の形式を定義します。 インターフェイスは *CRUD* アプローチに従っているため、実装するにはフォーム値を作成、読み取り、更新、削除するメソッドを記述する必要があります。</p> 

| **注：** 新しいストレージアダプターを追加する場合、新しいストレージアダプターでのみ使用できます。フォーム。 既存のすべてのフォームは、選択したアダプターを引き続き使用します（デフォルトではJSON）|作成時に別のストレージアダプターを使用することはできません。選択されました。

このチュートリアルのストレージアダプターの例では、フォームデータをシリアル化して、ファイルシステムに格納されている単純なファイルに格納します。

![図1：フォームレコードのストレージタイプを選択します。](../../images/forms-storage-type.png)

これらのコードスニペットには、サービスを使用する最初のメソッドの直下に呼び出しているサービスへの参照が含まれていることに注意してください。 これらをクラスの最後に配置するのは、Liferayコードの規則です。



## ストレージアダプターの実装

最初に、クラスを `StorageAdapter` 実装を提供する `コンポーネント` として宣言します。 ストレージアダプターを実装するには、抽象 `BaseStorageAdapter` クラスを拡張します。

    @Component(service = StorageAdapter.class)
    public class FileSystemStorageAdapter extends BaseStorageAdapter {
    



### ステップ1：ストレージタイプに名前を付ける

抽象クラスに基本実装のない唯一のメソッドは `getStorageType`です。 ファイルシステムストレージの例では、 `"File System"`返すようにします。

    @Override
    public String getStorageType() {
        return "File System";
    }
    

`getStorageType` は、フォーム作成者がフォームのストレージタイプを選択するときにUIに表示されるものを決定するため、人間が読める文字列を返します。 ここで返す文字列値は、 `StorageAdapterRegistry`のストレージアダプターのマップに追加されます。



### ステップ2：CRUDメソッドをオーバーライドする

次に、 `doCreateMethod` をオーバーライドして、一意のファイルIDで各フォームレコードを識別する `長い` を返します。

    @Override
    protected long doCreate(
        long companyId, long ddmStructureId, DDMFormValues ddmFormValues, 
        ServiceContext serviceContext)
        throws Exception {
    
        validate(ddmFormValues, serviceContext);
    
        long fileId = _counterLocalService.increment();
    
        DDMStructureVersion ddmStructureVersion =
            _ddmStructureVersionLocalService.getLatestStructureVersion(
                ddmStructureId);
    
        long classNameId = PortalUtil.getClassNameId(
            FileSystemStorageAdapter.class.getName());
    
        _ddmStorageLinkLocalService.addStorageLink(
            classNameId, fileId, ddmStructureVersion.getStructureVersionId(),
            serviceContext);
    
        saveFile(
            ddmStructureVersion.getStructureVersionId(), fileId, ddmFormValues);
    
        return fileId;
    }
    
    @Reference
    private CounterLocalService _counterLocalService;
    
    @Reference
    private DDMStorageLinkLocalService _ddmStorageLinkLocalService;
    
    @Reference
    private DDMStructureVersionLocalService _ddmStructureVersionLocalService;
    

このメソッドの最初の行（および後続の `doUpdate` メソッド）は、まだ記述されていない [`validate`](#step-3-validating-form-entries) メソッドを呼び出すため、そのメソッドを記述するまでクラスを保存しないでください。

ファイルIDを返すことに加えて、 `DDMStorageLinkLocalService`介してストレージリンクを追加します。 DDMストレージリンクは、各フォームレコードをフォームを支えるDDM構造に関連付けます。

`addStorageLink` によって取得の方法は、クラス名IDをとる `PortalUtil.getClassNameId`、 `FILEID` 、構造のバージョンID、およびサービスコンテキスト（ファイル記憶タイプの主キーとして使用されます）。 `saveFile` メソッドへの呼び出しもあります。このメソッドは、フォームレコードの値をシリアル化し、2つの追加ユーティリティメソッド（`getStructureFolder` および `getFile`）を使用して `java.io.File` オブジェクトを書き込みます。 他にもいくつかのユーティリティメソッドが呼び出されます。

    private File getFile(long structureId, long fileId) {
        return new File(
            getStructureFolder(structureId), String.valueOf(fileId));
    }
    
    private File getStructureFolder(long structureId) {
        return new File(String.valueOf(structureId));
    }
    
    private void saveFile(
            long structureVersionId, long fileId, DDMFormValues formValues)
        throws IOException {
    
        String serializedDDMFormValues = _ddmFormValuesJSONSerializer.serialize(
            formValues);
    
        File formEntryFile = getFile(structureVersionId, fileId);
    
        FileUtil.write(formEntryFile, serializedDDMFormValues);
    }
    
    @Reference
    private DDMFormValuesJSONSerializer _ddmFormValuesJSONSerializer;
    

`write` メソッドの呼び出しに注意してください。`FileUtil` は、 `java.io.File` オブジェクトを操作するためのLiferayユーティリティクラスです。 デフォルトでは、 `write` メソッドは、システムのユーザーホームフォルダーにデータを書き込みます。

`classPK`を使用して `File` を削除するには、 `doDeleteByClass` メソッドをオーバーライドし` 。</p>

<pre><code>@Override
protected void doDeleteByClass(long classPK) throws Exception {
    DDMStorageLink storageLink =
        _ddmStorageLinkLocalService.getClassStorageLink(classPK);

    FileUtil.delete(getFile(storageLink.getStructureId(), classPK));

    _ddmStorageLinkLocalService.deleteClassStorageLink(classPK);
}
`</pre> 

ファイルを削除したら、そのストレージリンクも削除する必要があります。 このロジックには `doDeleteByDDMStructure` を使用し` 。</p>

<pre><code>@Override
protected void doDeleteByDDMStructure(long ddmStructureId)
    throws Exception {

    FileUtil.deltree(getStructureFolder(ddmStructureId));

    _ddmStorageLinkLocalService.deleteStructureStorageLinks(ddmStructureId);
}
`</pre> 

フォームレコードの値を、それらが書き込まれた `File` オブジェクトから取得するには、 `doGetDDMFormValues`オーバーライドします。

    @Override
    protected DDMFormValues doGetDDMFormValues(long classPK) throws Exception {
        DDMStorageLink storageLink =
            _ddmStorageLinkLocalService.getClassStorageLink(classPK);
    
        DDMStructureVersion structureVersion =
            _ddmStructureVersionLocalService.getStructureVersion(
                storageLink.getStructureVersionId());
    
        String serializedDDMFormValues = FileUtil.read(
            getFile(structureVersion.getStructureVersionId(), classPK));
    
        return _ddmFormValuesJSONDeserializer.deserialize(
            structureVersion.getDDMForm(), serializedDDMFormValues);
    }
    
    @Reference
    private DDMFormValuesJSONDeserializer _ddmFormValuesJSONDeserializer;
    

レコードの値を上書きできるように、 `doUpdate` メソッドをオーバーライドします。 この例では、前述の `saveFile` ユーティリティメソッドを呼び出します。

    @Override
    protected void doUpdate(
            long classPK, DDMFormValues ddmFormValues,
            ServiceContext serviceContext)
        throws Exception {
    
        validate(ddmFormValues, serviceContext);
    
        DDMStorageLink storageLink =
            _ddmStorageLinkLocalService.getClassStorageLink(classPK);
    
        saveFile(
            storageLink.getStructureVersionId(), storageLink.getClassPK(),
            ddmFormValues);
    }
    

CRUDロジックを定義したら、ストレージアダプターを展開してテストします。



## ステップ3：フォームエントリの検証

上記の `doCreate` および `doUpdate` メソッドには、両方とも次の行が含まれます。

    validate(ddmFormValues, serviceContext);
    

ストレージアダプターはユーザーが入力したデータを処理するため、エントリに適切なデータのみが含まれていることを検証することが重要です。 `StorageAdapter``validate` メソッドを追加し` 。</p>

<pre><code>protected void validate(
    DDMFormValues ddmFormValues, ServiceContext serviceContext)
    throws Exception {

    boolean validateDDMFormValues = GetterUtil.getBoolean(
        serviceContext.getAttribute("validateDDMFormValues"), true);

    if (!validateDDMFormValues) {
        return;
    }

    _ddmFormValuesValidator.validate(ddmFormValues);
}
`</pre> 

必ず3つのことを実行してください。

1.  `validateDDMFormValues` 属性の値を取得します。

2.  `validateDDMFormValues` がfalseの場合、何もせずに検証を終了します。
   
   ユーザーが専用リンクでフォームにアクセスすると、進行中のフォーム値の定期的な自動保存プロセスがあります。 ユーザーがフォームの *Submit* ボタンを押すまでこのデータを検証する必要はありません。そのため、自動保存プロセスは `validateDDMFormValues` 属性を `false`ます。

3.  それ以外の場合は、 `DDMFormValuesValidator` サービスからvalidateメソッドを呼び出します。

必要なロジックを配置したら、ストレージアダプターを展開してテストします。



## ストレージアダプターの有効化

ストレージアダプタは、個々のフォームレベルで有効になります。 新しいフォームを作成し、フォームを保存または公開する前にストレージアダプター *を選択します*。 最初にフォームを保存するまで待つと、デフォルトのストレージアダプターがすでにフォームに割り当てられており、この設定は編集できなくなります。

1.  [サイト]メニュー→[コンテンツ]→[フォーム]に移動し、[ *追加* ]ボタン（![Add](../../images/icon-add.png)）をクリックします。

2.  フォームビルダビューで、 *オプション* ボタン（![Options](../../images/icon-options.png)）をクリックし、 *設定* ウィンドウを開きます。

3.  「 *という名前の選択リストフィールドから「ストレージタイプの選択」*、目的のタイプを選択し、「 *完了*をクリックします。

これで、フォームのすべてのエントリが目的の形式で保存されます。
