package JavaPROsrc.XML_Trains;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        String filename = "src/JavaPROsrc/XML_Trains/schedule.xml";
        TrainsXMLWorker worker = new TrainsXMLWorker();
        Trains trains = worker.loadTrainsFromFile(filename);
        System.out.println(trains);
        trains.add(new Train(3, "Paris", "Kyiv", new Date(), new Date()));
        worker.saveTrainsToFile(trains, filename);
        List<Train> trainList = trains.getScheduledTrains("20.11.2017", "15:00", "19:00");
        System.out.println(Arrays.deepToString(trainList.toArray()));
    }
}
