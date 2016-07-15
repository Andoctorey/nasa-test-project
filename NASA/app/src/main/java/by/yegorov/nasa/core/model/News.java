package by.yegorov.nasa.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@SuppressWarnings("unused")
public class News implements Parcelable {

    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    @SerializedName("guid")
    private String guid;

    @SerializedName("pubDate")
    private Date pubDate;

    @SerializedName("author")
    private String author;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("description")
    private String description;

    @SerializedName("content")
    private String content;

    @SerializedName("enclosure")
    private NewsEnclosure enclosure;

    public News() {
        //for parcelable
    }

    public String getSafeTitle() {
        return title != null ? title : "";
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public NewsEnclosure getSafeEnclosure() {
        return enclosure != null ? enclosure : new NewsEnclosure();
    }

    public String getGuid() {
        return guid;
    }

    public String getLink() {
        return link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.guid);
        dest.writeLong(this.pubDate != null ? this.pubDate.getTime() : -1);
        dest.writeString(this.author);
        dest.writeString(this.thumbnail);
        dest.writeString(this.description);
        dest.writeString(this.content);
        dest.writeParcelable(this.enclosure, flags);
    }

    protected News(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        this.guid = in.readString();
        long tmpPubDate = in.readLong();
        this.pubDate = tmpPubDate == -1 ? null : new Date(tmpPubDate);
        this.author = in.readString();
        this.thumbnail = in.readString();
        this.description = in.readString();
        this.content = in.readString();
        this.enclosure = in.readParcelable(NewsEnclosure.class.getClassLoader());
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
