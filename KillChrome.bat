taskkill /im chrome* /f
@echo off
cd %temp%
for /d %%D in (scoped*) do rd /s /q "%%D"
del /s