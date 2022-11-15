package com.zhz.idea.plugin.jkdoc.generate

import com.intellij.psi.PsiComment
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.LF
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PARAM
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.PLACEHOLDER_PARAMS
import com.zhz.idea.plugin.jkdoc.constants.DocDecorationConstants.RETURN
import com.zhz.idea.plugin.jkdoc.ext.appendDecorate
import com.zhz.idea.plugin.jkdoc.utils.DocFormatUtils
import com.zhz.idea.plugin.jkdoc.utils.globalSettings
import org.jetbrains.kotlin.idea.intentions.SpecifyTypeExplicitlyIntention
import org.jetbrains.kotlin.psi.KtClassOrObject
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
                    if (content.contains(PLACEHOLDER_PARAMS)) {
                        generateParams(this, contextComment, owner.primaryConstructorParameters)
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
                        generateParams(this, contextComment, owner.valueParameters)
                    } else if (content.contains(DocDecorationConstants.PLACEHOLDER_RETURN)) {
                        if (owner.hasDeclaredReturnType()) {
                            appendDecorate(contextComment, RETURN)
                            append(owner.typeReference?.typeElement?.text)
                            append(LF)
                        } else {
                            SpecifyTypeExplicitlyIntention.getTypeForDeclaration(owner).unwrap().toString().let {
                                if (it != "Unit") {
                                    appendDecorate(contextComment, RETURN)
                                    append(it)
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
     * @param sb StringBuilder
     * @param contextComment PsiComment
     * @param params List<KtParameter>
     * Author: zhuanghongzhan
     */
    private fun generateParams(sb: StringBuilder, contextComment: PsiComment, params: List<KtParameter>) {
        params.forEach { ktParameter ->
            val param = ktParameter.nameIdentifier?.text ?: ""
            val type = SpecifyTypeExplicitlyIntention.getTypeForDeclaration(ktParameter).unwrap().toString()
            if (param.isNotEmpty() && type.isNotEmpty()) {
                sb.appendDecorate(contextComment, PARAM)
                sb.append("$param $type")
                sb.append(LF)
            }
        }
    }


}
