package sk.hudak.async;

import sk.hudak.async.impl.LogUtil;
import sk.hudak.async.impl.TaskManagerImpl;

/**
 * Created by hudak on 10.05.2016.
 */
public class Starter {


    public static void main(String[] args) {

        LogUtil.log(">> start");
        AsyncEshopTaskManager taskManager = new TaskManagerImpl();

        // registrujem eshop-y
        taskManager.registerTask(EshopType.TESCO, new EshopSimpleTask() {
            @Override
            public void run() {
                System.out.println("Tesco... 1");
                try {
                    Thread.sleep(25 * 1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("!!! Tesco... 2");
                    return;
                }

                System.out.println("Tesco... 3");
            }
        });
        taskManager.registerTask(EshopType.METRO, new EshopSimpleTask() {
            @Override
            public void run() {
                System.out.println("Metro... ");
            }
        });

        LogUtil.log("tasky zaregistrovane");

        // spustim
        taskManager.startTasks();
        LogUtil.log("tasky spustene");

        int s = 8;
        LogUtil.log("zacinam cakat " + s + " sekundy");
        sleepFor(s);
        LogUtil.log("skoncil som cakat " + s + " sekundy");

        if (taskManager.isAnyTaskRunning()) {

            LogUtil.log("niektore tasky este bezia -> posielam request na stopnutie vsetkych");
            taskManager.stopTasks();
        } else {
            LogUtil.log("ziadne tasky nebezia");

        }
        LogUtil.log("<< koniec");
    }

    private static void sleepFor(int second) {
        try {
            Thread.sleep(second * 1000);

        } catch (InterruptedException e) {
            //ignore
            e.printStackTrace();
        }
    }


}
