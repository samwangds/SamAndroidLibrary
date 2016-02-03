package com.sam.lib.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class NotificationUtils {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void showNotification(Context context,Class cls,String title,String content,int iconResId){

//api 16
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(iconResId);
        builder.setTicker(AppUtils.getAppName(context));
        builder.setWhen( System.currentTimeMillis());
        //n.flags=Notification.FLAG_ONGOING_EVENT;
        Intent intent = new Intent(context, cls);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
//        builder. .setLatestEventInfo(context, title, content, pi);
        builder.setContentIntent(pi)
                .setContentTitle(title)
                .setContentText(content);

        String service = Context.NOTIFICATION_SERVICE;
        NotificationManager nm = (NotificationManager) context.getSystemService(service);
        nm.notify(1, builder.build());

    }
}
