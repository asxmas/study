package main;

import main.model.FileDescriptor;
import main.model.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Configuration
public class FileController {
    @Autowired
    private FilesRepository filesRepository;

    @GetMapping(value = "/files/")
    public ResponseEntity list(){
        Iterable<FileDescriptor> fileIterable = filesRepository.findAll();
        ArrayList<FileDescriptor> files = new ArrayList<>();
        fileIterable.forEach(files::add);
        return ResponseEntity.of(Optional.of(files));
    }

    @GetMapping(value = "/files/{id}")
    public ResponseEntity getFile(@PathVariable int id){
        return ResponseEntity.of(filesRepository.findById(id));
    }

    @PostMapping(value = "/files/")
    public @ResponseBody String fileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                FileDescriptor fileDescriptor = new FileDescriptor();
                fileDescriptor.setFileName(name);
                fileDescriptor.setSize(file.getSize());
                fileDescriptor.setExt(file.getContentType());
                fileDescriptor.setCreatedTs(LocalDateTime.now());
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                filesRepository.save(fileDescriptor);
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }

//    @PutMapping(value = "/files/{id}")
//    public ResponseEntity updateFile(@PathVariable Integer id, FileDescriptor newFile){
//        Optional<FileDescriptor> optionalFile = filesRepository.findById(id);
//        FileDescriptor file = optionalFile.get();
//        file.setFileName(newFile.getFileName());
//        file.setExt(newFile.getExt());
//        filesRepository.save(file);
//
//        return ResponseEntity.of(Optional.of(newFile));
//    }
//
//    @PutMapping(value = "/files/")
//    public ResponseEntity updateList(@RequestBody List <FileDescriptor> files){
//        files.forEach(file -> updateFile(file.getId(), file));
//        return ResponseEntity.of(Optional.of(files));
//    }
//
//    @DeleteMapping(value = "/files/{id}")
//    public ResponseEntity removePoint(@PathVariable int id){
//        Optional <FileDescriptor> file = filesRepository.findById(id);
//        filesRepository.deleteById(id);
//        return ResponseEntity.of(file);
//    }
//
//    @DeleteMapping(value = "/files/")
//    public ResponseEntity removeList(){
//        filesRepository.deleteAll();
//        return ResponseEntity.ok("To Do List is clear");
//    }


}
