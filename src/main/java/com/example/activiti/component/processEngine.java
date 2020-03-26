package com.example.activiti.component;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class processEngine {
        @Autowired
        private RepositoryService repositoryService;
        @Autowired
        private RuntimeService runtimeService;
        @Test
        /**流程定义：bpmn文件，一个定义对应多个流程实例。
         * 部署流程(非启动!)：部署信息表：ACT_RE_DEPLOYMENT  流程定义数据表：ACT_RE_PROCDEF 资源文件表：ACT_GE_BYTEARRAY
         * 流程实例(启动!):启动一个流程实例
         */
        public void deploymentProcess(){
                Deployment deployment = repositoryService.createDeployment()
                        .name ("请假流程")
                        .addClasspathResource ("processes/process.bpmn")
                        .addClasspathResource ("processes/process.png")
                        .name("请假申请")
                        .deploy ();
        }

        /**
         * 流程实例
         */
        @Test
        public void RuntimeProcess(){
                ProcessInstance process = runtimeService.startProcessInstanceByKey("process");
                System.out.println(process.getDeploymentId());
                System.out.println(process.getName());
                System.out.println(process.getId());

        }
}