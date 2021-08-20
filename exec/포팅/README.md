# 포팅 안내

1. app.zip 압축해제

2. ```sh
   docker-compose up -d --build
   ```
3. ```sh
   docker-compose down --rmi all
   ```
   

## app 구조 -> Docker-compose 기반 

```
📦 
└─ db
   ├─ conf.d
   │  └─ my.cnf
   ├─ initdb.d
   │  └─ init_sql.sql
   ├─ frontend
   ├─ target
   │  └─ orange-0.0.1-SNAPSHOT.jar
   ├─ docker-compose.yml
   └─ Dockerfile
```

## project_orange.abb

- 안드로이드 번들 파일
