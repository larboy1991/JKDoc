@file:Suppress("UnstableApiUsage", "DialogTitleCapitalization")
package com.zhz.idea.plugin.jkdoc.setting

import com.intellij.openapi.options.Configurable
import com.intellij.ui.dsl.builder.*
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.DEFAULT_CLASS_DOC_TEMPLATE
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.DEFAULT_METHOD_DOC_TEMPLATE
import com.zhz.idea.plugin.jkdoc.utils.globalSettings
import javax.swing.JComponent
import javax.swing.JTextArea

/**
 * 配置基类
 */
class JKDocConfigureFormImpl : Configurable {

    private lateinit var classTextArea: JTextArea

    private lateinit var methodTextArea: JTextArea

    override fun createComponent(): JComponent {
        return panel {
            separator().rowComment("java 和 kotlin 注释插件，支持自定义模版，使用简单，只需要在类、方法上输入 \"/**\"即可。")
            separator().rowComment("目前支持的格式化参数只有{DATE}、{TIME}、{PARAMS}、{RETURN}")
            row {
                label("类注释模版：")
            }
            row {
                classTextArea = textArea()
                    .text(globalSettings.classDocTemplate.ifBlank { DEFAULT_CLASS_DOC_TEMPLATE })
                    .columns(COLUMNS_LARGE)
                    .component
            }
            row {
                label("方法注释模版：")
            }
            row {
                methodTextArea = textArea()
                    .text(globalSettings.methodDocTemplate.ifBlank { DEFAULT_METHOD_DOC_TEMPLATE })
                    .columns(COLUMNS_LARGE)
                    .component
            }
        }
    }

    override fun isModified(): Boolean = classTextArea.text != globalSettings.classDocTemplate
            || methodTextArea.text != globalSettings.methodDocTemplate

    override fun apply() {
        globalSettings.classDocTemplate = classTextArea.text
        globalSettings.methodDocTemplate = methodTextArea.text
    }

    override fun getDisplayName(): String = "JKDoc"

}