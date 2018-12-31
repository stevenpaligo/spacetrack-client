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



ECHO Getting the SpaceTrack credentials...

IF NOT DEFINED SPACETRACK_USER_NAME (
	SET /P SPACETRACK_USER_NAME="-User Name: "
) ELSE (
    ECHO 	Using the existing user name
)

IF NOT DEFINED SPACETRACK_PASSWORD (
	SET /P SPACETRACK_PASSWORD="-Password: "
) ELSE (
    ECHO 	Using the existing password
)

ECHO.
ECHO.



ECHO Running the build...
mvn clean install -Dspacetrack.user.name="%SPACETRACK_USER_NAME%" -Dspacetrack.password="%SPACETRACK_PASSWORD%"



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
