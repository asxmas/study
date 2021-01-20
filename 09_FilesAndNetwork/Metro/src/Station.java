
public class Station implements Comparable<Station> {
    private String stationName;
    private Line line;

    public Station(String station, Line line){
        this.stationName = station;
        this.line = line;
    }

    public String getStationName() {
        return stationName;
    }

    public Line getLine() {
        return line;
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Station) obj) == 0;
    }

    @Override
    public int compareTo(Station station) {
        if(Integer.parseInt(this.line.getLineNumber().replaceAll("[^0-9]", "")) > Integer.parseInt(station.line.getLineNumber().replaceAll("[^0-9]", "")))
            return 1;
        if(this.line.getLineNumber().equals(station.line.getLineNumber()) && (this.stationName.equals(station.stationName))) {
             return 0;
        }
        return -1;
    }
}
