/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.dealer.domain;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    public DomainCatalog() {
        actions.put("SUBMIT", new WorkflowAction("SUBMIT", "提交渠道审批", List.of("草稿"), "待审核", "OPERATOR"));
        actions.put("APPROVE", new WorkflowAction("APPROVE", "批准渠道业务", List.of("待审核"), "已批准", "ADMIN"));
        actions.put("SETTLE", new WorkflowAction("SETTLE", "完成返利结算", List.of("已批准"), "已结算", "ADMIN"));
    }
    public String systemName() { return "知华科技经销商与渠道管理系统"; }
    public String scene() { return "经销商准入、区域、协议、价格、订单、库存、返利、目标、窜货与绩效"; }
    public String initialStatus() { return "草稿"; }
    public String partyLabel() { return "经销商/渠道"; }
    public String amountLabel() { return "渠道金额"; }
    public String quantityLabel() { return "订单数量"; }
    public String dueLabel() { return "履约期限"; }
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("ONBOARDING", "经销商准入", "完成资质、信用、协议和组织审批"),
            new ModuleDefinition("TERRITORY", "区域授权", "管理品牌、产品、区域和有效期"),
            new ModuleDefinition("PRICE", "渠道价格", "维护等级价、促销价和最低成交规则"),
            new ModuleDefinition("ORDER", "渠道订单", "处理下单、信用校验、履约和回执"),
            new ModuleDefinition("INVENTORY", "渠道库存", "采集进销存并识别积压和断货"),
            new ModuleDefinition("REBATE", "返利管理", "配置政策、计提、核销和对账"),
            new ModuleDefinition("TARGET", "目标管理", "分解销量、收入、铺货和回款目标"),
            new ModuleDefinition("ANTI_CHANNEL_CONFLICT", "渠道稽核", "识别窜货、低价和跨区销售"),
            new ModuleDefinition("PERFORMANCE", "渠道绩效", "综合增长、回款、覆盖和服务评分")
        ); }
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    public record ModuleDefinition(String code,String name,String description) {}
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}
