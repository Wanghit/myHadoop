import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

public class FsUrl {
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void cat(String remoteFilePath) {
        try (InputStream in = new URL("hdfs", "172.31.42.193", 9000, remoteFilePath)
                .openStream()) {
            IOUtils.copyBytes(in, System.out, 4096, false);
            IOUtils.closeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 主函数
     */
    public static void main(String[] args) {
        Path file=new Path(args[1]);// 需要读取文件的路径
        try {
            System.out.println("读取文件: " + file);
            FsUrl.cat(file.toString());
            System.out.println("\n读取完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
