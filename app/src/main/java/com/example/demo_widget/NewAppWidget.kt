package com.example.demo_widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {
    private val handler = Handler(Looper.getMainLooper())
    private val LOGTAG = "widget_manager"
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget2(context, appWidgetManager, appWidgetId)
        }
    }
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        Toast.makeText(context, "onDisabled called", Toast.LENGTH_LONG)
        handler.removeCallbacksAndMessages(null)
    }
    internal fun updateAppWidget2(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        // Construct the RemoteViews object
        val views = RemoteViews(context.packageName, R.layout.new_app_widget)
        views.setTextViewText(R.id.appwidget_text_title, context.getString(R.string.appwidget_text))

        // Update the widget body every second using a Handler
        var count = 0
        handler.post(object : Runnable {
            override fun run() {
                Log.d(LOGTAG, "entered runnable with counter = $count")
                views.setTextViewText(R.id.appwidget_text_body, count.toString())
                appWidgetManager.updateAppWidget(appWidgetId, views)
                count++
                // Schedule the next update after one second
                handler.postDelayed(this, 1000)
            }
        })

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText1 = context.getString(R.string.appwidget_text)
    val widgetText2 = context.getString(R.string.appwidget_text2)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
    views.setTextViewText(R.id.appwidget_text_title, widgetText1)
    views.setTextViewText(R.id.appwidget_text_body, appWidgetId.toString())
    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

