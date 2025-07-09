package com.zhz.idea.plugin.jkdoc.generate

import com.intellij.psi.PsiComment
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.LF
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PLACEHOLDER_PARAMS
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PLACEHOLDER_PARAMS_TYPE
import com.zhz.idea.plugin.jkdoc.ext.appendDecorate
import com.zhz.idea.plugin.jkdoc.utils.DocFormatUtils
import com.zhz.idea.plugin.jkdoc.utils.globalSettings
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtParameter

/**
 * kotlin 类注释生成工具类
 * <p>
 * Date: 2022-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
object KotlinDocGenerateUtils {


    /**
     * 生成kotlin类注释
     * <p>
     * Date: 2022-11-11 17:24
     * @param contextComment PsiComment
     * @param owner KtClassOrObject
     * @return String
     * Author: zhuanghongzhan
     */
    fun generateClass(contextComment: PsiComment, owner: KtClassOrObject): String {
        return buildString {
            globalSettings.kotlinClassDocTemplate
                .ifBlank { DocDecorationConstants.DEFAULT_KOTLIN_CLASS_DOC_TEMPLATE }
                .split("\n")
                .forEach {
                    val content = DocFormatUtils.formatPlaceholder(it)
                    if (content.contains(PLACEHOLDER_PARAMS) || content.contains(PLACEHOLDER_PARAMS_TYPE)) {
                        generateParams(this, it.trimStart(), contextComment, owner.primaryConstructorParameters)
                    } else {
                        appendDecorate(contextComment, content.trimStart())
                        append(LF)
                    }
                }
        }
    }

    /**
     * 生成构造方法注释文档
     * <p>
     * Date: 2022-12-19 17:06
     * Author: zhuanghongzhan
     * @param contextComment PsiComment
     * @param owner KtConstructor<*>
     * @return String
     */
    fun generateConstructor(contextComment: PsiComment, owner: KtConstructor<*>): String {
        return buildString {
            globalSettings.kotlinMethodDocTemplate
                .ifBlank { DocDecorationConstants.DEFAULT_KOTLIN_METHOD_DOC_TEMPLATE }
                .split("\n")
                .forEach {
                    val content = DocFormatUtils.formatPlaceholder(it)
                    if (content.contains(PLACEHOLDER_PARAMS)) {
                        generateParams(this, it.trimStart(), contextComment, owner.valueParameters)
                    } else if (content.contains(DocDecorationConstants.PLACEHOLDER_RETURN)) {
                        // 不处理带返回参数的行，因为构造方法不需要返回值
                    } else {
                        appendDecorate(contextComment, content.trimStart())
                        append(LF)
                    }
                }
        }
    }

    /**
     * kotlin 生成方法注释
     * <p>
     * Date: 2022-11-11 17:25
     * @param contextComment PsiComment
     * @param owner KtNamedFunction
     * @return String
     * Author: zhuanghongzhan
     */
    fun generateFunction(contextComment: PsiComment, owner: KtNamedFunction): String {
        return buildString {
            globalSettings.kotlinMethodDocTemplate
                .ifBlank { DocDecorationConstants.DEFAULT_KOTLIN_METHOD_DOC_TEMPLATE }
                .split("\n")
                .forEach {
                    val content = DocFormatUtils.formatPlaceholder(it)
                    if (content.contains(PLACEHOLDER_PARAMS)) {
                        generateParams(this, it.trimStart(), contextComment, owner.valueParameters)
                    } else if (content.contains(DocDecorationConstants.PLACEHOLDER_RETURN)) {
                        if (owner.hasDeclaredReturnType()) {
                            appendDecorate(contextComment, "")
                            append(
                                it.replace(
                                    DocDecorationConstants.PLACEHOLDER_RETURN,
                                    (owner.typeReference?.typeElement?.text) ?: ""
                                )
                            )
                            append(LF)
                        } else {
                            owner.typeReference?.text?.let { returnType ->
                                if (returnType != "Unit") {
                                    appendDecorate(contextComment, "")
                                    append(
                                        it.replace(
                                            DocDecorationConstants.PLACEHOLDER_RETURN,
                                            (owner.typeReference?.typeElement?.text) ?: ""
                                        )
                                    )
                                    append(LF)
                                }
                            }
                        }
                    } else {
                        appendDecorate(contextComment, content.trimStart())
                        append(LF)
                    }
                }
        }
    }

    /**
     * 插入参数
     * <p>
     * Date: 2022-11-11 17:25
     * @param sb StringBuilder 需要构建的注释
     * @param content String 当前行格式
     * @param contextComment PsiComment
     * @param params List<KtParameter>
     * Author: zhuanghongzhan
     */
    private fun generateParams(
        sb: StringBuilder,
        content: String,
        contextComment: PsiComment,
        params: List<KtParameter>
    ) {
        params.forEach { ktParameter ->
            val paramName = ktParameter.nameIdentifier?.text ?: ""
            val paramType = ktParameter.typeReference?.text ?: "Any"

            if (paramName.isNotEmpty() && paramType.isNotEmpty()) {
                sb.appendDecorate(contextComment, "")
                sb.append(content.replace(PLACEHOLDER_PARAMS, paramName).replace(PLACEHOLDER_PARAMS_TYPE, paramType))
                sb.append(LF)
            }
        }
    }


}
