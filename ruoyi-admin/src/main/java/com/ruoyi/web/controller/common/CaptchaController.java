package com.ruoyi.web.controller.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.imageio.ImageIO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.Producer;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.web.domain.AjaxResult;
import com.ruoyi.common.redis.RedisCache;
import com.ruoyi.common.utils.sign.Base64;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 验证码操作处理
 *
 * @author ruoyi
 */
@Api(value = "验证码操作处理")
@RestController
public class CaptchaController {

    /**
     * 验证码类型 math 数组计算
     */
    public static final String MATH = "math";
    /**
     * 验证码类型 char 字符验证
     */
    public static final String CHAR = "char";

    /**
     * 验证码服务
     */
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    /**
     * 验证码数字服务
     */
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * Redis服务工具类
     */
    @Autowired
    private RedisCache redisCache;
    /**
     * 系统配置服务
     */
    @Autowired
    private ISysConfigService configService;

    /**
     * 生成验证码
     * @return AjaxResult
     */
    @GetMapping("/captchaImage")
    @ApiOperation(value = "生成验证码")
    public AjaxResult getCode() {
        AjaxResult ajax = AjaxResult.success();
        //查询验证码开关是否开启
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        ajax.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled) {
            return ajax;
        }
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        //验证码确认Key
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        //验证码字符串   code验证码值
        String capStr, code = null;
        BufferedImage image = null;

        //获取验证码的类型
        String captchaType = RuoYiConfig.getCaptchaType();

        //生成验证码
        if (MATH.equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if (CHAR.equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            assert image != null;
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }

}
