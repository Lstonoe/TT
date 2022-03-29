package com.tqy.blog.service;

import com.tqy.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/2/23 9:52 下午
 */
public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);
    Tag getTagByNames(String name);

    Page<Tag> listTag(Pageable pageable);
    List<Tag> listTag();
    List<Tag> listTagTop(Integer size);
    List<Tag> ListTag(String ids);
    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);
}
