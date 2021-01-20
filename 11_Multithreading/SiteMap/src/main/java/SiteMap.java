import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveAction;


public class SiteMap extends RecursiveAction {
    private Node node;
    public SiteMap(Node node){
        this.node = node;
    }

    @Override
    protected void compute() {
        Set<SiteMap> subTasks = new CopyOnWriteArraySet<>();
        this.node.getChilds().forEach(child -> {
            SiteMap task = new SiteMap(child);
            try {
                Thread.sleep(100);
            } catch (Exception e){
                e.printStackTrace();
            }
            task.fork();
            subTasks.add(task);
        });
        subTasks.forEach(SiteMap::join);
    }

    public Node getNode() {
        return node;
    }
}

