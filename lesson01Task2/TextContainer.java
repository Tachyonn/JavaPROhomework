package JavaPROsrc.lesson01Task2;

@SaveTo(path = "file.txt")
public class TextContainer {
    private String text = "Hello, annotations!";

    public TextContainer() {
    }

    @Saver
    public void save(String path) {
        System.out.println(text + " is saved to + " + path);
    }
}
