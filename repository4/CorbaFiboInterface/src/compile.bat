:: Directorio de java SDK
SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_241"

:: direccion de las fuentes SRC de proyecto netbeans IDL "CorbaFiboInterface"
SET PATH_IDL="C:\Users\yo_BM\3D Objects\Ramon Parcial 3\corba-main\corba-main\CorbaFiboInterface\src"

:: nombre del archivo IDL
SET IDL_NAME=fibonacci.idl

:: comando para compilar
%JAVA_HOME%\bin\idlj -fall -td %PATH_IDL% %PATH_IDL%\%IDL_NAME%