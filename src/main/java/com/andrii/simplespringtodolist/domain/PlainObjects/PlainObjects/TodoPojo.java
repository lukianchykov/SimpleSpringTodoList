package com.andrii.simplespringtodolist.domain.PlainObjects.PlainObjects;

import com.andrii.simplespringtodolist.domain.Priority;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TodoPojo {

    private Long id;
    private String name;
    private String comment;
    private Date startDate;
    private Date endDate;
    private Boolean important;
    private Priority priority;
    private Set<TagPojo> tagList = new HashSet<>();
    private Long userId;

}
