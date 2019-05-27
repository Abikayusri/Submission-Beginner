package abika.sinaudicodingjava.submission_hokage.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Hokage implements Parcelable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getDie() {
        return die;
    }

    public void setDie(String die) {
        this.die = die;
    }

    public String getJutsu() {
        return jutsu;
    }

    public void setJutsu(String jutsu) {
        this.jutsu = jutsu;
    }

    private String name, title, photo, bio, born, die, jutsu;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.title);
        dest.writeString(this.photo);
        dest.writeString(this.bio);
        dest.writeString(this.born);
        dest.writeString(this.die);
        dest.writeString(this.jutsu);
    }

    public Hokage() {
    }

    protected Hokage(Parcel in) {
        this.name = in.readString();
        this.title = in.readString();
        this.photo = in.readString();
        this.bio = in.readString();
        this.born = in.readString();
        this.die = in.readString();
        this.jutsu = in.readString();
    }

    public static final Creator<Hokage> CREATOR = new Creator<Hokage>() {
        @Override
        public Hokage createFromParcel(Parcel source) {
            return new Hokage(source);
        }

        @Override
        public Hokage[] newArray(int size) {
            return new Hokage[size];
        }
    };
}
