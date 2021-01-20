import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static com.skillbox.airport.Flight.Type.DEPARTURE;

public class Main {


    public static void main(String[] args) throws ParseException {
        ArrayList<Flight> flights = new ArrayList<>();

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR,+2);
        Date currentTime = calendar.getTime();

        System.out.println(calendar.getTime());


        Airport.getInstance().getTerminals().stream().forEach(terminal -> terminal.getFlights().stream()
        .filter(flight -> flight.getDate().before(currentTime))
                .filter(flight -> flight.getDate().after(date))
                .filter(flight -> flight.getType() == DEPARTURE)
                .forEach(System.out::println));

    }
}