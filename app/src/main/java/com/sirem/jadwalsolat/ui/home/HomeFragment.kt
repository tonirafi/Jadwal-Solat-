package com.sirem.jadwalsolat.ui.home

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirem.jadwalsolat.R
import com.sirem.jadwalsolat.service.AlarmReceiver
import com.sirem.jadwalsolat.service.ReminderNotificationService
import com.sirem.jadwalsolat.ui.adapter.ListPrayAdapter
import kotlinx.android.synthetic.main.fragment_jadwal_solat.*
import kotlinx.android.synthetic.main.item_time_pray.view.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    internal lateinit var adapter: ListPrayAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return   inflater.inflate(R.layout.fragment_jadwal_solat, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUi()
    }

    fun setUi(){


        adapter = context?.let { ListPrayAdapter(it)}!!

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        adapter.addAll(getDataPray())
        setAlarm(getDataPray())
    }


    fun getDataPray():ArrayList<DataAzan>{
        var list=ArrayList<DataAzan>()

        var dataAzan1=DataAzan()
        dataAzan1.namaWaktu="Subuh"
        dataAzan1.jam=4
        dataAzan1.menit=26
        list.add(dataAzan1)

        var dataAzan2=DataAzan()
        dataAzan2.namaWaktu="Terbit"
        dataAzan2.jam=5
        dataAzan2.menit=43
        list.add(dataAzan2)

        var dataAzan3=DataAzan()
        dataAzan3.namaWaktu="Dzuhur"
        dataAzan3.jam=11
        dataAzan3.menit=46
        list.add(dataAzan3)

        var dataAzan4=DataAzan()
        dataAzan4.namaWaktu="Asar"
        dataAzan4.jam=14
        dataAzan4.menit=57
        list.add(dataAzan4)

        var dataAzan5=DataAzan()
        dataAzan5.namaWaktu="Maghrib"
        dataAzan5.jam=17
        dataAzan5.menit=49
        list.add(dataAzan5)

        var dataAzan6=DataAzan()
        dataAzan6.namaWaktu="Isya"
        dataAzan6.jam=18
        dataAzan6.menit=58
        list.add(dataAzan6)

        return list
    }



    private fun setAlarm(list:ArrayList<DataAzan>) {
        showForeground()
        list.forEachIndexed { index, dataAzan ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, dataAzan.jam!!)
            calendar.set(Calendar.MINUTE, dataAzan.menit!!)
            var time = calendar.timeInMillis - calendar.timeInMillis % 60000

            val intent = Intent(context, AlarmReceiver::class.java)
            intent.putExtra("title","Sudah Masuk Waktu ${dataAzan.namaWaktu}")
            intent.putExtra("showForeground",false)
            intent.putExtra("des","Solat Dulu Yuk")
            val intent_id = System.currentTimeMillis().toInt()
            var pendingIntent = PendingIntent.getBroadcast(context, intent_id, intent,  PendingIntent.FLAG_UPDATE_CURRENT)
            if (System.currentTimeMillis() > time) {
                if (Calendar.AM_PM === 0)
                    time += 1000 * 60 * 60 * 12
                else
                    time += time + 1000 * 60 * 60 * 24
            }

            var alarmManager = context?.getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager!!.setInexactRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }


    fun showForeground(){
        Toast.makeText(context, "Pengingat Waktu Solat Aktif", Toast.LENGTH_SHORT).show()
        val i = Intent(context, ReminderNotificationService::class.java)
        context?.stopService(i)
        i.putExtra("title","Pengingat Waktu Solat Aktif")
        i.putExtra("showForeground",true)
        i.putExtra("des","")
        context?.startService(i)
    }
}