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
        setContentView(R.layout.main_activity)

        findViewById<Button>(R.id.sendBroadcastButton).setOnClickListener { onSendBroadcastButtonClicked() }
        findViewById<Button>(R.id.startActivityButton).setOnClickListener { onStartActivityButtonClicked() }
        findViewById<Button>(R.id.startServiceButton).setOnClickListener { onStartServiceButtonClicked() }
    }

    private fun onSendBroadcastButtonClicked() {
        val implicitIntent = Intent()
        implicitIntent.action = "SomeBroadcastAction"
        implicitIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)

        val resolveInfoList = packageManager.queryBroadcastReceivers(implicitIntent, 0)
        val message = "Number of broadcast receivers that match the intent = ${resolveInfoList.size}"

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, message)

        // the following broadcast will be blocked by Android because implicit Broadcast intents are not allowed;
        // see 'MainActivity' in 'app1' for a workaround which converts the implicit intent into explicit intents
        sendBroadcast(implicitIntent)
    }

    private fun onStartActivityButtonClicked() {
        val intent = Intent()
        intent.action = "SomeActivityAction"

        val resolveInfoList = packageManager.queryIntentActivities(intent, 0)
        val message = "Number of activities that match the intent = ${resolveInfoList.size}"

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, message)

        startActivity(intent)
    }

    private fun onStartServiceButtonClicked() {
        val intent = Intent()
        intent.action = "SomeServiceAction"

        val resolveInfoList = packageManager.queryIntentServices(intent, 0)
        val message = "Number of services that match the intent = ${resolveInfoList.size}"

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, message)

        // this will throw an IllegalStateException because implicit Service intents are not allowed
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