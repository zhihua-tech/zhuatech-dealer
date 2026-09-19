/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.dealer.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class DomainDecisionService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public DecisionResult assess(DecisionRequest request) { double available=Math.max(0,request.creditLimit()-request.outstanding());int score=100;List<String> actions=new ArrayList<>();if(!request.active()){score-=70;actions.add("阻断无效经销商交易");}if(request.orderAmount()>available){score-=40;actions.add("申请信用额度或收款后下单");}if(request.territoryConflict()){score-=40;actions.add("处理渠道区域冲突");}if(request.targetAchievementRate()<70){score-=15;actions.add("制定渠道提升计划");}return result(score,actions,"APPROVE","MANUAL_REVIEW","REJECT",Map.of("availableCredit",available,"creditAfterOrder",available-request.orderAmount(),"rebateEstimate",request.orderAmount()*request.rebateRate()/100)); }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record DecisionRequest(
        @NotBlank String dealerCode,
        @PositiveOrZero double creditLimit,
        @PositiveOrZero double outstanding,
        @PositiveOrZero double orderAmount,
        @DecimalMin("0") @DecimalMax("100") double rebateRate,
        @PositiveOrZero double targetAchievementRate,
        boolean active,
        boolean territoryConflict) {}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}
