package SchulVerwaltung;

public class Main {
    public static void main(String[] args) {
        School s1 = new School("HTL", "Ybbs an der Donau");


        Pupil p1 = new Pupil("d3",18, s1);
        s1.addPupil(p1);
        System.out.println(p1);
//        System.out.println(s1);


    }
}
