package com.zhz.idea.plugin.jkdoc.provider

import com.intellij.lang.documentation.CodeDocumentationProvider
import com.intellij.lang.documentation.DocumentationProviderEx
import com.intellij.openapi.util.Pair
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.zhz.idea.plugin.jkdoc.generate.KotlinDocGenerateUtils
import org.jetbrains.kotlin.kdoc.psi.impl.KDocImpl
import org.jetbrains.kotlin.psi.*

/**
 * kotlin 注释生成provider
 * <p>
 * Date: 2022-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
class KotlinDocProvider : DocumentationProviderEx(), CodeDocumentationProvider {


    override fun findExistingDocComment(contextElement: PsiComment?): PsiComment? {
        if (contextElement is KDocImpl) {
            return contextElement
        }
        return PsiTreeUtil.getChildOfType(contextElement, KDocImpl::class.java)
    }

    override fun parseContext(startPoint: PsiElement): Pair<PsiElement, PsiComment> {
        var current: PsiElement? = startPoint
        while (current != null) {
            if (current is KDocImpl) {
                val owner = current.owner
                if (owner != null) {
                    return Pair.create(owner, current)
                }
            }
            current = current.parent
        }
        throw IllegalArgumentException("Cannot find context for documentation comment.")
    }

    override fun generateDocumentationContentStub(contextComment: PsiComment?): String {
        contextComment ?: return ""
        val owner = PsiTreeUtil.getParentOfType(contextComment, KtDeclaration::class.java, false)
        return when (owner) {
            is KtNamedFunction -> KotlinDocGenerateUtils.generateFunction(contextComment, owner)
            is KtClassOrObject -> KotlinDocGenerateUtils.generateClass(contextComment, owner)
            is KtConstructor<*> -> KotlinDocGenerateUtils.generateConstructor(contextComment, owner)
            else -> ""
        }
    }

}