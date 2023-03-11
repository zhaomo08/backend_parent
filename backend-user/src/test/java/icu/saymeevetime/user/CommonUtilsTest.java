package icu.saymeevetime.user;

import icu.saymeevetime.backend.utils.common.vo.BaseResponseVO;
import org.junit.Test;

/**
 * @author jiacheng
 * @date 11/3/23 9:37 pm 星期六
 * @description :
 */
public class CommonUtilsTest {

    @Test
    public void test() {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        System.out.println(baseResponseVO.run("gell"));
    }
}
