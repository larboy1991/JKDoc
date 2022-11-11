package com.zhz.idea.plugin.jkdoc.utils

import com.intellij.openapi.application.ApplicationManager
import com.zhz.idea.plugin.jkdoc.setting.JKDocSetting

val globalSettings
    get() = ApplicationManager.getApplication().getService(JKDocSetting::class.java).settings


