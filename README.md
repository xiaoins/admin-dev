# 电商后台管理系统

## 项目介绍

基于 Spring Boot 3.x 的电商后台管理系统，提供完整的后台管理功能，包括商品管理、订单管理、营销管理、数据统计等核心模块。

### 项目信息

- **项目名称**：电商后台管理系统
- **作者**：苏垒
- **学号**：230601186
- **专业班级**：计应23.8班
- **版本**：v1.0

## 技术栈

- **运行环境**：JDK 21、MySQL 8.0、Redis 6+
- **核心框架**：
  - Spring Boot 3.3.4
  - Spring Security 6.x
  - Spring Data Redis
  - MyBatis-Plus 3.5.5
  - Flyway（数据库迁移）
- **认证鉴权**：JWT (JSON Web Token)
- **API文档**：Knife4j 4.4.0 (OpenAPI 3)
- **工具库**：Lombok、Hutool、Guava

## 功能模块

### 1. 认证与授权
- ✅ 用户登录/登出
- ✅ JWT Token认证
- ✅ 基于角色的权限控制(RBAC)
- ✅ 密码加密存储(BCrypt)

### 2. 用户管理
- ✅ 用户CRUD
- ✅ 角色管理
- ✅ 权限配置

### 3. 商品管理
- ✅ 商品分类管理（树形结构）
- ✅ 商品CRUD
- ✅ 商品上下架
- ✅ 批量操作（上架/下架/删除）
- ✅ 商品列表查询（分页、筛选）

### 4. 订单管理
- ✅ 订单列表查询
- ✅ 订单详情查看
- ✅ 订单发货
- ✅ 订单取消

### 5. 营销管理
- ✅ 优惠券管理
- ✅ 活动管理
- ✅ 优惠券发放

### 6. 数据统计
- ✅ 概览统计
- ✅ 订单统计
- ✅ 商品统计
- ✅ 用户统计

### 7. 系统管理
- ✅ 操作日志
- ✅ 系统配置

## 项目结构

```
admin/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/xiaoins/admin/
│   │   │       ├── common/           # 通用模块（响应、异常、工具类）
│   │   │       ├── security/         # 安全模块（JWT、Spring Security配置）
│   │   │       ├── auth/             # 认证模块（登录、登出）
│   │   │       ├── user/             # 用户管理
│   │   │       ├── category/         # 商品分类
│   │   │       ├── product/          # 商品管理
│   │   │       ├── order/            # 订单管理
│   │   │       ├── marketing/        # 营销管理
│   │   │       ├── stats/            # 数据统计
│   │   │       ├── system/           # 系统管理
│   │   │       └── AdminApplication.java
│   │   └── resources/
│   │       ├── application.yml       # 主配置文件
│   │       ├── application-dev.yml   # 开发环境配置
│   │       ├── application-prod.yml  # 生产环境配置
│   │       └── db/migration/         # Flyway数据库迁移脚本
│   │           ├── V1__init_schema.sql
│   │           └── V2__seed_basic_data.sql
│   └── test/
├── pom.xml
└── README.md
```

## 快速开始

### 环境准备

1. 安装 JDK 21
2. 安装 MySQL 8.0+
3. 安装 Redis 6.0+
4. 安装 Maven 3.8+

### 数据库配置

1. 创建数据库：

```sql
CREATE DATABASE mall_admin CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 修改配置文件 `application-dev.yml`，配置数据库连接：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mall_admin?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
    username: root
    password: your_password
```

3. Flyway 会自动执行数据库迁移脚本，创建表结构并初始化数据

### 启动项目

1. 克隆项目到本地
2. 进入项目目录
3. 启动 Redis 服务
4. 执行以下命令：

```bash
cd admin
mvn clean install
mvn spring-boot:run
```

或者使用 IDE（如 IntelliJ IDEA）直接运行 `AdminApplication` 类。

### 访问系统

- **API文档地址**：http://localhost:8080/api/admin/v1/doc.html
- **健康检查**：http://localhost:8080/api/admin/v1/actuator/health

### 默认账号

系统初始化时会自动创建以下账号：

| 用户名 | 密码 | 角色 | 说明 |
|--------|------|------|------|
| admin | admin123 | 超级管理员 | 拥有所有权限 |
| operator | admin123 | 运营管理员 | 商品、订单、营销管理权限 |
| analyst | admin123 | 数据分析员 | 仅查看数据统计权限 |

## API接口

### 认证接口

- `POST /auth/login` - 用户登录
- `POST /auth/logout` - 用户登出

### 商品分类接口

- `GET /categories/tree` - 获取分类树
- `POST /categories` - 创建分类
- `PUT /categories/{id}` - 更新分类
- `DELETE /categories/{id}` - 删除分类

### 商品管理接口

- `GET /products` - 分页查询商品
- `GET /products/{id}` - 获取商品详情
- `POST /products` - 创建商品
- `PUT /products/{id}` - 更新商品
- `DELETE /products/{id}` - 删除商品
- `POST /products/{id}/publish` - 上架商品
- `POST /products/{id}/unpublish` - 下架商品
- `POST /products/batch-publish` - 批量上架
- `POST /products/batch-unpublish` - 批量下架
- `POST /products/batch-delete` - 批量删除

### 数据统计接口

- `GET /stats/overview` - 概览统计

更多接口详情请访问 API 文档页面。

## 统一响应格式

```json
{
  "code": "0",
  "message": "成功",
  "data": {},
  "traceId": "c9f8a7b6d5e4"
}
```

## 错误码说明

| 错误码 | 说明 |
|--------|------|
| 0 | 成功 |
| A0001 | 系统错误 |
| A0002 | 参数错误 |
| A0401 | 未认证，请先登录 |
| A0402 | Token无效或已过期 |
| A0301 | 无权限访问 |
| U1001 | 用户不存在 |
| U1003 | 密码错误 |
| P1001 | 商品不存在 |
| O1001 | 订单不存在 |

更多错误码请查看 `ResultCode` 枚举类。

## 开发指南

### 分层架构

- **Controller层**：接收请求，参数校验，调用Service
- **Service层**：业务逻辑处理
- **Mapper层**：数据库操作（MyBatis-Plus）
- **Entity层**：数据库实体
- **DTO层**：数据传输对象（请求）
- **VO层**：视图对象（响应）

### 代码规范

- 使用 Lombok 简化代码
- 使用 `@Valid` 进行参数校验
- 统一异常处理（`@ControllerAdvice`）
- 使用 Swagger 注解编写 API 文档
- 日志记录使用 slf4j

### 数据库迁移

使用 Flyway 进行数据库版本管理：

- 在 `src/main/resources/db/migration/` 目录下创建 SQL 脚本
- 文件命名规则：`V{version}__{description}.sql`
- 示例：`V3__add_user_avatar_column.sql`

## 配置说明

### JWT配置

```yaml
jwt:
  secret: YourSecretKey  # JWT密钥（生产环境请修改）
  expiration: 7200000    # 过期时间（2小时）
  header: Authorization  # 请求头名称
  prefix: Bearer         # Token前缀
```

### 业务配置

```yaml
business:
  order:
    timeout-minutes: 30      # 订单超时时间
    auto-receive-days: 7     # 自动收货时间
  login:
    max-fail-count: 3        # 最大失败次数
    lock-minutes: 30         # 锁定时间
```

## 部署

### Docker部署（待完善）

```bash
# 构建镜像
docker build -t mall-admin:1.0 .

# 运行容器
docker run -d -p 8080:8080 --name mall-admin mall-admin:1.0
```

### 生产环境注意事项

1. 修改 JWT 密钥
2. 配置生产环境数据库连接
3. 配置 Redis 连接
4. 关闭调试日志
5. 配置文件上传路径

## 待优化项

- [ ] 完善订单发货流程
- [ ] 实现退款审核功能
- [ ] 完善营销活动规则
- [ ] 增加更多统计图表
- [ ] 实现操作日志记录切面
- [ ] 添加 Redis 缓存优化
- [ ] 完善单元测试
- [ ] Docker化部署

## 许可证

Apache License 2.0

## 联系方式

- 作者：苏垒
- 邮箱：admin@example.com

---

**注意**：本项目为学习项目，不建议直接用于生产环境。

