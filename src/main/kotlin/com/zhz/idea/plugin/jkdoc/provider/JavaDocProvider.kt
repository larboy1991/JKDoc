package com.zhz.idea.plugin.jkdoc.provider

import com.intellij.lang.java.JavaDocumentationProvider
import com.intellij.psi.*
import com.intellij.psi.javadoc.PsiDocComment
import com.zhz.idea.plugin.jkdoc.generate.JavaDocGenerateUtils


/**
 * java 类注释生成provider
 * <p>
 * Date: 2022-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
class JavaDocProvider : JavaDocumentationProvider() {

    override fun generateDocumentationContentStub(contextComment: PsiComment?): String? {
        contextComment ?: return null
        when (val commentOwner = (contextComment as PsiDocComment).owner) {
            is PsiMethod -> return JavaDocGenerateUtils.generateMethod(contextComment, commentOwner)
            is PsiClass -> return JavaDocGenerateUtils.generateClass(contextComment)
        }
        return super.generateDocumentationContentStub(contextComment)
    }

}