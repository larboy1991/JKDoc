package com.zhz.idea.plugin.jkdoc.generate

import com.intellij.psi.PsiComment
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiType
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.LF
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PARAM
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PLACEHOLDER_PARAMS
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PLACEHOLDER_RETURN
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.RETURN
import com.zhz.idea.plugin.jkdoc.ext.appendDecorate
import com.zhz.idea.plugin.jkdoc.utils.DocFormatUtils
import com.zhz.idea.plugin.jkdoc.utils.globalSettings

/**
 * java 文档生成器
 */
object JavaDocGenerateUtils {

    /**
     * 生成java 类注释
     */
    fun generateClass(contextComment: PsiComment): String {
        return buildString {
            globalSettings.classDocTemplate
                .ifBlank { DocDecorationConstants.DEFAULT_CLASS_DOC_TEMPLATE }
                .split("\n")
                .forEach {
                    val content = DocFormatUtils.formatPlaceholder(it)
                    if (content.contains(PLACEHOLDER_PARAMS)) {
                        // java 类注释出现参数时不需要使用，这个只有用在kotlin里面使用
                    } else {
                        appendDecorate(contextComment, content.trimStart())
                        append(LF)
                    }
                }
        }
    }


    /**
     * 生成java 方法注释
     */
    fun generateMethod(contextComment: PsiComment, owner: PsiMethod): String {
        return buildString {
            globalSettings.methodDocTemplate
                .ifBlank { DocDecorationConstants.DEFAULT_METHOD_DOC_TEMPLATE }
                .split("\n")
                .forEach {
                    val content = DocFormatUtils.formatPlaceholder(it)
                    if (content.contains(PLACEHOLDER_PARAMS)) {
                        owner.parameterList.parameters.forEach { psiParameter ->
                            val paramName = psiParameter.name
                            val type = psiParameter.type.presentableText
                            appendDecorate(contextComment, PARAM)
                            append("$paramName $type")
                            append(LF)
                        }
                    } else if (content.contains(PLACEHOLDER_RETURN)) {
                        if (owner.returnType != null && PsiType.VOID != owner.returnType) {
                            //生成返回值，如果返回值是void则不生成
                            val returnType = owner.returnType?.presentableText
                            appendDecorate(contextComment, RETURN)
                            append("$returnType ")
                            append(LF)
                        }
                    } else {
                        appendDecorate(contextComment, content.trimStart())
                        append(LF)
                    }
                }
        }
    }


}