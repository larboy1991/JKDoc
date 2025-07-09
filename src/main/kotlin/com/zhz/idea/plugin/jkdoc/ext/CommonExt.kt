package com.zhz.idea.plugin.jkdoc.ext

import com.intellij.codeInsight.editorActions.CodeDocumentationUtil
import com.intellij.lang.CodeDocumentationAwareCommenter
import com.intellij.lang.LanguageCommenters
import com.intellij.psi.PsiComment

/**
 * 添加行注释
 * <p>
 * Date: 2022-11-11 17:24
 * @param contextComment PsiComment
 * @param str String
 * @return StringBuilder
 * Author: zhuanghongzhan
 */
fun StringBuilder.appendDecorate(contextComment: PsiComment, str: String): StringBuilder = append(
    CodeDocumentationUtil.createDocCommentLine(
        str,
        contextComment.containingFile,
        LanguageCommenters.INSTANCE.forLanguage(contextComment.language) as CodeDocumentationAwareCommenter
    )
)