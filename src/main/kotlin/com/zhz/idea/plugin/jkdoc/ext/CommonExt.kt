package com.zhz.idea.plugin.jkdoc.ext

import com.intellij.codeInsight.editorActions.CodeDocumentationUtil
import com.intellij.lang.CodeDocumentationAwareCommenter
import com.intellij.lang.LanguageCommenters
import com.intellij.psi.PsiComment

class CommonExt


/**
 * 添加注释行
 */
fun StringBuilder.appendDecorate(contextComment: PsiComment, str: String): StringBuilder = append(
    CodeDocumentationUtil.createDocCommentLine(
        str,
        contextComment.containingFile,
        LanguageCommenters.INSTANCE.forLanguage(contextComment.language) as CodeDocumentationAwareCommenter
    )
)