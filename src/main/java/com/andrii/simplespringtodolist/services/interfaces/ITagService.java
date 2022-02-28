package com.andrii.simplespringtodolist.services.interfaces;

import com.andrii.simplespringtodolist.domain.Tag;

public interface ITagService {
    Tag findOrCreate(Tag tag);
}
