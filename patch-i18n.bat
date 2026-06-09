@echo off
chcp 65001 >nul
powershell -NoProfile -ExecutionPolicy Bypass -File "E:\syson-rg\syson\patch-i18n.ps1"
