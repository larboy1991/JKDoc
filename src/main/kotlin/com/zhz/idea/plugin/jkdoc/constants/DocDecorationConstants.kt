package com.zhz.idea.plugin.jkdoc.constants

/**
 * 常量类
 * <p>
 * Date: 2022-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 * Author: zhuanghongzhan
 */
object DocDecorationConstants {
    const val LF = "\n"

    /**
     * kotlin 类注释模版
     */
    const val DEFAULT_KOTLIN_CLASS_DOC_TEMPLATE = "@author \n" +
            "@date {DATE} \n" +
            "@param {PARAMS}"

    /**
     * kotlin 方法注释模版
     */
    const val DEFAULT_KOTLIN_METHOD_DOC_TEMPLATE = "@author \n" +
            "@date {DATE} \n" +
            "@param {PARAMS}\n" +
            "@return {RETURN}"

    /**
     * java 类注释模版
     */
    const val DEFAULT_JAVA_CLASS_DOC_TEMPLATE = "@author \n" +
            "@date {DATE} "

    /**
     * java 方法注释模版
     */
    const val DEFAULT_JAVA_METHOD_DOC_TEMPLATE = "@author \n" +
            "@date {DATE} \n" +
            "@param {PARAMS}\n" +
            "@return {RETURN}"

    const val PLACEHOLDER_FORMAT_DATE = "{DATE}"
    const val PLACEHOLDER_FORMAT_TIME = "{TIME}"
    const val PLACEHOLDER_PARAMS = "{PARAMS}" //参数占位符
    const val PLACEHOLDER_PARAMS_TYPE = "{PARAMS_TYPE}" //参数类型
    const val PLACEHOLDER_RETURN = "{RETURN}" //返回值占位符
}
