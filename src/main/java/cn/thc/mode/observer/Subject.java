package cn.thc.mode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thc on 2016/11/16
 */
///推模型

//抽象主题角色类
public abstract class Subject {

    /**
     * 用来保存注册的观察者对象
     */
    private List<Observer> list = new ArrayList<Observer>();

    /**
     * 注册观察者对象
     * @param observer 观察者对象
     */
    public void attach(Observer observer){
        list.add(observer);
        System.out.println("Attached an observer");
    }

    /**
     * 删除观察者对象
     * @param observer 观察者对象
     */
    public void detach(Observer observer){
        list.remove(observer);
    }

    /**
     * 通知所有注册的观察者对象
     * @param newState
     */
    public void nodifyObservers(String newState){
        for (Observer observer : list) {
            observer.update(newState);
        }
    }

}

//具体主题角色类
class ConcreteSubject extends Subject{

    private String state;

    public String getState(){
        return state;
    }

    public void change(String newState){
        state = newState;
        System.out.println("主题状态为：" + state);
        //状态发生改变，通知各个观察者
        this.nodifyObservers(state);
    }

}

//抽象观察者角色类
interface Observer{

    public void update(String state);

}

//具体观察者角色类
class ConcreteObserver implements Observer{

    //观察者的状态
    private String oberverState;

    @Override
    public void update(String state) {
        //更新观察者的状态，使其与目标的状态保持一致
        oberverState = state;
        System.out.println("状态为：" + oberverState);
    }
}

//客户端类
class Client{

    public static void main(String[] args) {
        //创建主题对象
        ConcreteSubject subject = new ConcreteSubject();
        //创建观察者对象
        Observer observer = new ConcreteObserver();
        //将观察者对象登记到主题对象上
        subject.attach(observer);
        //改变主题对象的状态
        subject.change("new state");
    }

}

//拉模型，拉模型通常都是把主题对象当做参数传递

abstract class Subject2 {

    /**
     * 用来保存注册的观察者对象
     */
    private List<Observer2> list = new ArrayList<Observer2>();

    /**
     * 注册观察者对象
     * @param observer2 观察者对象
     */
    public void attach(Observer2 observer2){
        list.add(observer2);
        System.out.println("Attached an observer");
    }

    /**
     * 删除观察者对象
     * @param observer2 观察者对象
     */
    public void detach(Observer2 observer2){
        list.remove(observer2);
    }

    /**
     * 通知所有注册的观察者对象
     */
    public void nodifyObservers(){
        for (Observer2 observer2 : list) {
            observer2.update(this);
        }
    }

}

//具体主题角色类
class ConcreteSubject2 extends Subject2{

    private String state;

    public String getState(){
        return state;
    }

    public void change(String newState){
        state = newState;
        System.out.println("主题状态为：" + state);
        //状态发生改变，通知各个观察者
        this.nodifyObservers();
    }

}

//抽象观察者角色类
interface Observer2{

    /**
     * 更新接口
     * @param subject2 传入主题对象，方面获取相应的主题对象的状态
     */
    public void update(Subject2 subject2);

}

//具体观察者角色类
class ConcreteObserver2 implements Observer2{

    //观察者的状态
    private String oberverState;


    @Override
    public void update(Subject2 subject2) {

        /**
         * 更新观察者的状态，使其与目标的状态保持一致
         */
        oberverState = ((ConcreteSubject2)subject2).getState();
        System.out.println("观察者状态为：" + oberverState);

    }

}

class Client2{
    public static void main(String[] args) {
        //创建主题对象
        ConcreteSubject2 subject2 = new ConcreteSubject2();
        //创建观察者对象
        Observer2 observer2 = new ConcreteObserver2();
        //将观察者对象登记到主题对象上
        subject2.attach(observer2);
        //改变主题对象的状态
        subject2.change("new state");
    }
}