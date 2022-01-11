package com.zut.service;

import com.zut.domain.Course;
import com.zut.domain.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /**
     * 多条件查询课程信息
     * */
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /**
     * 添加课程和教师信息
     * */
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /**
     * 根据id查询课程信息
     * */
    public CourseVo findCourseById(int id);

    /**
     * 更新讲师信息及课程信息
     * */
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /**
     * 修改课程状态
     * */
    public void updateCourseStatus(int courseId, int status);
}
