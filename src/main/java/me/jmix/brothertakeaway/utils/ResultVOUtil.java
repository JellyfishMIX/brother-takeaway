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

    // public static ResultVO error(String stateInfo, Object data) {
    //     ResultVO resultVO = new ResultVO();
    //     resultVO.setCode();
    // }
}
