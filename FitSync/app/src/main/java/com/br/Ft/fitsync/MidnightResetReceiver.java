package com.br.Ft.fitsync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MidnightResetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Redefine a contagem de passos
        SharedPreferences sharedPreferences = context.getSharedPreferences(HomeFragment.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(HomeFragment.STEP_COUNT_KEY, 0);
        editor.apply();
    }
}
