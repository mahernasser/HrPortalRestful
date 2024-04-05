package com.llun.mapper;


import jakarta.ws.rs.core.Link;
import org.mapstruct.Named;

public class LinkBuilder {

    @Named("departmentLink")
    public Link departmentLink(Integer departmentId) {
        return Link.fromUri("/departments/{id}")
                .rel("department")
                .build(departmentId);
    }
}
