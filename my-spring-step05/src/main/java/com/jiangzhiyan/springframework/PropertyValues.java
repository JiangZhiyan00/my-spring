package com.jiangzhiyan.springframework;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author JiangZhiyan
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        Optional<PropertyValue> first = propertyValueList.parallelStream().filter(p -> p.getName().equals(propertyName)).findFirst();
        return first.orElse(null);
    }
}
