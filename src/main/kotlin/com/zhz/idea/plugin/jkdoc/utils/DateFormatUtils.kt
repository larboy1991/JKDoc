package com.zhz.idea.plugin.jkdoc.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * <p>
 * Date: 2022-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
object DateFormatUtils {

    /**
     * 事件格式化
     * <p>
     * Date: 2022-11-11 17:29
     * @param pattern String 格式化的类型，默认使用 "yyyy-MM-dd"
     * @return String 返回格式化之后的日期时间
     * Author: zhuanghongzhan
     */
    fun getCurrentFormatTime(pattern: String = "yyyy-MM-dd"): String {
        return SimpleDateFormat(pattern).format(Date())
    }


}