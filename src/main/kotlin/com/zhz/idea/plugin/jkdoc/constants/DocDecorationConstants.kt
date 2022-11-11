package com.zhz.idea.plugin.jkdoc.constants

/**
 * 常量类
 * <p>
 * Date: 2022-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
object DocDecorationConstants {
    const val PARAM = "@param"
    const val RETURN = "@return"
    const val LF = "\n"

    const val DEFAULT_CLASS_DOC_TEMPLATE =
        "<p>\nDate: {DATE}\nCompany: xxxxxxx\nUpdater:\nUpdate Time:\nUpdate Comments:\n{PARAMS}\n\nAuthor: handsomeMan"
    const val DEFAULT_METHOD_DOC_TEMPLATE = "<p>\nDate: {DATE} {TIME} \n {PARAMS}\n{RETURN}\nAuthor: handsomeMan"

    const val PLACEHOLDER_FORMAT_DATE = "{DATE}"
    const val PLACEHOLDER_FORMAT_TIME = "{TIME}"

    const val PLACEHOLDER_PARAMS = "{PARAMS}" //参数占位符
    const val PLACEHOLDER_RETURN = "{RETURN}" //返回值占位符


}
