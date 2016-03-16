package me.afsd.site.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * User: afsd
 * Date: 2016/3/16
 * Time: 15:39
 */
@Component
public class Hadoop {

    @Autowired
    private Configuration hadoopConfiguration;

    public void mkdir(String dir) throws IOException {
        FileSystem fs = FileSystem.get(hadoopConfiguration);
        fs.mkdirs(new Path(dir));
        fs.close();
        System.out.println();
    }

    public void getDateNodeHost() throws IOException {
        hadoopConfiguration.get("fs.default.name");
        FileSystem fs = FileSystem.get(hadoopConfiguration);
        DistributedFileSystem hdfs = (DistributedFileSystem) fs;
        DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();
        for (int i = 0; i < dataNodeStats.length; i++) {
            System.out.println("DataNode_" + i + "_Name:" + dataNodeStats[i].getHostName());
        }
    }

    public void createNewHDFSFile(String toCreateFilePath, String content) throws IOException {
        FileSystem hdfs = FileSystem.get(hadoopConfiguration);
        FSDataOutputStream os = hdfs.create(new Path(toCreateFilePath));
        os.write(content.getBytes("UTF-8"));
        os.close();
        hdfs.close();
    }
}
