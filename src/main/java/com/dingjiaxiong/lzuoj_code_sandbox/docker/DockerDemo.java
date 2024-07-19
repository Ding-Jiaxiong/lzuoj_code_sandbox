package com.dingjiaxiong.lzuoj_code_sandbox.docker;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.PullResponseItem;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.LogContainerResultCallback;

import java.util.List;

public class DockerDemo {

    public static void main(String[] args) throws InterruptedException {

        // 获取默认的 Docker Client
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
//        PingCmd pingCmd = dockerClient.pingCmd();
//        pingCmd.exec();

        String image = "nginx:latest";
//        PullImageCmd pullImageCmd = dockerClient.pullImageCmd(image);
//        PullImageResultCallback pullImageResultCallback = new PullImageResultCallback() {
//            @Override
//            public void onNext(PullResponseItem item) {
//                System.out.println("下载镜像：" + item.getStatus());
//                super.onNext(item);
//            }
//        };
//        pullImageCmd
//                .exec(pullImageResultCallback)
//                .awaitCompletion();
//        System.out.println("下载完成");

//        CreateContainerCmd containerCmd = dockerClient.createContainerCmd(image);
//        CreateContainerResponse createContainerResponse = containerCmd
//                .withCmd("echo", "Hello Docker")
//                .exec();
//        System.out.println(createContainerResponse);
//        String containerId = createContainerResponse.getId();

//        ListContainersCmd listContainersCmd = dockerClient.listContainersCmd();
//        List<Container> containerList = listContainersCmd.withShowAll(true).exec();
//        for (Container container : containerList) {
//            System.out.println(container);
//        }

        // 启动容器
        String containerId = "5548dc2dbe03";
////        dockerClient.startContainerCmd(containerId).exec();
//
//        // 查看日志
//        LogContainerResultCallback logContainerResultCallback = new LogContainerResultCallback() {
//            @Override
//            public void onNext(Frame item) {
//                System.out.println(item.getStreamType());
//                System.out.println("日志：" + new String(item.getPayload()));
//                super.onNext(item);
//            }
//        };
//
//// 阻塞等待日志输出
//        dockerClient.logContainerCmd(containerId)
//                .withStdErr(true)
//                .withStdOut(true)
//                .exec(logContainerResultCallback)
//                .awaitCompletion();

//        dockerClient.removeContainerCmd(containerId).withForce(true).exec();

        dockerClient.removeImageCmd(image).exec();
    }
}
