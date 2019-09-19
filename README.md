## B站码匠社区视频实践

## 资料
[Spring 文档](https://spring.io/guides)
[Spring web 文档](https://spring.io/guides/gs/serving-web-content/)
[Es文档](https://elasticsearch.cn/explore)
[Github deploy key文档](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
[Bootstrap文档](https://v3.bootcss.com/getting-starrted/)
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app)

##工具
[Git](https://git-scm.com/download)
[Visual-paradigm](https://www.visual-paradigm.com)

## 脚本

``` sql 
CREATE CACHED TABLE PUBLIC.USER(
    ID INT DEFAULT NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_CFF8FB43_241A_47BD_A06F_F0D3C0BCEF1B,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(50),
    TOKEN CHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFIDE BIGINT
)

```