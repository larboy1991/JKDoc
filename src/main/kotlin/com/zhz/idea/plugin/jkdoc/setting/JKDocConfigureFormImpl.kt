@file:Suppress("UnstableApiUsage", "DialogTitleCapitalization")

package com.zhz.idea.plugin.jkdoc.setting

import com.intellij.openapi.observable.properties.PropertyGraph
import com.intellij.openapi.options.Configurable
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.DEFAULT_JAVA_CLASS_DOC_TEMPLATE
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.DEFAULT_JAVA_METHOD_DOC_TEMPLATE
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.DEFAULT_KOTLIN_CLASS_DOC_TEMPLATE
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.DEFAULT_KOTLIN_METHOD_DOC_TEMPLATE
import com.zhz.idea.plugin.jkdoc.utils.globalSettings
import javax.swing.*

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


    private val propertyGraph: PropertyGraph = PropertyGraph()

    private val languageProperty = propertyGraph.lazyProperty { "" }

    private val buttons = listOf("Java", "Kotlin")

    private val rows = mutableMapOf<String, Cell<*>>()

    override fun createComponent(): JComponent {
        val result = panel {
            separator().rowComment("java 和 kotlin 注释插件，支持自定义模版，使用简单，只需要在类、方法上输入 \"/**\"即可。")
            separator().rowComment("目前支持的格式化参数只有{DATE}、{TIME}、{PARAMS}、{RETURN}、{PARAMS_TYPE}")
            row("language: ") {
                segmentedButton(buttons) { s -> s }.apply { bind(languageProperty) }
            }
            row {
                rows[buttons[0]] = cell(createJavaPanel())
                rows[buttons[1]] = cell(createKotlinPanel())
            }
        }
        languageProperty.afterChange {
            for ((key, row) in rows) {
                row.visible(key == it)
            }
        }
        languageProperty.set(buttons[0])
        return result
    }

    /**
     * 构建java模版布局
     * <p>
     * Date: 2022-11-15 11:45
     * Author: zhuanghongzhan
     * @return JPanel
     */
    private fun createJavaPanel(): JPanel {
        return panel {
            row {
                label("Java类注释模版：")
            }
            row {
                javaClassTextArea = textArea()
                        .text(globalSettings.javaClassDocTemplate.ifBlank { DEFAULT_JAVA_CLASS_DOC_TEMPLATE })
                        .horizontalAlign(HorizontalAlign.FILL)
                        .columns(55)
                        .rows(10)
                        .component
            }
            row {
                label("Java方法注释模版：")
            }
            row {
                javaMethodTextArea = textArea()
                        .text(globalSettings.javaMethodDocTemplate.ifBlank { DEFAULT_JAVA_METHOD_DOC_TEMPLATE })
                        .horizontalAlign(HorizontalAlign.FILL)
                        .rows(10)
                        .columns(55)
                        .component
            }
        }
    }


    /**
     * 构建kotlin模版布局
     * <p>
     * Date: 2022-11-15 11:46
     * Author: zhuanghongzhan
     * @return JPanel
     */
    private fun createKotlinPanel(): JPanel {
        return panel {
            row {
                label("Kotlin类注释模版：")
            }
            row {
                kotlinClassTextArea = textArea()
                        .text(globalSettings.kotlinClassDocTemplate.ifBlank { DEFAULT_KOTLIN_CLASS_DOC_TEMPLATE })
                        .horizontalAlign(HorizontalAlign.FILL)
                        .columns(55)
                        .rows(10)
                        .component
            }
            row {
                label("Kotlin方法注释模版：")
            }
            row {
                kotlinMethodTextArea = textArea()
                        .text(globalSettings.kotlinMethodDocTemplate.ifBlank { DEFAULT_KOTLIN_METHOD_DOC_TEMPLATE })
                        .horizontalAlign(HorizontalAlign.FILL)
                        .rows(10)
                        .columns(55)
                        .component
            }
        }

    }

    override fun isModified(): Boolean {
        return kotlinClassTextArea.text != globalSettings.kotlinClassDocTemplate
                || kotlinMethodTextArea.text != globalSettings.kotlinMethodDocTemplate
                || javaClassTextArea.text != globalSettings.kotlinMethodDocTemplate
                || javaMethodTextArea.text != globalSettings.kotlinMethodDocTemplate
    }


    override fun apply() {
        globalSettings.kotlinClassDocTemplate = kotlinClassTextArea.text
        globalSettings.kotlinMethodDocTemplate = kotlinMethodTextArea.text
        globalSettings.javaClassDocTemplate = javaClassTextArea.text
        globalSettings.javaMethodDocTemplate = javaMethodTextArea.text
    }

//    override fun reset() {
//        kotlinClassTextArea.text = DEFAULT_KOTLIN_CLASS_DOC_TEMPLATE
//        kotlinMethodTextArea.text = DEFAULT_KOTLIN_METHOD_DOC_TEMPLATE
//        javaClassTextArea.text = DEFAULT_JAVA_CLASS_DOC_TEMPLATE
//        javaMethodTextArea.text = DEFAULT_JAVA_METHOD_DOC_TEMPLATE
//    }

    override fun getDisplayName(): String = "JKDoc"

}