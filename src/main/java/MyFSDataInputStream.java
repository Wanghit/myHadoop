import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class MyFSDataInputStream extends FSDataInputStream {

    public MyFSDataInputStream(InputStream in) throws IOException {
        super(in);
    }

    public static String readline(Configuration conf, String remoteFilePath) {
        try (FileSystem fs =FileSystem.get(conf)) {
            Path remotePath = new Path(remoteFilePath);
            FSDataInputStream in = fs.open(remotePath);
            BufferedReader d = new BufferedReader(new InputStreamReader(in));
            String line = null;
            if ((line = d.readLine()) != null) {
                d.close();
                in.close();
                return line;
            }
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        Path file=new Path(args[1]);// 需要读取文件的路径
        System.out.println("读取文件: " + file);
        System.out.println(MyFSDataInputStream.readline(conf, file.toString()));
        System.out.println("\n读取完成");

    }
}

