package org.madbit.drugbox.service;

import org.madbit.drugbox.R;
import org.madbit.drugbox.activity.ExpiredDrugsActivity;
import org.madbit.drugbox.dmf.DrugDAO;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ExpiredDrugService extends Service {
	
	private NotificationManager mNM;
	// Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private int NOTIFICATION = R.string.notif_expired_title;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		DrugDAO drugDao = new DrugDAO(this);
		drugDao.open();
		int expiredDrugs = drugDao.getExpiredDrugsCount();
		drugDao.close();
		if(expiredDrugs > 0) {
			CharSequence text;			
			if(expiredDrugs == 1)
				text = getText(R.string.notif_expired_single);
			else
				text = String.format(getText(R.string.notif_expired_multi).toString(), expiredDrugs);			
			
			mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			// Set the icon, scrolling text and timestamp
	        Notification notification = new Notification(R.drawable.ic_menu_more, text, System.currentTimeMillis());

	        // The PendingIntent to launch our activity if the user selects this notification
	        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,  new Intent(this, ExpiredDrugsActivity.class), 0);

	        // Set the info for the views that show in the notification panel.
	        notification.setLatestEventInfo(this, getText(R.string.notif_expired_title), text, contentIntent);

	        // Send the notification.
	        mNM.notify(NOTIFICATION, notification);
		}
	}
}
