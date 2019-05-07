package com.itsisterhood.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        return filter(someBean, "field1", "field2");

    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeans(){
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value12", "value22", "value32"));

        return filter(someBeans, "field2", "field3");
    }

    private MappingJacksonValue filter(Object object, String... fieldsToKeep) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToKeep);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);
        MappingJacksonValue mapping = new MappingJacksonValue(object);
        mapping.setFilters(filterProvider);
        return mapping;
    }
}
