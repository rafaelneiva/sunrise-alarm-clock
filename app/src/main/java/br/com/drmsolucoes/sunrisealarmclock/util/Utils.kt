package br.com.drmsolucoes.sunrisealarmclock.util

import android.app.Activity
import android.content.*
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.io.IOException
import java.nio.charset.Charset

/**
 * Created by rafaelneiva on 03/11/17.z
 */

object Utils {

    @JvmStatic
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo.isConnected
    }

    /**
     * @param ctx      Context
     * @param jsonFile The file on assets
     * @return
     */
    fun loadJSONFromAsset(ctx: Context, jsonFile: String): String? {
        val json: String
        try {
            val `is` = ctx.assets.open(jsonFile)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    fun alreadyInList(list: List<*>, o: Any): Boolean {
        for (`object` in list) {
            if (o == `object`) return true
        }

        return false
    }

    fun shareByPackage(ctx: Context, message: String, pack: String?) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, message)
        sendIntent.type = "text/plain"
        if (pack != null)
            sendIntent.setPackage(pack)

        try {
            ctx.startActivity(sendIntent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }

    }

    fun getNavHeight(ctx: Context): Int {
        val resources = ctx.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else 0
    }

    fun screenShot(view: View): Bitmap? {
        try {
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    fun hideKeyboard(activity: Activity) {
        val view = activity.findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

}
