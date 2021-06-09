package android.intent.experiments.app

import android.app.Activity
import android.os.Bundle

class SomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.some_activity)
    }
}