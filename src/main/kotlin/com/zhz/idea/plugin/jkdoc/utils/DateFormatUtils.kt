package com.zhz.idea.plugin.jkdoc.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtils {


    /**
     * 获取格式化yyyy-MM-dd 日期格式
     */
    fun getCurrentFormatTime(pattern: String = "yyyy-MM-dd"): String {
        return SimpleDateFormat(pattern).format(Date())
    }


}