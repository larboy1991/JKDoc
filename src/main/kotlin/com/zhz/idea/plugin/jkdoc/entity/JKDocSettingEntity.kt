package com.zhz.idea.plugin.jkdoc.entity


/**
 * 设置配置项
 * <p>
 * Date: 2022-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 * @param classDocTemplate String 类注释模版
 * @param methodDocTemplate String 方法注释模版
 *
 * Author: zhuanghongzhan
 */
data class JKDocSettingEntity(
    var classDocTemplate: String = "", //类注释模版
    var methodDocTemplate: String = "" //方法注释模版
)