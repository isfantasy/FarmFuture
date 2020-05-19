package com.cn.farm.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cn.farm.database.Database;
import com.cn.farm.model.*;
import com.cn.farm.tools.FormatTool;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.Date;
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
    public void printGlobalAnimal() {
        List<Animal> animalList = Database.getGlobalAnimal();
        for (int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            System.out.println("------------------------" + i + "号-------------------------");
            System.out.println("\t名称：\t" + animal.getName());
            System.out.println("\t购买价格：\t" + animal.getPurchasePrice());
            System.out.println("\t描述：\t" + animal.getDescription());
            System.out.println();
        }
    }

    public void printGlobalPlant() {
        List<Plant> plantList = Database.getGlobalPlant();
        for (int i = 0; i < plantList.size(); i++) {
            Plant plant = plantList.get(i);
            System.out.println("------------------------" + i + "号-------------------------");
            System.out.println("\t名称：\t" + plant.getName());
            System.out.println("\t购买价格：\t" + plant.getPurchasePrice());
            System.out.println("\t出售价格：\t" + plant.getSellPrice());
            System.out.println("\t生长周期(小时)：\t" + plant.getDurationHour());
            System.out.println("\t描述：\t" + plant.getDescription());
            System.out.println();
        }
    }

    public void printGlobalFarmType() {
        ArrayNode farmTypeNode = (ArrayNode) Database.getGlobalParam("farmType");
        System.out.println("=============================================");
        for (JsonNode farmType : farmTypeNode) {
            System.out.println("\t" + farmType.get("key") + ". \t" + farmType.get("value").toString());
        }
        System.out.println("=============================================");
    }

    public void printFarmInfo() {
        Farm farm = Database.currentFarm;
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
        Farm farm = Database.currentFarm;
        List<Animal> animalList = farm.getAnimalList();
        if (animalList.size() < 1) {
            System.out.println("您没有购买动物");
        }
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

    public void pursueAnimal() {
        ObjectMapper mapper = new ObjectMapper();
        Scanner sc = new Scanner(System.in);
        List<Animal> animalList = Database.getGlobalAnimal();
        Farm farm = Database.currentFarm;
        int operate;
        System.out.println("以下为动物列表：");
        printGlobalAnimal();
        System.out.println("您当前可用余额：" + farm.getMoney());
        while (true) {
            System.out.println("请输入你要购买动物的编号(输入-1退出):");
            operate = sc.nextInt();
            if (operate == -1) {
                return;
            } else if (operate >= 0 && operate < animalList.size()) {
                if (animalList.get(operate).purchase()) {
                    System.out.println("购买成功");
                    return;
                } else {
                    System.out.println("购买失败");
                }
            } else {
                System.out.println("输入错误，请重新选择");
            }
        }
    }

    public void careAnimal() {
        Scanner sc = new Scanner(System.in);
        Farm farm = Database.currentFarm;
        List<Animal> animalList = farm.getAnimalList();
        List<Feed> feedList = farm.getFeedList();
        int operate, animalNum, feedNum;
        while (true) {
            System.out.println("********************打理动物*************************");
            System.out.println("*\t1. \t喂食");
            System.out.println("*\t0. \t返回上层");
            System.out.println("****************************************************");
            System.out.println("请选择操作：");
            operate = sc.nextInt();
            sc.nextLine();
            switch (operate) {
                case 0:
                    return;
                case 1:
                    printAnimalInfo();
                    System.out.println("请选择喂养的动物：");
                    animalNum = sc.nextInt();
                    sc.nextLine();
                    printFeedInfo();
                    System.out.println("请选择使用的饲料：");
                    feedNum = sc.nextInt();
                    sc.nextLine();
                    animalList.get(animalNum).feed(feedList.get(feedNum));
                    break;
                default:
                    break;
            }
            farm.updateFarm();
        }
    }

    public void animalManage() {
        Scanner sc = new Scanner(System.in);
        Farm farm = Database.currentFarm;
        int operate;
        while (true) {
            System.out.println("********************动物管理*************************");
            System.out.println("*\t1. \t打印已有动物信息");
            System.out.println("*\t2. \t购买新动物");
            System.out.println("*\t3. \t打理动物");
            System.out.println("*\t0. \t返回上层");
            System.out.println("****************************************************");
            System.out.println("请选择操作：");
            operate = sc.nextInt();
            sc.nextLine();
            switch (operate) {
                case 0:
                    return;
                case 1:
                    printAnimalInfo();
                    break;
                case 2:
                    pursueAnimal();
                    break;
                case 3:
                    careAnimal();
                    break;
                default:
                    break;
            }
        }
    }

    public void printPlantInfo() {
        Farm farm = Database.currentFarm;
        List<Plant> plantList = farm.getPlantList();
        if (plantList.size() < 1) {
            System.out.println("您没有购买植物");
        }
        for (int i = 0; i < plantList.size(); i++) {
            Plant plant = plantList.get(i);
            System.out.println("------------------------" + i + "号植物-------------------------");
            System.out.println("\t名称：\t" + plant.getName());
            System.out.println("\t购买价格：\t" + plant.getPurchasePrice());
            System.out.println("\t出售价格：\t" + plant.getSellPrice());
            System.out.println("\t描述：\t" + plant.getDescription());
            System.out.println("\t种植时间：\t" + plant.getCreateTime());
            System.out.println("\t上次浇水时间：\t" + plant.getLastWatering());
            Date start = DateUtil.parse(plant.getCreateTime());
            Date end = DateUtil.offset(start, DateField.HOUR, plant.getDurationHour() - plant.getAdjHour());
            System.out.println("\t还需生长时间：\t" + FormatTool.dateBetweenParse(start, end));
            System.out.println("\t状态：\t" + plant.getStatus());
            System.out.println();
        }
    }

    public void pursuePlant() {
        ObjectMapper mapper = new ObjectMapper();
        Scanner sc = new Scanner(System.in);
        List<Plant> plantList = Database.getGlobalPlant();
        Farm farm = Database.currentFarm;
        int operate;
        System.out.println("以下为植物列表：");
        printGlobalPlant();
        System.out.println("您当前可用余额：" + farm.getMoney());
        while (true) {
            System.out.println("请输入你要购买动物的编号(输入-1退出):");
            operate = sc.nextInt();
            if (operate == -1) {
                return;
            } else if (operate >= 0 && operate < plantList.size()) {
                if (plantList.get(operate).purchase()) {
                    System.out.println("购买成功");
                    return;
                } else {
                    System.out.println("购买失败");
                }
            } else {
                System.out.println("输入错误，请重新选择");
            }
        }
    }

    public void carePlant() {
        Scanner sc = new Scanner(System.in);
        Farm farm = Database.currentFarm;
        List<Plant> plantList = farm.getPlantList();
        List<Muck> muckList = farm.getMuckList();
        int operate, plantNum, muckNum;
        while (true) {
            System.out.println("********************打理植物*************************");
            System.out.println("*\t1. \t施肥");
            System.out.println("*\t2. \t浇水");
            System.out.println("*\t3. \t出售");
            System.out.println("*\t0. \t返回上层");
            System.out.println("****************************************************");
            System.out.println("请选择操作：");
            operate = sc.nextInt();
            sc.nextLine();
            switch (operate) {
                case 0:
                    return;
                case 1:
                    printPlantInfo();
                    System.out.println("请选择施肥的植物：");
                    plantNum = sc.nextInt();
                    sc.nextLine();
                    printMuckInfo();
                    muckNum = sc.nextInt();
                    sc.nextLine();
                    plantList.get(plantNum).fertilize(muckList.get(muckNum));
                    break;
                case 2:
                    printPlantInfo();
                    System.out.println("请选择浇水的植物：");
                    plantNum = sc.nextInt();
                    sc.nextLine();
                    plantList.get(plantNum).watering();
                    break;
                case 3:
                    printPlantInfo();
                    System.out.println("请选择想要出售的植物：");
                    plantNum = sc.nextInt();
                    sc.nextLine();
                    if (plantList.get(plantNum).sell()) {
                        plantList.remove(plantNum);
                        farm.updateFarm();
                    }
                    break;
                default:
                    break;
            }
            farm.updateFarm();
        }
    }

    public void plantManage() {
        Scanner sc = new Scanner(System.in);
        Farm farm = Database.currentFarm;
        int operate;
        while (true) {
            System.out.println("********************植物管理*************************");
            System.out.println("*\t1. \t打印已有植物信息");
            System.out.println("*\t2. \t购买新植物");
            System.out.println("*\t3. \t打理植物");
            System.out.println("*\t0. \t返回上层");
            System.out.println("****************************************************");
            System.out.println("请选择操作：");
            operate = sc.nextInt();
            sc.nextLine();
            switch (operate) {
                case 0:
                    return;
                case 1:
                    printPlantInfo();
                    break;
                case 2:
                    pursuePlant();
                    break;
                case 3:
                    carePlant();
                    break;
                default:
                    break;
            }
        }
    }

    public void printMuckInfo() {
        Farm farm = Database.currentFarm;
        List<Muck> muckList = farm.getMuckList();
        for (int i = 0; i < muckList.size(); i++) {
            Muck muck = muckList.get(i);
            System.out.println("------------------------" + i + "号肥料-------------------------");
            System.out.println("\t名称：\t" + muck.getName());
            System.out.println("\t购买价格：\t" + muck.getPurchasePrice());
            System.out.println("\t出售价格：\t" + muck.getSellPrice());
            System.out.println("\t肥料等级：\t" + muck.getLevel());
            System.out.println("\t加速时间(小时)：\t" + muck.getEffectHour());
            System.out.println("\t剩余数量：\t" + muck.getCount());
            System.out.println();
        }
    }

    public void pursueMuck() {
        ObjectMapper mapper = new ObjectMapper();
        Scanner sc = new Scanner(System.in);
        List<Muck> muckList = Database.getGlobalMuck();
        Farm farm = Database.currentFarm;
        int operate;
        System.out.println("以下为肥料列表：");
        for (int i = 0; i < muckList.size(); i++) {
            Muck muck = muckList.get(i);
            System.out.println("------------------------" + i + "号-------------------------");
            System.out.println("\t名称：\t" + muck.getName());
            System.out.println("\t购买价格：\t" + muck.getPurchasePrice());
            System.out.println("\t出售价格：\t" + muck.getSellPrice());
            System.out.println("\t肥料等级：\t" + muck.getLevel());
            System.out.println("\t加速时间(小时)：\t" + muck.getEffectHour());
            System.out.println("\t描述：\t" + muck.getDescription());
            System.out.println();
        }
        System.out.println("您当前可用余额：" + farm.getMoney());
        while (true) {
            System.out.println("请输入你要购买肥料的编号(输入-1退出):");
            operate = sc.nextInt();
            if (operate == -1) {
                return;
            } else if (operate >= 0 && operate < muckList.size()) {
                if (muckList.get(operate).purchase()) {
                    System.out.println("购买成功");
                    return;
                } else {
                    System.out.println("购买失败");
                }
            } else {
                System.out.println("输入错误，请重新选择");
            }
        }
    }

    public void muckManage() {
        Scanner sc = new Scanner(System.in);
        int operate;
        while (true) {
            System.out.println("********************肥料管理*************************");
            System.out.println("*\t1. \t打印已有肥料信息");
            System.out.println("*\t2. \t购买新肥料");
            System.out.println("*\t0. \t返回上层");
            System.out.println("****************************************************");
            System.out.println("请选择操作：");
            operate = sc.nextInt();
            sc.nextLine();
            switch (operate) {
                case 0:
                    return;
                case 1:
                    printMuckInfo();
                    break;
                case 2:
                    pursueMuck();
                    break;
                default:
                    break;
            }
        }
    }

    public void printFeedInfo() {
        Farm farm = Database.currentFarm;
        List<Feed> feedList = farm.getFeedList();
        for (int i = 0; i < feedList.size(); i++) {
            Feed feed = feedList.get(i);
            System.out.println("------------------------" + i + "号饲料-------------------------");
            System.out.println("\t名称：\t" + feed.getName());
            System.out.println("\t购买价格：\t" + feed.getPurchasePrice());
            System.out.println("\t出售价格：\t" + feed.getSellPrice());
            System.out.println("\t增加健康值：\t" + feed.getHealthEffect());
            System.out.println("\t增加幸福值：\t" + feed.getHappinessEffect());
            System.out.println("\t饲料等级：\t" + feed.getLevel());
            System.out.println("\t剩余数量：\t" + feed.getCount());
            System.out.println("\t描述：\t" + feed.getDescription());
            System.out.println();
        }
    }

    public void pursueFeed() {
        ObjectMapper mapper = new ObjectMapper();
        Scanner sc = new Scanner(System.in);
        List<Feed> feedList = Database.getGlobalFeed();
        Farm farm = Database.currentFarm;
        int operate;
        System.out.println("以下为肥料列表：");
        for (int i = 0; i < feedList.size(); i++) {
            Feed feed = feedList.get(i);
            System.out.println("------------------------" + i + "号-------------------------");
            System.out.println("\t名称：\t" + feed.getName());
            System.out.println("\t购买价格：\t" + feed.getPurchasePrice());
            System.out.println("\t出售价格：\t" + feed.getSellPrice());
            System.out.println("\t肥料等级：\t" + feed.getLevel());
            System.out.println("\t增加健康值：\t" + feed.getHealthEffect());
            System.out.println("\t增加幸福值：\t" + feed.getHappinessEffect());
            System.out.println("\t描述：\t" + feed.getDescription());
            System.out.println();
        }
        System.out.println("您当前可用余额：" + farm.getMoney());
        while (true) {
            System.out.println("请输入你要购买动物的编号(输入-1退出):");
            operate = sc.nextInt();
            if (operate == -1) {
                return;
            } else if (operate >= 0 && operate < feedList.size()) {
                if (feedList.get(operate).purchase()) {
                    System.out.println("购买成功");
                    return;
                } else {
                    System.out.println("购买失败");
                }
            } else {
                System.out.println("输入错误，请重新选择");
            }
        }
    }

    public void feedManage() {
        Scanner sc = new Scanner(System.in);
        int operate;
        while (true) {
            System.out.println("********************饲料管理*************************");
            System.out.println("*\t1. \t打印已有饲料信息");
            System.out.println("*\t2. \t购买新饲料");
            System.out.println("*\t0. \t返回上层");
            System.out.println("****************************************************");
            System.out.println("请选择操作：");
            operate = sc.nextInt();
            sc.nextLine();
            switch (operate) {
                case 0:
                    return;
                case 1:
                    printFeedInfo();
                    break;
                case 2:
                    pursueFeed();
                    break;
                default:
                    break;
            }
        }
    }

    public void itemManage() {
        Scanner sc = new Scanner(System.in);
        Farm farm = Database.currentFarm;
        int operate;
        while (true) {
            System.out.println("********************物品管理*************************");
            System.out.println("*\t1. \t饲料管理");
            System.out.println("*\t2. \t肥料管理");
            System.out.println("*\t0. \t返回上层");
            System.out.println("****************************************************");
            System.out.println("请选择操作：");
            operate = sc.nextInt();
            sc.nextLine();
            switch (operate) {
                case 0:
                    return;
                case 1:
                    feedManage();
                    break;
                case 2:
                    muckManage();
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
                    Farm farm = Database.currentFarm;
                    Date start = DateUtil.parse(farm.getCreateTime());
                    if (DateUtil.between(start, DateUtil.date(), DateUnit.DAY) >= farm.getDuration()){
                        System.out.println("游戏结束，以下是您本次游戏结果");
                        System.out.println("======================农场信息=======================");
                        System.out.println("\t名称：\t" + farm.getName());
                        System.out.println("\t类型：\t" + farm.getType());
                        System.out.println("\t可用金额：\t" + farm.getMoney());
                        System.out.println("\t游戏时间：\t" + farm.getDuration() + "天");
                        System.out.println("====================================================");
                        return;
                    }
                    int operate;
                    System.out.println("欢迎再次登陆^_^~");
                    while (true) {
                        System.out.println("****************************************************");
                        System.out.println("*\t1. \t打印农场基本信息");
                        System.out.println("*\t2. \t动物管理");
                        System.out.println("*\t3. \t植物管理");
                        System.out.println("*\t4. \t物品管理");
                        System.out.println("*\t0. \t退出游戏");
                        System.out.println("****************************************************");
                        System.out.println("请选择操作：");
                        operate = sc.nextInt();
                        sc.nextLine();
                        switch (operate) {
                            case 0:
                                return;
                            case 1:
                                application.printFarmInfo();
                                break;
                            case 2:
                                application.animalManage();
                                break;
                            case 3:
                                application.plantManage();
                                break;
                            case 4:
                                application.itemManage();
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
                application.printGlobalFarmType();
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
