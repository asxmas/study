package main;

import main.model.FileDescriptor;
import main.model.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {
    @Autowired
    FilesRepository filesRepository;

    @RequestMapping("/")
    public String index(Model model){

        Iterable<FileDescriptor> fileDescriptors = filesRepository.findAll();
        ArrayList<FileDescriptor> files = new ArrayList<>();
        fileDescriptors.forEach(files::add);
        model.addAttribute("files", files);
        model.addAttribute("filesCount", files.size());

        return "index";
    }
}

