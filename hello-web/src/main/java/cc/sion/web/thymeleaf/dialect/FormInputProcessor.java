package cc.sion.web.thymeleaf.dialect;

import cc.sion.web.thymeleaf.ThymeleafUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;


public class FormInputProcessor extends AbstractAttributeTagProcessor {

    private static final String ATTRIBUTE_NAME = "aa";
    private static final int PRECEDENCE = 300;

    protected FormInputProcessor( String dialectPrefix) {
        super(
                TemplateMode.HTML,            // 处理thymeleaf 的模型
                dialectPrefix,                // 标签前缀名
                null,         // No tag name: match any tag name
                false,   // No prefix to be applied to tag name
                ATTRIBUTE_NAME,             // 标签前缀的 属性 例如：<form:sansiEncrypt="">
                true,   // Apply dialect prefix to attribute name
                PRECEDENCE,                 // Precedence (inside dialect's precedence)
                true);     // Remove the matched attribute afterwards
    }

    @Override
    protected void doProcess(ITemplateContext context,
                             IProcessableElementTag tag, AttributeName attributeName,
                             String attributeValue, IElementTagStructureHandler structureHandler) {

        final String rawValue = ThymeleafUtils.getRawValue(tag,ATTRIBUTE_NAME);

        final String elementCompleteName = tag.getElementCompleteName(); //标签名
        //创建模型
        final IModelFactory modelFactory = context.getModelFactory();
        final IModel model = modelFactory.createModel();
        //添加模型 标签
        model.add(modelFactory.createOpenElementTag(elementCompleteName));

        model.add(modelFactory.createText(HtmlEscape.escapeHtml5(rawValue+" bbbbb")));

        //添加模型 标签
        model.add(modelFactory.createCloseElementTag(elementCompleteName));
        //替换页面标签
        structureHandler.replaceWith(model, false);
    }
}