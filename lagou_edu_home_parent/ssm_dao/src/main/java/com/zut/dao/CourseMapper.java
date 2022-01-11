package com.zut.dao;

import com.zut.domain.Course;
import com.zut.domain.CourseVo;
import com.zut.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    /**
     * 多条件查询课程
     * */
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /**
     * 添加课程信息，并返回新添加的课程的id
     * */
    public int saveCourse(Course course);

    /**
     * 添加教师信息
     * */
    public void saveTeacher(Teacher teacher);

    /**
     * 根据id查询课程信息
     * */
    public CourseVo findCourseById(int id);

    /**
     * 更新课程信息
     * */
    public void updateCourse(Course course);

    /**
     * 更新讲师信息
     * */
    public void updateTeacher(Teacher teacher);

    /**
     * 修改课程状态
     * */
    public void updateCourseStatus(Course course);
}
