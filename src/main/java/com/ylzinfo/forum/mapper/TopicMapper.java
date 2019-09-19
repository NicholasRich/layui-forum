package com.ylzinfo.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylzinfo.forum.entity.Topic;
import org.apache.ibatis.annotations.Param;

public interface TopicMapper extends BaseMapper<Topic> {
    IPage<Topic> getPublish(Page page, @Param("userId") Long userId);

    IPage<Topic> getCollection(Page page, @Param("userId") Long userId);

    Topic getDetail(@Param("id") Long id, @Param("userId") Long userId);

    Topic getContent(@Param("id")Long id);
}
