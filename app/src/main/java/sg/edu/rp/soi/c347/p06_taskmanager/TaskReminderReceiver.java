package sg.edu.rp.soi.c347.p06_taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TaskReminderReceiver extends BroadcastReceiver {

	int notificationID = 001;

	private TextView mTextView;
    int notifReqCode = 123;

	@Override
	public void onReceive(Context context, Intent i) {
		Task task = (Task) i.getSerializableExtra("task");
		String name = i.getStringExtra("name");
		String description = i.getStringExtra("description");

		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);



        RemoteInput ri = new RemoteInput.Builder("status")
                .setLabel("Status report")
                .setChoices(new String [] {"Done", "Not yet"})
                .build();


        // build notification
		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle(task.getName());
		builder.setContentText("Task");
		builder.setSmallIcon(android.R.drawable.ic_dialog_info);
		builder.setContentIntent(pIntent);
		builder.setAutoCancel(true);

		Notification n = builder.build();

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		// You may put an ID for the first parameter if you wish
		// to locate this notification to cancel
		notificationManager.notify(notifReqCode, n);

		NotificationCompat.Action action = new
				NotificationCompat.Action.Builder(
				R.mipmap.ic_launcher,
				"Launch Task Manager",
				pendingIntent).build();

		NotificationCompat.Action action2 = new
				NotificationCompat.Action.Builder(
				R.mipmap.ic_launcher,
				"Reply",
				pendingIntent).build();

		NotificationCompat.WearableExtender extender = new
				NotificationCompat.WearableExtender();
		extender.addAction(action);
        extender.addAction(action2);
	}

}
