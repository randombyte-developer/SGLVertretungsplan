package de.randombyte.sglvertretungsplan.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.randombyte.sglvertretungsplan.SetList;

public class Vertretung implements Parcelable {

    private String zeitraum;
    private String klasse;
    private String vertreter;
    private String statt;
    private String fach;
    private String raum;
    private String verlegung;
    private String art;
    private String zusatzinfo;

    public Vertretung() {
    }

    private Vertretung(Parcel source) {
        zeitraum = source.readString();
        klasse = source.readString();
        vertreter = source.readString();
        statt = source.readString();
        fach = source.readString();
        raum = source.readString();
        verlegung = source.readString();
        art = source.readString();
        zusatzinfo = source.readString();
    }

    public String getZeitraum() {
        return zeitraum;
    }

    public void setZeitraum(String zeitraum) {
        this.zeitraum = zeitraum;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public String getVertreter() {
        return vertreter;
    }

    public void setVertreter(String vertreter) {
        this.vertreter = vertreter;
    }

    public String getStatt() {
        return statt;
    }

    public void setStatt(String statt) {
        this.statt = statt;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }

    public String getRaum() {
        return raum;
    }

    public void setRaum(String raum) {
        this.raum = raum;
    }

    public String getVerlegung() {
        return verlegung;
    }

    public void setVerlegung(String verlegung) {
        this.verlegung = verlegung;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getZusatzinfo() {
        return zusatzinfo;
    }

    public void setZusatzinfo(String zusatzinfo) {
        this.zusatzinfo = zusatzinfo;
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Vertretung> CREATOR = new Creator<Vertretung>() {
        @Override
        public Vertretung createFromParcel(Parcel source) {
            return new Vertretung(source);
        }

        @Override
        public Vertretung[] newArray(int size) {
            return new Vertretung[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(zeitraum);
        dest.writeString(klasse);
        dest.writeString(vertreter);
        dest.writeString(statt);
        dest.writeString(fach);
        dest.writeString(raum);
        dest.writeString(verlegung);
        dest.writeString(art);
        dest.writeString(zusatzinfo);
    }

    //Generated by IntelliJ
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertretung that = (Vertretung) o;

        if (!zeitraum.equals(that.zeitraum)) return false;
        if (!klasse.equals(that.klasse)) return false;
        if (!vertreter.equals(that.vertreter)) return false;
        if (!statt.equals(that.statt)) return false;
        if (!fach.equals(that.fach)) return false;
        if (!raum.equals(that.raum)) return false;
        if (!verlegung.equals(that.verlegung)) return false;
        if (!art.equals(that.art)) return false;
        return zusatzinfo.equals(that.zusatzinfo);

    }

    //Generated by IntelliJ
    @Override
    public int hashCode() {
        int result = zeitraum.hashCode();
        result = 31 * result + klasse.hashCode();
        result = 31 * result + vertreter.hashCode();
        result = 31 * result + statt.hashCode();
        result = 31 * result + fach.hashCode();
        result = 31 * result + raum.hashCode();
        result = 31 * result + verlegung.hashCode();
        result = 31 * result + art.hashCode();
        result = 31 * result + zusatzinfo.hashCode();
        return result;
    }

    public static List<Vertretung> getFiltered(List<Vertretung> vertretungList, Profile profile) {

        List<Vertretung> filterResult = new SetList<>();

        for (Vertretung vertretung : vertretungList) {
            if (profile.isOberstufe()) {
                if (profile.getStufe().equalsIgnoreCase(vertretung.getKlasse())) { //e.g. Q1
                    for (Kurs kurs : profile.getKursList()) {
                        if (vertretung.getFach().equalsIgnoreCase(kurs.toString())) { //e.g. GK06-MU
                            filterResult.add(vertretung);
                        }
                    }
                }
            } else {
                if (vertretung.getKlasse().equalsIgnoreCase(profile.toString())) {
                    filterResult.add(vertretung);
                }
            }
        }

        return filterResult;
    }
}
