import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;


public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://lenta.ru/").get();

        Elements elements = doc.getElementsByClass("g-picture");
        elements.forEach(element -> {
            try {
                URL url = new URL(element.attr("src"));
                new FileOutputStream(fileAdress(url))
                        .getChannel()
                        .transferFrom(Channels.newChannel(url.openStream()), 0, Long.MAX_VALUE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(element.attr("src"));
        });
    }

    private static String fileAdress (URL url){
        String adress = url.toString();
        return adress.replaceAll(".+/","data/");
    }
}
