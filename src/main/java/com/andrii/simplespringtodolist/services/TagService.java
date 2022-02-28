package com.andrii.simplespringtodolist.services;

import com.andrii.simplespringtodolist.domain.Tag;
import com.andrii.simplespringtodolist.repositories.TagRepository;
import com.andrii.simplespringtodolist.services.interfaces.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class TagService implements ITagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService (TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    @Override
    @Transactional
    public Tag findOrCreate(Tag tag) {

        List<Tag> foundTags = tagRepository.findByName(tag.getName());

        if(foundTags.isEmpty()) {
            tagRepository.save(tag);
            return tag;
        } else {
            return foundTags.get(0);
        }
    }
}