call javac -sourcepath .\src -d build\classes -cp .\libs\commons-lang3-3.12.0.jar;.\libs\joda-time-2.10.10.jar src\ua\com\alevel\user\UserName.java src\ua\com\alevel\markup\Remainder.java src\ua\com\alevel\Main.java
call java -cp build\classes\;.\libs\commons-lang3-3.12.0.jar;.\libs\joda-time-2.10.10.jar ua.com.alevel.Main
