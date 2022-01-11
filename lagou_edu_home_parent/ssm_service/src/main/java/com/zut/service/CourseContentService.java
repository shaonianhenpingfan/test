package com.zut.service;

import com.zut.domain.Course;
import com.zut.domain.CourseLesson;
import com.zut.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /**
     * 根据id查询课程章节和课时
     * */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /**
     * 根据id查询课程信息
     * */
    public Course findCourseByCourseId(int courseId);

    /**
     * 新增章节
     * */
    public void saveSection(CourseSection section);

    /**
     * 修改章节
     * */
    public void updateSection(CourseSection section);

    /**
     * 修改课程章节状态信息
     * */
    public void updateSectionStatus(int id,int status);

    /**
     * 新增课时信息
     * */
    public void saveLesson(CourseLesson lesson);

    /**
     * 修改课程信息
     * */
    void updateLesson(CourseLesson lesson);
}
