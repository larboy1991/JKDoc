package com.zhz.idea.plugin.jkdoc.setting

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import com.zhz.idea.plugin.jkdoc.entity.JKDocSettingEntity

@State(name = "JKDocSettings", storages = [Storage("JKDocSettings.xml")])
class JKDocSettingImpl : JKDocSetting, PersistentStateComponent<JKDocSettingEntity> {

    override val settings = JKDocSettingEntity()

    override fun getState(): JKDocSettingEntity? {
        return XmlSerializerUtil.createCopy(settings)
    }

    override fun loadState(state: JKDocSettingEntity) {
        XmlSerializerUtil.copyBean(state, settings)
    }

}