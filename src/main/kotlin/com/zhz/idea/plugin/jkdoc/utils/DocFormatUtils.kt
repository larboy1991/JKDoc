package com.zhz.idea.plugin.jkdoc.utils

import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PLACEHOLDER_FORMAT_DATE
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PLACEHOLDER_FORMAT_TIME

/**
 * 文档处理工具类
 * <p>
 * Date: 2022-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
object DocFormatUtils {


    /**
     * 处理各种占位符
     * <p>
     * Date: 2022-11-11 17:31
     * @param content String
     * @return String
     * Author: zhuanghongzhan
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


    /**
     * 格式化日期
     * <p>
     * Date: 2022-11-11 17:32
     * @return String
     * Author: zhuanghongzhan
     */
    private fun formatDate(): String {
        return DateFormatUtils.getCurrentFormatTime("yyyy-MM-dd")
    }


    /**
     * 格式化时间
     * <p>
     * Date: 2022-11-11 17:32
     * @return String
     * Author: zhuanghongzhan
     */
    private fun formatTime(): String {
        return DateFormatUtils.getCurrentFormatTime("HH:mm")
    }

}