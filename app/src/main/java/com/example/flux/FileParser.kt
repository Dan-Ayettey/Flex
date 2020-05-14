package com.example.flux

import android.app.Activity
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONStringer
import java.nio.charset.Charset

internal class FileParser {
    fun parseFile(activity: Activity,raw:Int):JSONArray {
       activity.resources.openRawResource(raw).use{ output->
           return  JSONArray(String(output.readBytes(), Charset.defaultCharset()))

        }
    }
}