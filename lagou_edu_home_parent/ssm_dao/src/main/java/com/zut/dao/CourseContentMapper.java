package com.zut.dao;

import com.zut.domain.Course;
import com.zut.domain.CourseLesson;
import com.zut.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /**
     * 查询课程下的章节和课时信息
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
     * 修改章节状态
     * */
    public void updateSectionStatus(CourseSection section);

    /**
     * 保存课时
     * */
    public void saveLesson(CourseLesson lesson);

    public void updateLesson(CourseLesson lesson);
}
