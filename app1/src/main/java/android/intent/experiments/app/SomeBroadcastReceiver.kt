package android.intent.experiments.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class SomeBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val dataDir = context.applicationInfo.dataDir
        val message = "Broadcast received: dataDir = $dataDir"

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        Log.d("Foo", message)
    }
}