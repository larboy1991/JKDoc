package com.zhz.idea.plugin.jkdoc.setting;

import com.intellij.openapi.options.Configurable;

import javax.swing.*;

public abstract class JKDocConfigureForm  implements Configurable {

    protected JPanel mPanel;

    protected JTextField classTemplateEdit;

    protected JTextField methodTemplateEdit;


}
