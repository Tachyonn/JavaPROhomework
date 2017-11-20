package JavaPROsrc.XML_Trains;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "trains")
public class Trains {
    @XmlElement(name = "train")
    private List<Train> trains = new ArrayList<>();

    public Trains() {
    }

    public void add(Train train) {
        trains.add(train);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(trains.toArray());
    }

    public List<Train> getTrains() {
        return trains;
    }

    public List<Train> getScheduledTrains(String date, String from, String to) {
        List<Train> trainsList = new ArrayList<>();
        for (Train train : trains) {
            if (train.getDate().equals(date)) {
                if (train.getDeparture().compareTo(from) >= 0 && train.getDeparture().compareTo(to) <= 0) {
                    trainsList.add(train);
                }
            }
        }
        return trainsList;
    }
}
