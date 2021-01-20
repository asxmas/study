import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static Document metro;
    public static void main(String[] args) throws IOException {
        String fileAddress = "data\\metro.json";
        Path file = Paths.get(fileAddress);
        Files.createFile(file);

        metro = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0").get();
        TreeSet <Line> lines = lines(metro);
        HashSet<ArrayList<Station>> connections = connections(metro);
//запись в json stations

        JsonWriter writer = new JsonWriter(new FileWriter(fileAddress));
        writer.setIndent("  ");
        writer.beginObject();

        stationsToJSON(writer, lines);
        connectionsToJSON(writer, connections);
        linesToJSON(writer, lines);
        writer.endObject();
        writer.close();

// чтение из json кол-ва станций на каждой линии
        JsonReader reader = new JsonReader(new FileReader(fileAddress));
        ArrayList<String> linesAndStations = lineStationsReader(reader);
        reader.close();

        for(String lAndS : linesAndStations){
            System.out.println(lAndS);
        }
    }
    public static TreeSet<Line> lines(Document document){
        TreeSet<Line> lines = new TreeSet<>();

        document.select("table[class='standard sortable'] > tbody > tr")
                .stream()
                .filter(e -> e.childNodeSize() >= 14)
                .filter(e -> !e.child(3).select("span[class]").text().equals(""))
                .forEach(e -> {
                    lines.add(new Line(e.child(0).select("span").first().text().replaceAll("^0", ""), e.child(0).select("span[title]").first().attr("title")));
                });

        return lines;
    }

// парсинг станций
    private static ArrayList<Station> stations(Document document, Line line) {
        ArrayList<Station> stations = new ArrayList<>();
            document.select("table[class='standard sortable'] > tbody > tr")
                    .stream()
                    .filter(e -> e.childNodeSize() >= 14)
                    .filter(e -> !e.child(0).select("span[class]").text().equals(""))
                    .filter(e -> !e.child(1).select("small").text().contains("закрыта"))
                    .filter(e -> e.child(0).select("span").first().text().replaceAll("^0", "").equals(line.getLineNumber()))
                    .forEach(e -> {
                        stations.add(new Station(e.child(1).select("a[href]").get(0).attr("title").replaceAll("(\\(.*\\))", "").trim(), line));
                    });
// отдельный стрим для станций линий 11/8а
            document.select("table[class='standard sortable'] > tbody > tr")
                    .stream()
                    .filter(e -> e.childNodeSize() >= 14)
                    .filter(e -> !e.child(0).select("span[class]").text().equals(""))
                    .filter(e -> !e.child(1).select("small").text().contains("закрыта"))
                    .filter(e -> e.child(0).select("span").size() > 3)
                    .filter(e -> e.child(0).select("span").get(2).text().equals(line.getLineNumber()))
                    .forEach(e -> {
                        stations.add(new Station(e.child(1).select("a[href]").get(0).attr("title").replaceAll("(\\(.*\\))", "").trim(), line));
                    });
        return stations;
    }

    private static HashSet<ArrayList<Station>> connections (Document document){
        ArrayList<Station> stations = getAllStations();
        HashSet<ArrayList<Station>> connections = new HashSet<>();
//создаю список со всеми пересадками
        document.select("table[class='standard sortable'] > tbody > tr")
                .stream()
                .filter(e -> e.childNodeSize() >= 14)
                .filter(e -> !e.child(3).select("span[class]").text().equals(""))
                .filter(e -> !e.child(1).select("small").text().contains("закрыта"))
                .forEach(e -> {
                    ArrayList<Station> iteration = new ArrayList<>();
                    iteration.add(getStation(e.child(1).select("a[href]").get(0).attr("title").replaceAll("(\\(.*\\))", "").trim(), e.child(0).select("span").first().text().replaceAll("^0", ""), stations));
                    for (int i = 0; i < e.child(3).select("span").size(); i = i + 2) {
                        for (Station station : stations) {
                            if (e.child(3).select("span").get(i + 1).attr("title").contains(station.getStationName() + " ") && e.child(3).select("span").get(i).text().replaceAll("^0", "").equals(station.getLine().getLineNumber())) {
                                iteration.add(getStation( station.getStationName(), station.getLine().getLineNumber(), stations));
                                break;
                            }
                        }
                    }
                    iteration.sort(Station::compareTo);
                    connections.add(iteration);
                });

        document.select("table[class='standard sortable'] > tbody > tr")
                .stream()
                .filter(e -> e.childNodeSize() >= 14)
                .filter(e -> !e.child(3).select("span[class]").text().equals(""))
                .filter(e -> !e.child(1).select("small").text().contains("закрыта"))
                .filter(e -> e.child(0).select("span").size() > 3)
                .forEach(e -> {
                    ArrayList<Station> iteration = new ArrayList<>();
                    iteration.add(getStation(e.child(1).select("a[href]").get(0).attr("title").replaceAll("(\\(.*\\))", "").trim(), e.child(0).select("span").get(2).text().replaceAll("^0", ""), stations));


                    for (int i = 0; i < e.child(3).select("span").size(); i = i + 2){
                        for (Station station : stations) {
                            if (e.child(3).select("span").get(i + 1).attr("title").contains(station.getStationName() + " ") && e.child(3).select("span").get(i).text().replaceAll("^0", "").equals(station.getLine().getLineNumber())) {
                                iteration.add(getStation(station.getStationName(), station.getLine().getLineNumber(), stations));
                                break;
                            }
                        }
                    }
                    iteration.sort(Station::compareTo);

                    connections.add(iteration);

                });
        HashSet<ArrayList<Station>> finalConnections = duplicateDeleter(connections, stations);
        return finalConnections;

    }

    private static ArrayList<String> lineStationsReader(JsonReader reader) {

        ArrayList<String> linesAndStations = new ArrayList<>();
        try {
            reader.beginObject();
            reader.nextName();
            reader.beginObject();
         while (reader.hasNext()) {
             String lineNumber = reader.nextName();
             int count = 0;
             reader.beginArray();
             while (reader.hasNext()) {
                    reader.nextString();
                    count++;
                }
                linesAndStations.add(lineNumber + ": " + count);
                reader.endArray();
            }
            reader.endObject();
            reader.endObject();
            } catch (Exception e) {
            }
        return linesAndStations;
    }

    private static void connectionsToJSON (JsonWriter writer, HashSet<ArrayList<Station>> connections) throws IOException {
        writer.name("connections");
        writer.beginArray();
        for(ArrayList<Station> connection : connections){
            writer.beginArray();
            for(Station station : connection) {
                writer.beginObject();
                writer.name("line").value(station.getLine().getLineNumber());
                writer.name("station").value(station.getStationName());
                writer.endObject();
            }
            writer.endArray();
        }
        writer.endArray();
    }

    private static void linesToJSON (JsonWriter writer, TreeSet<Line> lines) throws IOException {
        writer.name("lines");
        writer.beginArray();
        for(Line line : lines) {
            writer.beginObject();
            writer.name("name").value(line.getLineName());
            writer.name("number").value(line.getLineNumber());
            writer.endObject();
        }
        writer.endArray();
    }

    private static void stationsToJSON (JsonWriter writer, TreeSet<Line> lines) throws IOException {
        writer.name("stations");
        writer.beginObject();
        for (Line line : lines) {
            ArrayList<Station> stations = stations(metro, line);
            writer.name(line.getLineNumber());
            writer.beginArray();
            for (Station station : stations) {
                writer.value(station.getStationName());
            }
            writer.endArray();
        }
        writer.endObject();
    }

    public static ArrayList<Station> getAllStations(){
        ArrayList<Station> stations = new ArrayList<>();
        for(Line line : lines(metro)){
            for(Station station : stations(metro, line)){
                stations.add(station);
            }
        }
        return stations;
    }

    public static Station getStation(String name, String lineNumber, ArrayList<Station> stations)
    {
        TreeSet<Line> lines = lines(metro);
        Station query = null;
        for(Line line : lines){
            if(line.getLineNumber().equals(lineNumber)){
                query = new Station(name, line);
            }
        }
        for(Station station : stations) {
            if (station.equals(query)) return station;
        }
        return null;
    }

    public static HashSet<ArrayList<Station>> duplicateDeleter(HashSet<ArrayList<Station>> connections, ArrayList<Station> stations){
        HashSet<ArrayList<Station>> finalConnections = new HashSet<>();
        for(Station station : stations){
            ArrayList<ArrayList<Station>> similarConnection = new ArrayList<>();
            for(ArrayList<Station> connection : connections){
                for(Station stationInConnection : connection){
                    if(station.equals(stationInConnection)) similarConnection.add(connection);
                }
            }
            ArrayList<Station> maxConnection = new ArrayList<>();
            for(ArrayList<Station> connection : similarConnection) {
                if (connection.size() >= maxConnection.size()) maxConnection = connection;
            }
            if(maxConnection.size() > 1) finalConnections.add(maxConnection);
            }
        return finalConnections;
        }
}
