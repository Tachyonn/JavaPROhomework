package JavaPROsrc.lesson01Task3;

public class SerializerTest {
    public static void runTest(){
        TestClass testClass1 = new TestClass();
        testClass1.setA(10);
        testClass1.setaChar('X');
        testClass1.setBool(true);
        testClass1.setString("Hello, serialization!");

        new Serializer().serialize(testClass1);
        System.out.println("Serialized " + testClass1.getClass().getName());
        TestClass testClass2 = new Serializer().deserialize(TestClass.class);
        System.out.println("Deserialized " + testClass2.getClass().getName());
        System.out.println("Fields after deserialization: " +
                testClass2.getString() +
                " " + testClass2.getaChar() +
                " " + testClass2.getA() +
                " " + testClass2.isBool());
    }
}
