package android.intent.experiments.app

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class SomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.some_activity)

        setActionBar(findViewById(R.id.toolbar))
        actionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<TextView>(R.id.textView).text = "dataDir = ${dataDir}"
    }
}