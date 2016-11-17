package cn.thc.mode.observer;

import java.util.Observable;

/**
 * Created by thc on 2016/11/17
 */
//参考：http://www.cnblogs.com/java-my-life/archive/2012/05/16/2502279.html
public class JavaObserver {
    public static void main(String[] args) {

        //创建被观察者对象
        Watched watched = new Watched();
        //创建观察者对象，并将被观察者对象登记
        java.util.Observer watcher = new Watcher(watched);
        //给被观察者状态赋值
        watched.setData("start");
        watched.setData("run");
        watched.setData("stop");

    }
}


class Watched extends Observable {

    private String data = "";

    public String getData(){
        return data;
    }

    public void setData(String data){
        if(!this.data.equals(data)){
            this.data = data;
            setChanged();
        }
        notifyObservers();
    }

}

class Watcher implements java.util.Observer {

    public Watcher(Observable o) {
        o.addObserver((java.util.Observer) this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("状态发生改变：" + ((Watched)o).getData());
    }
}
