package com.lzk.controller;

import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortMethod implements TemplateMethodModelEx {
    public Object exec(List list) throws TemplateModelException {
        SimpleSequence simpleSequence = (SimpleSequence) list.get(0);
        List<BigDecimal> result = simpleSequence.toList();
        //直接使用Collections.sort进行排序
        Collections.sort(result, new Comparator<BigDecimal>() {
            public int compare(BigDecimal o1, BigDecimal o2) {
                //                return o1.intValue()-o2.intValue();//降序
                return o1.intValue()-o2.intValue();//升序
            }
        });
        return result;
    }
}
