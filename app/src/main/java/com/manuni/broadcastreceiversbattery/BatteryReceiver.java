package com.manuni.broadcastreceiversbattery;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryReceiver extends BroadcastReceiver {
    @SuppressLint("SetTextI18n")
    @Override
    public void onReceive(Context context, Intent intent) {

        TextView chargingTxt = ((MainActivity)context).findViewById(R.id.charging);
        TextView percentageTxt = ((MainActivity)context).findViewById(R.id.percentCharge);
        ImageView batteryImage = ((MainActivity)context).findViewById(R.id.batteryImage);

        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        String message = "";
        switch (status){
            case BatteryManager.BATTERY_STATUS_FULL:
                message = "Full";
                break;
            case BatteryManager.BATTERY_STATUS_CHARGING:
                message = "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                message = "Unplugged Charger";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                message = "Not Charging";
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                message = "Unknown Status";
                break;
        }
        chargingTxt.setText(message);
        //level as percentage

        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        int percentageLevel = level*100/scale;
        percentageTxt.setText(percentageLevel+"%");

        //setting image as battery percentage

        Resources resources = context.getResources();
        if (percentageLevel <= 90 && percentageLevel >= 80){
           batteryImage.setImageDrawable(resources.getDrawable(R.drawable.ic_battery6));
        }else if (percentageLevel > 90 && percentageLevel <= 100){
            batteryImage.setImageDrawable(resources.getDrawable(R.drawable.ic_battery_full));
        }else if (percentageLevel >= 0 && percentageLevel <= 10 ){
            batteryImage.setImageDrawable(resources.getDrawable(R.drawable.ic_battery0));
        }else if (percentageLevel > 10 && percentageLevel <=20){
            batteryImage.setImageDrawable(resources.getDrawable(R.drawable.ic_battery1));
        }else if (percentageLevel >20 && percentageLevel <= 35){
            batteryImage.setImageDrawable(resources.getDrawable(R.drawable.ic_battery2));
        }else if (percentageLevel >35 && percentageLevel <=50){
            batteryImage.setImageDrawable(resources.getDrawable(R.drawable.ic_battery3));
        }else if (percentageLevel >50 && percentageLevel <=65){
            batteryImage.setImageDrawable(resources.getDrawable(R.drawable.ic_battery4));
        }else if (percentageLevel > 65 && percentageLevel < 80){
            batteryImage.setImageDrawable(resources.getDrawable(R.drawable.ic_battery5));
        }else {
            batteryImage.setImageDrawable(resources.getDrawable(R.drawable.ic_battery_unknown));
        }

    }
}
