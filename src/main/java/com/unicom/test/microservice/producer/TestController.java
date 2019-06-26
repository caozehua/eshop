package com.unicom.test.microservice.producer;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "演示-order" })
@RestController
public class TestController {
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

	@ApiOperation("获取订单信息")
	@GetMapping("/api/v1/orderinfo")
	public Object gainOrderInfo() {
		Map<String, Object> result = new HashMap<>();
		result.put("result", "ok");
		result.put("message", "欢迎各位领导莅临指导检查工作。");
		LOG.trace("controller trace测试");
		LOG.debug("controller debug测试");
		LOG.info("controller info测试");
		LOG.warn("controller warn测试");
		LOG.error("controller error测试");
		return result;
	}

	@ApiOperation("新增商品")
	@PostMapping("/api/v1/good")
	public Object creatGood(@ApiParam(value = "商品ID", required = true) @RequestParam String arg1,
			@ApiParam(value = "商品名称", required = true) @RequestParam String arg2) {
		Map<String, Object> result = new HashMap<>();
		result.put("arg1", arg1);
		result.put("arg2", arg2);
		result.put("result", arg1 + arg2);
		return result;
	}

	@ApiOperation("修改商品信息")
	@PutMapping("/api/v1/goodinfo")
	public Object modifyGoodInfo() {
		Map<String, Object> result = new HashMap<>();
		result.put("result", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return result;
	}

	@ApiOperation("删除商品")
	@DeleteMapping("/api/v1/good")
	public Object deleteGood(@ApiParam(value = "商品ID", required = true) @RequestParam String arg1) {
		Map<String, Object> result = new HashMap<>();
		result.put("result", HashUtil.getMD5String32(arg1.getBytes(Charset.forName("UTF-8"))));
		return result;
	}
}
