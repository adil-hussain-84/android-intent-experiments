package android.intent.experiments.app

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class SomeService : Service() {

    override fun onCreate() {
        super.onCreate()

        val message = "SomeService.onCreate(): dataDir = ${applicationInfo.dataDir}"

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.d("Foo", message)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Foo", "SomeService.onStartCommand(...)")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("Foo", "SomeService.onBind(...)")
        return null
    }
}