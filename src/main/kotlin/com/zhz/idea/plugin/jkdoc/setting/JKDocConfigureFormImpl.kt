package com.zhz.idea.plugin.jkdoc.setting

import com.intellij.openapi.options.Configurable
import com.intellij.ui.dsl.builder.*
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.DEFAULT_JAVA_CLASS_DOC_TEMPLATE
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.DEFAULT_JAVA_METHOD_DOC_TEMPLATE
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.DEFAULT_KOTLIN_CLASS_DOC_TEMPLATE
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.DEFAULT_KOTLIN_METHOD_DOC_TEMPLATE
import com.zhz.idea.plugin.jkdoc.utils.globalSettings
import javax.swing.JComponent
import javax.swing.JTextArea

/**
 * JKDoc 设置布局控制类
 * <p>
 * Date: 2022-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
@Suppress("DialogTitleCapitalization")
class JKDocConfigureFormImpl : Configurable {

    /**
     * kotlin 类注释模版输入框
     */
    private lateinit var kotlinClassTextArea: JTextArea

    /**
     * kotlin 方法注释模版输入框
     */
    private lateinit var kotlinMethodTextArea: JTextArea

    /**
     * java 类注释模版输入框
     */
    private lateinit var javaClassTextArea: JTextArea

    /**
     * java 方法注释模版输入框
     */
    private lateinit var javaMethodTextArea: JTextArea

    override fun createComponent(): JComponent {
        val result = panel {
            separator().rowComment("java 和 kotlin 注释插件，支持自定义模版，使用简单，只需要在类、方法上输入 \"/**\"即可。")
            separator().rowComment("目前支持的格式化参数只有{DATE}、{TIME}、{PARAMS}、{RETURN}、{PARAMS_TYPE}")

            // Kotlin 配置面板
            row("Kotlin 类注释模版：") {
                kotlinClassTextArea = textArea()
                    .text(globalSettings.kotlinClassDocTemplate.ifBlank { DEFAULT_KOTLIN_CLASS_DOC_TEMPLATE })
                    .align(AlignX.LEFT)
                    .columns(50)
                    .rows(10)
                    .component
            }
            row("Kotlin 方法注释模版：") {
                kotlinMethodTextArea = textArea()
                    .text(globalSettings.kotlinMethodDocTemplate.ifBlank { DEFAULT_KOTLIN_METHOD_DOC_TEMPLATE })
                    .align(AlignX.LEFT)
                    .columns(50)
                    .rows(10)
                    .component
            }

            // Java 配置面板
            row("Java 类注释模版：") {
                javaClassTextArea = textArea()
                    .text(globalSettings.javaClassDocTemplate.ifBlank { DEFAULT_JAVA_CLASS_DOC_TEMPLATE })
                    .align(AlignX.LEFT)
                    .columns(50)
                    .rows(10)
                    .component
            }
            row("Java 方法注释模版：") {
                javaMethodTextArea = textArea()
                    .text(globalSettings.javaMethodDocTemplate.ifBlank { DEFAULT_JAVA_METHOD_DOC_TEMPLATE })
                    .align(AlignX.LEFT)
                    .columns(50)
                    .rows(10)
                    .component
            }

        }

        return result
    }

    override fun isModified(): Boolean {
        if (!::kotlinClassTextArea.isInitialized
            || !::kotlinMethodTextArea.isInitialized
            || !::javaClassTextArea.isInitialized
            || !::javaMethodTextArea.isInitialized
        ) {
            return false
        }

        return kotlinClassTextArea.text != globalSettings.kotlinClassDocTemplate
                || kotlinMethodTextArea.text != globalSettings.kotlinMethodDocTemplate
                || javaClassTextArea.text != globalSettings.javaClassDocTemplate
                || javaMethodTextArea.text != globalSettings.javaMethodDocTemplate
    }

    /**
    *
     */
    override fun apply() {
        if (::kotlinClassTextArea.isInitialized) {
            globalSettings.kotlinClassDocTemplate = kotlinClassTextArea.text
        }
        if (::kotlinMethodTextArea.isInitialized) {
            globalSettings.kotlinMethodDocTemplate = kotlinMethodTextArea.text
        }

        if (::javaClassTextArea.isInitialized) {
            globalSettings.javaClassDocTemplate = javaClassTextArea.text
        }

        if (::javaMethodTextArea.isInitialized) {
            globalSettings.javaMethodDocTemplate = javaMethodTextArea.text
        }
    }


    override fun getDisplayName(): String = "JKDoc"

}