@ECHO OFF



REM Change to the script directory if needed
IF "%CD%" == "%~dp0" (
	SET DIR_CHANGED=0
) ELSE (
	ECHO Changing to the script directory...
	PUSHD "%~dp0"
	SET DIR_CHANGED=1
	ECHO.
	ECHO.
)



ECHO Getting the Space Track credentials...

IF NOT DEFINED SPACE_TRACK_USER_NAME (
	SET /P SPACE_TRACK_USER_NAME="	User Name: "
) ELSE (
    ECHO 	Using the existing user name
)

IF NOT DEFINED SPACE_TRACK_PASSWORD (
	SET /P SPACE_TRACK_PASSWORD="	Password: "
) ELSE (
    ECHO 	Using the existing password
)

ECHO.
ECHO.



ECHO Running the build...
mvn clean install -Dspace.track.user.name="%SPACE_TRACK_USER_NAME%" -Dspace.track.password="%SPACE_TRACK_PASSWORD%"



REM Change back to the original directory if needed
IF %DIR_CHANGED% == 1 (
	ECHO Changing back to the original directory...
	POPD
)

SET "DIR_CHANGED="
ECHO.
ECHO.



REM Pause for input
PAUSE
