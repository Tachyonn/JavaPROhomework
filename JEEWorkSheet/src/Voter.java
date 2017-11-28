public class Voter {
    private String name;
    private int age;

    public Voter(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Voter() {
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

    @Override
    public String toString() {
        return "Name:" + name + ", age:" + age;
    }
}