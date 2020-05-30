package com.li.yun.biao.zk.subscribe;

import org.I0Itec.zkclient.ZkClient;

import static java.lang.System.out;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 12:01 下午
 * @description 监听子节点变化
 */
public class ZookeeperSubscribeChildChanges {

    private static String zkServer = "47.98.178.129:2181";//zookeeper地址

    public static void main(String[] args) {
        new ZookeeperSubscribeChildChanges().subscribeChildChanges();
    }

    private void subscribeChildChanges() {
        ZkClient zkClient = new ZkClient(zkServer);//创建zookeeper的java客户端连接
        if (!zkClient.exists("/LAN")) {
            zkClient.createPersistent("/LAN");
        }
        zkClient.subscribeChildChanges("/LAN/Node", (parentPath, currentChilds) -> {
            String childs = "";
            if (currentChilds != null && currentChilds.size() > 0) {
                childs += "[";
                for (String s : currentChilds) {
                    childs += s + ",";
                }
                childs += "]";
            }
            out.println("ChildChange:" + parentPath + ",childs:" + childs);
        });
        zkClient.createPersistent("/LAN/Node");
        sleep(100);
        zkClient.createPersistent("/LAN/Node/n1");
        sleep(100);
        zkClient.createPersistent("/LAN/Node/n2");
        sleep(100);
        zkClient.createPersistent("/LAN/Node/n3");
        sleep(100);
        zkClient.delete("/LAN/Node/n1");
        sleep(100);
        zkClient.delete("/LAN/Node/n2");
        sleep(100);
        zkClient.delete("/LAN/Node/n3");
        sleep(3000);
        out.println("****");
        zkClient.deleteRecursive("/LAN/Node");
        sleep(3000);
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