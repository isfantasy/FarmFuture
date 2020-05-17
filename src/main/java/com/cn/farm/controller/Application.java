package com.cn.farm.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cn.farm.database.Database;
import com.cn.farm.model.Animal;
import com.cn.farm.model.Farm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Application
 * @Description: TODO
 * @Author Fantasy
 * @Date 2020/5/17
 * @Version V1.0
 **/

public class Application {
    public void printFarmInfo() {
        Farm farm = new Farm(true);
        System.out.println("======================农场信息=======================");
        System.out.println("\t名称：\t" + farm.getName());
        System.out.println("\t类型：\t" + farm.getType());
        System.out.println("\t是否损坏：\t" + farm.getFenceDamaged());
        System.out.println("\t可用金额：\t" + farm.getMoney());
        System.out.println("\t剩余操作：\t" + farm.getFarmerRemainderCount());
        System.out.println("\t创建时间：\t" + farm.getCreateTime());
        System.out.println("\t游戏时间：\t" + farm.getDuration() + "天");
        System.out.println("====================================================");
    }

    public void printAnimalInfo() {
        Farm farm = new Farm(true);
        List<Animal> animalList = farm.getAnimalList();
        for (int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            System.out.println("------------------------" + i + "号动物-------------------------");
            System.out.println("\t名称：\t" + animal.getName());
            System.out.println("\t购买价格：\t" + animal.getPurchasePrice());
            System.out.println("\t描述：\t" + animal.getDescription());
            System.out.println("\t健康度：\t" + animal.getHealthIndex());
            System.out.println("\t开心度：\t" + animal.getHappinessIndex());
            System.out.println("\t创建时间：\t" + animal.getCreateTime());
            System.out.println();
        }
    }

    public void printStoreAnimalInfo() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode animalNode = (ArrayNode) Database.getGlobalParam("animal");
        List<Animal> animalList = new ArrayList<>();
        for (JsonNode animalJson:animalNode){
            Animal animal = null;
            try {
                animal = mapper.readValue(animalJson.toString(), Animal.class);
                animalList.add(animal);
            } catch (JsonProcessingException e) {
                System.err.println("转化错误");
                e.printStackTrace();
            }
        }

        Farm farm = new Farm(true);
        for (int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            System.out.println("------------------------" + i + "号-------------------------");
            System.out.println("\t名称：\t" + animal.getName());
            System.out.println("\t购买价格：\t" + animal.getPurchasePrice());
            System.out.println("\t描述：\t" + animal.getDescription());
            System.out.println();
        }
    }

    public void animalManage() {
        Scanner sc = new Scanner(System.in);
        Farm farm = new Farm(true);
        int operate;
        while (true) {
            System.out.println("********************动物管理*************************");
            System.out.println("*\t1. \t打印已有动物信息");
            System.out.println("*\t2. \t买入动物");
            System.out.println("****************************************************");
            System.out.println("请选择操作：");
            operate = sc.nextInt();
            sc.nextLine();
            switch (operate) {
                case 1:
                    printAnimalInfo();
                    break;
                case 2:
                    printStoreAnimalInfo();
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Application application = new Application();
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎进入游戏，请选择：");
        String farmNameStr;
        while (true) {
            System.out.println("\t1: 已有农场\n\t2: 创建农场");
            int haveFarm = sc.nextInt();
            sc.nextLine();
            // 已有农场
            if (haveFarm == 1) {
                System.out.println("请输入农场名：");
                farmNameStr = sc.nextLine();
                if (Database.setCurrentFarm(farmNameStr)) {
                    Farm farm = new Farm(true);
                    int operate;
                    System.out.println("欢迎再次登陆^_^~");
                    while (true) {
                        System.out.println("****************************************************");
                        System.out.println("*\t1. \t打印农场基本信息");
                        System.out.println("*\t2. \t动物管理");
                        System.out.println("*\t3. \t植物管理");
                        System.out.println("*\t4. \t");
                        System.out.println("****************************************************");
                        System.out.println("请选择操作：");
                        operate = sc.nextInt();
                        sc.nextLine();
                        switch (operate) {
                            case 1:
                                application.printFarmInfo();
                                break;
                            case 2:
                                application.animalManage();
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    System.out.println("农场不存在，请重新选择");
                }
                //    创建农场
            } else if (haveFarm == 2) {
                System.out.println("请输入农场名：");
                Farm farm = new Farm();
                farmNameStr = sc.nextLine();
                if (ObjectUtil.isNotEmpty(Database.getFarmByName(farmNameStr))) {
                    System.out.println("该农场已存在，请更换名称或直接登陆");
                    continue;
                }
                farm.setName(farmNameStr);
                System.out.println("选择农场类型：");
                System.out.println("1: 初试金币+10%");
                System.out.println("2: 卖出收益+0.5%");
                farm.setType(sc.nextInt());
                System.out.println("请选择游戏持续时间(天)：");
                farm.setDuration(sc.nextInt());
                farm.setFenceDamaged(0);
                farm.setFarmerRemainderCount(2);
                int initMoney = Integer.parseInt(Database.getGlobalParam("initMoney").toString());
                if (farm.getType() == 1) {
                    farm.setMoney((int) Math.ceil(initMoney * 1.1));
                } else {
                    farm.setMoney(initMoney);
                }
                farm.setCreateTime(DateUtil.date().toString());
                System.out.println("农场创建成功，请重新登陆游戏");
                farm.createFarm();
            }
        }
    }
}
