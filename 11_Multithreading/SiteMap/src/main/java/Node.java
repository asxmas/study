import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Node {
    private Set<Node> childs = new HashSet<>();
    private String url;

    public Node(String url) {
        this.url = url;
    }

    public Set<Node> getChilds() {
        try {
        Document doc = Jsoup.connect(this.url).maxBodySize(0).get();
        Elements elements = doc.select("a[href]");
        elements.forEach(element -> {
            String newLink = element.absUrl("href");
            if (checkLink(newLink)) {
                childs.add(new Node(newLink));
            }
        });
        return childs;
    } catch (IOException e) {
        return Collections.emptySet();
    }
    }
    public boolean checkLink(String url){
        Pattern pattern = Pattern.compile("https://skillbox.ru" + ".+/$");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public String getUrl() {
        return url;
    }

    public Set<Node> getChildrenForMain (){
        return childs;
    }


}