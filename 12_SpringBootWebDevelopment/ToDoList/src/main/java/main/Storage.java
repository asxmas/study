package main;

import main.model.Point;

import java.util.*;

public class Storage {
    private static int currentId = 1;
    private static TreeMap<Integer, Point> toDoList = new TreeMap<>();

    public static List<Point> getToDoList() {
        return new ArrayList<>(Storage.toDoList.values()).size() == 0
                ? Collections.emptyList()
                : new ArrayList<>(Storage.toDoList.values());
    }

    public static int addPoint(Point newPoint){
        int id = currentId++;
        newPoint.setId(id);
        toDoList.put(id, newPoint);
        return id;
    }

    public static Optional getPoint(int pointId){
        return Optional.of(toDoList.get(pointId));
    }

    public static Optional removePoint(int pointId){
        Optional optionalPoint = Storage.getPoint(pointId);
        toDoList.remove(pointId);
        return optionalPoint;
    }

    public static Optional updatePoint(int pointId, Point newPoint){
        newPoint.setId(pointId);
        toDoList.put(pointId, newPoint);
        return Storage.getPoint(pointId);
    }
    public static Optional updateAll(List <Point> points){
        (points).forEach(point -> {
            updatePoint(point.getId(), point);
         });
        return Optional.of(points);
    }
    public static void removeAll(){
        toDoList.clear();
        currentId = 1;
    }


}
