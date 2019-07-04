package me.kupchenko.test7.tags;

import lombok.Data;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

@Data
public class TestTag extends SimpleTagSupport {
    private String arg1;
    private String arg2;

    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().getOut().write(arg1 + arg2);
    }
}
