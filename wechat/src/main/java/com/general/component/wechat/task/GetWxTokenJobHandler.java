//package com.general.component.wechat.task;
//
//import com.alibaba.fastjson.JSONObject;
//import com.general.component.wechat.core.constants.UrlConstants;
//import com.qianlima.xxljobtask.constants.RedisConstants;
//import com.qianlima.xxljobtask.constants.WxUrlConstants;
//import com.qianlima.xxljobtask.utils.HttpClientUtil;
//import com.qianlima.xxljobtask.utils.RedisUtil;
//import com.qianlima.xxljobtask.utils.SpringUtil;
//import com.xxl.job.core.biz.model.ReturnT;
//import com.xxl.job.core.handler.IJobHandler;
//import com.xxl.job.core.handler.annotation.JobHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @ClassName: GetWxTokenJobHandler
// * @Description: 获取微信的token
// * @author ly
// * @date 2019年1月24日 上午9:08:17
// * @version 1.0
// */
//@JobHandler(value = "getWxTokenJobHandler")
//@Component
//public class GetWxTokenJobHandler extends IJobHandler{
//	private static final Logger logger = LoggerFactory.getLogger(GetWxTokenJobHandler.class);
//
//	private RedisUtil redisUtil=null;
//	@Value("${wx.miniapp.xvzconfigs[0].appid}")
//    private String xvzMiniAppId;
//	@Value("${wx.miniapp.xvzconfigs[0].secret}")
//    private String xvzMiniSecret;
//	@Value("${wx1.publicnumber.xvzconfigs[0].appid}")
//	private String xvzPublicnumberAppId;
//	@Value("${wx1.publicnumber.xvzconfigs[0].secret}")
//	private String xvzPublicnumberSecret;
//
//    @Value("${wx1.publicnumber.indexconfigs[0].appid}")
//    private String indexAppId;
//    @Value("${wx1.publicnumber.indexconfigs[0].secret}")
//    private String indexSecret;
//
//    @Value("${wx1.publicnumber.yflmconfigs[0].appid}")
//    private String yflmAppId;
//    @Value("${wx1.publicnumber.yflmconfigs[0].secret}")
//    private String yflmSecret;
//
//    @Value("${wx.miniapp.preconfigs[0].appid}")
//    private String preconAppId;
//    @Value("${wx.miniapp.preconfigs[0].secret}")
//    private String preconSecret;
//
//    @Value("${wx.miniapp.memconfigs[0].appid}")
//    private String memconAppId;
//    @Value("${wx.miniapp.memconfigs[0].secret}")
//    private String memconSecret;
//
//    @Value("${wx.miniapp.innovationclockconfigs[0].appid}")
//    private String innovationclockAppId;
//    @Value("${wx.miniapp.innovationclockconfigs[0].secret}")
//    private String innovationclockSecret;
//
//	@Value("${wx1.publicnumber.rewardconfigs[0].appid}")
//	private String rewardAppId;
//	@Value("${wx1.publicnumber.rewardconfigs[0].secret}")
//	private String rewardSecret;
//	/**
//	 * @Title: execute
//	 * @Description: 定时获取token
//	 * @date 2019年1月24日 上午9:08:51
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	@Override
//	public ReturnT<String> execute(String param)  {
//		logger.info("------------------------------------开始获取token----------------------------------------");
//		if(redisUtil==null) {
//			redisUtil= SpringUtil.getBean(RedisUtil.class);
//		}
//		Map<String, String> map=new HashMap<>();
//		map.put("grant_type", "client_credential");
//
//		//获取小微站app token
//		String xvzAppToken = getWxToken(map, xvzMiniAppId, xvzMiniSecret);
//		//获取小微站公众号 token
//		String xvzPublicnumberToken = getWxToken(map, xvzPublicnumberAppId, xvzPublicnumberSecret);
//		//获取乙方联盟的token
//		String yflmToken = getWxToken(map, yflmAppId, yflmSecret);
//		//获取创新研发的token
//		String innovationclockToken = getWxToken(map, innovationclockAppId, innovationclockSecret);
//		//获取微商会会长端token
//		String preToken = getWxToken(map, preconAppId, preconSecret);
//		//获取微商会会员段token
//		String memToken = getWxToken(map, memconAppId, memconSecret);
//		//获取千里马指数token
//		String indexToken = getWxToken(map, indexAppId, indexSecret);
//		//获取悬赏项目的token
//		logger.info("rewardAppId-"+rewardAppId+",rewardSecret-"+rewardSecret);
//		String rewardToken=getWxToken(map, rewardAppId, rewardSecret);
//		logger.info("------------------------------------开始存token----------------------------------------");
//		logger.info("------------------------------------INNOVATIONCLOCK_TOKEN:"+innovationclockToken+"----------------------------------------");
//		redisUtil.setKeyExpire(RedisConstants.INNOVATIONCLOCK_TOKEN, innovationclockToken, RedisConstants.TOKEN_EXPIRE);
//		logger.info("------------------------------------xvzAppToken:"+xvzAppToken+"----------------------------------------");
//		redisUtil.setKeyExpire(RedisConstants.XVZ_APP_TOKEN, xvzAppToken, RedisConstants.TOKEN_EXPIRE);
//		logger.info("------------------------------------xvzPublicnumberToken:"+xvzPublicnumberToken+"----------------------------------------");
//		redisUtil.setKeyExpire(RedisConstants.XVZ_PUBLICNUM_TOKEN, xvzPublicnumberToken, RedisConstants.TOKEN_EXPIRE);
//		redisUtil.setKeyExpire(RedisConstants.YFLM_TOKEN, yflmToken, RedisConstants.TOKEN_EXPIRE);
//		redisUtil.setKeyExpire(RedisConstants.PRE_TOKEN, preToken, RedisConstants.TOKEN_EXPIRE);
//		redisUtil.setKeyExpire(RedisConstants.MEM_TOKEN, memToken, RedisConstants.TOKEN_EXPIRE);
//		redisUtil.setKeyExpire(RedisConstants.INDEX_TOKEN, indexToken, RedisConstants.TOKEN_EXPIRE);
//		redisUtil.setKeyExpire(RedisConstants.REWARD_TOKEN, rewardToken, RedisConstants.TOKEN_EXPIRE);
//
//		return SUCCESS;
//	}
//
//	/**
//	 * @ Description	获取token
//	 *
//	 * @ param
//	 * @ return
//	 * @ Author  ly
//	 * @ Date  2019-06-12
//	 * @ Version 1.0
//	 */
//	private String getWxToken(Map<String, String> map, String appId, String secret){
//		map.put("appid", appId);
//		map.put("secret", secret);
//		String jsonToken= HttpClientUtil.getRequest(UrlConstants.PUBLICACCOUNT_TOKEN_URL, map);
//		JSONObject jsonObject=JSONObject.parseObject(jsonToken);
//		String token=jsonObject.getString("access_token");
//		return token;
//	}
//
//
//}
