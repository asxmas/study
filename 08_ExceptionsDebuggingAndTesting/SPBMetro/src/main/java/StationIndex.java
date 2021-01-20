import core.Line;
import core.Station;

import java.util.*;
import java.util.stream.Collectors;

public class StationIndex
{
    HashMap<Integer, Line> number2line;
    TreeSet<Station> stations;
    TreeMap<Station, TreeSet<Station>> connections;

    public StationIndex()
    {
        number2line = new HashMap<>();
        stations = new TreeSet<>();
        connections = new TreeMap<>();
    }

    public void addStation(Station station)
    {
        stations.add(station);
    }

    public void addLine(Line line)
    {
        number2line.put(line.getNumber(), line);
    }

    public void addConnection(List<Station> stations)
    {
        for(Station station : stations)
        {
            if(!connections.containsKey(station)) {
                connections.put(station, new TreeSet<>());
            }
            connections.get(station).addAll(stations.stream()
                .filter(s -> !s.equals(station)).collect(Collectors.toList()));
        }
    }

    public Line getLine(int number)
    {
        return number2line.get(number);
    }

    public Station getStation(String name)
    {
        for(Station s : stations)
        {
            if(s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public Station getStation(String name, int lineNumber)
    {
        Station query = new Station(name, getLine(lineNumber));
        Station s = stations.ceiling(query);
        return s.equals(query) ? s : null;
    }

    public Set<Station> getConnectedStations(Station station)
    {
        if(connections.containsKey(station)) {
            return connections.get(station);
        }
        return new TreeSet<>();
    }

    public List<Station> getAllStations() {
        List<Station> stationsForPrint = new ArrayList<>();
        for(Station s : stations){
            stationsForPrint.add(s);
            System.out.println(s);
        }
        return stationsForPrint;
    }

    {

    }
}
