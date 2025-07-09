package com.zhz.idea.plugin.jkdoc.entity


/**
 * 设置配置项
 * <p>
 * Date: 2022-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
data class JKDocSettingEntity(
    var kotlinClassDocTemplate: String = "", //类注释模版
    var kotlinMethodDocTemplate: String = "", //方法注释模版
    var javaClassDocTemplate: String = "", // Java 类注释模版
    var javaMethodDocTemplate: String = "", // Java 方法注释模版
)