package icu.saymeevetime.backend.common;

import icu.saymeevetime.backend.utils.common.vo.BaseResponseVO;
import org.junit.Test;

/**
 * @author jiacheng
 * @date 11/3/23 9:24 pm 星期六
 * @description :
 */
public class UtilsTest {


    @Test
    public void test() {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        System.out.println(baseResponseVO.run("hello world"));
    }
}
