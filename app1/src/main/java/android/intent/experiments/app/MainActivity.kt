package android.intent.experiments.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.sendBroadcastButton).setOnClickListener { onSendBroadcastButtonClicked() }
        findViewById<Button>(R.id.startServiceButton).setOnClickListener { onStartServiceButtonClicked() }
    }

    private fun onSendBroadcastButtonClicked() {
        val intent = Intent()
        intent.action = "SomeBroadcastAction"
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        intent.`package` = "android.intent.experiments.app2"

        val resolveInfoList = packageManager.queryBroadcastReceivers(intent, 0)
        val message = "Number of broadcast receivers that can handle the intent = ${resolveInfoList.size}"

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, message)

        sendBroadcast(intent)
    }

    private fun onStartServiceButtonClicked() {
        val intent = Intent()
        intent.action = "SomeServiceAction"
        intent.`package` = "android.intent.experiments.app2"

        val resolveInfoList = packageManager.queryIntentServices(intent, 0)
        val message = "Number of services that can match the intent = ${resolveInfoList.size}"

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, message)

        val result = startService(intent)

        if (result != null) {
            Log.d(TAG, "Started service: $result")
        } else {
            Log.d(TAG, "Failed starting service: the service does not exist.")
        }
    }

    companion object {
        private const val TAG = "Foo"
    }
}