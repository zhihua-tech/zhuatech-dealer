# 经销商与渠道管理系统 API

所有业务接口默认位于 `/api`，除 `/public/**` 和健康检查外均需要 HTTP Basic 身份认证。生产环境应接入企业 IAM 或统一身份平台。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/public/about` | 产品、公司、官网和许可元数据 |
| GET | `/catalog` | 业务模块、字段标签和状态动作 |
| GET | `/dashboard` | 业务规模、金额、状态和模块统计 |
| GET/POST | `/records` | 业务台账查询与创建 |
| GET/PUT/DELETE | `/records/{id}` | 详情、草稿修改与删除 |
| POST | `/records/{id}/actions` | 执行服务端状态迁移 |
| POST | `/records/{id}/comments` | 增加协作记录 |
| GET | `/records/{id}/timeline` | 查询完整操作时间线 |
| GET | `/records/search` | 组合检索、分页和逾期筛选 |
| GET | `/records/export.csv` | 导出 UTF-8 CSV |
| GET | `/sla-summary` | SLA、逾期、风险和人员工作量 |
| POST | `/domain/decision` | 执行经销商与渠道管理系统专属领域规则 |
| GET/POST | `/enterprise/controls` | 企业控制项查询与幂等创建 |
| POST | `/enterprise/controls/{id}/submit` | 提交复核 |
| POST | `/admin/enterprise/controls/{id}/review` | 管理员审批或驳回 |
| POST | `/enterprise/controls/{id}/documents` | 登记附件哈希及存储元数据 |
| POST | `/enterprise/controls/{id}/complete` | 凭证完整后办结 |
| POST | `/admin/enterprise/controls/{id}/sync` | 登记外部系统回执 |

## 领域决策字段

| 字段 | 类型 | 含义 |
| --- | --- | --- |
| `dealerCode` | String | 经销商编号 |
| `creditLimit` | double | 信用额度 |
| `outstanding` | double | 已占用额度 |
| `orderAmount` | double | 本次订单金额 |
| `rebateRate` | double | 返利比例(%) |
| `targetAchievementRate` | double | 目标达成率(%) |
| `active` | boolean | 经销商有效 |
| `territoryConflict` | boolean | 存在区域冲突 |

接口统一返回 `ApiResponse`；业务冲突使用 HTTP 409，参数错误使用 400，未认证使用 401，无权限使用 403。

## 专业渠道经营接口

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/api/dealer-ops/dashboard` | 伙伴、订单与返利总览 |
| POST | `/api/dealer-ops/dealers` | 登记经销商 |
| POST | `/api/dealer-ops/dealers/{id}/submit` | 提交准入审核 |
| POST | `/api/admin/dealer-ops/dealers/{id}/approve` | 批准准入及授信 |
| POST | `/api/dealer-ops/dealers/{id}/authorizations` | 建立产品区域授权与价格底线 |
| POST | `/api/dealer-ops/orders` | 创建渠道订单并执行门禁 |
| POST | `/api/admin/dealer-ops/orders/{id}/approve` | 批准并占用信用额度 |
| POST | `/api/dealer-ops/orders/{id}/fulfill` | 登记履约 |
| POST | `/api/dealer-ops/orders/{id}/rebates` | 申请返利 |
| POST | `/api/admin/dealer-ops/rebates/{id}/settle` | 审批结算返利 |
