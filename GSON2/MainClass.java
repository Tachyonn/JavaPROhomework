package JavaPROsrc.GSON2;

public class MainClass {
    public static void main(String[] args) {
        BusinessCard businessCard = GsonWorker.readFromJsonFile("src/JavaPROsrc/GSON2/json.txt");
        System.out.println(businessCard);
    }
}
