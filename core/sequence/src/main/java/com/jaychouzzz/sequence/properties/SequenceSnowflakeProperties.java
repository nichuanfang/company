package com.jaychouzzz.sequence.properties;

import lombok.Data;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author lengleng
 * @date 2019-05-26
 * <p>
 * Snowflake 发号器属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "jaychouzzz.xsequence.snowflake")
public class SequenceSnowflakeProperties extends BaseSequenceProperties implements InitializingBean {

	/**
	 * 数据中心ID，值的范围在[0,31]之间，一般可以设置机房的IDC[必选]
	 */
	private long datacenterId;
	/**
	 * 工作机器ID，值的范围在[0,31]之间，一般可以设置机器编号[必选]
	 */
	private long workerId;

	@Override
	public void afterPropertiesSet(){
		this.datacenterId = this.initDataCenterId();
		this.workerId = this.initWorkerId();
	}

	/**
	 *
	 * @return 生成机器id
	 */
	private Long initWorkerId(){
		try {
			String hostAddress = Inet4Address.getLocalHost().getHostAddress();
			int[] ints = StringUtils.toCodePoints(hostAddress);
			int sums = 0;
			for(int b : ints){
				sums += b;
			}
			return (long)(sums % 32);
		} catch (UnknownHostException e) {
			// 如果获取失败，则使用随机数备用
			return RandomUtils.nextLong(0,31);
		}
	}

	/**
	 * 生成数据中心id
	 * @return 数据中心id
	 */
	private Long initDataCenterId(){
		int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
		int sums = 0;
		for (int i: ints) {
			sums += i;
		}
		return (long)(sums % 32);
	}
}
