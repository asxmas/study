import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.sql.Connection;
import java.util.*;

public class RouteCalculatorTest extends TestCase {
    List<Station> route = new ArrayList<>();
    List<Line> lines = new ArrayList<>();
    List<Station> stations = new ArrayList<>();
    List<List<Station>> connections = new ArrayList<>();

    StationIndex testStationIndex = new StationIndex();


    Line A = new Line(1, "A");
    Station A1 = new Station("A1", A);
    Station A2 = new Station("A2", A);
    Station A3 = new Station("A3", A);
    Station A4 = new Station("A4", A);
    Station A5 = new Station("A5", A);

    Line B = new Line(2, "B");
    Station B1 = new Station("B1", B);
    Station B2 = new Station("B2", B);
    Station B3 = new Station("B3", B);
    Station B4 = new Station("B4", B);

    Line C = new Line(3, "C");
    Station C1 = new Station("C1", C);
    Station C2 = new Station("C2", C);
    Station C3 = new Station("C3", C);
    Station C4 = new Station("C4", C);

    List<Station> A2C2 = new ArrayList<>();
    List<Station> B2C4 = new ArrayList<>();

    @Override
    protected void setUp() throws Exception {
        Collections.addAll(lines, A, B, C);
        lines.forEach(line -> testStationIndex.addLine(line));

        Collections.addAll(stations, A1, A2, A3, A4, A5, B1, B2, B3, B4, C1, C2, C3, C4);
        stations.forEach(station -> {
            station.getLine().addStation(station);
            testStationIndex.addStation(station);
        });
        A2C2.add(A2);
        A2C2.add(C2);
        B2C4.add(B2);
        B2C4.add(C4);
        Collections.addAll(connections, A2C2, B2C4);
        connections.forEach(connection -> testStationIndex.addConnection(connection));

        Collections.addAll(route, A1, A2, A3, A4, A5, B1, B2, B3);
    }
    RouteCalculator testRouteCalculator = new RouteCalculator(testStationIndex);

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 18.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute() {
        List<Station> actual = testRouteCalculator.getShortestRoute(A1, B1);
        List<Station> excepted = getList("A1","A2","C2","C3","C4","B2","B1");
        assertEquals(excepted, actual);
    }

    public void testGetRouteOnTheLine() {
        List<Station> actual = testRouteCalculator.getShortestRoute(B1, B4);
        List<Station> excepted = getList("B1","B2","B3","B4");

        assertEquals(excepted, actual);
    }

    public void testGetRouteWithOneConnection() {
        List<Station> actual = testRouteCalculator.getShortestRoute(A1, C1);
        List<Station> excepted = getList("A1","A2","C2","C1");
        assertEquals(excepted, actual);
    }

    public void testGetRouteWithTwoConnections() {
        List<Station> actual = testRouteCalculator.getShortestRoute(B1, A3);
        List<Station> excepted = getList("B1","B2","C4","C3","C2","A2","A3");
        assertEquals(excepted, actual);
    }

    public void testGetAllStations() {
        List<Station> actual = testStationIndex.getAllStations();
        List<Station> excepted = getList("A1","A2","A3","A4","A5","B1","B2","B3","B4","C1","C2","C3","C4");

        assertEquals(excepted, actual);
    }

    public List<Station> getList(String... names){
        List<Station> stationsList = new ArrayList<>();
        for(String station : names){
            stationsList.add(testStationIndex.getStation(station));
        }
        return stationsList;
    }
}
/**
 *
 *                          Line C
 *                         C1 |
 *                            |
 *                            |
 *                            | 2.5
 *                            |
 *                       2.5  |   2.5     2.5     2.5
 *      line A       A1 --- A2/C2 --- A3 ----- A4 --- A5
 *                           3.5
 *                            |
 *                            |
 *                            | 2.5
 *                            |
 *                            |
 *                         C3 |
 *                            |
 *                            |
 *                            | 2.5
 *                            |
 *                      2.5   |   2.5    2.5
 *      line B       B1 --- B2/C4 --- B3 --- B4
 *                           3.5       3.5
 */
