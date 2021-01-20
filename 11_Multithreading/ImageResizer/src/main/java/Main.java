import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main
{
    private static int newWidth = 300;
    private static int processors = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args)
    {
        String srcFolder = "C:\\Users\\asxma\\Pictures\\TestWallPappers";
        String dstFolder = "C:\\Users\\asxma\\Pictures\\speed_quality";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] allFiles = srcDir.listFiles();

        int blocks = allFiles.length/processors;
        int mod = allFiles.length % processors;

        File[][] files = new File[processors][];
        int srcPos = 0;
        for(int i = 0; i < processors; i++){
            int a = (mod > 0) ? 1 : 0;
            files[i] = new File[blocks*(i+1) + a - blocks*i];
            System.arraycopy(allFiles, srcPos, files[i], 0, files[i].length);
            new ImageResizer(files[i], newWidth, dstFolder, start).start();
            srcPos += files[i].length;
            mod--;
        }

    }
}
