package JavaPROsrc.XML_Trains;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class TrainsXMLWorker {
    JAXBContext context;

    public TrainsXMLWorker() {
        try {
            context = JAXBContext.newInstance(Trains.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Trains loadTrainsFromFile(String filename) {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Trains trains = (Trains) unmarshaller.unmarshal(setFile(filename));
            return trains;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveTrainsToFile(Trains trains, String filename) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(trains, setFile(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private File setFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            throw new IllegalArgumentException("No file to load!");
        }
        return file;
    }
}
