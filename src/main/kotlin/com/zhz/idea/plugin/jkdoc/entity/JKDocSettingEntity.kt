package com.zhz.idea.plugin.jkdoc.entity



/**
 * 设置配置项
 */
data class JKDocSettingEntity(
    var classDocTemplate: String = "", //类注释模版
    var methodDocTemplate: String = "" //方法注释模版
)