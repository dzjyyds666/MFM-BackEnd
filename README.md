# 拌食忆项目开发日志

## 一、后端开发

### 1、项目搭建

- 创建maven父项目,保留pom.xml文件,管理依赖版本

```xml
<!-- 继承Spring Boot父项目 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.5</version>
    </parent>

    <!-- 注意：直接替换pom文件中原有的properties -->
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <mybatis-plus.version>3.5.3.1</mybatis-plus.version>
        <swagger.version>2.9.2</swagger.version>
        <jwt.version>0.11.2</jwt.version>
        <easycaptcha.version>1.6.2</easycaptcha.version>
        <minio.version>8.2.0</minio.version>
        <knife4j.version>4.1.0</knife4j.version>
        <aliyun.sms.version>2.0.23</aliyun.sms.version>
    </properties>

    <!--配置dependencyManagement统一管理依赖版本-->
    <dependencyManagement>
        <dependencies>
            <!--mybatis-plus-->
            <!--官方文档：https://baomidou.com/pages/bab2db/ -->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${mybatis-plus.version}</version>
            </dependency>

            <!--knife4j文档-->
            <!--官方文档：https://doc.xiaominfo.com/docs/quick-start -->
            <dependency>
                <groupId>com.github.xiaoymin</groupId>
                <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
                <version>${knife4j.version}</version>
            </dependency>

            <!--JWT登录认证相关-->
            <!--官方文档：https://github.com/jwtk/jjwt#install-jdk-maven -->
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt-api</artifactId>
                <version>${jwt.version}</version>
            </dependency>
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt-impl</artifactId>
                <scope>runtime</scope>
                <version>${jwt.version}</version>
            </dependency>
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt-jackson</artifactId>
                <scope>runtime</scope>
                <version>${jwt.version}</version>
            </dependency>

            <!--图形验证码-->
            <!--官方文档：https://gitee.com/ele-admin/EasyCaptcha -->
            <dependency>
                <groupId>com.github.whvcse</groupId>
                <artifactId>easy-captcha</artifactId>
                <version>${easycaptcha.version}</version>
            </dependency>

            <!--对象存储，用于存储图像等非结构化数据-->
            <!--官方文档：https://min.io/docs/minio/linux/developers/minio-drivers.html?ref=docs#java-sdk -->
            <dependency>
                <groupId>io.minio</groupId>
                <artifactId>minio</artifactId>
                <version>${minio.version}</version>
            </dependency>

            <!--阿里云短信客户端，用于发送短信验证码-->
            <!--官方文档：https://help.aliyun.com/document_detail/215759.html?spm=a2c4g.215759.0.0.49f32807f4Yc0y -->
            <dependency>
                <groupId>com.aliyun</groupId>
                <artifactId>dysmsapi20170525</artifactId>
                <version>${aliyun.sms.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

- 创建common模块,管理项目基础工具
- 创建model模块,管理项目实体类对象
- 创建web模块,编写业务代码

### 2、准备工作

#### 1.使用Mybatis-Plus代码生成器生成项目基础代码

```java
public class CodeGenerator {
    public static void main(String[] args) {

        String url = "jdbc:mysql://{hostname}:{port}/{database}?&characterEncoding=utf-8&userSSL=false";
        String username = "{username}";
        String password = "{password}";
        String outputUrl = "{包地址/src/main/java/}";
        String mapperLocation = "包地址/src/main/resources/mapper/";
        String tables = "{tablename,tablename,tablename}";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Aaron") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir(outputUrl); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("{包名}") // 设置父包名
                            //.moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix(); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
```

#### 2.定义全局统一返回类

```java
/**
 * 全局统一返回结果类
 */
@Data
public class Result<T> {

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private T data;

    public Result() {
    }

    private static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }


    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    public static <T> Result<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

    public static <T> Result<T> fail(Integer code,String message) {
        Result<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}

```

```java
/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    PARAM_ERROR(202, "参数不正确"),
    SERVICE_ERROR(203, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    ILLEGAL_REQUEST(205, "非法请求"),
    REPEAT_SUBMIT(206, "重复提交"),
    DELETE_ERROR(207, "请先删除子集"),

    ADMIN_ACCOUNT_EXIST_ERROR(301, "账号已存在"),
    ADMIN_CAPTCHA_CODE_ERROR(302, "验证码错误"),
    ADMIN_CAPTCHA_CODE_EXPIRED(303, "验证码已过期"),
    ADMIN_CAPTCHA_CODE_NOT_FOUND(304, "未输入验证码"),


    ADMIN_LOGIN_AUTH(305, "未登陆"),
    ADMIN_ACCOUNT_NOT_EXIST_ERROR(306, "账号不存在"),
    ADMIN_ACCOUNT_ERROR(307, "用户名或密码错误"),
    ADMIN_ACCOUNT_DISABLED_ERROR(308, "该用户已被禁用"),
    ADMIN_ACCESS_FORBIDDEN(309, "无访问权限"),

    ADMIN_APARTMENT_DELETE_ERROR(310,"请先删除房间"),

    APP_LOGIN_AUTH(501, "未登陆"),
    APP_LOGIN_PHONE_EMPTY(502, "手机号码为空"),
    APP_LOGIN_CODE_EMPTY(503, "验证码为空"),
    APP_SEND_SMS_TOO_OFTEN(504, "验证法发送过于频繁"),
    APP_LOGIN_CODE_EXPIRED(505, "验证码已过期"),
    APP_LOGIN_CODE_ERROR(506, "验证码错误"),
    APP_ACCOUNT_DISABLED_ERROR(507, "该用户已被禁用"),


    TOKEN_EXPIRED(601, "token过期"),
    TOKEN_INVALID(602, "token非法");


    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

```

#### 3.定义全局异常处理类

```java
// 自定义异常类
@Data
public class MFMException extends RuntimeException {

    private Integer code;
    public MFMException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public MFMException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}

```

```java
// 全局异常处理类
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> error(Exception e)
    {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(MFMException.class)
    @ResponseBody
    public Result<String> error(MFMException e)
    {
        return Result.fail(e.getCode(),e.getMessage());
    }
}

```

#### 4.创建Jwt工具类,生成和解析JwtToken

```java
public class JWTutils {

    // token过期时间
    private static long tokenExpiresTime = 60 * 60 * 24 * 24 * 1000L;

    private static SecretKey tokenSecretKey = Keys.hmacShaKeyFor(DigestUtils.md5("Aaron"));

    public static String createToken(Long userId,String role){
        String token = Jwts.builder()
                // 设置主题
                .setSubject("USER_INFO")
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiresTime))
                // 设置用户id
                .claim("userId", userId)
                // 设置角色
                .claim("role", role)
                // 设置签名
                .signWith(tokenSecretKey)
                // 生成token
                .compact();
        return token;
    }

    public static Claims parseToken(String token){
        
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(tokenSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}

```

#### 5.定义拦截器解析token,并把解析出来的信息存储到线程中

```java
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access-token");
        Claims claims = JWTutils.parseToken(token);
        Long userId = claims.get("userId",Long.class);
        String role = claims.get("role",String.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setRole(role);
        LoginHolder.setLoginUser(userInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginHolder.remove();
    }
}

```

```java
@Configuration
public class WebMvcConfigration implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authenticationInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login/**");
    }
}
```

```java
public class LoginHolder {

    private static final ThreadLocal<UserInfo> Threadlocal = new ThreadLocal<>();

    public static void setLoginUser(UserInfo userInfo) {
        Threadlocal.set(userInfo);
    }

    public static UserInfo getLoginUser() {
        return Threadlocal.get();
    }

    public static void remove() {
        Threadlocal.remove();
    }
}

```



### 3、项目开发

#### 1.登录接口

