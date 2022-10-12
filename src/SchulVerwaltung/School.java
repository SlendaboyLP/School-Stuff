package SchulVerwaltung;

import java.util.ArrayList;

public class School {

    String name;
    String address;

    ArrayList<Pupil> pupils = new ArrayList<Pupil>();

    public School(String name, String address) {
        this.name = name;
        this.address = address;
        this.pupils = new ArrayList<Pupil>();
    }

    void removePupil(Pupil p){
        this.pupils.remove(p);
    }

    void addPupil(Pupil p) {
        this.pupils.add(p);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    @Override
//    public String toString() {
//        return "School{" +
//                "name='" + name + '\'' +
//                ", address='" + address + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pupils=" + pupils +
                '}';
    }
}