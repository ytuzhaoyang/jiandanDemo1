package utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by zhaoyang on 16/8/15.
 * Activity管理栈,用于Activity管理和应用程序退出
 */
public class ActivityManager {

    private static Stack<Activity> sActivityStack;
    private static ActivityManager sActivityManager;

    private ActivityManager() {
    }

    public static ActivityManager getInstance(){
        return ActivityManagerHolder.ACTIVITY_MANAGER;
    }

    private static class ActivityManagerHolder{
       private static final ActivityManager ACTIVITY_MANAGER = new ActivityManager();
    }

    /**
     * 把Activity放入栈中
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (sActivityStack == null) {
            sActivityStack = new Stack<>();
        }
        sActivityStack.add(activity);
    }

    /**
     * 将指定的activity从栈中移除
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (sActivityStack != null) {
            sActivityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 清空栈中所有的activity并结束应用
     */
    public void finishAllActivities() {
        if (sActivityStack != null){
            for (Activity act : sActivityStack){
                if (act != null) {
                    act.finish();
                }
            }

            sActivityStack.clear();
            System.exit(0);
        }
    }
}
