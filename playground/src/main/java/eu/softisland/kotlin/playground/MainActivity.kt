package eu.softisland.kotlin.playground

import android.app.Activity
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.media.AudioManager
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.os.storage.StorageManager
import android.support.v4.app.Fragment
import android.widget.TextView
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {

    val audioManager: AudioManager by bindService(Context.AUDIO_SERVICE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (findViewById(R.id.ringerMode) as TextView).text = "${audioManager.ringerMode}"
    }
}


public fun <V> Activity.bindService(serviceName: String)
        : ReadOnlyProperty<Activity, V> = fetchService(serviceName, Activity::getSystemService)

private fun Fragment.serviceBinder(serviceName: String): Any {
    return activity.getSystemService(serviceName)
}

@Suppress("UNCHECKED_CAST")
private fun <T, V> fetchService(serviceName: String, binder: T.(String) -> Any)
        = Lazy { t: T, desc -> t.binder(serviceName) as V? ?: viewNotFound(serviceName, desc) }

private fun viewNotFound(serviceName: String, desc: KProperty<*>): Nothing =
        throw IllegalStateException("System service $serviceName for '${desc.name}' not found.")

// Like Kotlin's lazy delegate but the initializer gets the target and metadata passed to it
private class Lazy<T, V>(private val initializer: (T, KProperty<*>) -> V) : ReadOnlyProperty<T, V> {
    private object EMPTY
    private var value: Any? = EMPTY

    override fun getValue(thisRef: T, property: KProperty<*>): V {
        if (value == EMPTY) {
            value = initializer(thisRef, property)
        }
        @Suppress("UNCHECKED_CAST")
        return value as V
    }
}