package com.funti.hello.spring.thymeleaf.controller;

import com.funti.hello.spring.thymeleaf.dao.DepartmentDao;
import com.funti.hello.spring.thymeleaf.dao.EmployeeDao;
import com.funti.hello.spring.thymeleaf.entities.Department;
import com.funti.hello.spring.thymeleaf.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@Controller
//提供给用户 http 请求接口，用户请求接口进行数据访问。
public class EmployeeController {

    @Autowired
    //自动装配
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    //request mapping get 方式
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps", employees);

        //thymeleaf 默认就会拼串
        //classpath:/templates/ + "emp/list" + .html
        return "emp/list";
    }
    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //来到添加页面,查出所有的部门，在页面先似乎
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //员工添加
    //springMVC自动将请求参数和入参对象的属性一一绑定：要求请求参数的名字和javabean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        //来到员工列表页面

        System.out.println("保存的员工信息: " + employee);
        //保存员工
        employeeDao.save(employee);

        //redirect:表示重定向一个地址  “/”代表当前项目路径， 参照ThymeleafViewResolver.java
        //forawrd:表示转发到一个地址

        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "emp/add";
    }
    //员工修改,需要提交员工id
    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {

        employeeDao.save(employee);
        System.out.println("修改的员工数据："+employee);
        return "redirect:/emps";
    }


    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        System.out.println("删除员工" + id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
