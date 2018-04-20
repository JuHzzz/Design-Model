package com.staticAgent;

/***
 * 代理模式---静态代理
 * 我刚开始创业，创办了一个大工厂，因为刚起步，所以我能力有限，只能做到生产和销售
 */

public class StaticAgent {

    public static void main(String[] args) {
       Factory factory = new OwnFactory("达利园");
        Factory_agent factory_agent = new Factory_agent(factory);
        factory_agent.produce();
        factory_agent.sale();
    }

}

//定义一个接口，包含要实现的方法
//这个接口是一个工厂品牌，这个品牌的工厂可以生产，还可以出售产品
interface Factory{

    void produce();
    void sale();

}

//写一个实现了接口的类，这个类就是我的委托类
//一个大工厂可以有很多个基地来生产和销售，每个基地都具有大工厂定义的功能(大工厂可以有属于自己企业的工厂来生产和销售)
class OwnFactory implements Factory {
    //1.首先创建我本工厂的一个变量用来给工厂命名
    private String factory_name ;
    public OwnFactory (String name){
        this.factory_name = name;
    }

    @Override
    public void produce() {
        System.out.println(factory_name+"生产效率很高，每天生产10000个产品！");
    }

    @Override
    public void sale() {
        System.out.println(factory_name+"商品市场很大，每天盈利1个亿！");
    }
}

//写一个代理类，这个代理类同样实现了大工厂这个接口，所以就具备生产和销售的能力
//大工厂生产的产品销量很好，所以有很多企业慕名而来，要来合作，帮我这个大工厂代理生产和销售，
// 大工厂为了进一步提高自己的生产力和销售能力，就扩展渠道，和别人合作，让别的厂家也来生产和销售,
// 但我这个产品是有专利的，不能让别人知道如何生产的，所以我就需要派驻工程师，让我这些工程师在其他厂家来生产销售
class Factory_agent implements Factory {
   OwnFactory ownFactory;
    //我需要过滤委托类
    //判断来我这里的工程师，是不是大工厂派来的,将大工厂派来的工程师过滤出来，让这些工程师只干自己的工作就好
    public Factory_agent(Factory factory){
        if(factory.getClass() ==OwnFactory.class){
            this.ownFactory = (OwnFactory) factory;
        }
    }

    @Override
    public void produce() {
        ownFactory.produce();
    }

    @Override
    public void sale() {
        ownFactory.sale();
    }

}

