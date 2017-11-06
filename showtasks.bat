call runcrud
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo Error during calling runcrud file
goto fail

:browser
echo Program will wait for 5 seconds to finish running Tomcat Server
sleep 5
start "C:\Program Files (x86)\Mozilla Firefox\firefox.exe" http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Couldn't open browser with a web page
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished
