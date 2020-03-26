package com.example.activiti.component;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class processEngine {
        @Autowired
        RepositoryService repositoryService;
        @Test
        /**
         * 部署流程（非启动！）
         */
        public void deploymentProcess(){
                Deployment deployment = repositoryService.createDeployment()
                        .name ("请假流程")
                        .addClasspathResource ("processes/process.bpmn")
                        .addClasspathResource ("processes/process.png")
                        .name("请假申请")
                        .deploy ();
        }
}