package com.example.controller;

import com.example.annotation.Log;
import com.example.entity.Account;
import com.example.enums.EnumsConnection;
import com.example.service.AccountService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "用户controller", tags = {"用户操作接口"})
@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/select")
    @ApiOperation(value = "对象传参", notes = "注意问题点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", defaultValue = "0", value = "用户姓名", required = true, paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 500, message = "内部错误"),
            @ApiResponse(code = 1, message = "操作失败")
    })
    @RequiresPermissions("system:account:select")
    @Log(describe = "用户查询", businessType = EnumsConnection.BusinessType.State.SELECT)
    public List<Account> index(@ApiIgnore @RequestBody Account account) {
        return accountService.findAll();
    }

    /**
     * @return
     */
    @GetMapping("/path/{id}")
    @ApiOperation("路径传参")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 500, message = "内部错误"),
            @ApiResponse(code = 1, message = "操作失败")
    })
    public Account path(@PathVariable("id") String id) {
        Account account = new Account();
        return account;
    }

    /**
     * @return
     */
    @PostMapping("/form")
    @ApiOperation("表单提交")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "age", value = "用户年龄", required = true, dataType = "String", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 500, message = "内部错误"),
            @ApiResponse(code = 1, message = "操作失败")
    })
    public List<Account> form(String name, String age) {
        return accountService.findAll();
    }

    /**
     * @return
     */
    @PostMapping("/entity")
    @ApiOperation("实体对象")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 500, message = "内部错误"),
            @ApiResponse(code = 1, message = "操作失败")
    })
    public List<Account> index4(@RequestBody Account account) {
        return accountService.findAll();
    }

    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传图片", notes = "上传图片")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 500, message = "内部错误"),
            @ApiResponse(code = 1, message = "操作失败")
    })
    public List<Account> upload(HttpServletRequest httpRequest,@ApiParam(value = "选择图片", required = true) MultipartFile file, Account account) {
        MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpRequest;
        List<MultipartFile> files = request.getFiles("file");
        System.out.println(files);

        System.out.println(files.size());

        return accountService.findAll();
    }

}

