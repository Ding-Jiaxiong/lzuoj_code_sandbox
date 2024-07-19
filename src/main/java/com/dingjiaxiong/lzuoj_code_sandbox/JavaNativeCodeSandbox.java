package com.dingjiaxiong.lzuoj_code_sandbox;

import cn.hutool.core.io.resource.ResourceUtil;
import com.dingjiaxiong.lzuoj_code_sandbox.model.ExecuteCodeRequest;
import com.dingjiaxiong.lzuoj_code_sandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Java 原生代码沙箱实现（直接复用模板方法）
 */
@Component
public class JavaNativeCodeSandbox extends JavaCodeSandboxTemplate {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }

//    public static void main(String[] args) {
//
//
//        JavaNativeCodeSandbox javaNativeCodeSandbox = new JavaNativeCodeSandbox();
//        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
//
//        executeCodeRequest.setInputList(Arrays.asList("1 2", "1 3"));
//
//        String code = ResourceUtil.readStr("testCode/simpleComputeArgs/Main.java", StandardCharsets.UTF_8);
////        String code = ResourceUtil.readStr("testCode/unsafeCode/RunFileError.java", StandardCharsets.UTF_8);
//
//        executeCodeRequest.setCode(code);
//        executeCodeRequest.setLanguage("java");
//
//        ExecuteCodeResponse executeCodeResponse = javaNativeCodeSandbox.executeCode(executeCodeRequest);
//
//        System.out.println(executeCodeResponse);
//
//    }

}
