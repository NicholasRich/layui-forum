package com.ylzinfo.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylzinfo.forum.entity.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TopicMapper extends BaseMapper<Topic> {
    IPage<Topic> getPublish(Page page, @Param("userId") String userId);

    List<Map<String, Object>> getPublishAndCollectionCount(@Param("userId") String userId);

    IPage<Topic> getCollection(Page page, @Param("userId") String userId);

    Topic getDetail(@Param("id") Long id, @Param("userId") String userId);
}
