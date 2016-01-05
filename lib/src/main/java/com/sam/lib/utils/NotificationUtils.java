package com.sam.lib.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationUtils {

    public static void showNotification(Context context,Class cls,String title,String content,int iconResId){

        Notification n = new Notification();
        n.icon = iconResId;
        n.tickerText = AppUtils.getAppName(context);
        n.when = System.currentTimeMillis();
        //n.flags=Notification.FLAG_ONGOING_EVENT;
        Intent intent = new Intent(context, cls);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
        n.setLatestEventInfo(context, title,content, pi);

        String service = Context.NOTIFICATION_SERVICE;
        NotificationManager nm = (NotificationManager) context.getSystemService(service);
        nm.notify(1, n);

/* api 16
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setTicker(AppUtils.getAppName(context));
        builder.setWhen( System.currentTimeMillis());
        //n.flags=Notification.FLAG_ONGOING_EVENT;
        Intent intent = new Intent(context, cls);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
//        builder. .setLatestEventInfo(context, title, content, pi);
        builder.setContentIntent(pi);
        builder.setContentTitle(title);

        String service = Context.NOTIFICATION_SERVICE;
        NotificationManager nm = (NotificationManager) context.getSystemService(service);
        nm.notify(1, builder.build());*/

    }
}
