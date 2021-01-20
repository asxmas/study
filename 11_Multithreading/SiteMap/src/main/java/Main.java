
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) throws IOException {
        String startUrl = "https://skillbox.ru/";
        Node site = new Node(startUrl);
        ForkJoinPool a = new ForkJoinPool();
        a.invoke(new SiteMap(site));
        FileWriter writer = new FileWriter("data\\links.txt");
        TreeSet<String> links = new TreeSet<>();
        getNodes(site, links);

        for(String link : links){
            writer.write(slashes(slashes(link)) + "\n");
        }
        writer.close();

    }
    public static String slashes(String url){
        String newUrl = "";
        int counts = url.replaceAll("[^/]", "").length() - 3;
        for(int i = 0; i < counts; i++){
            newUrl = newUrl + "\t";
        }
        newUrl = newUrl + url;
        return newUrl;
    }

    public static void getNodes(Node node, TreeSet<String> links){
        links.add(node.getUrl());
        for(Node child : node.getChildrenForMain()){
            getNodes(child, links);
        }
    }
}
