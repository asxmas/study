package main;

import main.model.Point;
import main.model.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {
    @Autowired
    PointRepository pointRepository;

    @Value("${someParameter.value}")
    private Integer someParameter;

    @RequestMapping("/")
    public String index(Model model){

        Iterable<Point> pointIterable = pointRepository.findAll();
        ArrayList<Point> points = new ArrayList<>();
        pointIterable.forEach(points::add);
        model.addAttribute("points", points);
        model.addAttribute("pointsCount", points.size());
        model.addAttribute("someParameter", someParameter);

        return "index";
    }
}

