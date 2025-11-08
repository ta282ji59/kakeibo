# 家計簿アプリ

## Springの起動方法
```bash
 ./mvnw spring-boot:run
```

## dbのセットアップ
```bash
user_name=XXXXXXX
user_password=********


docker run --name kakeibo-mysql \
-e MYSQL_ROOT_PASSWORD=rootpassword \
-e MYSQL_DATABASE=kakeibo_db \
-e MYSQL_USER=${user_name} \
-e MYSQL_PASSWORD=${user_password} \
-p 3306:3306 \
-d mysql:8.0

```