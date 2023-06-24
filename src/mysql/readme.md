# Mysql
## 安装Mysql
```shell
# 开启容器
docker run --name mysql -v local-dir/mysql-metadata:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:latest

# 进入容器
docker exec -it containerId sh

# 进入mysql
mysql -uroot -p
```

## 基本操作

