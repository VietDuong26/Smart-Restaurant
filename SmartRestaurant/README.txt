#chạy tester
set TOKEN=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwOTM0Mjc0NjM1Iiwicm9sZXMiOltdLCJpYXQiOjE3NzY5MjIwNTEsImV4cCI6MTc3NjkyMjk1MX0.L-Gixcn4vTUPZLKKX6pYHpbp2TBSIu_XQWhvyGMgab8yJb1DqF-0RMp3gcH6aCrrjEgxpg2EzUTKIvHM2MqlDQ
py -m schemathesis.cli run http://localhost:8080/v3/api-docs ^
  -H "Authorization: Bearer %TOKEN%"
  email: smartrestaurant130907@gmail.com
  pass:smartrestaurant@1309