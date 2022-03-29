package com.tqy.blog.service;

import com.tqy.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/2/23 9:52 下午
 */
public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);
    Type getTypeByNames(String name);

    Page<Type> listType(Pageable pageable);
    List<Type> listType();
    List<Type> listTypeTop(Integer size);

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
