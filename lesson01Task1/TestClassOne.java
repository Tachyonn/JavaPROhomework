package JavaPROsrc.lesson01Task1;

public class TestClassOne {
    public TestClassOne() {
    }

    @MyAnnot(a = 2, b = 5)
    public static void sout(int a, int b) {
        System.out.println(a + ", " + b);
    }

}
