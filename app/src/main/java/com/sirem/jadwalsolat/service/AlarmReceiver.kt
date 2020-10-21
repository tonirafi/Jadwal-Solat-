package com.sirem.jadwalsolat.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi


/**
 * Created by Govind on 2/27/2018.
 */
class AlarmReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onReceive(context: Context?, intent: Intent?) {
        val i = Intent(context, ReminderNotificationService::class.java)
        i.putExtra("title",intent?.getStringExtra("title"))
        i.putExtra("des",intent?.getStringExtra("des"))
        i.putExtra("showForeground",intent?.getBooleanExtra("showForeground",true))
        context!!.startService(i)
    }



}