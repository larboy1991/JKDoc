package com.zhz.idea.plugin.jkdoc.utils

import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PLACEHOLDER_FORMAT_DATE
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PLACEHOLDER_FORMAT_TIME

object DocFormatUtils {

    /**
     * 处理各种占位符
     */
    fun formatPlaceholder(content: String): String {
        var tmp = content
        if (content.contains(PLACEHOLDER_FORMAT_DATE)) {
            tmp = tmp.replace(PLACEHOLDER_FORMAT_DATE, formatDate())
        }
        if (content.contains(PLACEHOLDER_FORMAT_TIME)) {
            tmp = tmp.replace(PLACEHOLDER_FORMAT_TIME, formatTime())
        }
        return tmp
    }


    private fun formatDate(): String {
        return DateFormatUtils.getCurrentFormatTime("yyyy-MM-dd")
    }


    private fun formatTime(): String {
        return DateFormatUtils.getCurrentFormatTime("HH:mm")
    }

}