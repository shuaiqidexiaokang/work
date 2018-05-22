package com.lzk;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

public class c_ProcessDefinition {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    /*
    *
        #部署对象和流程定义相关表
        select * from act_re_deployment;	    #部署对象表

        select * from act_re_procdef;			#流程定义表

        select * from act_ge_bytearray;		    #资源文件表

        select * from act_ge_property;		    #主键生成策略表
    * */

    //部署流程定义（从classpath）
    @Test
    public void deploymentProcessDefinition_classpath() {
        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name("流程定义")
                .addClasspathResource("diagrams/a_helloworld.png")//从classpath的资源中加载，一次只能加载一个文件
                .addClasspathResource("diagrams/a_helloworld.bpmn")//从classpath的资源中加载，一次只能加载一个文件
                .deploy();//完成部署
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    //部署流程定义（从zip）
    @Test
    public void deploymentProcessDefinition_zip() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/a_helloworld.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);

        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name("流程定义")
                .addZipInputStream(zipInputStream)//指定zip格式的文件完成部署
                .deploy();//完成部署
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    //查询流程定义
    @Test
    public void findProcessDefinition() {
        List<ProcessDefinition> list = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createProcessDefinitionQuery()//创建一个流程定义的查询
                /*指定条件查询，where条件*/
//              .processDefinitionId(processDefinitionId)//使用流程定义的ID查询
//              .processDefinitionNameLike(processDefinitionNameLike)//使用流程定义的名称模糊查询
//              .processDefinitionKey(processDefinitionKey)//使用流程定义的KEY查询
//              .deploymentId(deploymentId)//使用部署对象的ID查询
                /*排序*/
                .orderByProcessDefinitionVersion().asc()//按照版本的升序排列
//              .orderByProcessDefinitionName().desc()//按照流程定义名称降序排列
                /*返回的结果集*/
                .list();//返回一个集合列表
//              .singleResult()//返回唯一结果集
//              .count()//返回结果集数量
//              .listPage(firstResult,maxResults);//分页查询
        if (list != null && list.size() > 0) {
            for (ProcessDefinition pd : list){
                System.out.println("流程定义ID：" + pd.getId());//流程定义的key+版本+随机生成数
                System.out.println("流程定义的KEY：" + pd.getKey());//对应helloworld.bpmn文件中的id属性
                System.out.println("流程定义的名称：" + pd.getName());//对应helloworld.bpmn文件中的name属性
                System.out.println("流程定义的版本：" + pd.getVersion());//当流程定义的key值相同时，版本升级，默认是1
                System.out.println("部署对象ID：" + pd.getDeploymentId());
                System.out.println("资源名称png文件：" + pd.getResourceName());
                System.out.println("资源名称bpmn文件：" + pd.getDiagramResourceName());
                System.out.println("##################################################");
            }
        }
    }

    //删除流程定义
    @Test
    public void deleteProcessDefinition() {
        String deploymentId = "1";
        /*不带级联的删除，只能删除没有启动的流程，如果流程启动，就会抛出异常*/
//        processEngine.getRepositoryService()
//                .deleteDeployment(deploymentId);

        /*级联删除，不管流程是否启动，都能删除*/
        processEngine.getRepositoryService()
                .deleteDeployment(deploymentId,true);
        System.out.println("删除成功！");
    }


    //查看流程图
    @Test
    public void viewPic() throws IOException {
        String deploymentId = "10001";
        //获取图片资源名称
        List<String> list = processEngine.getRepositoryService()
                .getDeploymentResourceNames(deploymentId);
        //定义图片资源的名称
        String resourceName = "";
        if (list !=null && list.size()>0){
            for (String name : list){
                System.out.println(name);
                if (name.indexOf(".png") > 0){
                    resourceName = name;
                }
            }
        }
        //获取图片的输入流
        InputStream in = processEngine.getRepositoryService()
                .getResourceAsStream(deploymentId,resourceName);

        //将图片生成到D盘下的目录下
        File file = new File("d:/" + resourceName);
        FileUtils.copyInputStreamToFile(in,file);
    }

    //查询最新流程版本的流程定义
    @Test
    public void findLastVesionProcessDefinition(){
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()
                .list();
        /*
        * Map<String,ProcessDefinition>
        * map集合的key:流程定义的key
        * map集合的value:流程定义的对象
        * map集合的特点:当map集合key值相同的情况下，后一次的值将会替换前一次的值
        * */
        Map<String,ProcessDefinition> map = new HashMap<>();
        if (list != null&& list.size() >0){
            for (ProcessDefinition pd : list){
                map.put(pd.getKey(),pd);
            }
        }
        List<ProcessDefinition> pdList = new ArrayList<>(map.values());
        if (pdList != null && pdList.size() > 0) {
            for (ProcessDefinition pd : pdList){
                System.out.println("流程定义ID：" + pd.getId());
                System.out.println("流程定义的名称：" + pd.getName());
                System.out.println("流程定义的KEY：" + pd.getKey());
                System.out.println("流程定义的版本：" + pd.getVersion());
                System.out.println("部署对象ID：" + pd.getDeploymentId());
                System.out.println("资源名称png文件：" + pd.getResourceName());
                System.out.println("资源名称bpmn文件：" + pd.getDiagramResourceName());
            }
        }
    }

    //删除流程定义（删除key相同的所有不同版本的流程定义）
    @Test
    public void deleteProcessDefinitionByKey(){
        //流程定义的key
        String processDefinitionKey = "helloworld";
        //先使用流程定义的key查询所有流程定义，查询所有流程定义的版本
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)//使用流程定义的key查询
                .list();
        //遍历，获取每一个流程定义的部署ID
        if (list != null && list.size() >0){
            for (ProcessDefinition pd : list){
                //使用流程定义部署ID删除流程定义
                processEngine.getRepositoryService()
                        .deleteDeployment(pd.getDeploymentId(),true);
            }
        }
    }
}
