package com.dynamicAgent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicAgent{

    public static void main(String[] args) {
        OwnFactory factory = new OwnFactory("达利园");
        InvocationHandler commission = new Mediation(factory);
        Factory factory_Agent = (Factory) Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                new Class[]{Factory.class},commission);
        factory_Agent.produce();
        factory_Agent.sale();

    }
}
//定义一个接口，这个接口是我要实现的规范
//这是一个工厂应该具备的功能，应该具备生产到销售的功能
interface Factory{

    void produce();
    void sale();

}

//创建一个类，这个类实现了Factory接口(委托类，即被代理的类)
//这个大工厂是我自己的，我可以生产销售属于我自己的产品
class OwnFactory implements Factory{
    //我自己的工厂也有很多，首先需要给这个工厂起个名字
    private String factory_name;
    public OwnFactory( String name){
        this.factory_name = name;
    }

    @Override
    public void produce() {
        System.out.println(factory_name+"生产效率很高，每天生产10000个产品！");

    }
    @Override
    public void sale() {
        System.out.println(factory_name+"销售效益很好，每天盈利1个亿！");
    }
}

/*
 创建一个实现了InvocationHandler接口的类，这个类是我的中介类，
 在这个类中我可以在代理实例上执行处理方法并返回结果，（给我要执行的方法做其他的处理）
*/
//我的这个工厂,有很多的产品，我现在要筛选出来是"达利园"的产品，并执行它
//但我不自己来筛选，因为那样成本太高了，我就找人来帮我筛选，同时，因为又有好多别的公司
//在和我合作，他们那边的生产线也需要做出来筛选，我就需要让中介类知道，筛选东西时候，
//先判断他们是筛选的我家的产品，是我委托类同类型的产品
class Mediation implements InvocationHandler{

    //创建一个用来接收委托类对象的参数
    private Object object;
    //commission ： 委托类对象
    public Mediation(Object commission){
            this.object = commission;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("筛选出来是达利园的产品");
        Object result = method.invoke(object,args);
        System.out.println("处理完毕");
        return result;

    }
}












 




