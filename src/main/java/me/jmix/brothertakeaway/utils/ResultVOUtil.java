package me.jmix.brothertakeaway.utils;

import me.jmix.brothertakeaway.vo.ResultVO;

import java.util.Map;

public class ResultVOUtil {
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setStateCode(0);
        resultVO.setStateInfo("操作成功");
        return resultVO;
    }

    public static ResultVO success(String stateInfo, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setStateCode(0);
        resultVO.setStateInfo(stateInfo);
        resultVO.setData(data);
        return resultVO;
    }
}
