package com.springboot.community.mapper;

import com.springboot.community.model.Question;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}