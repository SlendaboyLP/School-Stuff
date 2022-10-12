package SchulVerwaltung;

public class Pupil {

    String name;
    int age;

    School school;


    public Pupil(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Pupil(String name, int age, School school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school.getName() +
                '}';
    }
}
