package com.example.assignment.activities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.assignment.Assignment
import com.example.assignment.R
import com.example.assignment.Webservices.Consumes
import com.example.assignment.base.BaseActivity
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.model.Baseresponse
import com.example.assignment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


import java.lang.Error
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build

import android.os.ParcelFileDescriptor
import androidx.annotation.RequiresApi
import com.arefbhrn.eprdownloader.EPRDownloader
import com.arefbhrn.eprdownloader.OnDownloadListener
import java.io.FileDescriptor
import com.arefbhrn.eprdownloader.extendfiles.DownloaderQueue
import com.arefbhrn.eprdownloader.request.DownloadRequest
import com.arefbhrn.eprdownloader.utils.Utils
import com.example.assignment.ConnectivityWatcher


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class.java),
    Consumes {
    var downloadIDs: ArrayList<Int> = arrayListOf()
    var syncDownloadId: Int = 0
    val listOfImage: ArrayList<String> = arrayListOf(
        "https://cdn.wallpapersafari.com/36/6/WCkZue.png",
        "https://www.iliketowastemytime.com/sites/default/files/hamburg-germany-nicolas-kamp-hd-wallpaper_0.jpg",
        "https://images.hdqwalls.com/download/drift-transformers-5-the-last-knight-qu-5120x2880.jpg",
        "https://survarium.com/sites/default/files/calendars/survarium-wallpaper-calendar-february-2016-en-2560x1440.png"
    )
    var isAsyncSelected: Boolean = false

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun setInjector() {
        (application as Assignment).getappcomponannet()?.doinject(this@MainActivity)

    }

    private fun getRootDirPath(context: Context): String? {
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val file = ContextCompat.getExternalFilesDirs(
                context.applicationContext,
                null
            )[0]
            file.absolutePath
        } else {
            context.applicationContext.filesDir.absolutePath
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return image
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initview() {
        checkPermission()
        btnStart.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Please allow permission.", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            if (!ConnectivityWatcher(this).isNetworkAvailable()) {
                Toast.makeText(this, "Please check the internet connectivity.", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            when {
                btnStart.text.toString().contentEquals(getString(R.string.start_download)) -> {
                    downloadItems(isAsyncSelected)
                    btnStart.setText("pause")
                    btnAsync.isEnabled = false
                    btnSync.isEnabled = false
                }
                btnStart.text.toString().contentEquals("pause") -> {
                    btnStart.setText("resume")
                    pause(true)
                }
                btnStart.text.toString().contentEquals("resume") -> {
                    btnStart.setText("pause")
                    pause(false)
                }
                btnStart.text.toString().contentEquals("finish") -> {
                    btnAsync.isEnabled = true
                    btnSync.isEnabled = true
                }
            }


        }
        btnSync.setOnClickListener {
            isAsyncSelected = false
            btnAsync.setBackgroundColor(getColor(R.color.colorOffWhite))
            btnSync.setBackgroundColor(getColor(R.color.colorPrimary))
        }
        btnAsync.setOnClickListener {
            isAsyncSelected = true
            btnSync.setBackgroundColor(getColor(R.color.colorOffWhite))
            btnAsync.setBackgroundColor(getColor(R.color.colorPrimary))
        }
    }


    override fun errorresponse(error: Throwable?, data: Baseresponse<*>?) {

    }

    override fun handleresponse(data: Baseresponse<*>?) {
    }

    private fun downloadItems(asyncSelected: Boolean) {
        var downloadRequest4 = createDownloadRequest4()
        var downloadRequest3: DownloadRequest =
            EPRDownloader.download(listOfImage[2], getRootDirPath(this), "image3")
                .build()
                .addOnStartOrResumeListener {
                    progressBarThree.visibility = View.VISIBLE
                }
                .addOnProgressListener {
                    val progressPercent: Long = it.currentBytes * 100 / it.totalBytes
                    progressBarThree.progress = progressPercent.toInt()

                }
                .addOnDownloadListener(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        progressBarThree.visibility = View.GONE
                        val file = File(getRootDirPath(this@MainActivity) + "/image3");
                        if (file.exists()) iv3.setImageBitmap(getBitmapFromUri(Uri.fromFile(file)))
                        if (!asyncSelected) {
                            downloadRequest4.start()
                            syncDownloadId = downloadRequest4.downloadId
                        }
                    }

                    override fun onError(error: com.arefbhrn.eprdownloader.Error?) {
                    }


                })
        var downloadRequest2: DownloadRequest =
            EPRDownloader.download(listOfImage[1], getRootDirPath(this), "image2")
                .build()
                .addOnStartOrResumeListener {
                    progressBarTwo.visibility = View.VISIBLE
                }
                .addOnProgressListener {
                    val progressPercent: Long = it.currentBytes * 100 / it.totalBytes
                    progressBarTwo.progress = progressPercent.toInt()

                }
                .addOnDownloadListener(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        progressBarTwo.visibility = View.GONE
                        val file = File(getRootDirPath(this@MainActivity) + "/image2");
                        if (file.exists()) iv2.setImageBitmap(getBitmapFromUri(Uri.fromFile(file)))
                        if (!asyncSelected) {
                            downloadRequest3.start()
                            syncDownloadId = downloadRequest3.downloadId

                        }
                    }

                    override fun onError(error: com.arefbhrn.eprdownloader.Error?) {
                    }


                })
        var downloadRequest1: DownloadRequest =
            EPRDownloader.download(listOfImage[0], getRootDirPath(this), "image1").build()
                .addOnStartOrResumeListener {
                    progressBarOne.visibility = View.VISIBLE
                }
                .addOnProgressListener {
                    val progressPercent: Long = it.currentBytes * 100 / it.totalBytes
                    progressBarOne.progress = progressPercent.toInt()

                }
                .addOnDownloadListener(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        progressBarOne.visibility = View.GONE
                        val file = File(getRootDirPath(this@MainActivity) + "/image1");
                        if (file.exists()) iv1.setImageBitmap(getBitmapFromUri(Uri.fromFile(file)))
                        if (!asyncSelected) {
                            downloadRequest2.start()
                            syncDownloadId = downloadRequest2.downloadId

                        }
                    }

                    override fun onError(error: com.arefbhrn.eprdownloader.Error?) {
                    }


                })
        if (asyncSelected) {
            DownloaderQueue.setRunningLimit(4)
            DownloaderQueue.add(downloadRequest1)
            DownloaderQueue.add(downloadRequest2)
            DownloaderQueue.add(downloadRequest3)
            DownloaderQueue.add(downloadRequest4)
            downloadIDs.add(downloadRequest1.downloadId)
            downloadIDs.add(downloadRequest2.downloadId)
            downloadIDs.add(downloadRequest3.downloadId)
            downloadIDs.add(downloadRequest4.downloadId)
        } else {
            downloadRequest1.start()
            syncDownloadId = downloadRequest1.downloadId

        }

    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), 123
            )

        }
    }

    fun pause(boolean: Boolean) {
        if (isAsyncSelected) {
            for (i in downloadIDs) {
                if (boolean) {
                    EPRDownloader.pause(i)
                } else {
                    EPRDownloader.resume(i)
                }
            }
        } else {
            if (boolean)
                EPRDownloader.pause(syncDownloadId)
            else EPRDownloader.resume(syncDownloadId)
        }


    }

    fun createDownloadRequest4(): DownloadRequest {
        var request: DownloadRequest =
            EPRDownloader.download(listOfImage[3], getRootDirPath(this), "image4")
                .build()
                .addOnStartOrResumeListener {
                    progressBarFour.visibility = View.VISIBLE
                }
                .addOnProgressListener {
                    val progressPercent: Long = it.currentBytes * 100 / it.totalBytes
                    progressBarFour.progress = progressPercent.toInt()

                }
                .addOnDownloadListener(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        progressBarFour.visibility = View.GONE
                        val file = File(getRootDirPath(this@MainActivity) + "/image4");
                        if (file.exists()) iv4.setImageBitmap(getBitmapFromUri(Uri.fromFile(file)))
                        btnStart.setText("finish")
                        btnSync.isEnabled = true
                        btnAsync.isEnabled = true
                    }

                    override fun onError(error: com.arefbhrn.eprdownloader.Error?) {
                    }


                })
        return request
    }


}