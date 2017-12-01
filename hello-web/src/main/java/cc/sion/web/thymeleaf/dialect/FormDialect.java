package cc.sion.web.thymeleaf.dialect;


import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;


import java.util.*;

public class FormDialect extends AbstractProcessorDialect {

    private static final String NAME = "FORM";
    private static final String PREFIX = "ddd";

    public FormDialect() {
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String s) {
        return createStandardProcessorsSet(s);
    }

    private Set<IProcessor> createStandardProcessorsSet(String dialectPrefix) {
        LinkedHashSet<IProcessor> processors = new LinkedHashSet<IProcessor>();
        processors.add(new FormProcessor(dialectPrefix));
        processors.add(new FormInputProcessor(dialectPrefix));
        return processors;
    }
}
