@echo off
setlocal
set "ROOT=%~dp0.."
set "DATA=%~1"
set "RESULT=%~2"
if "%DATA%"=="" set "DATA=GoCJ_v1\Enhanced_GoCJ\GoCJ_Dataset_1000.csv"
if "%RESULT%"=="" set "RESULT=dynamic_sla-de-ralba\results\sla-deralba-gocj.xlsx"
cd /d "%ROOT%"
set "BUNDLED_MVN=%ROOT%\.tools\maven-extract\apache-maven-3.10.0\bin\mvn.cmd"
if exist "%BUNDLED_MVN%" (
  call "%BUNDLED_MVN%" "-Dmaven.repo.local=%ROOT%\.m2\repository" -f dynamic_sla-de-ralba\pom.xml compile org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.args=%DATA% %RESULT%"
) else (
  where mvn.cmd >nul 2>nul
  if errorlevel 1 (
    echo Maven was not found. Install Maven 3.x and add mvn.cmd to PATH.
    exit /b 1
  )
  call mvn.cmd -f dynamic_sla-de-ralba\pom.xml compile org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.args=%DATA% %RESULT%"
)
endlocal
