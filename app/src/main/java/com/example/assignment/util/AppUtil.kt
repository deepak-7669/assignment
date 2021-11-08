package com.example.assignment.util

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.example.assignment.R

import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class AppUtil {
    private val TAG: String = AppUtil::class.java.simpleName

    companion object {
        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
        fun getDate(
            milliSeconds: Long,
            dateFormat: String?
        ): String? { // Create a DateFormatter object for displaying date in specified format.
            val formatter = SimpleDateFormat(dateFormat)
            // Create a calendar object that will convert the date and time value in milliseconds to date.
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds * 1000L
            return formatter.format(calendar.time)
        }
        fun printLongMessage(response: String) {
            val maxLogSize = 4000
            for (i in 0..response.length / maxLogSize) {
                val start = i * maxLogSize
                var end = (i + 1) * maxLogSize
                end = if (end > response.length) response.length else end
                Log.e(TAG, ":response_decode = " + response.substring(start, end))
            }
        }

        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }

        fun hideKeyboard(context: Context, view: View?) {
            try {
                if (view != null) {
                    val imm =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm?.hideSoftInputFromWindow(view.windowToken, 0)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun avoidDoubleClicks(view: View) {
            val DELAY_IN_MS: Long = 900
            if (!view.isClickable()) {
                return
            }
            view.setClickable(false)
            view.postDelayed(Runnable { view.setClickable(true) }, DELAY_IN_MS)
        }

        fun isValidEmail(s: String): Boolean {
            //            val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
            val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            val pattern = Pattern.compile(EMAIL_PATTERN);
            val matcher = pattern.matcher(s);

            return !matcher.matches();
        }

        /*fun checkBlank(text: String): String {
            if (text.equals("null") || text.equals("")) return "N/A"
            else return text
        }*/

        fun checkBlank2(text: String?): String? {
            if (text.equals("null") || text.equals("") || text == null) return "N/A"
            else return text
        }

        /*fun checkNull(text: String): String {
            if (text.equals("null") || text.equals("")) return ""
            else return text
        }*/

        fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
            val bytes = ByteArrayOutputStream()
            inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes)
            val path = MediaStore.Images.Media.insertImage(
                inContext.contentResolver,
                inImage,
                "Title",
                null
            )
            return Uri.parse(path)
        }

      /*  fun setLogoutDialog(context: Context) {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setContentView(R.layout.dialog_logout_new)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            val tvYes: TextView = dialog.findViewById(R.id.tvYes)
            val tvNo: TextView = dialog.findViewById(R.id.tvNo)

            tvNo.setOnClickListener {
                dialog.dismiss()
            }

            tvYes.setOnClickListener {
                dialog.dismiss()
                RealmDialerReference.getInstance(context).isLoggedIn = false
                RealmDialerReference.getInstance(context).langCode=""
                RealmDialerReference.getInstance(context).clearPref()

            }
            dialog.show()
        }*/


        fun askpermission(context: Context, permission: String, permissioncode: Int) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayListOf(permission).toTypedArray(),
                permissioncode.toInt()
            )
        }

        fun checkpermission(permission: String, context: Context): Boolean {
            return context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        }

        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("hh:mm aa")
            return format.format(date)
        }

        fun getHoursfromtwotimestamps(time1: Long, time2: Long): Int {
            val time_1format = Date(time1)
            val time_2 = time2 * 1000
            val time_2format = Date(time_2)
            val diff: Long = time_1format.getTime() - time_2format.getTime()
            val hours = (diff / (1000 * 60 * 60))
            return hours.toInt()
        }

        fun setRandomColors(tvSrt: AppCompatImageView, number: String, context: Context) {
            if (number.last().equals('0')) {
                tvSrt.background.setTint(ContextCompat.getColor(context, R.color.colorBack1))
            }
            if (number.last().equals('1')) {
                tvSrt.background.setTint(ContextCompat.getColor(context, R.color.colorBack2))
            }
            if (number.last().equals('2')) {
                tvSrt.background.setTint(ContextCompat.getColor(context, R.color.colorBack3))
            }
            if (number.last().equals('3')) {
                tvSrt.background.setTint(ContextCompat.getColor(context, R.color.colorBack4))
            }
            if (number.last().equals('4')) {
                tvSrt.background.setTint(ContextCompat.getColor(context, R.color.colorBack5))
            }
            if (number.last().equals('5')) {
                tvSrt.background.setTint(ContextCompat.getColor(context, R.color.colorBack6))
            }
            if (number.last().equals('6')) {
                tvSrt.background.setTint(ContextCompat.getColor(context, R.color.colorBack7))
            }
            if (number.last().equals('7')) {
                tvSrt.background.setTint(ContextCompat.getColor(context, R.color.colorBack8))
            }
            if (number.last().equals('8')) {
                tvSrt.background.setTint(ContextCompat.getColor(context, R.color.colorBack9))
            }
            if (number.last().equals('9')) {
                tvSrt.background.setTint(ContextCompat.getColor(context, R.color.colorBack10))
            }
        }

        fun secToTime(sec: Int): String? {
            val seconds = sec % 60
            var minutes = sec / 60
            if (minutes >= 60) {
                val hours = minutes / 60
                minutes %= 60
                return String.format("%02d h %02d m %02d s", hours, minutes, seconds)
            }
            if (minutes >= 10) {
                if (seconds >= 10) {
                    return String.format("%02d m %02d s", minutes, seconds)
                } else {
                    return String.format("%02d m %01d s", minutes, seconds)
                }
            } else {
                if (seconds >= 10) {
                    return String.format("%01d m %02d s", minutes, seconds)
                } else {
                    return String.format("%01d m %01d s", minutes, seconds)
                }
                return String.format("%01d m %02d s", minutes, seconds)
            }
            if (seconds >= 10) {
                return String.format("%01d m %02d s", minutes, seconds)
            } else {
                return String.format("%01d m %01d s", minutes, seconds)
            }
            return String.format("00:%02d:%02d", minutes, seconds)
        }

        fun CaptilizedfirstlleterorWords(str: String): String? {
            return str.split(" ").joinToString(" ") { it.capitalize() }.trimEnd()
        }

    }
}