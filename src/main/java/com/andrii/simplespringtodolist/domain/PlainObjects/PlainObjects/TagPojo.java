package com.andrii.simplespringtodolist.domain.PlainObjects.PlainObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TagPojo {
    private Long id;
    private String name;
    private Set<Long> todoListIds = new HashSet<>();
}
