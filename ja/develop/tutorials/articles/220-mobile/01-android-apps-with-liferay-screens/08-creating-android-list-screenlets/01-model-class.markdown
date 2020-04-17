---
header-id: creating-the-model-class
---

# モデルクラスの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

エンティティは、LiferayからJSON形式で返されます。 アプリでこれらの結果を効率的に使用するには、Liferayのエンティティを表すモデルオブジェクトに変換する必要があります。 Screensの [`BaseListInteractor`](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java) はJSONエンティティを `Map` オブジェクトに変換しますが、アプリで使用するためにこれらを適切なエンティティオブジェクトに変換する必要があります。これは、モデルクラスを介して行います。

たとえば、ブックマークリストスクリーンレットのモデルクラス（`Bookmark`）は、ブックマークのURLとその他のデータを含む `ブックマーク` オブジェクトを作成します。 URLへの迅速なアクセスを保証するために、 `Map<String, Object>` 取得するコンストラクターは `Map` からそれを抽出し、 `url` 変数に設定します。 他のデータへのアクセスを許可するために、同じコンストラクターは `マップ` 全体を `値` 変数に設定します。 ゲッターとセッターに加えて、このクラスの残りは [Androidの `Parcelable` インターフェース](https://developer.android.com/reference/android/os/Parcelable.html)実装します：

    import android.os.Parcel;
    import android.os.Parcelable;
    
    import java.util.Map;
    
    public class Bookmark implements Parcelable {
    
        private String url;
        private Map values;
    
        public static final Creator<Bookmark> CREATOR = new Creator<Bookmark>() {
            @Override
            public Bookmark createFromParcel(Parcel in) {
                return new Bookmark(in);
            }
    
            @Override
            public Bookmark[] newArray(int size) {
                return new Bookmark[size];
            }
        };
    
        public Bookmark() {
            super();
        }
    
        protected Bookmark(Parcel in) {
            url = in.readString();
        }
    
        public Bookmark(Map<String, Object> stringObjectMap) {
            url = (String) stringObjectMap.get("url");
            values = stringObjectMap;
        }
    
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(url);
        }
    
        @Override
        public int describeContents() {
            return 0;
        }
    
        public String getUrl() {
            return url;
        }
    
        public Map getValues() {
            return values;
        }
    
        public void setValues(Map values) {
            this.values = values;
        }
    
    }

モデルクラスができたので、スクリーンレットのビューを作成できます。

## 関連トピック

[ビューを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-view)

[インタラクターの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor-0)

[スクリーンレットクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-screenlet-class-0)
