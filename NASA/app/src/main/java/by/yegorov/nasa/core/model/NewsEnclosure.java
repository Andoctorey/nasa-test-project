package by.yegorov.nasa.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NewsEnclosure implements Parcelable {

    @SerializedName("link")
    private String link;

    @SerializedName("type")
    private String type;

    public NewsEnclosure() {
        //for parcelable
    }

    public String getLink() {
        return link;
    }

    public String getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.link);
        dest.writeString(this.type);
    }

    protected NewsEnclosure(Parcel in) {
        this.link = in.readString();
        this.type = in.readString();
    }

    public static final Creator<NewsEnclosure> CREATOR = new Creator<NewsEnclosure>() {
        @Override
        public NewsEnclosure createFromParcel(Parcel source) {
            return new NewsEnclosure(source);
        }

        @Override
        public NewsEnclosure[] newArray(int size) {
            return new NewsEnclosure[size];
        }
    };
}
