package me.jmix.brothertakeaway.utils;

import me.jmix.brothertakeaway.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("操作成功");
        return resultVO;
    }

    public static ResultVO success(String stateInfo, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg(stateInfo);
        resultVO.setData(data);
        return resultVO;
    }

    // 表示异常的ResultVO
    public static ResultVO error(String exceptionClassName, Integer stateCode, String errMsg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setExceptionClassName(exceptionClassName);
        resultVO.setCode(stateCode);
        resultVO.setMsg(errMsg);
        return resultVO;
    }
}
