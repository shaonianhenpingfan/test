package com.zut.controller;

import com.zut.domain.Course;
import com.zut.domain.CourseVo;
import com.zut.domain.ResponseResult;
import com.zut.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 多条件查询课程信息
     * */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo)
    {
        List<Course> courses = courseService.findCourseByCondition(courseVo);
        return new ResponseResult(true, 200, "响应成功", courses);
    }

    /**
     * 课程图片上传
     * */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        String realPath = request.getServletContext().getRealPath("/");
        String webappPath = realPath.substring(0, realPath.indexOf("ssm_web"));
        String originalFilename = file.getOriginalFilename();
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String uploadPath = webappPath + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
            System.out.println("创建目录" + filePath);
        }
        file.transferTo(filePath);
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8888/upload/" + newFileName);

        return new ResponseResult(true, 200, "图片上传成功", map);
    }

    /**
     * 添加新增或修改的课程及讲师信息
     * */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        if (courseVo.getId() == null){
            courseService.saveCourseOrTeacher(courseVo);
            return new ResponseResult(true, 200, "新增成功", null);
        }else {
            courseService.updateCourseOrTeacher(courseVo);
            return new ResponseResult(true, 200, "修改成功", null);
        }

    }

    /**
     * 根据id查询课程信息及关联的讲师信息
     * */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(int id){
        CourseVo courseVo = courseService.findCourseById(id);
        return new ResponseResult(true, 200, "根据id查询课程成功", courseVo);
    }

    /**
     * 课程状态管理
     * */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id, int status){
        courseService.updateCourseStatus(id, status);
        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);
        return new ResponseResult(true, 200, "课程状态变更成功", map);
    }
}
