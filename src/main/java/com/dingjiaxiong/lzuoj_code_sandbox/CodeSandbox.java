package com.dingjiaxiong.lzuoj_code_sandbox;


import com.dingjiaxiong.lzuoj_code_sandbox.model.ExecuteCodeRequest;
import com.dingjiaxiong.lzuoj_code_sandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
