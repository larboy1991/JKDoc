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

    /**
     * kotlin 类注释模版
     */
    const val DEFAULT_KOTLIN_CLASS_DOC_TEMPLATE = "<p>\n" +
            "Date: {DATE}\n" +
            "Company: \n" +
            "Updater:\n" +
            "Update Time:\n" +
            "Update Comments:\n" +
            "{PARAMS}\n" +
            "\n" +
            "Author: "

    /**
     * kotlin 方法注释模版
     */
    const val DEFAULT_KOTLIN_METHOD_DOC_TEMPLATE = "<p>\n" +
            "Date: {DATE} {TIME}\n" +
            "Author: \n" +
            "{PARAMS}\n" +
            "{RETURN}"

    /**
     * kotlin 类注释模版
     */
    const val DEFAULT_JAVA_CLASS_DOC_TEMPLATE = "<p>\n" +
            "Date: {DATE}\n" +
            "Company: \n" +
            "Updater:\n" +
            "Update Time:\n" +
            "Update Comments:\n" +
            "{PARAMS}\n" +
            "\n" +
            "Author: "

    /**
     * kotlin 方法注释模版
     */
    const val DEFAULT_JAVA_METHOD_DOC_TEMPLATE = "<p>\n" +
            "Date: {DATE} {TIME}\n" +
            "Author: \n" +
            "{PARAMS}\n" +
            "{RETURN}"

    const val PLACEHOLDER_FORMAT_DATE = "{DATE}"
    const val PLACEHOLDER_FORMAT_TIME = "{TIME}"

    const val PLACEHOLDER_PARAMS = "{PARAMS}" //参数占位符
    const val PLACEHOLDER_RETURN = "{RETURN}" //返回值占位符
}
