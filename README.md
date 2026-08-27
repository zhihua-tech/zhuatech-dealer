# ZhuaTech DMS｜经销商与渠道管理系统

> 连接品牌方与渠道伙伴，统一订单、价格、库存、返利和绩效

ZhuaTech DMS 是知华科技（上海如静知华信息科技有限公司）发布的企业级源码项目，面向“经销商准入、区域、协议、价格、订单、库存、返利、目标、窜货与绩效”提供管理端与响应式业务端。工程采用前后端分离架构，所有示例数据均为虚构数据。

[知华科技官网](https://www.zhuatech.cn/) · [架构说明](docs/ARCHITECTURE.md) · [API 文档](docs/API.md) · [企业能力](docs/ENTERPRISE.md) · [测试说明](docs/TESTING.md)

![经销商与渠道管理系统产品界面示意](docs/images/product-overview.svg)

## 业务模块

| 模块 | 核心能力 |
| --- | --- |
| 经销商准入 | 完成资质、信用、协议和组织审批 |
| 区域授权 | 管理品牌、产品、区域和有效期 |
| 渠道价格 | 维护等级价、促销价和最低成交规则 |
| 渠道订单 | 处理下单、信用校验、履约和回执 |
| 渠道库存 | 采集进销存并识别积压和断货 |
| 返利管理 | 配置政策、计提、核销和对账 |
| 目标管理 | 分解销量、收入、铺货和回款目标 |
| 渠道稽核 | 识别窜货、低价和跨区销售 |
| 渠道绩效 | 综合增长、回款、覆盖和服务评分 |

![经销商与渠道管理系统业务闭环](docs/images/workflow.svg)

## 企业级控制

- ADMIN / OPERATOR 角色边界和管理员接口隔离；
- 服务端字段、模块、唯一编号和状态迁移校验；
- 组织、期间、责任人、风险等级、到期日和 SLA 统计；
- 幂等创建、JPA 乐观锁、重复提交保护和职责分离；
- 附件 SHA-256 元数据、业务凭证完整性与全流程审计；
- 组合检索、分页、逾期筛选、UTF-8 CSV 导出和协作时间线；
- 外部系统仅预留适配器，使用方自行配置地址与凭据；
- prod profile 拒绝默认密码、弱数据库口令和本地跨域来源。

## 技术架构

- 后端：Java 21、Spring Boot、Spring Security、JPA、Bean Validation、Actuator
- 前端：Vue 3、Vite、Axios，支持桌面端与移动端响应式布局
- 数据库：MySQL 8；自动化测试使用 H2
- 交付：Docker Compose、Nginx、环境变量、GitHub Actions
- Java 包名：`cn.zhuatech.dealer`

## 启动与测试

```bash
cd backend && mvn test
cd ../frontend && npm install && npm run build
cd .. && cp .env.example .env && docker compose up --build
```

开发演示账号：`admin / admin123`、`operator / operator123`。生产环境必须通过环境变量替换全部默认凭据。

## 许可与商业授权

Copyright © 2026 上海如静知华信息科技有限公司。

本工程仅允许个人学习、研究和非商业技术交流，**不得用于商业用途**。企业内部使用、生产部署、SaaS运营、项目交付、品牌替换、收费培训、咨询实施或再分发，均须事先获得上海如静知华信息科技有限公司书面授权，详见 [LICENSE](LICENSE)。

深度开发、私有化部署、系统集成与企业数字化咨询，请访问[知华科技官网](https://www.zhuatech.cn/)或扫码联系：

| 微信咨询一 | 微信咨询二 |
| --- | --- |
| ![微信咨询二维码一](docs/images/zhuatech-wechat-consulting.png) | ![微信咨询二维码二](docs/images/zhuatech-wechat-consulting-2.png) |

SEO：经销商与渠道管理系统、DMS系统源码、企业数字化、Java企业系统、Vue管理系统、知华科技、上海如静知华信息科技有限公司。

## V2.0 专业渠道经营域

新增经销商准入、信用额度、产品区域授权、最低成交价、渠道订单和返利结算模型。订单创建时同时校验协议期限、区域产品授权、价格底线与可用信用；履约订单才可申请返利，申请和批准金额均受服务端规则控制。专业入口为“渠道经营中心”，API 根路径为 `/api/dealer-ops`。
