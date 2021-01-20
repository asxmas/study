import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

//        Files.walk(Paths.get("data"))
//                .map(Path::toFile)
//                .collect(Collectors.toList())
//                .forEach(file -> {
//                    try {
//                        System.out.println(file + " " +  objectSize(file.toPath(), file.length()));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });

//        Path file = Paths.get("data\\Архив.txt");
//        System.out.println(objectPath(file));
//        Files.createFile(Paths.get(objectPath(file)));


        Files.walk(Paths.get("data\\"))
                .filter(file -> !file.getFileName().equals(Paths.get("data\\"))).
                forEach(file -> {
                    try {
                        if (Files.isDirectory(file)) {
                            Files.createDirectory(Paths.get(objectPath(file)));
                        } else {
                            Files.createFile(Paths.get(objectPath(file)));
                            Files.write(Paths.get(objectPath(file)), Files.readAllBytes(file));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    private static String objectPath (Path file){
        String objectPath = "datacopy" + file.getParent().toString().substring(4) + "\\" + file.getFileName();
        return objectPath;
    }

    private static String objectSize(Path file, long bytes) throws IOException {
        long bytesDirectoryOrFile;
        if(Files.isDirectory(file)){
            bytesDirectoryOrFile = Files.walk(file)
                    .filter(p -> p.toFile().isFile())
                    .mapToLong(p -> p.toFile().length())
                    .sum();
        }
        else bytesDirectoryOrFile = bytes;
        int divider = 1024;
        if (bytesDirectoryOrFile < divider) return bytesDirectoryOrFile + " B";
        int log = logConverter(divider, bytesDirectoryOrFile);
        String unit = ("KMGT").charAt(log-1) + "i";
        return String.format("%.2f %sB", bytesDirectoryOrFile / Math.pow(divider, log), unit);

    }

    private static int logConverter (long base, long result){
        int log = (int) (Math.log(result)/Math.log(base));
        return log;
    }
}
