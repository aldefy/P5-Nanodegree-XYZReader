package com.example.xyzreader.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import co.uk.rushorm.core.Rush;
import co.uk.rushorm.core.RushCallback;
import co.uk.rushorm.core.RushCore;

/**
 * Created by aditlal on 26/03/16.
 */
public class DataModel implements Parcelable, Rush {
    private String published_date;

    @SerializedName("id")
    private String mId;

    private String body;

    private String author;

    private String title;

    private String thumb;

    private String photo;

    @SerializedName("aspect_ratio")
    private float aspect_ratio;

    public DataModel() {
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public float getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(float aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }

    @Override
    public String toString() {
        return "ClassPojo [published_date = " + published_date + ", id = " + mId + ", body = " + body + ", author = " + author + ", title = " + title + ", thumb = " + thumb + ", photo = " + photo + ", aspect_ratio = " + aspect_ratio + "]";
    }

    @Override
    public void save() {
        RushCore.getInstance().save(this);
    }

    @Override
    public void save(RushCallback callback) {
        RushCore.getInstance().save(this, callback);
    }

    @Override
    public void delete() {
        RushCore.getInstance().delete(this);
    }

    @Override
    public void delete(RushCallback callback) {
        RushCore.getInstance().delete(this, callback);
    }

    @Override
    public String getId() {
        return RushCore.getInstance().getId(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(published_date);
        dest.writeString(mId);
        dest.writeString(body);
        dest.writeString(author);
        dest.writeString(thumb);
        dest.writeString(photo);
        dest.writeFloat(aspect_ratio);

    }

    // Creator
    public static final Parcelable.Creator<DataModel> CREATOR
            = new Parcelable.Creator<DataModel>() {
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    // "De-parcel object
    public DataModel(Parcel in) {
        title = in.readString();
        published_date = in.readString();
        mId = in.readString();
        body = in.readString();
        author = in.readString();
        thumb = in.readString();
        photo = in.readString();
        aspect_ratio = in.readFloat();
    }

}

