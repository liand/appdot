/**
 * 
 */
package org.appdot.web.taglib;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.CheckboxesTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * 扩展Spring提供的CheckboxesTag，使用Twitter-Bootstrap样式定义inline形式的checkboxes
 * 
 * @author Lian
 *
 */
@SuppressWarnings("serial")
public class BootstrapCheckboxesTag extends CheckboxesTag {

	private static final String LABEL_CHECKBOXES_INLINE = "label class=\"";

	private String labelCssClass;

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		super.setElement(LABEL_CHECKBOXES_INLINE + labelCssClass + "\"");
		return super.writeTagContent(tagWriter);
	}

	public String getLabelCssClass() {
		return labelCssClass;
	}

	public void setLabelCssClass(String labelCssClass) {
		this.labelCssClass = labelCssClass;
	}
}
