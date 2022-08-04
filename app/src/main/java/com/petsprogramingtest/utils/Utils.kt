package com.petsprogramingtest.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.IOException
import java.util.*


/**
 * The function has 2 parameters: context & fileName.
 * We get AssetManager object from context by context.assets,
 * then use AssetManager.open() method to open a file in assets folder using ACCESS_STREAMING mode,
 * it returns an InputStream.
 * Then bufferedReader() creates a buffered reader on the InputStream and readText() helps us to read this reader as a String.
 */
fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

/** This Function to Check day of week*/
fun isSaturdayOrSunday(): Boolean {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeZone = TimeZone.getDefault()

    return when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.SUNDAY -> {
            true
        }
        Calendar.SATURDAY -> {
            true
        }
        else -> {
            false
        }
    }
}

/** This function to check current time lies between 9:00 to 18:00*/
fun isBetween9To18(): Boolean {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeZone = TimeZone.getDefault()
    val hours = calendar.get(Calendar.HOUR_OF_DAY)
    return hours <= Constants.KEY_18 && hours >= Constants.KEY_9
}


@BindingAdapter("petImageUrl")
fun loadImage(
    view: View,
    imageUrl: String?
) {
    val image: ImageView = view as ImageView
    Glide.with(image.context)
        .load(imageUrl)
        .override(100, 100)
        .into(image)
}

@BindingAdapter("addDividerDecoration")
fun addItemDecoration(recyclerView: RecyclerView, dividerItemDecoration: DividerItemDecoration) {
    recyclerView.addItemDecoration(dividerItemDecoration)
}

/** For hiding the action bar*/
fun hideToolbar(activity: Activity) {
    (activity as AppCompatActivity).supportActionBar!!.hide()
}

/** Showing action bar*/
fun showsToolbar(activity: Activity) {
    (activity as AppCompatActivity).supportActionBar!!.show()
}


