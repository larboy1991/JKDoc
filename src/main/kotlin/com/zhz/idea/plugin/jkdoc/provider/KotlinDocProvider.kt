package com.zhz.idea.plugin.jkdoc.provider

import com.intellij.ide.util.PackageUtil
import com.intellij.lang.documentation.CodeDocumentationProvider
import com.intellij.lang.documentation.DocumentationProviderEx
import com.intellij.lang.java.JavaDocumentationProvider.getPackageInfoComment
import com.intellij.openapi.util.Pair
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.util.PsiTreeUtil
import com.zhz.idea.plugin.jkdoc.generate.KotlinDocGenerateUtils
import org.jetbrains.kotlin.kdoc.psi.impl.KDocImpl
import org.jetbrains.kotlin.psi.*

/**
 * kotlin 注释生成器
 */
class KotlinDocProvider : DocumentationProviderEx(), CodeDocumentationProvider {


    override fun findExistingDocComment(contextElement: PsiComment?): PsiComment? {
        if (contextElement is KDocImpl) {
            return contextElement.owner?.docComment
        }
        return contextElement
    }

    override fun parseContext(startPoint: PsiElement): Pair<PsiElement, PsiComment> {
        var current = startPoint
        while (true) {
            if (current is KDocImpl) {
                return Pair.create(if (current is PsiField) current.modifierList else current, current)
            } else if (PackageUtil.isPackageInfoFile(current)) {
                return Pair.create(current, getPackageInfoComment(current))
            }
            current = current.parent
        }
    }

    override fun generateDocumentationContentStub(contextComment: PsiComment?): String {
        contextComment ?: return ""
        val owner = PsiTreeUtil.getParentOfType<KtDeclaration>(contextComment)
            ?: contextComment.parent.takeIf { it is KtFunction || it is KtClassOrObject || it is KtSecondaryConstructor }
        return when (owner) {
            is KtNamedFunction -> KotlinDocGenerateUtils.generateFunction(contextComment, owner)
            is KtClassOrObject -> KotlinDocGenerateUtils.generateClass(contextComment, owner)
            else -> ""
        }
    }

}