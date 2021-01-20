package main;

import main.model.Point;
import main.model.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoListController {
    @Autowired
    private PointRepository pointRepository;

    @GetMapping(value = "/points/")
    public ResponseEntity list(){
        Iterable<Point> pointIterable = pointRepository.findAll();
        ArrayList<Point> points = new ArrayList<>();
        pointIterable.forEach(points::add);
        return ResponseEntity.of(Optional.of(points));
    }

    @GetMapping(value = "/points/{id}")
    public ResponseEntity getPoint(@PathVariable int id){
        return ResponseEntity.of(pointRepository.findById(id));
    }

    @PostMapping(value = "/points/")
    public int add(Point point){
        return pointRepository.save(point).getId();
    }

    @PutMapping(value = "/points/{id}")
    public ResponseEntity updatePoint(@PathVariable Integer id, Point newPoint){
        Optional<Point> optionalPoint = pointRepository.findById(id);
        Point point = optionalPoint.get();
        point.setName(newPoint.getName());
        point.setDescription(newPoint.getDescription());
        pointRepository.save(point);

        return ResponseEntity.of(Optional.of(newPoint));
    }

    @PutMapping(value = "/points/")
    public ResponseEntity updateList(@RequestBody List <Point> points){
        points.forEach(point -> updatePoint(point.getId(), point));
        return ResponseEntity.of(Optional.of(points));
    }

    @DeleteMapping(value = "/points/{id}")
    public ResponseEntity removePoint(@PathVariable int id){
        Optional <Point> point = pointRepository.findById(id);
        pointRepository.deleteById(id);
        return ResponseEntity.of(point);
    }

    @DeleteMapping(value = "/points/")
    public ResponseEntity removeList(){
        pointRepository.deleteAll();
        return ResponseEntity.ok("To Do List is clear");
    }


}
