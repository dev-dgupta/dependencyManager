Put here the SQL schema for the target database.

How to name upgrade scripts?

- Upgrade scripts must start with a number. The format <date><incremental>_<script description>.sql is a good example.

- we have used the pattern, yyyyMMddnnn_<script description> where yyyyMMdd represents the date, nnn is a running sequence number 
  of scripts with the same date. Always sequence number starts as 001 on a given date. 
  If more than one script is created with the same date, then it goes as 001, 002, 003 … and so on.

Full details are here: https://confluence.iontrading.com/pages/viewpage.action?title=Packaging+and+Use+of++DB+Deploy+Automation+Tool&spaceKey=PDRR


NOTE IF YOU DON'T NEED DATABASE
- remove the 'dbdeploy_scripts' folder under '<project root dir>/src/main/resources/ion-installer'
  
  
  