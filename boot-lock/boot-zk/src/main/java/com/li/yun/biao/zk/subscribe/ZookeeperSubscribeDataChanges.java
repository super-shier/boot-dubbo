package com.li.yun.biao.zk.subscribe;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import static java.lang.System.out;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 12:00 下午
 * @description 监听节点数据变化
 */
public class ZookeeperSubscribeDataChanges {

    private static String zkServer = "47.98.178.129:2181";//zookeeper地址

    public static void main(String[] args) {
        new ZookeeperSubscribeDataChanges().subscribeDataChanges();
    }

    private void subscribeDataChanges() {
        ZkClient zkClient = new ZkClient(zkServer);//创建zookeeper的java客户端连接
        if (!zkClient.exists("/LAN")) {
            zkClient.createPersistent("/LAN");
        }
        //注册监听事件
        zkClient.subscribeDataChanges("/LAN/Node", new IZkDataListener() {
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                out.println("DataDeleted:" + dataPath);
            }

            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                out.println("DataChange:" + dataPath + ",data:" + data);
            }
        });
        out.println("****************************************");
        zkClient.createPersistent("/LAN/Node");
        sleep(100);
        zkClient.writeData("/LAN/Node", 1);
        sleep(100);
        zkClient.writeData("/LAN/Node", 2);
        sleep(100);
        zkClient.writeData("/LAN/Node", 3);
        sleep(100);
        zkClient.writeData("/LAN/Node", 4);
        sleep(100);
        zkClient.writeData("/LAN/Node", 5);
        sleep(100);
        zkClient.writeData("/LAN/Node", null);
        sleep(100);
        zkClient.delete("/LAN/Node");
        sleep(2000);
        zkClient.unsubscribeAll();
        zkClient.close();
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
