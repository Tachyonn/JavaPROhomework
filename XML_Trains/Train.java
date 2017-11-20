package JavaPROsrc.XML_Trains;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement(name = "train")
public class Train {
    private int id;
    private String from;
    private String to;
    private Date date;
    private Date departure;

    public Train() {
    }

    public Train(int id, String from, String to, Date date, Date departure) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    @XmlElement
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    @XmlElement
    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    @XmlElement
    public void setDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.date = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDeparture() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(departure);
    }

    @XmlElement
    public void setDeparture(String departure) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            this.departure = sdf.parse(departure);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + getDate() +
                ", departure=" + getDeparture() +
                '}';
    }
}
