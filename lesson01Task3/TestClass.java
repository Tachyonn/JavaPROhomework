package JavaPROsrc.lesson01Task3;

public class TestClass {
    @Save
    private int a;
    @Save
    public String string;
    boolean bool;
    @Save
    private char aChar;

    public TestClass() {
    }

    public void someMethod() {
        System.out.println("This is test method.");
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }
}
