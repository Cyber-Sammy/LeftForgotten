@echo off
cd /D "C:\Users\rodx1\AppData\Roaming\ModrinthApp\profiles\NeoForge 1.21.1\mods"
echo Deleting old test jar...
del /q LeftForgotten*.jar >nul

echo Building...
cd /D "%~dp0.."
powershell -ExecutionPolicy Bypass -c "./gradlew :neoforge:build"

echo Moving files...
move "%~dp0..\neoforge\build\libs\*.jar" "C:\Users\rodx1\AppData\Roaming\ModrinthApp\profiles\NeoForge 1.21.1\mods" >nul

echo Deleting shadow jars...
cd /D "C:\Users\rodx1\AppData\Roaming\ModrinthApp\profiles\NeoForge 1.21.1\mods"
del /q *dev-shadow.jar >nul

echo Done.
timeout 5 >nul
exit