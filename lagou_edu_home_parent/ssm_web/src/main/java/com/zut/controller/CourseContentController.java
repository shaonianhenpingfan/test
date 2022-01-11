package com.zut.controller;

import com.zut.domain.Course;
import com.zut.domain.CourseLesson;
import com.zut.domain.CourseSection;
import com.zut.domain.ResponseResult;
import com.zut.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(int courseId){
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);
        return new ResponseResult(true, 200, "章节及课时查询成功", list);
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(int courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        return new ResponseResult(true, 200, "查询课程成功", course);
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {
        if (section.getId() == null){
            courseContentService.saveSection(section);
            return new ResponseResult(true, 200, "添加成功", null);
        }else{
            courseContentService.updateSection(section);
            return new ResponseResult(true, 200, "修改成功", null);
        }

    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id, @RequestParam int status){
        courseContentService.updateSectionStatus(id, status);

        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);

        return new ResponseResult(true, 200, "章节状态修改成功", map);
    }

    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson){
        if (lesson.getId() == null){
            courseContentService.saveLesson(lesson);
            return new ResponseResult(true, 200, "添加成功", null);
        }else{
            courseContentService.updateLesson(lesson);
            return new ResponseResult(true, 200, "添加成功", null);
        }
    }

}
